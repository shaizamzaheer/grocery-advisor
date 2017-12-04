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
public class GetStoresWithinRadiusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get lat and lon entered by user from form
		double userLat = Double.parseDouble(request.getParameter("userLat"));
		double userLon = Double.parseDouble(request.getParameter("userLon"));
		
		double radius = Integer.parseInt(request.getParameter("radius")); 
		
		StoreDAO storeDAO = new StoreDAO();
		List<Store> storesWithinRadius = (ArrayList<Store>)storeDAO.getStoresWithinRadius(userLat, userLon, radius);
		
		List<Integer> candidateStoreIDs = new ArrayList<Integer>();
		List<Double> candidateStoreDistances = new ArrayList<Double>();

		for (int i = 0; i < storesWithinRadius.size(); i++) {
			
			//current Store within list
			Store currStore = storesWithinRadius.get(i);
			
			//make list of just storeIDs 
			candidateStoreIDs.add(currStore.getStoreID());
			candidateStoreDistances.add(currStore.getDistance());
		}
		
		//put list of storeIDs on session so that DisplayResultsServlet can use it to filter only relevant stores when getting cheapest stores/prices
		request.getSession().setAttribute("candidateStoreIDs", candidateStoreIDs);
		
		request.getSession().setAttribute("candidateStoreDistances", candidateStoreDistances);
		//response.sendRedirect("gettingDistances.jsp");
		
		if (candidateStoreIDs.isEmpty()) {
			response.sendRedirect("results.jsp");
		}
		
		else {
		RequestDispatcher rd = request.getRequestDispatcher("DisplayResultsServlet");
		rd.forward(request, response);
		}
	}

}
