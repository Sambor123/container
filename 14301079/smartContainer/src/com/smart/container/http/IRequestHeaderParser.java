package com.smart.container.http;

import com.smart.container.bean.RequestHeader;

public interface IRequestHeaderParser {
	public RequestHeader parse(String txt) throws Exception;  
}
