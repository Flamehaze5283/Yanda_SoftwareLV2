package com.Yanda.Ruitesco.service;


import java.sql.SQLException;

import com.Yanda.Ruitesco.utils.response.MessageResponse;
import com.Yanda.Ruitesco.utils.response.MessageType;

public interface ICategoryService {
	public MessageResponse<Object> GetCategoryByParentId(int parent_id, String username);//查找该商品种类的所有子类
	public MessageResponse<String> InsertNewCategory(int parent_id, String categoryName, String username);//添加新商品种类
	public MessageResponse<String> UpdateCategoryName(int category_id, String categoryName, String username);//修改商品种类名称
	public MessageResponse<Object> GetAllCategoryByParentId(int parent_id, String username) throws SQLException;//获取所有子类
	public int GetParentId(int parent_id);//获取父类的父类id
	public MessageResponse<String> DeleteCategoryById(int category_id, String username);//根据id删除一个类(以及子类)
	public MessageResponse<Object> QueryAllLeaf();//寻找所有叶子节点类
}
