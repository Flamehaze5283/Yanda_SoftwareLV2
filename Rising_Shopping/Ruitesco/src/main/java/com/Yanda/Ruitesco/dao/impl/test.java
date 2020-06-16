package com.Yanda.Ruitesco.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Yanda.Ruitesco.sql.ConnectSql;

public class test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update user set password = '123' where username = '123456'";
		ConnectSql ConnSql = new ConnectSql();
		Connection conn = ConnSql.getConn();
		Statement state = null;
		state = conn.createStatement();
		ResultSet rs = null;
		int number = state.executeUpdate(sql);
		System.out.println(number);
	}
}
