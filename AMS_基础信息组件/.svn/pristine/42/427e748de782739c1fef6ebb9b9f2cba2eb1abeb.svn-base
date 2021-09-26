package com.yss.ifa.szt.tool.file;

import java.io.File;
import java.net.SocketTimeoutException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.stream.XMLStreamException;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.http.HTTPException;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.yss.framework.api.mvc.control.ControlException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.protocol.webService.AbstractWebService;
import com.yss.framework.protocol.webService.IWebServiceInfoLoader;
import com.yss.sys.ws.FileTransPojo;

public class SztFileSendClient extends AbstractWebService{
	
	private String ip;
	
	private String timeOut;
	
	private String filePath;
	
	private FileTransPojo pojo;
	
	public void init(String ip, FileTransPojo pojo, String timeOut, String filePath){
		this.ip = ip;
		this.pojo = pojo;
		this.timeOut = timeOut;
		this.filePath = filePath;
	}
	
	public String send(){
        String result = "";
        Thread current = Thread.currentThread();
		ClassLoader old = current.getContextClassLoader();
        try {
			String url = "http://"+ ip +"/FileMessage?wsdl";
			//获取桥接器(web项目)的的ClassLoader
			ClassLoader newCl = this.getClass().getClassLoader();
			//把当前bundle的ClassLoader设置为web项目（我们系统中有platform跟bridge）的ClassLoader
			current.setContextClassLoader(newCl);
            
			result=factory.invoke(url, new Object[]{pojo}, "send",timeOut,"true").toString();
    
        } catch (Exception ex) {
            throw new ServiceException("发送GET请求出现异常！",ex);
        }finally{
        	current.setContextClassLoader(old);
        }
        return result;
	}
    
    public static void main(String args[]) {
        {
          SendGet();
        }

        System.exit(0);
    }
    
	public static String SendGet(){
        String result = "";
        try {
			String url = "http://192.168.1.79/FileMessage?wsdl";
			FileTransPojo body = new FileTransPojo();
			String path = "C://Users//lenovo//Desktop//赢时胜v4.5客户新会计准则解决方案交流会.ppt";
			File file = new File(path);
    		body.setFileName(file.getName());
    	    DataSource source = new FileDataSource(file);
    	    body.setAction(12);
            body.setFileDataHandler(new DataHandler(source));
            SztFileSendClient client = new SztFileSendClient();
            client.init("192.168.1.79", body, "3000", "");
    		result = client.send();
    		System.out.println("==文件上传完成");
        } catch (Exception ex) {
            throw new ServiceException("发送GET请求出现异常！",ex);
        }
        return result;
	}

	@Override
	public String getURLInfo(IWebServiceInfoLoader info) {
		return null;
	}

	@Override
	public String getTargetNamespace(IWebServiceInfoLoader info) {
		return info.getTargetNamespace();
	}
    
}
