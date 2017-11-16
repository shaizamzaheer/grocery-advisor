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
	 * This method returns a list of suggestions of all the existing item_types.
	 */
	public List<String> getAllSuggestions() {
		
		Statement stmt = null;

		/**
		 * Prepare a query that searches the ItemCategory table for all item_types.
		 */
		String searchQuery = "select Item_Type from ItemCategory";  
		List<String> suggestionList = new ArrayList<String>(); //list to store item_types 

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			
			//For each tuple returned, store the item_type in the list
			while(rs.next()) {
				suggestionList.add(rs.getString(1));
			}
		}

		catch (Exception ex) {
			System.out.println("Something went wrong when trying to access the food table: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}
		
		//Return list of item_types (list of strings).
		return suggestionList;

	}
}
