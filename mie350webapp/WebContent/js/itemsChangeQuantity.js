// this script takes care of incrementing and decrementing the quantity for items in the ITEMS PAGE (not shopping cart popup)

window.addEventListener("load", function() {
  var contents = document.getElementById("contents");

  contents.addEventListener("click", function(e) {
	  
	  // if "+" button is clicked...
    if (e.target.className == "quantity-inc") {
      e.target.previousElementSibling.value =
        parseInt(e.target.previousElementSibling.value) + 1; //quantity is incremented by 1
    } //end if

    if (e.target.className == "quantity-dec") {
    	
    	// if "-" button is clicked...
      if (e.target.nextElementSibling.value > 0)
        e.target.nextElementSibling.value =
          parseInt(e.target.nextElementSibling.value) - 1; //quantity is decremented by 1 if it's bigger than 0 already; if 0, no change
    } //end if
  }); //end, click listener
  
  // tried to implement a robust way of typing in number into text input, but unsuccessful, so text input is disabled (doesn't allow for manual input)
  contents.addEventListener("click", function(e){
    if (e.target.className == "quantity") {
      
      var prevNumVal = e.target.value;
      e.target.addEventListener("input", function() {
        
        var regex = /[^0-9]+/g;
        if (regex.exec(this.value)) {
          this.value = prevNumVal; /* OUT OF SCOPE, just becomes 0 */
        } //end if

      }); //end, input listener
      
    } //end if
    
  }); //end, click listener
});