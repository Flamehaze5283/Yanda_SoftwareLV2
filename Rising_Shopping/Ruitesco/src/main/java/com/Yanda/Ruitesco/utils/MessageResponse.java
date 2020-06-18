package com.Yanda.Ruitesco.utils;

public class MessageResponse <T>{
	int status;
	T msg;
	
	public MessageResponse() {
		// TODO Auto-generated constructor stub
	}
	public MessageResponse(int status, T msg){
		this.status = status;
		this.msg = msg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getMsg() {
		return msg;
	}
	public void setMsg(T msg) {
		this.msg = msg;
	}
	
}
