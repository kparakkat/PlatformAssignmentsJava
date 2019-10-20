package com.manageuser.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;

import com.manageuser.poj.Login;
import com.manageuser.poj.User;
import com.manageuser.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Value("${error.message}")
	private String errorMessage;
	
	@Value("${error.captchaerrmsg}")
	private String error;

	@RequestMapping(value= "/index")
	public String index() {
		// return "Hello world!!!";
		return "index";
	}
	
	@RequestMapping(value= {"/","/home**"}, method = RequestMethod.GET )
	public String homePage(Model model) {
		// logger.info("Hitting the homepage");
		return "home";
	}
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String showRegister(Model model) {
		// logger.info("Loading the user register page");
		User user = new User();
		model.addAttribute("errorMessage", "");
		model.addAttribute("error", "");
		model.addAttribute("user", user);
		return "register";
	}
	
	@RequestMapping(value= {"/registerForm"}, method = RequestMethod.POST)
	public String manageUser(Model model, @Valid @ModelAttribute("user") User user, HttpSession session, HttpServletRequest request) {
		System.out.println("In Register Form");
		int userId = 0;
		String captcha = user.getCaptcha();
		System.out.println("Entered Catptcha Is : " + captcha);
		// String verifyCaptcha = request.getSession(false).getAttribute("captcha_security").toString();
		String verifyCaptcha = session.getAttribute("captcha_security").toString();
		// String verifyCaptcha = (String) request.getAttribute("captcha_security");
		System.out.println("Verify Catptcha Is : " + verifyCaptcha);
		if (!captcha.equals(verifyCaptcha)) {
			model.addAttribute("error", error);
			return "register";
		} 
		System.out.println("Catptcha Is Success");
		
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
			return "account";
			// logger.info("User Registration form have validation errors");
		}
		// logger.info("User registration is in progress");
		model.addAttribute("error", error);
		model.addAttribute("errorMessage", errorMessage);
		return "register";
	}
	
	@RequestMapping(value= {"/login"}, method = RequestMethod.GET)
	public String showLogin(Model model) {
		// logger.info("Loading login page");
		Login login = new Login();
		model.addAttribute("errorMessage", "");
		model.addAttribute("error", "");
		model.addAttribute("login", login);
		return "login";
	}
	
	@RequestMapping(value= {"/loginProcess"}, method = RequestMethod.POST)
	public String loginUser(Model model, @Valid @ModelAttribute("login") Login login, HttpSession session, HttpServletRequest request
			) {
		// @CookieValue(value = "captchaSecurity", defaultValue = "Atta") String captchaSecurity
		// ModelAndView model = null;
		// logger.info("Login in progress");
			
		System.out.println("In Login Form");
		String captcha = login.getCaptcha();
		System.out.println("Entered Catptcha Is : " + captcha);
		// String verifyCaptcha = session.getAttribute("captcha_security").toString();
		// String verifyCaptcha = null;
		String verifyCaptcha = request.getSession(false).getAttribute("captcha_security").toString();
		
		// if (request.getAttribute("captcha_security") != null )
		//   verifyCaptcha = request.getAttribute("captcha_security").toString();
		
		// String verifyCaptcha = request.getAttribute("captcha_security").toString();
		// String verifyCaptcha = model.("captcha_security");
		System.out.println("Verify Catptcha Is : " + verifyCaptcha);
		if (!captcha.equals(verifyCaptcha)) {
			model.addAttribute("error", error);
			return "login";
		} 
		System.out.println("Catptcha Is Success");
		
		String uname = login.getUsername();
		String pwd = login.getPassword();
		
		if (uname!=null && uname.length() > 0 && pwd != null && pwd.length() > 0 ) {
			User user = userService.validateUser(login);
			if (null != user) {
				// logger.info("Login success");
				model.addAttribute("userid", user.getId());
				return "account";
			} else {
				// logger.error("Login failed !");
				model.addAttribute("errorMessage", "Username or Password in wrong !!");
				return "login";
			}
		}
		else
		{
			model.addAttribute("errorMessage", "Username or Password should not be blank");
			return "login";
		}
	}
}
