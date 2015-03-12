package com.school.bank_java.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Alias("AccountVo")
@Entity
public class AccountVo {

	public AccountVo() { }
	public AccountVo(int userUid, String accountNumber, int amount) {
		this.userUid = userUid;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
    public AccountVo(UserVo userVo, String accountNumber, int amount) {
		this.userVo = userVo;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	public AccountVo(int uid, int userUid, String accountNumber, int amount) {
        this(userUid, accountNumber, amount);
        this.uid = uid;

	}

	private int uid;
	//mybatis에서만 쓰임
	private int userUid;
	private String accountNumber;
	private int amount;
	private Date createDt;

	//하이버네이트 추가
	private UserVo userVo;

	private List<AccountDetailVo> accountDetailList = new ArrayList<AccountDetailVo>();





	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Transient
	public int getUserUid() {
		return userUid;
	}

	public void setUserUid(int userUid) {
		this.userUid = userUid;
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

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@ManyToOne(fetch= FetchType.EAGER)
//	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn
	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}


//	@OneToMany(fetch=FetchType.LAZY, mappedBy="accountVo")
	@OneToMany(fetch=FetchType.EAGER, mappedBy="accountVo")
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("uid ASC")
	public List<AccountDetailVo> getAccountDetailList() {
		return accountDetailList;
	}
	public void setAccountDetailList(List<AccountDetailVo> accountDetailList) {
		this.accountDetailList = accountDetailList;
	}
	@Override
	public boolean equals(Object other) {
		 boolean result = false;
		    if (other instanceof AccountVo) {
		        AccountVo that = (AccountVo) other;
		        result = (this.getAccountNumber().equals(that.getAccountNumber())
		        			&&  this.getAmount() == that.getAmount()
		        		);
		    }
		    return result;
	}
}
