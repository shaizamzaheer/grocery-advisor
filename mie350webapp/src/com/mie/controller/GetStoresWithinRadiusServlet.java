package com.mie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.StoreDAO;
import com.mie.model.Store;

/**
 * Servlet implementation class GetStoresWithinRadiusServlet
 */
public class GetStoresWithinRadiusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("This servlet was reached"); //THIS PRINTED!
		System.out.println("Printing userLat: " + request.getParameter("userLat")); //THIS PRINTED!
		System.out.println("Printing userLon: " + request.getParameter("userLon")); //THIS PRINTED!
		
		//get lat and lon entered by user from form
		double userLat = Double.parseDouble(request.getParameter("userLat"));
		double userLon = Double.parseDouble(request.getParameter("userLon"));
		
		System.out.println(userLat + ", " + userLon); //THIS PRINTED, meaning successfully converted from String to double (2 lines above)
		
		double radius = 5; //5km radius
		
		StoreDAO storeDAO = new StoreDAO();
		List<Store> storesWithinRadius = storeDAO.getStoresWithinRadius(userLat, userLon, radius);
		
		List<Integer> candidateStoreIDs = new ArrayList<Integer>();
		
		String key = "AIzaSyB2GqdujVRHhAIpGZ5dRbkbzOrbvox711A";
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?&key=" 
				+ key
				+ "&units=metric"
				+ "&origins="
				+ userLat + ", " + userLon
				+ "&destinations=";
		
		for (int i = 0; i < storesWithinRadius.size(); i++) {
			
			//current Store within list
			Store currStore = storesWithinRadius.get(i);
			
			//make list of just storeIDs 
			candidateStoreIDs.add(currStore.getStoreID());
			
			//add destination lat and lon to url
			url += currStore.getLat() + ", " + currStore.getLon();
			
			//if store in list is not the first one or the last one, then add a "pipe" so that more destinations can be allowed in url
			if (i != 0 || i != storesWithinRadius.size()-1)
				url += "|";
		}
		
		//put url on session so that DistanceMatrixAPI can use it when it is called in the next JSP
		request.getSession().setAttribute("distanceMatrixURL", url);
		
		//put list of storeIDs on session so that DisplayResultsServlet can use it to filter only relevant stores when getting cheapest stores/prices
		request.getSession().setAttribute("candidateStoreIDs", candidateStoreIDs);
		
		//response.sendRedirect("gettingDistances.jsp");
		
	}

}
