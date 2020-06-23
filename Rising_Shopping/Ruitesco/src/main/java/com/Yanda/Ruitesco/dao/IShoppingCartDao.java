package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.ShoppingCart;

public interface IShoppingCartDao {
	public List<ShoppingCart> getCartList(int user_id);
	public boolean addProduct(ShoppingCart param);
	public boolean updateCartProduct(int user_id,int product_id,int count);
	public boolean removeCartProduct(int user_id,int product_id);
	public ShoppingCart queryRecord(int user_id,int product_id);
	public int queryProductStock(int product_id);
	public boolean setChecked(int user_id,int product_id);
	public boolean resetChecked(int user_id,int product_id);
	public int setCheckedAll(int user_id);
	public int setUnCheckedAll(int user_id);
	public int countUnCheckedByUserId(int user_id);
	public int countCheckedByUserId(int user_id);
	public int getProductCount(int user_id);
}
