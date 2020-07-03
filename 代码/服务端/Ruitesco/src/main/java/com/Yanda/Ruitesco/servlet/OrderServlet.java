package com.Yanda.Ruitesco.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Yanda.Ruitesco.javabean.Page;
import com.Yanda.Ruitesco.service.IOrderService;
import com.Yanda.Ruitesco.service.impl.OrderServiceImpl;
import com.Yanda.Ruitesco.utils.response.MessageType;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayAccount;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IOrderService order_service = new OrderServiceImpl();
	Gson gson=new Gson();
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
		case "create":
			Create(request, response);
			break;
		case "list":
			List(request, response);
			break;
		case "detail":
			Detail(request, response);
			break;
		case "cancel":
			Cancel(request, response);
			break;
		case "pay":
			Pay(request, response);
			break;
		case "alipay_callback":
			CallBack(request);
			break;
		case "query_order_pay_status":
			QueryStatus(request, response);
			break;
		default:
			System.out.println("mode参数异常");
			break;
		}	
	}
	public void Create(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：创建订单
		 * request: shipping_id 收货地址id, payment 实际付款, postage 邮费  (product_id 商品id, quantity 商品数量)  session: username/userid 
		 * 地址栏传参：mode="create"
		 * response: status 状态码(0成功, 1失败, 2其他情况), msg 反馈信息
		 * */
		HttpSession session = request.getSession();
		String username = "";
		int userId = 0;
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
		if(session.getAttribute("userid") != null)
			userId = Integer.parseInt(session.getAttribute("userid").toString());
//		String username = "123456";
		MessageType<Object> messageType;
		if(username.equals("") && userId == 0)
			messageType = new MessageType<Object>(10, "用户未登录", null);
		else {
			BigDecimal payment = BigDecimal.valueOf(0);
			BigDecimal postage = BigDecimal.valueOf(0);
			if(request.getParameter("payment") == null)
				messageType = new MessageType<Object>(2, "未向服务器传递价格参数", null);
			else if(request.getParameter("flag") == null)
				messageType = new MessageType<Object>(2, "未向服务器传递购买方式(购物车, 立即购买)", null);
			else {
				int product_id = 0;
				int quantity = 0;
				int shipping_id = 0;
				int flag = Integer.parseInt(request.getParameter("flag"));
				payment = BigDecimal.valueOf(Double.parseDouble(request.getParameter("payment")));
//				postage = BigDecimal.valueOf(Double.parseDouble(request.getParameter("postage")));
				if(request.getParameter("shipping_id") == null) {
					messageType = new MessageType<Object>(1, "未设置收货地址", null);
				}
				else {
					shipping_id = Integer.parseInt(request.getParameter("shipping_id"));
					String comment = request.getParameter("comment");
					if(comment == null)
						comment = "无";
					if(flag == 0) {
						if(request.getParameter("product_id") != null)
							product_id = Integer.parseInt(request.getParameter("product_id"));
						if(request.getParameter("quantity") != null)
							quantity = Integer.parseInt(request.getParameter("quantity"));
						messageType = order_service.createOrder(product_id, quantity, shipping_id, payment, postage, userId, comment);
					}
					else
						messageType = order_service.createOrder(shipping_id, payment, postage, userId, comment);
				}
			}
		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}

	public void List(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：获取该用户的所有订单
		 * request: pageNum, pageSize  session: username, userid 
		 * 地址栏传参：mode="list"
		 * response: status 状态码(0成功, 1失败, 10用户未登录), msg 反馈信息,  data 订单信息
		 * */
		HttpSession session = request.getSession();
		String username = "";
		int userId = 0;
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
		if(session.getAttribute("userid") != null)
			userId = Integer.parseInt(session.getAttribute("userid").toString());
//		String username = "123456";
		MessageType<Object> messageType;
		if(username.equals("") && userId == 0)
			messageType = new MessageType<Object>(10, "用户未登录", null);
		else {
			Page page = new Page();
			int pageNum = 0, pageSize = 0;
			if(request.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
				page.setPageNum(pageNum);
			}
			if(request.getParameter("pageSize") != null) {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
				page.setPageSize(pageSize);
			}
			messageType = order_service.GetAllOrderByUserId(userId, page);
		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public void Detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：根据订单号获取订单详情
		 * request: order_no session: username/userid 
		 * 地址栏传参：mode="detail"
		 * response: status 状态码(0成功, 1失败, 2其他情况), msg 反馈信息
		 * */
//		HttpSession session = request.getSession();
//		String username = "";
//		int userId = 0;
//		if(session.getAttribute("username") != null)
//			username = session.getAttribute("username").toString();
//		if(session.getAttribute("userid") != null)
//			userId = Integer.parseInt(session.getAttribute("userid").toString());
////		String username = "123456";
		MessageType<Object> messageType;
//		if(username.equals("") && userId == 0)
//			messageType = new MessageType<Object>(10, "用户未登录", null);
//		else {
			int orderNo = 0;
			if(request.getParameter("orderNo") != null)
				orderNo = Integer.parseInt(request.getParameter("orderNo"));
			if(orderNo == 0)
				messageType = new MessageType<Object>(1, "未获取到orderNo", null);
			else {
				messageType = order_service.GetDetailByOrderNo(orderNo);
			}
//		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public void Cancel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：取消订单
		 * request: order_no session: username/userid 
		 * 地址栏传参：mode="cancel"
		 * response: status 状态码(0成功, 1失败, 2其他情况), msg 反馈信息
		 * */
		HttpSession session = request.getSession();
		String username = "";
		int userId = 0;
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
		if(session.getAttribute("userid") != null)
			userId = Integer.parseInt(session.getAttribute("userid").toString());
//		String username = "123456";
		MessageType<Object> messageType;
		if(username.equals("") && userId == 0)
			messageType = new MessageType<Object>(10, "用户未登录", null);
		else {
			int orderNo = 0;
			if(request.getParameter("orderNo") == null)
				messageType = new MessageType<Object>(1, "未获取到orderNo", null);
			else {
				orderNo = Integer.parseInt(request.getParameter("orderNo"));
				messageType = order_service.CancelOrder(orderNo);
			}
		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public void Pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 功能：支付生成付款二维码
		 * request: order_no session: username/userid 
		 * 地址栏传参：mode="pay"
		 * response: status 状态码(0成功, 1失败, 2其他情况), msg 反馈信息
		 * */
		HttpSession session = request.getSession();
		String username = "";
		int userId = 0;
		if(session.getAttribute("username") != null)
			username = session.getAttribute("username").toString();
		if(session.getAttribute("userid") != null)
			userId = Integer.parseInt(session.getAttribute("userid").toString());
//		String username = "123456";
		MessageType<Object> messageType;
		if(username.equals("") && userId == 0)
			messageType = new MessageType<Object>(10, "用户未登录", null);
		else {
			int orderNo = 0;
			if(request.getParameter("orderNo") == null)
				messageType = new MessageType<Object>(1, "未获取到orderNo", null);
			else {
				orderNo = Integer.parseInt(request.getParameter("orderNo"));
				messageType = order_service.PayOrder(orderNo);
			}
		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
	
	public String CallBack(HttpServletRequest request) throws IOException {
		//支付宝回调接口
		System.out.println("====支付宝回调窗口====");
		MessageType<Object> messageType = new MessageType<Object>(10000, "验证签名失败", null);
		Map<String, String[]> params = request.getParameterMap();
		Map<String, String> requestParams = Maps.newHashMap();
		Iterator<String> it = params.keySet().iterator();
		int index = 0;
		while(it.hasNext()) {
			index++;
			String key = it.next();
			String []strArr = params.get(key);
			String value = "";
			for(int i = 0; i < strArr.length; i++) {
				value = (i == strArr.length - 1) ? value + strArr[i] : value + strArr[i] + ",";
			}
			System.out.println("第" + index + "组：");
			System.out.println("key: " + key + " value: " + value);
			requestParams.put(key, value);
		}
		
		//step1: 支付宝验证签名
//		try {
			System.out.println("sign_type:" + requestParams.get("sign_type"));
			System.out.println("alipay_public_key in configs: " + Configs.getAlipayPublicKey());
			System.out.println("sign_type_config: " + Configs.getSignType());
			requestParams.remove("sign_type");
			String alikey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuXuxQSnGNeFwQ4Dtjms8S3veMJfNlJGX8wEkjrYdDegjL80YcGEr+sqwTpNkJ7FjOYLnDRtCsl/R6JOr5Jov8c1TtbQKPdVsUunC3YZ1CFNFfWOChmcP2YRDBtog2IGs+X3elYKB00fN0LA1IkS2Hp8v5bYHc9x3U7uVD5h9iSEYKPOg/hRn6BMQvBn8WsY3myji+z3ofcvE4eVSzrUm95VbDZ3inSrAeMW7TjeWHDwyAt/C5QRXTBTohKr9nIFWUdrmi11NGogwp+dq8020aiv6++uSdsuibZWWlyBzqvxURVbVHeow5dbMzk3I+4/9vx/+1tSTNLpeHdEzlqNzqQIDAQAB";
			System.out.println(alikey.equals(Configs.getAlipayPublicKey()));
//			boolean result = AlipaySignature.rsaCheckV2(requestParams, Configs.getAlipayPublicKey(), "UTF-8", Configs.getSignType());
//			if(!result) {
//				System.out.println("result is false");
//				messageType = new MessageType<Object>(1, "非法请求", null);
//				String json = gson.toJson(messageType);
//				System.out.println(json);
//				return messageType.getMsg();
//			}
//			else {
//				System.out.println("result is true");
				messageType = order_service.Alipay_Callback(requestParams);
//			}
//		} catch (AlipayApiException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String json = gson.toJson(messageType);
		System.out.println(json);
		return messageType.getMsg();
	}
	
	public void QueryStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		int status = order_service.GetStatusByOrderNo(orderNo);
		MessageType<Object> messageType;
		if(status == -1)
			messageType = new MessageType<Object>(1, "不存在该订单", null);
		else
			messageType = new MessageType<Object>(0, null, status);
		String json = gson.toJson(messageType);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.close();
	}
}
