<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Grocery Advisor - Finalize</title>

<!-- Google API used -->
<!-- script reference to use Places Autocomplete, Google Places Web Service API for location autocomplete; implemented in locationSearch.js -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2GqdujVRHhAIpGZ5dRbkbzOrbvox711A&libraries=places"></script>

<link rel="stylesheet" type="text/css" href="css/popup.css">

<script type="text/javascript" src="js/editCart.js"></script>
<script type="text/javascript" src="js/displayShoppingCart.js"></script>
<link rel="stylesheet" type="text/css" href="css/navigation.css">

<!-- Google Icons used  -->
<!-- shopping cart icon (shopping_cart), check mark icon (done), trashcan icon (delete), magnifying glass icon (search); found in https://www.w3schools.com/icons/google_icons_action.asp -->
<!-- plus icon (add), minus icon (remove); found in https://www.w3schools.com/icons/google_icons_content.asp -->
<!-- walking icon (directions_walk), bike icon (directions_bike), car icon (directions_car), transit icon (directions_transit); found in https://www.w3schools.com/icons/google_icons_maps.asp -->
<!-- dropdown arrow icon (expand_more), X icon (close); found in https://www.w3schools.com/icons/google_icons_navigation.asp -->
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" type="text/css" href="css/items.css">

<script type="text/javascript" src="js/locationSearch.js"></script>
<script type="text/javascript" src="js/finalize.js"></script>
<link rel="stylesheet" type="text/css" href="css/finalize.css">
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
<h1 id="content-title">Finalize</h1>
	<div id="contents">

  <form action="GetStoresWithinRadiusServlet" id="finalize-form" method="post">
    
    <div id="transport-btns">
      <button type="button" id="transport-walk" class="transport-selected"><i class="material-icons">directions_walk</i></button>
      <button type="button" id="transport-bike"><i class="material-icons">directions_bike</i></button>
      <button type="button" id="transport-car"><i class="material-icons">directions_car</i></button>
      <button type="button" id="transport-transit"><i class="material-icons">directions_transit</i></button>
    </div>
    
    <input type="hidden" name="transport-method" value="walk"><!-- value changed via JS depending on which transport-btns above is clicked -->
    
        <div id="input-radius-container">
      <h1>Find stores within: <select name="radius" id="input-radius">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5" selected>5</option>
        <option value="10">10</option>
        <option value="15">15</option>
      </select> km</h1>
    </div>
    
    <!-- Location search bar uses Places Autocomplete, Google Places Web Service API for autocomplete location -->
    <!-- (see locationSearch.js for implementation) -->
    <div id="location-search-container">
    <input type="text" placeholder="Enter your location..." id="location-search-bar">
    	<input type="hidden" id="userLat" name="userLat" value=""/>
	<input type="hidden" id="userLon" name="userLon" value=""/>
    <input type="button" value="Go" id="location-search-btn">
      </div>
    

    
    <h1 id="ask-preference">What do you value more?</h1>
    
    <div id="radio-choices-container">
      <div class="radio-choice">
        <input type="radio" name="preference" value="price" checked>
        <h1>Price?</h1>
      </div>
      
      <div class="radio-choice">
        <input type="radio" name="preference" value="time">
        <h1>Travel Time?</h1>
      </div>
      
      <div class="radio-choice">
        <input type="radio" name="preference" value="both">
        <h1>Both?</h1>
      </div>
      
    </div>
    
    <!-- time value selection/input only displayed when radio button "Both" is selected -->
    <div id="time-value-container" style="display: none;">
      <h1>How much do you value an hour?
      <input type="number" min="1" value="10" id="time-value" name="time-value">
        $</h1>
    </div>
    
  </form>
</div>
</body>
</html>