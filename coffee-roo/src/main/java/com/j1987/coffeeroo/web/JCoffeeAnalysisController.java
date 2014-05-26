package com.j1987.coffeeroo.web;
import com.j1987.coffeeroo.domain.JCoffeeAnalysis;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.gvnix.web.report.roo.addon.GvNIXReports;

@RequestMapping("/jcoffeeanalyses")
@Controller
@RooWebScaffold(path = "jcoffeeanalyses", formBackingObject = JCoffeeAnalysis.class)
@GvNIXReports({ "analysiscoffeereport|pdf,xls" })
public class JCoffeeAnalysisController {
}
