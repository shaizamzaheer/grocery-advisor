window.addEventListener("load", function() {

	var popup = document.getElementById("popup");

	//when in/out button clicked...
	popup.addEventListener("click", function(e) {

		if (e.target.className == "inOutBtn") {
			console.log("A button was clicked!");
			var inCart = (e.target.value == 'false'); //if equals false, then becomes true; if not false, then becomes false
			var itemID = e.target.id;
			console.log("In or out of cart? " + inCart + " ItemID is: "	+ itemID);

			var xhrInOut = new XMLHttpRequest();
			console.log(encodeURI("ShoppingCartServlet?itemID=" + itemID + "&inCart="	+ inCart));
			xhrInOut.open("POST", encodeURI("ShoppingCartServlet?itemID=" + itemID + "&inCart=" + inCart));
			xhrInOut.onload = function() {

				var xhr2 = new XMLHttpRequest();
				xhr2.open("POST", "popup.jsp");
				xhr2.onload = function() {
					popup.innerHTML = xhr2.responseText;
					console.log(xhr2.responseText);
					popup.style.display = "block";
					closebtn.style.display = "block";

				};
				xhr2.send();

			};
			xhrInOut.send();
		}

	});

	//when number changed
	popup.addEventListener("input", function(e) {

		if (e.target.className == "popupItemQuantity") {
			console.log("A number was changed!");
			var quantity = e.target.value;
			var itemID = e.target.id;
			console.log("Quantity? " + quantity + " ItemID is: " + itemID);

			var xhrQty = new XMLHttpRequest();

			console.log(encodeURI("ShoppingCartServlet?itemID=" + itemID + "&quantity="	+ quantity));
			xhrQty.open("POST", encodeURI("ShoppingCartServlet?itemID=" + itemID	+ "&quantity=" + quantity));
			xhrQty.onload = function() {

				var xhr2 = new XMLHttpRequest();
				xhr2.open("POST", "popup.jsp");
				xhr2.onload = function() {
					popup.innerHTML = xhr2.responseText;
					console.log(xhr2.responseText);
					popup.style.display = "block";
					closebtn.style.display = "block";

				};
				xhr2.send();

			};
			xhrQty.send();
		}

	});

});