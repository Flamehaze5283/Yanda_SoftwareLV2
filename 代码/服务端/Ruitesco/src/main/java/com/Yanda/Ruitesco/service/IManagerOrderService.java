package com.Yanda.Ruitesco.service;

import java.util.List;

import com.Yanda.Ruitesco.dataResp.type.OrderData;
import com.Yanda.Ruitesco.javabean.PageUserList;

public interface IManagerOrderService {
	public List<OrderData> GetOrderList(int startRow,int endRow);		//获取用户列表登录
	public PageUserList GetNextPage(PageUserList pageUserList);
	public List<OrderData> GetOrderListByUserName(String username,int startRow,int endRow);
	public OrderData GetOrder(int orderNo);
	public boolean SendGoods(int orderNo);
}
