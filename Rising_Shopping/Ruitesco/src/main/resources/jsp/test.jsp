<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<%
		request.setAttribute("type", "check_valid");
	%>
	<form method="post">
		<button type="submit">提交测试</button>
	</form>
</body>
</html>