package com.Yanda.Ruitesco.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		
		String type = req.getParameter("type");
		if(type == "login") {
			//µÇÂ¼
		}
		else if(type == "register") {
			//×¢²á
		}
		else if(type == "exit") {
			//ÍË³ö
		}
		else {
			//ÆäËû
		}
	}
}
