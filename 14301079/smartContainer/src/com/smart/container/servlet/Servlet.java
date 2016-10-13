package com.smart.container.servlet;

public interface Servlet {
	public void service(ServletRequest req, ServletResponse res); 
	
	public void destroy();

	public ServletConfig getServletConfig();
	
	public String getServletInfo();
	
	public void init(ServletConfig servletConfig) throws ServletException;
}
