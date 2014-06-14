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

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JExporter;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JFirm;
import com.j1987.coffeeroo.domain.JLocalization;
import com.j1987.coffeeroo.domain.JSupplier;
import com.j1987.coffeeroo.domain.JTour;
import com.j1987.coffeeroo.framework.CoffeeAnalysisNotFoundException;
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
import com.j1987.coffeeroo.web.form.CoffeeAnalysisForm;
import com.j1987.coffeeroo.web.form.FilterAnalysisForm;

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
	private FirmService firmService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private BridgeService bridgeService;
	
	@Autowired
	private JSecurityService securityService;
	
	@Autowired
	private AnalysisService analysisService;
	
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
        

        JAnalysis coffeeAnalysis =  new JAnalysis();
        
        String factoryCode = null;
        
        coffeeAnalysis.setReference(coffeeAnalysisForm.getReference());
        
        List<JBridge> bridgeEntries = bridgeService.findBridgesByCodeEquals(coffeeAnalysisForm.getBridgeCode());
        JBridge bridgeEntry = null;
        
        if(!bridgeEntries.isEmpty()){
        	bridgeEntry = bridgeEntries.get(0);
        }
        
        coffeeAnalysis.setBridge(bridgeEntry);
        if(bridgeEntry.getFactory() != null){
        	JFactory factory = bridgeEntry.getFactory();
        	coffeeAnalysis.setFactoryCode(factory.getCode());
        	coffeeAnalysis.setFactoryName(factory.getName());
        }

        if(coffeeAnalysisForm.getFactoryCode() != null){
        	factoryCode = coffeeAnalysisForm.getFactoryCode();
        }

        JExporter exporterEntry = null;
        List<JExporter> exporters = exporterService.findExportersByCodeEquals(coffeeAnalysisForm.getExporterCode());
        if(! exporters.isEmpty()){
        	exporterEntry = exporters.get(0);
        }
		coffeeAnalysis.setExporterEntry(exporterEntry);
		coffeeAnalysis.setExporterName(exporterEntry.getName());
		
		JFirm dealerEntry = null;
		List<JFirm> firms = firmService.findFirmsByCodeEquals(coffeeAnalysisForm.getDealerCode());
		if(!firms.isEmpty()){
			dealerEntry = firms.get(0);
		}
		coffeeAnalysis.setDealerEntry(dealerEntry);
		coffeeAnalysis.setDealerName(dealerEntry.getName());
		
		JSupplier supplierEntry = null ;
		List<JSupplier> suppliers = supplierService.findSuppliersByCodeEquals(coffeeAnalysisForm.getSupplierCode());
		if(! suppliers.isEmpty()){
			supplierEntry = suppliers.get(0);
		}
		coffeeAnalysis.setSupplierEntry(supplierEntry);
		coffeeAnalysis.setSupplierName(supplierEntry.getName());
		
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
        
        /**
    	 * Taux Humidite
    	 */
    	coffeeAnalysis.setTauxHumidite1(coffeeAnalysisForm.getTauxHumidite1());
    	coffeeAnalysis.setTauxHumidite2(coffeeAnalysisForm.getTauxHumidite2());
    	coffeeAnalysis.setTauxHumidite3(coffeeAnalysisForm.getTauxHumidite3());
    	coffeeAnalysis.setMoyenneTauxHumidite(coffeeAnalysisForm.getMoyenneTauxHumidite());
        
    	/**
    	 * General Informations
    	 */
    	coffeeAnalysis.setReference(coffeeAnalysisForm.getReference());
    	coffeeAnalysis.setNetWeightOfProductAccepted(coffeeAnalysisForm.getNetWeightOfProductAccepted());
    	coffeeAnalysis.setNumberLading(coffeeAnalysisForm.getNumberLading());
    	coffeeAnalysis.setNumberSAIGIC(coffeeAnalysisForm.getNumberSAIGIC());
    	coffeeAnalysis.setNumberOfBagAllowed(coffeeAnalysisForm.getNumberOfBagAllowed());
    	coffeeAnalysis.setTruckNumber(coffeeAnalysisForm.getTruckNumber());
    	coffeeAnalysis.setTotalOfReportedBags(coffeeAnalysisForm.getTotalOfReportedBags());
    	coffeeAnalysis.setTotalOfBagPushed(coffeeAnalysisForm.getTotalOfBagPushed());
    	coffeeAnalysis.setSampleCode(coffeeAnalysisForm.getSampleCode());
    	
    	/**
    	 * Matieres Etrangeres
    	 */
    	coffeeAnalysis.setPoidsMatieresEtrangeres(coffeeAnalysisForm.getPoidsMatieresEtrangeres());
    	coffeeAnalysis.setPourcentageMatieresEtrangeres(coffeeAnalysisForm.getPourcentageMatieresEtrangeres());
    	
    	/**
    	 * Dechets
    	 */
    	coffeeAnalysis.setPoidsDechetsParches(coffeeAnalysisForm.getPoidsDechetsParches());
    	coffeeAnalysis.setPourcentageDechetsParches(coffeeAnalysisForm.getPourcentageDechetsParches());
    	
    	coffeeAnalysis.setPoidsDechetsCerise(coffeeAnalysisForm.getPoidsDechetsCerise());
    	coffeeAnalysis.setPourcentageDechetsCerise(coffeeAnalysisForm.getPourcentageDechetsCerise());
    	
    	coffeeAnalysis.setPoidsDechetsDemiCerises(coffeeAnalysisForm.getPoidsDechetsDemiCerises());
    	coffeeAnalysis.setPourcentageDechetsDemiCerises(coffeeAnalysisForm.getPourcentageDechetsDemiCerises());
    	
    	coffeeAnalysis.setPoidsDechetsPeaux(coffeeAnalysisForm.getPoidsDechetsPeaux());
    	coffeeAnalysis.setPourcentageDechetsPeaux(coffeeAnalysisForm.getPourcentageDechetsPeaux());
    	
    	coffeeAnalysis.setPoidsDechetsCoques(coffeeAnalysisForm.getPoidsDechetsCoques());
    	coffeeAnalysis.setPourcentageDechetsCoques(coffeeAnalysisForm.getPourcentageDechetsCoques());
    	
    	coffeeAnalysis.setPoidsSousTotalDechets(coffeeAnalysisForm.getPoidsSousTotalDechets());
    	coffeeAnalysis.setPourcentageSousTotalDechets(coffeeAnalysisForm.getPourcentageSousTotalDechets());
    	
    	/**
    	 * Hors Normes
    	 */
    	coffeeAnalysis.setPoidsHorsNormesBrisures(coffeeAnalysisForm.getPoidsHorsNormesBrisures());
    	coffeeAnalysis.setPourcentageHorsNormesBrisures(coffeeAnalysisForm.getPourcentageHorsNormesBrisures());
    	
    	coffeeAnalysis.setPoidsHorsNormesGrainsDemiNoirs(coffeeAnalysisForm.getPoidsHorsNormesGrainsDemiNoirs());
    	coffeeAnalysis.setPourcentageHorsNormesGrainsDemiNoirs(coffeeAnalysisForm.getPourcentageHorsNormesGrainsDemiNoirs());
    	
    	coffeeAnalysis.setPoidsHorsNormesGrainsNoirs(coffeeAnalysisForm.getPoidsHorsNormesGrainsNoirs());
    	coffeeAnalysis.setPourcentageHorsNormesGrainsNoirs(coffeeAnalysisForm.getPourcentageHorsNormesGrainsNoirs());
    	
    	coffeeAnalysis.setPoidsSousTotalHorsNormes(coffeeAnalysisForm.getPoidsSousTotalHorsNormes());
    	coffeeAnalysis.setPourcentageSousTotalHorsNormes(coffeeAnalysisForm.getPourcentageSousTotalHorsNormes());
    	
    	/**
    	 * Grains Acceptables
    	 */
    	
    	coffeeAnalysis.setPoidsGrainsAcceptablesDemiSombre(coffeeAnalysisForm.getPoidsGrainsAcceptablesDemiSombre());
    	coffeeAnalysis.setPourcentageGrainsAcceptablesDemiSombre(coffeeAnalysisForm.getPourcentageGrainsAcceptablesDemiSombre());
    	
    	coffeeAnalysis.setPoidsGrainsAcceptablesScolytes(coffeeAnalysisForm.getPoidsGrainsAcceptablesScolytes());
    	coffeeAnalysis.setPourcentageGrainsAcceptablesScolytes(coffeeAnalysisForm.getPourcentageGrainsAcceptablesScolytes());
    	
    	coffeeAnalysis.setPoidsGrainsAcceptablesIndesirables(coffeeAnalysisForm.getPoidsGrainsAcceptablesIndesirables());
    	coffeeAnalysis.setPourcentageGrainsAcceptablesIndesirables(coffeeAnalysisForm.getPourcentageGrainsAcceptablesIndesirables());
    	
    	coffeeAnalysis.setPoidsGrainsAcceptablesSpongieux(coffeeAnalysisForm.getPoidsGrainsAcceptablesSpongieux());
    	coffeeAnalysis.setPourcentageGrainsAcceptablesSpongieux(coffeeAnalysisForm.getPourcentageGrainsAcceptablesSpongieux());
    	
    	coffeeAnalysis.setPoidsGrainsAcceptablesVert(coffeeAnalysisForm.getPoidsGrainsAcceptablesVert());
    	coffeeAnalysis.setPourcentageGrainsAcceptablesVert(coffeeAnalysisForm.getPourcentageGrainsAcceptablesVert());
    	
    	coffeeAnalysis.setPoidsGrainsAcceptablesImmature(coffeeAnalysisForm.getPoidsGrainsAcceptablesImmature());
    	coffeeAnalysis.setPourcentageGrainsAcceptablesImmature(coffeeAnalysisForm.getPourcentageGrainsAcceptablesImmature());
    	
    	/**
    	 * Calibrage
    	 */
    	coffeeAnalysis.setCalibrageBase(coffeeAnalysisForm.getCalibrageBase());
    	coffeeAnalysis.setCalibrageTamis10(coffeeAnalysisForm.getCalibrageTamis10());
    	coffeeAnalysis.setCalibrageTamis12(coffeeAnalysisForm.getCalibrageTamis12());
    	coffeeAnalysis.setCalibrageTamis14(coffeeAnalysisForm.getCalibrageTamis14());
    	coffeeAnalysis.setCalibrageTamis16(coffeeAnalysisForm.getCalibrageTamis16());
    	coffeeAnalysis.setCalibrageTamis18(coffeeAnalysisForm.getCalibrageTamis18());
    	
    	/**
    	 * Grade
    	 */
    	coffeeAnalysis.setPourcentageGradeG0(coffeeAnalysisForm.getPourcentageGradeG0());
    	coffeeAnalysis.setPourcentageGradeG1(coffeeAnalysisForm.getPourcentageGradeG1());
    	coffeeAnalysis.setPourcentageGradeG2(coffeeAnalysisForm.getPourcentageGradeG2());
    	coffeeAnalysis.setPourcentageGradeG3(coffeeAnalysisForm.getPourcentageGradeG3());
    	coffeeAnalysis.setPourcentageGradeG4(coffeeAnalysisForm.getPourcentageGradeG4());
    	coffeeAnalysis.setPourcentageGradeHN(coffeeAnalysisForm.getPourcentageGradeHN());
        
        if(coffeeAnalysisForm.getAcceptation() == 1){
    		coffeeAnalysis.setStatus(true);
    		coffeeAnalysis.setAcceptation(true);
    	}else{
    		coffeeAnalysis.setStatus(false);
    		coffeeAnalysis.setAcceptation(false);
    	}
    	coffeeAnalysis.setProductType(JUtils.COFFEE_PRODUCT);
    	
    	analysisService.persist(coffeeAnalysis);
        
        
        logger.debug("create()- a new coffeeAnalysis has been created with success !");
        
        return "redirect:/workbench/analysis/coffeeanalysis/" + encodeUrlPathSegment(coffeeAnalysis.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html")
    public String update(@Valid CoffeeAnalysisForm coffeeAnalysisForm, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) throws FactoryNotFoundException {
		
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, coffeeAnalysisForm, coffeeAnalysisForm.getFactoryCode());
            logger.debug("update() - the coffeeAnalysisForm object is invalid. Redirect to update view");
            return UPDATE_VIEW;
        }
    	
        uiModel.asMap().clear();
        
		JAnalysis coffeeAnalysis = analysisService.findAnalysis(coffeeAnalysisForm.getId(), JUtils.COFFEE_PRODUCT);
		    	
	    String factoryCode = null;
	    
	    coffeeAnalysis.setReference(coffeeAnalysisForm.getReference());
	    
	    List<JBridge> bridgeEntries = bridgeService.findBridgesByCodeEquals(coffeeAnalysisForm.getBridgeCode());
	    JBridge bridgeEntry = null;
	    
	    if(!bridgeEntries.isEmpty()){
	    	bridgeEntry = bridgeEntries.get(0);
	    }
	    
	    coffeeAnalysis.setBridge(bridgeEntry);
	    if(bridgeEntry.getFactory() != null){
	    	JFactory factory = bridgeEntry.getFactory();
	    	coffeeAnalysis.setFactoryCode(factory.getCode());
	    	coffeeAnalysis.setFactoryName(factory.getName());
	    }
	
	    if(coffeeAnalysisForm.getFactoryCode() != null){
	    	factoryCode = coffeeAnalysisForm.getFactoryCode();
	    }
	
	    JExporter exporterEntry = null;
	    List<JExporter> exporters = exporterService.findExportersByCodeEquals(coffeeAnalysisForm.getExporterCode());
	    if(! exporters.isEmpty()){
	    	exporterEntry = exporters.get(0);
	    }
		coffeeAnalysis.setExporterEntry(exporterEntry);
		coffeeAnalysis.setExporterName(exporterEntry.getName());
		
		JFirm dealerEntry = null;
		List<JFirm> firms = firmService.findFirmsByCodeEquals(coffeeAnalysisForm.getDealerCode());
		if(!firms.isEmpty()){
			dealerEntry = firms.get(0);
		}
		coffeeAnalysis.setDealerEntry(dealerEntry);
		coffeeAnalysis.setDealerName(dealerEntry.getName());
		
		JSupplier supplierEntry = null ;
		List<JSupplier> suppliers = supplierService.findSuppliersByCodeEquals(coffeeAnalysisForm.getSupplierCode());
		if(! suppliers.isEmpty()){
			supplierEntry = suppliers.get(0);
		}
		coffeeAnalysis.setSupplierEntry(supplierEntry);
		coffeeAnalysis.setSupplierName(supplierEntry.getName());
		
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
			return UPDATE_VIEW;
		}
	
	    /**
	     * General Informations
	     */
		coffeeAnalysis.setReference(coffeeAnalysisForm.getReference());
		coffeeAnalysis.setNetWeightOfProductAccepted(coffeeAnalysisForm.getNetWeightOfProductAccepted());
		coffeeAnalysis.setNumberLading(coffeeAnalysisForm.getNumberLading());
		coffeeAnalysis.setNumberSAIGIC(coffeeAnalysisForm.getNumberSAIGIC());
		coffeeAnalysis.setNumberOfBagAllowed(coffeeAnalysisForm.getNumberOfBagAllowed());
		coffeeAnalysis.setTruckNumber(coffeeAnalysisForm.getTruckNumber());
		coffeeAnalysis.setTotalOfReportedBags(coffeeAnalysisForm.getTotalOfReportedBags());
		coffeeAnalysis.setTotalOfBagPushed(coffeeAnalysisForm.getTotalOfBagPushed());
		coffeeAnalysis.setSampleCode(coffeeAnalysisForm.getSampleCode());
		
		 /**
		 * Taux Humidite
		 */
		coffeeAnalysis.setTauxHumidite1(coffeeAnalysisForm.getTauxHumidite1());
		coffeeAnalysis.setTauxHumidite2(coffeeAnalysisForm.getTauxHumidite2());
		coffeeAnalysis.setTauxHumidite3(coffeeAnalysisForm.getTauxHumidite3());
		coffeeAnalysis.setMoyenneTauxHumidite(coffeeAnalysisForm.getMoyenneTauxHumidite());
		
		/**
		 * Matieres Etrangeres
		 */
		coffeeAnalysis.setPoidsMatieresEtrangeres(coffeeAnalysisForm.getPoidsMatieresEtrangeres());
		coffeeAnalysis.setPourcentageMatieresEtrangeres(coffeeAnalysisForm.getPourcentageMatieresEtrangeres());
		
		/**
		 * Dechets
		 */
		coffeeAnalysis.setPoidsDechetsParches(coffeeAnalysisForm.getPoidsDechetsParches());
		coffeeAnalysis.setPourcentageDechetsParches(coffeeAnalysisForm.getPourcentageDechetsParches());
		
		coffeeAnalysis.setPoidsDechetsCerise(coffeeAnalysisForm.getPoidsDechetsCerise());
		coffeeAnalysis.setPourcentageDechetsCerise(coffeeAnalysisForm.getPourcentageDechetsCerise());
		
		coffeeAnalysis.setPoidsDechetsDemiCerises(coffeeAnalysisForm.getPoidsDechetsDemiCerises());
		coffeeAnalysis.setPourcentageDechetsDemiCerises(coffeeAnalysisForm.getPourcentageDechetsDemiCerises());
		
		coffeeAnalysis.setPoidsDechetsPeaux(coffeeAnalysisForm.getPoidsDechetsPeaux());
		coffeeAnalysis.setPourcentageDechetsPeaux(coffeeAnalysisForm.getPourcentageDechetsPeaux());
		
		coffeeAnalysis.setPoidsDechetsCoques(coffeeAnalysisForm.getPoidsDechetsCoques());
		coffeeAnalysis.setPourcentageDechetsCoques(coffeeAnalysisForm.getPourcentageDechetsCoques());
		
		coffeeAnalysis.setPoidsSousTotalDechets(coffeeAnalysisForm.getPoidsSousTotalDechets());
		coffeeAnalysis.setPourcentageSousTotalDechets(coffeeAnalysisForm.getPourcentageSousTotalDechets());
		
		/**
		 * Hors Normes
		 */
		coffeeAnalysis.setPoidsHorsNormesBrisures(coffeeAnalysisForm.getPoidsHorsNormesBrisures());
		coffeeAnalysis.setPourcentageHorsNormesBrisures(coffeeAnalysisForm.getPourcentageHorsNormesBrisures());
		
		coffeeAnalysis.setPoidsHorsNormesGrainsDemiNoirs(coffeeAnalysisForm.getPoidsHorsNormesGrainsDemiNoirs());
		coffeeAnalysis.setPourcentageHorsNormesGrainsDemiNoirs(coffeeAnalysisForm.getPourcentageHorsNormesGrainsDemiNoirs());
		
		coffeeAnalysis.setPoidsHorsNormesGrainsNoirs(coffeeAnalysisForm.getPoidsHorsNormesGrainsNoirs());
		coffeeAnalysis.setPourcentageHorsNormesGrainsNoirs(coffeeAnalysisForm.getPourcentageHorsNormesGrainsNoirs());
		
		coffeeAnalysis.setPoidsSousTotalHorsNormes(coffeeAnalysisForm.getPoidsSousTotalHorsNormes());
		coffeeAnalysis.setPourcentageSousTotalHorsNormes(coffeeAnalysisForm.getPourcentageSousTotalHorsNormes());
		
		/**
		 * Grains Acceptables
		 */
		
		coffeeAnalysis.setPoidsGrainsAcceptablesDemiSombre(coffeeAnalysisForm.getPoidsGrainsAcceptablesDemiSombre());
		coffeeAnalysis.setPourcentageGrainsAcceptablesDemiSombre(coffeeAnalysisForm.getPourcentageGrainsAcceptablesDemiSombre());
		
		coffeeAnalysis.setPoidsGrainsAcceptablesScolytes(coffeeAnalysisForm.getPoidsGrainsAcceptablesScolytes());
		coffeeAnalysis.setPourcentageGrainsAcceptablesScolytes(coffeeAnalysisForm.getPourcentageGrainsAcceptablesScolytes());
		
		coffeeAnalysis.setPoidsGrainsAcceptablesIndesirables(coffeeAnalysisForm.getPoidsGrainsAcceptablesIndesirables());
		coffeeAnalysis.setPourcentageGrainsAcceptablesIndesirables(coffeeAnalysisForm.getPourcentageGrainsAcceptablesIndesirables());
		
		coffeeAnalysis.setPoidsGrainsAcceptablesSpongieux(coffeeAnalysisForm.getPoidsGrainsAcceptablesSpongieux());
		coffeeAnalysis.setPourcentageGrainsAcceptablesSpongieux(coffeeAnalysisForm.getPourcentageGrainsAcceptablesSpongieux());
		
		coffeeAnalysis.setPoidsGrainsAcceptablesVert(coffeeAnalysisForm.getPoidsGrainsAcceptablesVert());
		coffeeAnalysis.setPourcentageGrainsAcceptablesVert(coffeeAnalysisForm.getPourcentageGrainsAcceptablesVert());
		
		coffeeAnalysis.setPoidsGrainsAcceptablesImmature(coffeeAnalysisForm.getPoidsGrainsAcceptablesImmature());
		coffeeAnalysis.setPourcentageGrainsAcceptablesImmature(coffeeAnalysisForm.getPourcentageGrainsAcceptablesImmature());
		
		/**
		 * Calibrage
		 */
		coffeeAnalysis.setCalibrageBase(coffeeAnalysisForm.getCalibrageBase());
		coffeeAnalysis.setCalibrageTamis10(coffeeAnalysisForm.getCalibrageTamis10());
		coffeeAnalysis.setCalibrageTamis12(coffeeAnalysisForm.getCalibrageTamis12());
		coffeeAnalysis.setCalibrageTamis14(coffeeAnalysisForm.getCalibrageTamis14());
		coffeeAnalysis.setCalibrageTamis16(coffeeAnalysisForm.getCalibrageTamis16());
		coffeeAnalysis.setCalibrageTamis18(coffeeAnalysisForm.getCalibrageTamis18());
		
		/**
		 * Grade
		 */
		coffeeAnalysis.setPourcentageGradeG0(coffeeAnalysisForm.getPourcentageGradeG0());
		coffeeAnalysis.setPourcentageGradeG1(coffeeAnalysisForm.getPourcentageGradeG1());
		coffeeAnalysis.setPourcentageGradeG2(coffeeAnalysisForm.getPourcentageGradeG2());
		coffeeAnalysis.setPourcentageGradeG3(coffeeAnalysisForm.getPourcentageGradeG3());
		coffeeAnalysis.setPourcentageGradeG4(coffeeAnalysisForm.getPourcentageGradeG4());
		coffeeAnalysis.setPourcentageGradeHN(coffeeAnalysisForm.getPourcentageGradeHN());
	        
    	if(coffeeAnalysisForm.getAcceptation() == 1){
    		coffeeAnalysis.setStatus(true);
    		coffeeAnalysis.setAcceptation(true);
    	}else{
    		coffeeAnalysis.setStatus(false);
    		coffeeAnalysis.setAcceptation(false);
    	}
	        
    	coffeeAnalysis.setModificationDate(new Date());
    	coffeeAnalysis.setModifiedBy(securityService.currentUser());
    	
		analysisService.merge(coffeeAnalysis);
	
        return "redirect:/workbench/analysis/coffeeanalysis/" + encodeUrlPathSegment(coffeeAnalysis.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
    	
    	CoffeeAnalysisForm  coffeeAnalysisForm = new CoffeeAnalysisForm();
    	JAnalysis coffeeAnalysis = analysisService.findAnalysis(id,JUtils.COFFEE_PRODUCT);

    	populateEditCoffeeAnalysisForm(coffeeAnalysis, coffeeAnalysisForm);
    	
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
    	populateEditForm(uiModel, coffeeAnalysisForm, factoryCode);
        return UPDATE_VIEW;
    }
	
	
    private void populateEditCoffeeAnalysisForm(JAnalysis coffeeAnalysis,CoffeeAnalysisForm coffeeAnalysisForm) {
    	if(coffeeAnalysis.getAcceptation() == true){
    		coffeeAnalysisForm.setAcceptation(Long.valueOf(1));
    	}else{
    		coffeeAnalysisForm.setAcceptation(Long.valueOf(0));
    	}
    	
    	coffeeAnalysisForm.setReference(coffeeAnalysis.getReference());
    	coffeeAnalysisForm.setTourId(coffeeAnalysis.getTour().getId());
    	coffeeAnalysisForm.setProvenance(coffeeAnalysis.getProvenance().getId());
    	coffeeAnalysisForm.setDealerCode(coffeeAnalysis.getDealerEntry().getDealerCode());
    	coffeeAnalysisForm.setExporterCode(coffeeAnalysis.getExporterEntry().getCode());
//    	coffeeAnalysisForm.setClassification(coffeeAnalysis.getClassification());
		coffeeAnalysisForm.setConfirmity(coffeeAnalysis.getConformity());
		coffeeAnalysisForm.setSupplierCode(coffeeAnalysis.getSupplierEntry().getCode());
		coffeeAnalysisForm.setFactoryCode(coffeeAnalysis.getFactoryCode());
		
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
    	coffeeAnalysisForm.setBridgeCode(coffeeAnalysis.getBridge().getCode());
    	
    	/**
    	 * Taux Humidite
    	 */
    	coffeeAnalysisForm.setTauxHumidite1(coffeeAnalysis.getTauxHumidite1());
    	coffeeAnalysisForm.setTauxHumidite2(coffeeAnalysis.getTauxHumidite2());
    	coffeeAnalysisForm.setTauxHumidite3(coffeeAnalysis.getTauxHumidite3());
    	coffeeAnalysisForm.setMoyenneTauxHumidite(coffeeAnalysis.getMoyenneTauxHumidite());
    	
		/**
		 * Matieres Etrangeres
		 */
    	coffeeAnalysisForm.setPoidsMatieresEtrangeres(coffeeAnalysis.getPoidsMatieresEtrangeres());
    	coffeeAnalysisForm.setPourcentageMatieresEtrangeres(coffeeAnalysis.getPourcentageMatieresEtrangeres());
		
		/**
		 * Dechets
		 */
    	coffeeAnalysisForm.setPoidsDechetsParches(coffeeAnalysis.getPoidsDechetsParches());
    	coffeeAnalysisForm.setPourcentageDechetsParches(coffeeAnalysis.getPourcentageDechetsParches());
		
    	coffeeAnalysisForm.setPoidsDechetsCerise(coffeeAnalysis.getPoidsDechetsCerise());
    	coffeeAnalysisForm.setPourcentageDechetsCerise(coffeeAnalysis.getPourcentageDechetsCerise());
		
    	coffeeAnalysisForm.setPoidsDechetsDemiCerises(coffeeAnalysis.getPoidsDechetsDemiCerises());
    	coffeeAnalysisForm.setPourcentageDechetsDemiCerises(coffeeAnalysis.getPourcentageDechetsDemiCerises());
		
    	coffeeAnalysisForm.setPoidsDechetsPeaux(coffeeAnalysis.getPoidsDechetsPeaux());
    	coffeeAnalysisForm.setPourcentageDechetsPeaux(coffeeAnalysis.getPourcentageDechetsPeaux());
		
    	coffeeAnalysisForm.setPoidsDechetsCoques(coffeeAnalysis.getPoidsDechetsCoques());
    	coffeeAnalysisForm.setPourcentageDechetsCoques(coffeeAnalysis.getPourcentageDechetsCoques());
		
    	coffeeAnalysisForm.setPoidsSousTotalDechets(coffeeAnalysis.getPoidsSousTotalDechets());
    	coffeeAnalysisForm.setPourcentageSousTotalDechets(coffeeAnalysis.getPourcentageSousTotalDechets());
		
		/**
		 * Hors Normes
		 */
    	coffeeAnalysisForm.setPoidsHorsNormesBrisures(coffeeAnalysis.getPoidsHorsNormesBrisures());
    	coffeeAnalysisForm.setPourcentageHorsNormesBrisures(coffeeAnalysis.getPourcentageHorsNormesBrisures());
		
    	coffeeAnalysisForm.setPoidsHorsNormesGrainsDemiNoirs(coffeeAnalysis.getPoidsHorsNormesGrainsDemiNoirs());
    	coffeeAnalysisForm.setPourcentageHorsNormesGrainsDemiNoirs(coffeeAnalysis.getPourcentageHorsNormesGrainsDemiNoirs());
		
    	coffeeAnalysisForm.setPoidsHorsNormesGrainsNoirs(coffeeAnalysis.getPoidsHorsNormesGrainsNoirs());
    	coffeeAnalysisForm.setPourcentageHorsNormesGrainsNoirs(coffeeAnalysis.getPourcentageHorsNormesGrainsNoirs());
		
    	coffeeAnalysisForm.setPoidsSousTotalHorsNormes(coffeeAnalysis.getPoidsSousTotalHorsNormes());
    	coffeeAnalysisForm.setPourcentageSousTotalHorsNormes(coffeeAnalysis.getPourcentageSousTotalHorsNormes());
		
		/**
		 * Grains Acceptables
		 */
		
    	coffeeAnalysisForm.setPoidsGrainsAcceptablesDemiSombre(coffeeAnalysis.getPoidsGrainsAcceptablesDemiSombre());
    	coffeeAnalysisForm.setPourcentageGrainsAcceptablesDemiSombre(coffeeAnalysis.getPourcentageGrainsAcceptablesDemiSombre());
		
    	coffeeAnalysisForm.setPoidsGrainsAcceptablesScolytes(coffeeAnalysis.getPoidsGrainsAcceptablesScolytes());
    	coffeeAnalysisForm.setPourcentageGrainsAcceptablesScolytes(coffeeAnalysis.getPourcentageGrainsAcceptablesScolytes());
		
    	coffeeAnalysisForm.setPoidsGrainsAcceptablesIndesirables(coffeeAnalysis.getPoidsGrainsAcceptablesIndesirables());
    	coffeeAnalysisForm.setPourcentageGrainsAcceptablesIndesirables(coffeeAnalysis.getPourcentageGrainsAcceptablesIndesirables());
		
    	coffeeAnalysisForm.setPoidsGrainsAcceptablesSpongieux(coffeeAnalysis.getPoidsGrainsAcceptablesSpongieux());
    	coffeeAnalysisForm.setPourcentageGrainsAcceptablesSpongieux(coffeeAnalysis.getPourcentageGrainsAcceptablesSpongieux());
		
    	coffeeAnalysisForm.setPoidsGrainsAcceptablesVert(coffeeAnalysis.getPoidsGrainsAcceptablesVert());
    	coffeeAnalysisForm.setPourcentageGrainsAcceptablesVert(coffeeAnalysis.getPourcentageGrainsAcceptablesVert());
		
    	coffeeAnalysisForm.setPoidsGrainsAcceptablesImmature(coffeeAnalysis.getPoidsGrainsAcceptablesImmature());
    	coffeeAnalysisForm.setPourcentageGrainsAcceptablesImmature(coffeeAnalysis.getPourcentageGrainsAcceptablesImmature());
    	
    	coffeeAnalysisForm.setPoidsSousTotalGrainsAcceptables(coffeeAnalysis.getPoidsSousTotalGrainsAcceptables());
    	coffeeAnalysisForm.setPourcentageSousTotalGrainsAcceptables(coffeeAnalysis.getPourcentageSousTotalGrainsAcceptables());
		
		/**
		 * Calibrage
		 */
    	coffeeAnalysisForm.setCalibrageBase(coffeeAnalysis.getCalibrageBase());
    	coffeeAnalysisForm.setCalibrageTamis10(coffeeAnalysis.getCalibrageTamis10());
    	coffeeAnalysisForm.setCalibrageTamis12(coffeeAnalysis.getCalibrageTamis12());
    	coffeeAnalysisForm.setCalibrageTamis14(coffeeAnalysis.getCalibrageTamis14());
    	coffeeAnalysisForm.setCalibrageTamis16(coffeeAnalysis.getCalibrageTamis16());
    	coffeeAnalysisForm.setCalibrageTamis18(coffeeAnalysis.getCalibrageTamis18());
		
		/**
		 * Grade
		 */
    	coffeeAnalysisForm.setPourcentageGradeG0(coffeeAnalysis.getPourcentageGradeG0());
    	coffeeAnalysisForm.setPourcentageGradeG1(coffeeAnalysis.getPourcentageGradeG1());
    	coffeeAnalysisForm.setPourcentageGradeG2(coffeeAnalysis.getPourcentageGradeG2());
    	coffeeAnalysisForm.setPourcentageGradeG3(coffeeAnalysis.getPourcentageGradeG3());
    	coffeeAnalysisForm.setPourcentageGradeG4(coffeeAnalysis.getPourcentageGradeG4());
    	coffeeAnalysisForm.setPourcentageGradeHN(coffeeAnalysis.getPourcentageGradeHN());
    	
    	coffeeAnalysisForm.setConfirmity(coffeeAnalysis.getConformity());
    	
//    	coffeeAnalysisForm.setStatus(coffeeAnalysis.getStatus());
    	coffeeAnalysisForm.setVersion(coffeeAnalysis.getVersion());
		
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
    public String findAnalysisByQuerySearch(@Valid FilterAnalysisForm filterCoffeeAnalysis, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest){
    	
    	if (bindingResult.hasErrors()) {
    		populateEditFindAnalysis(uiModel, filterCoffeeAnalysis);
            logger.debug("findAnalysisByQuerySearch() - the filterCoffeeAnalysis object is invalid. Redirect to create view");
            return FIND_VIEW;
    	}
    	
    	uiModel.asMap().clear();
    	
    	List<JAnalysis> coffeeAnalysis = analysisService.findAnalysisByReferenceEqualsAndProductType(filterCoffeeAnalysis.getAnalysisReference(),JUtils.COFFEE_PRODUCT);
    	uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);
    	
    	populateEditFindAnalysis(uiModel, filterCoffeeAnalysis);
    	
    	return FIND_VIEW;
    }
    
    
    @RequestMapping(value = "/find", params = "form", produces = "text/html")
    public String findAnalysisByQuerySearchForm( Model uiModel, HttpServletRequest httpServletRequest){
    	populateEditFindAnalysis(uiModel, new FilterAnalysisForm());
    	return FIND_VIEW;
    }
    
    void populateEditFindAnalysis(Model uiModel, FilterAnalysisForm filterCoffeeAnalysis){
    	uiModel.addAttribute("filterCoffeeAnalysis", filterCoffeeAnalysis);
    	uiModel.addAttribute("currentNav", "analysisCoffee");
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) throws CoffeeAnalysisNotFoundException {
    	
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
    	if(factoryCode != null){
    		JAnalysis coffeeAnalysis = analysisService.findAnalysis(id, JUtils.COFFEE_PRODUCT);
    		
    		if(!coffeeAnalysis.getFactoryCode().equals(factoryCode) || coffeeAnalysis == null){
    			
    			String errMsg = ">>>> show() - Failed trying to access an other factory's coffeeAnalysis with id : " + coffeeAnalysis.getId();
   	   	   	 	logger.error(errMsg);
   	   	   	 	throw new CoffeeAnalysisNotFoundException("Aucun usine avec cet identifiant [ "+ coffeeAnalysis.getId() +" ] au sein de votre usine "); 
    		}
    		
    		uiModel.addAttribute("currentNav", "analysisCoffee");
            uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);
            uiModel.addAttribute("itemId", id);
            
            return  SHOW_VIEW;
    	}
    	
    	JAnalysis coffeeAnalysis = analysisService.findAnalysis(id, JUtils.COFFEE_PRODUCT);;

       if(coffeeAnalysis == null){
    	   String errMsg = ">>>> show() - Failed trying to access an factory's coffeeAnalysis with id : " + id;
   	   	 	logger.error(errMsg);
   	   	 	throw new CoffeeAnalysisNotFoundException("Aucune Analyse avec cet identifiant [ "+ id +" ] au sein de votre entreprise "); 
       }
       
       uiModel.addAttribute("currentNav", "analysisCoffee");
       uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);
       uiModel.addAttribute("itemId", id);
        
        return  SHOW_VIEW;
    }    
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "factoryFilter", required = false)String factoryFilter,@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel, HttpServletRequest httpServletRequest) throws FactoryNotFoundException {
        
    	if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            
            List<JAnalysis> coffeeAnalysis = new ArrayList<JAnalysis>();
            long nbr = 0;
            
            if(factoryFilter != null){
            	nbr = analysisService.countAnalysesInFactory(factoryFilter, JUtils.COFFEE_PRODUCT);
            	coffeeAnalysis = analysisService.findAnalysisByFactoryCodeEntries(factoryFilter, JUtils.COFFEE_PRODUCT, firstResult, sizeNo);
            }else{
            	nbr = analysisService.countAnalyses(JUtils.COFFEE_PRODUCT);
            	coffeeAnalysis = analysisService.findAnalysisEntries(firstResult, sizeNo,  JUtils.COFFEE_PRODUCT);
            }
            
            uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);
            float nrOfPages = (float)  nbr / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            
        } else {
        	List<JAnalysis> coffeeAnalysis = new ArrayList<JAnalysis>();
        	
            if(factoryFilter != null){
            	coffeeAnalysis = analysisService.findAnalysisByFactoryCode(factoryFilter, JUtils.COFFEE_PRODUCT);
            	
            }else{
            	coffeeAnalysis = analysisService.findAllAnalyses(JUtils.COFFEE_PRODUCT);
            	
            }
        	
            uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);
        }
    	
        
    	FilterAnalysisForm filterAnalysisForm = new FilterAnalysisForm();
    	uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
    	
    	if(factoryFilter != null){
    		filterAnalysisForm.setFactoryFilter(factoryFilter);
    		uiModel.addAttribute("factories", factoryService.findAllFactories());
    	}
    	
    	uiModel.addAttribute("currentNav", "analysisCoffee");
        
        return LIST_VIEW;
    }
    
    
    void populateEditForm(Model uiModel, CoffeeAnalysisForm coffeeAnalysisForm, String factoryCode ) {

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
    	
        uiModel.addAttribute("coffeeAnalysisForm", coffeeAnalysisForm);
        
        uiModel.addAttribute("currentNav", "analysisCoffee");
        
    }
    
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("JAnalysis__dateofanalysis_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JAnalysis__starttime_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JAnalysis__endtime_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JAnalysis__creationdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JAnalysis__modificationdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
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