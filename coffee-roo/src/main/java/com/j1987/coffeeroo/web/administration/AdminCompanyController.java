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
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.j1987.coffeeroo.domain.JCompany;
import com.j1987.coffeeroo.services.dao.CompanyService;

/**
 * It's an implementation of <tt>Company</tt>'s controller.It permits to show or update details of company
 * @author Franck Janel Agah
 *
 */

@Controller
@RequestMapping(value = "/administration/basesettings")
public class AdminCompanyController {
	
	private static final String UPDATE_VIEW = "administration/company/update";
	private static final String SHOW_VIEW = "administration/company/show";
	
	private static Logger logger = Logger.getLogger(AdminCompanyController.class);
	
	@Autowired
	CompanyService companyService;
	
	public AdminCompanyController() {
		// TODO Auto-generated constructor stub
	}
	
	 @RequestMapping(value = "/details", produces = "text/html")
	    public String show(Model uiModel) {
	    	
	    	JCompany company = companyService.findCompanyById(Long.valueOf("1"));
	        uiModel.addAttribute("company", company);
	        uiModel.addAttribute("itemId", company.getId());
	        uiModel.addAttribute("currentNav", "basesettings");
	        
	        return SHOW_VIEW;
	    }
	    
	    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
	    public String update(@Valid JCompany company, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	       
	    	if (bindingResult.hasErrors()) {
	            populateEditForm(uiModel, company);
	            return UPDATE_VIEW;
	        }
	    	
	        uiModel.asMap().clear();
	        companyService.update(company);
	        
	        logger.debug("update()- the company's details has been updated!");
	        
	        return "redirect:/administration/basesettings/details";
	    }
	    
	    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
	    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
	        
	    	populateEditForm(uiModel, companyService.findCompanyById(id));
	        return UPDATE_VIEW;
	    }
	    
	    void populateEditForm(Model uiModel, JCompany company) {
	        uiModel.addAttribute("company", company);
	        uiModel.addAttribute("currentNav", "basesettings");
	        
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