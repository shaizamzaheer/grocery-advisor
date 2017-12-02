<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/displayInlineBlock.css">
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
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>
	
	<%@ include file="navigation.jsp"%>
<h1 id="content-title"><%= session.getAttribute("item_type") %></h1>
	<div id="contents">
	
	<% List<Item> items = (ArrayList<Item>) session.getAttribute("items"); 
	HashMap<Integer, CartItem> shoppingCartDictionary = (HashMap<Integer, CartItem>)session.getAttribute("shoppingCartDictionary");
	String display = "none";
	String deleteClass = "";
	String btnText = "Add";
	int qty = 0;
	
for (Item item : items) { 
	if (shoppingCartDictionary != null && shoppingCartDictionary.containsKey(item.getItemID())) {
		display = "block";
		deleteClass = "delete";
		btnText = "Delete";
		qty = shoppingCartDictionary.get(item.getItemID()).getQuantity();
	}
	
	else if (shoppingCartDictionary != null && !shoppingCartDictionary.containsKey(item.getItemID())) {
		display = "none";
		deleteClass = "";
		btnText = "Add";
		qty = 0;
	}
%>
	
	  <div class="item-container">
	  <div class="item-image">
	    <div class="in-cart-symbol" style="display: <%= display%>;">
	      <i class="material-icons">done</i>
	      <span>In Cart</span>
	    </div>
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
	
	<% } %>
  
</div>
</body>
</html>

