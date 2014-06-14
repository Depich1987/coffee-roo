// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.web;

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JBill;
import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JExporter;
import com.j1987.coffeeroo.domain.JFirm;
import com.j1987.coffeeroo.domain.JLocalization;
import com.j1987.coffeeroo.domain.JSupplier;
import com.j1987.coffeeroo.domain.JTour;
import com.j1987.coffeeroo.web.JAnalysisController;
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

privileged aspect JAnalysisController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String JAnalysisController.create(@Valid JAnalysis JAnalysis_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, JAnalysis_);
            return "janalyses/create";
        }
        uiModel.asMap().clear();
        JAnalysis_.persist();
        return "redirect:/janalyses/" + encodeUrlPathSegment(JAnalysis_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String JAnalysisController.createForm(Model uiModel) {
        populateEditForm(uiModel, new JAnalysis());
        return "janalyses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String JAnalysisController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("janalysis_", JAnalysis.findJAnalysis(id));
        uiModel.addAttribute("itemId", id);
        return "janalyses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String JAnalysisController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("janalyses", JAnalysis.findJAnalysisEntries(firstResult, sizeNo));
            float nrOfPages = (float) JAnalysis.countJAnalyses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("janalyses", JAnalysis.findAllJAnalyses());
        }
        addDateTimeFormatPatterns(uiModel);
        return "janalyses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String JAnalysisController.update(@Valid JAnalysis JAnalysis_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, JAnalysis_);
            return "janalyses/update";
        }
        uiModel.asMap().clear();
        JAnalysis_.merge();
        return "redirect:/janalyses/" + encodeUrlPathSegment(JAnalysis_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String JAnalysisController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, JAnalysis.findJAnalysis(id));
        return "janalyses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String JAnalysisController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        JAnalysis JAnalysis_ = JAnalysis.findJAnalysis(id);
        JAnalysis_.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/janalyses";
    }
    
    void JAnalysisController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("JAnalysis__dateofanalysis_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JAnalysis__starttime_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JAnalysis__endtime_date_format", DateTimeFormat.patternForStyle("MS", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JAnalysis__creationdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("JAnalysis__modificationdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void JAnalysisController.populateEditForm(Model uiModel, JAnalysis JAnalysis_) {
        uiModel.addAttribute("JAnalysis_", JAnalysis_);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("jbills", JBill.findAllJBills());
        uiModel.addAttribute("jbridges", JBridge.findAllJBridges());
        uiModel.addAttribute("jexporters", JExporter.findAllJExporters());
        uiModel.addAttribute("jfirms", JFirm.findAllJFirms());
        uiModel.addAttribute("jlocalizations", JLocalization.findAllJLocalizations());
        uiModel.addAttribute("jsuppliers", JSupplier.findAllJSuppliers());
        uiModel.addAttribute("jtours", JTour.findAllJTours());
    }
    
    String JAnalysisController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
