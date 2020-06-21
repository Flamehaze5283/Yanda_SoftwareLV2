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
	<form method="post" action="manage/product?mode=save&categoryId=1&name=海尔洗衣机&subtitle=海尔大促销&mainImages=test2.jpg&subImages=test.jpg&detail=detailtext&price=1000&stock=100&status=1&id=6">
<!-- 		<input type="text" name="passwordOld" value="123"><br/> -->
<!-- 		<input type="text" name="categoryId" value="1"><br/>
		<input type="text" name="categoryName" value="脉动"><br/> -->
		<input id="提交" type="submit">	
	</form>
</body>
</html>