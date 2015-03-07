package com.school.bank_java.dao.mybatis;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school.bank_java.dao.IUserDao;
import com.school.bank_java.dao.mybatis.UserDao;
import com.school.bank_java.mybatis.SqlSessionMaker;
import com.school.bank_java.vo.UserVo;

public class UserDaoTest extends CommonDaoTest {
	final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	public static final IUserDao DAO = new UserDao(SqlSessionMaker.getSession(MYBATIS_CONFIG, true));

	private static final String USERID = "boojongmin";

	@Test
	public void test_01_selectUserByUserid() throws Exception{
		UserVo userVo = DAO.selectUserVoByUserid(USERID);
		Assert.assertEquals(userVo.getUserId(), USERID);
	}

	@Test
	public void test_02_insertUser() throws Exception {
		UserVo userVo = new UserVo("test", "테스트", new Date());
		int count = DAO.insertUser(userVo);
		Assert.assertEquals(1, count);
		Assert.assertTrue(userVo.equals( DAO.selectUserVoByUserid(userVo.getUserId())));
	}
}
