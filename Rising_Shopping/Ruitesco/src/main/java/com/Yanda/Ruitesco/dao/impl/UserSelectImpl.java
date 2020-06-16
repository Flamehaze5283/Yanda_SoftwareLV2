package com.Yanda.Ruitesco.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Yanda.Ruitesco.dao.IUserSelect;
import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.sql.ConnectSql;

public class UserSelectImpl implements IUserSelect {
	ConnectSql ConnSql = new ConnectSql();
	Connection conn;
	Statement state = null;
	ResultSet rs = null;
	String sql;
	public UserSelectImpl() {
		// TODO Auto-generated constructor stub
		try {
			//连接数据库
			conn = ConnSql.getConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：检查该参数在数据库中是否存在并返回boolean
	 * */
	@Override
	public boolean SelectUser(String userName) throws SQLException {
		// TODO Auto-generated method stub
		// 根据用户名检查有效性
		sql = "select username, email from user where username = '" + userName + "'";
		state = conn.createStatement();
		rs = state.executeQuery(sql);
		boolean flag = false;
		if(rs.next())
			flag = true;
		ConnectSql.close(state, rs);
		return flag;
	}
	@Override
	public boolean SelectUser(String email, String type) throws SQLException {
		// TODO Auto-generated method stub
		// 根据邮箱检查有效性
		sql = "select username, email from user where email = '" + email + "'";
		state = conn.createStatement();
		rs = state.executeQuery(sql);
		boolean flag = false;
		if(rs.next())
			flag = true;
		ConnectSql.close(state, rs);
		return flag;
	}
	/**
	 * 功能：查找用户信息并封装到UserData类对象中
	 * */
	@Override
	public User FindUser(String userName) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		sql = "select * from user where username = '" + userName + "'";
		state = conn.createStatement();
		rs = state.executeQuery(sql);
		rs.next();
		user.setUsername(rs.getString(0));
		user.setId(rs.getInt(1));
		user.setPassword(rs.getString(2));
		user.setPhone(rs.getString(3));
		user.setEmail(rs.getString(4));
		user.setRole(rs.getString(5));
		user.setQuestion(rs.getString(6));
		user.setAnswer(rs.getString(7));
		user.setCreate_time(rs.getTimestamp(8));
		user.setUpdate_time(rs.getTimestamp(9));
		return user;
	}
}
