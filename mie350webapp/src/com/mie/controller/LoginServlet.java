package com.mie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.FoodDAO;
import com.mie.dao.UserDAO;
import com.mie.model.User;

/**
 * Servlet implementation class LoginServlet
 */

// This servlet handles the login process

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// email and password are parameters sent from login.jsp
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// make user object out of parameters sent from login.jsp
		User user = new User(email, password);

		// check if user exists in database via userDAO
		UserDAO userDAO = new UserDAO();
		boolean doesUserExist = userDAO.allowLogin(user);

		// if user exists in database...
		if (doesUserExist) {
			
			// determine if user is returning (i.e. has a previous shopping cart stored)
			// so that a popup can be shown on next page prompting the user to load previous shopping cart
			user.setReturning(userDAO.isReturningUser(user)); 
			//System.out.println("Is the user returning? " + user.isReturning());
			sendToWelcome(request, response, user);
		}

		// if user doesn't exist in database
		else {
			
			// session objects are set so that when redirected back to login.jsp, 
			// the email and password field will be red and shows an error message 
			request.getSession().setAttribute("invalidLogin", true);
			request.getSession().setAttribute("invalidSignin", false);
			
			response.sendRedirect("login.jsp"); // redirect them back to login.jsp
		}

	}

	// This function configures information that is required in the next page, then redirects the user to that page (welcome.jsp)
	public void sendToWelcome(HttpServletRequest request,
			HttpServletResponse response, User user) throws ServletException,
			IOException {
		
		// store userID on session for later use (e.g. retrieving/inserting shopping cart)
		request.getSession().setAttribute("userID", user.getUserID());
		
		// store whether the user is returning; this will decide whether or not to prompt the user to load a previous cart
		request.getSession().setAttribute("isReturningUser", user.isReturning());

		// test check if userID is properly incorporated
		//System.out.println(user.getUserID());

		// get all the suggestions so that the next pages can display them in the search bar
		FoodDAO foodDAO = new FoodDAO();
		List<String> suggestionList = foodDAO.getAllSuggestions();
		
		//categories are: Grains, Meat, Other, Vegetables, Fruits, Dairy
		// get all the item types for each category so that the next pages can display them in the category dropdowns
		List<String> grainItems = foodDAO.getItemTypes("Grains");
		List<String> meatItems = foodDAO.getItemTypes("Meat");
		List<String> veggieItems = foodDAO.getItemTypes("Vegetables");
		List<String> fruitItems = foodDAO.getItemTypes("Fruits");
		List<String> dairyItems = foodDAO.getItemTypes("Dairy");
		List<String> otherItems = foodDAO.getItemTypes("Other");
		

		// make suggestion list, dropdown info, and user object available to all pages via session
		request.getSession().setAttribute("suggestionList", suggestionList);
		request.getSession().setAttribute("grainItems", grainItems);
		request.getSession().setAttribute("meatItems", meatItems);
		request.getSession().setAttribute("veggieItems", veggieItems);
		request.getSession().setAttribute("fruitItems", fruitItems);
		request.getSession().setAttribute("dairyItems", dairyItems);
		request.getSession().setAttribute("otherItems", otherItems);
		
		request.getSession().setAttribute("user", user);
		
		// initializes a boolean, the popup will not be shown;
		// on the next page, if the user is returning, this will then be set to true and allow for the popup to be displayed
		request.getSession().setAttribute("loadPopupShown", false); 

		// redirect to welcome.jsp (Welcome Page with search bar, etc.)
		response.sendRedirect("welcome.jsp");
	}

}
