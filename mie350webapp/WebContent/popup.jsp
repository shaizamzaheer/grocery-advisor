<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, com.mie.model.*"%>

<div id="popup-cart">
    <h1 id="popup-cart-title">Current Shopping Cart</h1>
    <div id="popup-close-btn"><i class="material-icons">close</i></div>

    <div id="popup-cart-contents">
    <%
		Set<CartItem> shoppingCart = (HashSet<CartItem>)session.getAttribute("shoppingCart"); 
    
    	// if shopping cart is not empty, then populate with cart items info (option to delete, item name and amount, and option to change quantity)
	  	if (shoppingCart != null && !shoppingCart.isEmpty()) {
	  		
			for (CartItem item : shoppingCart) { 

		%>
		
      <div class="popup-cart-item">
        <button class="popup-cart-delete" id="<%=item.getItemID()%>"><i class="material-icons">delete</i></button>
        <p class="popup-item-info"><span class="popup-item-name"><%= item.getItemName()%></span>, <span class="popup-item-amount"><%=item.getAmount() %></span></p>
        <div class="popup-quantity-control">
          <button class="quantity-dec"><i class="material-icons">remove</i></button>
          <input type="text" class="quantity" value="<%=item.getQuantity()%>" id="<%=item.getItemID()%>" disabled>
          <button class="quantity-inc"><i class="material-icons">add</i></button>
        </div>
      </div>
      
      <% } //end of for loop
			
		} //end of if not null
	  	
		// if shopping cart IS empty, display appropriate message
		else { %>

	<div id="popup-cart-empty">Your shopping cart is empty...</div>
	<% } //end of else (i.e. is null) %>
	
	
	</div>
	
	<% 
		// if shopping cart is not empty, then give the user options (buttons) to clear entire shopping cart or confirm shopping cart
		if (shoppingCart != null && !shoppingCart.isEmpty()) { %>
		<div id="popup-cart-btns">
      <button id="popup-cart-clearall" class="popup-cart-btn"><i class="material-icons">delete</i>  <span>Clear All</span></button>

      <form action="InsertCartServlet">
        <button id="popup-cart-next" class="popup-cart-btn"><i class="material-icons">done</i>  <span>Next</span></button>
      </form>
    </div>
	<% } %>
	
	</div>