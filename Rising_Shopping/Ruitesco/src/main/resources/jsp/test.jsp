<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    	session.setAttribute("userName", "123456");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<form method="post" action="user?mode=reset_password">
<!-- 		<input type="text" name="email" value="111@qq.com"><br/>
		<input type="text" name="phone" value="13133132323"><br/>
		<input type="text" name="question" value="你吃了吗？"><br/>
		<input type="text" name="answer" value="吃了"><br/> -->
		<input type="text" name="passwordOld" value="123"><br/>
		<input type="text" name="passwordNew" value="1234"><br/>
		<input id="提交" type="submit">	
	</form>
</body>
</html>