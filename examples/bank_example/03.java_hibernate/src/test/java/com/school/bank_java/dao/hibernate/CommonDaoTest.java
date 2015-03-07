package com.school.bank_java.dao.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school.bank_java.hibernate.CommonDao;
import com.school.bank_java.vo.UserVo;

public class CommonDaoTest {
	private static final Logger commonLogger = LoggerFactory.getLogger(CommonDaoTest.class);

	public SessionFactory factory = HibernateTestUtil.getSessionFactory();



}