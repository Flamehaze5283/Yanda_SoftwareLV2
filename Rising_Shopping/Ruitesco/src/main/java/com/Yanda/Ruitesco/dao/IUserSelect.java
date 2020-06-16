package com.Yanda.Ruitesco.dao;

import java.sql.SQLException;

import com.Yanda.Ruitesco.javabean.User;

public interface IUserSelect {
	/**
	 * 功能：检查该参数在数据库中是否存在并返回boolean
	 * */
	public boolean SelectUser(String userName) throws SQLException;
	public boolean SelectUser(String email, String type) throws SQLException;
	/**
	 * 功能：根据参数查找该用户的所有信息
	 * @throws SQLException 
	 * */
	public User FindUser(String userName) throws SQLException;
}
