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

// This servlet takes care of getting and passing along information about specific Items for a particular item type
// (e.g Demster's Sliced Bread and Wonder Sliced Bread for the item type: Bread)

public class DisplayItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// item_type is a parameter, sent either by browsing via the search bar or by categories
		String item_type = request.getParameter("item_type"); 
		
		// The details of all the items for the selected item type are read from the database via itemDAO, then stored as a list of Item objects.
		ItemDAO itemDAO = new ItemDAO();
		List<Item> items = itemDAO.getItems(item_type); 
		
		// item type and the list of items are stored on the session
		// so that the next page (items.jsp, which displays items) can use them to display the appropriate information
		request.getSession().setAttribute("item_type", item_type);
		request.getSession().setAttribute("items", items);
		
		response.sendRedirect("items.jsp");
	}

}
