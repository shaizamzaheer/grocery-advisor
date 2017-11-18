window.onload = function() {
  //btns is an array of all buttons with class 'btn' (btns is all "Add To Cart" buttons)
  var btns = document.getElementsByClassName("itemBtn"); 
  //since its an array, go through each button and add an event listener;
  for (i = 0; i < btns.length; i++) {
    
    btns[i].addEventListener("click", function() {
      var quantity = this.previousElementSibling.value;
      var itemID = this.previousElementSibling.previousElementSibling.previousElementSibling.value;
      console.log("Clicked out");
      if (quantity > 0) {
    	  console.log("Clicked in");
    	  var xhr = new XMLHttpRequest();
    	  
    	  xhr.open("POST", "AddToCartServlet?itemID="+itemID+"&quantity="+quantity);
    	  
    	  xhr.send();
      }
      
    });
    
  }
  
};