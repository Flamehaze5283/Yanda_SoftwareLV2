package com.Yanda.Ruitesco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Yanda.Ruitesco.service.ICategoryService;
import com.Yanda.Ruitesco.service.impl.CategoryServiceImpl;
import com.Yanda.Ruitesco.utils.MessageResponse;
import com.google.gson.Gson;

/**
 * Servlet implementation class TypeServlet
 */
@WebServlet("/manage/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //初始化CategoryService
	ICategoryService category_service = new CategoryServiceImpl();
	//创建Gson对象，转化json
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
		
		//根据参数mode值执行对应方法;
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
			GetDeepCategory(request, response);
			break;
		default:
			break;
		}
		
	}
	public void GetCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：获取类别的子节点
		 * request: parent_id 父类id  session: username 用户名
		 * 地址栏传参：mode="get_category"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息
		 * */
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		messageResponse = category_service.GetCategoryByParentId(category_id, username);
		String json = gson.toJson(messageResponse);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void InsertCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：增加新的商品种类
		 * request: parent_id 父类id, categoryName 种类名称  session: username
		 * 地址栏传参：mode="insert_category"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息
		 * */
		MessageResponse<String> messageResponse = new MessageResponse<String>();
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
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
		 * 功能：修改种类名称
		 * request: categoryId 种类id, categoryName 种类名称  session: username
		 * 地址栏传参：mode="set_category_name"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息
		 * */
		MessageResponse<String> messageResponse = new MessageResponse<String>();
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("categoryName");
		messageResponse = category_service.UpdateCategoryName(categoryId, categoryName, username);
		String json = gson.toJson(messageResponse);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void GetDeepCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：获取当前种类id并查询其所有子类
		 * request: categoryId 种类id  session: username
		 * 地址栏传参：mode="get_deep_category"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息
		 * */
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
	}
}
