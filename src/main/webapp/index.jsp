<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emaillist</title>
</head>
<body>
<h1>Emaillist</h1>
<ul>
	<li><a href = "<%=request.getContextPath() + "/Emaillist" %>">Model 1</a></li>
	<li><a href = "<%=request.getContextPath() + "/el" %>"> Model 2 (Servlet + JSP)</a></li>
</ul>
</body>
</html>