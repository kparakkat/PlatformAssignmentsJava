package com.manageuser.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manageuser.poj.Login;
import com.manageuser.poj.User;
import com.manageuser.service.IUserService;
import com.manageuser.service.UserService;

@Controller
public class UserController {
	
	public IUserService userService;
	
	@RequestMapping(value= {"/welcome**"}, method = RequestMethod.GET )
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "User Management");
		model.addObject("message", "This is a welcome page!");
		model.setViewName("hello");
		return model;
	}
	
	@RequestMapping(value= {"/","/home**"}, method = RequestMethod.GET )
	public ModelAndView homePage() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		userService = ctx.getBean("userService", UserService.class);
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public ModelAndView showRegister() {
		ModelAndView model = new ModelAndView("register");
		model.addObject("user", new User());
		return model;
	}
	
	@RequestMapping(value= {"/registerForm"}, method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return new ModelAndView("account");
	}
	
	@RequestMapping(value= {"/login"}, method = RequestMethod.GET)
	public ModelAndView showLogin() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("login", new Login());
		return model;
	}
	
	@RequestMapping(value= {"/loginForm"}, method = RequestMethod.POST)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) {
		ModelAndView model = null;
		System.out.println("In Login Controller Method");
		System.out.println(login.getUsername());
		User user = userService.validateUser(login);
		if (null != user) {
			model = new ModelAndView("account");
		} else {
			model = new ModelAndView("login");
			model.addObject("message", "Username or Password in wrong !!");
		}
		return model;
	}
	
}
