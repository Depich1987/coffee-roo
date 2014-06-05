package com.j1987.coffeeroo.web.administration;

import java.io.UnsupportedEncodingException;
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
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.services.dao.BridgeService;
import com.j1987.coffeeroo.services.dao.FactoryService;

@Controller
@RequestMapping(value = "/administration/bridges")
public class AdminBridgeController {
	
	private static final String UPDATE_VIEW = "administration/bridge/update";
	private static final String SHOW_VIEW = "administration/bridge/show";
	private static final String LIST_VIEW = "administration/bridge/list";
	private static final String CREATE_VIEW = "administration/bridge/create";
	
	private static Logger logger = Logger.getLogger(AdminBridgeController.class);
	
	@Autowired
	private BridgeService bridgeService;
	
	@Autowired
	private FactoryService factoryService;

	public AdminBridgeController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/checkcode", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody  boolean checkBridgeCode(@RequestParam("cashDeskCode")String cashDeskCode){
		boolean valid = false;
		List<JBridge> bridges = bridgeService.findBridgesByCodeEquals(cashDeskCode);
		if(bridges.isEmpty()) valid = true;
		return valid;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JBridge bridge, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bridge);
            logger.debug("create() - the bridge object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
		
        uiModel.asMap().clear();
        
        bridgeService.persist(bridge);
        
        logger.debug("create()- a new bridge has been created with success !");
        
        return "redirect:/administration/bridges/" + encodeUrlPathSegment(bridge.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	
        populateEditForm(uiModel, new JBridge());
        
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	
        uiModel.addAttribute("bridge", bridgeService.findBridge(id));
        uiModel.addAttribute("itemId", id);
        
        uiModel.addAttribute("currentNav", "bridges");
        
        return SHOW_VIEW;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       
    	if (page != null || size != null) {
            
    		int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("bridges", bridgeService.findBridgeEntries(firstResult, sizeNo));
            
            float nrOfPages = (float) bridgeService.countBridges() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       
    	} else {
            uiModel.addAttribute("bridges", bridgeService.findAllBridges());
        }
    	
    	uiModel.addAttribute("currentNav", "bridges");
    	
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JBridge bridge, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bridge);
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();
        bridge.merge();
        
        return "redirect:/administration/bridges/" + encodeUrlPathSegment(bridge.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    	
        populateEditForm(uiModel, bridgeService.findBridge(id));
        return UPDATE_VIEW;
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        
    	JBridge bridge = bridgeService.findBridge(id);
        bridge.remove();
        uiModel.asMap().clear();
        
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        
        return "redirect:/administration/bridges/list";
    }
    
    void populateEditForm(Model uiModel, JBridge bridge) {
    	
    	List<JFactory> factories = factoryService.findAllFactories();
        uiModel.addAttribute("bridge", bridge);
        uiModel.addAttribute("factories", factories);
        uiModel.addAttribute("currentNav", "bridges");
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
