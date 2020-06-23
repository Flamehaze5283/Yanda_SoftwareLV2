package com.Yanda.Ruitesco.utils.response.responsetype;

import java.util.ArrayList;
import java.util.List;

import com.Yanda.Ruitesco.dao.ICategoryDao;
import com.Yanda.Ruitesco.dao.impl.CategoryDaoImpl;
import com.Yanda.Ruitesco.javabean.Category;


public class CategoryMessage {
	List<CategoryType> data;
	int total;
	public CategoryMessage() {
		// TODO Auto-generated constructor stub
		data = new ArrayList<CategoryType>();
	}

	public List<CategoryType> getData() {
		return data;
	}

	public void setData(List<Category> data) {
		for(Category cg:data) {
			CategoryType temp = new CategoryType();
			temp.setId(cg.getId());
			temp.setParent_id(cg.getParent_id());
			temp.setCreate_time(cg.getCreate_time());
			temp.setUpdate_time(cg.getUpdate_time());
			temp.setName(cg.getName());
			ICategoryDao category_dao = new CategoryDaoImpl();
			List<Category> son = new ArrayList<Category>();
			son = category_dao.GetCategory(temp.getId());
			if(son != null)
				temp.setFather(true);
			else
				temp.setFather(false);
			this.data.add(temp);
		}
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}	
	
}
