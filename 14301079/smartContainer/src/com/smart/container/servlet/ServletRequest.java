package com.smart.container.servlet;

import java.util.ArrayList;
import java.util.List;

import com.smart.container.bean.RequestHeader;
import com.smart.container.bean.RequestParameter;

public class ServletRequest {
	// 引入请求头  
    private RequestHeader header;  
    //编码
	private String characterEncoding;
	
	public String getCharacterEncoding() {
		return characterEncoding;
	}
	
	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}
	
    // 设置参数集合  
    private List<RequestParameter> params = new ArrayList<RequestParameter>();  
    // 设置请求参数  
    public void setParameter(String param) {  
        if(param == null || param.trim().equals("")) {  
            return;  
        }  
          
        String[] result = param.split("&");  
          
        for (int i = 0; i < result.length; i++) {  
        	RequestParameter parameter = new RequestParameter();  
            parameter.setKey(result[i].split("=")[0]);  
            parameter.setValue((result[i].split("=").length <= 1) ? "" : result[i].split("=")[1]);  
            params.add(parameter);  
        }  
    }  
    // 获取请求参数的值  
    public String getParameter(String key) {  
        String result = null;  
        for(RequestParameter parameter : params) {  
            if(parameter.getKey().equals(key)) {  
                result =  parameter.getValue();  
            }  
        }  
        return result;  
    }  
  
    public RequestHeader getHeader() {  
        return header;  
    }  
  
    public void setHeader(RequestHeader header) {  
        this.header = header;  
    }  
}
