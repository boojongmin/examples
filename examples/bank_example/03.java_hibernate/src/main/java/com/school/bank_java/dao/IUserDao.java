package com.school.bank_java.dao;

import com.school.bank_java.vo.UserVo;

public interface IUserDao {

	public abstract UserVo selectUserVoByUserid(String userid);

	public abstract int insertUser(UserVo userVo);

}