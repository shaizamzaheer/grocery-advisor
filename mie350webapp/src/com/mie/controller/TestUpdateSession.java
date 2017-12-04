package com.mie.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestUpdateSession
 */

public class TestUpdateSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Integer> nums = (ArrayList<Integer>)request.getSession().getAttribute("nums");
		int num = Integer.parseInt(request.getParameter("num"));
		if (nums == null) 
			nums = new ArrayList<Integer>();
		
		nums.add(num);
		
		request.getSession().setAttribute("nums", nums);
	}

}
