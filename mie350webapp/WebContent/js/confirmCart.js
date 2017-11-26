window.addEventListener("load", function() {
	
	var popup = document.getElementById("popup");
	
	popup.addEventListener("click", function(e){
		
		if (e.target.id == "popup-cart-next") {
			e.target.parentElement.submit();
		}
	});
	
});