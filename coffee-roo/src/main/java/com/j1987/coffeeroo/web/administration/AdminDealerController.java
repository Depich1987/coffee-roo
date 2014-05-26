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

import com.j1987.coffeeroo.domain.JDealer;
import com.j1987.coffeeroo.services.dao.DealerService;

@Controller
@RequestMapping(value = "/administration/dealers")
public class AdminDealerController {
	
	private static final String UPDATE_VIEW = "administration/dealer/update";
	private static final String SHOW_VIEW = "administration/dealer/show";
	private static final String LIST_VIEW = "administration/dealer/list";
	private static final String CREATE_VIEW = "administration/dealer/create";
	
	private static Logger logger = Logger.getLogger(AdminDealerController.class);
	
	@Autowired
	private DealerService dealerService;

	public AdminDealerController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/checkcode", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody  boolean checkDealerCode(@RequestParam("cashDeskCode")String cashDeskCode){
		boolean valid = false;
		List<JDealer> dealers = dealerService.findDealersByCodeEquals(cashDeskCode);
		if(dealers.isEmpty()) valid = true;
		return valid;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JDealer dealer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, dealer);
            logger.debug("create() - the dealer object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
		
        uiModel.asMap().clear();
        
        dealerService.persist(dealer);
        
        logger.debug("create()- a new dealer has been created with success !");
        
        return "redirect:/administration/dealers/" + encodeUrlPathSegment(dealer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
    	
        populateEditForm(uiModel, new JDealer());
        
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	
        uiModel.addAttribute("dealer", dealerService.findDealer(id));
        uiModel.addAttribute("itemId", id);
        
        uiModel.addAttribute("currentNav", "dealers");
        
        return SHOW_VIEW;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
       
    	if (page != null || size != null) {
            
    		int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("dealers", dealerService.findDealerEntries(firstResult, sizeNo));
            
            float nrOfPages = (float) dealerService.countDealers() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
       
    	} else {
            uiModel.addAttribute("dealers", dealerService.findAllDealers());
        }
    	
    	uiModel.addAttribute("currentNav", "dealers");
    	
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JDealer dealer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
       
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, dealer);
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();
        dealer.merge();
        
        return "redirect:/administration/dealers/" + encodeUrlPathSegment(dealer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    	
        populateEditForm(uiModel, dealerService.findDealer(id));
        return UPDATE_VIEW;
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        
    	JDealer dealer = dealerService.findDealer(id);
        dealer.remove();
        uiModel.asMap().clear();
        
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        
        return "redirect:/administration/dealers/list";
    }
    
    void populateEditForm(Model uiModel, JDealer dealer) {
        uiModel.addAttribute("dealer", dealer);
        uiModel.addAttribute("currentNav", "dealers");
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
