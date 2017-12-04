package com.mie.controller;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.ShoppingCartDAO;
import com.mie.model.CartItem;

/**
 * Servlet implementation class InsertCartServlet
 */

// This servlet takes care of inserting the user's shopping cart into the database (all at once)

public class InsertCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// user's shopping cart and id are required to correctly delete the previous cart in the database (if any), and insert the current cart
		HashSet<CartItem> shoppingCart = (HashSet<CartItem>)request.getSession().getAttribute("shoppingCart");
		int userID = (Integer)request.getSession().getAttribute("userID");
		
//		for (CartItem item : shoppingCart) {
//			System.out.println("item id in cart right before calling insert method: " + item.getItemID());
//		}
		
		// user's personal previous shopping cart (if any, stored from previous uses of the website) is deleted
		// then the user's personal current shopping cart is inserted into ShoppingList table via shoppingCartDAO
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
		shoppingCartDAO.deleteCart(userID);
		shoppingCartDAO.insertCart(userID, shoppingCart);
		
		// after cart is inserted into database, user is redirected to finalize.jsp to configure their location, ranking, and travel preferences
		response.sendRedirect("finalize.jsp");
	}

}
