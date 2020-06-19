<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    	session.setAttribute("username", "123456");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<form method="post" action="manage/category?mode=get_deep_category">
		<input type="text" name="categoryId" value="3"><br/>
<!-- 		<input type="text" name="categoryName" value="脉动"><br/> -->
		<input id="提交" type="submit">	
	</form>
</body>
</html>