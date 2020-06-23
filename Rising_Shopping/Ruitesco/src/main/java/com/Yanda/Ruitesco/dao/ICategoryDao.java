package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Category;

public interface ICategoryDao {
	public List<Category> GetCategory(int id);	//根据parent_id获取子类别信息
	public int InsertCategory(int parent_id, String categoryName);//添加新的商品种类
	public int UpdateCategory(int category_id, String paramValue, String paramName);//更改商品种类信息
	public Category QueryCategory(int category_id);//查询该id的商品种类信息
	public int GetParentId(int id);//获取该类的父类id
	public int DeleteCategory(int id);//删除一个类
}
