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
	private static final long serialVersionUID = 1L;		//�������
	IUserService user_service = new UserServiceImpl();
	Gson gson = new Gson();
	String json;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		//doGet��doPost��ͬ
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
			//��¼
		}
		else if(type == "register") {
			//ע��
		}
		else if(type == "check_valid") {
			//���Ϸ���
			CheckValid(req, resp);
		}
		else if(type == "get_user_info") {
			GetUserInfo(req, resp);
		}
		else if(type == "exit") {
			//�˳�
		}
		else {
			//����
		}
	}
	public void CheckValid(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		/**
		 * ���ܣ�����û�����Ч
		 * request: str �û���, type �û�������(userName�û�����email����)
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
		 * request: ��, session: str �û���
		 * response: status ״̬��(0�ɹ�, 1ʧ��), msg ������Ϣ, data �û���Ϣ(id, username, email, phone, createTime, updateTime)
		 * */
		
		//��session�л�ȡ�û���
		HttpSession session = req.getSession();
		String userName = session.getAttribute("username").toString();
		
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
	
	public void ResetPassword(HttpServletRequest req, HttpServletResponse resp)
	{
		/**
		 * ���ܣ���¼״̬����������
		 * request: passwordOld ������, passwordNew ������
		 * response: status ״̬��(0�ɹ�, 1ʧ��), msg ������Ϣ
		 * */
		
		//��ȡ���
		String passwordOld = req.getParameter("passwordOld");
		String passwordNew = req.getParameter("passwordNew");
		
		HttpSession session = req.getSession();
		String userName = session.getAttribute("userName").toString();
		//�������ζ���
		UserResponse<String> userResponse = new UserResponse<String>();
		
	}
}


