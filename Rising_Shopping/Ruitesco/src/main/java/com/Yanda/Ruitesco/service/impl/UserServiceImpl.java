package com.Yanda.Ruitesco.service.impl;

import java.sql.SQLException;

import com.Yanda.Ruitesco.dao.IUserDao;
import com.Yanda.Ruitesco.dao.impl.UserDaoImpl;
import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.javabean.UserData;
import com.Yanda.Ruitesco.javabean.UserResponse;
import com.Yanda.Ruitesco.javabean.UserTotalData;
import com.Yanda.Ruitesco.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao user_dao;
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		user_dao = new UserDaoImpl();
	}
	/**
	 * 功能：用户登录
	 * */
	public User Login(String username,String password) {	
		User user = user_dao.getUserByName(username);
		if(user.getPassword() == password)
			user.setPassword("");
		else
			user=null;
		return user;
	}
	/**
	 * 功能：用户注册
	 * */
	public int RegUser(User user) {
		if(user_dao.getUserByName(user.getUsername())!=null)
			return 1;
		if(user_dao.addUser(user))
		{
			return 0;
		}
		return 2;
	}
	/**
	 * 功能：通过用户名查询用户信息
	 * */
	public User QueryUserByName(String userName){
		return user_dao.getUserByName(userName);
	}
	/**
	 * 功能：修改用户密码
	 * */
	public int UpdatePassword(String username,String oldpassword,String newpassword)
	{
		User user=user_dao.getUserByName(username);
		if(user.getPassword()==oldpassword)
		{
			user.setPassword(newpassword);
			if(user_dao.updateUser(username, user)==true)
				return 0;
			else
				return 1;
		}
		return 2;
	}
	/**
	 * 功能：检查用户名有效性
	 * */
	public UserResponse<String> CheckUsername(String userName, String type) throws SQLException {
		UserResponse<String> userResponse = new UserResponse<String>();
		if(type.equals("userName")) {
			if(!user_dao.SelectUser(userName)){
				userResponse.setStatus(0);
				userResponse.setMsg("校验成功");
			}
			else{
				userResponse.setStatus(1);
				userResponse.setMsg("用户名已存在");
			}
		}
		else if(type.equals("email")) {
			if(!user_dao.SelectUser(userName, type)) {
				userResponse.setStatus(0);
				userResponse.setMsg("校验成功");
			}
			else{
				userResponse.setStatus(1);
				userResponse.setMsg("用户名已存在");
			}
		}
		else {
			userResponse.setStatus(2);
			userResponse.setMsg("用户名为空");
		}
		return userResponse;
	}
	/**
	 * 功能：查找用户信息
	 * 
	 * */
	public UserResponse<Object> QueryUser(String userName) throws SQLException{
		UserResponse<Object> userResponse = new UserResponse<Object>();
		if(!userName.equals(""))
		{
			userResponse.setStatus(0);
			User user = new User();
			user = user_dao.FindUser(userName);
			UserData userData = new UserData();
			userData.setId(user.getId());
			userData.setUsername(user.getUsername());
			userData.setEmail(user.getEmail());
			userData.setPhone(user.getPhone());
			userData.setCreateTime(user.getCreate_time());
			userData.setUpdateTime(user.getUpdate_time());
			userResponse.setMsg(userData);
		}
		else
		{
			userResponse.setStatus(1);
			String msg = "用户未登录，无法获取当前用户信息";
			userResponse.setMsg(msg);
		}
		return userResponse;
	}
	
	/**
	 * 功能：登录状态下修改密码
	 * 
	 * */
	@Override
	public UserResponse<String> ResetPassword(String passwordOld, String passwordNew, String userName) throws SQLException {
		// TODO Auto-generated method stub
		UserResponse<String> userResponse = new UserResponse<String>();
		User user = new User();
		user = user_dao.FindUser(userName);
		if(!user.getPassword().equals(passwordOld))
		{
			userResponse.setStatus(1);
			userResponse.setMsg("旧密码有误");
		}
		else
		{
			if(user_dao.UpdatePassword(passwordNew, userName))
			{
				userResponse.setStatus(0);
				userResponse.setMsg("修改密码成功");
			}
			else
			{
				userResponse.setStatus(2);
				userResponse.setMsg("修改密码失败");
			}
		}
		return userResponse;
	}
	/**
	 * 功能：登录状态下更新个人信息
	 * @throws SQLException 
	 * 
	 * */
	public UserResponse<String> UpdateInformation(String userName, String email, String phone, String question,
			String answer) throws SQLException {
		// TODO Auto-generated method stub
		UserResponse<String> userResponse = new UserResponse<String>();
		if(userName.equals("")) {
			userResponse.setStatus(1);
			userResponse.setMsg("用户未登录");
		}
		else{
			if(user_dao.UpdateInformation(userName, email, phone, question, answer)) {
				userResponse.setStatus(0);
				userResponse.setMsg("更新个人信息成功");
			}
			else {
				userResponse.setStatus(2);
				userResponse.setMsg("数据库更新失败");
			}
			
		}
		return userResponse;
	}
	/**
	 * 功能：获取当前登录用户的信息
	 * @throws SQLException 
	 * 
	 * */
	@Override
	public UserResponse<Object> GetInformation(String userName) throws SQLException{
		UserResponse<Object> userResponse = new UserResponse<Object>();
		userResponse.setStatus(0);
		User user = new User();
		user = user_dao.FindUser(userName);
		UserTotalData userTotalData = new UserTotalData();
		userTotalData.setId(user.getId());
		userTotalData.setUsername(user.getUsername());
		userTotalData.setEmail(user.getEmail());
		userTotalData.setPhone(user.getPhone());
		userTotalData.setQuestion(user.getQuestion());
		userTotalData.setAnswer(user.getAnswer());
		userTotalData.setRole(user.getRole());
		userTotalData.setCreateTime(user.getCreate_time());
		userTotalData.setUpdateTime(user.getUpdate_time());
		userResponse.setMsg(userTotalData);
		return userResponse;
	}
}
