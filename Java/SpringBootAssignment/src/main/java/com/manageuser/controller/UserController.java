package com.manageuser.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manageuser.poj.Login;
import com.manageuser.poj.User;
import com.manageuser.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Value("${error.message}")
	private String errorMessage;
	
	@Value("${error.captchaerrmsg}")
	private String error;

	@RequestMapping(value= "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value= {"/","/home**"}, method = RequestMethod.GET )
	public String homePage(Model model) {
		logger.info("Hitting the homepage");
		return "home";
	}
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String showRegister(Model model) {
		User user = new User();
		model.addAttribute("errorMessage", "");
		model.addAttribute("error", "");
		model.addAttribute("user", user);
		logger.info("Loading the user register page");
		return "register";
	}
	
	@RequestMapping(value= {"/registerForm"}, method = RequestMethod.POST)
	public String manageUser(Model model, @Valid @ModelAttribute("user") User user, HttpSession session, HttpServletRequest request) {
		int userId = 0;
		String captcha = user.getCaptcha();
		String verifyCaptcha = session.getAttribute("captcha_security").toString();
		user.setCaptcha("");
		if (!captcha.equals(verifyCaptcha)) {
			model.addAttribute("error", error);
			logger.info("Captcha Verification Failed");
			return "register";
		} 
		
		String name = user.getName();
		String email = user.getEmail();
		String uname = user.getUsername();
		String pwd = user.getPassword();
		
		if (name != null && name.length() > 0 && email != null && email.length() > 0 
				&& uname!=null && uname.length() > 0 && pwd != null && pwd.length() > 0 ) {
			
			if (user.getId() >0){
					userService.updateUser(user);
					userId = user.getId();
			}
			else{
					userService.addUser(user);
					userId = user.getId();
			}
			model.addAttribute("userid", userId);
			logger.info("User registration or update form is done");
			return "account";
		}
		model.addAttribute("error", error);
		model.addAttribute("errorMessage", errorMessage);
		logger.error("User Registration or update form form have validation errors");
		return "register";
	}
	
	@RequestMapping(value= {"/login"}, method = RequestMethod.GET)
	public String showLogin(Model model) {
		Login login = new Login();
		model.addAttribute("errorMessage", "");
		model.addAttribute("error", "");
		model.addAttribute("login", login);
		logger.info("Loading login page");
		return "login";
	}
	
	@RequestMapping(value= {"/loginProcess"}, method = RequestMethod.POST)
	public String loginUser(Model model, @Valid @ModelAttribute("login") Login login, 
			HttpSession session, HttpServletRequest request ) 
	{
		String captcha = login.getCaptcha();
		String verifyCaptcha = session.getAttribute("captcha_security").toString();
		login.setCaptcha("");
		if (!captcha.equals(verifyCaptcha)) {
			model.addAttribute("error", error);
			return "login";
		} 
		String uname = login.getUsername();
		String pwd = login.getPassword();
		
		if (uname!=null && uname.length() > 0 && pwd != null && pwd.length() > 0 ) {
			User user = userService.validateUser(login);
			if (null != user) {
				model.addAttribute("userid", user.getId());
				logger.info("Login success");
				return "account";
			} else {
				logger.error("Login failed !");
				model.addAttribute("errorMessage", "Username or Password in wrong !!");
				return "login";
			}
		}
		else
		{
			model.addAttribute("errorMessage", "Username or Password should not be blank");
			logger.error("Login failed validation errors!");
			return "login";
		}
	}
	
	@RequestMapping(value= "/updateaccount/{userid}", method = RequestMethod.GET)
	public String updateAccount(Model model,@PathVariable("userid") int userid) {
		User user = userService.getUser(userid);
		user.setCaptcha("");
		model.addAttribute("errorMessage", "");
		model.addAttribute("error", "");
		model.addAttribute("user", user);
		logger.info("Loading account update page");
		return "register";
	}
	
	@RequestMapping(value= "/getaccount/{userid}", method = RequestMethod.GET)
	public String getaccount(Model model, @PathVariable("userid") int userid) {
		model.addAttribute("userid", userid);
		logger.info("Loading account page");
		return "account";
	}
	
	
	@RequestMapping(value = "/admin**/{userid}", method = RequestMethod.GET)
	public String adminPage(Model model, @PathVariable("userid") int userid) {
		model.addAttribute("userid", userid);
		logger.info("Loading admin page");
		return "admin";
	}
	
	@RequestMapping(value = {"/tutorials/{userid}"}, method = RequestMethod.GET)
	public String tutorialsPage(Model model, @PathVariable("userid") int userid) {
		model.addAttribute("userid", userid);
		logger.info("Loading tutorials page");
		return "tutorials";
	}
}
