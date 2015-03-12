package com.school.bank_java.spring.config;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.school.bank_java.code.AccountDetailType;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;
import com.school.bank_java.vo.UserVo;

public class HibernateDataInit {
	public static void run(SessionFactory factory){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		UserVo userVo = new UserVo("boojongmin", "부종민", new Date());

		AccountVo accountVo1 = new AccountVo(userVo, "0000100001", 1000, new Date());
		userVo.getAccountList().add(accountVo1);

		AccountDetailVo accountDetailVo1 = new AccountDetailVo(accountVo1, AccountDetailType.SAVE, 10000);
		AccountDetailVo accountDetailVo2 = new AccountDetailVo(accountVo1, AccountDetailType.TRANSFER, 9000);
		accountVo1.getAccountDetailList().add(accountDetailVo1);
		accountVo1.getAccountDetailList().add(accountDetailVo2);

		session.save(userVo);
	}

}
