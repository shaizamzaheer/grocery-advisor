<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/getDistances.js"></script>
</head>
<body>

	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	
	if (session.getAttribute("resultsVisited") != null && (Boolean)session.getAttribute("resultsVisited") == true) {
		session.setAttribute("resultsVisited", false);
		response.sendRedirect("finalize.jsp");
		return;
	}
	%>

<p id="url" style="display: none;"><%= session.getAttribute("distanceMatrixURL") %></p>

<form action="DisplayResultsServlet" method="post" id="form"> <!--  DisplayResultsServlet -->
	

</form>


</body>
</html>