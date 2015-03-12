package com.school.service;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.school.domain.User;
import com.school.repository.UserRepository;

@Component
@Transactional(readOnly=true)
public class UserService {
	@Inject
	ApplicationContext ctx;
	@Inject
	UserRepository repository;

	public User doLogin(String userId){
		UserRepository bean = ctx.getBean(UserRepository.class);
		return repository.findByUserId(userId);
	}

}
