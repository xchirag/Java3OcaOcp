<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Items </title>
</head>
<body>

List Of the Sale Items

<table>
<c:forEach var="eachItem" items="${listAttribute}"> 
<!-- eachItem is the variable name within this method only  -->
<tr>  <td> <c:out value="${eachItem.itemId}" /> </td></tr>
<tr> <td> <c:out value="${eachItem.itemName}" /> </td> <td> <input type="button" value="addToBasket"> </td> 
</c:forEach>
</table>

</body>
</html>

<!-- 

// firstly created an object to get the model sorted
// then created a daoImpl to get the methods sorted
// put and manage controller (get the items from database first into controller ==> then pass the list to jsp page ===> display list on jsp using tag)
// sort out jsp page  -->

<!--  on the page add the tags first -->