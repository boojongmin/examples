package com.school.bank_java.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.school.bank_java.vo.UserVo;

public class UserDao{
	@Autowired
	private SqlSessionTemplate session;
	
	private String id_prefix = "com.school.bank_java.mybatis.mapper" + ".";
	
	public UserVo selectUserVoByUserid(String userid) {
		return (UserVo)session.selectOne(id_prefix + "selectUserVoByUserid", userid);
	}

	public int insertUser(UserVo userVo) {
		return session.insert(id_prefix + "insertUser", userVo);
	}
}
