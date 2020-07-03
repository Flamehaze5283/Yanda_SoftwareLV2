package com.Yanda.Ruitesco.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Yanda.Ruitesco.dataResp.DataResp;
import com.Yanda.Ruitesco.dataResp.type.OrderData;
import com.Yanda.Ruitesco.dataResp.type.OrderDataListT;
import com.Yanda.Ruitesco.javabean.PageUserList;
import com.Yanda.Ruitesco.service.IManagerOrderService;
import com.Yanda.Ruitesco.service.impl.ManagerOrderServiceImpl;
import com.Yanda.Ruitesco.utils.response.MessageType;
import com.google.gson.Gson;

/**
 * Servlet implementation class ManagerOrderServlert
 */
@WebServlet("/manage/order")
public class ManagerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IManagerOrderService manager_order_service=new ManagerOrderServiceImpl();
	Gson gson=new Gson();
	String json;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerOrderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		case "list":
			List(req,resp);
			break;
		case "search":
			Search(req,resp);
			break;
		case "detail":
			Detail(req,resp);
			break;
		case "send_goods":
			SendGoods(req,resp);
			break;
		default:
			System.out.println("mode�����쳣");
			break;
		}
	}
	
	public void List(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{

		String pagesize=req.getParameter("pageSize");
		String pageNum=req.getParameter("pageNum");
		PageUserList pageUserList=new PageUserList();
		pageUserList.setPageSize(Integer.parseInt(pagesize));
		pageUserList.setPageNum(Integer.parseInt(pageNum));
		PrintWriter pw= resp.getWriter();
		
		if(req.getSession(false)==null)
		{
			System.out.println("δ��¼");
			DataResp<Object> dataResp =new DataResp<Object>(10,"�û�δ��¼",null);
			json = gson.toJson(dataResp);
		}
		else if(!(req.getSession(false).getAttribute("role").toString().equals("����Ա")))
		{
			DataResp<Object> dataResp =new DataResp<Object>(11,"�û�û��Ȩ��",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			PageUserList ppage=manager_order_service.GetNextPage(pageUserList);
			List<OrderData> datal =manager_order_service.GetOrderList(ppage.getStartRow(), ppage.getEndRow());
			OrderDataListT orderdatal=new OrderDataListT(ppage,datal);
			DataResp<OrderDataListT> dataResp =new DataResp<OrderDataListT>(0,null,orderdatal);
			json = gson.toJson(dataResp);
		}
		System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	public void Search(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{

		String userName=req.getParameter("userName");
		String pageSize=req.getParameter("pageSize");
		String pageNum=req.getParameter("pageNum");
		PageUserList pageUserList=new PageUserList();
		pageUserList.setPageSize(Integer.parseInt(pageSize));
		pageUserList.setPageNum(Integer.parseInt(pageNum));
		PrintWriter pw= resp.getWriter();
		
		if(req.getSession(false)==null)
		{
			System.out.println("δ��¼");
			DataResp<Object> dataResp =new DataResp<Object>(10,"�û�δ��¼",null);
			json = gson.toJson(dataResp);
		}
		else if(!(req.getSession(false).getAttribute("role").toString().equals("����Ա")))
		{
			DataResp<Object> dataResp =new DataResp<Object>(11,"�û�û��Ȩ��",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			PageUserList ppage=manager_order_service.GetNextPage(pageUserList);
			List<OrderData> datal =manager_order_service.GetOrderListByUserName(userName,ppage.getStartRow(), ppage.getEndRow());
			if(datal==null)
			{
				DataResp<String> dataResp=new DataResp<String>(1,"û���ҵ�����",null);
				json = gson.toJson(dataResp);
			}
			else
			{
				OrderDataListT orderdatal=new OrderDataListT(ppage,datal);
				DataResp<OrderDataListT> dataResp =new DataResp<OrderDataListT>(0,null,orderdatal);
				json = gson.toJson(dataResp);
			}
		}
		System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	public void Detail(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{

		String orderno=req.getParameter("orderNo");
		int orderNo = Integer.parseInt(orderno);
		PrintWriter pw= resp.getWriter();
		
		if(req.getSession(false)==null)
		{
			System.out.println("δ��¼");
			DataResp<Object> dataResp =new DataResp<Object>(10,"�û�δ��¼",null);
			json = gson.toJson(dataResp);
		}
		else if(!(req.getSession(false).getAttribute("role").toString().equals("����Ա")))
		{
			DataResp<Object> dataResp =new DataResp<Object>(11,"�û�û��Ȩ��",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			OrderData data =manager_order_service.GetOrder(orderNo);
			List<OrderData> data_list = new ArrayList<OrderData>();
			data_list.add(data);
			MessageType<Object> messageType = new MessageType<Object>(0, null, data_list);
			json = gson.toJson(messageType);
		}
		System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	public void SendGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{

		String orderno=req.getParameter("orderNo");
		int orderNo = Integer.parseInt(orderno);
		PrintWriter pw= resp.getWriter();
		
		if(req.getSession(false)==null)
		{
			System.out.println("δ��¼");
			DataResp<Object> dataResp =new DataResp<Object>(10,"�û�δ��¼",null);
			json = gson.toJson(dataResp);
		}
		else if(!(req.getSession(false).getAttribute("role").toString().equals("����Ա")))
		{
			DataResp<Object> dataResp =new DataResp<Object>(11,"�û�û��Ȩ��",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			DataResp<String> dataResp=null;
			if(manager_order_service.SendGoods(orderNo)==true)
				dataResp=new DataResp<String>(0,null,"�����ɹ�");
			else
				dataResp =new DataResp<String>(1,null,"����ʧ��");
			json = gson.toJson(dataResp);
		}
		System.out.println(json);
		pw.write(json);
		pw.close();
	}

}
