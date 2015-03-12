package com.school.bank_java.spring;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.school.bank_java.code.AccountDetailType;
import com.school.bank_java.spring.config.HibernateDataInit;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;
import com.school.bank_java.vo.UserVo;

public class Main {
	public static void main(String[] args){
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("hibernate", "test");
		ctx.load("classpath*:/com/school/bank_java/config/application-*.xml");
		ctx.refresh();
		SessionFactory f =  ctx.getBean(LocalSessionFactoryBean.class).getObject();

		Session session = f.openSession();
		session.beginTransaction();
		UserVo userVo = new UserVo("boojongmin", "부종민", new Date());

		AccountVo accountVo1 = new AccountVo(userVo, "0000100001", 1000, new Date());
		userVo.getAccountList().add(accountVo1);

		AccountDetailVo accountDetailVo1 = new AccountDetailVo(accountVo1, AccountDetailType.SAVE, 10000);
		AccountDetailVo accountDetailVo2 = new AccountDetailVo(accountVo1, AccountDetailType.TRANSFER, 9000);
		accountVo1.getAccountDetailList().add(accountDetailVo1);
		accountVo1.getAccountDetailList().add(accountDetailVo2);

		session.save(userVo);
		session.getTransaction().commit();
		session.close();
//		f.getCurrentSession().getTransaction().commit();
	}

}
