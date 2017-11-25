<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.User" %>

	<%	User user = (User)session.getAttribute("user");
		String username = user.getUsername();
	%>

<div id="topbar">
  <div id="logo-container"></div>
  <h1 id="website-name">Grocery Advisor</h1>
  <form action="LogoutServlet" id="signout-container"><input type="submit" value="Sign Out"  id="signout-btn"></form>
  <h1 id="user-name"><%= user.getUsername() %></h1>

</div>