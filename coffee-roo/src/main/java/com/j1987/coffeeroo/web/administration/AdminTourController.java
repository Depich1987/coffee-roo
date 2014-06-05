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

import com.j1987.coffeeroo.domain.JTour;
import com.j1987.coffeeroo.services.dao.TourService;

@Controller
@RequestMapping(value = "/administration/tours")
public class AdminTourController {
	
	private static final String UPDATE_VIEW = "administration/tour/update";
	private static final String SHOW_VIEW = "administration/tour/show";
	private static final String LIST_VIEW = "administration/tour/list";
	private static final String CREATE_VIEW = "administration/tour/create";
	
	private static Logger logger = Logger.getLogger(AdminTourController.class);
	
	@Autowired
	private TourService tourService;

	public AdminTourController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JTour tour, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tour);
            logger.debug("create() - the tour object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
		
        uiModel.asMap().clear();
        
        tourService.persist(tour);
        
        logger.debug("create()- a new tour has been created with success !");
        
        return "redirect:/administration/tours/" + encodeUrlPathSegment(tour.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	
        populateEditForm(uiModel, new JTour());
        
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	
        uiModel.addAttribute("tour", tourService.findTour(id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("currentNav", "tours");
        
        return SHOW_VIEW;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       
    	if (page != null || size != null) {
            
    		int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("tours", tourService.findTourEntries(firstResult, sizeNo));
            
            float nrOfPages = (float) tourService.countTours() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       
    	} else {
            uiModel.addAttribute("tours", tourService.findAllTours());
        }
    	
    	uiModel.addAttribute("currentNav", "tours");
    	
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JTour tour, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tour);
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();
        tour.merge();
        
        return "redirect:/administration/tours/" + encodeUrlPathSegment(tour.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    	
        populateEditForm(uiModel, tourService.findTour(id));
        return UPDATE_VIEW;
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        
    	JTour tour = tourService.findTour(id);
        tour.remove();
        uiModel.asMap().clear();
        
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        
        return "redirect:/administration/tours/list";
    }
    
    void populateEditForm(Model uiModel, JTour tour) {
        uiModel.addAttribute("tour", tour);
        uiModel.addAttribute("currentNav", "tours");
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
