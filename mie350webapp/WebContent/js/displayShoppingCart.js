window.addEventListener("load", function() {

  var popup = document.getElementById("popup");
  var displaybtn = document.getElementById("cart-btn");

  //click button to display popup
  displaybtn.addEventListener("click", function() {
    

    	var xhr2 = new XMLHttpRequest();
        xhr2.open("POST", "popup.jsp");
        xhr2.onload = function() {
        	popup.innerHTML = xhr2.responseText;
        	console.log(xhr2.responseText);
            popup.style.display = "block";
        };
        xhr2.send();
    	

  });
  
  //click grey area or X to close popup
    popup.addEventListener("click", function(e) {
      if (e.target == popup || e.target.id === "popup-close-btn") {
        popup.style.display = "none";
      }
  });
    
});