<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">



<style>
body, h1 {
	font-family: "Raleway", Arial, sans-serif
}

h1 {
	letter-spacing: 6px
}

.w3-row-padding img {
	margin-bottom: 12px
}
</style>


<title>Your Bank Account Details</title>
</head>
<body>


	<header class="w3-panel w3-center w3-opacity" style="padding:28px 1px">
	<h2>Hi ${sessionScope.username}</h2>
	<h4>You may update your bank balance on this page</h4>
	<h3 class="w3-xlarge">Your Current Bank Account Balance is:
		${customerBankAccount.accountBalance}</h3>
	<br>
	<br>

	<sf:form action="submitBankBalance2" method="post">

		<h6>Deposit Amount</h6>
		<input type="text" name="depositAmount" value="" />
		<input type="submit" name="commit" value="Update Balance" />
		
		<!-- --------------- -->
		<h3 class="w3-xlarge">${err}
		${checkOutTotal}</h3>
	<br>
	<br>

	<h3 class="w3-xlarge">${err1}
		${moneyMoreNeeded} ${err2}</h3>
	<br>
	<!-- ----if we want to add above code on this page
	and get rid of bankBalanceNotEnough jsp page then above messages has to come in as string as well 
	this is what done in homeController -->
	</sf:form> 
	<div class="w3-padding-32">
    <div class="w3-bar w3-border">
	<a href='${pageContext.request.contextPath}/listShoppingBaskets2' class="w3-bar-item w3-button">Go to Shopping Basket</a>
	</div>
	</div></header>





</body>
</html>


