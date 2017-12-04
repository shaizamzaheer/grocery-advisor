window.onload = function () {
  var createacc = document.querySelector("#login-create span");
  var signin = document.querySelector("#signin span")
  createacc.addEventListener("click", function() {
    
    
    document.getElementById("login-fields").style.display = "none";
    
    document.getElementById("invalid-login").style.display = "none";
    document.getElementById("login-email").classList.remove("login-red");
    document.getElementById("login-password").classList.remove("login-red");
    
    document.getElementById("signup-fields").style.display = "block";
    document.getElementById("signin").style.display = "block";
    this.parentElement.style.display = "none";
    
  });
  
  signin.addEventListener("click", function() {
	  
	  document.getElementById("invalid-signin").style.display = "none";
	  document.getElementById("missing-name").style.display = "none";
	  document.getElementById("missing-email").style.display = "none";
	  document.getElementById("missing-password").style.display = "none";
	  document.getElementById("missing-confirm").style.display = "none";
	  document.getElementById("diff-confirm").style.display = "none";
	  
	  document.getElementById("signup-name").classList.remove("login-red");
	  document.getElementById("signup-email").classList.remove("login-red");
	  document.getElementById("signup-password").classList.remove("login-red");
	  document.getElementById("confirm-password").classList.remove("login-red");
	    
    document.getElementById("login-fields").style.display = "block";
    document.getElementById("signup-fields").style.display = "none";
    document.getElementById("login-create").style.display = "block";
    this.parentElement.style.display = "none";
    
  });
  
  document.getElementById("login-left").addEventListener("input", function(e){
	  e.target.classList.remove("login-red");
	  
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
  
  document.getElementById("signup-submit").addEventListener("click", function() {
	  
	  var name = document.getElementById("signup-name");
	  var email = document.getElementById("signup-email");
	  var password = document.getElementById("signup-password");
	  var confirm = document.getElementById("confirm-password");
	  
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
	  
	  else if (password.value != confirm.value) {
		  document.getElementById("missing-confirm").style.display = "none";
		  document.getElementById("diff-confirm").style.display = "block";
		  
		  confirm.classList.add("login-red");
	  }
	  
	  else if (password.value == confirm.value) {
		  document.getElementById("missing-confirm").style.display = "none";
		  document.getElementById("diff-confirm").style.display = "none";
		  
		  confirm.classList.remove("login-red");
	  }
	  
	  if (name.value != "" && email.value != "" && password.value != "" && confirm.value != "" && password.value == confirm.value) {
		  this.parentElement.submit();
	  }
	  
	  
  });
  
};