package com.school.vo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.school.type.AccountDetailType;

@Entity
public class AccountDetail extends AbstractEntity {

	@ManyToOne//(cascade=CascadeType.ALL)
//	@JoinColumn(nullable=false)
	Account account;
	AccountDetailType type;
	int amount;

	public AccountDetail(){}
	public AccountDetail(AccountDetailType type, int amount) {
		this.type = type;
		this.amount = amount;
	}

	public AccountDetailType getType() {
		return type;
	}

	public void setType(AccountDetailType type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


}
