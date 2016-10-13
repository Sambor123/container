package com.smart.container.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WebUtil {
	
	static Map<String, String> servletMap=new HashMap<>();
    static {  
    	SAXReader reader = new SAXReader();
        File file = new File(ConfigUtil.getValue("smart.webapps")+"/web.xml");
		try {
			Document document = reader.read(file);
			Element root = document.getRootElement();
			List<Element> servlets=root.elements("servlet");
			List<Element> mappings=root.elements("servlet-mapping");
			for(int i=0;i<servlets.size();i++){
				Element s=servlets.get(i);
				for(int j=0;j<mappings.size();j++){
					Element m=mappings.get(i);
					if(s.element("servlet-name").getText().equals(m.element("servlet-name").getText())){
						String servletClass=s.element("servlet-class").getText();
						String urlPattern=m.element("url-pattern").getText();
						servletMap.put(urlPattern, servletClass);
					}
				}
			}
			//servletMap.put(servlets., value)
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }  
    public static String getServlet(String urlPattern) {
		return servletMap.get(urlPattern);
		
	}
    public static void main(String[] args){
    	System.out.println(WebUtil.getServlet("/Login"));
    }

	
}
