package com.Yanda.Ruitesco.service.impl;

import java.util.List;

import com.Yanda.Ruitesco.dao.IShoppingCartDao;
import com.Yanda.Ruitesco.dao.impl.ShoppingCartDaoImpl;
import com.Yanda.Ruitesco.javabean.ShoppingCart;
import com.Yanda.Ruitesco.service.IShoppingCartService;

public class ShoppingCartServiceImpl implements IShoppingCartService {
	IShoppingCartDao cart_dao = new ShoppingCartDaoImpl();

	@Override
	public List<ShoppingCart> GetCartList(int user_id) {

		return cart_dao.getCartList(user_id);
	}
	
	//状态码：
	//0添加成功	1添加失败	2购物车数量超过库存
	@Override
	public int Add(int user_id, int product_id,int count) {
		if(count<1)
		{
			return 999;
		}
		ShoppingCart shoppingcart = cart_dao.queryRecord(user_id, product_id);
		if(shoppingcart==null)
		{
				ShoppingCart data = new ShoppingCart(user_id,product_id,count);
				if(cart_dao.addProduct(data)==true)
					return 0;
				else
					return 1;
		}
		
		count=count+shoppingcart.getQuantity();
		if(cart_dao.updateCartProduct(user_id, product_id, count)==true)
			return 0;
		else 
			return 1;
	}

	//0添加成功	1添加失败	2购物车数量超过库存
	@Override
	public int Update(int user_id, int product_id, int count) {
		if(count<1)
		{
			return 999;
		}

		if(cart_dao.updateCartProduct(user_id, product_id, count)==true)
			return 0;
		else
			return 1;
	}

	@Override
	public boolean Remove(int user_id, int product_id) {
		
		return cart_dao.removeCartProduct(user_id,product_id);
	}
	
	public boolean Select(int user_id,int product_id)
	{
		return cart_dao.setChecked(user_id,product_id);
	}
	
	public boolean UnSelect(int user_id,int product_id)
	{
		return cart_dao.resetChecked(user_id,product_id);
	}

	@Override
	public int SelectAll(int user_id) {
		int expect=cart_dao.countUnCheckedByUserId(user_id);
		int real=cart_dao.setCheckedAll(user_id);
		if(expect==real)
			return 0;
		else if(expect > real) 
			return 1;
		else
			return 2;
	}

	@Override
	public int UnSelectAll(int user_id) {
		int expect=cart_dao.countCheckedByUserId(user_id);
		int real=cart_dao.setUnCheckedAll(user_id);
		if(expect==real)
			return 0;
		else if(expect > real) 
			return 1;
		else
			return 2;
	}

	@Override
	public int GetProductCount(int user_id) {
		return cart_dao.getProductCount(user_id);
	}


	


}
