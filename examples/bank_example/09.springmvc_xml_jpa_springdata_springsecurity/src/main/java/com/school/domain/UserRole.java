package com.school.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserRole extends AbstractEntity {

//	private Integer userRoleId;
	private String role;
	@ManyToOne
	@JoinColumn(nullable=false)
	private User user;

//	public Integer getUserRoleId() {
//		return userRoleId;
//	}
//	public void setUserRoleId(Integer userRoleId) {
//		this.userRoleId = userRoleId;
//	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
