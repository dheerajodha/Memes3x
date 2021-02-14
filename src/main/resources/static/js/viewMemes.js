//Passing a XMLHttpRequest (XHR) objects to interact with server to get the data from DB via our API
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      //parse our responseText into JSON array of object
      var myObj = JSON.parse(this.responseText);	
      var totalMemes = Object.keys(myObj).length;
      var card = document.getElementById("viewMemes");

      //Logic to display "atmost" 100 memes 
      var totalMemesToBeDisplayed = ((totalMemes > 100)? 100 : totalMemes);

      //loop through the JSON array and place value of each of its object into proper HTML tag
      for (let i = totalMemesToBeDisplayed-1; i >= 0; i--) {
          const element = myObj[i];
          
          //Creating Heading tag for displaying "Name" of the Meme poster
          var nameInfo = document.createElement("h2");
          nameInfo.className = "name";
          nameInfo.className = "card";
          nameInfo.innerHTML = element.name;

          //Creating Heading tag for displaying "Caption"
          var captionInfo = document.createElement("p");
          captionInfo.className = "caption";
          captionInfo.className = "card"
          captionInfo.innerHTML = element.caption;

          //Creating Heading tag for displaying "Meme" itself
          var imgUrl = new Image(); 
          imgUrl.className = "url";
          imgUrl.className = "card"
          imgUrl.src = element.url;
          
          //Storing the extracted data into a dictionary for ease of processing
          var dataDict = {
            imageId: element.imageId,
            name: element.name,
            caption: element.caption,
            url: imgUrl.src 
          };

          //Creating new div tag to store our new fetched Meme
          var tempCard = document.createElement("div");
          tempCard.className = "card";

          //We store the html content (in string form) as the innerHTML of our newly created div tag
          tempCard.innerHTML = cardTemplate(dataDict);

          //We append the created div tag as the next child of the main outer div tag
          card.appendChild(tempCard);
      }
    }
};

//This function returns a string which contains html tags with properly feeded data to them
var cardTemplate = function (parameter) {
	return `
		<h2 style="text-align: left; padding:18px 10px 0px 7px">${parameter.name}</h2>
    <p style="text-align: left; padding: 0 0 0 7px">${parameter.caption}<p/><br>
    <img style="vertical-align: left; border-width: 0 0 2px; padding: 0 0 25px 0" src=${parameter.url} width="70%" height="50%"><br>`;
};

//This function redirects us to the 'index.html' page
function goBackToMainPage() {
  location.href = "index.html";
  loadImages();
}

//Here, we call a GET request to fetch all the memes from our database via GET Endpoint of our API
xmlhttp.open("GET", "http://localhost:8080/memes/");
xmlhttp.send();