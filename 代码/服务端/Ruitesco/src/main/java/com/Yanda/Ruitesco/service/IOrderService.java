package com.Yanda.Ruitesco.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.Yanda.Ruitesco.javabean.Page;
import com.Yanda.Ruitesco.utils.response.MessageType;

public interface IOrderService {
	public MessageType<Object> createOrder(int shipping_id, BigDecimal payment, BigDecimal postage, int userId, String comment);//�ӹ��ﳵ���ɶ���
	public MessageType<Object> createOrder(int product_id, int quantity, int shipping_id, BigDecimal payment, BigDecimal postage, int userId, String comment);//�����������ɶ���
	public MessageType<Object> GetAllOrderByUserId(int user_id, Page page);		//��ȡ���û������ж�����������
	public MessageType<Object> GetDetailByOrderNo(int order_no);				//��ȡ�ö����Ķ�������
	public MessageType<Object> CancelOrder(int order_no);						//ȡ���ö���
	public MessageType<Object> PayOrder(int order_no);							//֧������
	public MessageType<Object> Alipay_Callback(Map<String, String> map);		//֧�����ص�
	public int GetStatusByOrderNo(int order_no);
}
