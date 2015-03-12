package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.school.repository.UserRepository;
import com.school.vo.User;

public class UserService {

	@Autowired
	ApplicationContext ctx;
	UserRepository repository;
	public UserService(){}
	@Autowired
	public UserService(UserRepository repository) { this.repository = repository; }


	public User doLogin(String userId){
		UserRepository bean = ctx.getBean(UserRepository.class);
		return repository.findByUserId(userId);
	}

}
