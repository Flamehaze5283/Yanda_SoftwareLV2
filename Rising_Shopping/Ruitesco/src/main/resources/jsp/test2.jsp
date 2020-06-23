<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% session.setAttribute("username", "123456"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<form method="post" action="manage/category?mode=delete_category">
		<!-- <input type="text" name="productName" value="可口可乐"><br/> -->
		<input type="text" name="category_id" value="5"><br/>
		<input id="提交" type="submit">	
	</form>
</body>
</html>