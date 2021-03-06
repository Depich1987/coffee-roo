// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.j1987.coffeeroo.web;

import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JUser;
import com.j1987.coffeeroo.web.JFactoryController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect JFactoryController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String JFactoryController.create(@Valid JFactory JFactory_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, JFactory_);
            return "jfactorys/create";
        }
        uiModel.asMap().clear();
        JFactory_.persist();
        return "redirect:/jfactorys/" + encodeUrlPathSegment(JFactory_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String JFactoryController.createForm(Model uiModel) {
        populateEditForm(uiModel, new JFactory());
        return "jfactorys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String JFactoryController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("jfactory_", JFactory.findJFactory(id));
        uiModel.addAttribute("itemId", id);
        return "jfactorys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String JFactoryController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jfactorys", JFactory.findJFactoryEntries(firstResult, sizeNo));
            float nrOfPages = (float) JFactory.countJFactorys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jfactorys", JFactory.findAllJFactorys());
        }
        return "jfactorys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String JFactoryController.update(@Valid JFactory JFactory_, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, JFactory_);
            return "jfactorys/update";
        }
        uiModel.asMap().clear();
        JFactory_.merge();
        return "redirect:/jfactorys/" + encodeUrlPathSegment(JFactory_.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String JFactoryController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, JFactory.findJFactory(id));
        return "jfactorys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String JFactoryController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        JFactory JFactory_ = JFactory.findJFactory(id);
        JFactory_.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/jfactorys";
    }
    
    void JFactoryController.populateEditForm(Model uiModel, JFactory JFactory_) {
        uiModel.addAttribute("JFactory_", JFactory_);
        uiModel.addAttribute("jbridges", JBridge.findAllJBridges());
        uiModel.addAttribute("jusers", JUser.findAllJUsers());
    }
    
    String JFactoryController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
