package com.mie.model;

import java.util.Date;

public class User {
	/**
	 * This class contains all of the relevant information, and getter/setter
	 * methods for the User object.
	 */

	private int userID;
	private String username;
	private String password;
	private String email;

	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
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
}