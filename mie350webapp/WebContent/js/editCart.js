// This script takes care of editing the user's cart via the shopping cart popup (changing quantity, deleting individual items, clearing entire cart)
// as well as updating shoppingCart and shoppingCartDictionary on the backend (via AJAX calls to ShoppingCartServlet)

// This script also updates the small circle with a number that appears over the shopping cart button to indicate how many items are in cart

// This script also updates any items that are directly behind the shopping cart popup (i.e. if the user accesses the cart popup while on the items page)
// Quantities and items that are deleted are updated accordingly, thus the items page and the shopping cart popup are always consistent

// This script also updates the message if the page behind the popup is the home page (i.e. whether or not the cart is empty)

window.addEventListener("load", function() {
	var popup = document.getElementById("popup");
	
	  popup.addEventListener("click", function(e) {
		  
		  // initializing variables
		  var quantity = 0;
		  var itemID = 0;
		  var prev = 0;
		  var url = "";
		  var deleteVar = "";
		  var arrItems = [];
		  
		  var cartCapacity = document.querySelector("#cart-btn span"); // access to red circle showing number of items in cart on cart button
		  var welcomeMsg = document.getElementById("welcome-message"); // access to message on home page
		  
		  // if "+" button is clicked in the cart popup...
	    if (e.target.className == "quantity-inc") {
	      e.target.previousElementSibling.value = parseInt(e.target.previousElementSibling.value) + 1; // increment quantity by 1
	      
	      quantity = e.target.previousElementSibling.value; // set quantity variable to new quantity to pass to ShoppingCartServlet
	      itemID = e.target.previousElementSibling.id; // set itemID to pass to ShoppingCartServlet
	    } //end if

	    // if "-" button is clicked...
	    if (e.target.className == "quantity-dec") {
	    	
	    	prev = e.target.nextElementSibling.value;
	      if (e.target.nextElementSibling.value > 1) {
	        e.target.nextElementSibling.value = parseInt(e.target.nextElementSibling.value) - 1; // decrement quantity by 1 if quantity is greater than 1; if 1, no change
	        
	        // set quantity and itemID variables to pass to ShoppingCartServlet
	        quantity = e.target.nextElementSibling.value;
		    itemID = e.target.nextElementSibling.id;
	      }
	    } //end if
	    
	    // if quantity was changed...
	    if (e.target.className == "quantity-inc" || (e.target.className == "quantity-dec" && prev != 1)) {
	    	url = encodeURI("ShoppingCartServlet?itemID=" + itemID + "&quantity="	+ quantity); //set the url for AJAX call to ShoppingCartServlet accordingly
	    	
	    	// find the hidden input element that holds the itemID in the items page
			var elementHidden = document.getElementById("contents").querySelector("input[type='hidden'][value='"+itemID+"']");
			
			// if hidden input element found (not null)
			if (elementHidden != null) {
				// dom traversal to get to the item's quantity on the items page and update it
				elementHidden.nextElementSibling.nextElementSibling.children[1].value = quantity; 
			}
			
	    } //end if for setting url for qty change
	    
	    // if "trashcan" delete button is clicked for a single item...
	    if (e.target.className == "popup-cart-delete") {
	    	deleteVar = "item";
	    	itemID = e.target.id;
	    	
	    	url = encodeURI("ShoppingCartServlet?itemID=" + itemID + "&delete="	+ deleteVar); //set url for AJAX call to ShoppingCartServlet accordingly
	    	
	    	// find hidden input element that holds itemID in items page
	    	var elementHidden = document.getElementById("contents").querySelector("input[type='hidden'][value='"+itemID+"']");
			
	    	// if found... 
			if (elementHidden != null) {
				var btn = elementHidden.nextElementSibling.nextElementSibling.nextElementSibling;
				btn.innerHTML = "Add"; // change button text from "Delete" to "Add"
				btn.classList.remove("delete"); // remove 'delete' class, reverts back to white style
				
				elementHidden.previousElementSibling.children[0].style.display = "none"; // remove "In Cart" symbol
			
			}
			
			// whether or not the item is found, the indicator on the cart button needs to be decremented
			cartCapacity.innerHTML = parseInt(cartCapacity.innerHTML) - 1;
			if (parseInt(cartCapacity.innerHTML) == 0) {
				cartCapacity.style.display = "none"; // if it reaches 0, it's no longer displayed
				
				// if welcomeMsg is not null, it means the page behind the popup is welcome.jsp
				// if the indicator reaches 0, then display appropriate message
				if (welcomeMsg != null) {
				welcomeMsg.innerHTML = "Looks like your list is empty. " + 
					"Use the categories or search for some groceries to make a shopping list. " + 
					"When you've added all your items, confirm your shopping list. " + 
					"Next, enter your location and proceed to get a ranked list of the best stores to shop at.";
				}
			}
	    } //end if for setting url for single item delete
	    
	    // if "clear all" button is clicked... 
	    if (e.target.id == "popup-cart-clearall") {
	    	deleteVar = "all";
	    	
	    	url = encodeURI("ShoppingCartServlet?delete="	+ deleteVar); //set url for AJAX call to ShoppingCartServlet accordingly
	    	
	    	arrItems = this.getElementsByClassName("popup-cart-delete"); // array of each delete button in shopping cart (delete button stores itemID)
	    	//console.log("items: " + arrItems);
	    	//console.log("# items:  " + arrItems.length);
	    	
	    	// for each item in cart...
	    	for (var i = 0; i < arrItems.length; i++) {
	    		itemID = arrItems[i].id; //get itemID
	    		//console.log("Item being deleted: " + itemID);
	    		
	    		// find hidden input element that holds itemID in items page
	    		var elementHidden = document.getElementById("contents").querySelector("input[type='hidden'][value='"+itemID+"']");
				
	    		// if found... 
				if (elementHidden != null) {
					var btn = elementHidden.nextElementSibling.nextElementSibling.nextElementSibling;
					btn.innerHTML = "Add"; // change button text from "Delete" to "Add"
					btn.classList.remove("delete"); // remove 'delete' class, reverts back to white style
					
					elementHidden.previousElementSibling.children[0].style.display = "none"; // remove "In Cart" symbol
					
				} //end if elementHidden not null
	    		
	    	} //end for loop, for each item in shopping cart
	    	
	    	cartCapacity.innerHTML = 0; // indicator is set to 0
			cartCapacity.style.display = "none"; // since it's 0, not displayed
			
			// if welcomeMsg is not null, it means the page behind the popup is welcome.jsp
			// since indicator is 0, then display appropriate message
			if (welcomeMsg != null) {
				welcomeMsg.innerHTML = "Looks like your list is empty. " + 
				"Use the categories or search for some groceries to make a shopping list. " + 
				"When you've added all your items, confirm your shopping list. " + 
				"Next, enter your location and proceed to get a ranked list of the best stores to shop at.";
				}
			
	    } //end if for deleting/clearing all items
	    

	    // if any edit was made (quantity change, single delete, clear all)...
	    if (e.target.className == "quantity-inc" || (e.target.className == "quantity-dec" && prev != 1) 
	    		|| e.target.className == "popup-cart-delete" 
	    			|| e.target.id == "popup-cart-clearall") {
	    	
	    	// respective parameters that were built in the url are passed via AJAX call to ShoppingCartServlet to update shoppingCart and shoppingCartDictionary
	    	var xhrQty = new XMLHttpRequest();

			//console.log(url);
			xhrQty.open("POST", url);
			
			// after the call to ShoppingCartServlet is made, another AJAX call is made to receive the entire popup.jsp as text and include it as HTML in the popup div 
			// this updates the shopping cart view
			xhrQty.onload = function() {

				var xhr2 = new XMLHttpRequest();
				xhr2.open("POST", "popup.jsp");
				xhr2.onload = function() {
					popup.innerHTML = xhr2.responseText;
					console.log(xhr2.responseText);
					popup.style.display = "block";
				};
				xhr2.send();

			};
			xhrQty.send();
			
	    } //end if
	    
	   
	    
	  }); //end, click listener
	  
	  // tried to implement a robust way of typing in number into text input, but unsuccessful, so text input is disabled (doesn't allow for manual input)
	  popup.addEventListener("click", function(e){
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
	}); //end, window listener