package com.Yanda.Ruitesco.service;

import java.sql.SQLException;

import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.javabean.UserResponse;

public interface IUserService {
	public User Login(String username,String password);		//�û���¼
	public int RegUser(User user); 		//�û�ע��
	public User QueryUserByName(String username);	//ͨ���û�����ѯ�û���Ϣ
	public int UpdatePassword(String username,String oldpassword,String newpassword);	//�޸��û�����
	public UserResponse<String> CheckUsername(String username, String type) throws SQLException;	//����û���������
	public UserResponse<Object> QueryUser(String username) throws SQLException;		//��ѯ�û���Ϣ
	public UserResponse<String> ResetPassword(String passwordOld, String passwordNew, String userName) throws SQLException;//��¼״̬���޸�����
	public UserResponse<String> UpdateInformation(String userName, String email, String phone, String question, String answer) throws SQLException;//��¼״̬�¸��¸�����Ϣ
	public UserResponse<Object> GetInformation(String userName) throws SQLException;//��ȡ��ǰ�û�����Ϣ
}
