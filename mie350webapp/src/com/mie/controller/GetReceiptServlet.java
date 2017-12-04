package com.mie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.CheapestDAO;
import com.mie.model.ReceiptItem;

/**
 * Servlet implementation class GetReceiptServlet
 */

// This servlet takes care of getting and passing along information about the receipt at a particular store for a partiuclar user's cart
// (i.e. quantity, item info, and price)

public class GetReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// userID required to access only user's personal cart
		int userID = (Integer)request.getSession().getAttribute("userID");
		
		// storeID required to access only the prices for a particular store within a franchise that sells all user's cart items
		// this parameter is sent from results.jsp
		int storeID = Integer.parseInt(request.getParameter("storeID"));
		
		// details of all the items (quantity, info, price) are read from the database via priceDAO,
		// and stored as a list of ReceiptItem objects (i.e. "receipt")
		CheapestDAO priceDAO = new CheapestDAO();
		List<ReceiptItem> receipt = (ArrayList<ReceiptItem>)priceDAO.getReceipt(userID, storeID);
		
		// the receipt is then stored on the session, so that popupReceipt.jsp can access it
		request.getSession().setAttribute("receipt", receipt);
	}

}
