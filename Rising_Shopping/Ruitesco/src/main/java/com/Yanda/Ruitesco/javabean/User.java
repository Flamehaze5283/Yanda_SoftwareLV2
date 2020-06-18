package com.Yanda.Ruitesco.javabean;

import java.sql.Timestamp;

public class User {
	String username;
	int id;
	String password;
	String phone;
	String email;
	String role;
	String question;
	String answer;
	Timestamp create_time;
	Timestamp update_time;
	
	public User() {}
	public User(String username, String password, String phone, 
		    String email, String role, String question,
		    String answer, Timestamp create_time,Timestamp update_time) {
	this.username = username;
	this.password = password;
	this.phone = phone;
	this.email = email;
	this.role = role;
	this.question = question;
	this.answer = answer;
	this.create_time = create_time;
	this.update_time = update_time;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", id=" + id + ", password=" + password + ", phone=" + phone + ", email="
				+ email + ", role=" + role + ", question=" + question + ", answer=" + answer + ", create_time="
				+ create_time + ", update_time=" + update_time + "]";
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
}
