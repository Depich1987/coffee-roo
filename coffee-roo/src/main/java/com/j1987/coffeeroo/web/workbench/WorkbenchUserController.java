package com.j1987.coffeeroo.web.workbench;

import java.util.List;

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

import com.j1987.coffeeroo.domain.JUser;
import com.j1987.coffeeroo.framework.UserNotFoundException;
import com.j1987.coffeeroo.services.dao.UserService;
import com.j1987.coffeeroo.services.security.JSecurityServiceImpl;
import com.j1987.coffeeroo.web.form.ChangePasswordForm;

@Controller
@RequestMapping(value = "/workbench/profile")
public class WorkbenchUserController {

	
	private static final String CHANGE_PASSWORD_VIEW = "workbench/profile/changepassword";
	private static final String SHOW_VIEW = "workbench/profile/show";
	
	private static Logger logger = Logger.getLogger(WorkbenchUserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	JSecurityServiceImpl securityService;
	
	public WorkbenchUserController() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * display the show view of current user
	 * @param userName
	 * @param httpServletRequest
	 * @param uiModel
	 * @return
	 * @throws UserNotFoundException
	 */
    @RequestMapping(value = "/{userName}", produces = "text/html")
    public String show(@PathVariable("userName") String userName, Model uiModel) throws UserNotFoundException {
        
    	List<JUser> users = userService.findUserByUserNameEquals(userName);
    	
    	JUser user = users.get(0);
    	
    	if(user == null){

	    	 String errMsg = ">>>> show() - Failed finding User with username : " + userName  ;
	   	   	 logger.error(errMsg);
	   	   	 throw new UserNotFoundException("Aucun utilisateur avec le pseudo [ "+ userName +" ] au sein de votre compagnie "); 

    	}else{

		   	String currentUserName = securityService.currentUser();
		   	
		   	if(currentUserName == userName){
		   		
		   	 String errMsg = ">>>> show() - Failed displaying User with an other username : [current User :"+ currentUserName + ", given userName"+ userName  ;
	   	   	 logger.error(errMsg);
	   	   	 throw new UserNotFoundException("Vous essayez de consulter les informations d'un autre utilisateur du systeme [ "+ userName +" ] de votre compagnie "); 
		   		
		   	}
    		
    		
    	}

    	uiModel.addAttribute("user", user);
        uiModel.addAttribute("itemId", userName);
        
        return SHOW_VIEW;
    }
	
	
    /**
     * Special case. So the current user can execute this piece of code
     * */
    @RequestMapping(value="/changepassword/{userName}", method = RequestMethod.POST, produces = "text/html")
    public String changePassword(
    		@Valid ChangePasswordForm changePasswordForm, 
    		BindingResult bindingResult,
    		HttpServletRequest httpServletRequest,
    		@PathVariable("userName") String  userName, Model uiModel) {
    	
    	List<JUser> users = userService.findUserByUserNameEquals(userName);
    	if (!users.isEmpty()) {
    		
    		JUser user = users.get(0);
    		String password = user.getPassword();
    		
    		String oldPwd = changePasswordForm.getOldPassword();
    		String encodedOldPwd =  securityService.encodePassword(oldPwd);
    		
    		String newPwd = changePasswordForm.getNewPassword();
    		String confirmPwd = changePasswordForm.getConfirmNewPassword();
    		
    		if (!password.equals(encodedOldPwd) || (StringUtils.isEmpty(newPwd)) || (!newPwd.equals(confirmPwd)) ) {
				// failed
    	        uiModel.addAttribute("userName", securityService.currentUser());
    	        uiModel.addAttribute("changePasswordForm", new ChangePasswordForm());
    	        uiModel.addAttribute("currentNav", "changepassword");

    			return CHANGE_PASSWORD_VIEW;
    			
			} else {
				String encodedNewPwd = securityService.encodePassword(newPwd);
				user.setPassword(encodedNewPwd);
				userService.update(user);
		        
	    		logger.debug("changePassword() - Changed user's password. "); 
			}
		}
    	
        uiModel.addAttribute("changepassword", "true");
    	
        return SHOW_VIEW;
    }
	
	
    /**
     * Allow to change the password of current user
     * @param uiModel
     * @return
     */
    @RequestMapping(value = "/changepassword/{userName}", params = "form", produces = "text/html")
    public String changePasswordForm(Model uiModel) {
        
    	uiModel.addAttribute("userName", securityService.currentUser());
        uiModel.addAttribute("changePasswordForm", new ChangePasswordForm());
        uiModel.addAttribute("currentNav", "changepassword");
        return CHANGE_PASSWORD_VIEW;
    }

}
