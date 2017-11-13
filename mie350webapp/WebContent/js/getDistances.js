/**
 * 
 */

window.onload = function() {
	
	var url = document.getElementById("url").innerHTML;
	url = url.replace(/&amp;/g, '&');
	console.log(url);
	var form = document.getElementById("form");
	var inputDistances = "";
	
	var xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onload = function() {
		
		var data = JSON.parse(xhr.responseText);
		var distanceArr = data.rows[0].elements;
		
		for (i = 0; i < distanceArr.length; i++) {
			inputDistances = inputDistances + "<input type='hidden' value='" + distanceArr[i].distance.value + "' name='dist" + i + "' />";
		}
		
		form.innerHTML = inputDistances;
		
		form.submit();
	};
	xhr.send();
};