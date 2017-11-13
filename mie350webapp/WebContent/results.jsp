<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.mie.model.*, java.sql.Time"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	
	<% 
	Set<Result> results = (TreeSet<Result>)session.getAttribute("results"); 
	
	for (Result result : results) { 
		Store store = result.getStoreDetails();
	%>
	<table>
	<tr>
	<th>Store:</th>
	<th><%= store.getFranchise() %></th>
	</tr>
	
	<tr>
	<th>Location:</th>
	<th><%= store.getStreetAddress() %>, <%= store.getRegion() %>, <%= store.getPostalCode() %></th>
	</tr>
	
	<tr>
	<th>Phone:</th>
	<th><%= store.getPhone() %></th>
	</tr>
	
	<tr>
	<th>Hours:</th>
	<th></th>
	</tr>
	
	<% HashMap<String, Time[]> hours = store.getHours(); 
		
		for(String day : hours.keySet()) { %>
			
			<tr>
				<td><%= day %></td>
				<td><%= hours.get(day)[0] %> to <%= hours.get(day)[1] %></td>
			</tr>
			
		<% } %>
		
	</table>
	<br>
	<br>
	<% } %>
	

</body>
</html>