package com.Yanda.Ruitesco.dao.impl;

import java.util.List;

import com.Yanda.Ruitesco.dao.IProductDao;
import com.Yanda.Ruitesco.javabean.Product;
import com.Yanda.Ruitesco.utils.JdbcUtil;

public class ProductDaoImpl implements IProductDao {

	@Override
	public List<Product> GetProductByName(String... productName) {
		// TODO Auto-generated method stub
		String sql = "select * from sales";
		String productname;
		List<Product> result;
		if(productName.length > 0)
		{
			sql += " where name like ?";
			productname = productName[0];
			productname = "%" + productname + "%";
			result = JdbcUtil.executeQuery(sql, Product.class, productname);
		}
		else
			result = JdbcUtil.executeQuery(sql, Product.class);
		return result;
	}

	@Override
	public List<Product> GetProductById(int productId) {
		// TODO Auto-generated method stub
		String sql = "select * from sales where id = ?";
		List<Product> result = JdbcUtil.executeQuery(sql, Product.class, productId);
		return result;
	}

	@Override
	public int UpdateProductStatus(int productId, int status) {
		// TODO Auto-generated method stub
		String sql = "update sales set status = ? where id = ?";
		return JdbcUtil.executeUpdate(sql, status, productId);
	}
	
	@Override
	public int UpdateProduct(Product product_New) {
		// TODO Auto-generated method stub
		return 0;
	}
}
