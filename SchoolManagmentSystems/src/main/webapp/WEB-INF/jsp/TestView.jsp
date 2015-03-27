<!DOCTYPE html>
<%@page import="com.schoolManagment.springapp.web.controllers.TestController"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>Welcome</title>

<link href="${rootURL}resources/bootstrap/css/bootstrap.css" media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${rootURL}resources/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${rootURL}resources/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${rootURL}resources/js/app.js"></script>

</head>
<body>

<h3>Email: <sec:authentication property="name"/></h3>
<h3>
	${noOfRecord} record found!!
</h3>
<p>	<a href="${rootUrl}logout">Logout</a></p>
</body>
</html>