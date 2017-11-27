/**
 * 
 */

window.onload = function () {
	
	initializeSearchBar();
	
};

function initializeSearchBar() {
	
	//set up autocomplete functionality in searchBar
	var searchBar = document.getElementById("location-search-bar");
	var autocomplete = new google.maps.places.Autocomplete(searchBar);
	
	//whenever user selects a different suggestion from the dropdown, get the location (lat, lon) from the suggestion
	autocomplete.addListener("place_changed", function() {
		
		var location = autocomplete.getPlace().geometry.location;
		var lat = location.lat();
		var lon = location.lng();

		var submitBtn = document.getElementById("location-search-btn");
		var form = document.getElementById("finalize-form");
		
		//fill values in input fields for user's lat and lon
		document.getElementById("userLat").value = lat;
		document.getElementById("userLon").value = lon;
		
		submitBtn.addEventListener("click", function() {
			
			//alert(lat + ", " + lon);
			//console.log(lat + ", " + lon);
			
			//submit form
			form.submit();
			
		});
	});
	
}