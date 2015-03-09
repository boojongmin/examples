package com.school.bank_java.service;

import java.sql.SQLException;

import com.school.bank_java.dao.UserDao;
import com.school.bank_java.vo.UserVo;

public class UserService {
	UserDao dao;

	public UserService(UserDao dao) { this.dao = dao; }



	public UserVo doLogin(String userid){

		if(true)throw new RuntimeException("test");

		return dao.selectUserVoByUserid(userid);
	}

}
