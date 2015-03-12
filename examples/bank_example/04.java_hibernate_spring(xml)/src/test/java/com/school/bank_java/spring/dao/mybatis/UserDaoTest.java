package com.school.bank_java.spring.dao.mybatis;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.school.bank_java.dao.UserDao;
import com.school.bank_java.spring.config.MybatisDBInit;
import com.school.bank_java.spring.config.HibernateDataInit;
import com.school.bank_java.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/com/school/bank_java/config/application-*.xml"})
@ActiveProfiles(profiles = {"test" ,  "mybatis"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class UserDaoTest {
	private static final String USERID = "boojongmin";


	@Autowired
	ConfigurableApplicationContext ctx;
	@Autowired
	SessionFactory factory;


	final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
//	public static final UserDao DAO = new MybatisUserDao(SqlSessionMaker.getSession(MYBATIS_CONFIG, true));
	@Autowired
	private UserDao dao;

	@BeforeClass
	public static void beforeSetup(){
		MybatisDBInit.run();
	}


	@Test
	public void test_01_selectUserByUserid() throws Exception{
		UserVo userVo = dao.selectUserVoByUserid(USERID);
		Assert.assertEquals(userVo.getUserId(), USERID);
	}

	@Test
	public void test_02_insertUser() throws Exception {
		UserVo userVo = new UserVo("test", "테스트", new Date());
		int count = dao.insertUser(userVo);
		Assert.assertEquals(1, count);
		Assert.assertTrue(userVo.equals( dao.selectUserVoByUserid(userVo.getUserId())));
	}
}
