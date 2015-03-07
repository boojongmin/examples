package com.school.bank_java.service;

import org.apache.ibatis.session.SqlSession;

import com.school.bank_java.dao.UserDao;
import com.school.bank_java.vo.UserVo;

public class UserService {
	UserDao dao;
	SqlSession session;
	
	public UserService(UserDao dao, SqlSession session){
		this.dao = dao; 
		this.session = session;
	}
	
	public UserVo doLogin(String userid){
		return dao.selectUserVoByUserid(userid);
	}

}
