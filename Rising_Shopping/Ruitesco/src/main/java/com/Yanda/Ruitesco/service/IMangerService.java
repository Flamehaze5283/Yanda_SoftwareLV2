package com.Yanda.Ruitesco.service;


import java.util.List;

import com.Yanda.Ruitesco.javabean.PageUserList;
import com.Yanda.Ruitesco.javabean.User;

public interface IMangerService {
	public User Login(String username,String password);		//����Ա��¼
	public PageUserList GetNextPage(PageUserList pageUserList);
	public List<User> GetUserList(int startRow,int endRow);		//��ȡ�û��б��¼
}
