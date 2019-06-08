<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/new_style_table3.css" />
<title>List of All Baskets</title>
</head>
<body>

	<div id="listDiv2">
		<table id="listTables">
			

			<c:forEach var="eachBasket" items="${listBasketsToJsp}">
				<tr>
				<td><c:out value="Basket ID" />
				<td><c:out value="Customer Name" />
				<td><c:out value="Basket Price" />
				<td><c:out value="Discount (%)" />
				<td><c:out value="Discounted Basket Price" />
			</tr>
				
				<tr>
					<td><c:out value="${eachBasket.basketId}" /> <!--  <td> <c:out value="${username}" />-->
					<td><c:out value="${eachBasket.customer.customerName }" />
					<td><c:out value="${eachBasket.basketPrice}" />
					<td><c:out value="${eachBasket.discountPercentage}" />
					<td><c:out value="${eachBasket.discountedBasketPrice}" /> 
					<c:set var="count" scope="page" value="0" /> 
					
					
					
					
					
					<div id="listDiv3">
						<c:forEach var="eachItem" items="${listOfItems}">
							<tr>

								<c:if test="${count == 0}">
									<tr>
										<td><c:out value="Product ID" />
										<td><c:out value="Product Name" />
										<td><c:out value="Product Quantity" /> <c:set
												var="count" value="1" />
									</tr>

							<tr>
								</c:if>
								<td><c:out value="${eachItem.key.id}" />
								<td><c:out value="${eachItem.key.productName}" />
								<td><c:out value="${eachItem.value}" />
							</tr>
						</c:forEach></div>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!--  

<div id="listDiv2">
<table id="listTables">
<tr>
	<td> <c:out value="Product ID" />
	<td> <c:out value="Product Name" />
	<td> <c:out value="Product Quantity" />
</tr>

<c:forEach var="eachItem" items="${listOfItems}">
<tr>
	<td> <c:out value="${eachItem.key.id}" />
	<td> <c:out value="${eachItem.key.productName}" />
	<td> <c:out value="${eachItem.value}" />
</tr>
</c:forEach>

</table>
</div>
-->
</body>
</html>
















<!--  
<td> <c:out value="${id}" />
<td> <c:out value="${productname}" />
<td> <c:out value="${quantity}" />
-->




























<!--  
<td> <c:out value="${eachBasket.customer.customerName}" />
<td> <c:out value="${eachBasket.listOfAddedItems.key.id}" />
<td> <c:out value="${eachBasket.listOfAddedItems.key.productName}" />
<td> <c:out value="${eachBasket.listOfAddedItems.value}" />-->




<!--  
<tr>
<td> <c:out value="${eachBasket.basketId}" />
<td> <c:out value="${eachBasket.customerName}" />
<td> <c:out value="${eachBasket.id}" />
<td> <c:out value="${eachBasket.productName}" />
<td> <c:out value="${eachBasket.value}" />
<td> <c:out value="${eachBasket.basketPrice}" />
<td> <c:out value="${eachBasket.discountPercentage}" />
<td> <c:out value="${eachBasket.discountedBasketPrice}" />
</tr>-->


