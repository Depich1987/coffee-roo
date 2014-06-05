package com.j1987.coffeeroo.web;
import com.j1987.coffeeroo.domain.JBridge;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jbridges")
@Controller
@RooWebScaffold(path = "jbridges", formBackingObject = JBridge.class)
public class JBridgeController {
}
