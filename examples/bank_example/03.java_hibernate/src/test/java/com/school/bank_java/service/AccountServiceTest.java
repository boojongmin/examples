package com.school.bank_java.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school.bank_java.code.AccountDetailType;
import com.school.bank_java.dao.IAccountDao;
import com.school.bank_java.dao.IUserDao;
import com.school.bank_java.dao.mybatis.AccountDao;
import com.school.bank_java.dao.mybatis.UserDao;
import com.school.bank_java.dao.mybatis.UserDaoTest;
import com.school.bank_java.exception.BankException;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.mybatis.SqlSessionMaker;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;
import com.school.bank_java.vo.UserVo;

public class AccountServiceTest extends CommonServiceTest{
	final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private static final SqlSession SQLSESSION = SqlSessionMaker.getSession(MYBATIS_CONFIG, false);
	public static final IUserDao USERDAO = new UserDao(SQLSESSION);
	public static final IAccountDao ACCOUNTDAO = new AccountDao(SQLSESSION);
	public static final AccountService SERVICE = new AccountService(ACCOUNTDAO, SQLSESSION);
	
	private final static int UID = 1;
	private final static int USERUID = 1;
	private final static String USERID = "boojongmin";
	
	private final AccountVo ACCOUNTVO = new AccountVo(UID, 1, "0000100001", 1000);
	private final static LoginManager LOGINMANAGER = new LoginManager();
	
	private static final List<String> ACCOUNTLlIST = new ArrayList<String>();
	
	@BeforeClass
	public static void setupData(){
		ACCOUNTLlIST.add("0000100001");
		LoginManager.loginMap.put("UserVo", new UserVo(USERUID, "boojongmin", "부종민"));
	}
	
	@Test
	public void test_01_viewMyAccount() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		List<AccountVo> list = SERVICE.viewMyAccount(user_uid);
		Assert.assertEquals(ACCOUNTLlIST.size(), list.size());
	}
	
	@Test
	public void test_02_createAccount() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		boolean createResult = SERVICE.createAccount(user_uid);
	}
	
	public void test_03_deposit_success() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		boolean depositTrue = SERVICE.deposit(user_uid, ACCOUNTVO.getAccountNumber(), 10000);
		Assert.assertTrue(depositTrue);
	}
	@Test(expected=BankException.class)
	public void test_04_deposit_fail() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		boolean depositFalse = SERVICE.deposit(user_uid, "fail AccountNumber", 10000);
		Assert.assertFalse(depositFalse);
	}
	public void test_05_withdrawal_success() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		boolean depositTrue = SERVICE.withdrawal(user_uid, ACCOUNTVO.getAccountNumber(), 10000);
		Assert.assertTrue(depositTrue);
	}
	
	@Test(expected=BankException.class)
	public void test_06_withdrawal_fail() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		SERVICE.withdrawal(user_uid, ACCOUNTVO.getAccountNumber(), 1000000000);
	}
	
	@Test
	public void test_07_transfer_success() throws Exception {
		int amount = 999;
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		SERVICE.createAccount(USERUID);
		List<AccountVo> list = ACCOUNTDAO.selectAccountList(USERUID);
		AccountVo vo = list.get(list.size() -1);
		boolean resultCondtion = SERVICE.transfer(user_uid, ACCOUNTVO.getAccountNumber(), vo.getAccountNumber(), amount);
		Assert.assertTrue(resultCondtion);
		Assert.assertEquals(ACCOUNTVO.getAmount() - amount, ACCOUNTDAO.selectAccountVoByUid(ACCOUNTVO.getUid()).getAmount() );
		Assert.assertEquals(vo.getAmount() + amount, ACCOUNTDAO.selectAccountVoByUid(vo.getUid()).getAmount() );
	}
	
	@Test(expected=BankException.class)
	public void test_08_transfer_fail() throws Exception {
		int amount = 9999999;
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		SERVICE.createAccount(USERUID);
		List<AccountVo> list = ACCOUNTDAO.selectAccountList(USERUID);
		AccountVo vo = list.get(list.size() -1);
		SERVICE.transfer(user_uid, ACCOUNTVO.getAccountNumber(), vo.getAccountNumber(), amount);
	}
	

}
