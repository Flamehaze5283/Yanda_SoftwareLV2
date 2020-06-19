package com.Yanda.Ruitesco.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.Yanda.Ruitesco.dao.ICategoryDao;
import com.Yanda.Ruitesco.javabean.Category;
import com.Yanda.Ruitesco.utils.JdbcUtil;

public class CategoryDaoImpl implements ICategoryDao {
	//��ȡ��Ʒ����
	@Override
	public List<Category> GetCategory(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from type where parent_id=?";
		List<Category> result = JdbcUtil.executeQuery(sql, Category.class, id);
		return result;
	}
	//��ѯ��id����Ʒ������Ϣ
	@Override
	public Category QueryCategory(int category_id) {
		// TODO Auto-generated method stub
		String sql = "select * from type where id = ?";
		List<Category> result = JdbcUtil.executeQuery(sql, Category.class, category_id);
		return result.get(0);
	}
	//����µ���Ʒ����
	@Override
	public int InsertCategory(int parent_id, String categoryName) {
		// TODO Auto-generated method stub
		String sql = "insert into type (parent_id, name, create_time, status) values(?, ?, ? ,1)";
		int result = 0;
		Date date = new Date();
	    //��ǰ�˻����Լ�ģ��һ�����ڸ�ʽ��תΪString����
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = format.format(date);
		result = JdbcUtil.executeUpdate(sql, parent_id, categoryName, dateStr);
		return result;
	}
	//������Ʒ������Ϣ
	@Override
	public int UpdateCategory(int category_id, String paramValue, String paramName) {
		// TODO Auto-generated method stub
		String sql = "update type set " + paramName + "=? where id=?";
		int result = 0;
		result = JdbcUtil.executeUpdate(sql, paramValue, category_id);
		return result;
	}
}
