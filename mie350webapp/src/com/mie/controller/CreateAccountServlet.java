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
import com.mie.controller.LoginServlet;

/**
 * Servlet implementation class CreateAccountServlet
 */

public class CreateAccountServlet extends LoginServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// make user object out of parameters sent from login.jsp
		User user = new User(request.getParameter("username"),
				request.getParameter("password"), request.getParameter("email"));

		// check if user exists in database
		UserDAO userDAO = new UserDAO();
		boolean doesUserExist = userDAO.checkUserExists(user);

		// if user DOES exist...
		if (doesUserExist)
			sendToWelcome(request, response, user);

		// if user DOES NOT exist...
		else {

			// put into userAccounts table
			userDAO.createaccount(user);
			
			//if username already exists, some SQL error will come up because username is the primary key
			//username restrictions (min characters or something) can be done in access, if we choose to do that
			//or restrict it within the form itself. We might need a userAlreadyExists.jsp to redirect to at this point

			sendToWelcome(request, response, user);

		}
	}
}
