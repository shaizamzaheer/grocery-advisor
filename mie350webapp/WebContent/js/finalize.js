// This script handles user's input to change their preferred travel method as well as if they prefer to rank by "both" price and time

window.addEventListener("load", function(){ 
	
//start of transport-btn click listener
  document.getElementById("transport-btns").addEventListener("click", function(e) {
	  
    var btns = this.children; // array of all the transport method buttons
    var prevSelected = "";
    
    // find specific button that is previously selected, then store in prevSelected
    for (var i = 0; i < btns.length; i++) {
      if (btns[i].classList.contains("transport-selected")) {
        prevSelected = btns[i];
        break;
      }
    }
    
    // if what is clicked is a button, and doesn't have the transport-selected class...
    if (e.target.tagName == "BUTTON" && !e.target.classList.contains("transport-selected")) {
      e.target.classList.add("transport-selected"); // add the class to the newly selected transport method (adds red styling)
      prevSelected.classList.remove("transport-selected"); // remove the class from the previously selected transport method (removes red styling)
      
      // update the hidden input (that will be passed to GetStoresWithinRadiusServlet) to the transport method selected
      if (e.target.id == "transport-walk") {
        this.nextElementSibling.value = "walk";
      }
      
      else if (e.target.id == "transport-bike") {
        this.nextElementSibling.value = "bike";
      }
      
      else if (e.target.id == "transport-car") {
        this.nextElementSibling.value = "car";
      }
      
      else if (e.target.id == "transport-transit") {
        this.nextElementSibling.value = "transit";
      }
    }
    
  }); //end of transport-btn click listener
  
  // if "Both" radio button is selected...
  document.getElementById("radio-choices-container").addEventListener("click", function(e){
    if (e.target == this.children[2].children[0]) {
      this.nextElementSibling.style.display = "block"; // display and allow user to input their preferred time value
    }
    
    // if "Both" radio button not selected... 
    else {
      this.nextElementSibling.style.display = "none"; // hide input for time value
    }
  });
}); 