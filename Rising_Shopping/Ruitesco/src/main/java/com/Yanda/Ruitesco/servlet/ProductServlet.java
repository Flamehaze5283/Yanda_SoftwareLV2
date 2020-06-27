package com.Yanda.Ruitesco.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.Yanda.Ruitesco.service.IProductService;
import com.Yanda.Ruitesco.service.impl.ProductServiceImpl;
import com.Yanda.Ruitesco.utils.response.MessageResponse;
import com.Yanda.Ruitesco.utils.response.MessageType;
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
			Upload(request, response);
			break;
		case "detail":
			Detail(request, response);
			break;
		case "set_sale_status":
			SetSaleStatus(request, response);
			break;
		case "save":
			Save(request, response);
			break;
		case "get_detail":
			GetDetail(request, response);
			break;
		default:
			System.out.println("mode�����쳣");
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
//		HttpSession session = request.getSession();
//		String username = "";
//		if(session.getAttribute("username") != null)
//			username = session.getAttribute("username").toString();
		String username = "123456";
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		messageResponse = product_service.GetProductInPage(pageNum, pageSize, username);
		String json = gson.toJson(messageResponse);
		System.out.println(json);
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
//		HttpSession session = request.getSession();
//		String username = "";
//		if(session.getAttribute("username") != null)
//			username = session.getAttribute("username").toString();
		String username = "123456";
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String productName = "";
		if(request.getParameter("productName") != null)
			productName = request.getParameter("productName");
		int productId = 0;
		if(request.getParameter("productId") != null)
			productId = Integer.parseInt(request.getParameter("productId"));
		if(productName.equals("")) {
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
		System.out.println(json);
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
//		int category_id = 999999;
		if(username.equals("")) {
			messageResponse.setStatus(10);
			messageResponse.setMsg("����Աδ��¼");
		}
		else {
			String path = "D:/�������/�γ����/git/Rising_Shopping/image/temp";
			String fn = null;
			//����������ɴ洢·��
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			upload.setSizeMax(1024*1024*10);//�ļ���СΪ10MB
			try {
				List<FileItem> fileItems = upload.parseRequest(request);
				//�������ļ���Ҳ�������ַ���������ͨ����
				for(FileItem fi:fileItems) {
					if(fi.isFormField()) {
//						if(fi.getFieldName().equals("category_id")) {
//							if(fi.getString("UTF-8") != null)
//								category_id = Integer.parseInt(fi.getString("UTF-8"));
//						}
//						System.out.println("��ֵΪ��" + fi.getFieldName() + " " + fi.getString("UTF-8"));
//						if(category_id != 999999) {
//							List<String> categoryName = new ArrayList<String>();
//							categoryName = product_service.getAllCategoryById(category_id);
//							for(int i = categoryName.size() - 1; i >= 0; i--) {
//								String temp = categoryName.get(i);
//								path += temp + "/";
//							}
//							System.out.println("�洢·����" + path);
//						}
						System.out.println("ͼƬ�ϴ��Ĳ����ļ���");
						messageResponse.setStatus(0);
						messageResponse.setMsg("ͼƬ�ϴ��Ĳ����ļ���");
					}
					else {
						//���ļ�
						//��ȡͼƬ��׺
//						if(category_id == 999999) {
//							messageResponse.setStatus(10);
//							messageResponse.setMsg("δ��ȡ��category_id");
//						}
//						else {
							String format = fi.getName().substring(fi.getName().indexOf("."), fi.getName().length());
							fn = UUID.randomUUID().toString().replaceAll("-", "") + format;
							System.out.println("�ļ�����" + fn);//�ļ���
							//fn������C:/abc/de/tt/fish.jpg
							File file = new File(path);
							if(!file.exists())
								file.mkdir();
							fi.write(new File(path, fn));
							messageResponse.setStatus(0);
							messageResponse.setMsg(path + '/' + fn);
//						}
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		String json = gson.toJson(messageResponse);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
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
		System.out.println(json);
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
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	public void Save(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * ���ܣ���ӻ��޸���Ʒ����
		 * request: id, category_id, name, subtitle, mainImages, subImages, detail, price, stock, status  session: username
		 * ��ַ�����Σ�mode="save"
		 * response: status ״̬��(0�ɹ�, 1ʧ��, 10����Աδ��¼), msg ������Ϣ, data ��ȡ���������Ϣ������ҳ����ز���
		 * */
//		HttpSession session = request.getSession();
//		String username = "";
//		if(session.getAttribute("username") != null)
//			username = session.getAttribute("username").toString();
		String username = "123456";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
//		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
//		String name = request.getParameter("name");
//		String subtitle = request.getParameter("subtitle");
//		String mainImage = request.getParameter("mainImage");
//		String subImages = request.getParameter("subImages");
//		String detail = request.getParameter("detail");
//		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
//		int stock = Integer.parseInt(request.getParameter("stock"));
//		int status = Integer.parseInt(request.getParameter("status"));
//		int id = Integer.parseInt(request.getParameter("id"));
		int categoryId = 0;
		String name = "";
		String subtitle = "";
		String mainImage = "";
		String subImages = "";
		String detail = "richText";
		BigDecimal price = BigDecimal.valueOf(0.01);
		int stock = 0;
		int status = 0;
		int id = 0;
		try {
			List<FileItem> fileItem = upload.parseRequest(request);
			for(FileItem fi:fileItem) {
				if(fi.isFormField()) {
					System.out.println(fi.getFieldName());
					if(fi.getFieldName().equals("categoryId"))
						categoryId = Integer.parseInt(fi.getString("UTF-8"));
					else if(fi.getFieldName().equals("name"))
						name = fi.getString("UTF-8");
					else if(fi.getFieldName().equals("subtitle"))
						subtitle = fi.getString("UTF-8");
					else if(fi.getFieldName().equals("mainImage")) {
						mainImage = fi.getString("UTF-8");
						System.out.println("mainImage:" + mainImage);
					}
					else if(fi.getFieldName().equals("subImages"))
						subImages = fi.getString("UTF-8");
					else if(fi.getFieldName().equals("detail")) {
						detail = fi.getString("UTF-8");
						System.out.println(detail);
					}
					else if(fi.getFieldName().equals("price"))
						price = BigDecimal.valueOf(Double.parseDouble(fi.getString("UTF-8")));
					else if(fi.getFieldName().equals("stock"))
						stock = Integer.parseInt(fi.getString("UTF-8"));
					else if(fi.getFieldName().equals("status"))
						status = Integer.parseInt(fi.getString("UTF-8"));
					else if(fi.getFieldName().equals("id"))
						id = Integer.parseInt(fi.getString("UTF-8"));
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		subImages = "xxx.jpg";
		MessageResponse<String> messageResponse = new MessageResponse<String>();
		MessageType<String> messageType = updateFileRoute(categoryId, mainImage);
		if(messageType.getStatus() == 1) {
			messageResponse.setStatus(4);
			messageResponse.setMsg("�ƶ��ļ�ʧ��");
		}
		else {
			mainImage = messageType.getData();
			if(id == 0)
				messageResponse = product_service.InsertProduct(categoryId, name, subtitle, mainImage, subImages, detail, price, stock, status, username);
			else
				messageResponse = product_service.UpdateProduct(id, categoryId, name, subtitle, mainImage, subImages, detail, price, stock, status, username);
		}
		String json = gson.toJson(messageResponse);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public void GetDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = "null";
		String detail = "";
		if(request.getParameter("detail") != null)
			detail = request.getParameter("detail");
		if(detail.equals(""))
			result = "null";
		else
			result = detail;
		String json = gson.toJson(result);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public MessageType<String> updateFileRoute(int categoryId, String image) {
		//����ͼƬ����޸�ͼƬ�洢·��
		System.out.println("��ǰͼƬ·����" + image);
		MessageType<String> messageType = new MessageType<String>();
		String path = "D:/�������/�γ����/git/Rising_Shopping/image/";
		List<String> categoryName = new ArrayList<String>();
		categoryName = product_service.getAllCategoryById(categoryId);
		for(int i = categoryName.size() - 1; i >= 0; i--) {
			String temp = categoryName.get(i);
			path += temp + "/";
		}
		try {
			File file =new File(image);
			File fileFolder = new File(path);
			if(!fileFolder.exists())
				fileFolder.mkdir();
			System.out.println("�洢·����" + path + file.getName());
			if(file.renameTo(new File(path + file.getName()))) {
				messageType = new MessageType<String>(0, "�ƶ��ļ��ɹ�", path + file.getName());
			}
			else
				messageType = new MessageType<String>(1, "�ƶ��ļ�ʧ��", null);
			}catch(Exception e) {
				e.printStackTrace();
		}
		System.out.println("�ļ�·���޸ģ�");
		String json = gson.toJson(messageType);
		System.out.println(json);
		return messageType;
	}
}
