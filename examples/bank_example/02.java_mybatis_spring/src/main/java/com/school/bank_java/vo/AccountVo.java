package com.school.bank_java.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("AccountVo")
public class AccountVo {
	
	public AccountVo() { }
	
	public AccountVo(int userUid, String accountNumber, int amount) {
		this.userUid = userUid;
		this.accountNumber = accountNumber;
		this.amount = amount;	  
	}
	
	public AccountVo(int uid, int userUid, String accountNumber, int amount) {
		this.uid = uid;
		this.userUid = userUid;
		this.accountNumber = accountNumber;
		this.amount = amount;	  
	}
	
	private int uid;
	private int userUid;
	private String accountNumber;
	private int amount;
	private Date createDt;
	
	

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

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

	@Override
	public boolean equals(Object other) {
		 boolean result = false;
		    if (other instanceof AccountVo) {
		        AccountVo that = (AccountVo) other;
		        result = (this.getUserUid() == that.getUserUid() 
		        			&& this.getAccountNumber().equals(that.getAccountNumber())
		        			&&  this.getAmount() == that.getAmount()
		        		);
		    }
		    return result;
	}
}
