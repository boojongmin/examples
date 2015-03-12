package com.school;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.school.command.AccountCommand;
import com.school.command.Command;
import com.school.command.UserCommand;
import com.school.config.RootConfig;
import com.school.login.LoginManager;
import com.school.service.AccountService;
import com.school.service.UserService;

public class Main {
	private static Logger logger = LoggerFactory.getLogger("Main");

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(RootConfig.class);
		ctx.getEnvironment().setActiveProfiles("production");
		ctx.refresh();
        while(true){
          if(LoginManager.loginMap.get("User") == null){
            Command command = ctx.getBean(UserCommand.class);
            command.run(ctx.getBean(UserService.class));
          }else{
            Command command = ctx.getBean(AccountCommand.class);
            command.run(ctx.getBean(AccountService.class));
          }
		}
	}
}
