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

import com.j1987.coffeeroo.domain.JExporter;
import com.j1987.coffeeroo.services.dao.ExporterService;

@Controller
@RequestMapping(value = "/administration/exporters")
public class AdminExporterController {
	
	private static final String UPDATE_VIEW = "administration/exporter/update";
	private static final String SHOW_VIEW = "administration/exporter/show";
	private static final String LIST_VIEW = "administration/exporter/list";
	private static final String CREATE_VIEW = "administration/exporter/create";
	
	private static Logger logger = Logger.getLogger(AdminExporterController.class);
	
	@Autowired
	private ExporterService exporterService;

	public AdminExporterController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JExporter exporter, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, exporter);
            logger.debug("create() - the exporter object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
		
        uiModel.asMap().clear();
        
        exporterService.persist(exporter);
        
        logger.debug("create()- a new exporter has been created with success !");
        
        return "redirect:/administration/exporters/" + encodeUrlPathSegment(exporter.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	
        populateEditForm(uiModel, new JExporter());
        
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	
        uiModel.addAttribute("exporter", exporterService.findExporter(id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("currentNav", "exporters");
        
        return SHOW_VIEW;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       
    	if (page != null || size != null) {
            
    		int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("exporters", exporterService.findExporterEntries(firstResult, sizeNo));
            
            float nrOfPages = (float) exporterService.countExporters() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       
    	} else {
            uiModel.addAttribute("exporters", exporterService.findAllExporters());
        }
    	
    	uiModel.addAttribute("currentNav", "exporters");
    	
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JExporter exporter, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, exporter);
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();
        exporter.merge();
        
        return "redirect:/administration/exporters/" + encodeUrlPathSegment(exporter.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    	
        populateEditForm(uiModel, exporterService.findExporter(id));
        return UPDATE_VIEW;
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        
    	JExporter exporter = exporterService.findExporter(id);
        exporter.remove();
        uiModel.asMap().clear();
        
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        
        return "redirect:/administration/exporters/list";
    }
    
    void populateEditForm(Model uiModel, JExporter exporter) {
        uiModel.addAttribute("exporter", exporter);
        uiModel.addAttribute("currentNav", "exporters");
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
