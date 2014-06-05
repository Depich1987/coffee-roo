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
@RequestMapping(value = "/workbench/analysis/coffeeanalysis")
public class WorkbenchAnalysisCoffeeController {
	
	private static final String UPDATE_VIEW = "workbench/coffeeanalysis/update";
	private static final String FIND_VIEW = "workbench/coffeeanalysis/find";
	private static final String SHOW_VIEW = "workbench/coffeeanalysis/show";
	private static final String LIST_VIEW = "workbench/coffeeanalysis/list";
	private static final String CREATE_VIEW = "workbench/coffeeanalysis/create";
	
	private static Logger logger = Logger.getLogger(WorkbenchAnalysisCoffeeController.class);
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private CoffeeAnalysisService coffeeAnalysisService;
	
	@Autowired
	private JSecurityService securityService;
	
	@Autowired
	private TourService tourService;
	
	@Autowired
	private LocalizationService localizationService;
	
	@Autowired
	private ExporterService exporterService;
	
	public WorkbenchAnalysisCoffeeController() {
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
        
        JCoffeeAnalysis coffeeAnalysis =  new JCoffeeAnalysis();
        
        JFactory factory = factories.get(0);
        
        coffeeAnalysis.setFactoryEntry(factory);
        
        JExporter exporterEntry = null;
        List<JExporter> exporters = exporterService.findExportersByCodeEquals(coffeeAnalysisForm.getExporterCode());
        if(! exporters.isEmpty()){
        	exporterEntry = exporters.get(0);
        }
		coffeeAnalysis.setExporterEntry(exporterEntry);
		
		JDealer dealerEntry = null;
		List<JDealer> dealers = dealerService.findDealersByCodeEquals(coffeeAnalysisForm.getDealerCode());
		if(!dealers.isEmpty()){
			dealerEntry = dealers.get(0);
		}
		coffeeAnalysis.setDealerEntry(dealerEntry);
		
		JSupplier supplierEntry = null ;
		List<JSupplier> suppliers = supplierService.findSuppliersByCodeEquals(coffeeAnalysisForm.getSupplierCode());
		if(! suppliers.isEmpty()){
			supplierEntry = suppliers.get(0);
		}
		coffeeAnalysis.setSupplierEntry(supplierEntry);
		
		JLocalization provenance = localizationService.findLocalization(coffeeAnalysisForm.getProvenance());
		coffeeAnalysis.setProvenance(provenance);
		
		JTour tour = tourService.findTour(coffeeAnalysisForm.getTourId());
		coffeeAnalysis.setTour(tour);
		
		coffeeAnalysis.setCreatedBy(securityService.currentUser());
        coffeeAnalysis.setCreationDate(new Date());
        
        try {
			Date coffeeAnalysisDate = JUtils.DATE_FORMAT.parse(coffeeAnalysisForm.getDateOfAnalysis());
			
			Date startTime = JUtils.TIME_FORMAT.parse(coffeeAnalysisForm.getStartTime());
			Date endTime = JUtils.TIME_FORMAT.parse(coffeeAnalysisForm.getEndTime());
			
			if(coffeeAnalysisDate != null){
				coffeeAnalysis.setDateOfAnalysis(coffeeAnalysisDate);
			}
			
			if(startTime != null){
				coffeeAnalysis.setStartTime(startTime);
			}
			
			if(endTime != null){
				coffeeAnalysis.setEndTime(endTime);
			}
			
		} catch (ParseException e) {

			populateEditForm(uiModel, coffeeAnalysisForm, factoryCode);
			logger.debug("create() - message :"+e.getMessage());
			return CREATE_VIEW;
		}
        
        String referenceGenerated = RandomStringUtils.random(8, true, true);
        coffeeAnalysis.setReference(referenceGenerated.toUpperCase());
        coffeeAnalysis.setNetWeightOfProductAccepted(coffeeAnalysisForm.getNetWeightOfProductAccepted());
        coffeeAnalysis.setNumberLading(coffeeAnalysisForm.getNumberLading());
        coffeeAnalysis.setNumberSAIGIC(coffeeAnalysisForm.getNumberSAIGIC());
        coffeeAnalysis.setNumberOfBagAllowed(coffeeAnalysisForm.getNumberOfBagAllowed());
        coffeeAnalysis.setTruckNumber(coffeeAnalysisForm.getTruckNumber());
        coffeeAnalysis.setTotalOfReportedBags(coffeeAnalysisForm.getTotalOfReportedBags());
        coffeeAnalysis.setTotalOfBagPushed(coffeeAnalysisForm.getTotalOfBagPushed());
        coffeeAnalysis.setSampleCode(coffeeAnalysisForm.getSampleCode());
        
        coffeeAnalysis.setCalibrageBase(coffeeAnalysisForm.getCalibrageBase());
        coffeeAnalysis.setStatus(Long.valueOf("0"));
        coffeeAnalysisService.persist(coffeeAnalysis);
        
        
        logger.debug("create()- a new coffeeAnalysis has been created with success !");
        
        return "redirect:/workbench/analysis/coffeeanalysis/" + encodeUrlPathSegment(coffeeAnalysis.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid CoffeeAnalysisForm coffeeAnalysisForm, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) throws FactoryNotFoundException {
		
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, coffeeAnalysisForm, coffeeAnalysisForm.getFactoryCode());
            logger.debug("update() - the coffeeAnalysisForm object is invalid. Redirect to update view");
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();
        
	JCoffeeAnalysis coffeeAnalysis = coffeeAnalysisService.findCoffeeAnalysis(coffeeAnalysisForm.getId());
	    	
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
	
	JFactory factory = factories.get(0);
	
	coffeeAnalysis.setFactoryEntry(factory);
	
	JExporter exporterEntry = null;
	List<JExporter> exporters = exporterService.findExportersByCodeEquals(coffeeAnalysisForm.getExporterCode());
	if(! exporters.isEmpty()){
		exporterEntry = exporters.get(0);
	}
	coffeeAnalysis.setExporterEntry(exporterEntry);
	
	JDealer dealerEntry = null;
	List<JDealer> dealers = dealerService.findDealersByCodeEquals(coffeeAnalysisForm.getDealerCode());
	if(!dealers.isEmpty()){
		dealerEntry = dealers.get(0);
	}
	coffeeAnalysis.setDealerEntry(dealerEntry);
	
	JSupplier supplierEntry = null ;
	List<JSupplier> suppliers = supplierService.findSuppliersByCodeEquals(coffeeAnalysisForm.getSupplierCode());
	if(! suppliers.isEmpty()){
		supplierEntry = suppliers.get(0);
	}
	coffeeAnalysis.setSupplierEntry(supplierEntry);
	
	JLocalization provenance = localizationService.findLocalization(coffeeAnalysisForm.getProvenance());
	coffeeAnalysis.setProvenance(provenance);
	
	coffeeAnalysis.setCreatedBy(securityService.currentUser());
	coffeeAnalysis.setCreationDate(new Date());
	
	try {
		Date coffeeAnalysisDate = JUtils.DATE_FORMAT.parse(coffeeAnalysisForm.getDateOfAnalysis());
		
		Date startTime = JUtils.TIME_FORMAT.parse(coffeeAnalysisForm.getStartTime());
		Date endTime = JUtils.TIME_FORMAT.parse(coffeeAnalysisForm.getEndTime());
		
		if(coffeeAnalysisDate != null){
			coffeeAnalysis.setDateOfAnalysis(coffeeAnalysisDate);
		}
		
		if(startTime != null){
			coffeeAnalysis.setStartTime(startTime);
		}
		
		if(endTime != null){
			coffeeAnalysis.setEndTime(endTime);
		}
		
	} catch (ParseException e) {
	
		populateEditForm(uiModel, coffeeAnalysisForm, factoryCode);
		logger.debug("create() - message :"+e.getMessage());
		return CREATE_VIEW;
	}

	String referenceGenerated = RandomStringUtils.random(8, true, true);
	coffeeAnalysis.setReference(referenceGenerated.toUpperCase());
	coffeeAnalysis.setNetWeightOfProductAccepted(coffeeAnalysisForm.getNetWeightOfProductAccepted());
	coffeeAnalysis.setNumberLading(coffeeAnalysisForm.getNumberLading());
	coffeeAnalysis.setNumberSAIGIC(coffeeAnalysisForm.getNumberSAIGIC());
	coffeeAnalysis.setNumberOfBagAllowed(coffeeAnalysisForm.getNumberOfBagAllowed());
	coffeeAnalysis.setTruckNumber(coffeeAnalysisForm.getTruckNumber());
	coffeeAnalysis.setTotalOfReportedBags(coffeeAnalysisForm.getTotalOfReportedBags());
	coffeeAnalysis.setTotalOfBagPushed(coffeeAnalysisForm.getTotalOfBagPushed());
	coffeeAnalysis.setSampleCode(coffeeAnalysisForm.getSampleCode());
	
	coffeeAnalysis.setCalibrageBase(coffeeAnalysisForm.getCalibrageBase());
        
        
	coffeeAnalysis.merge();
        return "redirect:/workbench/analysis/coffeeanalysis/" + encodeUrlPathSegment(coffeeAnalysis.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
    	
    	CoffeeAnalysisForm  coffeeAnalysisForm = new CoffeeAnalysisForm();
    	JCoffeeAnalysis coffeeAnalysis = coffeeAnalysisService.findCoffeeAnalysis(id);
    	
    	coffeeAnalysisForm.setAcceptation(coffeeAnalysis.getAcceptation());
    	coffeeAnalysisForm.setConfirmation(coffeeAnalysis.getConfirmation());
    	coffeeAnalysisForm.setDateOfAnalysis(coffeeAnalysis.getDateOfAnalysis().toString());
    	coffeeAnalysisForm.setReference(coffeeAnalysis.getReference());
    	coffeeAnalysisForm.setNetWeightOfProductAccepted(coffeeAnalysis.getNetWeightOfProductAccepted());
    	coffeeAnalysisForm.setNumberLading(coffeeAnalysis.getNumberLading());
    	coffeeAnalysisForm.setNumberSAIGIC(coffeeAnalysis.getNumberSAIGIC());
    	coffeeAnalysisForm.setNumberOfBagAllowed(coffeeAnalysis.getNumberOfBagAllowed());
    	coffeeAnalysisForm.setTruckNumber(coffeeAnalysis.getTruckNumber());
    	coffeeAnalysisForm.setTotalOfReportedBags(coffeeAnalysis.getTotalOfReportedBags());
    	coffeeAnalysisForm.setTotalOfBagPushed(coffeeAnalysis.getTotalOfBagPushed());
    	coffeeAnalysisForm.setSampleCode(coffeeAnalysis.getSampleCode());
    	coffeeAnalysisForm.setId(coffeeAnalysis.getId());
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
    	populateEditForm(uiModel, coffeeAnalysisForm, factoryCode);
        return UPDATE_VIEW;
    }
	
	
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel, HttpServletRequest httpServletRequest) {
    	
    	CoffeeAnalysisForm coffeeAnalysisForm = new CoffeeAnalysisForm();

    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
        populateEditForm(uiModel, coffeeAnalysisForm, factoryCode);
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/find", method = RequestMethod.POST ,produces = "text/html")
    public String findAnalysisByQuerySearch(@Valid FilterCoffeeAnalysis filterCoffeeAnalysis, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest){
    	
    	if (bindingResult.hasErrors()) {
    		populateEditFindAnalysis(uiModel, filterCoffeeAnalysis);
            logger.debug("findAnalysisByQuerySearch() - the filterCoffeeAnalysis object is invalid. Redirect to create view");
            return FIND_VIEW;
    	}
    	
    	uiModel.asMap().clear();
    	
    	List<JCoffeeAnalysis> coffeeAnalysis = coffeeAnalysisService.findCoffeeAnalysisByReferenceEquals(filterCoffeeAnalysis.getAnalysisReference());
    	uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);
    	
    	populateEditFindAnalysis(uiModel, filterCoffeeAnalysis);
    	
    	return FIND_VIEW;
    }
    
    
    @RequestMapping(value = "/find", params = "form", produces = "text/html")
    public String findAnalysisByQuerySearchForm( Model uiModel, HttpServletRequest httpServletRequest){
    	populateEditFindAnalysis(uiModel, new FilterCoffeeAnalysis());
    	return FIND_VIEW;
    }
    
    void populateEditFindAnalysis(Model uiModel, FilterCoffeeAnalysis filterCoffeeAnalysis){
    	uiModel.addAttribute("filterCoffeeAnalysis", filterCoffeeAnalysis);
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) throws CoffeeAnalysisNotFoundException {
    	
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
    	if(factoryCode != null){
    		JCoffeeAnalysis coffeeAnalysis = coffeeAnalysisService.findCoffeeAnalysis(id);
    		
    		if(!coffeeAnalysis.getFactoryEntry().getCode().equals(factoryCode) || coffeeAnalysis == null){
    			
    			String errMsg = ">>>> show() - Failed trying to access an other factory's coffeeAnalysis with id : " + coffeeAnalysis.getId();
   	   	   	 	logger.error(errMsg);
   	   	   	 	throw new CoffeeAnalysisNotFoundException("Aucun usine avec cet identifiant [ "+ coffeeAnalysis.getId() +" ] au sein de votre usine "); 
    		}
    		
            uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);
            uiModel.addAttribute("itemId", id);
            
            return  SHOW_VIEW;
    	}
    	
       JCoffeeAnalysis coffeeAnalysis = coffeeAnalysisService.findCoffeeAnalysis(id);

       if(coffeeAnalysis == null){
    	   String errMsg = ">>>> show() - Failed trying to access an factory's coffeeAnalysis with id : " + id;
   	   	 	logger.error(errMsg);
   	   	 	throw new CoffeeAnalysisNotFoundException("Aucune Analyse avec cet identifiant [ "+ id +" ] au sein de votre entreprise "); 
       }
       
       uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);
       uiModel.addAttribute("itemId", id);
        
        return  SHOW_VIEW;
    }
    
    @Secured(value = {"ROLE_ADMIN","ROLE_SUPERVISOR"})
    @RequestMapping(value = "/list/filter", produces = "text/html")
    public String listByFilter(@RequestParam(value = "factoryFilter", required = false)String factoryFilter, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) throws FactoryNotFoundException{
    	
    	if(factoryFilter == null){
    		
    		String errMsg = ">>>> listByFilter() - Failed finding Factory with code : " + factoryFilter;
	   	   	 logger.error(errMsg);
	   	   	 throw new FactoryNotFoundException("Aucune usine avec le code [ "+ factoryFilter +" ] au sein de votre usine "); 
    	}
    	
    	List<JFactory> factories = factoryService.findFactoriesByCodeEquals(factoryFilter);
    	
        if(factories.isEmpty()){
	    	String errMsg = ">>>> listByFilter() - Failed finding Factory with code : " + factoryFilter;
	   	   	 logger.error(errMsg);
	   	   	 throw new FactoryNotFoundException("Aucune usine avec le code [ "+ factoryFilter +" ] au sein de votre usine "); 

        }
    	
        JFactory factory = factories.get(0);
        
    	if (page != null || size != null) {
    		int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("coffeeAnalysis", coffeeAnalysisService.findCoffeeAnalysisByFactoryEntries(factory, firstResult, sizeNo));
            float nrOfPages = (float) coffeeAnalysisService.findCoffeeAnalysisByFactory(factory).size() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            
    	}else {
            uiModel.addAttribute("coffeeAnalysis", coffeeAnalysisService.findCoffeeAnalysisByFactory(factory));
        }
    	
    	uiModel.addAttribute("factories", factoryService.findAllFactories());
    	
    	FilterCoffeeAnalysis filterCoffeeAnalysis = new FilterCoffeeAnalysis();
    	if(factoryFilter != null){
    		filterCoffeeAnalysis.setFactoryFilter(factoryFilter);
    	}
    	
    	uiModel.addAttribute("filterCoffeeAnalysis", filterCoffeeAnalysis);
    	
    	return LIST_VIEW;
    }
    
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel, HttpServletRequest httpServletRequest) throws FactoryNotFoundException {
        
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
    	List<JFactory> factories = new ArrayList<JFactory>();
    	
    	if(factoryCode != null){
    		
    		factories = factoryService.findFactoriesByCodeEquals(factoryCode);
        	
            if(factories.isEmpty()){
    	    	String errMsg = ">>>> list() - Failed finding Factory with code : " + factoryCode;
    	   	   	 logger.error(errMsg);
    	   	   	 throw new FactoryNotFoundException("Aucune usine avec le code [ "+ factoryCode +" ] au sein de votre usine "); 

            }
    	}

        JFactory factory = null;
        
        if(!factories.isEmpty()){
        	factory = factories.get(0);
        }
    	
    	if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            
            List<JCoffeeAnalysis> coffeeAnalysis = new ArrayList<JCoffeeAnalysis>();
            long nbr = 0;
            
            if(factoryCode != null){
            	coffeeAnalysis = coffeeAnalysisService.findCoffeeAnalysisByFactoryEntries(factory, firstResult, sizeNo);
            	nbr = coffeeAnalysisService.findCoffeeAnalysisByFactory(factory).size();
            }else{
            	coffeeAnalysis = coffeeAnalysisService.findCoffeeAnalysisEntries(firstResult, sizeNo);
            	nbr = coffeeAnalysisService.countCoffeeAnalyses();
            }
            
            uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);
            float nrOfPages = (float)  nbr / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            
        } else {
        	List<JCoffeeAnalysis> cofeeAnalysis = new ArrayList<JCoffeeAnalysis>();
        	
            if(factoryCode != null){
            	cofeeAnalysis = coffeeAnalysisService.findCoffeeAnalysisByFactory(factory);
            	
            }else{
            	cofeeAnalysis = coffeeAnalysisService.findAllCoffeeAnalyses();
            	
            }
        	
            uiModel.addAttribute("cofeeAnalysis", cofeeAnalysis);
        }
    	
        
    	FilterCoffeeAnalysis filterCoffeeAnalysis = new FilterCoffeeAnalysis();
    	uiModel.addAttribute("filterCoffeeAnalysis", filterCoffeeAnalysis);
    	uiModel.addAttribute("factories", factoryService.findAllFactories());
    	
    	uiModel.addAttribute("currentNav", "coffeeanalysis");
        
        return LIST_VIEW;
    }
    
    
    void populateEditForm(Model uiModel, CoffeeAnalysisForm coffeeAnalysisForm, String factoryCode ) {

    	List<JFactory>  factories = new ArrayList<JFactory>();
    	
    	if(factoryCode == null){
    		factories = factoryService.findAllFactories();
    	}else{
    		factories = factoryService.findFactoriesByCodeEquals(factoryCode);
    	}
 
    	
    	uiModel.addAttribute("factories", factories);
    	uiModel.addAttribute("suppliers", supplierService.findAllSuppliers());
    	uiModel.addAttribute("dealers", dealerService.findAllDealers());
    	uiModel.addAttribute("tours", tourService.findAllTours());
    	uiModel.addAttribute("localizations", localizationService.findAllLocalizations());
    	uiModel.addAttribute("exporters", exporterService.findAllExporters());
    	
        uiModel.addAttribute("coffeeAnalysisForm", coffeeAnalysisForm);
        
        uiModel.addAttribute("currentNav", "coffeeanalysis");
        
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