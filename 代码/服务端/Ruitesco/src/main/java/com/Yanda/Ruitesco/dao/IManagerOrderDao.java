package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.dataResp.type.OrderData;
import com.Yanda.Ruitesco.dataResp.type.OrderItemVo;
import com.Yanda.Ruitesco.dataResp.type.OrderShippingVoData;



public interface IManagerOrderDao {
	public List<OrderData> getOrderList(int startRow,int endRow);
	public List<OrderItemVo> getOrderItemVoList(int orderNo);
	public OrderShippingVoData getShippingVo(int shoppingId);
	public int getNumOfAllOrder();
	public List<OrderData> getOrderByUserName(String username,int startRow,int endRow);
	public OrderData getOrder(int orderNo);
	public boolean sendGoods(int orderNo);
	public int username2Id(String username);
	public String productname2Image(String productname);
}
