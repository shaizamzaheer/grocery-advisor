package com.mie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.model.CartItem;

/**
 * Servlet implementation class ShoppingCartServlet
 */

// This servlet handles all actions related to the shopping cart 
// i.e. adding to cart, changing quantity of items, deleting items individually, or deleting all items at once

public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the parameter names to decide what to do - Add To Cart, Change Quantity, Delete, Delete All
		Enumeration<String> params = request.getParameterNames();
		
		HashSet<String> paramNames = new HashSet<String>();
		
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			System.out.println(param);
			paramNames.add(param);
		}
		// end of getting parameter names
		
		// a shopping cart dictionary will allow for efficient O(1) access to a particular item in the cart (a CartItem object)
		// and make modifications (i.e. quantity is changed, or item is deleted)
		HashMap<Integer, CartItem> shoppingCartDictionary = (HashMap<Integer, CartItem>) request.getSession().getAttribute("shoppingCartDictionary");
		
		//if request params > 2, that means all the item info is sent, meaning a new item is to be added to the cart
		if (paramNames.size() > 2) {
			//Since an item is to be added, if it's the first item, then the dictionary would initially be null and needs to be initialized
			if (shoppingCartDictionary == null)
				shoppingCartDictionary = new HashMap<Integer, CartItem>();
			
			addToCart(request, response, shoppingCartDictionary); // request will carry all item info to add to shoppingCartDictionary
		}
			
		//if the request only has itemID and quantity, means the quantity is being changed
		else if (paramNames.containsAll(Arrays.asList(new String[]{"itemID", "quantity"}))) 
			changeQuantity(Integer.parseInt(request.getParameter("itemID")), Integer.parseInt(request.getParameter("quantity")), shoppingCartDictionary);

		//if the request only has itemID and delete = 'item', means the item is deleted
		else if (paramNames.containsAll(Arrays.asList("itemID", "delete")))
			deleteItem(Integer.parseInt(request.getParameter("itemID")), request.getParameter("delete"), shoppingCartDictionary);
		
		//if the request only has delete = 'all', means entire shopping cart is emptied.
		else if(paramNames.size() == 1 && paramNames.contains("delete")) 
			clearAll(request.getParameter("delete"), shoppingCartDictionary);
		
		//Convert from dictionary to a set of CartItem objects (i.e. a shopping cart)
		Set<CartItem> shoppingCart = new HashSet<CartItem>(shoppingCartDictionary.values());
		
		//put dictionary and shoppingCart on session
		request.getSession().setAttribute("shoppingCartDictionary", shoppingCartDictionary);
		request.getSession().setAttribute("shoppingCart", shoppingCart);
		
	}
	
	// this function empties the HashMap, making the shopping cart empty
	private void clearAll(String delete, HashMap<Integer, CartItem> shoppingCartDictionary) {

		if (delete.equalsIgnoreCase("all")) 
			shoppingCartDictionary.clear();
		
	}

	// this function deletes a single item mapping from the HashMap, thus removing that item from the shopping cart
	private void deleteItem(int itemID, String delete, HashMap<Integer, CartItem> shoppingCartDictionary) {

		if (delete.equalsIgnoreCase("item")) 
			shoppingCartDictionary.remove(itemID);
		
	}
	
	// this function sets a new quantity for a single item in the HashMap, thus changing the quantity of an item in the shopping cart
	private void changeQuantity(int itemID, int quantity, HashMap<Integer, CartItem> shoppingCartDictionary) {
		shoppingCartDictionary.get(itemID).setQuantity(quantity); //change quantity
		
	}

	// this function adds a new item mapping to the HashMap, thus adding a new item to the shopping cart
	private void addToCart(HttpServletRequest request, HttpServletResponse response, HashMap<Integer, CartItem> shoppingCartDictionary) {
		
		//set item info
		int itemID = Integer.parseInt(request.getParameter("itemID"));
		String itemName = request.getParameter("itemName");
		String amount = request.getParameter("amount");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//add item to dictionary
		shoppingCartDictionary.put(itemID, new CartItem(itemID, itemName, amount, quantity));
	}

}
