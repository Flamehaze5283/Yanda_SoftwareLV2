package com.Yanda.Ruitesco.dataResp;


public class DataResp<T> {
	private int status;
	private String msg;
	private T data;
	
	
	public DataResp(int status,String msg,T data)
	{
		this.status=status;
		this.msg=msg;
		this.data=data;
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
