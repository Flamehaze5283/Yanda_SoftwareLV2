package com.Yanda.Ruitesco.utils.response.responsetype;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductDetail {
	int id;
	int categoryId;
	int parentCategoryId;
	String name;
	String subtitle;
	String imageHost = "http://img.business.com/";
	String mainImage;
	List<String> subImages;
	String detail;
	BigDecimal price;
	int stock;
	int status;
	Timestamp createTime;
	Timestamp updateTime;
	
	public ProductDetail() {
		// TODO Auto-generated constructor stub
		this.subImages = new ArrayList<String>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
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
	public String getImageHost() {
		return imageHost;
	}
	public void setImageHost(String imageHost) {
		this.imageHost = imageHost;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public List<String> getSubImages() {
		return subImages;
	}

	public void setSubImages(List<String> subImages) {
		this.subImages = subImages;
	}
	public void setSubImages(String subImages) {
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < subImages.length(); i++) {
			String temp = "";
			for(int j = i; j < subImages.length(); j++) {
				if(subImages.charAt(j) != ',' && j + 1 < subImages.length()) {
					temp += subImages.charAt(j);
				}
				else {
					if(subImages.charAt(j) == ',') {
						result.add(temp);
						i = j;
						break;
					}
					else {
						temp += subImages.charAt(j);
						result.add(temp);
						i = j;
					}
				}
			}
		}
		this.subImages = result;
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
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
