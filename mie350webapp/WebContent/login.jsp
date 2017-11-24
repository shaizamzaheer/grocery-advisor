
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
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
	%>
<div id="login-header">
  <div id="logo-container" >
  	<div id="logo"></div>
  </div>
  <h1>Welcome to Grocery Advisor</h1>
</div>

<div id="login-left" class="login-content">
    <form action="LoginServlet" method="post" id="login-fields">
      <input type="text" name="email" id="login-email" placeholder="Email">
      <input type="password" name="password" id="login-password" placeholder="Password">
      <input type="submit" value="Sign In" id="login-submit" class='login-btn'>
      <input type="button" value="Forgot Password" class='login-btn' id='login-forgot'>
      
    </form>
  
  <form action="CreateAccountServlet" method="post" id="signup-fields" style="display: none;">
      <input type="text" name="name" id="signup-name" placeholder="Name">
      <input type="text" name="email" id="signup-email" placeholder="Email">
    <input type="password" name="password" id="signup-password" placeholder="Password">
    <input type="password" id="confirm-password" placeholder="Confirm Password">
    
      <input type="submit" value="Sign Up" id="signup-submit">
      
      
      
    </form>
   

</div>

<div id="login-right" class="login-content">
  <h1 id='login-blurb'>We know you work hard for your money. This website wants to help you get the most for your dollar on everyday grocery purchases. It tailors grocery store recommendations to you based on what you need to buy and how far the store is from your location. 
    <br><br>
    <span id="login-create"><span>Create an account</span> to begin using the full power of your dollar.</span>
    
    <span id="signin" style="display: none;">Already have an account?<span>Sign In</span></span>
      
    </h1>
</div>
</body>
</html>