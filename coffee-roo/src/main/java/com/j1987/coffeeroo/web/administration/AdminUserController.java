package com.j1987.coffeeroo.web.administration;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.j1987.coffeeroo.domain.JCompany;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JRole;
import com.j1987.coffeeroo.domain.JUser;
import com.j1987.coffeeroo.framework.FactoryNotFoundException;
import com.j1987.coffeeroo.framework.JUtils;
import com.j1987.coffeeroo.framework.UserNotFoundException;
import com.j1987.coffeeroo.services.dao.CompanyService;
import com.j1987.coffeeroo.services.dao.FactoryService;
import com.j1987.coffeeroo.services.dao.UserService;
import com.j1987.coffeeroo.services.security.JSecurityService;
import com.j1987.coffeeroo.web.form.AssignFactoryForm;
import com.j1987.coffeeroo.web.form.AssignRoleForm;

@Controller
@RequestMapping(value = "/administration/users")
public class AdminUserController {
	
	private static final String UPDATE_VIEW = "administration/user/update";
	private static final String SHOW_VIEW = "administration/user/show";
	private static final String LIST_VIEW = "administration/user/list";
	private static final String CREATE_VIEW = "administration/user/create";
	
	private static final String ROLE_ASSIGMENT_VIEW = "administration/user/assignrole";
	private static final String FACTORY_ASSIGNMENT_VIEW = "administration/user/assignfactory";
	
	private static Logger logger = Logger.getLogger(AdminUserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private JSecurityService securityService; 

	public AdminUserController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JUser user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            logger.debug("create() - the user object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
		
        uiModel.asMap().clear();
        String passpwd = user.getPassword();
        String encodedPwd = securityService.encodePassword(passpwd);
        user.setPassword(encodedPwd);
        
        userService.persist(user);
        
        logger.debug("create()- a new user has been created with success !");
        
        return "redirect:/administration/users/" + encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	
        populateEditForm(uiModel, new JUser());
        
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) throws UserNotFoundException {
    	
    	JUser user = userService.findUserById(id);
    	
    	if(user == null){

	    	String errMsg = ">>>> show() - Failed finding User with id : " + id  ;
	   	   	 logger.error(errMsg);
	   	   	 throw new UserNotFoundException("Aucun utilisateur avec l'identifiant [ "+id+" ] au sein de votre entreprise "); 

    	}
    	
    	List<JUser> users = new ArrayList<JUser>();
    	
    	users.add(user);
    	
    	users = retrieveEnrichedUsers(users);
    	
    	uiModel.addAttribute("user", user);
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("currentNav", "users");
        
        return SHOW_VIEW;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       
    	if (page != null || size != null) {
            
    		int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("users", retrieveEnrichedUsers(userService.findUserEntriesWithoutFirstUser(firstResult, sizeNo)));
            
            float nrOfPages = (float) userService.countUsersWithoutFirstUser() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       
    	} else {
            uiModel.addAttribute("users", retrieveEnrichedUsers(userService.findAllUsersWithoutFirstUser()));
        }
    	
    	uiModel.addAttribute("currentNav", "users");
    	
        return LIST_VIEW;
    }
    
    private List<JUser> retrieveEnrichedUsers(List<JUser> users) {
    	
    	List<JUser> enrichedUsers = new ArrayList<JUser>();
    	for (JUser dtUser : users) {
    		List<JRole> roles = dtUser.getRoles();
    		
    		if (!roles.isEmpty()) {
    			
    			JRole role = roles.get(0);
				Map<String, String> namesMap = JUtils.uiRoleNamesMap();
				String roleName = namesMap.get(role.getName());
				dtUser.setRoleName(roleName);
				
				
				
				if(roleName.equals(JUtils.DB_UI_ROLE_ADMIN) || roleName.equals(JUtils.DB_UI_ROLE_FACTORY_MANAGER)){
					
					if(!dtUser.getCompanies().isEmpty()){
						dtUser.setFactoryNames("*");
					}
				}
				
				List<JFactory> factories = dtUser.getFactories();
				
				if (!factories.isEmpty()) {
					String factoryNames =  JUtils.retrieveObjectPropertiesAsString(factories);
					dtUser.setFactoryNames(factoryNames);
				}
    		}
    		
    		enrichedUsers.add(dtUser);
    	}
    	
    	return enrichedUsers;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JUser user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, user);
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();
        
        String passpwd = user.getPassword();
        String encodedPwd = securityService.encodePassword(passpwd);
        user.setPassword(encodedPwd);
        userService.update(user);
        
        return "redirect:/administration/users/" + encodeUrlPathSegment(user.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    	
        populateEditForm(uiModel, userService.findUserById(id));
        return UPDATE_VIEW;
        
    }
    
    @RequestMapping(value =  "/assignrole", method = RequestMethod.POST, produces = "text/html")
    public String assignRole(@Valid AssignRoleForm  assignRoleForm, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
    	
        if (bindingResult.hasErrors() || StringUtils.isEmpty(assignRoleForm.getRoleName())) {
        	
        	populateAssignRoleForm(assignRoleForm.getUserName(), uiModel);
            logger.error(">>>> create() - Failed assigning Role. Invalid values. Role Name might have not been selected");
            return ROLE_ASSIGMENT_VIEW;
        
        }
        
        uiModel.asMap().clear();
        
        String userName = assignRoleForm.getUserName();
        List<JUser> users = userService.findUserByUserNameEquals(userName);
        if (!users.isEmpty()) {
        	JUser user = users.get(0);
			
			Map<String, String> roleNamesMap = JUtils.dbRoleNamesMap();
			
			String roleName = assignRoleForm.getRoleName();
			String dbRoleName = roleNamesMap.get(roleName);
			List<JRole> roles = userService.findRoleByRoleNameEquals(dbRoleName);
			
			if (!roles.isEmpty()) {
				JRole role = roles.get(0);
				// TODO - REVIEW - Replace the role ??
				user.getRoles().clear();
				user.getRoles().add(role);
				
				role.getUsers().add(user);
				user.setRoleName(roleName);
				
				if(roleName.equals(JUtils.DB_UI_ROLE_ADMIN)){
					JCompany company = companyService.findCompanyById(Long.valueOf("1"));
					user.getCompanies().add(company);
					company.getUsers().add(user);
				}
				
				userService.merge(user);
				
			}
		}
        
        return "redirect:/administration/users/list?size=10";
    }
    
    @RequestMapping(value = "/assignrole/{userName}", params = "form", produces = "text/html")
    public String assignRoleForm(@PathVariable("userName")String userName,Model uiModel) {
    	populateAssignRoleForm(userName, uiModel);
        return ROLE_ASSIGMENT_VIEW;
    }
    
    @RequestMapping(value = "/assignfactory", method = RequestMethod.POST, produces = "text/html")
    public String assignFactory(@Valid AssignFactoryForm assignFactoryForm, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) throws UserNotFoundException, FactoryNotFoundException{
       
    	if (bindingResult.hasErrors()) {
        	List<JUser> candidates = userService.findUserByUserNameEquals(assignFactoryForm.getUserName());
        	if(candidates.isEmpty()){
        		
            	JUser user = candidates.get(0);
            	populateEditAssignFactoryForm(uiModel, user ,assignFactoryForm);
                logger.error("assignFactory() - >>>>  FAILED : The AssignFactoryForm is NOT valid");
        	}
        	
          return FACTORY_ASSIGNMENT_VIEW;
    	}
    	
    	List<JUser> candidates = userService.findUserByUserNameEquals(assignFactoryForm.getUserName());
    	
    	if(candidates.isEmpty()){
    		String errMsg = "assignFactory () - >>>> show() - Failed finding User with username : " + assignFactoryForm.getUserName()  ;
	   	   	 logger.error(errMsg);
	   	   	 throw new UserNotFoundException("Aucun utilisateur avec le pseudo [ "+assignFactoryForm.getUserName()+" ] au sein de votre compagnie "); 
    	}
    	
    	List<JFactory> factories = factoryService.findFactoriesByCodeEquals(assignFactoryForm.getFactoryCode());
    	
    	if(factories.isEmpty()){
    		String errMsg = "assignFactory () -  >>>> show() - Failed finding Factory with code : " + assignFactoryForm.getFactoryCode()  ;
	   	   	 logger.error(errMsg);
	   	   	 throw new FactoryNotFoundException("Aucune Usine avec le code [ "+assignFactoryForm.getFactoryCode()+" ] au sein de votre compagnie ");
    	}
    
    	JFactory factory = factories.get(0);
    	JUser user = candidates.get(0);
    	
    	List<JFactory> userFactories = user.getFactories();
    	
    	if (!userFactories.contains(factory)){
    		user.getFactories().add(factory);
    		
    		userService.merge(user);
    		
	        
    		logger.debug("assignFactory() - Factory assignment has been done successfully!");
    	}
    	
    	return "redirect:/administration/users/list?size=10";
    }
    
    @RequestMapping(value = "/assignfactory/{userName}",  params = "form", produces = "text/html")
    public String assignFactoryForm(@PathVariable("userName")String userName, Model uiModel){
    	
    	List<JUser> candidates = userService.findUserByUserNameEquals(userName);
    	if(!candidates.isEmpty()){
        	AssignFactoryForm assignFactoryForm = new AssignFactoryForm();
        	JUser user = candidates.get(0);
        	
        	populateEditAssignFactoryForm(uiModel,user,assignFactoryForm);
    	}

    	return FACTORY_ASSIGNMENT_VIEW;

    }
    
    void populateEditAssignFactoryForm(Model uiModel, JUser user, AssignFactoryForm assignFactoryForm){

    	assignFactoryForm.setUserName(user.getUserName());
    	
    	String factoryCode =  null;
        JFactory factory = null;
        List<JFactory> userFactories = user.getFactories();
        
        if (!userFactories.isEmpty()) {
        	factory = userFactories.get(0);
        	factoryCode = factory.getCode();
        	
		}

        assignFactoryForm.setFactoryCode(factoryCode);
        
        List<JFactory> factories =  new ArrayList<JFactory>();

        factories.addAll(factoryService.findAllFactories());

    	uiModel.addAttribute("assignFactoryForm", assignFactoryForm);
    	uiModel.addAttribute("factories", factories);
    	uiModel.addAttribute("itemId",user.getId());
    	uiModel.addAttribute("currentNav", "users");
    }
    
    
    @RequestMapping(value = "/unassign/{userName}", method = RequestMethod.GET, produces = "text/html")
    public String unassignUserPrivileges(@PathVariable("userName")String userName, ServletRequest httpServletRequest) throws UserNotFoundException{
    	
    	List<JUser> candidates = userService.findUserByUserNameEquals(userName);
    	if(candidates.isEmpty()){
    		String errMsg = "assignFactory () - >>>> show() - Failed finding User with username : " + userName ;
	   	   	 logger.error(errMsg);
	   	   	 throw new UserNotFoundException("Aucun utilisateur avec le pseudo [ "+userName+" ] au sein de votre compagnie "); 
    	}
    	
    	JUser user = candidates.get(0);
    	
    	if(user != null){
    		
    		//remove all companies of current user.
    		List<JCompany> userCompanies = user.getCompanies();
    		if(!userCompanies.isEmpty()){
    			
    			for (JCompany jCompany : userCompanies) {
					jCompany.getUsers().remove(user);
				}
    		}
    		
    		
    		// remove all factories of current user.
    		List<JFactory> userFactories = user.getFactories();
    		if(!userFactories.isEmpty()){
    			
    			for (JFactory factory : userFactories) {
    				factory.getUsers().remove(user);
				}
    		}
    		
    		//set the user's companies and factories to null
    		user.setCompanies(null);
    		user.setFactories(null);
    		
        	userService.merge(user);
    		logger.debug("unassignUserPrivileges() -  the unassignment has been done successfully!");
    		
    	}
    	

    	return  "redirect:/administration/users/list?size=10";
    	
    }
    
    /**
     * 
     * */
    private void populateAssignRoleForm(String userName, Model uiModel) {
        List<String> roleNames = new ArrayList<String>();
        Map<String, String> roleNamesMap = JUtils.uiRoleNamesMap();
        roleNames.add(roleNamesMap.get(JUtils.DB_ROLE_ADMIN));
        roleNames.add(roleNamesMap.get(JUtils.DB_ROLE_FACTORY_MANAGER));
        roleNames.add(roleNamesMap.get(JUtils.DB_ROLE_FACTORY_AGENT));
        
        AssignRoleForm assignRoleForm = new AssignRoleForm();
        assignRoleForm.setUserName(userName);
        
        uiModel.addAttribute("assignRoleForm", assignRoleForm);
        uiModel.addAttribute("roleNames", roleNames);

        uiModel.addAttribute("currentNav", "users");
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        
    	JUser user = userService.findUserById(id);
        user.remove();
        uiModel.asMap().clear();
        
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        
        return "redirect:/administration/users/list";
    }
    
    void populateEditForm(Model uiModel, JUser user) {
        uiModel.addAttribute("user", user);
        uiModel.addAttribute("currentNav", "users");
    }
    
    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }

}
