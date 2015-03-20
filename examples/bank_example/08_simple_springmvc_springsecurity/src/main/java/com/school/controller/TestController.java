package com.school.controller;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
//@Service(@Component + service)
//@Repository(@Component + dao)
//@Component
public class TestController{
	
	@RequestMapping("/test01")
	public String test01(){
		return "test";
	}

	
}
