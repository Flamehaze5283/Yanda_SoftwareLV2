package com.Yanda.Ruitesco.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	public int UpdateProduct(Product product_Update, int id) {
		// TODO Auto-generated method stub
		String sql = "update sales set category_id = ?, name = ?, subtitle = ?, main_image = ?, sub_image = ?, detail = ?, price = ?, stock = ?, status = ? where id = ?";
		return JdbcUtil.executeUpdate(sql, product_Update.getCategory_id(), product_Update.getName(), product_Update.getSubtitle(), product_Update.getMainImage(), product_Update.getSubImage(), product_Update.getDetail(), product_Update.getPrice(), product_Update.getStock(), product_Update.getStatus(), id);
}

	@Override
	public int InsertProduct(Product product_New) {
		// TODO Auto-generated method stub
		String sql = "insert into sales (category_id, name, subtitle, main_image, sub_image, detail, price, stock, status, create_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Date date = new Date();
	    //从前端或者自己模拟一个日期格式，转为String即可
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = format.format(date);
		return JdbcUtil.executeUpdate(sql, product_New.getCategory_id(), product_New.getName(), product_New.getSubtitle(), product_New.getMainImage(), product_New.getSubImage(), product_New.getDetail(), product_New.getPrice(), product_New.getStock(), product_New.getStatus(), dateStr);
	}
}
