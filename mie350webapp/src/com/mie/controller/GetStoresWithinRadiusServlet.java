package com.mie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.StoreDAO;
import com.mie.model.Store;

/**
 * Servlet implementation class GetStoresWithinRadiusServlet
 */

// This servlet takes care of getting all the stores (with the corresponding distances) that are within a specified radius from the user's location

public class GetStoresWithinRadiusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get user's lat, user's lon, and preferred radius parameters from finalize.jsp 
		// user's lat/lon is passed via Javascript, by using the Places Autcomplete, Google Places Web Service API
		double userLat = Double.parseDouble(request.getParameter("userLat"));
		double userLon = Double.parseDouble(request.getParameter("userLon"));
		double radius = Integer.parseInt(request.getParameter("radius")); 
		
		// details about the stores' geography (i.e. storeID, store's lat, store's lon, and distance from the user's lat/lon) is read from database
		// via storeDAO, and stored as a list of Store objects
		StoreDAO storeDAO = new StoreDAO();
		List<Store> storesWithinRadius = (ArrayList<Store>)storeDAO.getStoresWithinRadius(userLat, userLon, radius);
		
		// a separate list of storeIDs is extracted from the list of Store objects above for convenience when querying 
		// to get stores and corresponding prices in next servlet (DisplayResultsServlet)
		// "WHERE StoreID IN (#, #, #)" <- when querying, it's easy to transform a List<Integer> into a string enclosed by brackets
		// because [#, #, #] is the default string, so using .replace("[", "(") is very convenient and easy!
		List<Integer> candidateStoreIDs = new ArrayList<Integer>();
		
		// since storeIDs are extracted and put in a separate list, and distances are required as well,
		// the distances are also extracted and put in a list; thus, the mapping is reserved by index.
		List<Double> candidateStoreDistances = new ArrayList<Double>(); 

		for (int i = 0; i < storesWithinRadius.size(); i++) {
			
			Store currStore = storesWithinRadius.get(i); //current Store within list
			
			candidateStoreIDs.add(currStore.getStoreID()); //make list of just storeIDs
			candidateStoreDistances.add(currStore.getDistance()); //make list of just distances, which maps by index to storeIDs
		}
		
		// put list of storeIDs on session so that DisplayResultsServlet can use it when getting stores and corresponding prices
		// to filter out stores that have insufficient inventory
		request.getSession().setAttribute("candidateStoreIDs", candidateStoreIDs);
		
		// put list of distances on session so that DisplayResultsServlet can use it to make Result objects for each storeID
		request.getSession().setAttribute("candidateStoreDistances", candidateStoreDistances);
		
		// if candidateStoreIDs is empty, that means storesWithinRadius is empty, meaning no stores were found within the specified radius
		// user is redirected to results.jsp
		// since candidateStoreIDs is now on the session, when results.jsp finds out that it's empty, it will display the appropriate message
		if (candidateStoreIDs.isEmpty()) {
			response.sendRedirect("results.jsp");
		}
		
		// if candidateStoreIDs is not empty, that means that there are indeed some stores found within the specified radius
		// the process is forwarded to DisplayResultsServlet which will take care of filtering out the storeIDs and figuring out the results.
		else {
		RequestDispatcher rd = request.getRequestDispatcher("DisplayResultsServlet");
		rd.forward(request, response);
		}
	}

}
