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
<link rel="stylesheet" type="text/css" href="css/popupStore.css">
<script type="text/javascript" src="js/displayStoreDetails.js"></script>
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
	<div class="popup-store">
      <div class="popup-store-container">
        <h1 class="popup-store-title">Store Details</h1>
        <div class="popup-store-close"><i class="material-icons">close</i></div>

        <div class="popup-store-contents">

            <p class="popup-store-franchise"><%= store.getFranchise() %></p>
          <p class="popup-store-info"><span>Address:</span><span><%= store.getStreetAddress() %></span></p>
          <p class="popup-store-info"><span>Region:</span><span><%= store.getRegion() %>, <%= store.getPostalCode() %></span></p>
          <p class="popup-store-info"><span>Phone:</span><span><%= store.getPhone() %></span></p>
          <p class="popup-store-info"><span>Hours:</span><span>
          <% for (String line : store.getHoursCompact()) { %>
            <span><%= line %></span> <% } %>
            </span></p>

        </div>
      </div>
    </div>
    
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