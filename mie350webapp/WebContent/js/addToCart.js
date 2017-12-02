window.addEventListener("load", function() {

	var contents = document.getElementById("contents");
	
	contents.addEventListener("click", function(e) {
	    if(e.target.className == "item-btn" && !e.target.classList.contains("delete")) {
	      
	      var currElement = e.target; //currElem is button
	      currElement = currElement.previousElementSibling; //up one, currElem is quantity-control
	      var quantity = currElement.children[1].value;
	      /* Do something with quantity-control, like make it disappear? */
	      
	      currElement = currElement.previousElementSibling; //currElem is item-info
	      var itemName = currElement.children[0].innerHTML; //first child is item-name
	      var amount = currElement.children[1].innerHTML; //second child is item-amount
	      
	      currElement = currElement.previousElementSibling; //currElem is hidden itemID
	      var itemID = currElement.value;
	      
	      currElement = currElement.previousElementSibling; //currElem is item-image
	      var inCartSymbol = currElement.children[0]; //access to in-cart-symbol
	      
	      if (quantity > 0) {
	    	  e.target.innerHTML = "Delete";
		      e.target.classList.add("delete");
		      inCartSymbol.style.display = "block"; //display "In Cart" on image, showing that it's in cart
		      
		      
	    	  var xhr = new XMLHttpRequest();
	    	  console.log(encodeURI("ShoppingCartServlet?itemID="+itemID+"&itemName="+itemName+"&amount="+amount+"&quantity="+quantity));
	    	  xhr.open("POST", encodeURI("ShoppingCartServlet?itemID="+itemID+"&itemName="+itemName+"&amount="+amount+"&quantity="+quantity));
	    	  
	    	  xhr.send();
	      } //end if quantity > 0
	      
	    } //end if class = 'item-btn'
	    
	    else if (e.target.classList.contains("item-btn") && e.target.classList.contains("delete")) {
	    	
	    	e.target.innerHTML = "Add";
		      e.target.classList.remove("delete");
		      
		      var currElement = e.target; //currElem is button
		      currElement = currElement.previousElementSibling; //up one, currElem is quantity-control
		      
		      currElement = currElement.previousElementSibling; //currElem is item-info
		      
		      currElement = currElement.previousElementSibling; //currElem is hidden itemID
		      var itemID = currElement.value;
		      var deleteVar = "item";
		      
		      currElement = currElement.previousElementSibling; //currElem is item-image
		      var inCartSymbol = currElement.children[0]; //access to in-cart-symbol
		      
		      inCartSymbol.style.display = "none"; //remove "In Cart" on image, showing that it's not in cart anymore

		    	  var xhr = new XMLHttpRequest();
		    	  var url = encodeURI("ShoppingCartServlet?itemID=" + itemID + "&delete="	+ deleteVar);
		    	  xhr.open("POST", url);
		    	  
		    	  xhr.send();

	    }
	    
	  }); //end, click listener
  
});

