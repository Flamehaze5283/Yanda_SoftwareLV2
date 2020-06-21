package com.Yanda.Ruitesco.javabean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {
	int id;//商品id
	int category_id;//商品类别id
	String name;//商品名称
	String subtitle;//商品附加标题
	String main_image;//主图路径
	String sub_image;//副图路径
	String detail;//商品描述
	BigDecimal price;//商品价格
	int stock;//库存
	int status;//商品状态
	Timestamp create_time;//生成商品时间
	Timestamp update_time;//商品变更时间
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int id, int category_id, String name, String subtitle, String mainImage, String subImage,
			String detail, BigDecimal price, int stock, int status) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.name = name;
		this.subtitle = subtitle;
		this.main_image = mainImage;
		this.sub_image = subImage;
		this.detail = detail;
		this.price = price;
		this.stock = stock;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getMainImage() {
		return main_image;
	}

	public void setMainImage(String mainImage) {
		this.main_image = mainImage;
	}

	public String getSubImage() {
		return sub_image;
	}

	public void setSubImage(String subImage) {
		this.sub_image = subImage;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
}
