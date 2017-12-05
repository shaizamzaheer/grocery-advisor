// this script takes care of submitting the query (item type) in the search bar to DisplayItemsServlet when the search button is clicked

window.addEventListener("load", function() {
	
	var itemSearchBtn = document.getElementById("item-search-btn");
	
	// if search button is clicked...
	itemSearchBtn.addEventListener("click", function() {
		//alert("search btn clicked!");
		/* TODO: if value of input field (search bar) is 0, then dont submit/do something */
		this.parentElement.submit(); //submit the item type entered to DisplayItemsServlet
		
	});
	
});