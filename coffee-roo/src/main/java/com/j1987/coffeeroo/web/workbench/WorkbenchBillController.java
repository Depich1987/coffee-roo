package com.j1987.coffeeroo.web.workbench;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.j1987.coffeeroo.domain.JBill;
import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JSubmissionForApproval;
import com.j1987.coffeeroo.framework.JUtils;
import com.j1987.coffeeroo.services.dao.BillService;
import com.j1987.coffeeroo.services.dao.FactoryService;
import com.j1987.coffeeroo.services.dao.SubmissionService;
import com.j1987.coffeeroo.services.security.JSecurityService;
import com.j1987.coffeeroo.web.form.BillEvaluateForm;
import com.j1987.coffeeroo.web.form.FilterAnalysisForm;

@Controller
@RequestMapping(value = "/workbench/bills")
public class WorkbenchBillController {
	
	private static final String UPDATE_VIEW = "workbench/bills/update";
	private static final String EVALUATEPARAMS_VIEW = "workbench/bills/evaluateparams";
	private static final String EVALUATE_VIEW = "workbench/bills/evaluate";
	private static final String SHOW_VIEW = "workbench/bills/show";
	private static final String LIST_VIEW = "workbench/bills/list";
	private static final String CREATE_VIEW = "workbench/bills/create";
	
	private static Logger logger = Logger.getLogger(WorkbenchBillController.class);
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private JSecurityService securityService;

	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private SubmissionService submissionService;

	public WorkbenchBillController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid JBill bill, BindingResult bindingResult,
    		Model uiModel, 
    		HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bill,bill.getSubmission().getReference());
            return CREATE_VIEW;
        }
        
        try {
			Date myDate = JUtils.DATE_FORMAT.parse(bill.getDateOfBillAsString());
			
			if(myDate != null){
				bill.setDateOfBill(myDate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        uiModel.asMap().clear();
        bill.persist();
        return "redirect:/workbench/bills/" + encodeUrlPathSegment(bill.getId().toString(), httpServletRequest);
    }
	
	
	@RequestMapping(value = "/evaluate", params="form", method= RequestMethod.GET)
	public String evaluateBillForm(Model uiModel, HttpServletRequest httpServletRequest){
		
		HttpSession session = httpServletRequest.getSession();
    	String factoryCode = (String)session.getAttribute(JUtils.HTTP_SESSION_FACTORY_CODE);
    	
		populateEditBillEvaluator(uiModel, new FilterAnalysisForm() , factoryCode);
		
		
		return EVALUATEPARAMS_VIEW;
	}
	
    @RequestMapping(value = "/generatefile/{id}",method = RequestMethod.GET)
    public String generateBillreport(@PathVariable("id")Long id,
    		@RequestParam("format")String format,
    		Model uiModel, HttpServletRequest httpServletRequest) {
        
    	if ( null == format || format.length() <= 0 ) {
                uiModel.addAttribute("error", "message_format_required");
//                populateEditForm(uiModel, bill, submissionReference);
                return SHOW_VIEW;
        }
        
        final String REGEX = "(pdf)";
        Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(format);
       
        if ( !matcher.matches() ) {
                uiModel.addAttribute("error", "message_format_invalid");
                return "redirect:/workbench/bills/" + encodeUrlPathSegment(id.toString(), httpServletRequest);
        }
        
        Collection<JBill> dataSource =  new ArrayList<JBill>();
        JBill  bill =  billService.findBill(id);
        
        if(bill != null){
        	dataSource.add(bill);
        }
        if (dataSource.isEmpty()) {
                uiModel.addAttribute("error", "message_emptyresults_noreportgeneration");
                return "redirect:/workbench/bills/" + encodeUrlPathSegment(id.toString(), httpServletRequest);
        }
//        JCompany company = companyService.findCompanyById(Long.valueOf(1));
        
//        uiModel.addAttribute("coffeePrice", company.getCoffeePrice());
//        uiModel.addAttribute("cocoaPrice", company.getCocoaPrice());
        uiModel.addAttribute("numberToWord", "");
        uiModel.addAttribute("productType", "Cafe");
        uiModel.addAttribute("format", format);
        uiModel.addAttribute("title", "FACTURE");
        uiModel.addAttribute("bill_reportlist", dataSource);
        
        return "bill_reportlist";
    }
    
    @RequestMapping(value = "/create/{submissionReference}", params = "form", produces = "text/html")
    public String createForm(@PathVariable("submissionReference")String submissionReference,Model uiModel) {
        populateEditForm(uiModel, new JBill(),submissionReference);
        return CREATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("bill", JBill.findJBill(id));
        uiModel.addAttribute("itemId", id);
        return SHOW_VIEW;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("bills", billService.findBillEntries(firstResult, sizeNo));
            float nrOfPages = (float) billService.countBills() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("bills", billService.findAllBills());
        }
        
        uiModel.addAttribute("factories", factoryService.findAllFactories());
        uiModel.addAttribute("currentNav", "bills");
        
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/list/filter", produces = "text/html")
    public String list(@RequestParam(value = "factoryCode", required = false) String factoryCode,@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("bills", billService.findBillByFactoryCodeEntries(factoryCode,firstResult, sizeNo));
            float nrOfPages = (float) billService.countBillsByFactoryCode(factoryCode)/ sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("bills", billService.findBillByFactoryCode(factoryCode));
        }
        
        populateEditListFilterForm(uiModel,factoryCode);
        
        return LIST_VIEW;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid JBill bill, 
    		BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bill,null);
            return UPDATE_VIEW;
        }
        uiModel.asMap().clear();
        bill.merge();
        return "redirect:/workbench/bills/" + encodeUrlPathSegment(bill.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, billService.findBill(id),null);
        return UPDATE_VIEW;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        JBill bill = JBill.findJBill(id);
        bill.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/workbench/bills";
    }
    
    void populateEditForm(Model uiModel, JBill bill, String submissionReference) {
        

        List<JSubmissionForApproval> submissionForApprovals = new ArrayList<JSubmissionForApproval>();
        if(submissionReference != null){
        	submissionForApprovals = submissionService.findSubmissionForApprovalsByReferenceEquals(submissionReference);
        }else{
        	submissionForApprovals.add(bill.getSubmission());
        }

        BigDecimal totalWeightAllowed = new BigDecimal("0");
        
        for (JSubmissionForApproval jSubmissionForApproval : submissionForApprovals) {
			for (JCoffeeAnalysis analysis : jSubmissionForApproval.getAnalyzesCoffee()) {
				
//				totalWeightAllowed = totalWeightAllowed.add(analysis.getNetWeightOfProductAccepted());
//				bill.setTourName(analysis.getTour().getName());
//				bill.setFactoryCode(analysis.getFactoryEntry().getCode());
//				bill.setFactoryName(analysis.getFactoryEntry().getName());
//				bill.setExporterName(analysis.getExporterEntry().getName());
			}
		}
        
//        JCompany company = JCompany.findJCompany(Long.valueOf("1"));
//        bill.setAmountWithoutTaxes(company.getCoffeePrice().multiply(totalWeightAllowed));
        bill.setTotalWeightProductAllowed(totalWeightAllowed);
        bill.setTotalWeightProductPushed(new BigDecimal("0"));
        
        uiModel.addAttribute("bill", bill);

        if(!submissionForApprovals.isEmpty()){
        	uiModel.addAttribute("submissions", submissionForApprovals);
        }
    }
    
    void populateEditBillEvaluator(Model uiModel, FilterAnalysisForm filterAnalysisForm, String factoryCode){
    	uiModel.addAttribute("filterAnalysisForm", filterAnalysisForm);
    	uiModel.addAttribute("currentNav", "evaluatebill");
    	
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
    	
    }
    
    void populateEditListFilterForm(Model uiModel, String factoryCode){
    	
    	List<JFactory>  factories = null;
    	
    	if(factoryCode == null){
    		 factories = factoryService.findAllFactories();
    	}else{
    		factories = factoryService.findFactoriesByCodeEquals(factoryCode);
    	}
    	
    	 
    	if(!factories.isEmpty()){
    		uiModel.addAttribute("factories", factories);
    	}
    	
    	uiModel.addAttribute("currentNav", "bills");
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
