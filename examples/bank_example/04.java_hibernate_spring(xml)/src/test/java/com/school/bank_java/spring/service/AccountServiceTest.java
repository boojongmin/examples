package com.school.bank_java.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.school.bank_java.dao.AccountDao;
import com.school.bank_java.dao.UserDao;
import com.school.bank_java.dao.UserDaoTest;
import com.school.bank_java.exception.BankException;
import com.school.bank_java.login.LoginManager;
import com.school.bank_java.service.AccountService;
import com.school.bank_java.spring.config.MybatisDBInit;
import com.school.bank_java.vo.AccountVo;
import com.school.bank_java.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/com/school/bank_java/config/application-*.xml"})
@ActiveProfiles(profiles = {"test" ,  "hibernate"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class AccountServiceTest{
	final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Autowired
	ApplicationContext ctx;

	@Autowired
	public UserDao userdao;
	@Autowired
	public AccountDao accountdao;
	@Autowired
	public AccountService service;

	private final static int UID = 1;
	private final static int USERUID = 1;
	private final static String USERID = "boojongmin";

	private final AccountVo ACCOUNTVO = new AccountVo(UID, 1, "0000100001", 1000);
	private final static LoginManager LOGINMANAGER = new LoginManager();

	private static final List<String> ACCOUNTLlIST = new ArrayList<String>();

	@BeforeClass
	public static void setupData(){
		ACCOUNTLlIST.add("0000100001");
		LoginManager.loginMap.put("UserVo", new UserVo(USERUID, "boojongmin", "부종민", new Date()));
		MybatisDBInit.run();
	}

//	@Before
//	public void beforeSetup(){
//		DBInit.run();
//	}

	@Test
	public void test_01_viewMyAccount() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		List<AccountVo> list = service.viewMyAccount(user_uid);
		Assert.assertEquals(ACCOUNTLlIST.size(), list.size());
	}

	@Test
	public void test_02_createAccount() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		boolean createResult = service.createAccount(user_uid);
	}

	public void test_03_deposit_success() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		boolean depositTrue = service.deposit(user_uid, ACCOUNTVO.getAccountNumber(), 10000);
		Assert.assertTrue(depositTrue);
	}
	@Test(expected=BankException.class)
	public void test_04_deposit_fail() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		boolean depositFalse = service.deposit(user_uid, "fail AccountNumber", 10000);
		Assert.assertFalse(depositFalse);
	}
	@Test
	public void test_05_withdrawal_success() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		boolean depositTrue = service.withdrawal(user_uid, ACCOUNTVO.getAccountNumber(), 1000);
		Assert.assertTrue(depositTrue);
	}

	@Test(expected=BankException.class)
	public void test_06_withdrawal_fail() throws Exception {
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		service.withdrawal(user_uid, ACCOUNTVO.getAccountNumber(), 1000000000);
	}

	@Test
	public void test_07_transfer_success() throws Exception {
		int amount = 999;
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		service.createAccount(USERUID);
		List<AccountVo> list = accountdao.selectAccountList(USERUID);
		AccountVo vo = list.get(list.size() -1);
		boolean resultCondtion = service.transfer(user_uid, ACCOUNTVO.getAccountNumber(), vo.getAccountNumber(), amount);
		Assert.assertTrue(resultCondtion);
		Assert.assertEquals(ACCOUNTVO.getAmount() - amount, accountdao.selectAccountVoByUid(ACCOUNTVO.getUid()).getAmount() );
		Assert.assertEquals(vo.getAmount() + amount, accountdao.selectAccountVoByUid(vo.getUid()).getAmount() );
	}

	@Test(expected=BankException.class)
	public void test_08_transfer_fail() throws Exception {
		int amount = 9999999;
		int user_uid = LoginManager.loginMap.get("UserVo").getUid();
		service.createAccount(USERUID);
		List<AccountVo> list = accountdao.selectAccountList(USERUID);
		AccountVo vo = list.get(list.size() -1);
		service.transfer(user_uid, ACCOUNTVO.getAccountNumber(), vo.getAccountNumber(), amount);
	}


}
