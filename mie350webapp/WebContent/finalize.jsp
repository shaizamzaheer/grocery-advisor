<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2GqdujVRHhAIpGZ5dRbkbzOrbvox711A&libraries=places"></script>
<script src="js/locationSearch.js"></script>

<link rel="stylesheet" type="text/css" href="css/displayInlineBlock.css">

<link rel="stylesheet" type="text/css" href="css/popup.css">

<script type="text/javascript" src="js/editCart.js"></script>
<script type="text/javascript" src="js/displayShoppingCart.js"></script>
<link rel="stylesheet" type="text/css" href="css/navigation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" type="text/css" href="css/items.css">
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
<h1 id="content-title">Finalize</h1>
	<div id="contents">

<form action="GetStoresWithinRadiusServlet" method="get" id="form">

	<input type="text" id="searchBar" style="width: 50%"/>
	<input type="text" id="userLat" name="userLat" value=""/>
	<input type="text" id="userLon" name="userLon" value=""/>
	<input type="button" value="Confirm" id="submitButton"/>
</form>

</div>
</body>
</html>