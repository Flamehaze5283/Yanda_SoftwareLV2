package com.Yanda.Ruitesco.dao;

import java.math.BigDecimal;
import java.util.List;

import com.Yanda.Ruitesco.javabean.Order;
import com.Yanda.Ruitesco.javabean.OrderDetail;

public interface IOrderDao {
	public int CreateOrder(int user_id, int shipping_id, BigDecimal payment, BigDecimal postage, String comment);		//��������
	public List<Order> GetOrder(int id);																				//��ȡ�����������Զ����ɵ�order_no
	public List<Order> GetOrderByOrderNo(int order_no);																	//��ȡ��order_no�Ķ�������
	public int AddOrderDetail(List<OrderDetail> detail);																//��Ӷ�������
	public List<Order> GetAllOrder(int user_id);																		//��ѯ�������ж���
	public List<OrderDetail> GetOrderDetailByOrderNo(int order_no);														//��ѯ�ö����󶨵Ķ�������
	public int CancelOrder(int order_no);																				//ȡ������
	public int UpdateOrder(Order order);
}
