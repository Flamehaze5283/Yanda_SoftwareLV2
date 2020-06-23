package com.Yanda.Ruitesco.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Timestamp;
import java.util.List;

import com.Yanda.Ruitesco.dao.IUserDao;
import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.sql.ConnectSql;
import com.Yanda.Ruitesco.utils.JdbcUtil;

public class UserDaoImpl implements IUserDao {
	ConnectSql connSql = new ConnectSql();
	Connection conn;
	Statement state = null;
	ResultSet rs = null;
	String sql;
	
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
		try {
			conn = connSql.getConn();
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
		while(rs.next())
			flag = true;
		System.out.println(rs.getString("email"));
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
		while(rs.next())
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
		user.setId(rs.getInt(1));
		user.setUsername(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setPhone(rs.getString(4));
		user.setEmail(rs.getString(5));
		user.setRole(rs.getString(6));
		user.setQuestion(rs.getString(7));
		user.setAnswer(rs.getString(8));
		user.setCreate_time(rs.getTimestamp(9));
		user.setUpdate_time(rs.getTimestamp(10));
		return user;
	}
	/**
	 * 功能：登录时修改密码
	 * @throws SQLException 
	 * */
	@Override
	public boolean UpdatePassword(String passwordNew, String userName) throws SQLException {
		// TODO Auto-generated method stub
//		Timestamp current_Time = new Timestamp(System.currentTimeMillis());
		sql = "update user set password = '" + passwordNew + "' where username = '" + userName + "'";
		state = conn.createStatement();
		boolean flag = false;
		int number = state.executeUpdate(sql);
		if(number > 0)
			flag = true;
		return flag;
	}
	/**
	 * 功能：登录状态下修改个人信息
	 * @throws SQLException 
	 * */
	@Override
	public boolean UpdateInformation(String userName, String email, String phone, String question, String answer) throws SQLException {
		sql = "update user set email='" + email + "', phone='" + phone + "', question='" + question + "', answer='" + answer + "' where username = '" + userName + "'";
		state = conn.createStatement();
		boolean flag = false;
		int number = state.executeUpdate(sql);
		if(number > 0)
			flag = true;
		return flag;
	}
	/**
	 * 功能：后台添加用户
	 * */
	public boolean addUser(User user) {
		if(JdbcUtil.executeUpdate("insert into user(username,password,phone,email,role,question,answer,create_time,update_time) values(?,?,?,?,?,?,?,?,?)"
								,user.getUsername(),user.getPassword(),user.getPhone(),user.getEmail()
								,user.getRole(),user.getQuestion(),user.getAnswer()
								,user.getCreate_time(),user.getUpdate_time()) > 0)
			return true;
		return false;
	}
	/**
	 * 功能：通过用户名查找用户信息
	 * */
	public User getUserByName(String username) {
		List<User> userl= JdbcUtil.executeQuery("select * from user where username=?",User.class,username);
		if(userl!=null)
			return userl.get(0);
		return null;
	}
	/**
	 * 功能：通过用户名更改对应用户信息
	 * */
	public boolean updateUser(String username,User user)
	{
		if(JdbcUtil.executeUpdate("update user set username=?,password=?,phone=?,email=?,role=?,question=?,answer=?,create_time=?,update_time=? where username=?"
				,user.getUsername(),user.getPassword(),user.getPhone(),user.getEmail()
				,user.getRole(),user.getQuestion(),user.getAnswer()
				,user.getCreate_time(),user.getUpdate_time(),username)>0)
					return true;
		return false;
	}
	@Override
	public List<User> getUserList(int startRow,int endRow) {
		return JdbcUtil.executeQuery("select * from user limit ?,?", User.class,startRow,endRow-startRow+1);
	}

	@Override
	public int getNumOfAllUser() {
		return JdbcUtil.executeQueryInt("select count(*) from user");
	}
}
