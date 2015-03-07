package com.school.bank_java.dao.mybatis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.IDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school.bank_java.code.AccountDetailType;
import com.school.bank_java.dao.IAccountDao;
import com.school.bank_java.dao.mybatis.AccountDao;
import com.school.bank_java.mybatis.SqlSessionMaker;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;

public class AccountDaoTest extends CommonDaoTest{
	final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	public static final IAccountDao DAO = new AccountDao(SqlSessionMaker.getSession(MYBATIS_CONFIG, true));
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
	
	
	@Test
	public void test_01_view_my_account_by_uid() throws Exception {
		List<AccountVo> list = DAO.selectAccountList(UID);
		Assert.assertTrue(list.size() == 1);
		Assert.assertEquals(ACCOUNTVO.getAccountNumber(), list.get(0).getAccountNumber());	
	}
	
	@Test
	public void test_02_selectAccountVoByUid() throws Exception {
		AccountVo vo = DAO.selectAccountVoByUid(UID);
		Assert.assertTrue(ACCOUNTVO.equals(vo));
	}
	
	@Test
	public void test_03_selectAccountDetailByUid() throws Exception {
		List<AccountDetailVo> list = DAO.selectAcocuntDetailListByAccountUid(UID);
		Assert.assertTrue(list.size() == ACCOUNTDETAILlIST.size());
	}
	
	@Test
	public void test_04_insertAccountDetail() throws Exception {
		AccountDetailVo vo = new AccountDetailVo(1, AccountDetailType.SAVE, 10000);
		int count = DAO.insertAccountDetail(vo);
		AccountDetailVo resultVo = DAO.selectAccountDetailByUid(vo.getUid());
		Assert.assertEquals(1, count);
		Assert.assertTrue( vo.equals(resultVo));
	}
	
	@Test
	public void test_05_insertAccount() throws Exception {
		AccountVo vo = new AccountVo(USERUID, "0000100002", 0);
		int count = DAO.insertAccount(vo);
		Assert.assertEquals(1, count);
	}
	
	@Test
	public void test_06_seleectAccountCount() throws Exception {
		int accountCount = DAO.selectAccountCountByUserUid(USERUID);
		Assert.assertEquals(ACCOUNTLlIST.size(), accountCount);
	}
	
	@Test
	public void test_07_selectAccountVoByAccountNumber() throws Exception {
		AccountVo vo = DAO.selectAccountVoByAccountNumber(ACCOUNTVO.getAccountNumber());
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
		int count = DAO.updateAccount(updateVo);
		Assert.assertEquals(1, count);
		AccountVo vo = DAO.selectAccountVoByAccountNumber(updateVo.getAccountNumber());
		Assert.assertTrue(updateVo.equals(vo));
//		Assert.assertEquals(ACCOUNTVO.getAccountNumber(),vo.getAccountNumber() );
//		Assert.assertEquals(ACCOUNTVO.getAmount(),vo.getAmount() );
		
	}
}
