package com.j1987.coffeeroo.web.administration;

import java.io.UnsupportedEncodingException;

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
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.j1987.coffeeroo.domain.JCompany;
import com.j1987.coffeeroo.domain.JFactory;
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
	
	private static Logger logger = Logger.getLogger(AdminFactoryController.class);
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private CompanyService companyService;

	public AdminFactoryController() {
		// TODO Auto-generated constructor stub
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
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	
        uiModel.addAttribute("factory", factoryService.findFactory(id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("currentNav", "factories");
        
        return SHOW_VIEW;
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
