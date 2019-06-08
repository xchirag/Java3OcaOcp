<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" href="new_style_2.css" type="text/css" /> -->

<link rel = "stylesheet" href="resources/new_style_3.css" />



<title>This is a login page</title>
</head>
<body>

<div class="mainContainer"> 
<sf:form action="submitLogin" method="POST" modelAttribute="user">
		<sf:label path="userName">Username</sf:label>
		<sf:input path="userName" size="30" value="vik2" cssClass="inputClass" />
		<br />
		<sf:label path="password">Password</sf:label>
		<sf:input path="password" size="30" value="vik2" />
		
		<input type="submit" name="commit" value="submit" />
		<a href='registration'>Go to register page</a> <br>
		
		<label> ${error} </label> <br>
		<label> ${error1} </label>
	</sf:form>
	<!-- 
	<a href="listItems"> List Me Items AVaiable</a>
	<a href="listProducts"> List Me Products AvAILAbLe</a>
	<a href="listCustomers"> List All Users</a>
	<a href="listShoppingBaskets"> List My Shopping Basket</a> -->
	
</div>





</body>
</html>

<!-- 

// firstly created an object to get the model sorted
// then created a daoImpl to get the methods sorted
// put and manage controller (get the items from database first into controller ==> then pass the list to jsp page ===> display list on jsp using tag)
// sort out jsp page  -->