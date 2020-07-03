package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Product;

public interface IProductDao {
	public List<Product> GetProductByName(String... productName);//������Ʒ����ȡ��Ϣ
	public List<Product> GetProductById(int productId);//������Ʒid����
	public int UpdateProductStatus(int productId, int status);//��Ʒ���¼�
	public int UpdateProduct(Product product_Update, int id);//������Ʒ��Ϣ
	public int InsertProduct(Product product_New);//�����Ʒ
	public List<Product> GetProductByCategory(int category_id);//��ȡ������������Ʒ
}
