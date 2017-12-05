
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
	// this prevents this page to be cached; thus, after logging out, can't be accessed by "back" button
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
	// if a user already logged in (i.e. user is NOT null), then this prevents user from going back to login.jsp, and redirects to welcome.jsp
	if (session.getAttribute("user") != null) {
		//System.out.println("Is response null? " + response);
		response.sendRedirect("welcome.jsp");
		return;
	}
	
	String redLinesLogin = ""; //either blank, or "login-red" (class that makes bottom-border red); applied to email/password under login-fields
	String redLinesSignup = ""; //either blank, or "login-red" (class that makes bottom-border red); applied to fields under signup-fields
	String displaySignin = "style='display: none;'"; //initially, the signup fields not displayed, login fields displayed
	String displayLogin = ""; //blank, meaning no "style='display: none;'" i.e. login fields displayed
	String showInvalidMessage = "style='display: none;'"; //initially, the invalid message (for login) not displayed
	String showInvalidEmail = "style='display: none;'"; //initially, the invalid message (for existing email in signup) not displayed
	
	//if "invalidLogin" is true, then the appropriate fields (for login) should become red and the appropriate message should be displayed
	if (session.getAttribute("invalidLogin") != null && (Boolean)session.getAttribute("invalidLogin") == true) {
		redLinesLogin = "login-red";
		showInvalidMessage = "style='display: block;'";
		
		//signup fields are initialized to not be displayed
	}
	
	// if "invalidSignin" is true, then signup fields should be displaeyd 
	// and appropriate fields should become red and the appropriate message should be displayed
	if (session.getAttribute("invalidSignin") != null && (Boolean)session.getAttribute("invalidSignin") == true) {
		redLinesSignup = "login-red";
		showInvalidEmail = "style='display: block;'";
		
		// if the login fields/message are red, then dont show them since signup fields are being displayed
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

<!-- Login Elements -->
    <form action="LoginServlet" method="post" id="login-fields" <%= displayLogin %>>
      <input type="text" name="email" id="login-email" class="<%= redLinesLogin %>" placeholder="Email">
      <input type="password" name="password" id="login-password" class="<%= redLinesLogin %>" placeholder="Password">
      <input type="submit" value="Sign In" id="login-submit" class='login-btn'>
      <input type="button" value="Forgot Password" class='login-btn' id='login-forgot'>
      
    </form>
  
<!-- Signup Elements -->
  <form action="CreateAccountServlet" method="post" id="signup-fields" <%= displaySignin %>>
      <input type="text" name="name" id="signup-name" placeholder="Name">
      <input type="text" name="email" id="signup-email" class="<%= redLinesSignup %>" placeholder="Email">
      
    <input type="password" name="password" id="signup-password" placeholder="Password">
    <input type="password" id="confirm-password" placeholder="Confirm Password">
    
      <input type="button" value="Sign Up" id="signup-submit">

    </form>
    
<!-- Messages to display according to the error -->    
    <h1 id="invalid-signin" <%= showInvalidEmail %>>Email already exists</h1>
	<h1 id="invalid-login" <%= showInvalidMessage %>>The email or password you entered is incorrect</h1>
	<h1 id="missing-name">Please enter a name</h1>
	<h1 id="missing-email">Please enter an email</h1>
	<h1 id="missing-password">Please enter a password</h1>
	<h1 id="missing-confirm">Please confirm password</h1>
	<h1 id="diff-confirm">Passwords do not match</h1>
</div>

<!-- Right side message; buttons change via JS according to which is displayed: Login fields or Signup fields -->
<div id="login-right" class="login-content">
  <h1 id='login-blurb'>We know you work hard for your money. This website wants to help you get the most for your dollar on everyday grocery purchases. It tailors grocery store recommendations to you based on what you need to buy and how far the store is from your location. 
    <br><br>
    <span id="login-create" <%= displayLogin %>><span>Create an account</span> to begin using the full power of your dollar.</span>
    
    <span id="signin" <%= displaySignin %>>Already have an account?<span>Sign In</span></span>
      
    </h1>
</div>

<% 
	// these session objects set to false so that if login.jsp is refreshed, the errors don't linger
	session.setAttribute("invalidLogin", false);
   session.setAttribute("invalidSignin", false); %>
</body>
</html>