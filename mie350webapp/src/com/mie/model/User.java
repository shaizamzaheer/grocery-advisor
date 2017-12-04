package com.mie.model;

import java.util.Date;

// this class holds all relevant info about the user (i.e. userID, user's Name, password, email
// and also whether or not the user is "returning" (i.e. has a shopping cart already in the database from previous uses of the website)

public class User {

	private int userID;
	private String username;
	private String password;
	private String email;
	private boolean isReturning;

	public User(String email, String password) {
		setEmail(email);
		setPassword(password);
	}
	
	public User(String username, String password, String email) {
		setUsername(username);
		setPassword(password);
		setEmail(email);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [username=" + username
				+ ", password=" + password + ", email=" + email + "]";
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
		
	}
	
	public int getUserID() {
		return userID;
	}

	public boolean isReturning() {
		return isReturning;
	}

	public void setReturning(boolean isReturning) {
		this.isReturning = isReturning;
	}
}