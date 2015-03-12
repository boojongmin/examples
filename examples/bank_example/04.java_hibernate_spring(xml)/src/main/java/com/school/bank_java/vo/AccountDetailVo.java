package com.school.bank_java.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;

import com.school.bank_java.code.AccountDetailType;

@Alias("AccountDetailVo")
@Entity
public class AccountDetailVo {
	private int uid;
	//mybatis에서만 쓰임//@Transient getter에만 적용됨 feild는 인식 안함
	private int accountUid;
	private AccountDetailType type;
	private int amount;
	private Date createDt;

	//하이버네이트 추가
	private AccountVo accountVo;

	public AccountDetailVo() { }
	public AccountDetailVo(int accountUid, AccountDetailType type, int amount) {
		this.accountUid = accountUid;
		this.type = type;
		this.amount = amount;
	}
	public AccountDetailVo(AccountVo accountVo, AccountDetailType type, int amount) {
		this.accountVo = accountVo;
		this.type = type;
		this.amount = amount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Transient
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



	@ManyToOne(fetch=FetchType.EAGER)
//	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn
	public AccountVo getAccountVo() {
		return accountVo;
	}
	public void setAccountVo(AccountVo accountVo) {
		this.accountVo = accountVo;
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
