package com.Yanda.Ruitesco.utils.response.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.Yanda.Ruitesco.javabean.Order;
import com.Yanda.Ruitesco.javabean.OrderDetail;

public class OrderType {
	int orderNo;
	BigDecimal payment;
	int paymentType;
	BigDecimal postage;
	int status;
	Timestamp paymentTime;
	Timestamp sendTime;
	Timestamp endTime;
	Timestamp closeTime;
	Timestamp createTime;
	List<OrderItemList> orderItemVoList;
	int shippingId;
	String comment;
	
	public OrderType(Order order, List<OrderDetail> orderDetails) {
		// TODO Auto-generated constructor stub
		this.orderNo = order.getOrder_no();
		this.payment = order.getPayment();
		this.paymentType = order.getPayment_type();
		this.postage = order.getPostage();
		this.status = order.getStatus();
		this.paymentTime = order.getPayment_time();
		this.sendTime = order.getSend_time();
		this.endTime = order.getEnd_time();
		this.closeTime = order.getClose_time();
		this.createTime = order.getCreate_time();
		this.orderItemVoList = new ArrayList<OrderItemList>();
		for(int i = 0; i < orderDetails.size(); i++) {
			OrderItemList orderItemList = new OrderItemList(orderDetails.get(i));
			this.orderItemVoList.add(orderItemList);
		}
		this.shippingId = order.getShipping_id();
		this.comment = order.getComment();
	}
}
