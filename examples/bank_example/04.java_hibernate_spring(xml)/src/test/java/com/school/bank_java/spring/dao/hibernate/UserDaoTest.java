package com.school.bank_java.spring.dao.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.school.bank_java.dao.UserDao;
import com.school.bank_java.spring.config.HibernateDataInit;
import com.school.bank_java.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/com/school/bank_java/config/application-*.xml"})
@ActiveProfiles(profiles = {"test" ,  "hibernate"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests  {
	private static final String USERID = "boojongmin";

	final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Autowired
	private UserDao dao;
	@Autowired
	SessionFactory factory;

	@Before
	public void beforeSetup(){
		System.out.println(factory);
		HibernateDataInit.run(factory);
	}

	@Test
	public void test_01_selectUserByUserid() throws Exception{
		UserVo userVo = dao.selectUserVoByUserid(USERID);
		Assert.assertEquals(userVo.getUserId(), USERID);
		factory.getCurrentSession().getTransaction().rollback();
	}

	@Test
	public void test_02_insertUser() throws Exception {
		HibernateDataInit.run(factory);
		UserVo userVo = new UserVo("test", "테스트", new Date());
		int count = dao.insertUser(userVo);
		Assert.assertEquals(1, count);
		Assert.assertTrue(userVo.equals( dao.selectUserVoByUserid(userVo.getUserId())));
	}
}
