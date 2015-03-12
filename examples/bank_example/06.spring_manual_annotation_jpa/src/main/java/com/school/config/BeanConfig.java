package com.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.school.command.AccountCommand;
import com.school.command.UserCommand;
import com.school.repository.AccountDetailRepository;
import com.school.repository.AccountRepository;
import com.school.repository.UserRepository;
import com.school.service.AccountService;
import com.school.service.UserService;

@Configuration
public class BeanConfig {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountDetailRepository accountDetailRepository;



	@Bean
	UserService userService(){
		return new UserService(userRepository );
	}

    @Bean
	AccountService accountSerivce(){
		return new AccountService(userRepository, accountRepository, accountDetailRepository);
	}

 	@Bean
	UserCommand userCommand(){
		return new UserCommand();
	}

 	@Bean
	AccountCommand accountCommand(){
		return new AccountCommand();
	}
}
