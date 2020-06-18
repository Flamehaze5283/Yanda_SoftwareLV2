package com.Yanda.Ruitesco.servlet;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.google.gson.Gson;

@WebServlet("/test")
public class TestServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;		//清除警告
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		req.getRequestDispatcher("WEB-INF/classes/jsp/test.jsp").forward(req, resp);
	}
}
///* 允许跨域的主机地址 */
//resp.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8848");
///* 允许跨域的请求方法GET, POST, HEAD 等 */
//resp.setHeader("Access-Control-Allow-Methods","*");
///* 重新预检验跨域的缓存时间 (s) */
//resp.setHeader("Access-Control-Max-Age","3600");
///* 允许跨域的请求头 */
//resp.setHeader("Access-Control-Allow-Headers","*");
///* 是否携带cookie */
//resp.setHeader("Access-Control-Allow-Credentials", "true");
//String test_servlet = "success";
//Gson gson = new Gson();
//String json = gson.toJson(test_servlet);
//System.out.println(json);
//PrintWriter pw = resp.getWriter();
//pw.write(json);
//pw.close();
