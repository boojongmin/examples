package com.school.bank_java.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.school.bank_java.code.AccountDetailType;

@Alias("AccountDetailVo")
public class AccountDetailVo {
	private int uid;
	private int accountUid;
	private AccountDetailType type;
	private int amount;
	private Date createDt;
	
	public AccountDetailVo() { }
	public AccountDetailVo(int accountUid, AccountDetailType type, int amount) {
		this.accountUid = accountUid;
		this.type = type;
		this.amount = amount;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getAccountUid() {
		return accountUid;
	}

	public void setAccountUid(int accountUid) {
		this.accountUid = accountUid;
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

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
	@Override
	public boolean equals(Object other) { 
	  boolean result = false;
      if (other instanceof AccountDetailVo) {
          AccountDetailVo that = (AccountDetailVo) other;
          result = (this.getAccountUid() == that.getAccountUid() 
        		  && this.getType().equals(that.getType())
                  && this.getAmount() == that.getAmount()
                );
      }
      return result;
	}
	
}
