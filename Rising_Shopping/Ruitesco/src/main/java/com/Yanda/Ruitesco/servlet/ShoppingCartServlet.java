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
import com.Yanda.Ruitesco.dataResp.type.CartAddDataT;
import com.Yanda.Ruitesco.dataResp.type.CartData;
import com.Yanda.Ruitesco.dataResp.type.CartRemoveDataT;
import com.Yanda.Ruitesco.dataResp.type.CartSelectDataT;
import com.Yanda.Ruitesco.dataResp.type.CartUpdateDataT;
import com.Yanda.Ruitesco.javabean.ShoppingCart;
import com.Yanda.Ruitesco.service.IShoppingCartService;
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
	String json=null;
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
		case "list":
			GetList(request,response);
			break;
		case "add":
			AddProduct(request,response);
			break;
		case "update":
			Update(request,response);
			break;
		case "delete_product":
			RemoveProduct(request,response);
			break;
		case "select":
			SelectProduct(request,response);
			break;
		case "un_select":
			UnSelectProduct(request,response);
			break;
		case "select_all":
			SelectAll(request,response);
			break;
		case "un_select_all":
			UnSelectAll(request,response);
			break;
		case "get_cart_product_count":
			GetProductCount(request,response);
		default:
			break;
		}
	}
	public void GetList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw= resp.getWriter();
		if(req.getSession(false)==null)
		{
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录,请登录",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			List<CartData> datal=new ArrayList<CartData>();
			int user_id=(int)(req.getSession(false).getAttribute("userid"));
			List<ShoppingCart> cartl = cart_service.GetCartList(user_id); 
			for(ShoppingCart x : cartl)
			{
				CartData cartData=new CartData(x);
				datal.add(cartData);
			}
			//CartDataListT cartdatal=new CartDataListT(datal);
			//DataResp<CartDataListT> dataResp =new DataResp<CartDataListT>(0,null,cartdatal);
			DataResp<List<CartData>> dataResp =new DataResp<List<CartData>>(0,null,datal);
			json = gson.toJson(dataResp);
		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	public void AddProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw= resp.getWriter();
		if(req.getSession(false)==null)
		{
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录,请登录",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			int user_id=(int)(req.getSession(false).getAttribute("userid"));
			int product_id=Integer.parseInt(req.getParameter("productId"));
			int count=Integer.parseInt(req.getParameter("count"));
			int status=cart_service.Add(user_id, product_id, count);
			if(status==0){
				CartAddDataT data=new CartAddDataT(product_id,count);
				DataResp<CartAddDataT> dataResp =new DataResp<CartAddDataT>(0,null,data);
				json = gson.toJson(dataResp);
			}
			else if(status==1){
				DataResp<Object> dataResp =new DataResp<Object>(status,"添加失败",null);
				json = gson.toJson(dataResp);
			}
			else if(status==999){
				DataResp<Object> dataResp =new DataResp<Object>(status,"商品数量不能小于1",null);
				json = gson.toJson(dataResp);
			}
		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	public void Update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw= resp.getWriter();
		if(req.getSession(false)==null)
		{
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录,请登录",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			int user_id=(int)(req.getSession(false).getAttribute("userid"));
			int product_id=Integer.parseInt(req.getParameter("productId"));
			int count=Integer.parseInt(req.getParameter("count"));
			int status=cart_service.Update(user_id, product_id, count);
			if(status==0){
				CartUpdateDataT data=new CartUpdateDataT(product_id,count);
				DataResp<CartUpdateDataT> dataResp =new DataResp<CartUpdateDataT>(0,null,data);
				json = gson.toJson(dataResp);
			}
			else if(status==1){
				DataResp<Object> dataResp =new DataResp<Object>(status,"修改失败",null);
				json = gson.toJson(dataResp);
			}
			else if(status==999){
				DataResp<Object> dataResp =new DataResp<Object>(status,"商品数量不能小于1",null);
				json = gson.toJson(dataResp);
			}
		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	
	public void RemoveProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw= resp.getWriter();
		if(req.getSession(false)==null)
		{
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录,请登录",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			int user_id=(int)(req.getSession(false).getAttribute("userid"));
			int product_id=Integer.parseInt(req.getParameter("productId"));
			if(cart_service.Remove(user_id, product_id)==true)
			{
				CartRemoveDataT data=new CartRemoveDataT(product_id);
				DataResp<CartRemoveDataT> dataResp =new DataResp<CartRemoveDataT>(0,null,data);
				json = gson.toJson(dataResp);
			}else
			{
				DataResp<Object> dataResp =new DataResp<Object>(1,"删除失败",null);
				json = gson.toJson(dataResp);
			}
		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	public void SelectProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw= resp.getWriter();
		if(req.getSession(false)==null)
		{
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录,请登录",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			int user_id=(int)(req.getSession(false).getAttribute("userid"));
			int product_id=Integer.parseInt(req.getParameter("productId"));
			if(cart_service.Select(user_id, product_id))
			{
				CartSelectDataT data=new CartSelectDataT(product_id);
				DataResp<CartSelectDataT> dataResp =new DataResp<CartSelectDataT>(0,null,data);
				json = gson.toJson(dataResp);
			}else
			{
				DataResp<Object> dataResp =new DataResp<Object>(1,"选中失败",null);
				json = gson.toJson(dataResp);
			}
		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	public void UnSelectProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw= resp.getWriter();
		if(req.getSession(false)==null)
		{
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录,请登录",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			int user_id=(int)(req.getSession(false).getAttribute("userid"));
			int product_id=Integer.parseInt(req.getParameter("productId"));
			if(cart_service.UnSelect(user_id, product_id))
			{
				CartSelectDataT data=new CartSelectDataT(product_id);
				DataResp<CartSelectDataT> dataResp =new DataResp<CartSelectDataT>(0,null,data);
				json = gson.toJson(dataResp);
			}else
			{
				DataResp<Object> dataResp =new DataResp<Object>(1,"选中失败",null);
				json = gson.toJson(dataResp);
			}
		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	public void SelectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw= resp.getWriter();
		if(req.getSession(false)==null)
		{
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录,请登录",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			int user_id=(int)(req.getSession(false).getAttribute("userid"));
			int status=cart_service.SelectAll(user_id);
			if(status==0){
				DataResp<Object> dataResp =new DataResp<Object>(status,"全选成功",null);
				json = gson.toJson(dataResp);
			}
			else if(status==1){
				DataResp<Object> dataResp =new DataResp<Object>(status,"仅选中部分，未全部选中",null);
				json = gson.toJson(dataResp);
			}
			else if(status==2) {
				DataResp<Object> dataResp =new DataResp<Object>(status,"发生了未知错误",null);
				json = gson.toJson(dataResp);
			}
		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	public void UnSelectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw= resp.getWriter();
		if(req.getSession(false)==null)
		{
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录,请登录",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			int user_id=(int)(req.getSession(false).getAttribute("userid"));
			int status=cart_service.UnSelectAll(user_id);
			if(status==0){
				DataResp<Object> dataResp =new DataResp<Object>(status,"全选成功",null);
				json = gson.toJson(dataResp);
			}
			else if(status==1){
				DataResp<Object> dataResp =new DataResp<Object>(status,"仅选中部分，未全部选中",null);
				json = gson.toJson(dataResp);
			}
			else if(status==2) {
				DataResp<Object> dataResp =new DataResp<Object>(status,"发生了未知错误",null);
				json = gson.toJson(dataResp);
			}
		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}
	public void GetProductCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter pw= resp.getWriter();
		if(req.getSession(false)==null)
		{
			DataResp<Object> dataResp =new DataResp<Object>(10,"用户未登录,请登录",null);
			json = gson.toJson(dataResp);
		}
		else
		{
			int user_id=(int)(req.getSession(false).getAttribute("userid"));
			int productcount=cart_service.GetProductCount(user_id);
			if(productcount>-1){
				DataResp<Integer> dataResp =new DataResp<Integer>(0,null,productcount);
				json = gson.toJson(dataResp);
			}
			else {
				DataResp<Object> dataResp =new DataResp<Object>(1,"数据异常",null);
				json = gson.toJson(dataResp);
			}

		}
		//System.out.println(json);
		pw.write(json);
		pw.close();
	}

}
