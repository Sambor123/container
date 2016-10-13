package com.smart.container;
/**
 * 容器初始化组件
 * @author Xiongbo
 *
 */
public class StartContainer {
	public static void main(String[] args) {  
        try {  
        	System.out.println("Container is preparing...");;
            SmartContainer.openServer();  
        } catch (Exception e) {  
        	e.printStackTrace();
            try {
            	SmartContainer.closeServer();  
            } catch (Exception e1) {  
                e1.printStackTrace();  
            }  
        }  
    }  
}
