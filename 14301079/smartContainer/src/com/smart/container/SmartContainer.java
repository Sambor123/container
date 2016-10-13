package com.smart.container;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

import com.smart.container.bean.RequestHeader;
import com.smart.container.http.IRequestHeaderParser;
import com.smart.container.servlet.Servlet;
import com.smart.container.servlet.ServletRequest;
import com.smart.container.servlet.ServletResponse;
import com.smart.container.utils.ConfigUtil;
import com.smart.container.utils.WebUtil;

public class SmartContainer extends Thread{
	private static ServerSocket server;
    private Socket socket;

    public SmartContainer(Socket socket) {
        this.socket = socket;
    }
    
    public static void openServer() throws Exception {  
        server = new ServerSocket(Integer.parseInt(ConfigUtil.getValue("smart.port")));  
        System.out.println("Container is starting...");;
        while (true) {  
        	System.out.println("Waiting for client to connect in port 3333...");;
        	Socket socket=server.accept();
        	System.out.println("Connectted..."+socket.getInetAddress());;
            new SmartContainer(socket).start();  
        }  
    }  
  
    public static void closeServer() throws Exception {  
	    if (server != null) {  
	        if (!server.isClosed()) {  
	            server.close();  
	        }  
	    }  
    }
    @Override
    public void run(){
    	InputStream in = null;  
        OutputStream out = null;  
        FileInputStream fin = null;  
        InputStreamReader isr=null;
        
        try {  
            in = socket.getInputStream(); // 获取客户端发送的字节流  
            try {
    			 isr= new InputStreamReader(in,"GBK");
    		} catch (UnsupportedEncodingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} 
            out = socket.getOutputStream(); // 获取服务端响应的字节流  
            byte[] b = new byte[1024 * 1024]; // 设置字节缓冲区  
            in.read(b); // 读取客户端字节流（字节流的请求头） 
            String txt = new String(b,"utf-8").trim(); // 将请求头封装成String，准备交给解析器解析  
            System.out.println(txt);
            IRequestHeaderParser parser = (IRequestHeaderParser) Class.forName(  
                    ConfigUtil.getValue("smart.requestheader.class"))  
                    .newInstance(); // 使用工厂设计模式从请求头中加载请求头解析器的实例  
            RequestHeader header = parser.parse(txt); // 解析请求头文本，使用RequestHeader封装其内容  
            //System.out.println("header:"+header);  
            //封装请求头  
            ServletRequest request = new ServletRequest();  
            request.setParameter(header.getParameter());  
            request.setHeader(header); 
            ServletResponse response=new ServletResponse();
            
            // 有请求处理类就加载，没有就检索静态网页文件  
            if(WebUtil.getServlet(header.getUrl()) != null) {  
            	System.out.println("Servlet:"+WebUtil.getServlet(header.getUrl()));
                try {  
                	Servlet servlet = (Servlet) Class.forName(WebUtil.getServlet(header.getUrl())).newInstance(); 
                	
                    response.setOut(new PrintWriter(out,true));  
                    servlet.service(request,response);
                    
                } catch(Exception e) {// 编译Servlet发生异常，加载500  
                    File file = new File(ConfigUtil.getValue("smart.webapps"), "500.html");  
                    fin = new FileInputStream(file);  
                    byte[] buf = new byte[(int) file.length()];  
                    fin.read(buf);  
                    out.write(buf);  
                }  
            } else {  
                File file = new File(ConfigUtil.getValue("smart.webapps"),header.getUrl()); // 从配置文件检索服务端静态页面存放目录，定位到服务器端的静态页面  
                if(!file.exists()) {// 请求静态页面不存在，加载404  
                    file = new File(ConfigUtil.getValue("smart.webapps"), "404.html");  
                }   
                fin = new FileInputStream(file);  
                byte[] buf = new byte[(int) file.length()];  
                fin.read(buf); // 读取静态页面内容  
                out.write(buf); // 将静态页面内容写回给客户端浏览器显示  
            }  
        } catch (Exception e) {
            e.printStackTrace();  
        } finally {  
            try {  
                if (fin != null) {  
                    fin.close();  
                }  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
                if (socket != null) {  
                    socket.close();  
                }  
            } catch (IOException e) {  
            }  
        } 
       
    }
}
