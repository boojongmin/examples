package com.school.bank_java.spring.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.school.bank_java.dao.UserDao;
import com.school.bank_java.spring.config.DBInit;
import com.school.bank_java.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/school/bank_java/spring/config/test-application-context.xml"})
public class UserDaoTest {
	private static final String USERID = "boojongmin";

	final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
//	public static final UserDao DAO = new MybatisUserDao(SqlSessionMaker.getSession(MYBATIS_CONFIG, true));
	@Autowired
	private UserDao dao;

	@Before
	public void beforeSetup(){
		DBInit.run();
	}


	@Test
	public void test_01_selectUserByUserid() throws Exception{
		UserVo userVo = dao.selectUserVoByUserid(USERID);
		Assert.assertEquals(userVo.getUserId(), USERID);
	}

	@Test
	public void test_02_insertUser() throws Exception {
		UserVo userVo = new UserVo("test", "테스트");
		int count = dao.insertUser(userVo);
		Assert.assertEquals(1, count);
		Assert.assertTrue(userVo.equals( dao.selectUserVoByUserid(userVo.getUserId())));
	}
}
