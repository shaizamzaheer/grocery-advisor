package com.mie.dao;

import java.sql.Connection;
import java.sql.Date;
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
							+ " FROM Store AS S, SoldIn AS SI, ShoppingList AS SL, Inventory N"
							+ " WHERE SI.ItemID = SL.ItemID" 
							+ " AND SI.Franchise = S.Franchise"
							+ " AND SL.Quantity <= N.Stock"
							+ " AND S.StoreID = N.StoreID"
							+ " AND SI.ItemID = N.ItemID"
							+ " AND SL.ItemID = N.ItemID"
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
	
	public List<ReceiptItem> getReceipt(int userID, int storeID) {
		Statement stmt = null;

		List<ReceiptItem> receipt = new ArrayList<ReceiptItem>();
		int itemID = 0;
		String item_name = "";
		String amount = "";
		int quantity = 0;
		double price = 0;
		Date saleEnd = null;
	
		/**
		 * Prepare a query gets the storeID and corresponding total price for all items in user's shopping cart that is sold at those stores
		 */
		String searchQuery = "SELECT I.ItemID, I.Item_Name, I.Amount, SL.Quantity, SI.Price, SI.PriceEnd"
							+ " FROM Store AS S, SoldIn AS SI, ShoppingList AS SL, Inventory N, Items I"
							+ " WHERE SI.ItemID = SL.ItemID"
							+ " AND SI.ItemID = I.ItemID"
							+ " AND SI.ItemID = N.ItemID" 
							+ " AND SI.Franchise = S.Franchise"
							+ " AND SL.Quantity <= N.Stock"
							+ " AND S.StoreID = N.StoreID"
							+ " AND SI.ItemID = SL.ItemID"
							+ " AND SL.AccountID = " + userID
							+ " AND S.StoreID = " + storeID
							+ " AND SL.ItemID In (SELECT ItemID FROM ShoppingList WHERE AccountID = " + userID + ")";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			while(rs.next()) {
				//populate list
				itemID = rs.getInt(1);
				item_name = rs.getString(2);
				amount = rs.getString(3);
				quantity = rs.getInt(4);
				price = rs.getDouble(5);
				saleEnd = rs.getDate(6);
				
				System.out.println("ID: " + itemID + " Name: " + item_name + " Amount: " + amount + "Qty: " + quantity
						+ " Price: $" + price + " Sale Ends On: " + saleEnd);
				
				receipt.add(new ReceiptItem(itemID, item_name, amount, quantity, price, saleEnd));
			}
		}

		catch (Exception ex) {
			System.out.println("Error reading shopping list: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}
		
		return receipt;
	}

}
