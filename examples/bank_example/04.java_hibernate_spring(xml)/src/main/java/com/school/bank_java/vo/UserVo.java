package com.school.bank_java.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

//Mybatis Annotation
@Alias("UserVo")
//Hibernate Annotaion
@Entity
public class UserVo {

	private int uid;
	private String userId;
	private String userName;
	private Date createDt;
	private List<AccountVo> accountList = new ArrayList<AccountVo>();

	public UserVo(){}
	public UserVo(String userId, String userName, Date createDt) {
		this.userId = userId;
		this.userName = userName;
		this.createDt = createDt;

	}
	public UserVo(int uid, String userId, String userName, Date createDt) {
		this(userId, userName, createDt);
		this.uid = uid;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Column(unique=true, nullable=false, length=20)
	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
//	@OneToMany(fetch= FetchType.LAZY, mappedBy="userVo")
	@OneToMany(fetch= FetchType.EAGER, mappedBy="userVo", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT) // <-- list를 써주고자할때 그리고 FetchType.EAGER를할때는 반드시 해주어야한다.
	@OrderBy("uid ASC")
	public List<AccountVo> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<AccountVo> accountList) {
		this.accountList = accountList;
	}


	@Override
	public boolean equals(Object other) {
      boolean result = false;
      if (other instanceof UserVo) {
    	  UserVo that = (UserVo) other;
          result = (
        		  this.getUserId() != null && this.getUserId().equals(that.getUserId()))
        		  && (this.getUserName() != null && this.getUserName().equals(that.getUserName())
                );
      }
      return result;
	}
}
