package com.school.repository;

import org.springframework.data.repository.CrudRepository;

import com.school.vo.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUserId(String userId);

}
