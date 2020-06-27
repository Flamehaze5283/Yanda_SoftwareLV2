package com.Yanda.Ruitesco.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Yanda.Ruitesco.service.IShippingAddrService;
import com.Yanda.Ruitesco.service.impl.ShippingAddrServiceImpl;
import com.Yanda.Ruitesco.utils.response.MessageType;
import com.google.gson.Gson;

/**
 * Servlet implementation class ShippingAddrServlet
 */
@WebServlet("/shipping")
public class ShippingAddrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //初始化ShippingAddrService
	IShippingAddrService ship_service = new ShippingAddrServiceImpl();
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
		case "add":
			AddAddress(request, response);
			break;
		case "del":
			DeleteAddress(request, response);
			break;
		case "update":
			UpdateAddress(request, response);
			break;
		case "select":
			SelectAddress(request, response);
		case "list":
			SelectAllAddress(request, response);
			break;
		default:
			System.out.println("mode参数异常");
			break;
		}
	}
	
	public void AddAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：添加收货地址
		 * request: userId, receiverName, receiverPhone, receiverMobile, receiverProvince, receiverCity, receiverAddress, receiverZip  session: username, userid
		 * 地址栏传参：mode="save"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
		int userid = 0;
		if(session.getAttribute("userid") != null)
			userid = Integer.parseInt(session.getAttribute("userid").toString());
		MessageType<Object> messageType;
		if(userid == 0 && username.equals("")){
			messageType = new MessageType<Object>(1, "用户未登录", null);
		}
		else {
			int user_id = Integer.parseInt(request.getParameter("userId"));
			String receiverName = request.getParameter("receiverName");
			String receiverPhone = request.getParameter("receiverPhone");
			String receiverMobile = request.getParameter("receiverMobile");
			String receiverProvince = request.getParameter("receiverProvince");
			String receiverDistrict = "";
			if(request.getParameter("receiverDistrict") != null)
				receiverDistrict = request.getParameter("receiverDistrict");
			else
				receiverDistrict = receiverProvince;
			String receiverCity = request.getParameter("receiverCity");
			String receiverAddress = request.getParameter("receiverAddress");
			int receiverZip = Integer.parseInt(request.getParameter("receiverZip"));
			messageType = ship_service.addAddress(user_id, receiverName, receiverPhone, receiverMobile, receiverProvince, receiverDistrict, receiverCity, receiverAddress, receiverZip);
		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public void DeleteAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：删除地址
		 * request: shippingId 地址id session: username, userid
		 * 地址栏传参：mode="save"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
		int userid = 0;
		if(session.getAttribute("userid") != null)
			userid = Integer.parseInt(session.getAttribute("userid").toString());
		MessageType<Object> messageType;
		if(userid == 0 && username.equals("")){
			messageType = new MessageType<Object>(1, "用户未登录", null);
		}
		else {
			int shippingId = Integer.parseInt(request.getParameter("shippingId"));
			messageType = ship_service.deleteAddress(shippingId);
		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public void UpdateAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：登录状态下修改地址
		 * request: shippingId 地址id session: username, userid
		 * 地址栏传参：mode="update"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
		int userid = 0;
		if(session.getAttribute("userid") != null)
			userid = Integer.parseInt(session.getAttribute("userid").toString());
		MessageType<Object> messageType;
		if(userid == 0 && username.equals("")){
			messageType = new MessageType<Object>(1, "用户未登录", null);
		}
		else {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("receiverName");
			String phone = request.getParameter("receiverPhone");
			String mobile = request.getParameter("receiverMobile");
			String province = request.getParameter("receiverProvince");
			String district = "";
			if(request.getParameter("receiverDistrict") != null)
				district = request.getParameter("receiverDistrict");
			else
				district = province;
			String city = request.getParameter("receiverCity");
			String address = request.getParameter("receiverCity");
			int zip = Integer.parseInt(request.getParameter("receiverZip"));
			messageType = ship_service.updateAddress(id, name, phone, mobile, province, district, city, address, zip);
		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public void SelectAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：登录状态下查看具体地址
		 * request: shippingId 地址id session: username, userid
		 * 地址栏传参：mode="select"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
		int userid = 0;
		if(session.getAttribute("userid") != null)
			userid = Integer.parseInt(session.getAttribute("userid").toString());
		MessageType<Object> messageType;
		if(userid == 0 && username.equals("")){
			messageType = new MessageType<Object>(1, "请登录后查询", null);
		}
		else {
			int id = Integer.parseInt(request.getParameter("shippingId"));
			messageType = ship_service.selectAddress(id);
		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public void SelectAllAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：登录状态下查看具体地址
		 * request: shippingId 地址id session: username, userid
		 * 地址栏传参：mode="select"
		 * response: status 状态码(0成功, 1失败, 10管理员未登录), msg 反馈信息, data 获取的子类别信息集合与页码相关参数
		 * */
		HttpSession session = request.getSession();
		String username = "";
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
		int userid = 0;
		if(session.getAttribute("userid") != null)
			userid = Integer.parseInt(session.getAttribute("userid").toString());
		MessageType<Object> messageType;
		if(userid == 0 && username.equals("")){
			messageType = new MessageType<Object>(1, "请登录后查询", null);
		}
		else {
			messageType = ship_service.selectAddressByUserId(userid);
		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
}
