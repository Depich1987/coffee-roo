package com.j1987.coffeeroo.web.workbench;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.framework.JUtils;
import com.j1987.coffeeroo.services.dao.AnalysisService;
import com.j1987.coffeeroo.services.dao.CoffeeAnalysisService;
import com.j1987.coffeeroo.services.dao.FactoryService;
import com.j1987.coffeeroo.services.dao.LocalizationService;
import com.j1987.coffeeroo.web.form.FilterAnalysisForm;
import com.j1987.coffeeroo.web.form.ReportFilterForm;

@Controller
@RequestMapping(value = "/workbench/reports")
public class WorkbenchReportController {
	
	private static final String PERIODICALREPORT_VIEW = "workbench/report/periodicalreport";
	private static final String PERIODICALREPORTPARAMS_VIEW = "workbench/report/periodicalreportparams";
	
	private static final String PERIODICALREPORTRESULTCOFFEE_VIEW = "workbench/report/periodicalreportcoffee";
	private static final String PERIODICALREPORTRESULTCOCOA_VIEW = "workbench/report/periodicalreportcocoa";
	
	private static final String GENERATE_REPORT_COFFEEANALYSIS = "jcoffeeanalysis_analysiscoffeereport";
	private static final String GENERATE_REPORT_COCOAANALYSIS = "jcocoaanalysis_analysiscocoareport";
	
	private static Logger logger = Logger.getLogger(WorkbenchReportController.class);
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private CoffeeAnalysisService coffeeAnalysisService;
	
	@Autowired
	private AnalysisService analysisService;
	
//	@Autowired
//	private BridgeService bridgeService;
	
	@Autowired
	private LocalizationService localizationService;
//	
//	@Autowired
//	private JSecurityService securityService;

	public WorkbenchReportController() {
		// TODO Auto-generated constructor stub
	}
	
	
    
    @RequestMapping(value = "/generatefile",method = RequestMethod.GET)
    public String generateAnalysiscoffeereport(@Valid ReportFilterForm  reportFilterForm, Model uiModel) {
        
    	if ( null == reportFilterForm.getFormat() || reportFilterForm.getFormat().length() <= 0 ) {
                uiModel.addAttribute("error", "message_format_required");
                uiModel.addAttribute("reportFilterForm", reportFilterForm);
                return PERIODICALREPORT_VIEW;
        }
        
        final String REGEX = "(pdf|xls)";
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(reportFilterForm.getFormat());
       
        if ( !matcher.matches() ) {
                uiModel.addAttribute("error", "message_format_invalid");
                uiModel.addAttribute("reportFilterForm", reportFilterForm);
                return PERIODICALREPORT_VIEW;
        }
        
        Collection<JCoffeeAnalysis> dataSource = coffeeAnalysisService.findNotSentCoffeeAnalysisByReferenceList(reportFilterForm.getAnalysisList());
        if (dataSource.isEmpty()) {
                uiModel.addAttribute("error", "message_emptyresults_noreportgeneration");
                uiModel.addAttribute("reportFilterForm", reportFilterForm);
                return PERIODICALREPORT_VIEW;
        }
        
        
        
        uiModel.addAttribute("format", reportFilterForm.getFormat());
        uiModel.addAttribute("title", "ANALYSISCOFFEEREPORT");
        uiModel.addAttribute("analysiscoffeereportList", dataSource);
        
        return "jcoffeeanalysis_analysiscoffeereport";
    }
    
    @RequestMapping(value = "/findanalysis", method = RequestMethod.GET ,produces = "text/html")
	public String findAnalysisByFilter(@Valid FilterAnalysisForm filterAnalysisForm, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel, HttpServletRequest httpServletRequest, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
            populateEditFindAnalysis(uiModel, filterAnalysisForm, filterAnalysisForm.getFactoryFilter());
            logger.debug("generateAnalysisByFilter() - the filterAnalysisForm object is invalid. Redirect to search view");
            return PERIODICALREPORTPARAMS_VIEW;
        }
		
		Date startDate = null;
		Date endDate = null;
		
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
		
		 try {
			 
			 	if(filterAnalysisForm.getStartDate() != null){
			 		startDate = JUtils.DATE_FORMAT.parse(filterAnalysisForm.getStartDate());
			 	}
			 	if(filterAnalysisForm.getEndDate() != null){
				 endDate = JUtils.DATE_FORMAT.parse(filterAnalysisForm.getEndDate());
			 	}
				 
				 if(startDate != null){
					 filterAnalysisForm.setSd(startDate);
				 }
				 
				 if(endDate != null){
					 filterAnalysisForm.setEd(endDate);
				 }
		 }
		 catch (ParseException e) {

			 populateEditFindAnalysis(uiModel, filterAnalysisForm, factoryCode);
				logger.debug("findAnalysisByFilter() - message :"+e.getMessage());
				return PERIODICALREPORTPARAMS_VIEW;
			}
		 uiModel.asMap().clear();
		 
		
		 if (page != null || size != null) {
			 
			 int sizeNo = size == null ? 10 : size.intValue();
	         final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
	         
	         filterAnalysisForm.setPage(page);
	         filterAnalysisForm.setSize(size);
	         filterAnalysisForm.setFirstResult(firstResult);
	         filterAnalysisForm.setMaxResult(sizeNo);
	         
	            float nrOfPages = (float) size / sizeNo;
	            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		 }
		 
		
		 
		 List<JAnalysis> analysis = analysisService.findAnalysisByFilterForm(filterAnalysisForm);
		 
		 uiModel.addAttribute("analysis", analysis);
		 
		 uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
		 uiModel.addAttribute("currentNav", "periodicalanalysisreport");
		 
		 if(filterAnalysisForm.getProductTypeFilter().equals(JUtils.COFFEE_PRODUCT)){
//			 populateEditFindAnalysis(uiModel, filterAnalysisForm, factoryCode);
			 return PERIODICALREPORTRESULTCOFFEE_VIEW;
		 }
		 
//		 populateEditFindAnalysis(uiModel, filterAnalysisForm, factoryCode);
		return PERIODICALREPORTRESULTCOCOA_VIEW;
	}
    
    
    @RequestMapping(value = "/generatereport", method = RequestMethod.GET ,produces = "text/html")
	public String generateAnalysisByFilter(@Valid FilterAnalysisForm filterAnalysisForm, Model uiModel, HttpServletRequest httpServletRequest,  BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
            populateEditFindAnalysis(uiModel, filterAnalysisForm, filterAnalysisForm.getFactoryFilter());
            logger.debug("generateAnalysisByFilter() - the filterAnalysisForm object is invalid. Redirect to search view");
            return PERIODICALREPORTPARAMS_VIEW;
        }
    	
		String myFormat = "xls";
    	
    	if ( null == myFormat || myFormat.length() <= 0 ) {
            uiModel.addAttribute("error", "message_format_required");
            uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
            return PERIODICALREPORTPARAMS_VIEW;
    }
    
    final String REGEX = "(pdf|xls)";
    Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(myFormat);
   
    if ( !matcher.matches() ) {
            uiModel.addAttribute("error", "message_format_invalid");
            uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
            return PERIODICALREPORTPARAMS_VIEW;
    }
    
    Date startDate = null;
	Date endDate = null;
	
	
	HttpSession session = httpServletRequest.getSession();
	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
	
	try {
		 
	 	if(filterAnalysisForm.getStartDate() != null){
	 		startDate = JUtils.DATE_FORMAT.parse(filterAnalysisForm.getStartDate());
	 	}
	 	if(filterAnalysisForm.getEndDate() != null){
		 endDate = JUtils.DATE_FORMAT.parse(filterAnalysisForm.getEndDate());
	 	}
		 
		 if(startDate != null){
			 filterAnalysisForm.setSd(startDate);
		 }
		 
		 if(endDate != null){
			 filterAnalysisForm.setEd(endDate);
		 }
 }
 catch (ParseException e) {

	 populateEditFindAnalysis(uiModel, filterAnalysisForm, factoryCode);
		logger.debug("findAnalysisByFilter() - message :"+e.getMessage());
		return PERIODICALREPORTPARAMS_VIEW;
	}

	filterAnalysisForm.setFirstResult(0);
	filterAnalysisForm.setMaxResult(0);
	
    Collection<JAnalysis> dataSource = new ArrayList<JAnalysis>();
    dataSource = analysisService.findAnalysisByFilterForm(filterAnalysisForm);

    if (dataSource.isEmpty()) {
            uiModel.addAttribute("error", "message_emptyresults_noreportgeneration");
            uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
            return PERIODICALREPORTPARAMS_VIEW;
    }
    
    
    
    uiModel.addAttribute("format", myFormat);
    
    if(filterAnalysisForm.getProductTypeFilter().equals(JUtils.COCOA_PRODUCT)){
        uiModel.addAttribute("title", "ANALYSISCOCOAREPORT");
        uiModel.addAttribute("analysiscocoareportList", dataSource);
        
        return GENERATE_REPORT_COCOAANALYSIS;
    }

    uiModel.addAttribute("title", "ANALYSISCOFFEEREPORT");
    uiModel.addAttribute("analysiscoffeereportList", dataSource);
    
    return GENERATE_REPORT_COFFEEANALYSIS;
    
    
    
	}
    
    
    @RequestMapping(value = "/findanalysis", params = "form", produces = "text/html")
    public String findAnalysisByFilterForm( Model uiModel, HttpServletRequest httpServletRequest){
    	
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
    	populateEditFindAnalysis(uiModel, new FilterAnalysisForm(), factoryCode);
    	return PERIODICALREPORTPARAMS_VIEW;
    }

    
    void populateEditFindAnalysis(Model uiModel, FilterAnalysisForm filterAnalysisForm, String factoryCode){
    	uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
    	uiModel.addAttribute("currentNav", "periodicalanalysisreport");
    	
    	List<JFactory>  factories = null;
    	
    	if(factoryCode == null){
    		 factories = factoryService.findAllFactories();
    	}else{
    		factories = factoryService.findFactoriesByCodeEquals(factoryCode);
    	}
    	
    	 JFactory factory = null;
    	if(!factories.isEmpty()){
    		uiModel.addAttribute("factories", factories);
    		factory = factories.get(0);
    		filterAnalysisForm.setFactoryFilter(factory.getCode());
    	}
    	List<JBridge> bridges = new ArrayList<JBridge>();
    	if(factory != null){
    		bridges.addAll(factory.getBridges());
    	}
    	uiModel.addAttribute("bridges", bridges);
    	uiModel.addAttribute("localizations", localizationService.findAllLocalizations());
    }
}
