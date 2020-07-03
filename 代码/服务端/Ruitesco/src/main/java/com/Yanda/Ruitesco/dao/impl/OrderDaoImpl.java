package com.Yanda.Ruitesco.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.Yanda.Ruitesco.dao.IOrderDao;
import com.Yanda.Ruitesco.javabean.Order;
import com.Yanda.Ruitesco.javabean.OrderDetail;
import com.Yanda.Ruitesco.utils.JdbcUtil;

public class OrderDaoImpl implements IOrderDao {

	@Override
	public int CreateOrder(int user_id, int shipping_id, BigDecimal payment, BigDecimal postage, String comment) {
		// TODO Auto-generated method stub
		//创建订单(未支付状态)
		String sql = "insert into `order` (user_id, shipping_id, payment, payment_type, postage, status, `comment`, create_time) values(?, ?, ?, 1, ?, ?, ?, ?)";
		Date date = new Date();
	    //从前端或者自己模拟一个日期格式，转为String即可
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = format.format(date);
		int result = JdbcUtil.executeUpdate(sql, user_id, shipping_id, payment, postage, 1, comment, dateStr);
		if(result <= 0)
			return result;
		else {
			sql = "select id from `order` where user_id = ? and shipping_id = ? and payment = ? and postage = ? and create_time = ? and `comment` = ?";
			int id = JdbcUtil.executeQueryInt(sql, user_id, shipping_id, payment, postage, dateStr, comment);
			sql = "update `order` set order_no = ? where user_id = ? and shipping_id = ? and payment = ? and postage = ? and create_time = ? and `comment` = ?";
			JdbcUtil.executeUpdate(sql, id, user_id, shipping_id, payment, postage, dateStr, comment);
			return id;
		}
	}
	
	public List<Order> GetOrder(int id){
		//获取创建订单的order_no
		String sql = "select * from `order` where id = ?";
		return JdbcUtil.executeQuery(sql, Order.class, id);
	}

	@Override
	public List<Order> GetOrderByOrderNo(int order_no) {
		// TODO Auto-generated method stub
		String sql = "select * from `order` where order_no = ?";
		return JdbcUtil.executeQuery(sql, Order.class, order_no);
	}

	@Override
	public int AddOrderDetail(List<OrderDetail> detail) {
		// TODO Auto-generated method stub
		String sql = "insert into order_detail (order_no, user_id, product_id, product_name, current_unit_price, quantity, total_price, create_time) values(?, ?, ?, ?, ?, ?, ?, ?)";
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = format.format(date);
		int result = 0;
		for(int i = 0; i < detail.size(); i++) {
			result += JdbcUtil.executeUpdate(sql, detail.get(i).getOrder_no(), detail.get(i).getUser_id(), detail.get(i).getProduct_id(), detail.get(i).getProduct_name(), detail.get(i).getCurrent_unit_price(), detail.get(i).getQuantity(), detail.get(i).getTotal_price(), dateStr);
		}
		return result;
	}

	@Override
	public List<Order> GetAllOrder(int user_id) {
		// TODO Auto-generated method stub
		String sql = "select * from `order` where user_id = ?";
		return JdbcUtil.executeQuery(sql, Order.class, user_id);
	}

	@Override
	public List<OrderDetail> GetOrderDetailByOrderNo(int order_no) {
		// TODO Auto-generated method stub
		String sql = "select * from order_detail where order_no = ?";
		return JdbcUtil.executeQuery(sql, OrderDetail.class, order_no);
	}

	@Override
	public int CancelOrder(int order_no) {
		// TODO Auto-generated method stub
		String sql = "update `order` set status = 0 where order_no = ?";
		return JdbcUtil.executeUpdate(sql, order_no);
	}

	@Override
	public int UpdateOrder(Order order) {
		// TODO Auto-generated method stub
		String sql = "update `order` set status = ?, payment_time = ? where order_no = ?";
		return JdbcUtil.executeUpdate(sql, order.getStatus(), order.getPayment_time(), order.getOrder_no());
	}
}
