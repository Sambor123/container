package com.smart.container.servlet;

import java.io.PrintWriter;

public class ServletResponse {
	public PrintWriter out;  
	private String enCoding="utf-8";
	
    public PrintWriter getOut() {  
        return out;  
    }  
  
    public void setOut(PrintWriter out) {  
        this.out = out;  
    }

	public String getEnCoding() {
		return enCoding;
	}

	public void setEnCoding(String enCoding) {
		this.enCoding = enCoding;
	}  
	
	public void write(String text){
		
	}
    
    
}
