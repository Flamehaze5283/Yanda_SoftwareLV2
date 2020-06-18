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

import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.javabean.UserResponse;
import com.Yanda.Ruitesco.service.IUserService;
import com.Yanda.Ruitesco.service.impl.UserServiceImpl;
import com.google.gson.Gson;

@WebServlet("/user")
public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;		//�������
	IUserService user_service = new UserServiceImpl();
	Gson gson = new Gson();
	String json;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet��doPost��ͬ
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* ��������������ַ */
		resp.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8848");
		/* �����������󷽷�GET, POST, HEAD �� */
		resp.setHeader("Access-Control-Allow-Methods","*");
		/* ����Ԥ�������Ļ���ʱ�� (s) */
		resp.setHeader("Access-Control-Max-Age","3600");
		/* ������������ͷ */
		resp.setHeader("Access-Control-Allow-Headers","*");
		/* �Ƿ�Я��cookie */
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
			break;
		case "forget_check_answer":
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
			System.out.println("mode�����쳣");
			break;
		}
//		if(mode.equals("login")) {
//			//��¼
//		}
//		else if(mode.equals("register")) {
//			//ע��
//		}
//		else if(mode.equals("check_valid")) {
//			//���Ϸ���
//			CheckValid(req, resp);
//		}
//		else if(mode.equals("get_user_info")) {
//			//��ȡ��¼�û���Ϣ
//			GetUserInfo(req, resp);
//		}
//		else if(mode.equals("reset_password")) {
//			//��¼״̬���޸�����
//			ResetPassword(req, resp);
//		}
//		else if(mode.equals("update_information")) {
//			UpdateInformation(req, resp);
//		}
//		else if(mode.equals("get_information")) {
//			GetInformation(req, resp);
//		}
//		else if(mode.equals("logout")) {
//			//�˳�
//			LogOut(req, resp);
//		}
//		else {
//			//����
//			System.out.println("mode�����쳣");
//		}
	}
	public void Login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		/**
		 * ���ܣ�����û�����Ч
		 * request: username �û���, password ����
		 * ��ַ�����Σ�mode="login"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 2�������), msg ������Ϣ
		 * */
		DataResp dataResp=null;
		String username=req.getParameter("username");
		//��Ϊǰ�˼��ܣ���˽����ڼ����˼��ܺ�����
		String password=req.getParameter("password");
		
		User user = user_service.Login(username, password);
		if(user!=null)
		{
			dataResp=new DataResp(0,new Object(){
			@SuppressWarnings("unused")
			public int id=user.getId();
			@SuppressWarnings("unused")
			public String username=user.getUsername();
			@SuppressWarnings("unused")
			public String email=user.getEmail();
			@SuppressWarnings("unused")
			public String phone=user.getPhone();
			@SuppressWarnings("unused")
			public String role=user.getRole();
			@SuppressWarnings("unused")
			public Timestamp createTime =user.getCreate_time();
			@SuppressWarnings("unused")
			public Timestamp updateTime=user.getUpdate_time();
			},"");
			req.getRequestDispatcher("success.jsp").forward(req, resp);
		}
		else 
		{	
			dataResp=new DataResp(1,null,"�������");
		}

		json = gson.toJson(dataResp);
		System.out.println(json);
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			pw.write(json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public void Register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		/**
		 * ���ܣ��û�ע��
		 * request: username �û���, password ����, phone �绰����, email ��������, role ���, question �ܱ�����, answer �ܱ���
		 *  ��ַ�����Σ�mode="register"
		 * response: status ״̬��(0�ɹ�, 1ʧ��), msg ������Ϣ
		 * */
		DataResp dataResp=null;
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

		if(user_service.RegUser(user)==0)
		{
				dataResp=new DataResp(0,null,"ע��ɹ�");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		else 
		{
				dataResp=new DataResp(0,null,"�û��Ѵ���");
		}

		
		json = gson.toJson(dataResp);
		System.out.println(json);
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			pw.write(json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
//	public void GetUserInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException
//	{
//		DataResp dataResp=null;
//		HttpSession session = req.getSession();
//		String userName = session.getAttribute("username").toString();
//		User user = userService.QueryUserByName(userName);
//		
//		if(user!=null)
//		{
//			dataResp=new DataResp(0,new Object() {
//				@SuppressWarnings("unused")
//				public int id=user.getId();
//				@SuppressWarnings("unused")
//				public String email=user.getEmail();
//				@SuppressWarnings("unused")
//				public String phone=user.getPhone();
//				@SuppressWarnings("unused")
//				public Timestamp createTime=user.getCreate_time();
//				@SuppressWarnings("unused")
//				public Timestamp updateTime=user.getUpdate_time();
//			},"");
//		}else {
//			dataResp=new DataResp(1,null,"�û�δ��¼���޷���ȡ��ǰ�û���Ϣ");
//		}
//		
//		json = gson.toJson(dataResp);
//		System.out.println(json);
//		PrintWriter pw;
//		try {
//			pw = resp.getWriter();
//			pw.write(json);
//			pw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
//	}
	
	public void CheckValid(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		/**
		 * ���ܣ�����û�����Ч
		 * request: str �û���, type �û�������(userName�û�����email����)
		 * ��ַ�����Σ�mode="check_valid"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 2�������), msg ������Ϣ
		 * */
		
		//��ȡ�û����������
		String user_name = req.getParameter("str");
		String user_type = req.getParameter("type");
		//�����������ز�response����
		UserResponse<String> userResponse = new UserResponse<String>();
		try {
			//����û����Ƿ���Ч
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
		 * ���ܣ���ȡ��¼�û���Ϣ
		 * request: ��, session: userName �û���
		 * ��ַ�����Σ�mode="get_user_info"
		 * response: status ״̬��(0�ɹ�, 1ʧ��), msg ������Ϣ, data �û���Ϣ(id, username, email, phone, createTime, updateTime)
		 * */
		
		//��session�л�ȡ�û���
		HttpSession session = req.getSession();
		String userName = session.getAttribute("userName").toString();	
		//�����زζ���, ʧ�ܴ���String����(����msg)�� �ɹ�����data��������(����data����)
		UserResponse<Object> userResponse = new UserResponse<Object>();
		//�������ݿ��ҵ���Ӧ�û���Ϣ
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

	public void ForgetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		
	}
	
	public void UpdatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		/**
		 * ���ܣ���ȡ��¼�û���Ϣ
		 * request: passwordOld ������, passwordNew ������, session: userName �û���
		 * ��ַ�����Σ�mode="get_user_info"
		 * response: status ״̬��(0�ɹ�, 1ʧ��), msg ������Ϣ, data �û���Ϣ(id, username, email, phone, createTime, updateTime)
		 * */
		DataResp dataResp=null;
		//��ȡ���
		String passwordOld = req.getParameter("passwordOld");
		String passwordNew = req.getParameter("passwordNew");
		
		HttpSession session = req.getSession();
		String userName = session.getAttribute("userName").toString();
		
		if(user_service.UpdatePassword(userName,passwordOld,passwordNew)==0)
		{
			dataResp=new DataResp(0,null,"�޸�����ɹ�");
			req.getRequestDispatcher("main.jsp").forward(req, resp);
		}else
		{
			dataResp=new DataResp(1,null,"�������������");
		}
		
		json = gson.toJson(dataResp);
		System.out.println(json);
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			pw.write(json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void ResetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		/**
		 * ���ܣ���¼״̬����������
		 * request: passwordOld ������, passwordNew ������   session: userName �û���
		 * ��ַ�����Σ�mode="reset_password"
		 * response: status ״̬��(0�ɹ�, 1ʧ��), msg ������Ϣ
		 * */
		
		//��ȡ���
		String passwordOld = req.getParameter("passwordOld");
		String passwordNew = req.getParameter("passwordNew");
		System.out.println("passwordOld=" + passwordOld + " passwordNew" + passwordNew);
		HttpSession session = req.getSession();
		String userName = session.getAttribute("userName").toString();
		//�������ζ���
		UserResponse<String> userResponse = new UserResponse<String>();
		try {
			userResponse = user_service.ResetPassword(passwordOld, passwordNew, userName);
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
		 * ���ܣ���¼״̬�¸��¸�����Ϣ
		 * request: email �����ʼ�, phone �ֻ�����, question �ܱ�����, answer �ܱ���  session: userName �û���
		 * ��ַ�����Σ�mode="update_information"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 2���ݿ����ʧ��), msg ������Ϣ
		 * */
		//��ǰ�˻�ȡ����
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
	
		HttpSession session = req.getSession();
		String userName = session.getAttribute("userName").toString();
		
		//������Ӧ�زζ���
		UserResponse<String> userResponse = new UserResponse<String>();
		try {
			userResponse = user_service.UpdateInformation(userName, email, phone, question, answer);
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
		 * ���ܣ���ȡ��ǰ��¼�û���Ϣ
		 * request: ��
		 * ��ַ�����Σ�mode="get_information"
		 * response: status ״̬��(0�ɹ�, 1ʧ��), msg ������Ϣ, data �û���Ϣ(id, username, email, phone, question, answer, role, createTime, updateTime)
		 * */
		//�����زζ���, ʧ�ܴ���String����(����msg)�� �ɹ�����data��������(����data����)
		UserResponse<Object> userResponse = new UserResponse<Object>();
		String userName = "";
		//��cookie�л�ȡ��ǰ�û���Ϣ
		Cookie[] cookie = req.getCookies();
		if(cookie == null || cookie.length <= 0) {
			userResponse.setStatus(10);
			userResponse.setMsg("�û�δ��¼���޷���ȡ��ǰ�û���Ϣ,״̬�룺10, ǿ���˳���¼");
		}
		else {
			for(Cookie c:cookie) {
				if(c.getName().equals("userName"))
					userName = c.getValue().toString();
			}
			//�������ݿ��ҵ���Ӧ�û���Ϣ
			try {
				userResponse = user_service.GetInformation(userName);
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
		 * ���ܣ��˳���¼
		 * request: ��, session: userName �û���
		 * ��ַ�����Σ�mode="logout"
		 * response: status ״̬��(0�ɹ�, 1ʧ��), msg ������Ϣ
		 * */
		//�����ز�response����
		UserResponse<String> userResponse = new UserResponse<String>();
		req.getSession().invalidate();//���session�����еĲ���
		//��ȡsession�е�userName���������û�������˳���¼
		HttpSession session = req.getSession();
		String userName = "";
		userName = session.getAttribute("userName").toString();
		if(userName.equals("")) {
			userResponse.setStatus(0);
			userResponse.setMsg("�˳��ɹ�");
		}
		else {
			userResponse.setStatus(1);
			userResponse.setMsg("�������쳣");
		}
		json = gson.toJson(userResponse);
		PrintWriter pw = resp.getWriter();
		pw.write(json);
		pw.close();
	}
}

