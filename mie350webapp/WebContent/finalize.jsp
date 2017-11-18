<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.model.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2GqdujVRHhAIpGZ5dRbkbzOrbvox711A&libraries=places"></script>
<script src="js/locationSearch.js"></script>
</head>

<body>

	<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>

<table>
<tr>
<th>Info</th>
<th>Qty</th>
</tr>

<%
	List<CartItem> shoppingCart = (ArrayList<CartItem>)session.getAttribute("shoppingCart");
	for (CartItem cartItem : shoppingCart) { %>
		
		<tr>
		<th><%= cartItem.getItemName() %></th>
		<th><%= cartItem.getQuantity() %></th>
		</tr>
		
	<% } %>

</table>

<form action="GetStoresWithinRadiusServlet" method="get" id="form">

	<input type="text" id="searchBar" style="width: 50%"/>
	<input type="text" id="userLat" name="userLat" value=""/>
	<input type="text" id="userLon" name="userLon" value=""/>
	<input type="button" value="Confirm" id="submitButton"/>
</form>

</body>
</html>