package com.manageuser.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	private Logger logger = Logger.getLogger(UserController.class);
	
	@PostConstruct
	public void initialize() {
		logger.info("Intializing the service");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		userService = ctx.getBean("userService", UserService.class);
		logger.info("Intialized");
	}
	
	@RequestMapping(value= {"/","/home**"}, method = RequestMethod.GET )
	public ModelAndView homePage() {
		logger.info("Hitting the homepage");
		ModelAndView model = new ModelAndView();
		model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public ModelAndView showRegister() {
		logger.info("Loading the user register page");
		ModelAndView model = new ModelAndView("register");
		model.addObject("user", new User());
		return model;
	}
	
	@RequestMapping(value= {"/registerForm"}, method = RequestMethod.POST)
	public ModelAndView manageUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpSession session,  HttpServletRequest request) {
		
		int userId = 0;
		ModelAndView model = null;
		
		String captcha = session.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		if (!captcha.equals(verifyCaptcha)) {
			model = new ModelAndView("register");
			model.addObject("user", user);
			model.addObject("error", "Captcha Invalid");
			return model;
		}
		
		if (bindingResult.hasErrors()) {
			logger.info("User Registration form have validation errors");
			model = new ModelAndView("register");
			model.addObject("user", user);
			return model;
		}
		
		logger.info("User registration is in progress");
		
		if (user.getId() >0){
			int rowCount = userService.updateUser(user);
			if (rowCount > 0 )
			{
				logger.info("Update is Succesful !");
				userId = user.getId();
			}
			else
			{
				logger.error("Update is Failed !");
			}
		}else{
			userId = userService.addUser(user);
		}
		logger.info("New user added/updated");
		model = new ModelAndView("account");
		model.addObject("userid", userId);
		return model;
	}
	
	@RequestMapping(value= "/getaccount/{userid}", method = RequestMethod.GET)
	public ModelAndView getaccount(@PathVariable("userid") int userid) {
		logger.info("Loading account page");
		ModelAndView model = new ModelAndView("account");
		model.addObject("userid", userid);
		return model;
	}
	
	@RequestMapping(value= "/updateaccount/{userid}", method = RequestMethod.GET)
	public ModelAndView updateAccount(@PathVariable("userid") int userid) {
		logger.info("Loading account update page");
		ModelAndView model = new ModelAndView("register");
		User user = userService.getUser(userid);
		user.setCaptcha("");
		model.addObject("user", user);
		return model;
	}
	
	@RequestMapping(value= {"/login"}, method = RequestMethod.GET)
	public ModelAndView showLogin() {
		logger.info("Loading login page");
		ModelAndView model = new ModelAndView("login");
		model.addObject("login", new Login());
		return model;
	}
	
	@RequestMapping(value= {"/loginProcess"}, method = RequestMethod.POST)
	public ModelAndView loginUser(@ModelAttribute("login") Login login, HttpSession session,  HttpServletRequest request) {
		ModelAndView model = null;
		logger.info("Login in progress");
				
		String captcha = session.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		if (!captcha.equals(verifyCaptcha)) {
			model = new ModelAndView("login");
			model.addObject("login", login);
			model.addObject("error", "Captcha Invalid");
			return model;
		}
		
		User user = userService.validateUser(login);
		if (null != user) {
			logger.info("Login success");
			model = new ModelAndView("account");
			model.addObject("userid", user.getId());
		} else {
			logger.error("Login failed !");
			model = new ModelAndView("login");
			model.addObject("login", login);
			model.addObject("message", "Username or Password in wrong !!");
		}
		return model;
	}
	
	@RequestMapping(value = "/admin**/{userid}", method = RequestMethod.GET)
	public ModelAndView adminPage(@PathVariable("userid") int userid) {
		logger.info("Loading admin page");
		ModelAndView model = new ModelAndView();
		model.addObject("userid", userid);
		model.setViewName("admin");
		return model;
	}
	
	@RequestMapping(value = {"/tutorials/{userid}"}, method = RequestMethod.GET)
	public ModelAndView tutorialsPage(@PathVariable("userid") int userid) {
		logger.info("Loading tutorials page");
		ModelAndView model = new ModelAndView();
		model.addObject("userid", userid);
		model.setViewName("tutorials");
		return model;
	}
	
}
