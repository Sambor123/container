package com.smart.container.app;

import com.smart.container.servlet.Servlet;
import com.smart.container.servlet.ServletConfig;
import com.smart.container.servlet.ServletException;
import com.smart.container.servlet.ServletRequest;
import com.smart.container.servlet.ServletResponse;

public class LoginServlet implements Servlet{
	
	@Override
	public void service(ServletRequest request, ServletResponse response) {
        // TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
	    String username = request.getParameter("username");
	    String password = request.getParameter("paw");
	    System.out.println(username+password);
	    
	    //response.setEnCoding("utf-8");
	    response.out.println("Good,this is Servlet!");
	    response.out.println("username:"+username+"  password:"+password);
	    response.out.close();
   }
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
