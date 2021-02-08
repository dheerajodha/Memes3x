var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      var myObj = JSON.parse(this.responseText);	

      var totalMemes = Object.keys(myObj).length;

      var totalMemesToBeDisplayed = (totalMemes > 100)? 100 : totalMemes;

      for (let i = totalMemesToBeDisplayed-1; i >= 0; i--) {
          const element = myObj[i];
          
          var name = document.createElement('p');
          name.innerHTML = element.name;

          var caption = document.createElement('p');
          caption.innerHTML = element.caption;

          var img = new Image(); 
          img.src = element.url; 

          //img.width = 750;
          //img.height = 550;
          document.getElementById("viewMemes").appendChild(name);
          document.getElementById("viewMemes").appendChild(caption);
          document.getElementById('viewMemes').appendChild(img); 
      }
    }
};
xmlhttp.open("GET", "http://localhost:8080/memes/");
xmlhttp.send();