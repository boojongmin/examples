package com.school.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Account extends AbstractEntity {

	@Column(name="accountNumber")
	String accountNumber;
	@ManyToOne
	@JoinColumn(/*referencedColumnName="id"*/nullable=false)
	User user;
	int amount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="account", orphanRemoval = true)
//    @JoinColumn(name="account_id")
	Set<AccountDetail> accountDetails = new HashSet<AccountDetail>();

	public Account(){}
	public Account(String accountNumber, int amount) {
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
//	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "account_id")
	public Set<AccountDetail> getAccountDetails() {
		return accountDetails;
	}
	public void setAccountDetails(Set<AccountDetail> accountDetails) {
		this.accountDetails = accountDetails;
	}

}
