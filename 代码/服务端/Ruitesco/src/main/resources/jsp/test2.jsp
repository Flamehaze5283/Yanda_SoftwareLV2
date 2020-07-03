<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% session.setAttribute("username", "123456"); %>
    <% session.setAttribute("userid", "2"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<form method="post" action="manage/category?mode=get_leaf">
		<!-- <input type="text" name="productName" value="可口可乐"><br/> -->
		<!-- <input type="text" name="detail" value="我是你爸爸"><br/>
		<input type="text" name="shippingId" value="2"><br/>
		<input type="text" name="receiverName" value="吴彦祖"><br/>
		<input type="text" name="receiverPhone" value="022-11144514"><br/>
		<input type="text" name="receiverMobile" value="1145147777"><br/>
		<input type="text" name="receiverProvince" value="天津"><br/>
		<input type="text" name="receiverCity" value="天津市"><br/>
		<input type="text" name="receiverAddress" value="天津市红桥区xx公路xx小区xx-xx-xx"><br/>
		<input type="text" name="receiverZip" value="300131"><br/> -->
<!-- 		<input type="text" name="product_id" value="1"><br/>
		<input type="text" name="quantity" value="1"><br/> -->
		<input type="text" name="shipping_id" value="1"><br/>
		<input type="text" name="payment" value="153.23"><br/>
		<input type="text" name="postage" value="5.00"><br/>
		<input type="text" name="orderNo" value="19"><br/>
		<input id="提交" type="submit">	
	</form>
</body>
</html>