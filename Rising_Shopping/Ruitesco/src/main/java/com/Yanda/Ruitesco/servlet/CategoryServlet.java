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

import com.Yanda.Ruitesco.service.ICategoryService;
import com.Yanda.Ruitesco.service.impl.CategoryServiceImpl;
import com.Yanda.Ruitesco.utils.response.MessageResponse;
import com.Yanda.Ruitesco.utils.response.responsetype.category.ParentId;
import com.google.gson.Gson;

/**
 * Servlet implementation class TypeServlet
 */
@WebServlet("/manage/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //��ʼ��CategoryService
	ICategoryService category_service = new CategoryServiceImpl();
	//����Gson����ת��json
	Gson gson = new Gson();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* ��������������ַ */
		response.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8848");
		/* �����������󷽷�GET, POST, HEAD �� */
		response.setHeader("Access-Control-Allow-Methods","*");
		/* ����Ԥ�������Ļ���ʱ�� (s) */
		response.setHeader("Access-Control-Max-Age","3600");
		/* ������������ͷ */
		response.setHeader("Access-Control-Allow-Headers","*");
		/* �Ƿ�Я��cookie */
		response.setHeader("Access-Control-Allow-Credentials", "true");
		//���ݲ���modeִֵ�ж�Ӧ����;
		String mode = "";
		mode = request.getParameter("mode");
		switch (mode) {
		case "get_category":
			GetCategory(request, response);
			break;
		case "insert_category":
			InsertCategory(request, response);
			break;
		case "set_category_name":
			SetCategoryName(request, response);
			break;
		case "get_deep_category":
			try {
				GetDeepCategory(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "back":
			Back(request, response);
			break;
//		case "delete_category":
//			try {
//				DeleteCategory(request, response);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			break;
		default:
			System.out.println("mode�����쳣");
			break;
		}
		
	}
	public void GetCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * ���ܣ���ȡ�����ӽڵ�
		 * request: parent_id ����id  session: username �û���
		 * ��ַ�����Σ�mode="get_category"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ
		 * */
		HttpSession session = request.getSession();
		Object session_username = session.getAttribute("username");
		String username = "";
		if(session_username != null)
			username = session_username.toString();
//		String username = "123456";
		username = "123456";
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		messageResponse = category_service.GetCategoryByParentId(category_id, username);
		String json = gson.toJson(messageResponse);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void InsertCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * ���ܣ������µ���Ʒ����
		 * request: parent_id ����id, categoryName ��������  session: username
		 * ��ַ�����Σ�mode="insert_category"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ
		 * */
		MessageResponse<String> messageResponse = new MessageResponse<String>();
		HttpSession session = request.getSession(false);
		Object session_username = session.getAttribute("username");
		String username = "";
		if(session_username != null)
			username = session_username.toString();
//		String username = "123456";
		int parentId = Integer.parseInt(request.getParameter("parentId"));
		String categoryName = request.getParameter("categoryName");
		messageResponse = category_service.InsertNewCategory(parentId, categoryName, username);
		String json = gson.toJson(messageResponse);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void SetCategoryName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * ���ܣ��޸���������
		 * request: categoryId ����id, categoryName ��������  session: username
		 * ��ַ�����Σ�mode="set_category_name"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ
		 * */
		MessageResponse<String> messageResponse = new MessageResponse<String>();
		HttpSession session = request.getSession(false);
		Object session_username = session.getAttribute("username");
		String username = "";
		if(session_username != null)
			username = session_username.toString();
//		String username = "123456";
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("categoryName");
		messageResponse = category_service.UpdateCategoryName(categoryId, categoryName, username);
		String json = gson.toJson(messageResponse);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void GetDeepCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		/**
		 * ���ܣ���ȡ��ǰ����id����ѯ����������
		 * request: categoryId ����id  session: username
		 * ��ַ�����Σ�mode="get_deep_category"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ
		 * */
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		HttpSession session = request.getSession();
		Object session_username = session.getAttribute("username");
		String username = "";
		if(session_username != null)
			username = session_username.toString();
//		String username = "123456";
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		messageResponse = category_service.GetAllCategoryByParentId(categoryId, username);
		String json = gson.toJson(messageResponse);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void Back(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		parent_id = category_service.GetParentId(parent_id);
		ParentId result = new ParentId(parent_id);
		String json = gson.toJson(result);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void DeleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		/**
		 * ���ܣ�ɾ����ǰ���༰����������
		 * request: categoryId ����id  session: username
		 * ��ַ�����Σ�mode="delete_category"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ
		 * */
		MessageResponse<String> messageResponse = new MessageResponse<String>();
		HttpSession session = request.getSession();
		Object session_username = session.getAttribute("username");
		String username = "";
		if(session_username != null)
			username = session_username.toString();
//		String username = "123456";
		int categoryId = Integer.parseInt(request.getParameter("category_id"));
		messageResponse = category_service.DeleteCategoryById(categoryId, username);
		String json = gson.toJson(messageResponse);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
}
