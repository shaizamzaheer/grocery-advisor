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

public class UserDAO {

	/**
	 * This class handles the User objects and the login component of the web
	 * app.
	 */
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	

	/**
	 * This method attempts to find the user that is trying to log in by first
	 * retrieving the username and password entered by the user.
	 */
	public boolean checkUserExists(User user) {
		

		boolean doesUserExist = true;

		Statement stmt = null;

		String username = user.getUsername();
		String password = user.getPassword();

		/**
		 * Prepare a query that searches the members table in the database with
		 * the given username and password.
		 */
		String userTable = "UserAccounts";
		String searchQuery = "select * from " + userTable + " where username='"
				+ username + "' AND password='" + password + "'";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// If there are no results from the query, then user doesn't exist.
			if (!more)
				doesUserExist = false;

			else
				// if user exists, set accountID in object
				user.setUserID(rs.getInt("AccountID"));
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}

		// Return the whether or not user exists.
		return doesUserExist;

	}

	public void createaccount(User user) {

		// get info from object
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();

		// prepare query
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
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}

	}
}
