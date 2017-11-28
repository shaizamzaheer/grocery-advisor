window.addEventListener("load", function() {

  var contents = document.getElementById("contents");

  //click button to display popup
  contents.addEventListener("click", function(e) {
    
	  if (e.target.className === "result-store") {
		  e.target.parentElement.parentElement.previousElementSibling.style.display = "block";
	  }
            
  });
  
  //click grey area or X to close popup
    contents.addEventListener("click", function(e) {
      if (e.target.className === "popup-store") {
        e.target.style.display = "none";
      }
      
      else if (e.target.className === "popup-store-close") {
    	  e.target.parentElement.parentElement.style.display = "none";
      }
  });
    
});