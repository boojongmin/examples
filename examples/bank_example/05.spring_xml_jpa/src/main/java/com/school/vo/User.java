package com.school.vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class User extends AbstractEntity{
	private String userId;
	private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user", orphanRemoval = true, fetch=FetchType.LAZY)
    @OrderBy("id")
	private Set<Account> accounts = new HashSet<Account>();

	public User(){}
	public User(String userId, String name ) {
		this.userId = userId;
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}


//    @OneToMany(fetch= FetchType.LAZY, mappedBy="user", cascade = CascadeType.ALL)
//	@OneToMany(fetch= FetchType.EAGER, mappedBy="userVo", cascade = CascadeType.ALL)
//	@Fetch(value = FetchMode.SUBSELECT) // <-- list를 써주고자할때 그리고 FetchType.EAGER를할때는 반드시 해주어야한다.
//	@OrderBy("uid ASC")
//	public Set<Account> getAccounts() {
//		return accounts;
//	}
//	public void setAccounts(Set<Account> accounts) {
//		this.accounts = accounts;
//	}
}
