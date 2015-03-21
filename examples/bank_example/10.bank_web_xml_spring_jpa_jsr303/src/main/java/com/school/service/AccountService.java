package com.school.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.domain.Account;
import com.school.domain.AccountDetail;
import com.school.domain.User;
import com.school.exception.BankException;
import com.school.repository.AccountDetailRepository;
import com.school.repository.AccountRepository;
import com.school.repository.UserRepository;
import com.school.type.AccountDetailType;

//@Component
@Service
@Transactional(readOnly=true)
public class AccountService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	AccountRepository accRepo;
	@Autowired
	AccountDetailRepository accDetailRepo;


	public Set<Account> viewMyAccount(Long id) {
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
		executeDeposit(id, accountNumber, amount, true, AccountDetailType.DEPOSIT);
	}

	private void executeDeposit(Long id, String accountNumber, int amount, boolean isMyAccount, AccountDetailType type) {
		Account myAccount = checkMyAndOtherAccount(id, accountNumber, isMyAccount);
		int totalAmount = checkTotalAmount(amount, myAccount, type);
		myAccount.setAmount( totalAmount );
		accRepo.save(myAccount);
	}

	private int checkTotalAmount(int amount, Account myAccount, AccountDetailType type) {
		AccountDetail vo = new AccountDetail(type, amount);
		myAccount.getAccountDetails().add(vo);
		vo.setAccount(myAccount);
		accDetailRepo.save(vo);
		int totalAmount = myAccount.getAmount() + amount;
		if(totalAmount < 0){ throw new BankException("잔액이 부족합니다."); }
		return totalAmount;
	}

	private Account checkMyAndOtherAccount(Long id, String accountNumber, boolean isMyAccount) {
		Account Account = accRepo.findByAccountNumber(accountNumber);
		if(Account == null){ throw new BankException("계좌번호가 존재하지 않습니다."); }
		if(isMyAccount && (long)Account.getUser().getId() != (long)id){ throw new BankException("본인의 계좌에만 입금/출금/삭제가 가능합니다."); }
		return Account;
	}

	@Transactional(readOnly=false)
	public void withdrawal(Long id, String accountNumber, int amount) {
		executeDeposit(id, accountNumber, amount * -1, true, AccountDetailType.WITHDRAW);
	}

	@Transactional(readOnly=false)
	public void transfer(Long id, String myAccountNumber, String otherAccountNumber, int amount) {
		executeDeposit(id, myAccountNumber, amount * -1, true, AccountDetailType.TRANSFER);
		executeDeposit(id, otherAccountNumber, amount * 1, false, AccountDetailType.TRANSFER);
    }

	@Transactional(readOnly=false)
	public void removeAccount(Long id, String accountNumber) {
		Account account = accRepo.findByAccountNumber(accountNumber);
		if(account == null){throw new BankException("계좌번호가 없습니다.");}
		User user = account.getUser();
		checkMyAndOtherAccount(id, accountNumber, true);
		accRepo.delete(account);
	}
}
