package com.Yanda.Ruitesco.utils.response.order;

import java.math.BigDecimal;

import com.Yanda.Ruitesco.dao.IProductDao;
import com.Yanda.Ruitesco.dao.impl.ProductDaoImpl;
import com.Yanda.Ruitesco.javabean.OrderDetail;
import com.Yanda.Ruitesco.javabean.Product;

public class OrderItemList {
	//商品信息列表
	int orderNo;
	int productId;
	String productName;
	String description;
	String productImage;
	BigDecimal currentUnitPrice;
	int quantity;
	BigDecimal totalPrice;
	
	public OrderItemList(OrderDetail orderDetail) {
		// TODO Auto-generated constructor stub
		IProductDao product_dao = new ProductDaoImpl();
		this.orderNo = orderDetail.getOrder_no();
		this.productId = orderDetail.getProduct_id();
		Product product = product_dao.GetProductById(this.productId).get(0);
		this.productName = product.getName();
		this.description = product.getSubtitle();
		this.productImage = product.getMainImage();
		this.currentUnitPrice = product.getPrice();
		this.quantity = orderDetail.getQuantity();
		this.totalPrice = orderDetail.getTotal_price();
	}
}
