package com.highradius.internship;

public class User {
	String username;
	String password;
	String userLevel;
	String orderRange;
	String status;
	
	public User() {
		super();
	
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String userLevel, String orderRange, String status) {
		super();
		this.username = username;
		this.password = password;
		this.userLevel = userLevel;
		this.orderRange = orderRange;
		this.status = status;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserLevel() {
		return userLevel;
	}
	
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	
	public String getOrderRange() {
		return orderRange;
	}
	
	public void setOrderRange(String orderRange) {
		this.orderRange = orderRange;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
