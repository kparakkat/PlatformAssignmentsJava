package com.manageuser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@RequestMapping(value= {"/","/welcome**"}, method = RequestMethod.GET )
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "User Management");
		model.addObject("message", "This is a welcome page!");
		model.setViewName("hello");
		return model;
	}
}
