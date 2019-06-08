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
<title>Basket as a new style</title>
<style>
body,h1 {font-family: "Raleway", Arial, sans-serif}
h1 {letter-spacing: 6px}
.w3-row-padding img {margin-bottom: 12px}
</style>
</head>
<body>

	<c:set var="count" scope="page" value="0" />
	<table id="listTables">
		<c:if test="${count == 0}">
			<tr>
				<td><c:out value="Product ID" />
				<td><c:out value="Customer Name" />
				<td><c:out value="Product Name" />
				<td><c:out value="Product Quantity" />
				<td><c:out value="Product Price" /> <c:set var="count"
						value="1" />
			</tr>
		</c:if>
		<c:forEach var="eachBasket" items="${listProductToJsp}">
			<tr>
				<td><c:out value="${eachBasket.key.id}" /> <!-- product Id -->
				<td><c:out value="${sessionScope.username}" /> <!-- UserName as a session variable -->
				<td><c:out value="${eachBasket.key.productName}" /> <!--  product name -->
				<td><c:out value="${eachBasket.value}" /> <!-- product quantity -->
				<td><c:out value="${eachBasket.key.price}" /> <!-- product price -->
				<!--  <td><c:out value="${sessionScope.finalCheckoutPrice}" /> -->
			</tr>
		</c:forEach>
	</table>
<header class="w3-panel w3-center w3-opacity" style="padding:128px 16px">
	<div class="w3-padding-32">
    <div class="w3-bar w3-border">
      <a href='${pageContext.request.contextPath}/listProducts' class="w3-bar-item w3-button">Return To Product List</a>
      <a href='${pageContext.request.contextPath}/checkout' class="w3-bar-item w3-button">Checkout</a>
     
    </div>
  </div>

</header>

</body>
</html>











