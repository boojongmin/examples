package com.school.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Account extends AbstractEntity {

	@Column(name="accountNumber")
    @NotNull(message="공백은 입력할 수 없습니다.") @Size(min=10, max=10, message="계좌번호는 10글자입니다.")
	String accountNumber;
	@ManyToOne
	@JoinColumn(/*referencedColumnName="id"*/nullable=false)
	User user;
	//validator http://docs.oracle.com/javaee/6/tutorial/doc/gircz.html
	@NotNull(message="공백은 입력할 수 없습니다.") @DecimalMin(value="1", message="0은 입력할 수 없습니다.") @DecimalMax(value="" + Integer.MAX_VALUE, message=Integer.MAX_VALUE + " 값 초과한 숫자는 입력할 수 없습니다.")
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