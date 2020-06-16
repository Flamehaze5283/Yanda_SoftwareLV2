package com.Yanda.Ruitesco.dao;

import java.sql.SQLException;

import com.Yanda.Ruitesco.javabean.User;

public interface IUserSelect {
	/**
	 * ���ܣ����ò��������ݿ����Ƿ���ڲ�����boolean
	 * */
	public boolean SelectUser(String userName) throws SQLException;
	public boolean SelectUser(String email, String type) throws SQLException;
	/**
	 * ���ܣ����ݲ������Ҹ��û���������Ϣ
	 * @throws SQLException 
	 * */
	public User FindUser(String userName) throws SQLException;
}
