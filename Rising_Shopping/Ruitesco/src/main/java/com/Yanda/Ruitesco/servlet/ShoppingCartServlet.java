package com.Yanda.Ruitesco.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Yanda.Ruitesco.service.ICategoryService;
import com.Yanda.Ruitesco.service.IShoppingCartService;
import com.Yanda.Ruitesco.service.impl.CategoryServiceImpl;
import com.Yanda.Ruitesco.service.impl.ShoppingCartServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //初始化ShoppingCartService
	IShoppingCartService cart_service = new ShoppingCartServiceImpl();
	//创建Gson对象，转化json
	Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		case "":
			
			break;
		case "":
			
			break;
		case "":
			
			break;
		case "":

			break;
		default:
			break;
		}
	}

}
