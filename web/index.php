<html>
<head>
<title>SMART ROOTS</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="shortcut icon" href="images\fav_icon.png">
<style>

body {
	font-family: "Lato", sans-serif;
	background-image: url('http://bgfons.com/upload/leaves_texture1258.jpg');
    background-repeat: no-repeat;
    background-position: right top;
    background-attachment: fixed;

	}

div.transbox {
    margin: 30px;
    background-color: #ffffff;

    opacity: 0.7;
    filter: alpha(opacity=70); /* For IE8 and earlier */
}

div.transbox p {
    margin: 5%;
    font-weight: bold;
    color: #000000;
}
		
.mySlides {display: none}

.w3-bar{
	float:right;
	}

	
</style>
</head>

<body>
<!--Navbar-->
<div class="w3-top">
  <div class="w3-bar w3-green w3-card-2 w3-padding-32 w3-center w3-large">
  <img style="float:left; width:5%; padding-left:15px;" src="images\RootsMonkey.png" alt="logo" class="w3-hide-small"> 
    <a class="w3-bar-item w3-button w3-padding-large w3-hide-medium w3-hide-large w3-right" href="javascript:void(0)" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    <a href="index.php" class="w3-bar-item w3-button w3-padding-large w3-hide-small">HOME</a>
    <a href="gettingStarted.php" class="w3-bar-item w3-button w3-padding-large w3-hide-small">GETTING STARTED</a>
    <a href="appFeatures.php" class="w3-bar-item w3-button w3-padding-large w3-hide-small">APP FEATURES</a>
    <a href="compPlanting.php" class="w3-bar-item w3-button w3-padding-large w3-hide-small">COMPANION PLANTING</a>
	<a href="account.php" class="w3-bar-item w3-button w3-padding-large w3-hide-small">ACCOUNT</a>
    <a href="login.php" class="w3-bar-item w3-button w3-padding-large w3-hide-small">LOGIN</a>
	<a href="about.php" class="w3-bar-item w3-button w3-padding-large w3-hide-small">ABOUT</a>
	<a href="contact.php" class="w3-bar-item w3-button w3-padding-large w3-hide-small">CONTACT</a>
  </div>
</div>

<!-- Navbar on small screens -->
<div id="navDemo" class="w3-bar-block w3-green w3-hide w3-hide-large w3-hide-medium w3-top" style="margin-top:46px">
<img style="float:center; width:20%; padding-left:30px; padding-bottom:10px;" src="images\RootsMonkey.png" alt="logo">
  <a href="index.php" class="w3-bar-item w3-button w3-padding-large">HOME</a>
  <a href="gettingStarted.php" class="w3-bar-item w3-button w3-padding-large">GETTING STARTED</a>
  <a href="appFeatures.php" class="w3-bar-item w3-button w3-padding-large">APP FEATURES</a>
  <a href="compPlanting.php" class="w3-bar-item w3-button w3-padding-large">COMPANION PLANTING</a>
  <a href="account.php" class="w3-bar-item w3-button w3-padding-large">ACCOUNT</a>
  <a href="login.php" class="w3-bar-item w3-button w3-padding-large">LOGIN</a>
  <a href="about.php" class="w3-bar-item w3-button w3-padding-large">ABOUT</a>
  <a href="contact.php" class="w3-bar-item w3-button w3-padding-large">CONTACT</a>
</div>

<!-- Page content -->
<div class="w3-content w3-white" style="max-width:2000px;margin-top:46px" id="index">

  <!-- Automatic Slideshow Images -->
  <div class="mySlides w3-display-container w3-center" style="margin-top:150px">
    <img src="images\RootsMonkey2.png" style="width:20%; max-height:620;">
      <div class="w3-container">
		<h4>Creating a substainable garden!</h4>  
	  </div>
  </div>
  <div class="mySlides w3-display-container w3-center">
    <img src="images\garden1.jpg" style="width:100%; max-height:620px;">     
  </div>
  <div class="mySlides w3-display-container w3-center">
    <img src="images\garden2.jpg" style="width:100%; max-height:620px;">
  </div>
</div>

  
<!-- Getting Started Section -->

<!--App Features-->





<!-- Companion Planting Section -->


<!--About Section-->




<!-- The Contact Section -->

  
<!-- End Page Content -->
</div>

<!-- Footer -->
<footer class="w3-container w3-padding-64 w3-center w3-opacity w3-light-grey w3-xlarge">
  <i class="fa fa-facebook-official w3-hover-opacity"></i>
  <i class="fa fa-instagram w3-hover-opacity"></i>
  <i class="fa fa-snapchat w3-hover-opacity"></i>
  <i class="fa fa-pinterest-p w3-hover-opacity"></i>
  <i class="fa fa-twitter w3-hover-opacity"></i>
  <i class="fa fa-linkedin w3-hover-opacity"></i>
  <p class="w3-medium">Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
</footer>

<script>
// Automatic Slideshow - change image every 4 seconds
var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}    
    x[myIndex-1].style.display = "block";  
    setTimeout(carousel, 4000);    
}

// Used to toggle the menu on small screens when clicking on the menu button
function myFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else { 
        x.className = x.className.replace(" w3-show", "");
    }
}
// When the user clicks anywhere outside of the modal, close it
var modal = document.getElementById('ticketModal');
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

</script>

</body>
</html>
