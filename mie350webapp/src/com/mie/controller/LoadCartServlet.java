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
public class LoadCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userID = (Integer)request.getSession().getAttribute("userID");
		
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
		HashSet<CartItem> shoppingCart = (HashSet<CartItem>) shoppingCartDAO.loadCart(userID);
		
		HashMap<Integer, CartItem> shoppingCartDictionary = makeDictionary(shoppingCart);
		
		//put dictionary and shoppingCart on session
		request.getSession().setAttribute("shoppingCartDictionary", shoppingCartDictionary);
		request.getSession().setAttribute("shoppingCart", shoppingCart);
	}

	private HashMap<Integer, CartItem> makeDictionary(HashSet<CartItem> shoppingCart) {
		
		HashMap<Integer, CartItem> dictionary = new HashMap<Integer, CartItem>();
		
		for (CartItem item : shoppingCart) {
			dictionary.put(item.getItemID(), item);
		}
		
		return dictionary;
	}

}
