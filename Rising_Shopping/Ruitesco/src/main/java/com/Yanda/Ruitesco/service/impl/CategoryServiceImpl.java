package com.Yanda.Ruitesco.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Stack;

import com.Yanda.Ruitesco.dao.ICategoryDao;
import com.Yanda.Ruitesco.dao.IUserDao;
import com.Yanda.Ruitesco.dao.impl.CategoryDaoImpl;
import com.Yanda.Ruitesco.dao.impl.UserDaoImpl;
import com.Yanda.Ruitesco.javabean.Category;
import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.service.ICategoryService;
import com.Yanda.Ruitesco.utils.response.MessageResponse;

public class CategoryServiceImpl implements ICategoryService {
	ICategoryDao category_dao;
	
	public CategoryServiceImpl() {
		// TODO Auto-generated constructor stub
		category_dao = new CategoryDaoImpl();
	}
	
	//根据parent_id查找该类的所有子类
	@Override
	public MessageResponse<Object> GetCategoryByParentId(int parent_id, String username) {
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		if(username.equals(""))
		{
			messageResponse.setStatus(10);
			messageResponse.setMsg("用户未登录");
		}
		else {
			List<Category> data = category_dao.GetCategory(parent_id);
			if(data == null) {
				messageResponse.setStatus(1);
				messageResponse.setMsg("未找到该商品类别");
			}
			else {
				messageResponse.setStatus(0);
				messageResponse.setMsg(data);
			}
		}
		return messageResponse;
	}
	//添加新商品种类，自动生成id并填入其父类id
	@Override
	public MessageResponse<String> InsertNewCategory(int parent_id, String categoryName, String username) {
		// TODO Auto-generated method stub
		MessageResponse<String> messageResponse = new MessageResponse<String>();
		if(username.equals(""))
		{
			messageResponse.setStatus(10);
			messageResponse.setMsg("用户未登录");
		}
		else {
			int update_number = category_dao.InsertCategory(parent_id, categoryName);
			if(update_number > 0) {
				messageResponse.setStatus(0);
				messageResponse.setMsg("添加商品种类成功");
			}
			else {
				messageResponse.setStatus(1);
				messageResponse.setMsg("添加商品种类失败");
			}
		}
		return messageResponse;
	}
	//修改商品种类名称,通过category_id获取
	@Override
	public MessageResponse<String> UpdateCategoryName(int category_id, String categoryName, String username) {
		// TODO Auto-generated method stub
		MessageResponse<String> messageResponse = new MessageResponse<String>();
		if(username.equals(""))
		{
			messageResponse.setStatus(10);
			messageResponse.setMsg("用户未登录");
		}
		else {
			int update_number = category_dao.UpdateCategory(category_id, categoryName, "name");
			if(update_number > 0) {
				messageResponse.setStatus(0);
				messageResponse.setMsg("更新商品种类名称成功");
			}
			else {
				messageResponse.setStatus(1);
				messageResponse.setMsg("更新商品种类名称失败");
			}
		}
		return messageResponse;
	}
	@Override
	public MessageResponse<Object> GetAllCategoryByParentId(int parent_id, String username) throws SQLException {
		// TODO Auto-generated method stub
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		if(username.equals("")){
			messageResponse.setStatus(10);
			messageResponse.setMsg("用户未登录");
		}
		else
		{
			IUserDao user_dao = new UserDaoImpl();
			User user = user_dao.FindUser(username);
			if(user.getRole().equals("用户")) {
				messageResponse.setStatus(1);
				messageResponse.setMsg("无权限获取种类信息");
			}			
			else{
				Stack<Integer> id = new Stack<Integer>();
				List<Category> temp;
				List<Integer> result = new Stack<Integer>();
				result.add(parent_id);
				id.push(parent_id);
				while(!id.empty()) {
					int current_id = id.peek();
					id.pop();
					temp = new Stack<Category>();
					temp = category_dao.GetCategory(current_id);
					if(temp != null) {
						for(Category cg:temp) {
							result.add(cg.getId());
							id.push(cg.getId());
						}
					}
				}
				if(result.isEmpty() || result == null) {
					messageResponse.setStatus(2);
					messageResponse.setMsg("该类不存在，请确认categoryId是否正确");
				}
				else {
					messageResponse.setStatus(1);
					messageResponse.setMsg(result);
				}
			}
		}
		return messageResponse;
	}
}
