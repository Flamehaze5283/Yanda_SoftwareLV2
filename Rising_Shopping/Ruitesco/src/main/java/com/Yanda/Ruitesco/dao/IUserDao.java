package com.Yanda.Ruitesco.dao;

import java.sql.SQLException;
import java.util.List;

import com.Yanda.Ruitesco.javabean.User;

public interface IUserDao {
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
	/**
	 * 功能：登录状态下修改密码
	 * */
	public boolean UpdatePassword(String passwordNew, String userName) throws SQLException;	//修改密码
	/**
	 * 功能：登录状态下修改个人信息
	 * @throws SQLException 
	 * */
	public boolean UpdateInformation(String userName, String email, String phone, String question, String answer) throws SQLException;//修改个人信息
	/**
	 * 功能：后台添加用户
	 * */
	public boolean addUser(User user); 
	/**
	 * 功能：通过用户名查找用户信息
	 * */
	public User getUserByName(String userName);
	/**
	 * 功能：通过用户名更改对应用户信息
	 * */
	public boolean updateUser(String username,User usr);
	/**
	 * 
	 * @return 所有user的列表包括其他管理员
	 */
	public List<User> getUserList(int startRow,int endRow);
	/**
	 * 
	 * @return user的总数目，包含管理员和所有用户
	 */
	public int getNumOfAllUser();
}
