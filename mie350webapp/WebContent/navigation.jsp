<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" %>

<div id="navigationArea" style="background: grey; padding: 25px;">
  <div class="displayInlineBlock">
    <form action="DisplayItemsServlet"> <!-- DisplayItemsServlet -->
      <input type="text" name="item_type" list="suggestions">
      <datalist id="suggestions">
      
      <!-- Populate search bar dropdown suggestion list with suggestions -->
      	<%
      	List<String> suggestionList = (ArrayList<String>) session.getAttribute("suggestionList");
      	
      	for (String suggestion : suggestionList) { %>
      		<option> <%= suggestion %> </option>
      	<% } %>
      
      </datalist>

      <input type="submit" value="Search">
    </form>
  </div>

  <div class="displayInlineBlock" style="float: right;">
    <form action="FinalizeServlet" method="post"> <!-- FinalizeServlet -->
      <input type="submit" value="Shopping Cart">
    </form>

  </div>

  <br><br>

  <div> <!-- all following actions are DisplayCategoryServlet -->
    <form class="displayInlineBlock" action=""><input type="hidden" value="Grain"><input type="submit" value="Grain"></form>
    <form class="displayInlineBlock" action=""><input type="hidden" value="Dairy"><input type="submit" value="Dairy"></form>
    <form class="displayInlineBlock" action=""><input type="hidden" value="Fruit"><input type="submit" value="Fruit"></form>
    <form class="displayInlineBlock" action=""><input type="hidden" value="Vegetables"><input type="submit" value="Vegetables"></form>
    <form class="displayInlineBlock" action=""><input type="hidden" value="Meat"><input type="submit" value="Meat"></form>
    <form class="displayInlineBlock" action=""><input type="hidden" value="Alternatives"><input type="submit" value="Alternatives"></form>

  </div>
</div>