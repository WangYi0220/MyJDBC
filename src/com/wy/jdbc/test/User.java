package com.wy.jdbc.test;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String loginName;
	private String password;
	private int userStatus;
	private Date createDate;
	private String userName;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String loginName, String password, int userStatus, Date createDate, String userName) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.password = password;
		this.userStatus = userStatus;
		this.createDate = createDate;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", userStatus=" + userStatus
				+ ", createDate=" + createDate + ", userName=" + userName + "]";
	}
}
