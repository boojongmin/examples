package com.school.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.userinfo.UserInfo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/login")
	public String login(){
		return "/user/login";
	}
	
	@RequestMapping("/login/{user}")
	public String login(@PathVariable("user") String user, Model m){
		m.addAttribute("user", user);
		return "/user/login";
	}
	
//	@RequestMapping(value="/loginProc", method=RequestMethod.POST)
//	public String loginProc(@RequestParam Map<String, String> param, HttpSession session,
//				ModelAndView mav){
////		String userId = request.getParameter("userId");
////		String password = request.getParameter("password");
//		
//		String myUserId = (String)UserInfo.userInfo.get("userId");
//		String myPassword = (String)UserInfo.userInfo.get("password");
//		
//		if(myUserId.equals(param.get("userId")) && myPassword.equals(param.get("password"))){
////			HttpSession session = request.getSession();
//			session.setAttribute("User", UserInfo.userInfo);
//			return "/user/loginSuccess";
//		}
//		
//		return "redirect:/school/user/login?login=fail";
//	}
	
	
	@RequestMapping("/loginSuccess")
	public String loginSuccess(HttpSession session){
		session.setAttribute("User", UserInfo.userInfo);
		return "/loginSuccess";
	}
	
	

}

class UserVo{
	private String userId;
	private String password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
