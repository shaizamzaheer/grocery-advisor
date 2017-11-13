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

public class FoodDAO {
	/**
	 * This class handles the Food objects and... TO FILL IN.
	 */
	static Connection currentCon = null;
	static ResultSet rs = null;

	/**
	 * This method attempts to find the user that is trying to log in by
	 * first retrieving the username and password entered by the user.
	 */
	public List<String> getAllSuggestions() {

		boolean doesUserExist = true;
		
		Statement stmt = null;

		/**
		 * Prepare a query that searches the members table in the database
		 * with the given username and password.
		 */
		String foodTable = "ItemCategory";
		String searchQuery = "select Item_Type from " + foodTable; //Food 
		List<String> suggestionList = new ArrayList<String>();

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			
			//For each tuple returned, store the foodName in the list
			while(rs.next()) {
				suggestionList.add(rs.getString(1));
			}
		}

		catch (Exception ex) {
			System.out.println("Something went wrong when trying to access the food table: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}
		
		//Return the whether or not user exists.
		return suggestionList;

	}
	
	public static void main(String[] args) {
		FoodDAO dao = new FoodDAO();
		
		List<String> suggestionList = dao.getAllSuggestions();
		System.out.println(suggestionList);
	}
}
