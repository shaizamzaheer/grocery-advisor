<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.mie.model.*, java.sql.Time"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Grocery Advisor - Results</title>

<link rel="stylesheet" type="text/css" href="css/popup.css">

<script type="text/javascript" src="js/editCart.js"></script>
<script type="text/javascript" src="js/displayShoppingCart.js"></script>
<link rel="stylesheet" type="text/css" href="css/navigation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" type="text/css" href="css/items.css">
<link rel="stylesheet" type="text/css" href="css/results.css">
<link rel="stylesheet" type="text/css" href="css/popupStore.css">
<script type="text/javascript" src="js/displayStoreDetails.js"></script>

<link rel="stylesheet" type="text/css" href="css/popupReceipt.css">
<script type="text/javascript" src="js/displayReceipt.js"></script>
</head>
<body>

	<%
	// this prevents this page to be cached; thus, after logging out, can't be accessed by "back" button
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	// if a user didn't log in (i.e. user is null), then this page cannot be accessed (by url) so redirect to login.jsp
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>
	
		<%@ include file="navigation.jsp"%> <!-- Fixed top area -->
<h1 id="content-title">Results</h1>
	<div id="contents">
	
	
	<% 
	Set<Result> results = (TreeSet<Result>)session.getAttribute("results"); 
	
	// if results is not null and not empty, then that means there are results to display
	if (results != null && !results.isEmpty()) {
	int count = 1; //count used to number the ranked stores
	for (Result result : results) { 
		Store store = result.getStoreDetails();
	%>
	
	<!-- Each store is associated with a popup which will show the store details -->
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
    
    <!-- Each result is contained within a "result-container", showing the number/rank, the store franchise with address, price and ETA -->
	<div class="result-container">
	    <span class="result-number"><%= count %>.</span><!--  
	    --><div>
		    <span class="result-store"><%= store.getFranchise() %> (<%= store.getStreetAddress() %>)</span><!-- clicking this will show the store details popup -->
		    <span class="result-cost" id="<%= store.getStoreID() %>">Total Cost: $<%= result.getPrice() %></span><!-- clicking this will show the receipt popup -->
		    <span class="result-time">ETA: <%= result.getETA() %> min</span>
	    </div>
  	</div>
  	
	<% count++;
	} //end of for-loop
	} //end of if 
	
	// if results is null, then that means no stores were found within the radius specified (see near bottom of GetStoresWithinRadiusServlet, and DisplayResultsServlet)
	else if (results == null) { %>
	
	<h1 class="no-store-message">Sorry, we couldn't find any stores that are within the radius you specified.</h1>
	
	<% } 
	
	// if results is not null, but is empty, then that means there WERE stores that were within the radius, 
	// but they were all filtered out because they either didn't have ALL the user's items, or they didn't have enough of an item(s) in stock.
	else if (results != null && results.isEmpty()) { %>
	
	<h1 class="no-store-message">Sorry, we couldn't find any stores that had all (or enough of) the items you were looking for.</h1>
	
	<% } %>
	
</div>

<!-- A div to hold the popup receipt elements (popupReceipt.jsp) when retrieved via AJAX -->
<div id="popup-receipt">

</div>

</body>
</html>