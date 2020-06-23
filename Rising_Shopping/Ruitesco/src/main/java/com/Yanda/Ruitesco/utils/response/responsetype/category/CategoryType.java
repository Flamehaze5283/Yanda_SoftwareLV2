package com.Yanda.Ruitesco.utils.response.responsetype.category;

import java.sql.Timestamp;

public class CategoryType {
	//在每个类别中添加该类是否含有子类属性
	int id;
	int parent_id;
	String name;
	Timestamp create_time;
	Timestamp update_time;
	boolean isFather;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public boolean isFather() {
		return isFather;
	}
	public void setFather(boolean isFather) {
		this.isFather = isFather;
	}
}
