package com.j1987.coffeeroo.web;
import com.j1987.coffeeroo.domain.JFirm;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jfirms")
@Controller
@RooWebScaffold(path = "jfirms", formBackingObject = JFirm.class)
public class JFirmController {
}
