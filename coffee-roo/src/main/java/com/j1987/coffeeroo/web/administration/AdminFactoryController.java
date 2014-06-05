package com.j1987.coffeeroo.web.administration;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;



import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JCompany;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.services.dao.BridgeService;
import com.j1987.coffeeroo.services.dao.CompanyService;
import com.j1987.coffeeroo.services.dao.FactoryService;


/**
 * It's an implementation of <tt>Factory</tt>'s controller.It permits to create, update, show, list or delete {@link JFactory}
 * @author Franck Janel Agah
 *
 */
@Controller
@RequestMapping(value = "/administration/factories")
public class AdminFactoryController {
	
	private static final String UPDATE_VIEW = "administration/factory/update";
	private static final String SHOW_VIEW = "administration/factory/show";
	private static final String LIST_VIEW = "administration/factory/list";
	private static final String CREATE_VIEW = "administration/factory/create";
	
	private static final String CREATE_BRIDGE_VIEW = "administration/factory/createbridge";
	private static final String SHOW_BRIDGE_VIEW = "administration/factory/showbridge";
	private static final String UPDATE_BRIDGE_VIEW = "administration/factory/updatebridge";
	
	
	private static Logger logger = Logger.getLogger(AdminFactoryController.class);
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private BridgeService bridgeService;
	
	@Autowired
	private CompanyService companyService;

	public AdminFactoryController() {
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Validate Factory Code before create - UNIQUELESS Implementation
     * @param factoryCode
     * @return
     */
	@RequestMapping(value = "/validatefactorycode", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody  boolean validateFactoryCode(@RequestParam("factoryCode")String factoryCode){
		boolean valid = false;
		List<JFactory> factories = factoryService.findFactoriesByCodeEquals(factoryCode);
		if(factories.isEmpty()) valid = true;
		return valid;
	}
	
	@RequestMapping(value = "/validatebridgecode", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody  boolean validateBridgeCode(@RequestParam("bridgeCode")String bridgeCode){
		boolean valid = false;
		List<JBridge> bridges = bridgeService.findBridgesByCodeEquals(bridgeCode);
		if(bridges.isEmpty()) valid = true;
		return valid;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JFactory factory, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, factory);
            logger.debug("create() - the factory object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
		
        uiModel.asMap().clear();
        JCompany company = companyService.findCompanyById(Long.valueOf("1"));
        if(company != null)		factory.setCompany(company );
        
        factoryService.persist(factory);
        
        logger.debug("create()- a new factory has been created with success !");
        
        return "redirect:/administration/factories/" + encodeUrlPathSegment(factory.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	
        populateEditForm(uiModel, new JFactory());
        
        return CREATE_VIEW;
    }
    
    /**
     * 
     * */
    @RequestMapping(value= "/createbridge/{id}", params = "form", produces = "text/html")
    public String createBridgeForm(@PathVariable("id")Long id, Model uiModel) {
    	
    	JFactory factory = factoryService.findFactory(id);
        
    	JBridge bridge =  new JBridge();
        bridge.setFactory(factory);
        
        String baseBridgeCode = factory.getCode();
        Long number = bridgeService.countBridgesInFactory(factory);
        number+=1;
        bridge.setCode(baseBridgeCode+String.format("%02d", number));
        populateEditBridgeForm(uiModel, bridge);
        
        return CREATE_BRIDGE_VIEW;
    }
    
    /**
     * 
     * */
    @RequestMapping(value="/createbridge", method = RequestMethod.POST)
    public String createBridge(@Valid JBridge bridge, Model uiModel, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        
    	if (bindingResult.hasErrors()) {
    		populateEditBridgeForm(uiModel, bridge);
    		logger.debug("create() - the bridge object is invalid. Redirect to create view");
            return CREATE_BRIDGE_VIEW;
        }
    	
        uiModel.asMap().clear();
        JFactory factory = bridge.getFactory();
        
        factory.getBridges().add(bridge);
        factoryService.merge(factory);
        
        return "redirect:/administration/factories/" + encodeUrlPathSegment(bridge.getFactory().getId().toString(), httpServletRequest);
    }
    
    
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	
        uiModel.addAttribute("factory", factoryService.findFactory(id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("currentNav", "factories");
        
        return SHOW_VIEW;
    }
    
    
    @RequestMapping(value ="/bridges/{id}", produces = "text/html")
    public String showBridge(@PathVariable("id") Long id, Model uiModel) {
    	JBridge  bridge = bridgeService.findBridge(id);
        uiModel.addAttribute("bridge", bridge);
        uiModel.addAttribute("factory", bridge.getFactory());
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("currentNav", "bridges");
        return SHOW_BRIDGE_VIEW;
    }
    
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       
    	if (page != null || size != null) {
            
    		int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("factories", factoryService.findFactoryEntries(firstResult, sizeNo));
            
            float nrOfPages = (float) factoryService.countFactories() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       
    	} else {
            uiModel.addAttribute("factories", factoryService.findAllFactories());
        }
    	
    	uiModel.addAttribute("currentNav", "factories");
    	
        return LIST_VIEW;
    }
    
    
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JFactory factory, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, factory);
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();

        factoryService.update(factory);
        
        return "redirect:/administration/factories/" + encodeUrlPathSegment(factory.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    	
        populateEditForm(uiModel, factoryService.findFactory(id));
        return UPDATE_VIEW;
        
    }
    
    @RequestMapping(value ="/updatebridge/{id}", params = "form", produces = "text/html")
    public String updateBridgeForm(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
    	
    	JBridge bridge = bridgeService.findBridge(id);
    	populateEditBridgeForm(uiModel, bridge);
    	
        return UPDATE_BRIDGE_VIEW;
    }
	
	
    @RequestMapping(value ="/updatebridge/{bridgeId}", method = RequestMethod.POST, produces = "text/html")
    public String updateBridge(
    		@PathVariable("bridgeId") Long bridgeId, @Valid JBridge bridge , BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
    	if (bindingResult.hasErrors()) {
        	JBridge bridgeFound = bridgeService.findBridge(bridgeId);
            uiModel.addAttribute("bridge", bridgeFound);
            uiModel.addAttribute("factory", bridge.getFactory());
            return UPDATE_BRIDGE_VIEW;
        }
    	
        uiModel.asMap().clear();
        
    	bridge.setId(bridgeId);
        bridgeService.update(bridge);

        return "redirect:/administration/bridges/" + encodeUrlPathSegment(bridge.getId().toString(), httpServletRequest);
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        
    	JFactory factory = factoryService.findFactory(id);
        factory.remove();
        uiModel.asMap().clear();
        
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        
        return "redirect:/administration/factories/list";
    }
    
    void populateEditForm(Model uiModel, JFactory factory) {
        uiModel.addAttribute("factory", factory);
        uiModel.addAttribute("currentNav", "factories");
    }
    
    void populateEditBridgeForm(Model uiModel, JBridge bridge){
    	
    	JFactory factory = bridge.getFactory();
    	List<JFactory> factories = new ArrayList<JFactory>();
    	factories.add(bridge.getFactory());
    	
    	uiModel.addAttribute("bridge", bridge);
    	uiModel.addAttribute("currentNav", "factories");
    	

    	uiModel.addAttribute("factory",factory);

    	uiModel.addAttribute("factories", factories);
    	
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
