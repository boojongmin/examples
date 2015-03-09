package com.school.bank_java.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("UserVo")
public class UserVo {
	private int uid;
	private String userId;
	private String userName;
	private Date createDt;
		
	public UserVo(){}
	public UserVo(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	public UserVo(int uid, String userId, String userName) {
		this.uid = uid;
		this.userId = userId;
		this.userName = userName;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserid(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String userName) {
		this.userName = userName;
	}
	public Date getCreatedt() {
		return createDt;
	}
	public void setCreatedt(Date createDt) {
		this.createDt = createDt;
	}
	
	@Override
	public boolean equals(Object other) {
      boolean result = false;
      if (other instanceof UserVo) {
    	  UserVo that = (UserVo) other;
          result = (
        		  this.getUserId() != null && this.getUserId().equals(that.getUserId()))
        		  && (this.getUsername() != null && this.getUsername().equals(that.getUsername())
                );
      }
      return result;
	}
}
