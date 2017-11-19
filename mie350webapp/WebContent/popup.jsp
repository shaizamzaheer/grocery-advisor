<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, com.mie.model.*"%>
<div id='toDisplay'>
	<%
		Set<CartItem> shoppingCart = (TreeSet<CartItem>)session.getAttribute("shoppingCart"); 
	  	if (shoppingCart != null) {
	  		
			for (CartItem item : shoppingCart) { 
		  	String btnContent;	
		  	if (item.isInCart()) {
		  		btnContent = "In";
		  	}
		  	else {
		  		btnContent = "Out";	
		  	}
		%>
		<button style="display: inline; margin: 30px;" class="inOutBtn" id="<%=item.getItemID()%>" value="<%= item.isInCart() %>"><%=btnContent %></button>

		<div style="display: inline; margin: 30px;" class="popupItemName">
			<%=item.getItemName()%>
		</div>

		<input style="display: inline; width: 30px; margin: 30px;" type="number" value="<%=item.getQuantity()%>" min="1" class="popupItemQuantity" id="<%=item.getItemID()%>"/>
		
		<br>
		<%	} %>
		
		<form action="InsertCartServlet">
			<input type="submit" value="Confirm" />
		</form>
		<% }

		else { %>

	<div>Shopping Cart is empty...</div>
	<% } %>
</div>
