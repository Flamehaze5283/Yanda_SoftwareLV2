package com.Yanda.Ruitesco.dataResp.type;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.Yanda.Ruitesco.javabean.OrderDetail;

public class OrderItemVo {
	
	int orderNo;
	int productId;
	String productName;
	String productImage;
	BigDecimal currentUnitPrice;
	int quantity;
	BigDecimal totalPrice;
	Timestamp createTime;
	
	
	public OrderItemVo(int orderNo, int productId, String productName, String productImage,
			BigDecimal currentUnitPrice, int quantity, BigDecimal totalPrice, Timestamp createTime) {
		this.orderNo = orderNo;
		this.productId = productId;
		this.productName = productName;
		this.productImage = productImage;
		this.currentUnitPrice = currentUnitPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.createTime = createTime;
	}
	public OrderItemVo(OrderDetail orderDetail)
	{
		this.orderNo = orderDetail.getOrder_no();
		this.productId = orderDetail.getProduct_id();
		this.productName = orderDetail.getProduct_name();
		this.productImage = orderDetail.getProduct_image();
		this.currentUnitPrice = orderDetail.getCurrent_unit_price();
		this.quantity = orderDetail.getQuantity();
		this.totalPrice = orderDetail.getTotal_price();
		this.createTime = orderDetail.getCreate_time();
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public BigDecimal getCurrentUnitPrice() {
		return currentUnitPrice;
	}
	public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
		this.currentUnitPrice = currentUnitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
