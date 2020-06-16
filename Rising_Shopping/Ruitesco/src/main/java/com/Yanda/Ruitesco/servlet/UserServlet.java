package com.Yanda.Ruitesco.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Yanda.Ruitesco.javabean.UserResponse;
import com.Yanda.Ruitesco.service.IUserService;
import com.Yanda.Ruitesco.service.impl.UserServiceImpl;
import com.google.gson.Gson;

@WebServlet("/user")
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;		//清除警告
	IUserService user_service = new UserServiceImpl();
	Gson gson = new Gson();
	String json;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		//doGet与doPost相同
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String type = req.getParameter("type");
		if(type == "login") {
			//登录
		}
		else if(type == "register") {
			//注册
		}
		else if(type == "check_valid") {
			//检查合法性
			CheckValid(req, resp);
		}
		else if(type == "get_user_info") {
			GetUserInfo(req, resp);
		}
		else if(type == "exit") {
			//退出
		}
		else {
			//其他
		}
	}
	public void CheckValid(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		/**
		 * 功能：检查用户名有效
		 * request: str 用户名, type 用户名种类(userName用户名，email邮箱)
		 * response: status 状态码(0成功, 1失败, 2其他情况), msg 反馈信息
		 * */
		
		//获取用户名极其类别
		String user_name = req.getParameter("str");
		String user_type = req.getParameter("type");
		//创建服务器回参response对象
		UserResponse<String> userResponse = new UserResponse<String>();
		try {
			//检查用户名是否有效
			userResponse = user_service.CheckUsername(user_name, user_type);
			json = gson.toJson(userResponse);
			System.out.println(json);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw;
		pw = resp.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public void GetUserInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		/**
		 * 功能：获取登录用户信息
		 * request: 无, session: str 用户名
		 * response: status 状态码(0成功, 1失败), msg 反馈信息, data 用户信息(id, username, email, phone, createTime, updateTime)
		 * */
		
		//从session中获取用户名
		HttpSession session = req.getSession();
		String userName = session.getAttribute("username").toString();
		
		//创建回参对象, 失败创建String类型(返回msg)， 成功创建data数组类型(返回data数组)
		UserResponse<Object> userResponse = new UserResponse<Object>();
		//查找数据库找到对应用户信息
		try {
			userResponse = user_service.QueryUser(userName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		json = gson.toJson(userResponse);
		System.out.println(json);
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			pw.write(json);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void ResetPassword(HttpServletRequest req, HttpServletResponse resp)
	{
		/**
		 * 功能：登录状态下重置密码
		 * request: passwordOld 旧密码, passwordNew 新密码
		 * response: status 状态码(0成功, 1失败), msg 反馈信息
		 * */
		
		//获取入参
		String passwordOld = req.getParameter("passwordOld");
		String passwordNew = req.getParameter("passwordNew");
		
		HttpSession session = req.getSession();
		String userName = session.getAttribute("userName").toString();
		//创建出参对象
		UserResponse<String> userResponse = new UserResponse<String>();
		
	}
}


