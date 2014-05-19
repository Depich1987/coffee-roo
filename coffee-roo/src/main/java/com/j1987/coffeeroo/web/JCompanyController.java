package com.j1987.coffeeroo.web;
import com.j1987.coffeeroo.domain.JCompany;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jcompanys")
@Controller
@RooWebScaffold(path = "jcompanys", formBackingObject = JCompany.class)
public class JCompanyController {
}
