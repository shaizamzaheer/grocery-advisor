window.addEventListener("load", function(){ 
//start of transport-btn click listener
  document.getElementById("transport-btns").addEventListener("click", function(e) {
    var btns = this.children;
    var prevSelected = "";
    for (var i = 0; i < btns.length; i++) {
      if (btns[i].classList.contains("transport-selected")) {
        prevSelected = btns[i];
        break;
      }
    }
    if (e.target.tagName == "BUTTON" && !e.target.classList.contains("transport-selected")) {
      e.target.classList.add("transport-selected");
      prevSelected.classList.remove("transport-selected");
      
      if (e.target.id == "transport-walk") {
        this.nextElementSibling.value = "walk";
      }
      
      else if (e.target.id == "transport-bike") {
        this.nextElementSibling.value = "bike";
      }
      
      else if (e.target.id == "transport-car") {
        this.nextElementSibling.value = "car";
      }
      
      else if (e.target.id == "transport-transit") {
        this.nextElementSibling.value = "transit";
      }
    }
    
  }); //end of transport-btn click listener
  
  document.getElementById("radio-choices-container").addEventListener("click", function(e){
    if (e.target == this.children[2].children[0]) {
      this.nextElementSibling.style.display = "block";
    }
    
    else {
      this.nextElementSibling.style.display = "none";
    }
  });
}); 