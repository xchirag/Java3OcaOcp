<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>This page displays list of Available Products</title>
<link rel = "stylesheet" href="resources/new_style_table.css" />
</head>
<body>

<table id="listTables">
<tr>
<td> <c:out value="Product ID" />
<td> <c:out value="Product Name" />
<td> <c:out value="Product Price (£)" />
<td> <c:out value="Add Item in Basket" />
<td> <c:out value="Remove Item from Basket" />
</tr>

<c:forEach var="eachProduct" items="${listProductsToJsp}">
<tr>
<td> <c:out value="${eachProduct.id}" />
<td> <c:out value="${eachProduct.productName}" />
<td> <c:out value="${eachProduct.price}" />

<td> <sf:form action="addItemToBasket/${eachProduct.id}" method="GET">
    <input type="submit" name="commit" value="Add" />
</sf:form>

<td>
<sf:form action="removeItemFromBasket/${eachProduct.id}" method="GET">
    <input type="submit" name="commit" value="Remove" />
</sf:form>


</tr>
</c:forEach>
</table>

</body>
</html>