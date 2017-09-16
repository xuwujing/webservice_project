package com.pcm.service;
/**
 * 
* Title: AxisServiceHello
* Description: Axis2 发布
* Version:1.0.0  
* @author panchengming
 */
public class AxisServiceHello {
	
	/** 供客户端调用方法  
	 * @param name  传入参数
	 * @return String 返回结果
	 * */
	public String getValue(String name){
		return "Axis 欢迎你！ "+name;
	}
	
//	//用于生成class类
//	public static void main(String[] args) {
//		System.out.println(new AxisServiceHello().getValue("panchengming"));
//	}

}
