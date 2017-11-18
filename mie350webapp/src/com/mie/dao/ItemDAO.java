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

import com.mie.util.DbUtil;

public class ItemDAO {
	/**
	 * This class handles the Item objects and... TO FILL IN.
	 */
	static Connection currentCon = null;
	static ResultSet rs = null;

	/**
	 * This method returns a list of suggestions of all the existing item_types.
	 */
	public List<Item> getItems(String item_type) {
		
		Statement stmt = null;

		/**
		 * Prepare a query that searches the Items table for all items of a specific item_type.
		 */
		String searchQuery = "Select ItemID, Item_Name, Amount from Items WHERE Item_Type = '" + item_type + "'";  
		List<Item> items = new ArrayList<Item>(); //list to store items

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			
			//For each tuple returned, store item in the list
			while(rs.next()) {
				items.add(new Item(rs.getInt("ItemID"), rs.getString("Item_Name"), rs.getString("Amount")));
			}
		}

		catch (Exception ex) {
			System.out.println("Something went wrong when trying to access the food table: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}
		
		//Return list of items (list of Item objects)
		return items;

	}
}
