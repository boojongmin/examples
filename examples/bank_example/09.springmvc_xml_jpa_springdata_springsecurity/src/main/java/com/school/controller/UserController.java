package com.school.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.school.security.CustomUserDetails;
import com.school.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;


	private static final Logger logger = LoggerFactory .getLogger(UserController.class);

//	@RequestMapping(value = "login", method = RequestMethod.GET)
//	public void login(HttpSession session) {
//		logger.info("Welcome login! {}", session.getId());
//	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		logger.info("Welcome login! {}", session.getId());
		return "login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "loginSuccess", method = RequestMethod.GET)
	public String login_success(HttpSession session) throws IOException {
//		WebAuthenticationDetails userDetails = (WebAuthenticationDetails) SecurityContextHolder .getContext().getAuthentication().getDetails();
		User user = (User)SecurityContextHolder .getContext().getAuthentication().getPrincipal();
		session.setAttribute("User", service.doLogin(user.getUsername()));
		return "loginSuccess";
	}

	@RequestMapping(value = "page1", method = RequestMethod.GET)
	public void page1() {
	}

	@RequestMapping(value = "loginDuplicate", method = RequestMethod.GET)
	public String login_duplicate() {
		return "loginDuplicate";
	}

}
