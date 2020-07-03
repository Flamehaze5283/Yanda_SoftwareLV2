package com.Yanda.Ruitesco.dataResp.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderData {	
	//statusDesc,statusDesc
	public static final Map<Integer,String> paymenttype;
	public static final Map<Integer,String> statue;
	static
	{
		paymenttype=new HashMap<Integer,String>();
		statue=new HashMap<Integer,String>();
		paymenttype.put(1, "在线支付");
		paymenttype.put(2, "线下支付");
		statue.put(1, "未付款");
		statue.put(0, "已取消");
		statue.put(2, "已付款");
		statue.put(3, "已发货");
		statue.put(4, "交易成功");
		statue.put(5, "交易关闭");

	}
	
	
	int orderNo;
	BigDecimal payment;
	int  paymentType;
	String paymentTypeDesc;
	BigDecimal postage;
	int status;
	String statusDesc;
	Timestamp paymentTime;
	Timestamp sendTime;
	Timestamp endTime;
	Timestamp closeTime;
	Timestamp createTime;
	List<OrderItemVo> orderItemVoList;
	int total;
	String imageHost;
	int shoppingId;
	String receiverName;
	OrderShippingVoData shippingVo;
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentTypeDesc() {
		return paymentTypeDesc;
	}
	public void setPaymentTypeDesc(String paymentTypeDesc) {
		this.paymentTypeDesc = paymentTypeDesc;
	}
	public BigDecimal getPostage() {
		return postage;
	}
	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Timestamp getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Timestamp paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Timestamp closeTime) {
		this.closeTime = closeTime;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public List<OrderItemVo> getOrderItemVoList() {
		return orderItemVoList;
	}
	public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
		this.orderItemVoList = orderItemVoList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getImageHost() {
		return imageHost;
	}
	public void setImageHost(String imageHost) {
		this.imageHost = imageHost;
	}
	public int getShoppingId() {
		return shoppingId;
	}
	public void setShoppingId(int shoppingId) {
		this.shoppingId = shoppingId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public OrderShippingVoData getShippingVo() {
		return shippingVo;
	}
	public void setShippingVo(OrderShippingVoData shippingVo) {
		this.shippingVo = shippingVo;
	}
	public static Map<Integer, String> getPaymenttype() {
		return paymenttype;
	}
	public static Map<Integer, String> getStatue() {
		return statue;
	}

	
	

	
	
}
