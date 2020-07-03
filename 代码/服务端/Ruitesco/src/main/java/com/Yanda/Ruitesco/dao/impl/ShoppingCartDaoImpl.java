package com.Yanda.Ruitesco.dao.impl;

import java.util.List;

import com.Yanda.Ruitesco.dao.IShoppingCartDao;
import com.Yanda.Ruitesco.javabean.ShoppingCart;
import com.Yanda.Ruitesco.utils.JdbcUtil;

public class ShoppingCartDaoImpl implements IShoppingCartDao{

	@Override
	public List<ShoppingCart> getCartList(int user_id) {		
		return JdbcUtil.executeQuery("select * from shopping_cart where user_id=?", ShoppingCart.class, user_id);
	}

	@Override
	public boolean addProduct(ShoppingCart param) {
		if(JdbcUtil.executeUpdate("insert into shopping_cart(user_id,product_id,quantity,checked,create_time,update_time) values(?,?,?,?,?,?)",param.getUser_id(),param.getProduct_id(),param.getQuantity(),param.getChecked(),param.getCreate_time(),param.getUpdate_time())>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean updateCartProduct(int user_id, int product_id, int count) {
		if(JdbcUtil.executeUpdate("update shopping_cart set quantity=? where user_id=? and product_id=?",count,user_id,product_id)>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean removeCartProduct(int user_id, int product_id) {
		if(JdbcUtil.executeUpdate("delete from shopping_cart where user_id=? and product_id=?",user_id,product_id)>0)
			return true;
		else
			return false;
	}

	@Override
	public ShoppingCart queryRecord(int user_id, int product_id) {
		List<ShoppingCart> list = JdbcUtil.executeQuery("select * from shopping_cart where user_id=? and product_id=?",ShoppingCart.class, user_id,product_id);
		if(list==null)
			return null;
		else
			return list.get(0);
	}
	public int queryProductStock(int product_id)
	{
		return JdbcUtil.executeQueryInt("select stock from sales where id=?", product_id);
	}

	@Override
	public boolean setChecked(int user_id, int product_id) {
		if(JdbcUtil.executeUpdate("update shopping_cart set checked=? where user_id=? and product_id=?",true,user_id,product_id)>0)
			return true;
		return false;
	}

	@Override
	public boolean resetChecked(int user_id, int product_id) {
		if(JdbcUtil.executeUpdate("update shopping_cart set checked=? where user_id=? and product_id=?",false,user_id,product_id)>0)
			return true;
		return false;
	}

	@Override
	public int setCheckedAll(int user_id) {
		
		return JdbcUtil.executeUpdate("update shopping_cart set checked=? where user_id=? and checked=0",true,user_id);

	}
	
	@Override
	public int setUnCheckedAll(int user_id) {
		return JdbcUtil.executeUpdate("update shopping_cart set checked=? where user_id=? and checked=1",false,user_id);
	}
	

	@Override
	public int countUnCheckedByUserId(int user_id) {
		return JdbcUtil.executeQueryInt("select Count(*) from shopping_cart where user_id=? and checked=0",user_id);
	}

	@Override
	public int countCheckedByUserId(int user_id) {
		return JdbcUtil.executeQueryInt("select Count(*) from shopping_cart where user_id=? and checked=1",user_id);
	}

	@Override
	public int getProductCount(int user_id) {

		return JdbcUtil.executeQueryInt("select Count(*) from shopping_cart where user_id=?",user_id);
	}




}
