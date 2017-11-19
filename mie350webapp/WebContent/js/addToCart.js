window.addEventListener("load", function() {
  //btns is an array of all buttons with class 'btn' (btns is all "Add To Cart" buttons)
	console.log("Hey before getting item buttons");
  var btns = document.getElementsByClassName("itemBtn"); 
  console.log("Hey after getting item buttons");
  console.log(btns);
  //since its an array, go through each button and add an event listener;
  for (i = 0; i < btns.length; i++) {
    
    btns[i].addEventListener("click", function() {
    	var currElement = this; //start DOM traversal on button
    	
    	currElement = currElement.previousElementSibling; //now on number input quantity
      var quantity = currElement.value; //TO SEND TO SERVLET
      
      currElement = currElement.previousElementSibling; //now on itemInfo (need children itemName and amount
      var itemInfo = currElement.children; //array of children
      var itemName = itemInfo[0].innerHTML; //TO SEND TO SERVLET
      var amount = itemInfo[1].innerHTML; //TO SEND TO SERVLET
      
      currElement = currElement.previousElementSibling; //now on hidden input itemID
      var itemID = currElement.value; //TO SEND TO SERVLET
      
      var inCart = true; //TO SEND TO SERVLET
      
      console.log("Clicked out");
      if (quantity > 0) {
    	  console.log("Clicked in");
    	  var xhr = new XMLHttpRequest();
    	  console.log(encodeURI("ShoppingCartServlet?itemID="+itemID+"&itemName="+itemName+"&amount="+amount+"&quantity="+quantity+"&inCart="+inCart));
    	  xhr.open("POST", encodeURI("ShoppingCartServlet?itemID="+itemID+"&itemName="+itemName+"&amount="+amount+"&quantity="+quantity+"&inCart="+inCart));
    	  
    	  xhr.send();
      }
      
    });
    
  }
  
});