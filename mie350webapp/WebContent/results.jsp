<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.mie.model.*, java.sql.Time"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/displayInlineBlock.css">
</head>
<body>

	<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>
	
		<%@ include file="topbar.jsp" %>
	
	
	<% 
	Set<Result> results = (TreeSet<Result>)session.getAttribute("results"); 
	
	for (Result result : results) { 
		Store store = result.getStoreDetails();
	%>
	<table>
	<tr>
	<th>Store:</th>
	<td><%= store.getFranchise() %></td>
	</tr>
	
	<tr>
	<th>Location:</th>
	<td><%= store.getStreetAddress() %>, <%= store.getRegion() %>, <%= store.getPostalCode() %></td>
	</tr>
	
	<tr>
	<th>Phone:</th>
	<td><%= store.getPhone() %></td>
	</tr>
	
	<tr>
	<th>Hours:</th>
	<td></td>
	</tr>
	
	<% HashMap<String, Time[]> hours = store.getHours(); 
		
		for(String day : hours.keySet()) { %>
			
			<tr>
				<td><%= day %></td>
				<td><%= hours.get(day)[0] %> to <%= hours.get(day)[1] %></td>
			</tr>
			
		<% } %>
	
	<tr>
	<th>Total Price:</th>
	<td>$ <%= result.getPrice() %></td>
	</tr>
	
	<tr>
	<th>Distance:</th>
	<td><%= result.getDistance() %> m</td>
	</tr>	
	</table>
	<br>
	<br>
	<% } %>
	

</body>
</html>