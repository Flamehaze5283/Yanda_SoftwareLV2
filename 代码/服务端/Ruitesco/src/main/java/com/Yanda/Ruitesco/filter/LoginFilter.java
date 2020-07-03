package com.Yanda.Ruitesco.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Yanda.Ruitesco.dataResp.DataResp;
import com.Yanda.Ruitesco.utils.LoginLimitUtil;
import com.google.gson.Gson;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns= {"/user","/manage/user"},filterName="/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		if(request.getParameter("mode").equals("login"))
		{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8"); 
			String ipAddress = req.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
			    ipAddress = req.getRemoteAddr();
			}
			long remainTime = LoginLimitUtil.QuaryLimited(req.getParameter("username"),ipAddress);
			if(remainTime==0)
				chain.doFilter(request, response);
			else
			{
				PrintWriter pw= resp.getWriter();
				String json=null;
				Gson gson=new Gson();
				DataResp<Long> dataResp =new DataResp<Long>(1,"请在过了指定秒数后在访问",remainTime);
				json = gson.toJson(dataResp);
				System.out.println(json);
				pw.write(json);
				pw.close();
		
			}
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
