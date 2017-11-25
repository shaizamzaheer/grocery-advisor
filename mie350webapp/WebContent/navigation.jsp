<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.mie.model.User" %>
	
	<%	User user = (User)session.getAttribute("user");
		String username = user.getUsername();
	%>
	
<div id="topbar">
  <div id="logo-container"></div>
  <h1 id="website-name">Grocery Advisor</h1>
  <form action="LogoutServlet" id="signout-container"><input type="submit" value="Sign Out"  id="signout-btn"></form>
  <h1 id="user-name"><%= user.getUsername() %></h1>
</div>

<div id="navigation">
  <form action="DisplayItemsServlet" id="item-search-container">
    <input type="text" placeholder="Search items..." id="item-search-bar" name="item_type" list="suggestions">
    <datalist id="suggestions">
      
      <!-- Populate search bar dropdown suggestion list with suggestions -->
      	<%
      	List<String> suggestionList = (ArrayList<String>) session.getAttribute("suggestionList");
      	
      	for (String suggestion : suggestionList) { %>
      		<option> <%= suggestion %> </option>
      	<% } %>
      
      </datalist>
    <button id="item-search-btn"><i class="material-icons">search</i></button>
  </form>
  
  <button id="cart-btn"><i class="material-icons">shopping_cart</i></button>
  
  <ul id="category-btns">
    <li><span>Grain</span><i class="material-icons">expand_more</i></li>
    <li><span>Dairy</span><i class="material-icons">expand_more</i></li>
    <li><span>Fruits</span><i class="material-icons">expand_more</i></li>
    <li><span>Veggies</span><i class="material-icons">expand_more</i></li>
    <li><span>Milk</span><i class="material-icons">expand_more</i></li>
    <li><span>Other</span><i class="material-icons">expand_more</i></li>
  </ul>
</div>
      
<div id="popup">

</div>

<div id="closebtn">X</div>