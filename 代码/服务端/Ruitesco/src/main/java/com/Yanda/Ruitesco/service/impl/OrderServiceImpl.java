package com.Yanda.Ruitesco.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Yanda.Ruitesco.dao.IOrderDao;
import com.Yanda.Ruitesco.dao.IProductDao;
import com.Yanda.Ruitesco.dao.IShoppingCartDao;
import com.Yanda.Ruitesco.dao.impl.OrderDaoImpl;
import com.Yanda.Ruitesco.dao.impl.ProductDaoImpl;
import com.Yanda.Ruitesco.dao.impl.ShoppingCartDaoImpl;
import com.Yanda.Ruitesco.javabean.Order;
import com.Yanda.Ruitesco.javabean.OrderDetail;
import com.Yanda.Ruitesco.javabean.Page;
import com.Yanda.Ruitesco.javabean.Product;
import com.Yanda.Ruitesco.javabean.ShoppingCart;
import com.Yanda.Ruitesco.service.IOrderService;
import com.Yanda.Ruitesco.utils.response.MessageType;
import com.Yanda.Ruitesco.utils.response.order.OrderPayType;
import com.Yanda.Ruitesco.utils.response.order.OrderType;
import com.Yanda.Ruitesco.utils.response.order.OrderTypeInPage;
import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.MonitorHeartbeatSynResponse;
import com.alipay.demo.trade.DemoHbRunner;
import com.alipay.demo.trade.Main;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayHeartbeatSynRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.hb.EquipStatus;
import com.alipay.demo.trade.model.hb.ExceptionInfo;
import com.alipay.demo.trade.model.hb.HbStatus;
import com.alipay.demo.trade.model.hb.PosTradeInfo;
import com.alipay.demo.trade.model.hb.SysTradeInfo;
import com.alipay.demo.trade.model.hb.Type;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.alipay.demo.trade.utils.Utils;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.gson.Gson;

public class OrderServiceImpl implements IOrderService {
	IOrderDao order_dao = new OrderDaoImpl();
	IShoppingCartDao cart_dao = new ShoppingCartDaoImpl();
	IProductDao product_dao = new ProductDaoImpl();
	@Override
	public MessageType<Object> createOrder(int shipping_id, BigDecimal payment, BigDecimal postage, int user_id, String comment) {
		// TODO Auto-generated method stub
		//从购物车中结算
		//获取购物车选中的商品 -> 构建订单详情表数组 -> 通过order_no与订单表绑定
		MessageType<Object> messageType;
		int id = order_dao.CreateOrder(user_id, shipping_id, payment, postage, comment);
		if(id <= 0)
			messageType = new MessageType<Object>(3, "创建订单失败", null);
		else {
			List<Order> list = order_dao.GetOrder(id);
			if(list == null || list.get(0).getOrder_no() <= 0)
				messageType = new MessageType<Object>(3, "获取order_no失败", null);
			else {
				int order_no = list.get(0).getOrder_no();						//获取该订单的order_no用于订单详情表绑定
				List<ShoppingCart> cart = cart_dao.getCartList(user_id);		//获取购物车中选中的商品
				List<OrderDetail> checked_product = new ArrayList<OrderDetail>();
				for(int i = 0; i < cart.size(); i++)
					if(cart.get(i).getChecked() == 1) {
						int product_id = cart.get(i).getProduct_id();
						Product temp = (product_dao.GetProductById(product_id).get(0));
						OrderDetail detail = new OrderDetail();
						detail.setOrder_no(order_no);
						detail.setUser_id(user_id);
						detail.setProduct_id(temp.getId());
						detail.setProduct_image(temp.getMainImage());
						detail.setProduct_name(temp.getName());
						detail.setCurrent_unit_price(temp.getPrice());
						detail.setQuantity(cart.get(i).getQuantity());
						detail.setTotal_price(detail.getCurrent_unit_price().multiply(BigDecimal.valueOf((double)detail.getQuantity())));
						checked_product.add(detail);
					}
				int update_number = order_dao.AddOrderDetail(checked_product);
				if(update_number != checked_product.size())
					messageType = new MessageType<Object>(3, "添加订单详情异常", null);
				else {
					for(ShoppingCart product:cart) {
						cart_dao.removeCartProduct(user_id, product.getProduct_id());
					}
					OrderType orderType = new OrderType(list.get(0), checked_product);
					messageType = new MessageType<Object>(0, "创建订单成功", orderType);
				}
			}
		}
		return messageType;
	}

	@Override
	public MessageType<Object> createOrder(int product_id, int quantity, int shipping_id, BigDecimal payment, BigDecimal postage, int user_id, String comment) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		Product product = product_dao.GetProductById(product_id).get(0);
		int id = order_dao.CreateOrder(user_id, shipping_id, payment, postage, comment);
		if(id <= 0)
			messageType = new MessageType<Object>(3, "创建订单失败", null);
		else {
			List<Order> list = order_dao.GetOrder(id);
			if(list == null || list.get(0).getOrder_no() <= 0)
				messageType = new MessageType<Object>(3, "获取order_no失败", null);
			else {
				int order_no = list.get(0).getOrder_no();						//获取该订单的order_no用于订单详情表绑定
				List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
				OrderDetail detail = new OrderDetail();
				detail.setOrder_no(order_no);
				detail.setUser_id(user_id);
				detail.setProduct_id(product.getId());
				detail.setProduct_image(product.getMainImage());
				detail.setProduct_name(product.getName());
				detail.setCurrent_unit_price(product.getPrice());
				detail.setQuantity(quantity);
				detail.setTotal_price(detail.getCurrent_unit_price().multiply(BigDecimal.valueOf((double)detail.getQuantity())));
				orderDetails.add(detail);
				if(order_dao.AddOrderDetail(orderDetails) != 1)
					messageType = new MessageType<Object>(3, "添加订单详情异常", null);
				else {
					OrderType orderType = new OrderType(list.get(0), orderDetails);
					messageType = new MessageType<Object>(0, "创建订单成功", orderType);
				}
			}
		}
		return messageType;
	}

	@Override
	public MessageType<Object> GetAllOrderByUserId(int user_id, Page page) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		List<Order> orders = order_dao.GetAllOrder(user_id);
		if(orders == null)
			messageType = new MessageType<Object>(1, "未获取到该用户的订单", null);
		else {
			List<OrderType> orderTypes = new ArrayList<OrderType>();
			for(Order order:orders) {
				List<OrderDetail> details = order_dao.GetOrderDetailByOrderNo(order.getOrder_no());
				orderTypes.add(new OrderType(order, details));
			}
			int total = orderTypes.size();
			if(total <= 0)
				messageType = new MessageType<Object>(2, "订单异常，订单内不存在商品", null);
			else {
				page.CalculatePage(total);
				OrderTypeInPage result = new OrderTypeInPage(orderTypes, page);
				messageType = new MessageType<Object>(0, "获取订单列表成功", result);
			}
		}
		return messageType;
	}

	@Override
	public MessageType<Object> GetDetailByOrderNo(int order_no) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		List<Order> order = order_dao.GetOrder(order_no);
		List<OrderDetail> orderDetails = order_dao.GetOrderDetailByOrderNo(order_no);
		if(order == null || orderDetails == null)
			messageType = new MessageType<Object>(2, "未获取到订单", null);
		else {
			OrderType orderType = new OrderType(order.get(0), orderDetails);
			messageType = new MessageType<Object>(0, "获取订单详情成功", orderType);
		}
		return messageType;
	}

	@Override
	public MessageType<Object> CancelOrder(int order_no) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		if(order_dao.GetOrderByOrderNo(order_no) == null)
			messageType = new MessageType<Object>(3, "未获取到该订单", null);
		else if(order_dao.GetOrderByOrderNo(order_no).get(0).getStatus() == 2)
			messageType = new MessageType<Object>(4, "该订单已付款，无法取消", null);
		else {
			int result = order_dao.CancelOrder(order_no);
			if(result <= 0)
				messageType = new MessageType<Object>(2, "取消订单失败", null);
			else
				messageType = new MessageType<Object>(0, "取消订单成功", null);
		}
		return messageType;
	}


	@Override
	public int GetStatusByOrderNo(int order_no) {
		// TODO Auto-generated method stub
		List<Order> order = order_dao.GetOrderByOrderNo(order_no);
		if(order == null)
			return -1;
		else
			return order.get(0).getStatus();
	}
	/************************支付相关接口以及方法*********************************/
	@Override
	public MessageType<Object> PayOrder(int order_no) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		Order order;
		List<Order> order_list = order_dao.GetOrder(order_no);
		if(order_list == null) {
			messageType = new MessageType<Object>(1, "未获取到该订单", null);
		}
		else {
			// 测试当面付2.0生成支付二维码
			order = order_list.get(0);
			if(order.getStatus() != 1)
				messageType = new MessageType<Object>(10000, "该订单不是未支付状态", null);
			else
				messageType = test_trade_precreate(order);
		}
		return messageType;
	}

	@Override
	public MessageType<Object> Alipay_Callback(Map<String, String> map) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		int OrderNo = Integer.parseInt(map.get("out_trade_no"));
		String trade_no = map.get("trade_no");
		String trade_status = map.get("trade_status");
		String payment_time = map.get("gmt_payment");
		if(order_dao.GetOrderByOrderNo(OrderNo) == null)
			messageType = new MessageType<Object>(1, "不存在该订单", null);
		else {
			Order order = order_dao.GetOrderByOrderNo(OrderNo).get(0);
			if(order.getStatus() >= 2)
				messageType = new MessageType<Object>(2, "该订单已付款,支付宝重复调用", null);
			else {
				if(trade_status.equals("TRADE_SUCCESS")) {
					//支付成功
					order.setStatus(2);
					order.setPayment_time(Timestamp.valueOf(payment_time));
					if(order_dao.UpdateOrder(order) <= 0)
						messageType = new MessageType<Object>(3, "修改订单信息失败", null);
					else
						messageType = new MessageType<Object>(0, "订单支付成功", null);
				}
				else
					messageType = new MessageType<Object>(4, "订单支付失败", null);
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(messageType);
		System.out.println(json);
		return messageType;
	}
	
	private static Log log = LogFactory.getLog(Main.class);

    // 支付宝当面付2.0服务
    private static AlipayTradeService tradeService;

    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
    private static AlipayTradeService tradeWithHBService;

    // 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
    private static AlipayMonitorService monitorService;

    static {
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

        /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
            .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
            .setFormat("json").build();
    }

    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                    response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }

    // 测试系统商交易保障调度
    public void test_monitor_schedule_logic() {
        // 启动交易保障线程
        DemoHbRunner demoRunner = new DemoHbRunner(monitorService);
        demoRunner.setDelay(5); // 设置启动后延迟5秒开始调度，不设置则默认3秒
        demoRunner.setDuration(10); // 设置间隔10秒进行调度，不设置则默认15 * 60秒
        demoRunner.schedule();

        // 启动当面付，此处每隔5秒调用一次支付接口，并且当随机数为0时交易保障线程退出
        while (Math.random() != 0) {
            test_trade_pay(tradeWithHBService);
            Utils.sleep(5 * 1000);
        }

        // 满足退出条件后可以调用shutdown优雅安全退出
        demoRunner.shutdown();
    }

    // 系统商的调用样例，填写了所有系统商商需要填写的字段
    public void test_monitor_sys() {
        // 系统商使用的交易信息格式，json字符串类型
        List<SysTradeInfo> sysTradeInfoList = new ArrayList<SysTradeInfo>();
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000001", 5.2, HbStatus.S));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000002", 4.4, HbStatus.F));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000003", 11.3, HbStatus.P));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000004", 3.2, HbStatus.X));
        sysTradeInfoList.add(SysTradeInfo.newInstance("00000005", 4.1, HbStatus.X));

        // 填写异常信息，如果有的话
        List<ExceptionInfo> exceptionInfoList = new ArrayList<ExceptionInfo>();
        exceptionInfoList.add(ExceptionInfo.HE_SCANER);
        //        exceptionInfoList.add(ExceptionInfo.HE_PRINTER);
        //        exceptionInfoList.add(ExceptionInfo.HE_OTHER);

        // 填写扩展参数，如果有的话
        Map<String, Object> extendInfo = new HashMap<String, Object>();
        //        extendInfo.put("SHOP_ID", "BJ_ZZ_001");
        //        extendInfo.put("TERMINAL_ID", "1234");

        String appAuthToken = "应用授权令牌";//根据真实值填写

        AlipayHeartbeatSynRequestBuilder builder = new AlipayHeartbeatSynRequestBuilder()
            .setAppAuthToken(appAuthToken).setProduct(com.alipay.demo.trade.model.hb.Product.FP).setType(Type.CR)
            .setEquipmentId("cr1000001").setEquipmentStatus(EquipStatus.NORMAL)
            .setTime(Utils.toDate(new Date())).setStoreId("store10001").setMac("0a:00:27:00:00:00")
            .setNetworkType("LAN").setProviderId("2088911212323549") // 设置系统商pid
            .setSysTradeInfoList(sysTradeInfoList) // 系统商同步trade_info信息
            //                .setExceptionInfoList(exceptionInfoList)  // 填写异常信息，如果有的话
            .setExtendInfo(extendInfo) // 填写扩展信息，如果有的话
        ;

        MonitorHeartbeatSynResponse response = monitorService.heartbeatSyn(builder);
        dumpResponse(response);
    }

    // POS厂商的调用样例，填写了所有pos厂商需要填写的字段
    public void test_monitor_pos() {
        // POS厂商使用的交易信息格式，字符串类型
        List<PosTradeInfo> posTradeInfoList = new ArrayList<PosTradeInfo>();
        posTradeInfoList.add(PosTradeInfo.newInstance(HbStatus.S, "1324", 7));
        posTradeInfoList.add(PosTradeInfo.newInstance(HbStatus.X, "1326", 15));
        posTradeInfoList.add(PosTradeInfo.newInstance(HbStatus.S, "1401", 8));
        posTradeInfoList.add(PosTradeInfo.newInstance(HbStatus.F, "1405", 3));

        // 填写异常信息，如果有的话
        List<ExceptionInfo> exceptionInfoList = new ArrayList<ExceptionInfo>();
        exceptionInfoList.add(ExceptionInfo.HE_PRINTER);

        // 填写扩展参数，如果有的话
        Map<String, Object> extendInfo = new HashMap<String, Object>();
        //        extendInfo.put("SHOP_ID", "BJ_ZZ_001");
        //        extendInfo.put("TERMINAL_ID", "1234");

        AlipayHeartbeatSynRequestBuilder builder = new AlipayHeartbeatSynRequestBuilder()
            .setProduct(com.alipay.demo.trade.model.hb.Product.FP)
            .setType(Type.SOFT_POS)
            .setEquipmentId("soft100001")
            .setEquipmentStatus(EquipStatus.NORMAL)
            .setTime("2015-09-28 11:14:49")
            .setManufacturerPid("2088000000000009")
            // 填写机具商的支付宝pid
            .setStoreId("store200001").setEquipmentPosition("31.2433190000,121.5090750000")
            .setBbsPosition("2869719733-065|2896507033-091").setNetworkStatus("gggbbbgggnnn")
            .setNetworkType("3G").setBattery("98").setWifiMac("0a:00:27:00:00:00")
            .setWifiName("test_wifi_name").setIp("192.168.1.188")
            .setPosTradeInfoList(posTradeInfoList) // POS厂商同步trade_info信息
            //                .setExceptionInfoList(exceptionInfoList) // 填写异常信息，如果有的话
            .setExtendInfo(extendInfo) // 填写扩展信息，如果有的话
        ;

        MonitorHeartbeatSynResponse response = monitorService.heartbeatSyn(builder);
        dumpResponse(response);
    }

    // 测试当面付2.0支付
    public void test_trade_pay(AlipayTradeService service) {
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = "tradepay" + System.currentTimeMillis()
                            + (long) (Math.random() * 10000000L);

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店消费”
        String subject = "xxx品牌xxx门店当面付消费";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = "0.01";

        // (必填) 付款条码，用户支付宝钱包手机app点击“付款”产生的付款条码
        String authCode = "用户自己的支付宝付款码"; // 条码示例，286648048691290423
        // (可选，根据需要决定是否使用) 订单可打折金额，可以配合商家平台配置折扣活动，如果订单部分商品参与打折，可以将部分商品总价填写至此字段，默认全部商品可打折
        // 如果该值未传入,但传入了【订单总金额】,【不可打折金额】 则该值默认为【订单总金额】- 【不可打折金额】
        //        String discountableAmount = "1.00"; //

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0.0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品3件共20.00元"
        String body = "购买商品3件共20.00元";

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        String providerId = "2088100200300400500";
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId(providerId);

        // 支付超时，线下扫码交易定义为5分钟
        String timeoutExpress = "5m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx面包", 1000, 1);
        // 创建好一个商品后添加至商品明细列表
        goodsDetailList.add(goods1);

        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        goodsDetailList.add(goods2);

        String appAuthToken = "应用授权令牌";//根据真实值填写

        // 创建条码支付请求builder，设置请求参数
        AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
            //            .setAppAuthToken(appAuthToken)
            .setOutTradeNo(outTradeNo).setSubject(subject).setAuthCode(authCode)
            .setTotalAmount(totalAmount).setStoreId(storeId)
            .setUndiscountableAmount(undiscountableAmount).setBody(body).setOperatorId(operatorId)
            .setExtendParams(extendParams).setSellerId(sellerId)
            .setGoodsDetailList(goodsDetailList).setTimeoutExpress(timeoutExpress);

        // 调用tradePay方法获取当面付应答
        AlipayF2FPayResult result = service.tradePay(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝支付成功: )");
                break;

            case FAILED:
                log.error("支付宝支付失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，订单状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
    }

    // 测试当面付2.0查询订单
    public void test_trade_query() {
        // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
        String outTradeNo = "tradepay14817938139942440181";

        // 创建查询请求builder，设置请求参数
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
            .setOutTradeNo(outTradeNo);

        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("查询返回该订单支付成功: )");

                AlipayTradeQueryResponse response = result.getResponse();
                dumpResponse(response);

                log.info(response.getTradeStatus());
                if (Utils.isListNotEmpty(response.getFundBillList())) {
                    for (TradeFundBill bill : response.getFundBillList()) {
                        log.info(bill.getFundChannel() + ":" + bill.getAmount());
                    }
                }
                break;

            case FAILED:
                log.error("查询返回该订单支付失败或被关闭!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，订单支付状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
    }

    // 测试当面付2.0退款
    public void test_trade_refund() {
        // (必填) 外部订单号，需要退款交易的商户外部订单号
        String outTradeNo = "tradepay14817938139942440181";

        // (必填) 退款金额，该金额必须小于等于订单的支付金额，单位为元
        String refundAmount = "0.01";

        // (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
        // 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
        String outRequestNo = "";

        // (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
        String refundReason = "正常退款，用户买多了";

        // (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
        String storeId = "test_store_id";

        // 创建退款请求builder，设置请求参数
        AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
            .setOutTradeNo(outTradeNo).setRefundAmount(refundAmount).setRefundReason(refundReason)
            .setOutRequestNo(outRequestNo).setStoreId(storeId);

        AlipayF2FRefundResult result = tradeService.tradeRefund(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝退款成功: )");
                break;

            case FAILED:
                log.error("支付宝退款失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，订单退款状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
    }

    // 测试当面付2.0生成支付二维码
    public MessageType<Object> test_trade_precreate(Order order) {
    	//内网穿透URL
    	String url = "http://a46bgd.natappfree.cc/";
    	MessageType<Object> messageType;
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
//        String outTradeNo = "tradeprecreate" + System.currentTimeMillis()
//                            + (long) (Math.random() * 10000000L);
        List<OrderDetail> details = order_dao.GetOrderDetailByOrderNo(order.getOrder_no());
    	
        String outTradeNo = String.valueOf(order.getOrder_no());

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
//        String subject = "xxx品牌xxx门店当面付扫码消费";
        String subject = "订单" + outTradeNo + " 的扫码支付";
        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
//        String totalAmount = "0.01";
        String totalAmount = String.valueOf(order.getPayment().doubleValue());
        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
//        String body = "购买商品3件共20.00元";
        int number = 0;
        if(details != null)
        	number = details.size();
        String body = "购买" + number + "类商品共计" + order.getPayment().doubleValue() + "元";
        
        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        
        if(details != null) {
	        for(OrderDetail detail:details) {
	        	GoodsDetail goods = GoodsDetail.newInstance(String.valueOf(detail.getProduct_id()), detail.getProduct_name(), detail.getCurrent_unit_price().longValue(), detail.getQuantity());
	        	goodsDetailList.add(goods);
        	}
        }
//        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
//        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);
//        // 创建好一个商品后添加至商品明细列表
//        goodsDetailList.add(goods1);
//
//        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
//        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
//        goodsDetailList.add(goods2);

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
            .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
            .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
            .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
            .setTimeoutExpress(timeoutExpress)
            .setNotifyUrl(url + "Ruitesco/order?mode=alipay_callback")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
            .setGoodsDetailList(goodsDetailList);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                // 需要修改为运行机器上的路径
                String localpath = "D:/软件工程/课程设计/git/Rising_Shopping/image/";
                String filePath = String.format(localpath + "user/alipay/qr-%s.png",
                    response.getOutTradeNo());
                log.info("filePath:" + filePath);
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                OrderPayType orderPayType = new OrderPayType(order.getOrder_no(), "user/alipay/qr-" + response.getOutTradeNo() + ".png");
                messageType = new MessageType<Object>(0, "预下单成功，已生成付款二维码", orderPayType);
                return messageType;
//                break;

            case FAILED:
                log.error("支付宝预下单失败!!!");
                messageType = new MessageType<Object>(1, "支付宝预下单失败", null);
                return messageType;
//                break;

            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                messageType = new MessageType<Object>(1, "支付宝系统异常", null);
                return messageType;
//                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                messageType = new MessageType<Object>(1, "不支持交易状态", null);
                return messageType;
//                break;
        }
    }


}
