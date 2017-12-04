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

// This class is a DAO that accesses the ItemCategory table to get information about item types 
// used for search bar suggestions and category dropdowns

public class FoodDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;

	// This method returns a list of suggestions of all the existing item_types (used in the search bar suggestions)
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
	
	// This method returns a list of item types for a particular category (used in the category dropdowns)
	// categories are: Grains, Meat, Other, Vegetables, Fruits, Dairy
	public List<String> getItemTypes(String category) {
		
		Statement stmt = null;

		/**
		 * Prepare a query that searches the ItemCategory table for all item_types given a particular category.
		 */
		String searchQuery = "select Item_Type from ItemCategory where Item_Category='" + category + "'";  
		List<String> itemTypes = new ArrayList<String>(); //list to store item_types 

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			
			//For each tuple returned, store the item_type in the list of strings
			while(rs.next()) {
				itemTypes.add(rs.getString(1));
			}
		}

		catch (Exception ex) {
			System.out.println("Something went wrong when trying to get the item types for a category: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}
		
		//Return list of item_types (list of strings).
		return itemTypes;

	}
}
