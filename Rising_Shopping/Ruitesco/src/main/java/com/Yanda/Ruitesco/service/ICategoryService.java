package com.Yanda.Ruitesco.service;


import com.Yanda.Ruitesco.utils.MessageResponse;

public interface ICategoryService {
	public MessageResponse<Object> GetCategoryByParentId(int parent_id, String username);//���Ҹ���Ʒ�������������
	public MessageResponse<String> InsertNewCategory(int parent_id, String categoryName, String username);//�������Ʒ����
	public MessageResponse<String> UpdateCategoryName(int category_id, String categoryName, String username);//�޸���Ʒ��������
	public MessageResponse<Object> GetAllCategoryByParentId(int parent_id, String username);//��ȡ��������
}
