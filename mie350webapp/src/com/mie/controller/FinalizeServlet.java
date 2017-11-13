package com.mie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.ShoppingCartDAO;
import com.mie.model.CartItem;

/**
 * Servlet implementation class FinalizeServlet
 */
public class FinalizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
		List<CartItem> shoppingCart = shoppingCartDAO.getShoppingCart();
		
		request.getSession().setAttribute("shoppingCart", shoppingCart);
		
		response.sendRedirect("finalize.jsp");
	}

}
