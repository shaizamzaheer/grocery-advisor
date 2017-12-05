// this script takes care of displaying the user's shopping cart popup when the shopping cart button is clicked

window.addEventListener("load", function() {

  var popup = document.getElementById("popup"); // div element that will hold the elements from popup.jsp
  var displaybtn = document.getElementById("cart-btn"); // cart button that is clicked to display shopping cart popup

  //click shopping cart button to display popup
  displaybtn.addEventListener("click", function() {
    
	  // AJAX call is made to receive the entire popup.jsp as text and include it as HTML in the popup div
    	var xhr2 = new XMLHttpRequest();
        xhr2.open("POST", "popup.jsp");
        xhr2.onload = function() {
        	popup.innerHTML = xhr2.responseText; // popup.jsp included within popup div
        	console.log(xhr2.responseText);
            popup.style.display = "block"; //popup div is then displayed (from "none" to "block"), thus displaying the shopping cart
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