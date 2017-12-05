<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Grocery Advisor - Items</title>
<link rel="stylesheet" type="text/css" href="css/popup.css">
<script type="text/javascript" src="js/addToCart.js"></script>
<script type="text/javascript" src="js/editCart.js"></script>
<script type="text/javascript" src="js/displayShoppingCart.js"></script>
<link rel="stylesheet" type="text/css" href="css/navigation.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" type="text/css" href="css/items.css">
<script src="js/itemsChangeQuantity.js"></script>
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
	
<h1 id="content-title"><%= session.getAttribute("item_type") %></h1>
	<div id="contents">
	
	<% List<Item> items = (ArrayList<Item>) session.getAttribute("items"); 
	
	// if "items" is not empty, means there are items to display
	// but they must be displayed correctly 
	// if it's in cart, it should be displayed with "Delete" button, not "Add", and should show "In Cart" symbol
	// if it's not in cart, it should be dipslayed with "Add" button, and not show "in Cart" symbol
	if (!items.isEmpty()) {
	HashMap<Integer, CartItem> shoppingCartDictionary = (HashMap<Integer, CartItem>)session.getAttribute("shoppingCartDictionary");
	String display = "none"; //used to display/not display "In Cart" symbol
	String deleteClass = ""; //either blank, or "delete", styles the button to be white or red respectively
	String btnText = "Add"; //either "Add" or "Delete"
	int qty = 0;
	
for (Item item : items) { 
	
	// if the item IS in cart...
	if (shoppingCartDictionary != null && shoppingCartDictionary.containsKey(item.getItemID())) {
		display = "block"; //then display "In Cart" symbol
		deleteClass = "delete"; //button should be styled red
		btnText = "Delete"; //button text should say "Delete"
		qty = shoppingCartDictionary.get(item.getItemID()).getQuantity(); //the quantity should be how it is in the cart
	}
	
	// if the item IS NOT in cart...
	else if (shoppingCartDictionary != null && !shoppingCartDictionary.containsKey(item.getItemID())) {
		display = "none"; //don't display "In Cart" symbol
		deleteClass = ""; //button styled default (white)
		btnText = "Add"; //button text says "Add"
		qty = 0; //quantity is default (0)
	}
%>
	
	  <div class="item-container">
	  <div class="item-image">
	    <div class="in-cart-symbol" style="display: <%= display%>;">
	      <i class="material-icons">done</i>
	      <span>In Cart</span>
	    </div>
	    
	    <!-- Image for each item is displayed; each image was retrieved from walmart.ca -->
	  	<img src="img/<%= item.getItemID()%>.png" style="max-width: 100%; max-height: 100%; border-radius: 10px; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	  </div>
	  <input type="hidden" value="<%= item.getItemID() %>" />
	  <p class="item-info"><span class="item-name"><%= item.getItemName()%></span>, <span class="item-amount"><%=item.getAmount() %></span></p>
	  <div class="quantity-control">
	    <button class="quantity-dec"><i class="material-icons">remove</i></button>
	    <input type="text" class="quantity" value="<%= qty %>" disabled>
	    <button class="quantity-inc"><i class="material-icons">add</i></button>
	  </div>
	
	  <button type="button" class="item-btn <%= deleteClass%>"><%= btnText %></button>
	</div>
	
	<% } //end of for
	} //end of if
	
	//if "items" IS empty, then display appropriate message
	else { %>
	
	<h1 id="no-items-found">Sorry, we couldn't find any items for the item type: "<%=session.getAttribute("item_type")  %>".
	<br><br>Please use the categories or the search bar's autocomplete feature to help you find what you are looking for.</h1>
	
	<% } %>
  
</div>
</body>
</html>

