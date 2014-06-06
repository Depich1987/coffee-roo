/**
 * @author Franck Janel Agah
 * Manage {@link JAnalysis} CRUD actions.
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

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JExporter;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JFirm;
import com.j1987.coffeeroo.domain.JLocalization;
import com.j1987.coffeeroo.domain.JSupplier;
import com.j1987.coffeeroo.domain.JTour;
import com.j1987.coffeeroo.framework.AnalysisNotFoundException;
import com.j1987.coffeeroo.framework.FactoryNotFoundException;
import com.j1987.coffeeroo.framework.JUtils;
import com.j1987.coffeeroo.services.dao.AnalysisService;
import com.j1987.coffeeroo.services.dao.BridgeService;
import com.j1987.coffeeroo.services.dao.ExporterService;
import com.j1987.coffeeroo.services.dao.FactoryService;
import com.j1987.coffeeroo.services.dao.FirmService;
import com.j1987.coffeeroo.services.dao.LocalizationService;
import com.j1987.coffeeroo.services.dao.SupplierService;
import com.j1987.coffeeroo.services.dao.TourService;
import com.j1987.coffeeroo.services.security.JSecurityService;
import com.j1987.coffeeroo.web.form.CocoaAnalysisForm;
import com.j1987.coffeeroo.web.form.FilterAnalysisForm;

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
	private FirmService firmService;
	
	@Autowired
	private BridgeService bridgeService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private AnalysisService analysisService;
	
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
	
	@RequestMapping(value = "/checkcode", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody  boolean checkBridgeCode(@RequestParam("reference")String reference){
		boolean valid = false;
		List<JAnalysis> analyzes = analysisService.findAnalysisByReferenceEquals(reference);
		if(analyzes.isEmpty()) valid = true;
		return valid;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid CocoaAnalysisForm cocoaAnalysisForm, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) throws FactoryNotFoundException {
        
		if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cocoaAnalysisForm, cocoaAnalysisForm.getFactoryCode());
            logger.debug("create() - the cocoaAnalysisForm object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
		
        uiModel.asMap().clear();
        

        JAnalysis cocoaAnalysis =  new JAnalysis();
        
        String factoryCode = null;
        
        cocoaAnalysis.setReference(cocoaAnalysisForm.getReference());
        
        List<JBridge> bridgeEntries = bridgeService.findBridgesByCodeEquals(cocoaAnalysisForm.getBridgeCode());
        JBridge bridgeEntry = null;
        
        if(!bridgeEntries.isEmpty()){
        	bridgeEntry = bridgeEntries.get(0);
        }
        
        cocoaAnalysis.setBridge(bridgeEntry);
        if(bridgeEntry.getFactory() != null){
        	JFactory factory = bridgeEntry.getFactory();
        	cocoaAnalysis.setFactoryCode(factory.getCode());
        	cocoaAnalysis.setFactoryName(factory.getName());
        }

        if(cocoaAnalysisForm.getFactoryCode() != null){
        	factoryCode = cocoaAnalysisForm.getFactoryCode();
        }

        JExporter exporterEntry = null;
        List<JExporter> exporters = exporterService.findExportersByCodeEquals(cocoaAnalysisForm.getExporterCode());
        if(! exporters.isEmpty()){
        	exporterEntry = exporters.get(0);
        }
		cocoaAnalysis.setExporterEntry(exporterEntry);
		cocoaAnalysis.setExporterName(exporterEntry.getName());
		
		JFirm dealerEntry = null;
		List<JFirm> firms = firmService.findFirmsByCodeEquals(cocoaAnalysisForm.getDealerCode());
		if(!firms.isEmpty()){
			dealerEntry = firms.get(0);
		}
		cocoaAnalysis.setDealerEntry(dealerEntry);
		cocoaAnalysis.setDealerName(dealerEntry.getName());
		
		JSupplier supplierEntry = null ;
		List<JSupplier> suppliers = supplierService.findSuppliersByCodeEquals(cocoaAnalysisForm.getSupplierCode());
		if(! suppliers.isEmpty()){
			supplierEntry = suppliers.get(0);
		}
		cocoaAnalysis.setSupplierEntry(supplierEntry);
		cocoaAnalysis.setSupplierName(supplierEntry.getName());
		
		JLocalization provenance = localizationService.findLocalization(cocoaAnalysisForm.getProvenance());
		cocoaAnalysis.setProvenance(provenance);
		
		JTour tour = tourService.findTour(cocoaAnalysisForm.getTourId());
		cocoaAnalysis.setTour(tour);
		
		cocoaAnalysis.setCreatedBy(securityService.currentUser());
        cocoaAnalysis.setCreationDate(new Date());
        
        try {
			Date coffeeAnalysisDate = JUtils.DATE_FORMAT.parse(cocoaAnalysisForm.getDateOfAnalysis());
			
			Date startTime = JUtils.TIME_FORMAT.parse(cocoaAnalysisForm.getStartTime());
			Date endTime = JUtils.TIME_FORMAT.parse(cocoaAnalysisForm.getEndTime());
			
			if(coffeeAnalysisDate != null){
				cocoaAnalysis.setDateOfAnalysis(coffeeAnalysisDate);
			}
			
			if(startTime != null){
				cocoaAnalysis.setStartTime(startTime);
			}
			
			if(endTime != null){
				cocoaAnalysis.setEndTime(endTime);
			}
			
		} catch (ParseException e) {

			populateEditForm(uiModel, cocoaAnalysisForm, factoryCode);
			logger.debug("create() - message :"+e.getMessage());
			return CREATE_VIEW;
		}
        
        cocoaAnalysis.setNetWeightOfProductAccepted(cocoaAnalysisForm.getNetWeightOfProductAccepted());
        cocoaAnalysis.setNumberLading(cocoaAnalysisForm.getNumberLading());
        cocoaAnalysis.setNumberSAIGIC(cocoaAnalysisForm.getNumberSAIGIC());
    	cocoaAnalysis.setNumberOfBagAllowed(cocoaAnalysisForm.getNumberOfBagAllowed());
    	cocoaAnalysis.setTruckNumber(cocoaAnalysisForm.getTruckNumber());
    	cocoaAnalysis.setTotalOfReportedBags(cocoaAnalysisForm.getTotalOfReportedBags());
    	cocoaAnalysis.setTotalOfBagPushed(cocoaAnalysisForm.getTotalOfBagPushed());
    	cocoaAnalysis.setSampleCode(cocoaAnalysisForm.getSampleCode());
    	
    	/**
    	 * Taux Humidite
    	 */
    	cocoaAnalysis.setTauxHumidite1(cocoaAnalysisForm.getTauxHumidite1());
    	cocoaAnalysis.setTauxHumidite2(cocoaAnalysisForm.getTauxHumidite2());
    	cocoaAnalysis.setTauxHumidite3(cocoaAnalysisForm.getTauxHumidite3());
    	cocoaAnalysis.setMoyenneTauxHumidite(cocoaAnalysisForm.getMoyenneTauxHumidite());
    	
    	/**
    	 * Grainage
    	 */
    	cocoaAnalysis.setNombreFeves(cocoaAnalysisForm.getNombreFeves());
    	cocoaAnalysis.setFevesPar100g(cocoaAnalysisForm.getFevesPar100g());
    	
    	/**
    	 * Matieres Etrangeres
    	 */
    	cocoaAnalysis.setPoidsMatieresEtrangeres(cocoaAnalysisForm.getPoidsMatieresEtrangeres());
    	cocoaAnalysis.setPourcentageMatieresEtrangeres(cocoaAnalysisForm.getPourcentageMatieresEtrangeres());
        
    	/**
    	 * Brisures
    	 */
    	cocoaAnalysis.setPoidsBrisures(cocoaAnalysisForm.getPoidsBrisures());
    	cocoaAnalysis.setPourcentageBrisures(cocoaAnalysisForm.getPourcentageBrisures());
    	
    	/********************
    	 * Epreuve a la coupe
    	 ********************/
    	
    	/**
    	 * Plateau 1
    	 */
    	cocoaAnalysis.setFevesMoisiesPlateau1(cocoaAnalysisForm.getFevesMoisiesPlateau1());
    	cocoaAnalysis.setFevesArdoiseesPlateau1(cocoaAnalysisForm.getFevesArdoiseesPlateau1());
    	cocoaAnalysis.setFevesMiteesPlateau1(cocoaAnalysisForm.getFevesMiteesPlateau1());
    	cocoaAnalysis.setFevesGermeesPlateau1(cocoaAnalysisForm.getFevesGermeesPlateau1());
    	cocoaAnalysis.setFevesPlatesPlateau1(cocoaAnalysisForm.getFevesPlatesPlateau1());
    	cocoaAnalysis.setTotalFevesDefectueusesPlateau1(cocoaAnalysisForm.getTotalFevesDefectueusesPlateau1());
    	cocoaAnalysis.setFevesViolettePlateau1(cocoaAnalysisForm.getFevesViolettePlateau1());
    	
    	/**
    	 * Plateau 2
    	 */
    	cocoaAnalysis.setFevesMoisiesPlateau2(cocoaAnalysisForm.getFevesMoisiesPlateau2());
    	cocoaAnalysis.setFevesArdoiseesPlateau2(cocoaAnalysisForm.getFevesArdoiseesPlateau2());
    	cocoaAnalysis.setFevesMiteesPlateau2(cocoaAnalysisForm.getFevesMiteesPlateau2());
    	cocoaAnalysis.setFevesGermeesPlateau2(cocoaAnalysisForm.getFevesGermeesPlateau2());
    	cocoaAnalysis.setFevesPlatesPlateau2(cocoaAnalysisForm.getFevesPlatesPlateau2());
    	cocoaAnalysis.setTotalFevesDefectueusesPlateau2(cocoaAnalysisForm.getTotalFevesDefectueusesPlateau2());
    	cocoaAnalysis.setFevesViolettePlateau2(cocoaAnalysisForm.getFevesViolettePlateau2());
    	
    	/**
    	 * Plateau 3
    	 */
    	cocoaAnalysis.setFevesMoisiesPlateau3(cocoaAnalysisForm.getFevesMoisiesPlateau3());
    	cocoaAnalysis.setFevesArdoiseesPlateau3(cocoaAnalysisForm.getFevesArdoiseesPlateau3());
    	cocoaAnalysis.setFevesMiteesPlateau3(cocoaAnalysisForm.getFevesMiteesPlateau3());
    	cocoaAnalysis.setFevesGermeesPlateau3(cocoaAnalysisForm.getFevesGermeesPlateau3());
    	cocoaAnalysis.setFevesPlatesPlateau3(cocoaAnalysisForm.getFevesPlatesPlateau3());
    	cocoaAnalysis.setTotalFevesDefectueusesPlateau3(cocoaAnalysisForm.getTotalFevesDefectueusesPlateau3());
    	cocoaAnalysis.setFevesViolettePlateau3(cocoaAnalysisForm.getFevesViolettePlateau3());
    	
    	cocoaAnalysis.setClassification(cocoaAnalysisForm.getClassification());
    	cocoaAnalysis.setConformity(cocoaAnalysisForm.getConformity());
    	
    	cocoaAnalysis.setStatus(cocoaAnalysisForm.getStatus());
    	
    	cocoaAnalysis.setProductType(JUtils.COCOA_PRODUCT);
    	analysisService.persist(cocoaAnalysis);
    	
    	 logger.debug("create()- a new coffeeAnalysis has been created with success !");
         
         return "redirect:/workbench/analysis/cocoaanalysis/" + encodeUrlPathSegment(cocoaAnalysis.getId().toString(), httpServletRequest);
	}
	
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel, HttpServletRequest httpServletRequest) {
    	
    	CocoaAnalysisForm cocoaAnalysisForm = new CocoaAnalysisForm();

    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
        populateEditForm(uiModel, cocoaAnalysisForm, factoryCode);
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) throws AnalysisNotFoundException {
    	
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
    	if(factoryCode != null){
    		JAnalysis analysis = analysisService.findAnalysis(id, JUtils.COCOA_PRODUCT);
    		
    		if(!analysis.getFactoryCode().equals(factoryCode) || analysis == null){
    			
    			String errMsg = ">>>> show() - Failed trying to access an other factory's coffeeAnalysis with id : " + id;
   	   	   	 	logger.error(errMsg);
   	   	   	 	throw new AnalysisNotFoundException("Aucune Analyse avec cet identifiant [ "+ id +" ] au sein de votre usine "); 
    		}
    		
            uiModel.addAttribute("analysis", analysis);
            uiModel.addAttribute("itemId", id);
            
            uiModel.addAttribute("currentNav", "analysisCocoa");
            
            return  SHOW_VIEW;
    	}
    	
       JAnalysis analysis = analysisService.findAnalysis(id,JUtils.COCOA_PRODUCT);

       if(analysis == null){
    	   String errMsg = ">>>> show() - Failed trying to access an factory's Analysis with id : " + id;
   	   	 	logger.error(errMsg);
   	   	 	throw new AnalysisNotFoundException("Aucune Analyse avec cet identifiant [ "+ id +" ] au sein de votre entreprise "); 
       }
       
       uiModel.addAttribute("analysis", analysis);
       uiModel.addAttribute("itemId", id);
        
        return  SHOW_VIEW;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "factoryFilter", required = false)String factoryFilter,@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel, HttpServletRequest httpServletRequest) throws FactoryNotFoundException {
        
    	if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            
            List<JAnalysis> cocoaAnalysis = new ArrayList<JAnalysis>();
            long nbr = 0;
            
            if(factoryFilter != null){
            	nbr = analysisService.countAnalysesInFactory(factoryFilter, JUtils.COCOA_PRODUCT);
            	cocoaAnalysis = analysisService.findAnalysisByFactoryCodeEntries(factoryFilter, JUtils.COCOA_PRODUCT, firstResult, sizeNo);
            }else{
            	nbr = analysisService.countAnalyses(JUtils.COCOA_PRODUCT);
            	cocoaAnalysis = analysisService.findAnalysisEntries(firstResult, sizeNo,  JUtils.COCOA_PRODUCT);
            }
            
            uiModel.addAttribute("cocoaAnalysis", cocoaAnalysis);
            float nrOfPages = (float)  nbr / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            
        } else {
        	List<JAnalysis> cocoaAnalysis = new ArrayList<JAnalysis>();
        	
            if(factoryFilter != null){
            	cocoaAnalysis = analysisService.findAnalysisByFactoryCode(factoryFilter, JUtils.COCOA_PRODUCT);
            	
            }else{
            	cocoaAnalysis = analysisService.findAllAnalyses(JUtils.COCOA_PRODUCT);
            	
            }
        	
            uiModel.addAttribute("cocoaAnalysis", cocoaAnalysis);
        }
    	
        
    	FilterAnalysisForm filterAnalysisForm = new FilterAnalysisForm();
    	uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
    	
    	if(factoryFilter != null){
    		filterAnalysisForm.setFactoryFilter(factoryFilter);
    		uiModel.addAttribute("factories", factoryService.findAllFactories());
    	}
    	
    	uiModel.addAttribute("currentNav", "analysisCocoa");
        
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id")Long id, Model uiModel, HttpServletRequest httpServletRequest) {
    	
    	CocoaAnalysisForm cocoaAnalysisForm = new CocoaAnalysisForm();
    	
    	JAnalysis  analysis = analysisService.findAnalysis(id, JUtils.COCOA_PRODUCT);

    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
        populateEditForm(uiModel, cocoaAnalysisForm, factoryCode);
        return UPDATE_VIEW;
    }
    
    @RequestMapping(value = "/find", method = RequestMethod.POST ,produces = "text/html")
    public String findAnalysisByQuerySearch(@Valid FilterAnalysisForm filterAnalysisForm, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest){
    	
    	if (bindingResult.hasErrors()) {
    		uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
            logger.debug("findAnalysisByQuerySearch() - the filterCoffeeAnalysis object is invalid. Redirect to create view");
            return "redirect:/workbench/analysis/cocoaanalysis/list?size=10";
    	}
    	
    	uiModel.asMap().clear();
    	
    	List<JAnalysis> analysis = analysisService.findAnalysisByReferenceEqualsAndProductType(filterAnalysisForm.getAnalysisReference(),JUtils.COCOA_PRODUCT);
    	uiModel.addAttribute("analysis", analysis);
    	uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
    	
    	uiModel.addAttribute("currentNav", "analysisCocoa");
    	
    	return FIND_VIEW;
    }

	
	void populateEditForm(Model uiModel, CocoaAnalysisForm cocoaAnalysisForm, String factoryCode ) {

    	List<JFactory>  factories = new ArrayList<JFactory>();
    	List<JBridge> bridges = new ArrayList<JBridge>();
    	
    	if(factoryCode == null){
    		bridges.addAll(bridgeService.findAllBridges());
    	}else{
    		factories = factoryService.findFactoriesByCodeEquals(factoryCode);
    		
        	for (JFactory factory : factories) {
        		bridges.addAll(factory.getBridges());
    		}
    	}
    	
    	uiModel.addAttribute("bridges", bridges);
    	uiModel.addAttribute("suppliers", supplierService.findAllSuppliers());
    	uiModel.addAttribute("firms", firmService.findAllFirms());
    	uiModel.addAttribute("tours", tourService.findAllTours());
    	uiModel.addAttribute("localizations", localizationService.findAllLocalizations());
    	uiModel.addAttribute("exporters", exporterService.findAllExporters());
    	
        uiModel.addAttribute("cocoaAnalysisForm", cocoaAnalysisForm);
        
        uiModel.addAttribute("currentNav", "analysisCocoa");
        
    }
	
	void populateEditCocoaAnalysisForm(JAnalysis analysis, CocoaAnalysisForm cocoaAnalysisForm){
		
		cocoaAnalysisForm.setId(analysis.getId());
		cocoaAnalysisForm.setNetWeightOfProductAccepted(analysis.getNetWeightOfProductAccepted());
		cocoaAnalysisForm.setNumberLading(analysis.getNumberLading());
        analysis.setNumberSAIGIC(analysis.getNumberSAIGIC());
    	analysis.setNumberOfBagAllowed(analysis.getNumberOfBagAllowed());
    	analysis.setTruckNumber(analysis.getTruckNumber());
    	analysis.setTotalOfReportedBags(analysis.getTotalOfReportedBags());
    	analysis.setTotalOfBagPushed(analysis.getTotalOfBagPushed());
    	analysis.setSampleCode(analysis.getSampleCode());
    	
    	/**
    	 * Taux Humidite
    	 */
    	analysis.setTauxHumidite1(analysis.getTauxHumidite1());
    	analysis.setTauxHumidite2(analysis.getTauxHumidite2());
    	analysis.setTauxHumidite3(analysis.getTauxHumidite3());
    	analysis.setMoyenneTauxHumidite(analysis.getMoyenneTauxHumidite());
    	
    	/**
    	 * Grainage
    	 */
    	analysis.setNombreFeves(analysis.getNombreFeves());
    	analysis.setFevesPar100g(analysis.getFevesPar100g());
    	
    	/**
    	 * Matieres Etrangeres
    	 */
    	analysis.setPoidsMatieresEtrangeres(analysis.getPoidsMatieresEtrangeres());
    	analysis.setPourcentageMatieresEtrangeres(analysis.getPourcentageMatieresEtrangeres());
        
    	/**
    	 * Brisures
    	 */
    	analysis.setPoidsBrisures(analysis.getPoidsBrisures());
    	analysis.setPourcentageBrisures(analysis.getPourcentageBrisures());
    	
    	/********************
    	 * Epreuve a la coupe
    	 ********************/
    	
    	/**
    	 * Plateau 1
    	 */
    	analysis.setFevesMoisiesPlateau1(analysis.getFevesMoisiesPlateau1());
    	analysis.setFevesArdoiseesPlateau1(analysis.getFevesArdoiseesPlateau1());
    	analysis.setFevesMiteesPlateau1(analysis.getFevesMiteesPlateau1());
    	analysis.setFevesGermeesPlateau1(analysis.getFevesGermeesPlateau1());
    	analysis.setFevesPlatesPlateau1(analysis.getFevesPlatesPlateau1());
    	analysis.setTotalFevesDefectueusesPlateau1(analysis.getTotalFevesDefectueusesPlateau1());
    	analysis.setFevesViolettePlateau1(analysis.getFevesViolettePlateau1());
    	
    	/**
    	 * Plateau 2
    	 */
    	analysis.setFevesMoisiesPlateau2(analysis.getFevesMoisiesPlateau2());
    	analysis.setFevesArdoiseesPlateau2(analysis.getFevesArdoiseesPlateau2());
    	analysis.setFevesMiteesPlateau2(analysis.getFevesMiteesPlateau2());
    	analysis.setFevesGermeesPlateau2(analysis.getFevesGermeesPlateau2());
    	analysis.setFevesPlatesPlateau2(analysis.getFevesPlatesPlateau2());
    	analysis.setTotalFevesDefectueusesPlateau2(analysis.getTotalFevesDefectueusesPlateau2());
    	analysis.setFevesViolettePlateau2(analysis.getFevesViolettePlateau2());
    	
    	/**
    	 * Plateau 3
    	 */
    	analysis.setFevesMoisiesPlateau3(analysis.getFevesMoisiesPlateau3());
    	analysis.setFevesArdoiseesPlateau3(analysis.getFevesArdoiseesPlateau3());
    	analysis.setFevesMiteesPlateau3(analysis.getFevesMiteesPlateau3());
    	analysis.setFevesGermeesPlateau3(analysis.getFevesGermeesPlateau3());
    	analysis.setFevesPlatesPlateau3(analysis.getFevesPlatesPlateau3());
    	analysis.setTotalFevesDefectueusesPlateau3(analysis.getTotalFevesDefectueusesPlateau3());
    	analysis.setFevesViolettePlateau3(analysis.getFevesViolettePlateau3());
    	
    	analysis.setClassification(analysis.getClassification());
    	analysis.setConformity(analysis.getConformity());
    	
    	analysis.setStatus(analysis.getStatus());
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