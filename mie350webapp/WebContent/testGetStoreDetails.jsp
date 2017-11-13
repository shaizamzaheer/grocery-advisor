<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.mie.dao.*, com.mie.model.*, java.util.*, java.sql.Time"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testing StoreDAO.getStoreDetails(ID)</title>
</head>
<body>

	<% 
	StoreDAO dao = new StoreDAO();
	Store store = dao.getStoreDetails(1);
	
	
	out.println("<br>Franchise: " + store.getFranchise());
	out.println("<br>Street_Address: " + store.getStreetAddress());
	out.println("<br>Region: " + store.getRegion());
	out.println("<br>Postal_Code: " + store.getPostalCode());
	out.println("<br>Phone: " + store.getPhone());
	out.println("<br>Hours: ");
	
	HashMap<String, Time[]> hours = store.getHours();
	for(String day : hours.keySet()) {
		out.println("<br>&emsp;" + day + ": " + hours.get(day)[0] + " to " + hours.get(day)[1]);
	}
	
	%>
		

</body>
</html>