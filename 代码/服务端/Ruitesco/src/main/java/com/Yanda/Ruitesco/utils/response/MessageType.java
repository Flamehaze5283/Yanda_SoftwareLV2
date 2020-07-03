package com.Yanda.Ruitesco.utils.response;

public class MessageType <T>{
	int status;
	String msg;
	T data;
	
	public MessageType() {
		// TODO Auto-generated constructor stub
	}
	
	public MessageType(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
