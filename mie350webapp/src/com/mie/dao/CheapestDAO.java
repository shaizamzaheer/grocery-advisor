package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mie.util.DbUtil;
import com.mie.model.*;
import com.mie.controller.*;
import com.mie.util.*;

public class CheapestDAO {
	/**
	 * This class handles calculating the prices for the stores within a certain radius of the user for their particular shopping list
	 */
	static Connection currentCon = null;
	static ResultSet rs = null;

	/**
	 * This method finds the prices for each storeID that's within the user's preferred/fixed radius
	 */
	
	public HashMap<Integer, Double> getCheapestStoreIDsAndPrices(int userID, ArrayList<Integer> candidateStoreIDs) {
		Statement stmt = null;

		HashMap<Integer, Double> storeIDToPrice = new HashMap<Integer, Double>();
	
		/**
		 * Prepare a query gets the storeID and corresponding total price for all items in user's shopping cart that is sold at those stores
		 */
		String searchQuery = "SELECT S.StoreID as StoreID, Sum(SI.Price*SL.Quantity) as TotalPrice"
							+ " FROM Store AS S, SoldIn AS SI, ShoppingList AS SL"
							+ " WHERE SI.ItemID = SL.ItemID" 
							+ " AND SI.Franchise = S.Franchise"
							/*+ " AND SL.Quantity <= I.Stock"*/
							+ " AND SL.AccountID = " + userID
							+ " AND S.StoreID In " + candidateStoreIDs.toString().replace("[", "(").replace("]", ")")
							+ " AND SL.ItemID In (SELECT ItemID FROM ShoppingList WHERE AccountID = " + userID + ")"
							+ " GROUP BY S.StoreID"
							+ " HAVING Count(*) = (SELECT COUNT(*) FROM ShoppingList WHERE AccountID = " + userID + ")";

		
		List<CartItem> shoppingCart = new ArrayList<CartItem>(); //shopping cart is a list of cartitems (contains item_name, quantity)

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			while(rs.next()) {
				//populate list
				storeIDToPrice.put(rs.getInt(1), rs.getDouble(2));
			}
		}

		catch (Exception ex) {
			System.out.println("Error reading shopping list: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}
		
		return storeIDToPrice;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(3);
		test.add(60);
		test.add(23);
		test.add(27);
		test.add(2);
		System.out.println(test.toString().replace("[", "(").replace("]", ")"));
		
		String searchQuery = "SELECT S.StoreID, Sum(SI.Price*SL.Quantity)"
				+ " FROM Store AS S, SoldIn AS SI, ShoppingList AS SL"
				+ " WHERE SI.ItemID = SL.ItemID" 
				+ " AND SI.Franchise = S.Franchise"
				+ " AND SL.AccountID = " + 1
				+ " AND S.StoreID In " + test.toString().replace("[", "(").replace("]", ")")
				+ " AND SL.ItemID In (SELECT ItemID FROM ShoppingList WHERE AccountID = " + 1 + ")"
				+ " GROUP BY S.StoreID"
				+ " HAVING Count(*) = (SELECT COUNT(*) FROM ShoppingList WHERE AccountID = " + 1 + ")";
		
		System.out.println(searchQuery);
	}

}
