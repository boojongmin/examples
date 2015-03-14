package com.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.domain.User;
import com.school.repository.UserRepository;

//@Component
@Service
@Transactional(readOnly = true)
public class UserService{
	@Autowired
	UserRepository repository;


	public User doLogin(String userId) {
//		UserRepository bean = ctx.getBean(UserRepository.class);
		return repository.findByUserId(userId);
	}

}
