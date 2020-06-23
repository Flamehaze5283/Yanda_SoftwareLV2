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
import javax.servlet.http.HttpSession;

import com.Yanda.Ruitesco.dataResp.DataResp;
import com.Yanda.Ruitesco.dataResp.type.*;
import com.Yanda.Ruitesco.javabean.User;
import com.Yanda.Ruitesco.javabean.PageUserList;
import com.Yanda.Ruitesco.service.IMangerService;
import com.Yanda.Ruitesco.service.impl.ManagerServicelmpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/manage/user")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IMangerService manager_service=new ManagerServicelmpl();
	Gson gson=new Gson();
	String json;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
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
		/* 允许跨域的主机地址 */
		resp.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8848");
		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		resp.setHeader("Access-Control-Allow-Methods","*");
		/* 重新预检验跨域的缓存时间 (s) */
		resp.setHeader("Access-Control-Max-Age","3600");
		/* 允许跨域的请求头 */
		resp.setHeader("Access-Control-Allow-Headers","*");
		/* 是否携带cookie */
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		String mode = req.getParameter("mode");
		switch(mode){
		case "login":
			Login(req,resp);
			break;
		case "list":
			GetUserList(req,resp);
			break;
		default:
			System.out.println("mode参数异常");
			break;
		}
	}
	

	public void Login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		/**
		 * 功能：检查用户名有效
		 * request: username 用户名, password 密码
		 * 地址栏传参：mode="login"
		 * response: status 状态码(0成功, 1失败, 2其他情况), msg 反馈信息
		 * */
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		User user = manager_service.Login(username, password);
		PrintWriter pw= resp.getWriter();
		if(user!=null)
		{
				HttpSession session=req.getSession(true);
				session.setAttribute("username", user.getUsername());
				session.setAttribute("role", user.getRole());
				
				DataManagerLoginT data = new DataManagerLoginT(user.getId(),user.getUsername(),user.getEmail(),user.getPhone(),user.getRole()
							,user.getCreate_time(),user.getUpdate_time());
				DataResp<DataManagerLoginT> dataResp = new  DataResp<DataManagerLoginT>(0,null,data);
				json = gson.toJson(dataResp);
		}
		else 
		{
			DataResp<Object> dataResp =new DataResp<Object>(1,"密码错误",null);
			json = gson.toJson(dataResp);

		}
		System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	
	public void GetUserList(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		/**
		 * 功能：检查用户名有效
		 * request: username 用户名, password 密码
		 * 地址栏传参：mode="login"
		 * response: status 状态码(0成功, 1失败, 2其他情况), msg 反馈信息
		 * */
		String pagesize=req.getParameter("pageSize");
		String pageNum=req.getParameter("pageNum");
		PageUserList pageUserList=new PageUserList();
		pageUserList.setPageSize(Integer.parseInt(pagesize));
		pageUserList.setPageNum(Integer.parseInt(pageNum));
		PrintWriter pw= resp.getWriter();
		
		if(req.getSession(false)==null)
		{
			System.out.println("未登录");
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录",null);
			json = gson.toJson(dataResp);
		}
		else if(!(req.getSession(false).getAttribute("role").toString().equals("管理员")))
		{
			DataResp<Object> dataResp =new DataResp<Object>(1,"用户没有权限",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			PageUserList ppage=manager_service.GetNextPage(pageUserList);
			List<User> userl =manager_service.GetUserList(ppage.getStartRow(), ppage.getEndRow());
			List<UserData> datal = new ArrayList<UserData>();
			for(User user : userl)
			{
				UserData userData=new UserData(user);
				datal.add(userData);
			}
			UserDataListT userdatal=new UserDataListT(ppage,datal);
			DataResp<UserDataListT> dataResp =new DataResp<UserDataListT>(0,null,userdatal);
			json = gson.toJson(dataResp);
		}
		System.out.println(json);
		pw.write(json);
		pw.close();
	}

}
