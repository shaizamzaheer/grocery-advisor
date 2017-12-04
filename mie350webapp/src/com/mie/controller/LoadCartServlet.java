package com.mie.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.ShoppingCartDAO;
import com.mie.model.CartItem;

/**
 * Servlet implementation class LoadCartServlet
 */

// This servlet takes care of loading a returning user's previous shopping cart 

public class LoadCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// userID is required to access the user's personal shopping cart in the database
		int userID = (Integer)request.getSession().getAttribute("userID");
		
		// details of the user's shopping cart (itemID, item info, quantity) are retrieved from database via shoppingCartDAO
		// and stored as a set of CartItem objects (i.e. the user's shopping cart)
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
		HashSet<CartItem> shoppingCart = (HashSet<CartItem>) shoppingCartDAO.loadCart(userID);
		
		// a "dictionary" is made from the set of CartItems to map each itemID to the corresponding CartItem
		// this allows for efficient O(1) access when the user edits or removes an item in their cart
		// e.g. if a user increases the quantity of the item with itemID 1, the respective CartItem's quantity is adjusted
		// e.g. if a user deletes the item with itemID 2, that particular mapping is quickly and easily removed from the HashMap
		HashMap<Integer, CartItem> shoppingCartDictionary = makeDictionary(shoppingCart);
		
		// put dictionary and shoppingCart on session
		request.getSession().setAttribute("shoppingCartDictionary", shoppingCartDictionary);
		request.getSession().setAttribute("shoppingCart", shoppingCart);
		
		// redirect user to home page
		response.sendRedirect("welcome.jsp");
	}

	// This function makes a "dictionary" (HashMap) from the shopping cart (Set<CartItem>)
	private HashMap<Integer, CartItem> makeDictionary(HashSet<CartItem> shoppingCart) {
		
		HashMap<Integer, CartItem> dictionary = new HashMap<Integer, CartItem>();
		
		// for each item in the shopping cart, map it's itemID to the item itself.
		for (CartItem item : shoppingCart) {
			dictionary.put(item.getItemID(), item);
		}
		
		return dictionary;
	}

}
