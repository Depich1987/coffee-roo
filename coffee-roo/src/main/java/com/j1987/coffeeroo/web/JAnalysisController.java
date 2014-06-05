package com.j1987.coffeeroo.web;
import com.j1987.coffeeroo.domain.JAnalysis;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/janalyses")
@Controller
@RooWebScaffold(path = "janalyses", formBackingObject = JAnalysis.class)
public class JAnalysisController {
}
