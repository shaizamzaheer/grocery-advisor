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
public class GetReceiptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userID = (Integer)request.getSession().getAttribute("userID");
		int storeID = Integer.parseInt(request.getParameter("storeID"));
		
		CheapestDAO priceDAO = new CheapestDAO();
		
		List<ReceiptItem> receipt = (ArrayList<ReceiptItem>)priceDAO.getReceipt(userID, storeID);
		
		request.getSession().setAttribute("receipt", receipt);
	}

}
