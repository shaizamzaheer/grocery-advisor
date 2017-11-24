window.onload = function () {
  var createacc = document.querySelector("#login-create span");
  var signin = document.querySelector("#signin span")
  createacc.addEventListener("click", function() {
    
    
    document.getElementById("login-fields").style.display = "none";
    document.getElementById("signup-fields").style.display = "block";
    document.getElementById("signin").style.display = "block";
    this.parentElement.style.display = "none";
    
  });
  
  signin.addEventListener("click", function() {
    
    
    document.getElementById("login-fields").style.display = "block";
    document.getElementById("signup-fields").style.display = "none";
    document.getElementById("login-create").style.display = "block";
    this.parentElement.style.display = "none";
    
  });
  
};