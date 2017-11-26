window.addEventListener("load", function() {
	console.log("Trying to print!");
  var popup = document.getElementById("popup");
  var displaybtn = document.getElementById("cart-btn");
  var closebtn = document.getElementById("closebtn");

  //click button to display popup
  displaybtn.addEventListener("click", function() {
    

    	var xhr2 = new XMLHttpRequest();
        xhr2.open("POST", "popup.jsp");
        xhr2.onload = function() {
        	popup.innerHTML = xhr2.responseText;
        	console.log(xhr2.responseText);
            popup.style.display = "block";
            closebtn.style.display = "block";
            
          //inOutBtns is an array of all In/Out Buttons in shopping cart popup
        	console.log("hey, in editCart now...");
          var inOutBtns = document.getElementsByClassName("inOutBtn");
          console.log(inOutBtns);
          //quantities is an array of all popupItemQuantities
          var quantities = document.getElementsByClassName("popupItemQuantity");
          console.log(quantities);
          //listen for a click on the in/out button, then call a servlet with ajax to update the shopping cart
          for (i = 0; i < inOutBtns.length; i++) {
            
            inOutBtns[i].addEventListener("click", function() {
            	console.log("A button was clicked!");
              var inCart = this.value;
              var itemID = this.id;
              console.log("In or out of cart? " + inCart + " ItemID is: " + itemID);

            	  var xhrInOut = new XMLHttpRequest();
            	  console.log("ShoppingCartServlet?itemID="+itemID+"&inCart="+inCart);
            	  xhrInOut.open("POST", "ShoppingCartServlet?itemID="+itemID+"&inCart="+inCart);
            	  xhrInOut.onload = function() {
            		    

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
            	  xhrInOut.send();

            });
          }
          
        //if quantity changes, then call a servlet with ajax to update the shopping cart
          for (i = 0; i < quantities.length; i++) {
            
            quantities[i].addEventListener("input", function() {
            	console.log("A number was changed!");
              var quantity = this.value;
              var itemID = this.id;
              console.log("Quantity? " + quantity + " ItemID is: " + itemID);

            	  var xhrQty = new XMLHttpRequest();
            	  
            	  console.log("ShoppingCartServlet?itemID="+itemID+"&quantity="+quantity);
            	  xhrQty.open("POST", "ShoppingCartServlet?itemID="+itemID+"&quantity="+quantity);
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

            });
            
          }
        };
        xhr2.send();
    	

  });
  
  //click x button to close popup
  closebtn.addEventListener("click", function() {
    popup.style.display = "none";
    closebtn.style.display = "none";
  });
  
  //click grey area to close popup
    popup.addEventListener("click", function(e) {
      if (e.target == popup) {
        popup.style.display = "none";
        closebtn.style.display = "none";
      }
  });
});