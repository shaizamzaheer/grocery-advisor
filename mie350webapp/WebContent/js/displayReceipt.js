window.addEventListener("load", function() {

  var popupReceipt = document.getElementById("popup-receipt");
  var contents = document.getElementById("contents");

  //click button to display popup-receipt
  contents.addEventListener("click", function(e) {
    
	  if (e.target.className === "result-cost") {
		  
		  var storeID = e.target.id;
		  var xhrMakeReceipt = new XMLHttpRequest();
		  console.log(encodeURI("GetReceiptServlet?storeID="+storeID));
		  xhrMakeReceipt.open("POST", encodeURI("GetReceiptServlet?storeID="+storeID));
		  
		  xhrMakeReceipt.onload = function() {
			  
			  var xhr2 = new XMLHttpRequest();
			  xhr2.open("POST", "popupReceipt.jsp");
			  xhr2.onload = function() {
				  	popupReceipt.innerHTML = xhr2.responseText;
				  	console.log(xhr2.responseText);
				  	popupReceipt.style.display = "block";
			  };
        	xhr2.send();
			  
		  };
		  
		  xhrMakeReceipt.send();
	  }
	  
	  
	  
    	
    	

  });
  
  //click grey area or X to close popupReceipt
    popupReceipt.addEventListener("click", function(e) {
      if (e.target == popupReceipt || e.target.id === "popup-receipt-close") {
        popupReceipt.style.display = "none";
      }
  });
    
});