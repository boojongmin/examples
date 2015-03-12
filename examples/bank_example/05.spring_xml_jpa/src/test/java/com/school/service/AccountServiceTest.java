package com.school.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.school.AbstractIntegrationTest;
import com.school.DBData;
import com.school.exception.BankException;
import com.school.repository.AccountRepository;
import com.school.repository.UserRepository;
import com.school.vo.Account;
import com.school.vo.User;

public class AccountServiceTest  extends AbstractIntegrationTest{
	@Autowired
	AccountService service;
	@Autowired
	UserRepository userRepo;
	@Autowired
	AccountRepository accountRepo;

	@Test
	public void test_01_createAccount(){
		User user = userRepo.findByUserId(DBData.USER.getUserId());
		service.createAccount(user.getId());
		User resultUser = userRepo.findOne(1L);
		assertThat(resultUser.getAccounts().size(), is(2));
	}

	@Test(expected=BankException.class)
	public void test_02_checkTotalAmount_fail_other_user_account(){
		User user = userRepo.findByUserId(DBData.USER.getUserId());
		user = userRepo.findOne(user.getId());
		Account account = user.getAccounts().iterator().next();
		service.deposit(2L, account.getAccountNumber(), 1000);
	}

	@Test
	public void test_03_checkTotalAmount(){
		User user = userRepo.findByUserId(DBData.USER.getUserId());
		Account account = user.getAccounts().iterator().next();
		service.deposit(user.getId(), account.getAccountNumber(), 1000);

	}

	@Test(expected=BankException.class)
	public void test_04_withdrawal_fail_over_amount(){
		User user = userRepo.findByUserId(DBData.USER.getUserId());
		Account account = user.getAccounts().iterator().next();
		service.withdrawal(user.getId(), account.getAccountNumber(), 9999999);
	}

	@Test
	public void test_05_withdrawal(){
		User user = userRepo.findByUserId(DBData.USER.getUserId());
		Account account = user.getAccounts().iterator().next();
		service.withdrawal(user.getId(), account.getAccountNumber(), 1000);
	}

	@Test(expected=BankException.class)
	public void test_06_transfer_fail_over_amount(){
		User user = userRepo.findByUserId(DBData.USER.getUserId());
		service.createAccount(user.getId());
		Account account = 	user.getAccounts().iterator().next();
        Account otherAccount = user.getAccounts().iterator().next();
		service.transfer(user.getId(), account.getAccountNumber(), otherAccount.getAccountNumber(), 9999999);
	}

	@Test
	public void test_07_transfer(){
		User user = userRepo.findByUserId(DBData.USER.getUserId());
		service.createAccount(user.getId());
		Iterator<Account> iter = user.getAccounts().iterator();
		Account account = 	iter.next();
        Account otherAccount = iter.next();
		service.transfer(user.getId(), account.getAccountNumber(), otherAccount.getAccountNumber(), 1000);
		accountRepo.findByUserId(user.getId()).forEach(x -> System.out.println(x.getAccountNumber() + "/" + x.getAmount()));
		assertThat(accountRepo.findOne(account.getId()).getAmount(), is(0));
	}

	@Test
	public void test_08_creaetAccount_and_viewMyAccount(){
		User user = userRepo.findByUserId(DBData.USER.getUserId());
		service.createAccount(user.getId());
		Set<Account> list  = accountRepo.findByUserId(user.getId());
		assertThat(list.size(), is(2));

	}
}
