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
public class InsertCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashSet<CartItem> shoppingCart = (HashSet<CartItem>)request.getSession().getAttribute("shoppingCart");
		int userID = (Integer)request.getSession().getAttribute("userID");
		
		for (CartItem item : shoppingCart) {
			System.out.println("item id in cart right before calling insert method: " + item.getItemID());
		}
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
		shoppingCartDAO.deleteCart(userID);
		shoppingCartDAO.insertCart(userID, shoppingCart);
		
		response.sendRedirect("finalize.jsp");
	}

}
