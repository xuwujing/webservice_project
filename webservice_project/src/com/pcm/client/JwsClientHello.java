package com.pcm.client;

import com.pcm.ws.jws.JwsServiceHello;
import com.pcm.ws.jws.JwsServiceHelloService;


/**
 * 
* Title: JwsClientHello
* Description: webService 客户端调用
* Version:1.0.0  
* @author panchengming
 */
public class JwsClientHello {

	public static void main(String[] args) {
		 //调用webservice
		 JwsServiceHello hello=new JwsServiceHelloService().getJwsServiceHelloPort();
        String name=hello.getValue("panchengming");
        System.out.println(name);
	}

} 
