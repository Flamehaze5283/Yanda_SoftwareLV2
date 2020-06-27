package com.Yanda.Ruitesco.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

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
		if(result == null)
			return null;
		else
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
	@Override
	public int GetParentId(int id) {
		// TODO Auto-generated method stub
		String sql = "select parent_id from type where id = ?";
		int result;
		if(JdbcUtil.executeQuery(sql, Category.class, id) == null)
			result = 0;
		else
			result = JdbcUtil.executeQuery(sql, Category.class, id).get(0).getParent_id();
		return result;
	}
	@Override
	public int DeleteCategory(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from type where id = ?";
		int result = JdbcUtil.executeUpdate(sql, id);
		return result;
	}
	
	@Override
	public List<String> getAllCategoryById(int category_id) {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<String>();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(category_id);
		while(!stack.empty()) {
			int id = stack.peek();
			stack.pop();
			Category category = QueryCategory(id);
			if(category != null) {
				result.add(category.getEn_name());
				stack.push(category.getParent_id());
			}
		}
		return result;
	}
}
