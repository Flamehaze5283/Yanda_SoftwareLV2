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
	<form action="manage/product?mode=upload" method="post" 
		enctype="multipart/form-data">
		 <input type="file"  name="uploadfile"/><br/>
			<input type="submit" value="上传" />
	</form>
</body>
</html>