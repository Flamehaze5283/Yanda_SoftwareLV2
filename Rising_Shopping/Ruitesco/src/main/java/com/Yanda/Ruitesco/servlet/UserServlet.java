package com.Yanda.Ruitesco.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Yanda.Ruitesco.dataResp.DataResp;
import com.Yanda.Ruitesco.dataResp.type.DataLoginT;
import com.Yanda.Ruitesco.javabean.User;
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
		//doGet与doPost相同
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* 允许跨域的主机地址 */
		resp.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8848");
		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		resp.setHeader("Access-Control-Allow-Methods","*");
		/* 重新预检验跨域的缓存时间 (s) */
		resp.setHeader("Access-Control-Max-Age","3600");
		/* 允许跨域的请求头 */
		resp.setHeader("Access-Control-Allow-Headers","*");
		/* 是否携带cookie */
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		String mode = req.getParameter("mode");
		switch(mode){
		case "login":
			Login(req,resp);
			break;
		case "register":
			Register(req,resp);
			break;
		case "check_valid":
			CheckValid(req, resp);
			break;
		case "get_user_info":
			GetUserInfo(req,resp);
			break;
		case "forget_get_question":
			ForgetPassword(req,resp);
			break;
		case "forget_check_answer":
			ForgetCheck(req,resp);
			break;
		case "forget_reset_password":
			UpdatePassword(req,resp);
			break;
		case "reset_password":
			ResetPassword(req, resp);
			break;
		case "update_information":
			UpdateInformation(req, resp);
			break;
		case "get_information":
			GetInformation(req, resp);
			break;
		case "logout":
			LogOut(req, resp);
			break;
		default:
			System.out.println("mode参数异常");
			break;
		}
	}
	public void Login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		/**
		 * 功能：检查用户名有效
		 * request: username 用户名, password 密码
		 * 地址栏传参：mode="login"
		 * response: status 状态码(0成功, 1失败, 2其他情况), msg 反馈信息
		 * */
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		System.out.println(username+password);
		User user = user_service.Login(username, password);
		PrintWriter pw= resp.getWriter();
		if(user!=null)
		{
				DataLoginT data = new DataLoginT(user.getId(),user.getUsername(),user.getPhone(),user.getEmail()
							,user.getCreate_time(),user.getUpdate_time());
				DataResp<DataLoginT> dataResp = new  DataResp<DataLoginT>(0,null,data);
//				req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
				json = gson.toJson(dataResp);
		}
		else 
		{
			DataResp<Object> dataResp =new DataResp<Object>(1,"密码错误",null);
			json = gson.toJson(dataResp);

		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	public void Register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		/**
		 * 功能：用户注册
		 * request: username 用户名, password 密码, phone 电话号码, email 电子邮箱, role 身份, question 密保问题, answer 密保答案
		 *  地址栏传参：mode="register"
		 * response: status 状态码(0成功, 1失败), msg 反馈信息
		 * */
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String phone=req.getParameter("phone");
		String email=req.getParameter("email");
		String role=req.getParameter("role");
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");

		Date date = new Date();
		Timestamp create_time = new Timestamp(date.getTime());
		Timestamp update_time = create_time;
			
		User user = new User(username,password,phone,email,
							       role,question,answer,create_time,update_time);
		PrintWriter	pw = resp.getWriter();
		if(user_service.RegUser(user)==0)
		{
				DataResp<Object> dataResp = new DataResp<Object>(0,"校验成功",null);
//				req.getRequestDispatcher("login.jsp").forward(req, resp);
				json = gson.toJson(dataResp);
		}
		else 
		{
				DataResp<Object> dataResp = new DataResp<Object>(0,"用户已存在",null);
				json = gson.toJson(dataResp);
		}
		
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	
	public void CheckValid(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		/**
		 * 功能：检查用户名有效
		 * request: str 用户名, type 用户名种类(userName用户名，email邮箱)
		 * 地址栏传参：mode="check_valid"
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
		 * request: 无, session: username 用户名
		 * 地址栏传参：mode="get_user_info"
		 * response: status 状态码(0成功, 1失败), msg 反馈信息, data 用户信息(id, username, email, phone, createTime, updateTime)
		 * */
		
		//从session中获取用户名
		HttpSession session = req.getSession();
		String username = session.getAttribute("username").toString();	
		//创建回参对象, 失败创建String类型(返回msg)， 成功创建data数组类型(返回data数组)
		UserResponse<Object> userResponse = new UserResponse<Object>();
		//查找数据库找到对应用户信息
		try {
			userResponse = user_service.QueryUser(username);
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

	public void ForgetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String username = req.getParameter("username");
		User user = user_service.QueryUserByName(username);
		PrintWriter	pw = resp.getWriter();
		if(user!=null)
		{
			DataResp<String> dataResp = new DataResp<String>(0,null,user.getQuestion());
			json = gson.toJson(dataResp);

		}
		else 
		{
			DataResp<Object> dataResp = new DataResp<Object>(1,"用户名输入错误",null);
			json = gson.toJson(dataResp);
		}
		
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	public void ForgetCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String username = req.getParameter("username");
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
		User user = user_service.QueryUserByName(username);
		PrintWriter	pw = resp.getWriter();
		if(user!=null)
		{
			if(question.equals(user.getQuestion())&&answer.equals(user.getAnswer()))
			{
				DataResp<String> dataResp = new DataResp<String>(0,null,"3235ffe-fewff-ff34534");
				json = gson.toJson(dataResp);
			}else
			{
				DataResp<Object> dataResp = new DataResp<Object>(1,"问题答案错误",null);
				json = gson.toJson(dataResp);
			}
		}
		else 
		{
			DataResp<Object> dataResp = new DataResp<Object>(1,"用户不存在",null);
			json = gson.toJson(dataResp);
		}
		
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	public void UpdatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		/**
		 * 功能：获取登录用户信息
		 * request: passwordOld 旧密码, passwordNew 新密码, session: username 用户名
		 * 地址栏传参：mode="update_password"
		 * response: status 状态码(0成功, 1失败), msg 反馈信息, data 用户信息(id, username, email, phone, createTime, updateTime)
		 * */
		String passwordOld = req.getParameter("passwordOld");
		String passwordNew = req.getParameter("passwordNew");
		HttpSession session = req.getSession();
		String username = session.getAttribute("username").toString();
		
		PrintWriter pw = resp.getWriter();
		if(user_service.UpdatePassword(username,passwordOld,passwordNew)==0)
		{
			DataResp<Object> dataResp=new DataResp<Object>(0,"修改密码成功",null);
//			req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
			json = gson.toJson(dataResp);
		}else
		{
			DataResp<Object> dataResp=new DataResp<Object>(1,"旧密码输入错误",null);
			json = gson.toJson(dataResp);
		}
		
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	public void ResetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		/**
		 * 功能：登录状态下重置密码
		 * request: passwordOld 旧密码, passwordNew 新密码   session: username 用户名
		 * 地址栏传参：mode="reset_password"
		 * response: status 状态码(0成功, 1失败), msg 反馈信息
		 * */
		
		//获取入参
		String passwordOld = req.getParameter("passwordOld");
		String passwordNew = req.getParameter("passwordNew");
		System.out.println("passwordOld=" + passwordOld + " passwordNew" + passwordNew);
		HttpSession session = req.getSession();
		String username = session.getAttribute("username").toString();
		//创建出参对象
		UserResponse<String> userResponse = new UserResponse<String>();
		try {
			userResponse = user_service.ResetPassword(passwordOld, passwordNew, username);
			json = gson.toJson(userResponse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		PrintWriter pw;
		pw = resp.getWriter();
		pw.write(json);
		pw.close();
		
	}
	public void UpdateInformation(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		/**
		 * 功能：登录状态下更新个人信息
		 * request: email 电子邮件, phone 手机号码, question 密保问题, answer 密保答案  session: username 用户名
		 * 地址栏传参：mode="update_information"
		 * response: status 状态码(0成功, 1失败, 2数据库更新失败), msg 反馈信息
		 * */
		//从前端获取参数
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
	
		HttpSession session = req.getSession();
		String username = session.getAttribute("username").toString();
		
		//创建响应回参对象
		UserResponse<String> userResponse = new UserResponse<String>();
		try {
			userResponse = user_service.UpdateInformation(username, email, phone, question, answer);
			json = gson.toJson(userResponse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw = resp.getWriter();
		pw.write(json);
		pw.close();
	}
	public void GetInformation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/**
		 * 功能：获取当前登录用户信息
		 * request: 无
		 * 地址栏传参：mode="get_information"
		 * response: status 状态码(0成功, 1失败), msg 反馈信息, data 用户信息(id, username, email, phone, question, answer, role, createTime, updateTime)
		 * */
		//创建回参对象, 失败创建String类型(返回msg)， 成功创建data数组类型(返回data数组)
		UserResponse<Object> userResponse = new UserResponse<Object>();
		String username = "";
		//从cookie中获取当前用户信息
		Cookie[] cookie = req.getCookies();
		if(cookie == null || cookie.length <= 0) {
			userResponse.setStatus(10);
			userResponse.setMsg("用户未登录，无法获取当前用户信息,状态码：10, 强制退出登录");
		}
		else {
			for(Cookie c:cookie) {
				if(c.getName().equals("username"))
					username = c.getValue().toString();
			}
			//查找数据库找到对应用户信息
			try {
				userResponse = user_service.GetInformation(username);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		json = gson.toJson(userResponse);
		PrintWriter pw;
		pw = resp.getWriter();
		pw.write(json);
		pw.close();
	}
	public void LogOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/**
		 * 功能：退出登录
		 * request: 无, session: username 用户名
		 * 地址栏传参：mode="logout"
		 * response: status 状态码(0成功, 1失败), msg 反馈信息
		 * */
		//创建回参response对象
		UserResponse<String> userResponse = new UserResponse<String>();
		req.getSession().invalidate();//清空session对象中的参数
		//获取session中的userName参数，如果没有则已退出登录
		HttpSession session = req.getSession();
		String username = "";
		username = session.getAttribute("username").toString();
		if(username.equals("")) {
			userResponse.setStatus(0);
			userResponse.setMsg("退出成功");
		}
		else {
			userResponse.setStatus(1);
			userResponse.setMsg("服务器异常");
		}
		json = gson.toJson(userResponse);
		PrintWriter pw = resp.getWriter();
		pw.write(json);
		pw.close();
	}
}

