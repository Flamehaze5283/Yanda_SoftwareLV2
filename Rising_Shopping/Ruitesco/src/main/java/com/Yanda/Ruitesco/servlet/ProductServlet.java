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
	//����Gson����ת��json
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
		 * ���ܣ���ȡ��ҳ�е�Ԫ��
		 * request: pageNum ҳ��,  pageSize ҳ���С  session: username
		 * ��ַ�����Σ�mode="list"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
		 * ���ܣ��������ƻ�id������Ʒ
		 * request: productName or productId, pageNum, pageSize  session: username
		 * ��ַ�����Σ�mode="search"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
				messageResponse.setMsg("δ��ȡ�����ƻ�id");
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
		 * ���ܣ�ͼƬ�ϴ�
		 * request: productName or productId  session: username
		 * ��ַ�����Σ�mode="upload"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
		 * ���ܣ�����id�鿴��Ʒ����
		 * request: productId  session: username
		 * ��ַ�����Σ�mode="detail"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
		 * ���ܣ�������Ʒ״̬�����¼ܣ�
		 * request: productId, status  session: username
		 * ��ַ�����Σ�mode="set_sale_status"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
		 * ���ܣ�������Ʒ״̬�����¼ܣ�
		 * request: productId, status  session: username
		 * ��ַ�����Σ�mode="set_sale_status"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
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
