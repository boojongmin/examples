package com.school.bank_java.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.school.bank_java.dao.UserDao;
import com.school.bank_java.vo.UserVo;

public class MybatisUserDao implements UserDao{
	private SqlSession session;
	private String id_prefix = "com.school.bank_java.mybatis.mapper" + ".";

	public MybatisUserDao() { }
	public MybatisUserDao(SqlSession session) {
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.UserDao#selectUserVoByUserid(java.lang.String)
	 */
	@Override
	public UserVo selectUserVoByUserid(String userid) {
		return (UserVo)session.selectOne(id_prefix + "selectUserVoByUserid", userid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.UserDao#insertUser(com.school.bank_java.vo.UserVo)
	 */
	@Override
	public int insertUser(UserVo userVo) {
		return session.insert(id_prefix + "insertUser", userVo);
	}
}
