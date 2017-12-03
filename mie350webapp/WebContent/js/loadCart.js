window.addEventListener("load", function() {
	
	popupLoad = document.getElementById("popup-load");
	popupLoad.classList.add("loadPopupShown");
	
	popupLoad.addEventListener("click", function(e){
		
		if (e.target.id == "popup-load-close" || e.target.id == "popup-load-no" || e.target == this) {
			this.style.display = "none";
		}
		
		else if (e.target.id == "popup-load-yes") {
			e.target.parentElement.submit();
		}
	});
});