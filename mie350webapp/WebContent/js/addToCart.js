// This script takes care of what happens when an "Add" or "Delete" button is clicked from the items page

window.addEventListener("load", function() {

	var contents = document.getElementById("contents");
	
	contents.addEventListener("click", function(e) {
		
		var cartCapacity = document.querySelector("#cart-btn span"); //element that indicates how many items are in cart (small red circle with number on cart button)
		
		// if an "Add" button is clicked (it has an 'item-btn' class but not a 'delete' class)...
	    if(e.target.classList.contains("item-btn") && !e.target.classList.contains("delete")) {
	      
	      var currElement = e.target; //currElem is button
	      currElement = currElement.previousElementSibling; //up one, currElem is quantity-control
	      var quantity = currElement.children[1].value;
	      
	      currElement = currElement.previousElementSibling; //currElem is item-info
	      var itemName = currElement.children[0].innerHTML; //first child is item-name
	      var amount = currElement.children[1].innerHTML; //second child is item-amount
	      
	      currElement = currElement.previousElementSibling; //currElem is hidden itemID
	      var itemID = currElement.value;
	      
	      currElement = currElement.previousElementSibling; //currElem is item-image
	      var inCartSymbol = currElement.children[0]; //access to in-cart-symbol
	      
	      // if the quantity is greater than 0...
	      if (quantity > 0) {
	    	  e.target.innerHTML = "Delete"; // change the button text from "Add" to "Delete"
		      e.target.classList.add("delete"); // add 'delete' class (this styles it to red)
		      inCartSymbol.style.display = "block"; //display "In Cart" on image, showing that it's in cart
		      
		      cartCapacity.innerHTML = parseInt(cartCapacity.innerHTML) + 1; // increment the cart capacity
		      if (parseInt(cartCapacity.innerHTML) > 0) {
					cartCapacity.style.display = "block"; // if cart capacity is greater than 0, then show the cart capacity (small red circle with number on cart button)
				}
		      
		      // send item info and quantity to ShoppingCartServlet via AJAX to add item to shoppingCart and shoppingCartDictionary 
	    	  var xhr = new XMLHttpRequest();
	    	  //console.log(encodeURI("ShoppingCartServlet?itemID="+itemID+"&itemName="+itemName+"&amount="+amount+"&quantity="+quantity));
	    	  xhr.open("POST", encodeURI("ShoppingCartServlet?itemID="+itemID+"&itemName="+itemName+"&amount="+amount+"&quantity="+quantity));
	    	  
	    	  xhr.send();
	      } //end if quantity > 0
	      
	    } //end if class = 'item-btn'
	    
	    // if "Delete" button is clicked (it has both 'item-btn' and 'delete' classes)
	    else if (e.target.classList.contains("item-btn") && e.target.classList.contains("delete")) {
	    	
	    	e.target.innerHTML = "Add"; // change button text from "Delete" to "Add"
		      e.target.classList.remove("delete"); // remove 'delete' class; this gives it back the original style of white
		      
		      var currElement = e.target; //currElem is button
		      currElement = currElement.previousElementSibling; //up one, currElem is quantity-control
		      
		      currElement = currElement.previousElementSibling; //currElem is item-info
		      
		      currElement = currElement.previousElementSibling; //currElem is hidden itemID
		      var itemID = currElement.value;
		      var deleteVar = "item";
		      
		      currElement = currElement.previousElementSibling; //currElem is item-image
		      var inCartSymbol = currElement.children[0]; //access to in-cart-symbol
		      
		      inCartSymbol.style.display = "none"; //remove "In Cart" on image, showing that it's not in cart anymore
		      
		      cartCapacity.innerHTML = parseInt(cartCapacity.innerHTML) - 1; // decrement cart capacity
				if (parseInt(cartCapacity.innerHTML) == 0) {
					cartCapacity.style.display = "none"; // if cart capacity is 0 (that was the last item removed from cart) then don't show circle with number 
				}
		      
				//send itemID and delete = 'item' to ShoppingCartServlet via AJAX to delete single item with itemID from shoppingCart and shoppingCartDictionary
		    	  var xhr = new XMLHttpRequest();
		    	  var url = encodeURI("ShoppingCartServlet?itemID=" + itemID + "&delete="	+ deleteVar);
		    	  xhr.open("POST", url);
		    	  
		    	  xhr.send();

	    }
	    
	  }); //end, click listener
  
});

