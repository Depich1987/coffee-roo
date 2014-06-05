package com.j1987.coffeeroo.web.workbench;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
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

import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JSubmissionForApproval;
import com.j1987.coffeeroo.framework.FactoryNotFoundException;
import com.j1987.coffeeroo.framework.JUtils;
import com.j1987.coffeeroo.services.dao.CoffeeAnalysisService;
import com.j1987.coffeeroo.services.dao.FactoryService;
import com.j1987.coffeeroo.services.dao.SubmissionService;
import com.j1987.coffeeroo.services.security.JSecurityService;
import com.j1987.coffeeroo.web.form.ReportFilterForm;

@Controller
@RequestMapping(value = "/workbench/submissions/coffee")
public class WorkbenchSubmissionCoffeeController {
	
	private static final String UPDATE_VIEW = "workbench/submissioncoffee/update";
	private static final String GENERATE_REPORT_COFFEEANALYSIS = "jcoffeeanalysis_analysiscoffeereport";
	private static final String SHOW_VIEW = "workbench/submissioncoffee/show";
	private static final String LIST_VIEW = "workbench/submissioncoffee/list";
	private static final String CREATE_VIEW = "workbench/submissioncoffee/create";
	
	private static Logger logger = Logger.getLogger(WorkbenchSubmissionCoffeeController.class);
	
//	@Autowired
//	private CoffeeAnalysisService coffeeAnalysisService;
	
	@Autowired
	private JSecurityService securityService;
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private SubmissionService submissionService;

	public WorkbenchSubmissionCoffeeController() {
		// TODO Auto-generated constructor stub
	}
	
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JSubmissionForApproval submission, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        
    	if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, submission);
            logger.debug("create() - the submission object is invalid. Redirect to create view");
            return CREATE_VIEW;
        }
        uiModel.asMap().clear();
        
    	String referenceGenerated = RandomStringUtils.random(8, true, true);
    	submission.setReference(referenceGenerated.toUpperCase());
        submission.setCreationDate(new Date());
        submission.setCreatedBy(securityService.currentUser());
        submission.setStatus(Long.valueOf("1"));
        submissionService.persist(submission);
        
        return "redirect:/workbench/submissions/coffee/" + encodeUrlPathSegment(submission.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/create", params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new JSubmissionForApproval());
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
    	
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("submission", submissionService.findSubmissionForApproval(id));
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("currentNav", "submissions");
        
        return SHOW_VIEW;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("submissions", submissionService.findSubmissionForApprovalEntries(firstResult, sizeNo));
            float nrOfPages = (float) submissionService.countSubmissionForApprovals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("submissions", submissionService.findAllSubmissionForApprovals());
        }
        addDateTimeFormatPatterns(uiModel);
        
        uiModel.addAttribute("currentNav", "submissions");
        
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/list/filter", produces = "text/html")
    public String listByFilter(@RequestParam(value = "factoryCode", required = false) String factoryCode, 
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, 
    		Model uiModel, HttpServletRequest httpServletRequest) throws FactoryNotFoundException {
        
    	HttpSession session = httpServletRequest.getSession();
    	factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
    	if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("submissions", submissionService.findSubmissionForApprovalEntries(firstResult, sizeNo));
            float nrOfPages = (float) submissionService.countSubmissionForApprovals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("submissions", submissionService.findAllSubmissionForApprovals());
        }
        addDateTimeFormatPatterns(uiModel);
        
        uiModel.addAttribute("currentNav", "submissions");
        
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JSubmissionForApproval submission, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
         if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, submission);
            logger.debug("update() - the submission object is invalid. Redirect to update view");
            return UPDATE_VIEW;
        }
        uiModel.asMap().clear();
        
        JSubmissionForApproval submissionForApproval = submissionService.findSubmissionForApproval(submission.getId());
        
        submissionService.update(submission);
        Set<JCoffeeAnalysis> analyzesCoffee = new HashSet<JCoffeeAnalysis>(submissionForApproval.getAnalyzesCoffee());
        for (JCoffeeAnalysis analysis : analyzesCoffee) {
        	analysis.setStatus(Long.valueOf("2"));
		}
        submissionForApproval.setAnalyzesCoffee(analyzesCoffee);
        submissionService.merge(submissionForApproval);
        return "redirect:/workbench/submissions/coffee/" + encodeUrlPathSegment(submission.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, submissionService.findSubmissionForApproval(id));
        return UPDATE_VIEW;
    }

    @RequestMapping(value = "/generatefile/{submissionId}",method = RequestMethod.GET)
    public String generateAnalysiscoffeereport(@PathVariable("submissionId")Long submissionId, Model uiModel) {
        
        
    	JSubmissionForApproval submissionForApproval = submissionService.findSubmissionForApproval(submissionId);
    	
        final String REGEX = "(pdf|xls)";
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("xls");
       
        if ( !matcher.matches() ) {
                uiModel.addAttribute("error", "message_format_invalid");
                uiModel.addAttribute("submission", submissionForApproval);
                return SHOW_VIEW;
        }
        
        List<JCoffeeAnalysis> analysis = new ArrayList<JCoffeeAnalysis>();
        analysis.addAll(submissionForApproval.getAnalyzesCoffee());
        Collection<JCoffeeAnalysis> dataSource = enrichedAnalysis(analysis,submissionForApproval);
        if (dataSource.isEmpty()) {
                uiModel.addAttribute("error", "message_emptyresults_noreportgeneration");
                uiModel.addAttribute("submission", submissionForApproval);
                return SHOW_VIEW;
        }

        uiModel.addAttribute("format", "xls");
        uiModel.addAttribute("title", "ANALYSISCOFFEEREPORT");
        uiModel.addAttribute("analysiscoffeereportList", dataSource);
        
        return GENERATE_REPORT_COFFEEANALYSIS;
    }
    
     List<JCoffeeAnalysis> enrichedAnalysis(List<JCoffeeAnalysis> coffeeAnalysis,JSubmissionForApproval submissionForApproval){

    	for (JCoffeeAnalysis jCoffeeAnalysis : coffeeAnalysis) {
    		
    		jCoffeeAnalysis.setFactoryName(jCoffeeAnalysis.getFactoryEntry().getName());
    		jCoffeeAnalysis.setDealerName(jCoffeeAnalysis.getDealerEntry().getName());
    		jCoffeeAnalysis.setSupplierName(jCoffeeAnalysis.getSupplierEntry().getName());
    		jCoffeeAnalysis.setExporterName(jCoffeeAnalysis.getExporterEntry().getName());

    		jCoffeeAnalysis.setStatus(Long.valueOf("1"));
    		jCoffeeAnalysis.merge();
        		

		}
    	
    	Set<JCoffeeAnalysis> analysisCasted = new HashSet<JCoffeeAnalysis>();
    	analysisCasted.addAll(coffeeAnalysis);
    	submissionForApproval.setAnalyzesCoffee(analysisCasted);
    	submissionForApproval.merge();
    	
    	return coffeeAnalysis;
    }
    
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("submission_creationdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void populateEditForm(Model uiModel, JSubmissionForApproval submission) {
        uiModel.addAttribute("submission", submission);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("analysis", submission.getAnalyzesCoffee());
        uiModel.addAttribute("currentNav", "submissions");
        uiModel.addAttribute("statusMap", JUtils.populateUiStatusMap());
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
