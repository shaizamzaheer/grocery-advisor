
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta name="viewport" content="width=device-width">
<title>Grocery Advisor - Login</title>
<script src="js/login.js"></script>

<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body">

	<%
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	if (session.getAttribute("user") != null) {
		//System.out.println("Is response null? " + response);
		response.sendRedirect("welcome.jsp");
		return;
	}
	
	String redLinesLogin = "";
	String redLinesSignup = "";
	String displaySignin = "style='display: none;'";
	String displayLogin = "";
	String showInvalidMessage = "style='display: none;'";
	String showInvalidEmail = "style='display: none;'";
	
	if (session.getAttribute("invalidLogin") != null && (Boolean)session.getAttribute("invalidLogin") == true) {
		redLinesLogin = "login-red";
		showInvalidMessage = "style='display: block;'";
	}
	
	if (session.getAttribute("invalidSignin") != null && (Boolean)session.getAttribute("invalidSignin") == true) {
		redLinesSignup = "login-red";
		showInvalidEmail = "style='display: block;'";
		displayLogin = "style='display: none;'";
		displaySignin = "";
	}
	
	
	%>
<div id="login-header">
  <div id="logo-container" >
  	<div id="logo"></div>
  </div>
  <h1>Welcome to Grocery Advisor</h1>
</div>

<div id="login-left" class="login-content">
    <form action="LoginServlet" method="post" id="login-fields" <%= displayLogin %>>
      <input type="text" name="email" id="login-email" class="<%= redLinesLogin %>" placeholder="Email">
      <input type="password" name="password" id="login-password" class="<%= redLinesLogin %>" placeholder="Password">
      <input type="submit" value="Sign In" id="login-submit" class='login-btn'>
      <input type="button" value="Forgot Password" class='login-btn' id='login-forgot'>
      
    </form>
  
  <form action="CreateAccountServlet" method="post" id="signup-fields" <%= displaySignin %>>
      <input type="text" name="name" id="signup-name" placeholder="Name">
      <input type="text" name="email" id="signup-email" class="<%= redLinesSignup %>" placeholder="Email">
      
    <input type="password" name="password" id="signup-password" placeholder="Password">
    <input type="password" id="confirm-password" placeholder="Confirm Password">
    
      <input type="button" value="Sign Up" id="signup-submit">
      
      
      
    </form>
    
    <h1 id="invalid-signin" <%= showInvalidEmail %>>Email already exists</h1>
	<h1 id="invalid-login" <%= showInvalidMessage %>>The email or password you entered is incorrect</h1>
	<h1 id="missing-name">Please enter a name</h1>
	<h1 id="missing-email">Please enter an email</h1>
	<h1 id="missing-password">Please enter a password</h1>
	<h1 id="missing-confirm">Please confirm password</h1>
	<h1 id="diff-confirm">Passwords do not match</h1>
</div>

<div id="login-right" class="login-content">
  <h1 id='login-blurb'>We know you work hard for your money. This website wants to help you get the most for your dollar on everyday grocery purchases. It tailors grocery store recommendations to you based on what you need to buy and how far the store is from your location. 
    <br><br>
    <span id="login-create" <%= displayLogin %>><span>Create an account</span> to begin using the full power of your dollar.</span>
    
    <span id="signin" <%= displaySignin %>>Already have an account?<span>Sign In</span></span>
      
    </h1>
</div>

<% session.setAttribute("invalidLogin", false);
   session.setAttribute("invalidSignin", false); %>
</body>
</html>