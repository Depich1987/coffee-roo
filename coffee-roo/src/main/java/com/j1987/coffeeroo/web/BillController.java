package com.j1987.coffeeroo.web;
import com.j1987.coffeeroo.domain.JBill;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jbills")
@Controller
@RooWebScaffold(path = "jbills", formBackingObject = JBill.class)
public class BillController {
}
