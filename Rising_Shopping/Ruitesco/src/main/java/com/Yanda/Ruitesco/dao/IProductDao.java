package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Product;

public interface IProductDao {
	public List<Product> GetProductByName(String... productName);//������Ʒ����ȡ��Ϣ
	public int UpdateProduct(Product product_New);//������Ʒ��Ϣ
}
