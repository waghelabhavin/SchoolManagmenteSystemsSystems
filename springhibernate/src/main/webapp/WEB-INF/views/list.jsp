<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List Employee</title>
</head>
<body>
	<div id="content">
		<table border="1px">
			<tr bgcolor="gray">
				<td>ID</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Phone</td>
				<td>Email</td>
			</tr>
			
			<c:if test="${not empty employees}">
			 <c:forEach items="${employees}" var="emp" >
				<tr>
					<td>${emp.id}</td>
					<td>${emp.firstName}</td>
					<td>${emp.lastName}</td>
					<td>${emp.phone}</td>
					<td>${emp.email}</td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty employees}">
				<tr>
					<td colspan="5">No records found!</td>
				</tr>
			</c:if>
			<tr>
				<td align="center" colspan="5"><button
						onclick="location.href='<c:url value='/manage/add.do' />'">Add</button></td>
			</tr>
		</table>
	</div>
</body>
</html>