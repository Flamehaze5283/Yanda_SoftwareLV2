package com.Yanda.Ruitesco.servlet;

public final class DataResp {
	public int status;
	public Object data;
	public String msg;
	public DataResp(int status, Object data, String msg) {
		this.status = status;
		this.data = data;
		this.msg = msg;
	}
	
}
