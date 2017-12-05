// this script takes care of displaying the store details popup when a particular store name/address is clicked in results.jsp

window.addEventListener("load", function() {

  var contents = document.getElementById("contents");

  //click button to display popup-store
  contents.addEventListener("click", function(e) {
    
	  // If the store name/address is clicked...
	  if (e.target.className === "result-store") {
		  e.target.parentElement.parentElement.previousElementSibling.style.display = "block"; // dom traversal to find popup-store, then display it
	  }
            
  });
  
  //click grey area or X to close popup-store
    contents.addEventListener("click", function(e) {
      if (e.target.className === "popup-store") {
        e.target.style.display = "none"; //if the grey area is clicked, then can directly change display to none
      }
      
      else if (e.target.className === "popup-store-close") {
    	  e.target.parentElement.parentElement.style.display = "none"; //if X is clicked, then dom traversal to find popup-store, then change display to none
      }
  });
    
});