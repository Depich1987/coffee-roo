// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.web;

import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import com.j1987.coffeeroo.domain.JDealer;
import com.j1987.coffeeroo.domain.JExporter;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JLocalization;
import com.j1987.coffeeroo.domain.JSubmissionForApproval;
import com.j1987.coffeeroo.domain.JSupplier;
import com.j1987.coffeeroo.domain.JTour;
import com.j1987.coffeeroo.web.JCoffeeAnalysisController;
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

privileged aspect JCoffeeAnalysisController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String JCoffeeAnalysisController.create(@Valid JCoffeeAnalysis JCoffeeAnalysis_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, JCoffeeAnalysis_);
            return "jcoffeeanalyses/create";
        }
        uiModel.asMap().clear();
        JCoffeeAnalysis_.persist();
        return "redirect:/jcoffeeanalyses/" + encodeUrlPathSegment(JCoffeeAnalysis_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String JCoffeeAnalysisController.createForm(Model uiModel) {
        populateEditForm(uiModel, new JCoffeeAnalysis());
        return "jcoffeeanalyses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String JCoffeeAnalysisController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("jcoffeeanalysis_", JCoffeeAnalysis.findJCoffeeAnalysis(id));
        uiModel.addAttribute("itemId", id);
        return "jcoffeeanalyses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String JCoffeeAnalysisController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jcoffeeanalyses", JCoffeeAnalysis.findJCoffeeAnalysisEntries(firstResult, sizeNo));
            float nrOfPages = (float) JCoffeeAnalysis.countJCoffeeAnalyses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jcoffeeanalyses", JCoffeeAnalysis.findAllJCoffeeAnalyses());
        }
        addDateTimeFormatPatterns(uiModel);
        return "jcoffeeanalyses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String JCoffeeAnalysisController.update(@Valid JCoffeeAnalysis JCoffeeAnalysis_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, JCoffeeAnalysis_);
            return "jcoffeeanalyses/update";
        }
        uiModel.asMap().clear();
        JCoffeeAnalysis_.merge();
        return "redirect:/jcoffeeanalyses/" + encodeUrlPathSegment(JCoffeeAnalysis_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String JCoffeeAnalysisController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, JCoffeeAnalysis.findJCoffeeAnalysis(id));
        return "jcoffeeanalyses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String JCoffeeAnalysisController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        JCoffeeAnalysis JCoffeeAnalysis_ = JCoffeeAnalysis.findJCoffeeAnalysis(id);
        JCoffeeAnalysis_.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/jcoffeeanalyses";
    }
    
    void JCoffeeAnalysisController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("JCoffeeAnalysis__dateofanalysis_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JCoffeeAnalysis__starttime_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JCoffeeAnalysis__endtime_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JCoffeeAnalysis__creationdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JCoffeeAnalysis__modificationdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void JCoffeeAnalysisController.populateEditForm(Model uiModel, JCoffeeAnalysis JCoffeeAnalysis_) {
        uiModel.addAttribute("JCoffeeAnalysis_", JCoffeeAnalysis_);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("jdealers", JDealer.findAllJDealers());
        uiModel.addAttribute("jexporters", JExporter.findAllJExporters());
        uiModel.addAttribute("jfactorys", JFactory.findAllJFactorys());
        uiModel.addAttribute("jlocalizations", JLocalization.findAllJLocalizations());
        uiModel.addAttribute("jsubmissionforapprovals", JSubmissionForApproval.findAllJSubmissionForApprovals());
        uiModel.addAttribute("jsuppliers", JSupplier.findAllJSuppliers());
        uiModel.addAttribute("jtours", JTour.findAllJTours());
    }
    
    String JCoffeeAnalysisController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
