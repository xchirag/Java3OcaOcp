<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Address Page</title>
<link rel="stylesheet" href="resources/new_style_3.css" />

</head>
<body>

<div class="mainContainer"> 

	<sf:form action="submitUpdateAddress" method="post"
			modelAttribute="user">
		


			<!-- <sf:label path="password"> New Password:   </sf:label> <label> ${error1} </label>
			<sf:input path="password" size="30" value="" />

			<sf:label path="confirmPassword"> Confirm Your New Password:  </sf:label> <label> ${error2} </label>
			<sf:input path="confirmPassword" size="30" value="" /> -->
			
			<sf:label path="homeAddress"> New Home Address:   </sf:label> <label> ${error1} </label>
			<sf:input path="homeAddress" size="30" value="" />

			<!-- <sf:label path="emailAddress"> New Email Address:   </sf:label> <label> ${error4} </label>
			<sf:input path="emailAddress" size="30" value="" /> -->
			
			<input type="submit" name="commit" value="Update Your Address" />
			
			<a href='${pageContext.request.contextPath}/'>Go to Login page</a>
			
		</sf:form>
		<label> ${error} </label> <br>
</div>
</body>
</html>