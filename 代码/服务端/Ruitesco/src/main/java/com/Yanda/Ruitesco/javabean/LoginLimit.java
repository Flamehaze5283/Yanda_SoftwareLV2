package com.Yanda.Ruitesco.javabean;

import java.sql.Timestamp;

public class LoginLimit {
	private int id;
	private String username;
	private String ip;
	private int status;
	private Timestamp create_time;
	private int level;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "LoginLimit [id=" + id + ", username=" + username + ", ip=" + ip + ", status=" + status
				+ ", create_time=" + create_time + ", level=" + level + "]";
	}
	
	

}
