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

import com.j1987.coffeeroo.domain.JFirm;
import com.j1987.coffeeroo.services.dao.FirmService;


/**
 * It's an implementation of <tt>Firm</tt>'s controller.It permits to show or update details of dealer
 * @author Franck Janel Agah
 *
 */

@Controller
@RequestMapping(value = "/administration/generalsettings")
public class AdminFirmController {
	
	private static final String UPDATE_VIEW = "administration/firm/update";
	private static final String SHOW_VIEW = "administration/firm/show";
	
	private static Logger logger = Logger.getLogger(AdminFirmController.class);
	
	@Autowired
	private FirmService firmService;

	public AdminFirmController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/details", produces = "text/html")
    public String show(Model uiModel) {
    	
    	JFirm firm = firmService.findFirm(Long.valueOf("1"));
        uiModel.addAttribute("firm", firm);
        uiModel.addAttribute("itemId", firm.getId());
        uiModel.addAttribute("currentNav", "generalsettings");
        
        return SHOW_VIEW;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JFirm firm, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, firm);
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();
        firmService.update(firm);
        
        logger.debug("update()- the firm's details has been updated!");
        
        return "redirect:/administration/generalsettings/details";
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        
    	populateEditForm(uiModel, firmService.findFirm(id));
        return UPDATE_VIEW;
    }
    
    void populateEditForm(Model uiModel, JFirm firm) {
        uiModel.addAttribute("firm", firm);
        uiModel.addAttribute("currentNav", "generalsettings");
        
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
