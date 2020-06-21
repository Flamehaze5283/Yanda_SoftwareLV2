<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<form method="post" action="manage/product?mode=set_sale_status">
		<!-- <input type="text" name="productName" value="可口可乐"><br/> -->
		<input type="text" name="productId" value="1"><br/>
		<input type="text" name="status" value="2"><br/>
		<input id="提交" type="submit">	
	</form>
</body>
</html>