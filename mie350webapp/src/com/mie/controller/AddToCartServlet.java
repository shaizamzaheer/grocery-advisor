package com.mie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.ShoppingCartDAO;
import com.mie.model.CartItem;

/**
 * Servlet implementation class AddToCartServlet
 */
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userID = (Integer) request.getSession().getAttribute("userID");
		//int userID = Integer.parseInt(request.getParameter("userID"));
		int itemID = Integer.parseInt(request.getParameter("itemID"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		CartItem cartItem = new CartItem(itemID, quantity);
		
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
		shoppingCartDAO.insertShoppingItem(userID, cartItem);
	}

}
