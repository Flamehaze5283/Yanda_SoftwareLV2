package com.Yanda.Ruitesco.utils.response;

/**
 * ǰ���û��ز�responseͨ����
 * */
public class UserResponse <T>{
	int status;
	T msg;
	
	public UserResponse() {
		// TODO Auto-generated constructor stub
	}
	public UserResponse(int Status, T msg){
		this.status = Status;
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
