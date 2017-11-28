<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.mie.model.*, java.sql.Time"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results</title>
<link rel="stylesheet" type="text/css" href="css/displayInlineBlock.css">

<link rel="stylesheet" type="text/css" href="css/popup.css">

<script type="text/javascript" src="js/editCart.js"></script>
<script type="text/javascript" src="js/displayShoppingCart.js"></script>
<link rel="stylesheet" type="text/css" href="css/navigation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" type="text/css" href="css/items.css">
<link rel="stylesheet" type="text/css" href="css/results.css">
</head>
<body>

	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	
	session.setAttribute("resultsVisited", true);
	%>
	
		<%@ include file="navigation.jsp"%>
<h1 id="content-title">Results</h1>
	<div id="contents">
	
	
	<% 
	Set<Result> results = (TreeSet<Result>)session.getAttribute("results"); 
	int count = 1;
	for (Result result : results) { 
		Store store = result.getStoreDetails();
	%>
	
	<div class="result-container">
	    <span class="result-number"><%= count %>.</span><!--  
	    --><div>
		    <span class="result-store"><%= store.getFranchise() %> (<%= store.getStreetAddress() %>)</span>
		    <span class="result-cost">Total Cost: $<%= result.getPrice() %></span>
		    <span class="result-time">ETA: <%= result.getETA() %> min</span>
	    </div>
  	</div>
  	
	<% count++;
	} %>
	
</div>
</body>
</html>