// this script takes care of prompting a returning user to load their previous shopping cart and handling the user's response in welcome.jsp

window.addEventListener("load", function() {
	
	popupLoad = document.getElementById("popup-load"); // the popup prompt
	popupLoad.classList.add("loadPopupShown"); // after the window is loaded, the popup fades in 0.6 sec after the loadPopupShown class is added, via transitions in css (see #popup-load and #popup-load.loadPopupShown in welcome.css)
	
	popupLoad.addEventListener("click", function(e){
		
		// if popup is closed via X button or "No" button or clicking in grey area...
		if (e.target.id == "popup-load-close" || e.target.id == "popup-load-no" || e.target == this) {
			this.style.display = "none"; // simply make the popup disappear (set display to none)
		}
		
		// if user clicks "Yes" button...
		else if (e.target.id == "popup-load-yes") {
			e.target.parentElement.submit(); // form submits, meaning LoadCartServlet is called to load the user's previous cart
		}
	});
});