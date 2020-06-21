package com.Yanda.Ruitesco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Yanda.Ruitesco.service.IProductService;
import com.Yanda.Ruitesco.service.impl.ProductServiceImpl;
import com.Yanda.Ruitesco.utils.response.MessageResponse;
import com.google.gson.Gson;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/manage/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//创建Gson对象，转化json
	Gson gson = new Gson();
	IProductService product_service = new ProductServiceImpl();
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
		/* 允许跨域的主机地址 */
		response.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8848");
		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		response.setHeader("Access-Control-Allow-Methods","*");
		/* 重新预检验跨域的缓存时间 (s) */
		response.setHeader("Access-Control-Max-Age","3600");
		/* 允许跨域的请求头 */
		response.setHeader("Access-Control-Allow-Headers","*");
		/* 是否携带cookie */
		response.setHeader("Access-Control-Allow-Credentials", "true");
		//根据参数mode值执行对应方法;
		String mode = "";
		mode = request.getParameter("mode");
		switch (mode) {
		case "list":
			List(request, response);
			break;
		case "search":
			Search(request, response);
			break;
		case "upload":
//			Upload(request, response);
			break;
		case "detail":
			Detail(request, response);
			break;
		case "set_sale_status":
			SetSaleStatus(request, response);
		default:
			break;
		}
	}
	public void List(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：获取本页中的元素
		 * request: pageNum 页码,  pageSize 页面大小  session: username
		 * 地址栏传参：mode="list"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
//		String username = "123456";
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		messageResponse = product_service.GetProductInPage(pageNum, pageSize, username);
		String json = gson.toJson(messageResponse);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void Search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：根据名称或id查找商品
		 * request: productName or productId, pageNum, pageSize  session: username
		 * 地址栏传参：mode="search"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
//		String username = "123456";
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String productName = request.getParameter("productName");
		int productId = 0;
		productId = Integer.parseInt(request.getParameter("productId"));
		if(productName == null) {
			if(productId == 0)
			{
				messageResponse.setStatus(2);
				messageResponse.setMsg("未获取到名称或id");
			}
			else {
				messageResponse = product_service.QueryProductById(pageNum, pageSize, productId, username);
			}
		}
		else
			messageResponse = product_service.QueryProductByName(pageNum, pageSize, productName, username);
		String json = gson.toJson(messageResponse);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void Upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：图片上传
		 * request: productName or productId  session: username
		 * 地址栏传参：mode="upload"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
//		String username = "123456";
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
	}
	public void Detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：根据id查看商品详情
		 * request: productId  session: username
		 * 地址栏传参：mode="detail"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
//		String username = "123456";
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		int productId = Integer.parseInt(request.getParameter("productId"));
		messageResponse = product_service.QueryProductById(productId, username);
		String json = gson.toJson(messageResponse);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void SetSaleStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：设置商品状态（上下架）
		 * request: productId, status  session: username
		 * 地址栏传参：mode="set_sale_status"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
//		String username = "123456";
		int productId = Integer.parseInt(request.getParameter("productId"));
		int status = Integer.parseInt(request.getParameter("status"));
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		messageResponse = product_service.SetProductStatus(productId, status, username);
		String json = gson.toJson(messageResponse);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void Save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：设置商品状态（上下架）
		 * request: productId, status  session: username
		 * 地址栏传参：mode="set_sale_status"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
//		String username = "123456";
		int productId = Integer.parseInt(request.getParameter("productId"));
		int status = Integer.parseInt(request.getParameter("status"));
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		messageResponse = product_service.SetProductStatus(productId, status, username);
		String json = gson.toJson(messageResponse);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
}
