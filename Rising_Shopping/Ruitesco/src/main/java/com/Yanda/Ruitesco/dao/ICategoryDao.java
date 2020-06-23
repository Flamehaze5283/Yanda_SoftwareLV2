package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Category;

public interface ICategoryDao {
	public List<Category> GetCategory(int id);	//����parent_id��ȡ�������Ϣ
	public int InsertCategory(int parent_id, String categoryName);//����µ���Ʒ����
	public int UpdateCategory(int category_id, String paramValue, String paramName);//������Ʒ������Ϣ
	public Category QueryCategory(int category_id);//��ѯ��id����Ʒ������Ϣ
	public int GetParentId(int id);//��ȡ����ĸ���id
	public int DeleteCategory(int id);//ɾ��һ����
}
