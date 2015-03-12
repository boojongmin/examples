package com.school.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.school.domain.Account;
import com.school.domain.AccountDetail;
import com.school.domain.User;
import com.school.exception.BankException;
import com.school.repository.AccountDetailRepository;
import com.school.repository.AccountRepository;
import com.school.repository.UserRepository;
import com.school.type.AccountDetailType;

@Transactional(readOnly=true)
public class AccountService {

	UserRepository userRepo;
	AccountRepository accRepo;
	AccountDetailRepository accDetailRepo;

	public AccountService(){}
	@Autowired
	public AccountService(UserRepository userRepo, AccountRepository accRepo,AccountDetailRepository accDetailRepo) { this.userRepo = userRepo; this.accRepo = accRepo; this.accDetailRepo = accDetailRepo; }

	public Set<Account> viewMyAccount(Long id) {
//		Set<Account> list = userRepo.findOne(id).getAccounts();
		Set<Account> list = accRepo.findByUserId(id);
		list.size();
		return list;
		}
	@Transactional(readOnly=false)
	public void createAccount(Long id) {
		User user = userRepo.findOne(id);
		Iterable<User> findAll = userRepo.findAll();

		int accountCount = accRepo.countByUser(user).intValue() + 1;
		String accountNumber = String.format("%05d%05d", id, accountCount);
		Account account = new Account(accountNumber, 0);
		user.getAccounts().add(account);
		account.setUser(user);
		userRepo.save(user);
	}
	@Transactional(readOnly=false)
	public void deposit(Long id, String accountNumber, int amount) {
		executeDeposit(id, accountNumber, amount, true);
	}

	private void executeDeposit(Long id, String accountNumber, int amount, boolean isMyAccount) {
		Account myAccount = checkMyAndOtherAccount(id, accountNumber, isMyAccount);
		int totalAmount = checkTotalAmount(amount, myAccount);
		myAccount.setAmount( totalAmount );
		accRepo.save(myAccount);
	}

	private int checkTotalAmount(int amount, Account myAccount) {
		AccountDetail vo = new AccountDetail(AccountDetailType.SAVE, amount);
		myAccount.getAccountDetails().add(vo);
		accRepo.save(myAccount);
		int totalAmount = myAccount.getAmount() + amount;
		if(totalAmount < 0){ throw new BankException("잔액이 부족합니다."); }
		return totalAmount;
	}

	private Account checkMyAndOtherAccount(Long id, String accountNumber, boolean isMyAccount) {
		Account Account = accRepo.findByAccountNumber(accountNumber);
		if(Account == null){ throw new BankException("계좌번호가 존재하지 않습니다."); }
		if(isMyAccount && Account.getUser().getId() != id){ throw new BankException("본인의 계좌에만 입금/출금/삭제가 가능합니다."); }
		return Account;
	}

	@Transactional(readOnly=false)
	public void withdrawal(Long id, String accountNumber, int amount) {
		deposit(id, accountNumber, amount * -1);
	}

	@Transactional(readOnly=false)
	public void transfer(Long id, String myAccountNumber, String otherAccountNumber, int amount) {
		executeDeposit(id, myAccountNumber, amount * -1, true);
		executeDeposit(id, otherAccountNumber, amount * 1, false);
    }

	@Transactional(readOnly=false)
	public void removeAccount(String accountNumber) {
		Account account = accRepo.findByAccountNumber(accountNumber);
		if(account == null){throw new BankException("계좌번호가 없습니다.");}
		User user = account.getUser();
		checkMyAndOtherAccount(user.getId(), accountNumber, true);
		user.getAccounts().remove(account);
	}
}
