<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.mie.model.*" %>
	
	<%	User user = (User)session.getAttribute("user");	%>
	
<div id="topbar">
  <div id="logo-container"><div id="logo"></div></div>
  <h1 id="website-name">Grocery Advisor</h1>
  <form action="LogoutServlet" id="signout-container"><input type="submit" value="Sign Out"  id="signout-btn"></form>
  <h1 id="user-name"><%= user.getUsername() %></h1>
  <input type="hidden" value="<%= user.isReturning() %>" id="user-returning"/>
</div>

<div id="navigation">
  <form action="DisplayItemsServlet" id="item-search-container">
    <input type="text" placeholder="Search items..." id="item-search-bar" name="item_type" list="suggestions">
    <datalist id="suggestions">
      
      <!-- Populate search bar dropdown suggestion list with suggestions -->
      	<%
      	List<String> suggestionList = (ArrayList<String>) session.getAttribute("suggestionList");
      	
      	Set<CartItem> shoppingCart = (HashSet<CartItem>)session.getAttribute("shoppingCart"); 
      	int capacity = 0;
      	String displayCapacity = "none";
	  	if (shoppingCart != null && !shoppingCart.isEmpty()) {
	  		capacity = shoppingCart.size();
	  		displayCapacity = "block";
	  	}
      	
      	for (String suggestion : suggestionList) { %>
      		<option> <%= suggestion %> </option>
      	<% } %>
      
      </datalist>
    <button id="item-search-btn"><i class="material-icons">search</i></button>
  </form>
  
  <button id="cart-btn"><i class="material-icons">shopping_cart</i><span style="display:<%= displayCapacity%>"><%=capacity %></span></button>
  
  <!-- Populate each category button with respective dropdown information  -->
  <ul id="category-btns">
    <li><span>Grain</span><i class="material-icons">expand_more</i>
    	<div class="category-dropdown-container">
    	<% List<String> grainItems = (ArrayList<String>)session.getAttribute("grainItems");
    	
    	for (String grainItem : grainItems) { %>
	        <form action="DisplayItemsServlet" class="category-dropdown">
	          <input type="hidden" value="<%= grainItem %>" name="item_type">
	          <input type="submit" value="<%= grainItem %>" class="category-dropdown-content">
	        </form>
	    <% } %>
        </div>
    </li><!--  
    
    --><li><span>Dairy</span><i class="material-icons">expand_more</i>
        <div class="category-dropdown-container">
    	<% List<String> dairyItems = (ArrayList<String>)session.getAttribute("dairyItems");
    	
    	for (String diaryItem : dairyItems) { %>
	        <form action="DisplayItemsServlet" class="category-dropdown">
	          <input type="hidden" value="<%= diaryItem %>" name="item_type">
	          <input type="submit" value="<%= diaryItem %>" class="category-dropdown-content">
	        </form>
	    <% } %>
        </div>
    </li><!--  
    
    --><li><span>Fruits</span><i class="material-icons">expand_more</i>
        <div class="category-dropdown-container">
    	<% List<String> fruitItems = (ArrayList<String>)session.getAttribute("fruitItems");
    	
    	for (String fruitItem : fruitItems) { %>
	        <form action="DisplayItemsServlet" class="category-dropdown">
	          <input type="hidden" value="<%= fruitItem %>" name="item_type">
	          <input type="submit" value="<%= fruitItem %>" class="category-dropdown-content">
	        </form>
	    <% } %>
        </div>
    </li><!--  
    
    --><li><span>Veggies</span><i class="material-icons">expand_more</i>
        <div class="category-dropdown-container">
    	<% List<String> veggieItems = (ArrayList<String>)session.getAttribute("veggieItems");
    	
    	for (String veggieItem : veggieItems) { %>
	        <form action="DisplayItemsServlet" class="category-dropdown">
	          <input type="hidden" value="<%= veggieItem %>" name="item_type">
	          <input type="submit" value="<%= veggieItem %>" class="category-dropdown-content">
	        </form>
	    <% } %>
        </div>
    </li><!--  
    
    --><li><span>Meat</span><i class="material-icons">expand_more</i>
        <div class="category-dropdown-container">
    	<% List<String> meatItems = (ArrayList<String>)session.getAttribute("meatItems");
    	
    	for (String meatItem : meatItems) { %>
	        <form action="DisplayItemsServlet" class="category-dropdown">
	          <input type="hidden" value="<%= meatItem %>" name="item_type">
	          <input type="submit" value="<%= meatItem %>" class="category-dropdown-content">
	        </form>
	    <% } %>
        </div>
    </li><!--  
    
    --><li><span>Other</span><i class="material-icons">expand_more</i>
        <div class="category-dropdown-container">
    	<% List<String> otherItems = (ArrayList<String>)session.getAttribute("otherItems");
    	
    	for (String otherItem : otherItems) { %>
	        <form action="DisplayItemsServlet" class="category-dropdown">
	          <input type="hidden" value="<%= otherItem %>" name="item_type">
	          <input type="submit" value="<%= otherItem %>" class="category-dropdown-content">
	        </form>
	    <% } %>
        </div>
    </li>
    
  </ul>
</div>

<!-- A div to hold the shopping cart popup elements (popup.jsp) when retrieved via AJAX -->      
<div id="popup">

</div>