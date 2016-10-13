package com.smart.container.app;

import com.smart.container.servlet.HttpServlet;
import com.smart.container.servlet.ServletRequest;
import com.smart.container.servlet.ServletResponse;

public class LoginServlet1 extends HttpServlet{  
	  
    @Override  
    public void doGet(ServletRequest req, ServletResponse res) {
    	System.out.println("Hello World! This is a dynamic page with get method ");
    	res.out.print("Hello World! This is a dynamic page with get method "+"<br>"); // 输出结果信息  
    	res.out.close();
    }  
      
    @Override  
    public void doPost(ServletRequest req, ServletResponse res) {  
    	System.out.println("Hello World! This is a dynamic page with post method");
    	res.out.print("Hello World! This is a dynamic page with post method "+"<br>"); // 输出结果信息  
    	res.out.close();
    }  
  
}  