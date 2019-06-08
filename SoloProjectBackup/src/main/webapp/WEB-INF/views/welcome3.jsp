<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<title>You are logged!</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1 {font-family: "Raleway", Arial, sans-serif}
h1 {letter-spacing: 6px}
.w3-row-padding img {margin-bottom: 12px}
</style>
<body>

<!-- !PAGE CONTENT! -->
<div class="w3-content" style="max-width:1500px">

<!-- Header -->
<header class="w3-panel w3-center w3-opacity" style="padding:128px 16px">
  <h1 class="w3-xlarge">Lets Start Shopping</h1>
  <h1>${sessionScope.username}</h1>
  
  <div class="w3-padding-32">
    <div class="w3-bar w3-border">
    <a href="listProducts" class="w3-bar-item w3-button"> List Products</a>
	<a href="listCustomers" class="w3-bar-item w3-button w3-hide-small"> List Users</a>
	<a href="listShoppingBaskets2" class="w3-bar-item w3-button"> My Shopping Basket</a>
	<a href="listShoppingBaskets" class="w3-bar-item w3-button w3-hide-small">OLD way</a>
	<a href="updateUser" class="w3-bar-item w3-button"> Update your Details</a>
	<a href="pastOrders" class="w3-bar-item w3-button"> Past Orders</a>
	<a href="logout" class="w3-bar-item w3-button w3-hide-small">Logout</a>
    </div>
  </div>
</header>

 
<!-- End Page Content -->
</div>

<!-- Footer -->
<footer class="w3-container w3-padding-64 w3-light-grey w3-center w3-large"> 
  <i class="fa fa-facebook-official w3-hover-opacity"></i>
  <i class="fa fa-instagram w3-hover-opacity"></i>
  <i class="fa fa-snapchat w3-hover-opacity"></i>
  <i class="fa fa-pinterest-p w3-hover-opacity"></i>
  <i class="fa fa-twitter w3-hover-opacity"></i>
  <i class="fa fa-linkedin w3-hover-opacity"></i>
  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank" class="w3-hover-text-green">Chirag</a></p>
</footer>

</body>
</html>