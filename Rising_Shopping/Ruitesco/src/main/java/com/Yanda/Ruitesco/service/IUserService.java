package com.Yanda.Ruitesco.service;

import java.sql.SQLException;

import com.Yanda.Ruitesco.javabean.UserResponse;

public interface IUserService {
	public UserResponse<String> CheckUsername(String username, String type) throws SQLException;	//����û���������
	public UserResponse<Object> QueryUser(String username) throws SQLException;		//��ѯ�û���Ϣ
	public UserResponse<String> ResetPassword(String passwordNew, String userName);	//��¼״̬���޸�����
}
