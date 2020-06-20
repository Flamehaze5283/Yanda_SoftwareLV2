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
		if(productName != null)
			sql += "where name = ?";
		String productname = productName[0];
		List<Product> result = JdbcUtil.executeQuery(sql, Product.class, productname);
		return result;
	}

	@Override
	public int UpdateProduct(Product product_New) {
		// TODO Auto-generated method stub
		return 0;
	}

}
