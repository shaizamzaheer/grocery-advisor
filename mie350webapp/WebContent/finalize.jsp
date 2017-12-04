<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Grocery Advisor - Finalize</title>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2GqdujVRHhAIpGZ5dRbkbzOrbvox711A&libraries=places"></script>

<link rel="stylesheet" type="text/css" href="css/popup.css">

<script type="text/javascript" src="js/editCart.js"></script>
<script type="text/javascript" src="js/displayShoppingCart.js"></script>
<link rel="stylesheet" type="text/css" href="css/navigation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" type="text/css" href="css/items.css">

<script type="text/javascript" src="js/locationSearch.js"></script>
<script type="text/javascript" src="js/finalize.js"></script>
<link rel="stylesheet" type="text/css" href="css/finalize.css">
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

  <form action="GetStoresWithinRadiusServlet" id="finalize-form" method="post">
    
    <div id="transport-btns">
      <button type="button" id="transport-walk" class="transport-selected"><i class="material-icons">directions_walk</i></button>
      <button type="button" id="transport-bike"><i class="material-icons">directions_bike</i></button>
      <button type="button" id="transport-car"><i class="material-icons">directions_car</i></button>
      <button type="button" id="transport-transit"><i class="material-icons">directions_transit</i></button>
    </div>
    
    <input type="hidden" name="transport-method" value="walk">
    
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
    
    <div id="time-value-container" style="display: none;">
      <h1>How much do you value an hour?
      <input type="number" min="1" value="10" id="time-value" name="time-value">
        $</h1>
    </div>
    
  </form>
</div>
</body>
</html>