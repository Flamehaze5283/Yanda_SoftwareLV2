package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Category;

public interface ICategoryDao {
	public List<Category> GetCategory(int id);	//根据id获取类别信息
}
