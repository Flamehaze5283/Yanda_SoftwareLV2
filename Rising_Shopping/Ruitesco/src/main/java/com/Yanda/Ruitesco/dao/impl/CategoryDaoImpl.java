package com.Yanda.Ruitesco.dao.impl;

import java.util.List;

import com.Yanda.Ruitesco.dao.ICategoryDao;
import com.Yanda.Ruitesco.javabean.Category;
import com.Yanda.Ruitesco.utils.JdbcUtil;

public class CategoryDaoImpl implements ICategoryDao {

	@Override
	public List<Category> GetCategory(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from type where parent_id=?";
		List<Category> result = JdbcUtil.executeQuery(sql, Category.class, id);
		return result;
	}

}
