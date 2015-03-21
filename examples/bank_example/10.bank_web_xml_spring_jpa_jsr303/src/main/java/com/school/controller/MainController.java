package com.school.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.school.exception.BankException;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String login_success(HttpSession session) throws IOException {
		return "index";
	}

}

