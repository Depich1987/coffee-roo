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

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JSubmissionForApproval;
import com.j1987.coffeeroo.framework.JUtils;
import com.j1987.coffeeroo.services.dao.CoffeeAnalysisService;
import com.j1987.coffeeroo.services.dao.FactoryService;
import com.j1987.coffeeroo.services.security.JSecurityService;
import com.j1987.coffeeroo.web.form.FilterAnalysisForm;
import com.j1987.coffeeroo.web.form.ReportFilterForm;

@Controller
@RequestMapping(value = "/workbench/reports")
public class WorkbenchReportController {
	
	private static final String PERIODICALREPORT_VIEW = "workbench/report/periodicalreport";
	private static Logger logger = Logger.getLogger(WorkbenchReportController.class);
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private CoffeeAnalysisService coffeeAnalysisService;
	
	@Autowired
	private JSecurityService securityService;

	public WorkbenchReportController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/findcoffeeanalysis", method = RequestMethod.POST ,produces = "text/html")
	public String findCoffeeAnalysisByFilter(@Valid FilterAnalysisForm filterCoffeeAnalysis, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest){
		
		
		Date startDate = null;
		Date endDate = null;
		
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
		
		 try {
				 startDate = JUtils.DATE_FORMAT.parse(filterCoffeeAnalysis.getStartDate());
				 endDate = JUtils.DATE_FORMAT.parse(filterCoffeeAnalysis.getEndDate());
		 }
		 catch (ParseException e) {

			 populateEditFindAnalysis(uiModel, filterCoffeeAnalysis, factoryCode);
				logger.debug("findCoffeeAnalysisByFilter() - message :"+e.getMessage());
				return PERIODICALREPORT_VIEW;
			}
		 uiModel.asMap().clear();
		 
		 List<JCoffeeAnalysis> coffeeAnalysis = new ArrayList<JCoffeeAnalysis>();
		 List<JFactory> factories = factoryService.findFactoriesByCodeEquals(filterCoffeeAnalysis.getFactoryFilter());
		 if(!factories.isEmpty()){
			 coffeeAnalysis.addAll(coffeeAnalysisService.findNotSentCoffeeAnalysisByFactoryListAndCoffeeAnalysisDateBetween(factories, startDate, endDate));
		 }
		 
		 uiModel.addAttribute("coffeeAnalysis", coffeeAnalysis);

		 if(!coffeeAnalysis.isEmpty()){
			 uiModel.addAttribute("reportFilterForm", new ReportFilterForm());
		 }
		
		 populateEditFindAnalysis(uiModel, new FilterAnalysisForm(), factoryCode);
		 
		 uiModel.addAttribute("resultDispaly", true);
		 
		return PERIODICALREPORT_VIEW;
	}
	
    @RequestMapping(value = "/findcoffeeanalysis", params = "form", produces = "text/html")
    public String findCoffeeAnalysisByFilterForm( Model uiModel, HttpServletRequest httpServletRequest){
    	
    	HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
    	populateEditFindAnalysis(uiModel, new FilterAnalysisForm(), factoryCode);
    	return PERIODICALREPORT_VIEW;
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
        
        Collection<JCoffeeAnalysis> dataSource = enrichedAnalysis(coffeeAnalysisService.findNotSentCoffeeAnalysisByReferenceList(reportFilterForm.getAnalysisList()), reportFilterForm.getAutomaticSubmission(), reportFilterForm.getDescription());
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
    
     List<JCoffeeAnalysis> enrichedAnalysis(List<JCoffeeAnalysis> coffeeAnalysis, boolean status,String description){
    	
    	 JSubmissionForApproval submissionForApproval = new JSubmissionForApproval();
    	 
    	 if(status == true){
    		 submissionForApproval.setDescription(description);
	        String referenceGenerated = RandomStringUtils.random(8, true, true);
	        submissionForApproval.setReference(referenceGenerated.toUpperCase());
	        submissionForApproval.setCreationDate(new Date());
	        submissionForApproval.setCreatedBy(securityService.currentUser());
	        submissionForApproval.setStatus(Long.valueOf("1"));
		    submissionForApproval.persist();
    	 }

    	for (JCoffeeAnalysis jCoffeeAnalysis : coffeeAnalysis) {
    		
    		jCoffeeAnalysis.setFactoryName(jCoffeeAnalysis.getFactoryEntry().getName());
    		jCoffeeAnalysis.setDealerName(jCoffeeAnalysis.getDealerEntry().getName());
    		jCoffeeAnalysis.setSupplierName(jCoffeeAnalysis.getSupplierEntry().getName());
    		jCoffeeAnalysis.setExporterName(jCoffeeAnalysis.getExporterEntry().getName());
    		
    		if(status == true ){
        		jCoffeeAnalysis.setStatus(Long.valueOf("1"));
        		jCoffeeAnalysis.merge();
        		submissionForApproval.getAnalyzesCoffee().add(jCoffeeAnalysis);
    		}

		}
    	
    	if(status ==  true){
    		submissionForApproval.merge();
    	}
    	
    	return coffeeAnalysis;
    }
    
    void populateEditFindAnalysis(Model uiModel, FilterAnalysisForm filterCoffeeAnalysis, String factoryCode){
    	uiModel.addAttribute("filterCoffeeAnalysis", filterCoffeeAnalysis);
    	uiModel.addAttribute("currentNav", "periodicalreport");
    	
    	List<JFactory>  factories = null;
    	
    	if(factoryCode == null){
    		 factories = factoryService.findAllFactories();
    	}else{
    		factories = factoryService.findFactoriesByCodeEquals(factoryCode);
    	}
    	
    	 
    	if(!factories.isEmpty()){
    		uiModel.addAttribute("factories", factories);
    	}
    }
}
