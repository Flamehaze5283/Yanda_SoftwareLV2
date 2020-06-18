package com.Yanda.Ruitesco.service.impl;

import java.sql.SQLException;

import com.Yanda.Ruitesco.dao.IUserDao;
import com.Yanda.Ruitesco.dao.impl.UserDaoImpl;
import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.javabean.UserData;
import com.Yanda.Ruitesco.javabean.UserResponse;
import com.Yanda.Ruitesco.javabean.UserTotalData;
import com.Yanda.Ruitesco.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao user_dao;
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		user_dao = new UserDaoImpl();
	}
	/**
	 * ���ܣ��û���¼
	 * */
	public User Login(String username,String password) {	
		User user = user_dao.getUserByName(username);
		if(user.getPassword() == password)
			user.setPassword("");
		else
			user=null;
		return user;
	}
	/**
	 * ���ܣ��û�ע��
	 * */
	public int RegUser(User user) {
		if(user_dao.getUserByName(user.getUsername())!=null)
			return 1;
		if(user_dao.addUser(user))
		{
			return 0;
		}
		return 2;
	}
	/**
	 * ���ܣ�ͨ���û�����ѯ�û���Ϣ
	 * */
	public User QueryUserByName(String userName){
		return user_dao.getUserByName(userName);
	}
	/**
	 * ���ܣ��޸��û�����
	 * */
	public int UpdatePassword(String username,String oldpassword,String newpassword)
	{
		User user=user_dao.getUserByName(username);
		if(user.getPassword()==oldpassword)
		{
			user.setPassword(newpassword);
			if(user_dao.updateUser(username, user)==true)
				return 0;
			else
				return 1;
		}
		return 2;
	}
	/**
	 * ���ܣ�����û�����Ч��
	 * */
	public UserResponse<String> CheckUsername(String userName, String type) throws SQLException {
		UserResponse<String> userResponse = new UserResponse<String>();
		if(type.equals("userName")) {
			if(!user_dao.SelectUser(userName)){
				userResponse.setStatus(0);
				userResponse.setMsg("У��ɹ�");
			}
			else{
				userResponse.setStatus(1);
				userResponse.setMsg("�û����Ѵ���");
			}
		}
		else if(type.equals("email")) {
			if(!user_dao.SelectUser(userName, type)) {
				userResponse.setStatus(0);
				userResponse.setMsg("У��ɹ�");
			}
			else{
				userResponse.setStatus(1);
				userResponse.setMsg("�û����Ѵ���");
			}
		}
		else {
			userResponse.setStatus(2);
			userResponse.setMsg("�û���Ϊ��");
		}
		return userResponse;
	}
	/**
	 * ���ܣ������û���Ϣ
	 * 
	 * */
	public UserResponse<Object> QueryUser(String userName) throws SQLException{
		UserResponse<Object> userResponse = new UserResponse<Object>();
		if(!userName.equals(""))
		{
			userResponse.setStatus(0);
			User user = new User();
			user = user_dao.FindUser(userName);
			UserData userData = new UserData();
			userData.setId(user.getId());
			userData.setUsername(user.getUsername());
			userData.setEmail(user.getEmail());
			userData.setPhone(user.getPhone());
			userData.setCreateTime(user.getCreate_time());
			userData.setUpdateTime(user.getUpdate_time());
			userResponse.setMsg(userData);
		}
		else
		{
			userResponse.setStatus(1);
			String msg = "�û�δ��¼���޷���ȡ��ǰ�û���Ϣ";
			userResponse.setMsg(msg);
		}
		return userResponse;
	}
	
	/**
	 * ���ܣ���¼״̬���޸�����
	 * 
	 * */
	@Override
	public UserResponse<String> ResetPassword(String passwordOld, String passwordNew, String userName) throws SQLException {
		// TODO Auto-generated method stub
		UserResponse<String> userResponse = new UserResponse<String>();
		User user = new User();
		user = user_dao.FindUser(userName);
		if(!user.getPassword().equals(passwordOld))
		{
			userResponse.setStatus(1);
			userResponse.setMsg("����������");
		}
		else
		{
			if(user_dao.UpdatePassword(passwordNew, userName))
			{
				userResponse.setStatus(0);
				userResponse.setMsg("�޸�����ɹ�");
			}
			else
			{
				userResponse.setStatus(2);
				userResponse.setMsg("�޸�����ʧ��");
			}
		}
		return userResponse;
	}
	/**
	 * ���ܣ���¼״̬�¸��¸�����Ϣ
	 * @throws SQLException 
	 * 
	 * */
	public UserResponse<String> UpdateInformation(String userName, String email, String phone, String question,
			String answer) throws SQLException {
		// TODO Auto-generated method stub
		UserResponse<String> userResponse = new UserResponse<String>();
		if(userName.equals("")) {
			userResponse.setStatus(1);
			userResponse.setMsg("�û�δ��¼");
		}
		else{
			if(user_dao.UpdateInformation(userName, email, phone, question, answer)) {
				userResponse.setStatus(0);
				userResponse.setMsg("���¸�����Ϣ�ɹ�");
			}
			else {
				userResponse.setStatus(2);
				userResponse.setMsg("���ݿ����ʧ��");
			}
			
		}
		return userResponse;
	}
	/**
	 * ���ܣ���ȡ��ǰ��¼�û�����Ϣ
	 * @throws SQLException 
	 * 
	 * */
	@Override
	public UserResponse<Object> GetInformation(String userName) throws SQLException{
		UserResponse<Object> userResponse = new UserResponse<Object>();
		userResponse.setStatus(0);
		User user = new User();
		user = user_dao.FindUser(userName);
		UserTotalData userTotalData = new UserTotalData();
		userTotalData.setId(user.getId());
		userTotalData.setUsername(user.getUsername());
		userTotalData.setEmail(user.getEmail());
		userTotalData.setPhone(user.getPhone());
		userTotalData.setQuestion(user.getQuestion());
		userTotalData.setAnswer(user.getAnswer());
		userTotalData.setRole(user.getRole());
		userTotalData.setCreateTime(user.getCreate_time());
		userTotalData.setUpdateTime(user.getUpdate_time());
		userResponse.setMsg(userTotalData);
		return userResponse;
	}
}
