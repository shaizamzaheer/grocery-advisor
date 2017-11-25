<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>

<link rel="stylesheet" type="text/css" href="css/displayInlineBlock.css">
<link rel="stylesheet" type="text/css" href="css/popup.css">
<link rel="stylesheet" type="text/css" href="css/topbar.css">
<script type="text/javascript" src="js/displayShoppingCart.js"></script>
</head>
<body>
	
	<%
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>
	
	<%@ include file="topbar.jsp" %>
	<%@ include file="navigation.jsp"%>
	<%	User user1 = (User)session.getAttribute("user");
		String username1 = user1.getUsername();
	%>
	<h1 style="margin: 20px;">Welcome, <%= username %> !</h1>
</body>
</html>