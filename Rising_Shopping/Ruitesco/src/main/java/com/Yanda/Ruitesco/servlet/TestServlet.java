package com.Yanda.Ruitesco.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test")
public class TestServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;		//�������
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* ��������������ַ */
		resp.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:8848");
		/* �����������󷽷�GET, POST, HEAD �� */
		resp.setHeader("Access-Control-Allow-Methods","*");
		/* ����Ԥ�������Ļ���ʱ�� (s) */
		resp.setHeader("Access-Control-Max-Age","3600");
		/* ������������ͷ */
		resp.setHeader("Access-Control-Allow-Headers","*");
		/* �Ƿ�Я��cookie */
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		req.getRequestDispatcher("WEB-INF/classes/jsp/test2.jsp").forward(req, resp);
	}
}

