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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//make user object out of parameters sent from login.jsp
		User user = new User(request.getParameter("username"), request.getParameter("password"));
		
		//check if user exists in database
		UserDAO userDAO = new UserDAO();
		boolean doesUserExist = userDAO.checkUserExists(user);
		
		//if user DOES exist...
		if (doesUserExist) {
			
			request.getSession().setAttribute("userID", user.getUserID()); //store userID on session for later use
			System.out.println(user.getUserID()); //test check if userID is properly incorporated
			
			//get all the suggestions so that the next page can use it
			FoodDAO foodDAO = new FoodDAO();
			List<String> suggestionList = foodDAO.getAllSuggestions();
			
			//make suggestion list and user object available to all pages
			request.getSession().setAttribute("suggestionList", suggestionList);
			request.getSession().setAttribute("user", user);
			
			//redirect to welcome.jsp (Welcome Page with search bar, etc.)
			response.sendRedirect("welcome.jsp");
		}
		
		//if user DOES NOT exist...
		else {
			
			//redirect them back to Log In page
			response.sendRedirect("login.jsp");
		}
	
	}

}
