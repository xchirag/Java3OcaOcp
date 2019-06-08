<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/new_style_table3.css" />

<style>
table {
    border-collapse: collapse;
    width: 50%;
}

th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

tr:hover {background-color:#f5f5f5;}

body,h1 {font-family: "Raleway", Arial, sans-serif}
h1 {letter-spacing: 6px}
.w3-row-padding img {margin-bottom: 12px}

</style>

<title>Thank you for making Payment</title>
</head>
<body>


<header class="w3-panel w3-center w3-opacity" style="padding:8px 1px">
<h3>Hi ${sessionScope.username}</h3>
<h3>Thank you for confirming Order</h3>
<h4>You have made payment of : £ ${sessionScope.finalCheckoutPrice}</h4>
<h5>Your account balance is : £ ${balance}</h5>
<h4>Items will be delivered at your Address : ${shippingAddress}</h4>
<div class="w3-padding-32">
<div class="w3-bar w3-border">
<!--  <a href="updateAddress" class="w3-bar-item w3-button">Update Your Address</a> -->
<a href="logout" class="w3-bar-item w3-button">Logout</a>
</div>
</div>
</header>


</body>
</html>


