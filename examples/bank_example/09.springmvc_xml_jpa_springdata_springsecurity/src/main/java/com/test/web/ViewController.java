package com.test.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ViewController implements Controller {

      @Override
      public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	  ModelAndView modelAndView = new ModelAndView("/WEB-INF/views/hello.jsp");

          return modelAndView;
      }
}
