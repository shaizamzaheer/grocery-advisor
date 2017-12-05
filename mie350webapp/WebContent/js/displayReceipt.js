// this script takes care of displaying the receipt popup when a particular "Total Cost" is clicked in results.jsp

window.addEventListener("load", function() {

  var popupReceipt = document.getElementById("popup-receipt"); // div element that will hold the elements from popupReceipt.jsp
  var contents = document.getElementById("contents");

  //click button to display popup-receipt
  contents.addEventListener("click", function(e) {
    
	  // if the "Total Cost" is clicked in results.jsp...
	  if (e.target.className === "result-cost") {
		  
		  // pass storeID to GetReceiptServlet via AJAX call so that GetReceiptServlet can get the receipt info and store it on session which popupReceipt.jsp reads to formulate a receipt...
		  var storeID = e.target.id;
		  var xhrMakeReceipt = new XMLHttpRequest();
		  //console.log(encodeURI("GetReceiptServlet?storeID="+storeID));
		  xhrMakeReceipt.open("POST", encodeURI("GetReceiptServlet?storeID="+storeID));
		  
		  // after the call to GetReceiptServlet is made, another AJAX call is made to receive the entire popupReceipt.jsp as text and include it as HTML in the popup-receipt div 
		  xhrMakeReceipt.onload = function() {
			  
			  var xhr2 = new XMLHttpRequest();
			  xhr2.open("POST", "popupReceipt.jsp");
			  xhr2.onload = function() {
				  	popupReceipt.innerHTML = xhr2.responseText; //popupReceipt.jsp included within popup-receipt div
				  	console.log(xhr2.responseText);
				  	popupReceipt.style.display = "block"; //popup-receipt div is then displayed (from "none" to "block"), thus displaying the receipt 
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