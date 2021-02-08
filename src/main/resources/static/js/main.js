
//const http = new XMLHttpRequest()

//http.open("GET", "http://localhost:8080/memes")
//http.send()

//http.onload = () => console.log(http.responseText)

document.getElementById("myButton").onclick = function () {
        location.href = "viewMemes.html";
        loadImages();
};