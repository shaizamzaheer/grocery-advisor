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
		
		int userID = (Integer)request.getSession().getAttribute("userID");
		String travelMethod = request.getParameter("transport-method");
		String preference = request.getParameter("preference");
		Double timeValue = request.getParameter("time-value") == null ? null : Double.parseDouble(request.getParameter("time-value"));
		
		ArrayList<Integer> candidateStoreIDs = (ArrayList<Integer>) request.getSession().getAttribute("candidateStoreIDs");
		ArrayList<Double> candidateStoreDistances = (ArrayList<Double>) request.getSession().getAttribute("candidateStoreDistances");
		
		HashMap<Integer, Double> storeToDistances = new HashMap<Integer, Double>();
		
		for (int i = 0; i < candidateStoreIDs.size(); i++) {
			storeToDistances.put(candidateStoreIDs.get(i), candidateStoreDistances.get(i));
		}
		
		Set<Result> results = new TreeSet<Result>();
		
		CheapestDAO cheapestDAO = new CheapestDAO();
		HashMap<Integer, Double> cheapestStoreIDsAndPrices = cheapestDAO.getCheapestStoreIDsAndPrices(userID, candidateStoreIDs);
		
		StoreDAO storeDAO = new StoreDAO();
		
		//for each storeID, pass to storeDAO.getStoreDetails(int storeID) to get Store object, 
		//then make Result object: 
		//new Result(store object, price (from hashmap i.e. cheapestStoreIDsAndPrice.get(i)), distance (getparameter "dist"+i from request))
		//add to list of Results
		
		
		System.out.println("Candidate StoreIDS: ");
		System.out.println(candidateStoreIDs);
		
		System.out.println("StoreIDsToPrices:");
		System.out.println(cheapestStoreIDsAndPrices);
		
		
		//for (int i = 0; i < candidateStoreIDs.size(); i++) {
		for (Integer i : cheapestStoreIDsAndPrices.keySet()) {
			
			System.out.println("ID: " + i);
			
			//Store storeDetails = storeDAO.getStoreDetails(candidateStoreIDs.get(i));
			Store storeDetails = storeDAO.getStoreDetails(i);
			
			System.out.println(storeDetails.getFranchise() + ", " + storeDetails.getStoreID());
			
			//double price = cheapestStoreIDsAndPrices.get(candidateStoreIDs.get(i));
			double price = cheapestStoreIDsAndPrices.get(i);
			
			//double distance = Double.parseDouble(request.getParameter("dist"+i));
			
			double distance = storeToDistances.get(i);
			
			Result result = new Result(storeDetails, price, distance, travelMethod, preference, timeValue);
			
			results.add(result);
		}
		
		//put list of results on session so that results.jsp can access it
		request.getSession().setAttribute("results", results);
		
		response.sendRedirect("results.jsp");
	
	}

}
