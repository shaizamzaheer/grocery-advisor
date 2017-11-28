<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>

<link rel="stylesheet" type="text/css" href="css/popup.css">
<script type="text/javascript" src="js/addToCart.js"></script>
<script type="text/javascript" src="js/editCart.js"></script>
<script type="text/javascript" src="js/displayShoppingCart.js"></script>
<link rel="stylesheet" type="text/css" href="css/navigation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" type="text/css" href="css/items.css">
<link rel="stylesheet" type="text/css" href="css/welcome.css">
</head>
<body>
	
	<%
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>
	
	<%@ include file="navigation.jsp"%>
	
<h1 id="content-title">Home</h1>
<div id="contents">
	 <h1 id="empty-message">
	 Looks like your list is empty. 
	 Use the categories or search for some groceries to make a shopping list. 
	 When you've added all your items, confirm your shopping list. 
	 Next, enter your location and proceed to get a ranked list of the best stores to shop at.
	 </h1>
</div>
</body>
</html>