<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel = "stylesheet" href="resources/new_style_table.css" />
<title>List of Customers</title>
</head>
<body>

<table id="listTables">
<tr>
<td> <c:out value="Customer Number" />
<td> <c:out value="Customer Name" />
<td> <c:out value="Address" />
<td> <c:out value="email" />
<td> <c:out value="shipAddress" />
</tr>

<c:forEach var="customer" items="${listCustomersToJsp}">
<tr>
<td> <c:out value="${customer.customerNumber}" />
<td> <c:out value="${customer.customerName}" />
<td> <c:out value="${customer.address}" />
<td> <c:out value="${customer.email}" />
<td> <c:out value="${customer.shipAddress}" />

</tr>
</c:forEach>
</table>


</body>
</html>