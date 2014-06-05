// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.web;

import com.j1987.coffeeroo.domain.JBill;
import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JSubmissionForApproval;
import com.j1987.coffeeroo.web.JSubmissionForApprovalController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect JSubmissionForApprovalController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String JSubmissionForApprovalController.create(@Valid JSubmissionForApproval JSubmissionForApproval_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, JSubmissionForApproval_);
            return "jsubmissionforapprovals/create";
        }
        uiModel.asMap().clear();
        JSubmissionForApproval_.persist();
        return "redirect:/jsubmissionforapprovals/" + encodeUrlPathSegment(JSubmissionForApproval_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String JSubmissionForApprovalController.createForm(Model uiModel) {
        populateEditForm(uiModel, new JSubmissionForApproval());
        return "jsubmissionforapprovals/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String JSubmissionForApprovalController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("jsubmissionforapproval_", JSubmissionForApproval.findJSubmissionForApproval(id));
        uiModel.addAttribute("itemId", id);
        return "jsubmissionforapprovals/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String JSubmissionForApprovalController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jsubmissionforapprovals", JSubmissionForApproval.findJSubmissionForApprovalEntries(firstResult, sizeNo));
            float nrOfPages = (float) JSubmissionForApproval.countJSubmissionForApprovals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jsubmissionforapprovals", JSubmissionForApproval.findAllJSubmissionForApprovals());
        }
        addDateTimeFormatPatterns(uiModel);
        return "jsubmissionforapprovals/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String JSubmissionForApprovalController.update(@Valid JSubmissionForApproval JSubmissionForApproval_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, JSubmissionForApproval_);
            return "jsubmissionforapprovals/update";
        }
        uiModel.asMap().clear();
        JSubmissionForApproval_.merge();
        return "redirect:/jsubmissionforapprovals/" + encodeUrlPathSegment(JSubmissionForApproval_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String JSubmissionForApprovalController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, JSubmissionForApproval.findJSubmissionForApproval(id));
        return "jsubmissionforapprovals/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String JSubmissionForApprovalController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        JSubmissionForApproval JSubmissionForApproval_ = JSubmissionForApproval.findJSubmissionForApproval(id);
        JSubmissionForApproval_.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/jsubmissionforapprovals";
    }
    
    void JSubmissionForApprovalController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("JSubmissionForApproval__creationdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void JSubmissionForApprovalController.populateEditForm(Model uiModel, JSubmissionForApproval JSubmissionForApproval_) {
        uiModel.addAttribute("JSubmissionForApproval_", JSubmissionForApproval_);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("jbills", JBill.findAllJBills());
        uiModel.addAttribute("jcoffeeanalyses", JCoffeeAnalysis.findAllJCoffeeAnalyses());
    }
    
    String JSubmissionForApprovalController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
