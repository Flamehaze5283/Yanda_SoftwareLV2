package com.Yanda.Ruitesco.service;

import java.sql.SQLException;

import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.javabean.UserResponse;

public interface IUserService {
	public User Login(String username,String password);		//用户登录
	public int RegUser(User user); 		//用户注册
	public User QueryUserByName(String username);	//通过用户名查询用户信息
	public int UpdatePassword(String username,String oldpassword,String newpassword);	//修改用户密码
	public UserResponse<String> CheckUsername(String username, String type) throws SQLException;	//检查用户名可用性
	public UserResponse<Object> QueryUser(String username) throws SQLException;		//查询用户信息
	public UserResponse<String> ResetPassword(String passwordOld, String passwordNew, String userName) throws SQLException;//登录状态下修改密码
	public UserResponse<String> UpdateInformation(String userName, String email, String phone, String question, String answer) throws SQLException;//登录状态下更新个人信息
	public UserResponse<Object> GetInformation(String userName) throws SQLException;//获取当前用户的信息
}
