package com.j1987.coffeeroo.web.ajaxservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.services.dao.BridgeService;
import com.j1987.coffeeroo.services.dao.FactoryService;

@Controller
@RequestMapping(value = "/ajaxrequest")
public class AjaxController {
	
	@Autowired
	private FactoryService factoryService;
	
	@Autowired
	private BridgeService bridgeService;
	
	public AjaxController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/getnewbridgecode", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody  String generateBridgeCode(@RequestParam("factoryId")Long factoryId){
		
		String generatedCode = "";
		
		JFactory factory = factoryService.findFactory(factoryId);
		if(factory != null) {
			
			String baseBridgeCode = factory.getCode();
	        Long number = bridgeService.countBridgesInFactory(factory);
	        number+=1;
	        baseBridgeCode+=String.format("%02d", number);
	        generatedCode = baseBridgeCode;
		}
		return generatedCode;
	}
	
	@RequestMapping(value = "/getfactoryparent", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody  String findFactoryParentByBridge(@RequestParam("bridgeCode")String bridgeCode){

			List<JBridge> bridges = bridgeService.findBridgesByCodeEquals(bridgeCode);
			String factoryName = "N/A";
			if(!bridges.isEmpty()){
				JBridge bridge = bridges.get(0);
				if(bridge.getFactory() != null ) factoryName = bridge.getFactory().getName();

			}
			return factoryName;
		
	}
}