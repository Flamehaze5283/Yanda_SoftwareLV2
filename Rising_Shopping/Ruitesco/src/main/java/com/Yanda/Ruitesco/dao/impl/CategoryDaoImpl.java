package com.Yanda.Ruitesco.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.Yanda.Ruitesco.dao.ICategoryDao;
import com.Yanda.Ruitesco.javabean.Category;
import com.Yanda.Ruitesco.utils.JdbcUtil;

public class CategoryDaoImpl implements ICategoryDao {
	//获取商品种类
	@Override
	public List<Category> GetCategory(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from type where parent_id=?";
		List<Category> result = JdbcUtil.executeQuery(sql, Category.class, id);
		return result;
	}
	//查询该id的商品种类信息
	@Override
	public Category QueryCategory(int category_id) {
		// TODO Auto-generated method stub
		String sql = "select * from type where id = ?";
		List<Category> result = JdbcUtil.executeQuery(sql, Category.class, category_id);
		return result.get(0);
	}
	//添加新的商品种类
	@Override
	public int InsertCategory(int parent_id, String categoryName) {
		// TODO Auto-generated method stub
		String sql = "insert into type (parent_id, name, create_time, status) values(?, ?, ? ,1)";
		int result = 0;
		Date date = new Date();
	    //从前端或者自己模拟一个日期格式，转为String即可
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = format.format(date);
		result = JdbcUtil.executeUpdate(sql, parent_id, categoryName, dateStr);
		return result;
	}
	//更改商品种类信息
	@Override
	public int UpdateCategory(int category_id, String paramValue, String paramName) {
		// TODO Auto-generated method stub
		String sql = "update type set " + paramName + "=? where id=?";
		int result = 0;
		result = JdbcUtil.executeUpdate(sql, paramValue, category_id);
		return result;
	}
}
