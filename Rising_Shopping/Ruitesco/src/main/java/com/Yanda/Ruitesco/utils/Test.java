package com.Yanda.Ruitesco.utils;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		System.out.println("*************");
		JdbcUtil.executeUpdate("insert into user(name,age) values(?,?)","¶¹°ü",10);
		List<User> userList = JdbcUtil.executeQuery("select id,name from user",User.class);
		for(User u : userList)
		{
			System.out.println(u.toString());
		}
		System.out.println("id:3"+JdbcUtil.getById("user", User.class, 3));
		System.out.println("+++++++++++");
	}
	
}
