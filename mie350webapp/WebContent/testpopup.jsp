<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
  <div id='toDisplay'>
  	<% List<Integer> nums = (ArrayList<Integer>)session.getAttribute("nums"); 
  	if (nums != null) {
  	for (Integer num : nums) { %>
  	<%= num %><br>
  	<% } }%>
  </div>


