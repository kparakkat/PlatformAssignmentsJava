package com.manageuser.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostConstruct
	public void initialize() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		userService = ctx.getBean("userService", UserService.class);
	}
	
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
		int userId;
		if (user.getId() >0){
			userId = userService.updateUser(user);
		}else{
			userId = userService.addUser(user);
		}
		ModelAndView model = new ModelAndView("account");
		model.addObject("userid", userId);
		return model;
	}
	
	@RequestMapping(value= "/updateaccount/{userid}", method = RequestMethod.GET)
	public ModelAndView updateAccount(@PathVariable("userid") int userid) {
		System.out.println(userid);
		ModelAndView model = new ModelAndView("register");
		User user = userService.getUser(userid);
		System.out.println(user.getName());
		model.addObject("user", user);
		return model;
	}
	
	@RequestMapping(value= {"/login"}, method = RequestMethod.GET)
	public ModelAndView showLogin() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("login", new Login());
		return model;
	}
	
	@RequestMapping(value= {"/loginProcess"}, method = RequestMethod.POST)
	public ModelAndView loginUser(@ModelAttribute("login") Login login) {
		ModelAndView model = null;
		System.out.println("In Login Controller Method");
		System.out.println(login.getUsername());
		User user = userService.validateUser(login);
		if (null != user) {
			model = new ModelAndView("account");
			model.addObject("userid", user.getId());
		} else {
			model = new ModelAndView("login");
			model.addObject("message", "Username or Password in wrong !!");
		}
		return model;
	}
	
}
