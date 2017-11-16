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

public class ShoppingCartDAO {

	/**
	 * This class handles the User objects and the login component of the web
	 * app.
	 */
	static Connection currentCon = null;
	static ResultSet rs = null;

	/**
	 * This method attempts to find the user that is trying to log in by
	 * first retrieving the username and password entered by the user.
	 */
	public List<CartItem> getShoppingCart(int userID) {
		
		Statement stmt = null;

		/**
		 * Prepare a query gets the shopping cart (item_name and quantity) for a particular user.
		 */
		String searchQuery = "select I.Item_Name, S.Quantity from Items I, ShoppingList S where I.ItemID = S.ItemID and S.AccountID = " + userID;
		
		List<CartItem> shoppingCart = new ArrayList<CartItem>(); //shopping cart is a list of cartitems (contains item_name, quantity)

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			while(rs.next()) {
				//populate list
				shoppingCart.add(new CartItem(rs.getString("Item_Name"), rs.getInt("Quantity")));//
			}
		}

		catch (Exception ex) {
			System.out.println("Error reading shopping list: An Exception has occurred! "
					+ ex);
			ex.printStackTrace();
		}
		
		//Return the user's shopping cart.
		return shoppingCart;

	}
}
