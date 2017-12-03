package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mie.util.DbUtil;
import com.mie.model.*;
import com.mie.controller.*;
import com.mie.util.*;

public class ShoppingCartDAO {

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
	public List<CartItem> getShoppingCart(int userID) {

		Statement stmt = null;

		/**
		 * Prepare a query gets the shopping cart (item_name and quantity) for a
		 * particular user.
		 */
		String searchQuery = "select I.Item_Name, S.Quantity from Items I, ShoppingList S where I.ItemID = S.ItemID and S.AccountID = "
				+ userID;

		List<CartItem> shoppingCart = new ArrayList<CartItem>(); // shopping
																	// cart is a
																	// list of
																	// cartitems
																	// (contains
																	// item_name,
																	// quantity)

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			while (rs.next()) {
				// populate list
				shoppingCart.add(new CartItem(rs.getString("Item_Name"), rs
						.getInt("Quantity")));//
			}
		}

		catch (Exception ex) {
			System.out
					.println("Error reading shopping list: An Exception has occurred! "
							+ ex);
			ex.printStackTrace();
		}

		// Return the user's shopping cart.
		return shoppingCart;

	}

	public void insertShoppingItem(int userID, CartItem cartItem) {

		PreparedStatement pst = null;
		/**
		 * Prepare a query that inserts the cartitme into the shoppingcart table
		 */

		String insertQuery = "INSERT INTO ShoppingList(AccountID, ItemID, Quantity) VALUES (?,?,?)";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();

			pst = currentCon.prepareStatement(insertQuery);
			pst.setInt(1, userID);
			pst.setInt(2, cartItem.getItemID());
			pst.setInt(3, cartItem.getQuantity());
			pst.executeUpdate();

			System.out.println(userID + ", " + cartItem.getItemID() + ", "
					+ cartItem.getQuantity()
					+ " has been entered successfully!");
		}

		catch (Exception ex) {
			System.out
					.println("Something went wrong trying to insert shopping item: "
							+ ex);
			System.out.println("Stacktrace: ");
			ex.printStackTrace();
		}

	}

	public void deleteCart(int userID) {

		PreparedStatement pst = null;
		/**
		 * Prepare a query that inserts the cartitme into the shoppingcart table
		 */

		String insertQuery = "DELETE FROM ShoppingList WHERE AccountID=?";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();

			pst = currentCon.prepareStatement(insertQuery);
			pst.setInt(1, userID);
			pst.executeUpdate();
		}

		catch (Exception ex) {
			System.out
					.println("Something went wrong trying to delete a user's entire cart: "
							+ ex);
			System.out.println("Stacktrace: ");
			ex.printStackTrace();
		}

	}

	public void insertCart(int userID, HashSet<CartItem> cart) {

		PreparedStatement pst = null;
		/**
		 * Prepare a query that inserts a single item into shopping list table.
		 * Will be looped through to add user's entire cart.
		 */
		System.out.println("Cart being entered: ");
		for (CartItem item : cart) {
			System.out.println("ID entered: " + item.getItemID());
		}

		String insertQuery = "INSERT INTO ShoppingList(AccountID, ItemID, Quantity) VALUES (?,?,?)";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();

			for (CartItem item : cart) {
				pst = currentCon.prepareStatement(insertQuery);
				pst.setInt(1, userID);
				pst.setInt(2, item.getItemID());
				pst.setInt(3, item.getQuantity());
				pst.executeUpdate();

				System.out.println(userID + ", " + item.getItemID() + ", "
						+ item.getQuantity()
						+ " has been entered successfully!");

			}
		}

		catch (Exception ex) {
			System.out
					.println("Something went wrong trying to insert one or all shopping item(s): "
							+ ex);
			System.out.println("Stacktrace: ");
			ex.printStackTrace();
		}

	}
	
	public Set<CartItem> loadCart(int userID) {
		
		Statement stmt = null;

		/**
		 * Prepare a query gets the shopping cart (item_name and quantity) for a particular user.
		 */
		String searchQuery = "select I.ItemID, I.Item_Name, I.Amount, S.Quantity from Items I, ShoppingList S where I.ItemID = S.ItemID and S.AccountID = " + userID;
		
		Set<CartItem> shoppingCart = new HashSet<CartItem>(); //shopping cart is a list of cartitems (contains item_name, quantity)

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			while(rs.next()) {
				//populate list
				int itemID = rs.getInt(1);
				String itemName = rs.getString(2);
				String amount = rs.getString(3);
				int quantity = rs.getInt(4);
				shoppingCart.add(new CartItem(itemID, itemName, amount, quantity));//
			}
		}

		catch (Exception ex) {
			System.out.println("Error reading shopping list to load cart: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}
		
		//Return the user's shopping cart.
		return shoppingCart;

	}
}
