package com.Yanda.Ruitesco.dataResp.type;

import java.sql.Timestamp;

import com.Yanda.Ruitesco.javabean.User;

public class UserData {
	private int id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String question;
	private String answer;
	private String role;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	public UserData(User user)
	{
		this.id=user.getId();
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.email=user.getEmail();
		this.phone=user.getPhone();
		this.question=user.getQuestion();
		this.answer=user.getAnswer();
		this.role=user.getRole();
		this.createTime=user.getCreate_time();
		this.updateTime=user.getUpdate_time();
	}
	
	public UserData(int id, String username, String password, String email, String phone, String question,
			String answer, String role, Timestamp createTime, Timestamp updateTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.question = question;
		this.answer = answer;
		this.role = role;
		this.createTime = createTime;
		this.updateTime = updateTime;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
