package com.wind.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/MAIN")
public class MainOwnController {

	  @RequestMapping(method = RequestMethod.GET)
	  public String viewApplication() {
	    return "index";
	  }
	  
	  @RequestMapping(value="showEntity",method = RequestMethod.GET)
	  public String showEntity() {
	    return "show_entity";
	  }
	  
	  @RequestMapping(value="modifyEntity",method = RequestMethod.GET)
	  public String modifyEntity() {
	    return "modify_entity";
	  }
}
