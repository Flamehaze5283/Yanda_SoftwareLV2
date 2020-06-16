package com.Yanda.Ruitesco.service;

import java.sql.SQLException;

import com.Yanda.Ruitesco.javabean.UserResponse;

public interface IUserService {
	public UserResponse<String> CheckUsername(String username, String type) throws SQLException;	//检查用户名可用性
	public UserResponse<Object> QueryUser(String username) throws SQLException;		//查询用户信息
	public UserResponse<String> ResetPassword(String passwordNew, String userName);	//登录状态下修改密码
}
