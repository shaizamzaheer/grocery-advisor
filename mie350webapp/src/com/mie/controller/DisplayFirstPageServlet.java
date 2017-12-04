package com.mie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayFirstPageServlet
 */

// This is a 'dummy' servlet that is used to redirect the browser to login.jsp when "http://localhost:8080/mie350webapp" is entered as the url,
// because copying and pasting http://localhost:8080/mie350webapp does not automatically go to http://localhost:8080/mie350webapp/login.jsp
// "login.jsp" needs to be in the url so that cache-control is managed

public class DisplayFirstPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("login.jsp");
	}

}
