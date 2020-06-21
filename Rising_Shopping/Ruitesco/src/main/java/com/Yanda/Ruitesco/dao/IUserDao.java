package com.Yanda.Ruitesco.dao;

import java.sql.SQLException;
import java.util.List;

import com.Yanda.Ruitesco.javabean.User;

public interface IUserDao {
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
	/**
	 * ���ܣ���¼״̬���޸�����
	 * */
	public boolean UpdatePassword(String passwordNew, String userName) throws SQLException;	//�޸�����
	/**
	 * ���ܣ���¼״̬���޸ĸ�����Ϣ
	 * @throws SQLException 
	 * */
	public boolean UpdateInformation(String userName, String email, String phone, String question, String answer) throws SQLException;//�޸ĸ�����Ϣ
	/**
	 * ���ܣ���̨����û�
	 * */
	public boolean addUser(User user); 
	/**
	 * ���ܣ�ͨ���û��������û���Ϣ
	 * */
	public User getUserByName(String userName);
	/**
	 * ���ܣ�ͨ���û������Ķ�Ӧ�û���Ϣ
	 * */
	public boolean updateUser(String username,User usr);
	/**
	 * 
	 * @return ����user���б������������Ա
	 */
	public List<User> getUserList(int startRow,int endRow);
	/**
	 * 
	 * @return user������Ŀ����������Ա�������û�
	 */
	public int getNumOfAllUser();
}
