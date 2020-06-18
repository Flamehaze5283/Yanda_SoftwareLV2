package com.Yanda.Ruitesco.dataResp.type;

import java.sql.Timestamp;

public class DataLoginT {
	private int id;
	private String username;
	private String email;
	private String phone;
	private Timestamp createTime;
	private Timestamp updateTime; 
	
	public DataLoginT(int id,String username,String email,String phone,Timestamp createTime,Timestamp updateTime)
	{
		this.id=id;
		this.username=username;
		this.email=email;
		this.phone=phone;
		this.createTime=createTime;
		this.updateTime=updateTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
