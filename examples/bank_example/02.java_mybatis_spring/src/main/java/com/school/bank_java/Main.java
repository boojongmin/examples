package com.school.bank_java;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.school.bank_java.command.AccountCommand;
import com.school.bank_java.command.Command;
import com.school.bank_java.command.LoginCommand;
import com.school.bank_java.dao.AccountDao;
import com.school.bank_java.dao.UserDao;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.mybatis.SqlSessionMaker;
import com.school.bank_java.service.AccountService;
import com.school.bank_java.service.UserService;

public class Main {
	private static Logger logger = LoggerFactory.getLogger("Main");

	public static void main(String[] args) {
		String[] configXml = {
			"classpath:com/school/bank_java/config/application-context.xml", 
			"classpath:com/school/bank_java/config/aop.xml"
		};
		ApplicationContext context = new FileSystemXmlApplicationContext(configXml); 
		logger.debug(">>>test");
			while(true){
//			Scanner scaner =new Scanner(System.in);
			if(LoginManager.loginMap.get("UserVo") == null){
				Command command = context.getBean(LoginCommand.class);
				command.run(context.getBean(UserService.class));
			}else{
				Command command = context.getBean(AccountCommand.class);
				command.run(context.getBean(AccountService.class));
			}
		}
	}
}
