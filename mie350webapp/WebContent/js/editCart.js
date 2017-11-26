// improved code

window.addEventListener("load", function() {
	var popup = document.getElementById("popup");
	
	  popup.addEventListener("click", function(e) {
		  
		  var quantity = 0;
		  var itemID = 0;
		  var prev = 0;
		  var url = "";
		  var deleteVar = "";
		  
	    if (e.target.className == "quantity-inc") {
	      e.target.previousElementSibling.value = parseInt(e.target.previousElementSibling.value) + 1;
	      
	      quantity = e.target.previousElementSibling.value;
	      itemID = e.target.previousElementSibling.id;
	    } //end if

	    if (e.target.className == "quantity-dec") {
	    	
	    	prev = e.target.nextElementSibling.value;
	      if (e.target.nextElementSibling.value > 1) {
	        e.target.nextElementSibling.value = parseInt(e.target.nextElementSibling.value) - 1;
	        
	        quantity = e.target.nextElementSibling.value;
		    itemID = e.target.nextElementSibling.id;
	      }
	    } //end if
	    
	    if (e.target.className == "quantity-inc" || (e.target.className == "quantity-dec" && prev != 1)) {
	    	url = encodeURI("ShoppingCartServlet?itemID=" + itemID + "&quantity="	+ quantity);
	    } //end if for setting url for qty change
	    
	    if (e.target.className == "popup-cart-delete") {
	    	deleteVar = "item";
	    	itemID = e.target.id;
	    	
	    	url = encodeURI("ShoppingCartServlet?itemID=" + itemID + "&delete="	+ deleteVar);
	    } //end if for setting url for single item delete
	    
	    if (e.target.id == "popup-cart-clearall") {
	    	deleteVar = "all";
	    	
	    	url = encodeURI("ShoppingCartServlet?delete="	+ deleteVar);
	    } //end if for deleting/clearing all items
	    

	    if (e.target.className == "quantity-inc" || (e.target.className == "quantity-dec" && prev != 1) 
	    		|| e.target.className == "popup-cart-delete" 
	    			|| e.target.id == "popup-cart-clearall") {
	    	var xhrQty = new XMLHttpRequest();

			console.log(url);
			xhrQty.open("POST", url);
			xhrQty.onload = function() {

				var xhr2 = new XMLHttpRequest();
				xhr2.open("POST", "popup.jsp");
				xhr2.onload = function() {
					popup.innerHTML = xhr2.responseText;
					console.log(xhr2.responseText);
					popup.style.display = "block";
					closebtn.style.display = "block";

				};
				xhr2.send();

			};
			xhrQty.send();
			
	    } //end if
	    
	   
	    
	  }); //end, click listener
	  
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