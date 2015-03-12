package com.school.bank_java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.school.bank_java.command.AccountCommand;
import com.school.bank_java.command.Command;
import com.school.bank_java.command.LoginCommand;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.service.AccountService;
import com.school.bank_java.service.UserService;

public class Main {
	private static Logger logger = LoggerFactory.getLogger("Main");

	public static void main(String[] args) {
//		System.setProperty("spring.profiles.active", "mybatis");
//		String[] configXml = {
//			"classpath:com/school/bank_java/config/application-context.xml",
//			"classpath:com/school/bank_java/config/application-mybatis.xml",
//			"classpath:com/school/bank_java/config/application-hibernate.xml",
//			"classpath:com/school/bank_java/config/aop.xml"
//		};
//		ApplicationContext context = new FileSystemXmlApplicationContext(configXml);
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("mybatis", "test");
		ctx.load("classpath:/com/school/bank_java/config/application-*.xml");
		ctx.refresh();
			while(true){
			if(LoginManager.loginMap.get("UserVo") == null){
				Command command = ctx.getBean(LoginCommand.class);
				command.run(ctx.getBean(UserService.class));
			}else{
				Command command = ctx.getBean(AccountCommand.class);
				command.run(ctx.getBean(AccountService.class));
			}
		}
	}
}
