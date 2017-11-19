
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Expedia Shopping - Login</title>

</head>
<body style="margin: 0;">

	<%
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	if (session.getAttribute("user") != null) {
		//System.out.println("Is response null? " + response);
		response.sendRedirect("welcome.jsp");
		return;
	}
	%>
<div id="Login" style="background: black; padding: 25px; color:white">
	<div>
		<h1>Expedia Shopping</h1>
		<h3>Login</h3>
	</div>
	<div id="" style="float: right">
		<form action="LoginServlet" method="post">

			Username: <input type="text" name="username" /> 
			Password: <input type="password" name="password" /> 
			<input type="submit" value="Submit">
			<br />
		</form>
	</div>
</div>

<div id="Create User" style="background: black; padding: 25px; color:white">
	<div>
		<h3>Or Create an Account</h3>
	</div>
	<div id="" style="float: right">
		<form action="CreateAccountServlet" method="post">

			Username: <input type="text" name="username" /> 
			Password: <input type="password" name="password" />
			Email: <input type = "email" name = "email"/>
			<input type="submit" value="Create Account">
			<br />
		</form>
	</div>
</div>
		<br /> 
		For demonstration purposes, please use the following
		credentials:
		<ul>
			<li><b>Username</b>: albertloa <b>Password</b>: abcd1234</li>
			<li><b>Username</b>: nathanling <b>Password</b>: 1234abcd</li>
		</ul>

				<!-- You can put right sidebar links here if you want to. -->


</body>
</html>