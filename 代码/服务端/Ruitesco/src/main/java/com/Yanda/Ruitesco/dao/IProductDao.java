package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Product;

public interface IProductDao {
	public List<Product> GetProductByName(String... productName);//根据商品名获取信息
	public List<Product> GetProductById(int productId);//根据商品id查找
	public int UpdateProductStatus(int productId, int status);//产品上下架
	public int UpdateProduct(Product product_Update, int id);//更新商品信息
	public int InsertProduct(Product product_New);//添加商品
	public List<Product> GetProductByCategory(int category_id);//获取该类别的所有商品
}
