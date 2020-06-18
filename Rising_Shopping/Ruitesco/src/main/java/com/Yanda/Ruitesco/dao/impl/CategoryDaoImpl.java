package com.Yanda.Ruitesco.dao.impl;

import com.Yanda.Ruitesco.dao.ICategoryDao;
import com.Yanda.Ruitesco.utils.JdbcUtil;
import com.Yanda.Ruitesco.utils.MessageResponse;

public class CategoryDaoImpl implements ICategoryDao {
	JdbcUtil jdbcUtil;
	public CategoryDaoImpl() {
		// TODO Auto-generated constructor stub
		jdbcUtil = new JdbcUtil();
	}
	@Override
	public MessageResponse<Object> GetCategory(int id) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
