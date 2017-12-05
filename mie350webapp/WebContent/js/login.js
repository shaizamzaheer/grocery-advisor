// this script takes care of switching between login and signup as well as display errors in login.jsp

window.onload = function () {
  var createacc = document.querySelector("#login-create span"); // button to switch from login to signup
  var signin = document.querySelector("#signin span"); // button to switch from signup to login
  
  // if button to switch from login to signup is clicked...
  createacc.addEventListener("click", function() {
    
    
    document.getElementById("login-fields").style.display = "none"; // dont display login fields
    
    document.getElementById("invalid-login").style.display = "none"; // dont display invalid login message
    
    // remove all red styling from fields
    document.getElementById("login-email").classList.remove("login-red");
    document.getElementById("login-password").classList.remove("login-red");
    
    document.getElementById("signup-fields").style.display = "block"; // display signup fields
    document.getElementById("signin").style.display = "block"; // display message that contains button to switch from signup to login
    this.parentElement.style.display = "none"; // dont display message that contains button to switch from login to signup
    
  });
  
  // if button to switch from signup to login is clicked...
  signin.addEventListener("click", function() {
	  
	  // dont display all/any error messages regarding signup fields
	  document.getElementById("invalid-signin").style.display = "none";
	  document.getElementById("missing-name").style.display = "none";
	  document.getElementById("missing-email").style.display = "none";
	  document.getElementById("missing-password").style.display = "none";
	  document.getElementById("missing-confirm").style.display = "none";
	  document.getElementById("diff-confirm").style.display = "none";
	  
	  // dont display all/any red styling for signup fields
	  document.getElementById("signup-name").classList.remove("login-red");
	  document.getElementById("signup-email").classList.remove("login-red");
	  document.getElementById("signup-password").classList.remove("login-red");
	  document.getElementById("confirm-password").classList.remove("login-red");
	    
    document.getElementById("login-fields").style.display = "block"; // display login fields
    document.getElementById("signup-fields").style.display = "none"; // dont display signup fields
    document.getElementById("login-create").style.display = "block"; // display message containing button to switch from login to signup
    this.parentElement.style.display = "none"; // dont display message containing button to switch from signup to login
    
  });
  
  // whenever user starts to type in a field that has red styilng...
  document.getElementById("login-left").addEventListener("input", function(e){
	  e.target.classList.remove("login-red"); // remove that field's red styling
	  
	  // for any of the following fields that's typed in, remove the error message that's related to it (if it's displayed)
	  if (e.target.id == "signup-name") {
		  document.getElementById("missing-name").style.display = "none";
	  }
	  
	  else if (e.target.id == "signup-email") {
		  document.getElementById("missing-email").style.display = "none";
		  document.getElementById("invalid-signin").style.display = "none";
	  }
	  
	  else if (e.target.id == "signup-password") {
		  document.getElementById("missing-password").style.display = "none";
	  }
	  
	  else if (e.target.id == "confirm-password") {
		  document.getElementById("missing-confirm").style.display = "none";
		  document.getElementById("diff-confirm").style.display = "none";
		  
	  }
	  
	  else if (e.target.id == "login-email" || e.target.id == "login-password") {
		  document.getElementById("invalid-login").style.display = "none";
	  }
  });
  
  // if the "Sign Up" button is clicked...
  document.getElementById("signup-submit").addEventListener("click", function() {
	  
	  // access all the signup fields
	  var name = document.getElementById("signup-name");
	  var email = document.getElementById("signup-email");
	  var password = document.getElementById("signup-password");
	  var confirm = document.getElementById("confirm-password");
	  
	  // if any of the fields are blank, then style that field with red and show the relevant error message
	  if (name.value == "") {
		  document.getElementById("missing-name").style.display = "block";
		  
		  name.classList.add("login-red");
	  }
	  
	  if (email.value == "") {
		  document.getElementById("missing-email").style.display = "block";
		  document.getElementById("invalid-signin").style.display = "none";
		  
		  email.classList.add("login-red");
	  }
	  
	  if (password.value == "") {
		  document.getElementById("missing-password").style.display = "block";
		  
		  password.classList.add("login-red");
	  }
	  
	  if (confirm.value == "") {
		  document.getElementById("missing-confirm").style.display = "block";
		  document.getElementById("diff-confirm").style.display = "none";
		  
		  confirm.classList.add("login-red");
	  }
	  
	  // if confirm password doesnt match password, then style confirm password in red and display relevant error message
	  else if (password.value != confirm.value) {
		  document.getElementById("missing-confirm").style.display = "none";
		  document.getElementById("diff-confirm").style.display = "block";
		  
		  confirm.classList.add("login-red");
	  }
	  
	  // if confirm password DOES match password, then remove red styling and dont display error message
	  else if (password.value == confirm.value) {
		  document.getElementById("missing-confirm").style.display = "none";
		  document.getElementById("diff-confirm").style.display = "none";
		  
		  confirm.classList.remove("login-red");
	  }
	  
	  // if all fields are NOT blank, then submit field values to CreateAccountServlet
	  if (name.value != "" && email.value != "" && password.value != "" && confirm.value != "" && password.value == confirm.value) {
		  this.parentElement.submit();
	  }
	  
	  
  });
  
};