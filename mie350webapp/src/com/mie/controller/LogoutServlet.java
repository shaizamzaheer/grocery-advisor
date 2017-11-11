package com.mie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	/**
	 * This class handles all aspects of the logout action.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * Invalidate the current member's session and set its status to false.
		 */
		HttpSession session = request.getSession(false);
		session.invalidate();

		response.sendRedirect("login.jsp");
	}
}