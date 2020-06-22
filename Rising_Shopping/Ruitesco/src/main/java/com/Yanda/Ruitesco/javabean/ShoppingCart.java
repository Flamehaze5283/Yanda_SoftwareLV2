package com.Yanda.Ruitesco.javabean;

import java.sql.Timestamp;

public class ShoppingCart {
	int id;					//购物车id
	int user_id;			//用户id(为用户id添加索引)
	int product_id;			//商品id
	int quantity;			//该商品购买数量
	boolean checked;		//该商品在购物车中是否选中
	Timestamp create_time;	//该条目创建时间(需要在创建时自主添加)
	Timestamp update_time;	//该条目更新时间(由数据库自动更新)
}
