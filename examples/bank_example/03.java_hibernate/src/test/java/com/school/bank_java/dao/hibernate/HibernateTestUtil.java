package com.school.bank_java.dao.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.school.bank_java.code.AccountDetailType;
import com.school.bank_java.hibernate.CommonDao;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;
import com.school.bank_java.vo.UserVo;

/**
 * @author Deepak Kumar * Web: http://www.roseindia.net
 *  Update by arahansa@naver.com
 */
public class HibernateTestUtil {
	private static SessionFactory sessionFactory;
	private static String configFile = "com/school/bank_java/dao/hibernate/hibernate_test.cfg.xml";

//	static{
//		try {
//			Configuration cfg = new Configuration().configure(configFile);
//			StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
//			sb.applySettings(cfg.getProperties());
//			StandardServiceRegistry standardServiceRegistry = sb.build();
//			sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);
//
//
//			//test dat 초기화
//			CommonDao<UserVo> userDao = new CommonDao(sessionFactory, UserVo.class);
//            UserVo userVo = new UserVo("boojongmin", "부종민", new Date());
//            userDao.insert(userVo);
//
//			CommonDao<AccountVo> accountDao = new CommonDao(sessionFactory, AccountVo.class);
//			AccountVo accountVo1 = new AccountVo(userVo, "0000100001", 1000);
////			AccountVo accountVo2 = new AccountVo(userVo, "0000100002", 0);
//			accountDao.insert(accountVo1);
////			accountDao.insert(accountVo2);
//
//
//			CommonDao<AccountDetailVo> accountDetailDao = new CommonDao(sessionFactory, AccountDetailVo.class);
//			AccountDetailVo accountDetailVo1 = new AccountDetailVo(accountVo1, AccountDetailType.SAVE, 10000);
//			AccountDetailVo accountDetailVo2 = new AccountDetailVo(accountVo1, AccountDetailType.WITHDRAW, -9000);
//			accountDetailDao.insert(accountDetailVo1);
//			accountDetailDao.insert(accountDetailVo2);
//
//
//		} catch (Throwable th) {
//			System.err.println("Enitial SessionFactory creation failed" + th);
//			throw new ExceptionInInitializerError(th);
//		}
//	}


	public static SessionFactory getSessionFactory() {
//		if(sessionFactory!=null){sessionFactory.close();sessionFactory = null;}
		try {
			Configuration cfg = new Configuration().configure(configFile);
			StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
			sb.applySettings(cfg.getProperties());
			StandardServiceRegistry standardServiceRegistry = sb.build();
			sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);


			//test dat 초기화
			CommonDao<UserVo> userDao = new CommonDao(sessionFactory, UserVo.class);
            UserVo userVo = new UserVo("boojongmin", "부종민", new Date());
            userDao.insert(userVo);

			CommonDao<AccountVo> accountDao = new CommonDao(sessionFactory, AccountVo.class);
			AccountVo accountVo1 = new AccountVo(userVo, "0000100001", 1000);
//			AccountVo accountVo2 = new AccountVo(userVo, "0000100002", 0);
			accountDao.insert(accountVo1);
//			accountDao.insert(accountVo2);


			CommonDao<AccountDetailVo> accountDetailDao = new CommonDao(sessionFactory, AccountDetailVo.class);
			AccountDetailVo accountDetailVo1 = new AccountDetailVo(accountVo1, AccountDetailType.SAVE, 10000);
			AccountDetailVo accountDetailVo2 = new AccountDetailVo(accountVo1, AccountDetailType.WITHDRAW, -9000);
			accountDetailDao.insert(accountDetailVo1);
			accountDetailDao.insert(accountDetailVo2);


		} catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}


		return sessionFactory;
	}

	public void shutdown() {
		sessionFactory.close();
	}
}
