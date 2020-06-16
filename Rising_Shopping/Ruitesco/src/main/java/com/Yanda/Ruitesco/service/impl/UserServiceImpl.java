package com.Yanda.Ruitesco.service.impl;

import java.sql.SQLException;

import com.Yanda.Ruitesco.dao.IUserSelect;
import com.Yanda.Ruitesco.dao.impl.UserSelectImpl;
import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.javabean.UserData;
import com.Yanda.Ruitesco.javabean.UserResponse;
import com.Yanda.Ruitesco.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserSelect user_select;
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		user_select = new UserSelectImpl();
	}
	
	/**
	 * ���ܣ�����û�����Ч��
	 * 
	 * */
	public UserResponse<String> CheckUsername(String userName, String type) throws SQLException {
		UserResponse<String> userResponse = new UserResponse<String>();
		if(type == "userName") {
			if(!user_select.SelectUser(userName)){
				userResponse.setStatus(0);
				userResponse.setMsg("У��ɹ�");
			}
			else{
				userResponse.setStatus(1);
				userResponse.setMsg("�û����Ѵ���");
			}
		}
		else if(type == "email") {
			if(!user_select.SelectUser(userName, type)) {
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
		if(userName != "" || userName != null)
		{
			userResponse.setStatus(0);
			User user = new User();
			user = user_select.FindUser(userName);
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
	@Override
	public UserResponse<String> ResetPassword(String passwordNew, String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
