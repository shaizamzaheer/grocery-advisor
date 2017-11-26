window.addEventListener("load", function() {
  var contents = document.getElementById("contents");

  contents.addEventListener("click", function(e) {
    if (e.target.className == "quantity-inc") {
      e.target.previousElementSibling.value =
        parseInt(e.target.previousElementSibling.value) + 1;
    } //end if

    if (e.target.className == "quantity-dec") {
      if (e.target.nextElementSibling.value > 0)
        e.target.nextElementSibling.value =
          parseInt(e.target.nextElementSibling.value) - 1;
    } //end if
  }); //end, click listener
  
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