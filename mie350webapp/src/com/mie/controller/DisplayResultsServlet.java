package com.mie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.CheapestDAO;
import com.mie.dao.StoreDAO;
import com.mie.model.Result;
import com.mie.model.Store;

/**
 * Servlet implementation class DisplayResultsServlet
 */
public class DisplayResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		ArrayList<Integer> candidateStoreIDs = (ArrayList<Integer>) request.getSession().getAttribute("candidateStoreIDs");
		Set<Result> results = new TreeSet<Result>();
		
		CheapestDAO cheapestDAO = new CheapestDAO();
		HashMap<Integer, Double> cheapestStoreIDsAndPrices = cheapestDAO.getCheapestStoreIDsAndPrices(candidateStoreIDs);
		
		StoreDAO storeDAO = new StoreDAO();
		
		//for each storeID, pass to storeDAO.getStoreDetails(int storeID) to get Store object, 
		//then make Result object: 
		//new Result(store object, price (from hashmap i.e. cheapestStoreIDsAndPrice.get(i)), distance (getparameter "dist"+i from request))
		//add to list of Results
		
		for (int i = 0; i < candidateStoreIDs.size(); i++) {
			Store storeDetails = storeDAO.getStoreDetails(candidateStoreIDs.get(i));
			double price = cheapestStoreIDsAndPrices.get(candidateStoreIDs.get(i));
			double distance = Double.parseDouble(request.getParameter("dist"+i));
			Result result = new Result(storeDetails, price, distance);
			results.add(result);
		}
		
		//put list of results on session so that results.jsp can access it
		request.getSession().setAttribute("results", results);
		
		response.sendRedirect("results.jsp");
	
	}

}
