package com.school.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUserId(String userId);

}
