package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.util.DbUtil;
import com.mie.model.*;
import com.mie.controller.*;
import com.mie.util.*;

// this class is a DAO that accesses the UserAccounts table to retrieve and insert information about users
// used for checking if users exist (during login and signup), and to insert new users (during signup)
// this class also accesses the ShoppingList table to retrieve information about users that already have a shopping cart in the database

public class UserDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	
	// this function returns whether or not a user is allowed to login based on the email and password they entered
	public boolean allowLogin(User user) {
		
		boolean allowLogin = true;

		Statement stmt = null;

		String email = user.getEmail();
		String password = user.getPassword();

		/**
		 * Prepare a query that searches for the given email and password.
		 */
		String userTable = "UserAccounts";
		String searchQuery = "select * from " + userTable + " where email='"
				+ email + "' AND password='" + password + "'";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// If there are no results from the query, then user doesn't exist.
			if (!more)
				allowLogin = false;

			else {
				// if user exists, set accountID and user's name in object
				user.setUserID(rs.getInt("AccountID")); // needed on following pages for later use (e.g. accessing shopping cart)
				user.setUsername(rs.getString("Username")); // needed to display on following pages
			}
		}

		catch (Exception ex) {
			System.out.println("allow login failed: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}

		// Return whether or not the user is allowed to login
		return allowLogin;

	}
	
	// this function returns whether or not a user is allowed to sign up based on the email they entered 
	// name, password, and confirm password are checked via Javascript directly on the JSP
	public boolean allowSignup(User user) {

		boolean allowSignup = true;

		Statement stmt = null;

		String email = user.getEmail();

		/**
		 * Prepare a query that searches for the given email
		 */
		String userTable = "UserAccounts";
		String searchQuery = "select * from " + userTable + " where email='"
				+ email + "'";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// If there are results from the query, then email already exists.
			if (more)
				allowSignup = false;

		}

		catch (Exception ex) {
			System.out.println("allow signup: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}

		// Return the whether or not user is allowed to signup.
		return allowSignup;

	}

	// this function inserts a new user's information (if valid) into the database
	public void createaccount(User user) {

		// get user's info (Name, Email, Password)
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();

		// prepare insert query
		String userTable = "UserAccounts";
		String insertQuery = "INSERT INTO " + userTable + " ( Username, [Password], Email ) VALUES ( '"
				+ username + "', '" + password + "', '" + email + "')";

		Statement stmt = null;

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(insertQuery);
			

		}

		catch (Exception ex) {
			System.out.println("new user insertion failed: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}

	}
	
	// this function returns whether or not the user is "returning" 
	// (i.e. has a shopping cart already stored in the database from previous uses of the website)
	public boolean isReturningUser(User user) {
		

		boolean isReturning = true;

		Statement stmt = null;

		int userID = user.getUserID();

		/**
		 * Prepare a query that searches the shoppingList table for the user's id
		 */
		String searchQuery = "select * from ShoppingList where AccountID="	+ userID + "";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// If there are no results from the query, then user doesn't exist.
			if (!more)
				isReturning = false;

		}

		catch (Exception ex) {
			System.out.println("allow login failed: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}

		// Return the whether or not user is returning.
		return isReturning;

	}
}
