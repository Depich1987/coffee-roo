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

import com.j1987.coffeeroo.domain.JSupplier;
import com.j1987.coffeeroo.services.dao.SupplierService;

@Controller
@RequestMapping(value = "/administration/suppliers")
public class AdminSupplierController {
	
	private static final String UPDATE_VIEW = "administration/supplier/update";
	private static final String SHOW_VIEW = "administration/supplier/show";
	private static final String LIST_VIEW = "administration/supplier/list";
	private static final String CREATE_VIEW = "administration/supplier/create";
	
	private static Logger logger = Logger.getLogger(AdminSupplierController.class);
	
	@Autowired
	private SupplierService supplierService;

	public AdminSupplierController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JSupplier supplier, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, supplier);
            logger.debug("create() - the supplier object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
		
        uiModel.asMap().clear();
        
        supplierService.persist(supplier);
        
        logger.debug("create()- a new supplier has been created with success !");
        
        return "redirect:/administration/suppliers/" + encodeUrlPathSegment(supplier.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	
        populateEditForm(uiModel, new JSupplier());
        
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	
        uiModel.addAttribute("supplier", supplierService.findSupplier(id));
        uiModel.addAttribute("itemId", id);
        
        return SHOW_VIEW;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       
    	if (page != null || size != null) {
            
    		int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("suppliers", supplierService.findSupplierEntries(firstResult, sizeNo));
            
            float nrOfPages = (float) supplierService.countSuppliers() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       
    	} else {
            uiModel.addAttribute("suppliers", supplierService.findAllSuppliers());
        }
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JSupplier supplier, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, supplier);
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();
        supplier.merge();
        
        return "redirect:/administration/suppliers/" + encodeUrlPathSegment(supplier.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    	
        populateEditForm(uiModel, supplierService.findSupplier(id));
        return UPDATE_VIEW;
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        
    	JSupplier supplier = supplierService.findSupplier(id);
        supplier.remove();
        uiModel.asMap().clear();
        
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        
        return "redirect:/administration/suppliers/list";
    }
    
    void populateEditForm(Model uiModel, JSupplier supplier) {
        uiModel.addAttribute("supplier", supplier);
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
