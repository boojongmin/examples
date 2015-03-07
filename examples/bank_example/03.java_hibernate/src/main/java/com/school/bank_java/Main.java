package com.school.bank_java;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school.bank_java.command.AccountCommand;
import com.school.bank_java.command.Command;
import com.school.bank_java.command.LoginCommand;
import com.school.bank_java.dao.mybatis.AccountDao;
import com.school.bank_java.dao.mybatis.UserDao;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.mybatis.SqlSessionMaker;
import com.school.bank_java.service.AccountService;
import com.school.bank_java.service.LoginService;

public class Main {
	private static Logger logger = LoggerFactory.getLogger("Main");

	public static void main(String[] args) {
		logger.debug(">>>test");
		
		SqlSession session = SqlSessionMaker.getSession(false);
		LoginService loginService = new LoginService(new UserDao(session), session);
		AccountService accountService = new AccountService(new AccountDao(session), session);
		
		while(true){
//			Scanner scaner =new Scanner(System.in);
			if(LoginManager.loginMap.get("UserVo") == null){
				Command command = new LoginCommand();
				command.run(loginService);
			}else{
				Command command = new AccountCommand();
				command.run(accountService);
			}
		}
	}
}
