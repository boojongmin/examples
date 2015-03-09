package com.school.bank_java.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.school.bank_java.code.AccountDetailType;
import com.school.bank_java.dao.AccountDao;
import com.school.bank_java.spring.config.DBInit;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/school/bank_java/spring/config/test-application-context.xml"})
public class AccountDaoTest{
	final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Autowired
	public  AccountDao dao;

	private static final int UID = 1;
	private static final int USERUID = 1;
	private static final int ACCOUNTUID = 1;
	private static final AccountVo ACCOUNTVO = new AccountVo(UID, USERUID, "0000100001", 1000);

	private static final List<AccountVo> ACCOUNTLlIST = new ArrayList<AccountVo>();
	private static final List<AccountDetailVo> ACCOUNTDETAILlIST = new ArrayList<AccountDetailVo>();

	@BeforeClass
	public static void setupData(){

		ACCOUNTLlIST.add(ACCOUNTVO);
		ACCOUNTDETAILlIST.add(new AccountDetailVo(ACCOUNTUID, AccountDetailType.SAVE, 10000));
		ACCOUNTDETAILlIST.add(new AccountDetailVo(ACCOUNTUID, AccountDetailType.WITHDRAW, -9000));
	}

	@Before
	public void beforeSetup(){
		DBInit.run();
	}


	@Test
	public void test_01_view_my_account_by_uid() throws Exception {
		List<AccountVo> list = dao.selectAccountList(UID);
		Assert.assertTrue(list.size() == 1);
		Assert.assertEquals(ACCOUNTVO.getAccountNumber(), list.get(0).getAccountNumber());
	}

	@Test
	public void test_02_selectAccountVoByUid() throws Exception {
		AccountVo vo = dao.selectAccountVoByUid(UID);
		Assert.assertTrue(ACCOUNTVO.equals(vo));
	}

	@Test
	public void test_03_selectAccountDetailByUid() throws Exception {
		List<AccountDetailVo> list = dao.selectAcocuntDetailListByAccountUid(UID);
		Assert.assertTrue(list.size() == ACCOUNTDETAILlIST.size());
	}

	@Test
	public void test_04_insertAccountDetail() throws Exception {
		AccountDetailVo vo = new AccountDetailVo(1, AccountDetailType.SAVE, 10000);
		int count = dao.insertAccountDetail(vo);
		AccountDetailVo resultVo = dao.selectAccountDetailByUid(vo.getUid());
		Assert.assertEquals(1, count);
		Assert.assertTrue( vo.equals(resultVo));
	}

	@Test
	public void test_05_insertAccount() throws Exception {
		AccountVo vo = new AccountVo(USERUID, "0000100002", 0);
		int count = dao.insertAccount(vo);
		Assert.assertEquals(1, count);
	}

	@Test
	public void test_06_seleectAccountCount() throws Exception {
		int accountCount = dao.selectAccountCountByUserUid(USERUID);
		Assert.assertEquals(ACCOUNTLlIST.size(), accountCount);
	}

	@Test
	public void test_07_selectAccountVoByAccountNumber() throws Exception {
		AccountVo vo = dao.selectAccountVoByAccountNumber(ACCOUNTVO.getAccountNumber());
		Assert.assertTrue(ACCOUNTVO.equals(vo));
	}
	@Test
	public void test_08_updateAccount() throws Exception {
		AccountVo updateVo = new AccountVo(
				ACCOUNTVO.getUid(),
				ACCOUNTVO.getUserUid(),
				"0000100002",
				9000000
				);
		int count = dao.updateAccount(updateVo);
		Assert.assertEquals(1, count);
		AccountVo vo = dao.selectAccountVoByAccountNumber(updateVo.getAccountNumber());
		Assert.assertTrue(updateVo.equals(vo));

	}
}
