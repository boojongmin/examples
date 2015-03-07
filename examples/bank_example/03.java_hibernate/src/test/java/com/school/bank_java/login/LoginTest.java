package com.school.bank_java.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.school.bank_java.dao.mybatis.UserDao;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.mybatis.SqlSessionMaker;
import com.school.bank_java.service.LoginService;
import com.school.bank_java.vo.AccountVo;
import com.school.bank_java.vo.UserVo;

public class LoginTest {
//	UserDao dao;
//	SqlSession session;
//	private LoginService service;
//	private String userid ="boojongmin";
//	
//	@BeforeClass
//	public void Setup(){
//		session = SqlSessionMaker.getSession();
//		//user table data
//		UserVo vo = new UserVo();
////		session.put("boojongmin", vo);
//		dao = new UserDao(session);
//		
//		service = new LoginService(dao);
//		
//		session.delete("truncate.truncate_account");
//		session.delete("truncate.truncate_user");
//		session.commit();
//		UserVo userVo = new UserVo();
//		userVo.setUserid("boojongmin");
//		userVo.setUsername("부종민");
//		session.insert("truncate.insert_user", userVo);
//
//		
//		List<AccountVo> accountList = new ArrayList();
//		
//		for(int i = 1; i < 10; i++){
//			AccountVo accountVo = new AccountVo();
//			accountVo.setUserUid(userVo.getUid());
//			accountVo.setAccountNumber("0000100001");
//			accountVo.setAmount(10000 * i);
//			accountList.add(accountVo);
//		}
//		
//		session.insert("truncate.insert_account", accountList );
//		session.commit();
//	}
//	
//	@After
//	public void Close(){
//		session.close();
//	}
//	
//	@Test
//	public void t01_shallDoLoginBoojongminThanNotNull(){
//		UserVo vo = service.doLogin(userid );
//		Assert.assertNotNull(vo);
//		Assert.assertEquals(vo.getUserId(), userid );
//	}
//	
//	@Test
//	public void t02_doLoginAction(){
//		UserVo vo = service.doLogin(userid);
//		LoginManager.loginMap.put("UsersVo", vo);
//		Assert.assertEquals(vo, LoginManager.loginMap.get("UsersVo"));
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
