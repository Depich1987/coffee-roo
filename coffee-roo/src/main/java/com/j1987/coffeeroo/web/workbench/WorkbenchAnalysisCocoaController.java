/**
 * @author Franck Janel Agah
 *
 */
package com.j1987.coffeeroo.web.workbench;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JDealer;
import com.j1987.coffeeroo.domain.JExporter;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JLocalization;
import com.j1987.coffeeroo.domain.JSupplier;
import com.j1987.coffeeroo.domain.JTour;
import com.j1987.coffeeroo.framework.CoffeeAnalysisNotFoundException;
import com.j1987.coffeeroo.framework.FactoryNotFoundException;
import com.j1987.coffeeroo.framework.JUtils;
import com.j1987.coffeeroo.services.dao.AnalysisService;
import com.j1987.coffeeroo.services.dao.CoffeeAnalysisService;
import com.j1987.coffeeroo.services.dao.DealerService;
import com.j1987.coffeeroo.services.dao.ExporterService;
import com.j1987.coffeeroo.services.dao.FactoryService;
import com.j1987.coffeeroo.services.dao.LocalizationService;
import com.j1987.coffeeroo.services.dao.SupplierService;
import com.j1987.coffeeroo.services.dao.TourService;
import com.j1987.coffeeroo.services.security.JSecurityService;
import com.j1987.coffeeroo.web.form.CoffeeAnalysisForm;
import com.j1987.coffeeroo.web.form.FilterCoffeeAnalysis;

@Controller
@RequestMapping(value = "/workbench/analysis/cocoaanalysis")
public class WorkbenchAnalysisCocoaController {
	
	private static final String UPDATE_VIEW = "workbench/cocoaanalysis/update";
	private static final String FIND_VIEW = "workbench/cocoaanalysis/find";
	private static final String SHOW_VIEW = "workbench/cocoaanalysis/show";
	private static final String LIST_VIEW = "workbench/cocoaanalysis/list";
	private static final String CREATE_VIEW = "workbench/cocoaanalysis/create";
	
	private static Logger logger = Logger.getLogger(WorkbenchAnalysisCocoaController.class);
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private FactoryService factoryService;
	
//	@Autowired
//	private CocoaAnalysisService coffeeAnalysisService;
	
	@Autowired
	private JSecurityService securityService;
	
	@Autowired
	private TourService tourService;
	
	@Autowired
	private LocalizationService localizationService;
	
	@Autowired
	private ExporterService exporterService;
	
	public WorkbenchAnalysisCocoaController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid CoffeeAnalysisForm coffeeAnalysisForm, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) throws FactoryNotFoundException {
        
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, coffeeAnalysisForm, coffeeAnalysisForm.getFactoryCode());
            logger.debug("create() - the coffeeAnalysisForm object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
		
        uiModel.asMap().clear();
        
        HttpSession session = httpServletRequest.getSession();
        String factoryCode = null;
        
        if(coffeeAnalysisForm.getFactoryCode() != null){
        	factoryCode = coffeeAnalysisForm.getFactoryCode();
        }else{
        	 
        	factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
        }
        
        List<JFactory>  factories = factoryService.findFactoriesByCodeEquals(factoryCode);
        
        if(factories.isEmpty()){
    	    	String errMsg = ">>>> create() - Failed finding Factory with code : " + coffeeAnalysisForm.getFactoryCode();
    	   	   	 logger.error(errMsg);
    	   	   	 throw new FactoryNotFoundException("Aucune usine avec le code [ "+ coffeeAnalysisForm.getFactoryCode() +" ] au sein de votre usine "); 

        }
        
	}
	
}