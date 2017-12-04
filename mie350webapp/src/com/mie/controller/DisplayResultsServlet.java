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

// This servlet takes care of getting, ranking, and passing along information about the results (i.e. store details, cost, ETA)

public class DisplayResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// userID will be required to manipulate the ShoppingList data (i.e. access the user's personal shopping cart)
		int userID = (Integer)request.getSession().getAttribute("userID");
		
		// Parameters sent from finalize.jsp
		// transport-method will be used to calculate ETA
		// preference and timeValue will be used to rank the stores based on price, time, or both (if both, then time-value is used as well)
		String travelMethod = request.getParameter("transport-method");
		String preference = request.getParameter("preference");
		Double timeValue = request.getParameter("time-value") == null ? null : Double.parseDouble(request.getParameter("time-value"));
		
		// get list of storeIDs (that were within the user's specified radius) and corresponding distances made in GetStoresWithinRadiusServlet
		// ArrayLists used so that storeIDs and corresponding distances can be mapped by index
		ArrayList<Integer> candidateStoreIDs = (ArrayList<Integer>) request.getSession().getAttribute("candidateStoreIDs");
		ArrayList<Double> candidateStoreDistances = (ArrayList<Double>) request.getSession().getAttribute("candidateStoreDistances");
		
		// A HashMap is used to map the list of storeIDs and the corresponding distances into one data structure 
		// (this could've been done in GetStoresWithinRadiusServlet)
		HashMap<Integer, Double> storeToDistances = new HashMap<Integer, Double>();
		
		for (int i = 0; i < candidateStoreIDs.size(); i++) {
			storeToDistances.put(candidateStoreIDs.get(i), candidateStoreDistances.get(i));
		}
		
		// The reason for this mapping is because some storeIDs may be filtered out because of insufficient inventory,
		// and accessing a HashMap (that maps storeIDs to prices) with filtered out storeIDs would throw a NullPointerException
		
		// Set of results objects stored in a TreeSet, to automatically rank them based on user's preference (price, time, both)
		Set<Result> results = new TreeSet<Result>();
		
		// Each storeID (that isn't filtered out due to insufficient inventory) and the corresponding price of the user's cart
		// is mapped and stored as a HashMap via cheapestDAO
		CheapestDAO cheapestDAO = new CheapestDAO();
		HashMap<Integer, Double> cheapestStoreIDsAndPrices = cheapestDAO.getCheapestStoreIDsAndPrices(userID, candidateStoreIDs);
		
		// For each storeID in cheapestStoreIDsAndPrices, storeDAO will get the corresponding store details and store them in Store objects
		StoreDAO storeDAO = new StoreDAO();		
		
		for (Integer i : cheapestStoreIDsAndPrices.keySet()) {
			
			//System.out.println("ID: " + i);
			//Store storeDetails = storeDAO.getStoreDetails(candidateStoreIDs.get(i));
			
			// for storeID i, gets its Franchise, Street Address, Region, Postal Code, Hours and also formats the hours
			Store storeDetails = storeDAO.getStoreDetails(i); 
			
			//System.out.println(storeDetails.getFranchise() + ", " + storeDetails.getStoreID());
			//double price = cheapestStoreIDsAndPrices.get(candidateStoreIDs.get(i));
			
			// for storeID i, gets corresponding price
			double price = cheapestStoreIDsAndPrices.get(i);
			
			//double distance = Double.parseDouble(request.getParameter("dist"+i));
			
			// for storeID i, gets corresponding straight-line distance
			double distance = storeToDistances.get(i);
			
			// finally, a Result object is made out of the store details, price, and distance, 
			// as well as the user's choice of travel and ranking preference
			// distance will be converted to a time (ETA) using travelMethod
			// and the results will be ranked using preference and/or timeValue
			Result result = new Result(storeDetails, price, distance, travelMethod, preference, timeValue);
			
			results.add(result);
		}
		
		//System.out.println(results);
		
		//put set of results on session so that results.jsp can access it
		request.getSession().setAttribute("results", results);
		
		response.sendRedirect("results.jsp");
	
	}

}
