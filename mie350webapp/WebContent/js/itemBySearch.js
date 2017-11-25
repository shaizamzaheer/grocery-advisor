window.addEventListener("load", function() {
	
	var itemSearchBtn = document.getElementById("item-search-btn");
	
	itemSearchBtn.addEventListener("click", function() {
		alert("search btn clicked!");
		/* TODO: if value of input field (search bar) is 0, then dont submit/do something */
		this.parentElement.submit();
		
	});
	
});