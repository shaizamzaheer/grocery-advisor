// this script takes care of submitting the shopping cart info to InsertCartServlet when "Next" is clicked in the shopping cart popup

window.addEventListener("load", function() {
	
	var popup = document.getElementById("popup");
	
	// if a click happens within the 'popup' element...
	popup.addEventListener("click", function(e){
		
		// if what is clicked is the "Next" button in the shopping cart popup, then shopping cart is confirmed and submitted (to InsertCartServlet)
		if (e.target.id == "popup-cart-next") {
			e.target.parentElement.submit();
		}
	});
	
});