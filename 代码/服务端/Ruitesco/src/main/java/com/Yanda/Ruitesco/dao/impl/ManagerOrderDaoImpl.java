package com.Yanda.Ruitesco.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Yanda.Ruitesco.dao.IManagerOrderDao;
import com.Yanda.Ruitesco.dataResp.type.OrderData;
import com.Yanda.Ruitesco.dataResp.type.OrderItemVo;
import com.Yanda.Ruitesco.dataResp.type.OrderShippingVoData;
import com.Yanda.Ruitesco.javabean.Order;
import com.Yanda.Ruitesco.javabean.OrderDetail;
import com.Yanda.Ruitesco.javabean.Product;
import com.Yanda.Ruitesco.javabean.ShippingAddr;
import com.Yanda.Ruitesco.utils.JdbcUtil;

public class ManagerOrderDaoImpl implements IManagerOrderDao{
	@Override
	public List<OrderData> getOrderList(int startRow, int endRow) {
		List<Order> temp=JdbcUtil.executeQuery("select * from `order` limit ?,?",Order.class, startRow,endRow-startRow+1);
		if(temp==null)
		{
			return null;
		}
		List<OrderData> result=new ArrayList<OrderData>(); 
		for(Order x : temp)
		{
			OrderData orderdata=new OrderData();
			orderdata.setOrderNo(x.getOrder_no());
			orderdata.setPayment(x.getPayment());
			orderdata.setPaymentType(x.getPayment_type());
			orderdata.setPaymentTypeDesc(OrderData.statue.get(x.getPayment_type()));
			orderdata.setPostage(x.getPostage());
			orderdata.setStatus(x.getStatus());
			orderdata.setStatusDesc(OrderData.paymenttype.get(x.getStatus()));
			orderdata.setPaymentTime(x.getPayment_time());
			orderdata.setSendTime(x.getPayment_time());
			orderdata.setEndTime(x.getEnd_time());
			orderdata.setCloseTime(x.getClose_time());
			orderdata.setCreateTime(x.getCreate_time());
			orderdata.setOrderItemVoList(this.getOrderItemVoList(x.getOrder_no()));
			orderdata.setTotal(orderdata.getOrderItemVoList().size());
			orderdata.setImageHost("");
			orderdata.setShoppingId(x.getShipping_id());
			orderdata.setShippingVo(this.getShippingVo(x.getShipping_id()));
			result.add(orderdata);
		}
		return result;
	}

	@Override
	public List<OrderItemVo> getOrderItemVoList(int orderNo) {
		List<OrderDetail> temp=JdbcUtil.executeQuery("select * from order_detail where order_no=?", OrderDetail.class, orderNo);
		if(temp==null)
		{
			return null;
		}
		List<OrderItemVo> result=new ArrayList<OrderItemVo>();
		for(OrderDetail x : temp)
		{
			OrderItemVo orderItemVo=new OrderItemVo(x);
			orderItemVo.setProductImage(this.productname2Image(orderItemVo.getProductName()));
			result.add(orderItemVo);
		}
		return result;
	}

	@Override
	public OrderShippingVoData getShippingVo(int shippingId) {
		List<ShippingAddr> temp=JdbcUtil.executeQuery("select * from shipping_addr where id=?",ShippingAddr.class ,shippingId);
		OrderShippingVoData result = new OrderShippingVoData(temp.get(0));
		return result;
	}

	@Override
	public int getNumOfAllOrder() {
		return JdbcUtil.executeQueryInt("select count(*) from `order`");
	}

	@Override
	public List<OrderData> getOrderByUserName(String username,int startRow,int endRow) {
		int userId=this.username2Id(username);
		List<Order> temp=JdbcUtil.executeQuery("select * from `order` where user_id=? limit ?,?",Order.class,userId,startRow,endRow-startRow+1);
		if(temp==null)
		{
			return null;
		}
		List<OrderData> result=new ArrayList<OrderData>(); 
		for(Order x : temp)
		{
			OrderData orderdata=new OrderData();
			orderdata.setOrderNo(x.getOrder_no());
			orderdata.setPayment(x.getPayment());
			orderdata.setPaymentType(x.getPayment_type());
			orderdata.setPaymentTypeDesc(OrderData.statue.get(x.getPayment_type()));
			orderdata.setPostage(x.getPostage());
			orderdata.setStatus(x.getStatus());
			orderdata.setStatusDesc(OrderData.paymenttype.get(x.getStatus()));
			orderdata.setPaymentTime(x.getPayment_time());
			orderdata.setSendTime(x.getPayment_time());
			orderdata.setEndTime(x.getEnd_time());
			orderdata.setCloseTime(x.getClose_time());
			orderdata.setCreateTime(x.getCreate_time());
			orderdata.setOrderItemVoList(this.getOrderItemVoList(x.getOrder_no()));
			orderdata.setTotal(orderdata.getOrderItemVoList().size());
			orderdata.setImageHost("");
			orderdata.setShoppingId(x.getShipping_id());
			orderdata.setShippingVo(this.getShippingVo(x.getShipping_id()));
			result.add(orderdata);
		}
		return result;
	}

	@Override
	public OrderData getOrder(int orderNo) {
		List<Order> temp=JdbcUtil.executeQuery("select * from `order` where order_no=?",Order.class,orderNo);
		if(temp==null)
		{
			return null;
		}
		Order x=temp.get(0);
		OrderData result=new OrderData();

		result.setOrderNo(x.getOrder_no());
		result.setPayment(x.getPayment());
		result.setPaymentType(x.getPayment_type());
		result.setPaymentTypeDesc(OrderData.statue.get(x.getPayment_type()));
		result.setPostage(x.getPostage());
		result.setStatus(x.getStatus());
		result.setStatusDesc(OrderData.paymenttype.get(x.getStatus()));
		result.setPaymentTime(x.getPayment_time());
		result.setSendTime(x.getPayment_time());
		result.setEndTime(x.getEnd_time());
		result.setCloseTime(x.getClose_time());
		result.setCreateTime(x.getCreate_time());
		result.setOrderItemVoList(this.getOrderItemVoList(x.getOrder_no()));
		result.setTotal(result.getOrderItemVoList().size());
		result.setImageHost("");
		result.setShoppingId(x.getShipping_id());
		result.setShippingVo(this.getShippingVo(x.getShipping_id()));
		
		return result;
	}

	@Override
	public boolean sendGoods(int orderNo) {
		if(JdbcUtil.executeQueryInt("select status from `order` where order_no=?", orderNo)==2)
		{
			if(JdbcUtil.executeUpdate("update `order` set status=3 where order_no=?",orderNo)>0)
			{
				Date date = new Date();
				Timestamp sendtime = new Timestamp(date.getTime());
				if(JdbcUtil.executeUpdate("update `order` set send_time=? where order_no=?",sendtime,orderNo)>0)
				{
					return true;
				}
				else
				{
					JdbcUtil.executeUpdate("update `order` set status=2 where order_no=?",orderNo);
				}
			}
		}
		return false;
	}

	@Override
	public int username2Id(String username) {
		return JdbcUtil.executeQueryInt("select id from user where username=?", username);
	}

	@Override
	public String productname2Image(String productname) {
		String sql = "select * from sales where name = ?";
		List<Product> product = JdbcUtil.executeQuery(sql, Product.class, productname);
		if(product != null)
			return product.get(0).getMainImage();
		else
			return "Î´ÕÒµ½Í¼Æ¬Â·¾¶";
	}
	

}
