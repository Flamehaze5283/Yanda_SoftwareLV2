package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Product;

public interface IProductDao {
	public List<Product> GetProductByName(String... productName);//根据商品名获取信息
	public int UpdateProduct(Product product_New);//更新商品信息
}
