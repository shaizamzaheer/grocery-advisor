<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Grocery Advisor - Home</title>

<link rel="stylesheet" type="text/css" href="css/popup.css">
<script type="text/javascript" src="js/addToCart.js"></script>
<script type="text/javascript" src="js/editCart.js"></script>
<script type="text/javascript" src="js/displayShoppingCart.js"></script>
<link rel="stylesheet" type="text/css" href="css/navigation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" type="text/css" href="css/items.css">
<link rel="stylesheet" type="text/css" href="css/welcome.css">

<script src="js/loadCart.js"></script>
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
<% if (shoppingCart == null || shoppingCart.isEmpty()) { %>
	 <h1 id="welcome-message">
	 Looks like your list is empty. 
	 Use the categories or search for some groceries to make a shopping list. 
	 When you've added all your items, confirm your shopping list. 
	 Next, enter your location and proceed to get a ranked list of the best stores to shop at.
	 </h1>
	 <% } 
	 
	 else if (shoppingCart != null && !shoppingCart.isEmpty()) { %>
	 <h1 id="welcome-message">
	 Great, your list has some items in it!
	 Keep browsing for more items, or if you've added all your items, confirm your shopping list. 
	 Next, enter your location and proceed to get a ranked list of the best stores to shop at.
	 </h1>
	 <% } %>
</div>

<% if (!((Boolean)session.getAttribute("loadPopupShown")) && user.isReturning()) { %>
<div id="popup-load">
  <div id="popup-load-container">
    <h1 id="popup-cart-title">Load Previous Cart?</h1>
    <div id="popup-load-close"><i class="material-icons">close</i></div>

    <h1 id="popup-load-contents">
      Welcome back, <%= user.getUsername() %>! Would you like to load your previous cart and continue from where you left off? 
    </h1>

    <div id="popup-load-btns">
      <button id="popup-load-no" class="popup-load-btn"><i class="material-icons">close</i>  <span>No</span></button>

      <form action="LoadCartServlet" method="post">
        <button id="popup-load-yes" class="popup-load-btn"><i class="material-icons">done</i>  <span>Yes</span></button>
      </form>
    </div>
  </div>
</div>
    
<% 
session.setAttribute("loadPopupShown", true);
}
	
%>
</body>
</html>