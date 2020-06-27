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
    //��ʼ��ShippingAddrService
	IShippingAddrService ship_service = new ShippingAddrServiceImpl();
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
			System.out.println("mode�����쳣");
			break;
		}
	}
	
	public void AddAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * ���ܣ�����ջ���ַ
		 * request: userId, receiverName, receiverPhone, receiverMobile, receiverProvince, receiverCity, receiverAddress, receiverZip  session: username, userid
		 * ��ַ�����Σ�mode="save"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
			messageType = new MessageType<Object>(1, "�û�δ��¼", null);
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
		 * ���ܣ�ɾ����ַ
		 * request: shippingId ��ַid session: username, userid
		 * ��ַ�����Σ�mode="save"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
			messageType = new MessageType<Object>(1, "�û�δ��¼", null);
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
		 * ���ܣ���¼״̬���޸ĵ�ַ
		 * request: shippingId ��ַid session: username, userid
		 * ��ַ�����Σ�mode="update"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
			messageType = new MessageType<Object>(1, "�û�δ��¼", null);
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
		 * ���ܣ���¼״̬�²鿴�����ַ
		 * request: shippingId ��ַid session: username, userid
		 * ��ַ�����Σ�mode="select"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
			messageType = new MessageType<Object>(1, "���¼���ѯ", null);
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
		 * ���ܣ���¼״̬�²鿴�����ַ
		 * request: shippingId ��ַid session: username, userid
		 * ��ַ�����Σ�mode="select"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
			messageType = new MessageType<Object>(1, "���¼���ѯ", null);
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
