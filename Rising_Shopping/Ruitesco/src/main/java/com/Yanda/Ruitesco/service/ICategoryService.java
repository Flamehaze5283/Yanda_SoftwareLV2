package com.Yanda.Ruitesco.service;


import com.Yanda.Ruitesco.utils.MessageResponse;

public interface ICategoryService {
	public MessageResponse<Object> GetCategoryByParentId(int parent_id, String username);//查找该商品种类的所有子类
	public MessageResponse<String> InsertNewCategory(int parent_id, String categoryName, String username);//添加新商品种类
	public MessageResponse<String> UpdateCategoryName(int category_id, String categoryName, String username);//修改商品种类名称
	public MessageResponse<Object> GetAllCategoryByParentId(int parent_id, String username);//获取所有子类
}
