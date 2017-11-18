<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/displayInlineBlock.css">
<script src="js/addToCart.js"></script>
</head>
<body>
<%@ include file="topbar.jsp" %>
	<%@ include file="navigation.jsp"%>

<% List<Item> items = (ArrayList<Item>) session.getAttribute("items"); 
	
for (Item item : items) { %>
	
	<div class="item" style="width: 250px; display: inline-block; border: 2px solid black; margin: 10px; text-align: center;">
		<div class="itemImg" style="width: 90%; height: 150px; margin: 10px auto; background-color: grey;">
			<img src="" alt="IMAGE!" style="position: relative; top: 40%; "/>
		</div>
		<input type="hidden" value="<%= item.getItemID() %>" />
		<div class="itemInfo" style="margin: auto;"><%= item.getItemName() + ", " + item.getAmount() %></div>
		<input type="number" min="0" value="0" style="margin: 10px auto; width: 50px;"/>
		<input type="button" value="Add To Cart" class="itemBtn" />
		
	</div>

<% } %>
</body>
</html>