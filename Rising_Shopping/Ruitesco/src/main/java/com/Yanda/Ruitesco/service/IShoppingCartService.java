package com.Yanda.Ruitesco.service;

import java.util.List;

import com.Yanda.Ruitesco.javabean.ShoppingCart;

public interface IShoppingCartService {
	public List<ShoppingCart> GetCartList(int user_id);
	//返回值为状态码
	public int Add(int user_id,int product_id,int count);
	//返回值为状态码
	public int Update(int user_id,int product_id,int count);
	public boolean Remove(int user_id,int product_id);
	public boolean Select(int user_id,int product_id);
	public boolean UnSelect(int user_id,int product_id);
	public int SelectAll(int user_id);
	public int UnSelectAll(int user_id);
	public int GetProductCount(int user_id);
}
