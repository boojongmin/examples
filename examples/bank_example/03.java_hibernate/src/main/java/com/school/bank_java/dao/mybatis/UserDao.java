package com.school.bank_java.dao.mybatis;

import org.apache.ibatis.session.SqlSession;

import com.school.bank_java.dao.IUserDao;
import com.school.bank_java.vo.UserVo;

public class UserDao implements IUserDao{
	private SqlSession session;
	
	private String id_prefix = "com.school.bank_java.mybatis.mapper" + ".";
	
	public UserDao(SqlSession session){
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IUserDao#selectUserVoByUserid(java.lang.String)
	 */
	@Override
	public UserVo selectUserVoByUserid(String userid) {
		return (UserVo)session.selectOne(id_prefix + "selectUserVoByUserid", userid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IUserDao#insertUser(com.school.bank_java.vo.UserVo)
	 */
	@Override
	public int insertUser(UserVo userVo) {
		return session.insert(id_prefix + "insertUser", userVo);
	}
}
