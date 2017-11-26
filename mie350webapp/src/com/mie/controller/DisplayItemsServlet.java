package com.mie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.ItemDAO;
import com.mie.model.Item;

/**
 * Servlet implementation class DisplayItemsServlet
 */
public class DisplayItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String item_type = request.getParameter("item_type");
		
		ItemDAO itemDAO = new ItemDAO();
		
		List<Item> items = itemDAO.getItems(item_type); 
		
		request.getSession().setAttribute("item_type", item_type);
		request.getSession().setAttribute("items", items);
		
		response.sendRedirect("items.jsp");
	}

}
