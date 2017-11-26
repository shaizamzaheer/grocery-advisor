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
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get the parameter names to decide what to do - Add To Cart, Change Quantity, Delete, Delete All
		Enumeration<String> params = request.getParameterNames();
		
		HashSet<String> paramNames = new HashSet<String>();
		
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			System.out.println(param);
			paramNames.add(param);
		}
		//end of getting parameter names
		
		//A shopping cart dictionary will make it easy to make modifications to a particular item (if it's quantity is changed, or deleted)
		HashMap<Integer, CartItem> shoppingCartDictionary = (HashMap<Integer, CartItem>) request.getSession().getAttribute("shoppingCartDictionary");
		
		//if request params > 2, that means all the item info is sent, meaning a new item is to be added to the cart
		if (paramNames.size() > 2) {
			//Since an item is to be added, if it's the first item, then the dictionary would initially be null 
			if (shoppingCartDictionary == null)
				shoppingCartDictionary = new HashMap<Integer, CartItem>();
			
			addToCart(request, response, shoppingCartDictionary);
		}
			
		//if the request only has itemID and quantity, means the quantity is being changed
		else if (paramNames.containsAll(Arrays.asList(new String[]{"itemID", "quantity"}))) 
			changeQuantity(Integer.parseInt(request.getParameter("itemID")), Integer.parseInt(request.getParameter("quantity")), shoppingCartDictionary);
		
		//Convert from dictionary to an arrangement of items
		Set<CartItem> shoppingCart = new HashSet<CartItem>(shoppingCartDictionary.values());
		
		//put dictionary and shoppingCart on session
		request.getSession().setAttribute("shoppingCartDictionary", shoppingCartDictionary);
		request.getSession().setAttribute("shoppingCart", shoppingCart);
		
	}

	private void changeQuantity(int itemID, int quantity, HashMap<Integer, CartItem> shoppingCartDictionary) {
		shoppingCartDictionary.get(itemID).setQuantity(quantity); //change quantity
		
	}

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
