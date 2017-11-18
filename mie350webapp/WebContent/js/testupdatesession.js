window.onload = function() {
  var popup = document.getElementById("popup");
  var displaybtn = document.getElementById("displaybtn");
  var closebtn = document.getElementById("closebtn");

  //click button to display popup
  displaybtn.addEventListener("click", function() {
	  var num = document.getElementById("num").value;
	  console.log(num);
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "TestUpdateSession?num=" + num);
    xhr.onload = function() {
    	var xhr2 = new XMLHttpRequest();
        xhr2.open("POST", "testpopup.jsp");
        xhr2.onload = function() {
        	popup.innerHTML = xhr2.responseText;
        	console.log(xhr2.responseText);
            popup.style.display = "block";
            closebtn.style.display = "block";
        	
        };
        xhr2.send();
//    	popup.innerHTML = '<%@ include file="popup.jsp" %>';
//        popup.style.display = "block";
//        closebtn.style.display = "block";
    	
    };
    xhr.send();
  });
  
  //click x button to close popup
  closebtn.addEventListener("click", function() {
    popup.style.display = "none";
    closebtn.style.display = "none";
  });
  
  //click grey area to close popup
    popup.addEventListener("click", function(e) {
      if (e.target == popup) {
        popup.style.display = "none";
        closebtn.style.display = "none";
      }
  });
};