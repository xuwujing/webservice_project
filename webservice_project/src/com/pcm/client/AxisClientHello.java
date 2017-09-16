package com.pcm.client;



import java.io.IOException;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;


/**
 * 
* Title: AxisClientHello
* Description: webService 客户端调用
* Version:1.0.0  
* @author panchengming
 */
public class AxisClientHello {
	private final static String url="http://192.168.1.105:8080/axis2/services/AxisServiceHello?wsdl"; //wsdl地址 
	private final static String data="PanChengMing";												   //参数
	private final static String tns = "http://service.pcm.com";  									   //命名空间
	private final static String method="getValue";												  //调用的方法
	
	 //调用webservice
	public static void main(String[] args) throws  IOException{
			getRPC();			//调用方法一
			getDocument();		//调用方法二
	}
	
	
	
    /**  
     * 方法一：  
     * 应用rpc的方式调用 这种方式就等于远程调用，  
     * 即通过url定位告诉远程服务器，告知方法名称，参数等， 调用远程服务，得到结果。  
     * 使用 org.apache.axis2.rpc.client.RPCServiceClient类调用WebService  
     *  
        【注】：  
          
            如果被调用的WebService方法有返回值 应使用 invokeBlocking 方法 该方法有三个参数  
              第一个参数的类型是QName对象，表示要调用的方法名；  
              第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；  
                当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。  
              第三个参数表示WebService方法的 返回值类型的Class对象，参数类型为Class[]。  
            如果被调用的WebService方法没有返回值 应使用 invokeRobust 方法  
              该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同。  
      
            在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名，  
            也就是 <wsdl:definitions>元素的targetNamespace属性值。  
     *  
     */  
     @SuppressWarnings("rawtypes")
	public static void getRPC() throws AxisFault{ 
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        // 指定调用WebService的URL
        EndpointReference targetEPR = new EndpointReference(url);
        options.setTo(targetEPR);

        // 指定要调用的WSDL文件的命名空间及getValue方法
        QName qn = new QName(tns, method);
        // 指定getValue方法的参数值
        Object[] ob = new Object[] { data };
        // 指定getValue方法返回值的数据类型的Class对象
        Class[] classes = new Class[] { String.class };
        // 调用getValue方法并输出该方法的返回值
        System.out.println(serviceClient.invokeBlocking(qn, ob, classes)[0]);
	}
     
     /**  
      * 方法二： 应用document方式调用  
      * 用ducument方式应用现对繁琐而灵活。现在用的比较多。因为真正摆脱了我们不想要的耦合  
      */  
     public static void getDocument() throws AxisFault{ 
    	  OMElement result = null;  
          try {  
              Options options = new Options();  
              // 指定调用WebService的URL    
              EndpointReference targetEPR = new EndpointReference(url);  
              options.setTo(targetEPR);  
              // options.setAction("urn:getPrice");    
              
              ServiceClient sender = new ServiceClient();  
              sender.setOptions(options);  
    
              OMFactory fac = OMAbstractFactory.getOMFactory();  
              // 命名空间
              OMNamespace omNs = fac.createOMNamespace(tns, "");  
              
              OMElement ot = fac.createOMElement(method, omNs);  
              OMElement symbol = fac.createOMElement("name", omNs);  
              symbol.addChild(fac.createOMText(symbol, data));  
              ot.addChild(symbol);  
              result=sender.sendReceive(ot);
              System.out.println(result);  
          } catch (AxisFault axisFault) {  
              axisFault.printStackTrace();  
          }  
     }
	
}
