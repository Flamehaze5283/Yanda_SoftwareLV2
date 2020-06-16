package com.Yanda.Ruitesco.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Yanda.Ruitesco.dao.IUserUpdate;
import com.Yanda.Ruitesco.sql.ConnectSql;

public class UserUpdateImpl implements IUserUpdate {
	ConnectSql connSql = new ConnectSql();
	Connection conn;
	Statement state = null;
	ResultSet rs = null;
	String sql;
	
	public UserUpdateImpl() {
		// TODO Auto-generated constructor stub
		try {
			conn = connSql.getConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：登录时修改密码
	 * @throws SQLException 
	 * */
	@Override
	public boolean UpdatePassword(String passwordNew, String userName) throws SQLException {
		// TODO Auto-generated method stub
		sql = "update user set password = '" + passwordNew + "' where username = '" + userName + "'";
		state = conn.createStatement();
		boolean flag = false;
		int number = state.executeUpdate(sql);
		if(number > 0)
			flag = true;
		return flag;
	}

}
