package com.Yanda.Ruitesco.dao;

import java.math.BigDecimal;
import java.util.List;

import com.Yanda.Ruitesco.javabean.Order;
import com.Yanda.Ruitesco.javabean.OrderDetail;

public interface IOrderDao {
	public int CreateOrder(int user_id, int shipping_id, BigDecimal payment, BigDecimal postage, String comment);		//创建订单
	public List<Order> GetOrder(int id);																				//获取创建订单中自动生成的order_no
	public List<Order> GetOrderByOrderNo(int order_no);																	//获取该order_no的订单详情
	public int AddOrderDetail(List<OrderDetail> detail);																//添加订单详情
	public List<Order> GetAllOrder(int user_id);																		//查询个人所有订单
	public List<OrderDetail> GetOrderDetailByOrderNo(int order_no);														//查询该订单绑定的订单详情
	public int CancelOrder(int order_no);																				//取消订单
	public int UpdateOrder(Order order);
}
