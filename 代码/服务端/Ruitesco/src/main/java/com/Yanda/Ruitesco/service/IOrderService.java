package com.Yanda.Ruitesco.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.Yanda.Ruitesco.javabean.Page;
import com.Yanda.Ruitesco.utils.response.MessageType;

public interface IOrderService {
	public MessageType<Object> createOrder(int shipping_id, BigDecimal payment, BigDecimal postage, int userId, String comment);//从购物车生成订单
	public MessageType<Object> createOrder(int product_id, int quantity, int shipping_id, BigDecimal payment, BigDecimal postage, int userId, String comment);//立即购买生成订单
	public MessageType<Object> GetAllOrderByUserId(int user_id, Page page);		//获取该用户的所有订单及其详情
	public MessageType<Object> GetDetailByOrderNo(int order_no);				//获取该订单的订单详情
	public MessageType<Object> CancelOrder(int order_no);						//取消该订单
	public MessageType<Object> PayOrder(int order_no);							//支付订单
	public MessageType<Object> Alipay_Callback(Map<String, String> map);		//支付宝回调
	public int GetStatusByOrderNo(int order_no);
}
