/**
 * 
 */

window.onload = function() {
	
	var url = document.getElementById("url").innerHTML;
	url = url.replace(/&amp;/g, '&');
	console.log(url);
	var div = document.getElementById("distances");
	var inputDistances = "";
	var distanceArr = [];
	var xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onload = function() {
		
		var data = JSON.parse(xhr.responseText);
		console.log(data);
		console.log(data.rows[0].elements);
		distanceArr = data.rows[0].elements;
		
		for (i = 0; i < distanceArr.length; i++) {
			inputDistances = inputDistances + "<input type='hidden' value='" + distanceArr[i].distance.value + "' />";
		}
		
		div.innerHTML = inputDistances;

	};
	xhr.send();
};