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

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// make user object out of parameters sent from login.jsp
		User user = new User(email, password);

		// check if user exists in database
		UserDAO userDAO = new UserDAO();
		boolean doesUserExist = userDAO.allowLogin(user);

		if (doesUserExist) {
			
			//user exists
			user.setReturning(userDAO.isReturningUser(user)); //determine if user is returning, so that a popup can be shown on next page
			System.out.println("Is the user returning? " + user.isReturning());
			sendToWelcome(request, response, user);
		}

		else {
			// redirect them back to Log In page
			response.sendRedirect("login.jsp");
		}

	}

	public void sendToWelcome(HttpServletRequest request,
			HttpServletResponse response, User user) throws ServletException,
			IOException {
		
		// store userID on session for later use
		request.getSession().setAttribute("userID", user.getUserID());
		
		request.getSession().setAttribute("isReturningUser", user.isReturning());

		// test check if userID is properly incorporated
		System.out.println(user.getUserID());

		// get all the suggestions so that the next page can use it
		FoodDAO foodDAO = new FoodDAO();
		List<String> suggestionList = foodDAO.getAllSuggestions();
		
		//categories are: Grains, Meat, Other, Vegetables, Fruits, Dairy
		List<String> grainItems = foodDAO.getItemTypes("Grains");
		List<String> meatItems = foodDAO.getItemTypes("Meat");
		List<String> veggieItems = foodDAO.getItemTypes("Vegetables");
		List<String> fruitItems = foodDAO.getItemTypes("Fruits");
		List<String> dairyItems = foodDAO.getItemTypes("Dairy");
		List<String> otherItems = foodDAO.getItemTypes("Other");
		

		// make suggestion list and user object available to all pages
		request.getSession().setAttribute("suggestionList", suggestionList);
		request.getSession().setAttribute("grainItems", grainItems);
		request.getSession().setAttribute("meatItems", meatItems);
		request.getSession().setAttribute("veggieItems", veggieItems);
		request.getSession().setAttribute("fruitItems", fruitItems);
		request.getSession().setAttribute("dairyItems", dairyItems);
		request.getSession().setAttribute("otherItems", otherItems);
		
		request.getSession().setAttribute("user", user);

		// redirect to welcome.jsp (Welcome Page with search bar, etc.)
		response.sendRedirect("welcome.jsp");
	}

}
