package com.yss.ams.visaval.support.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.osgi.internal.baseadaptor.DefaultClassLoader;
import org.osgi.framework.Bundle;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.yss.ams.visaval.support.util.pojo.AlgoAPIU;
import com.yss.ams.visaval.support.util.pojo.ParamsU;
import com.yss.framework.api.util.JAXBProcessor;

/**
 * AlgoAPI.xml解析工具类
 * 
 * @author 马向峰
 * @Date 20170712
 */
public class AlgoAPIXMLParse {

	/**
	 * 加载并解析AlgoApi.xml
	 */
	/*private static Bundle bundle = null;
	
	private Bundle getBundle(){
		return ((DefaultClassLoader) this.getClass().getClassLoader()).getBundle();
	}*/
	
	public AlgoAPIU load() {
		InputStream is = null;
		try {
			//bundle = getBundle();
			Bundle bundle = ((DefaultClassLoader) this.getClass().getClassLoader()).getBundle();
			URL url = bundle.getEntry("META-INF/config/algo/AlgoApi.xml");
			URL runtimeURL = FileLocator.resolve(url);
			JAXBContext jaxbContext = JAXBContext.newInstance(AlgoAPIU.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        SAXParserFactory sax = SAXParserFactory.newInstance();
	        sax.setNamespaceAware(false);
	        XMLReader xmlReader = sax.newSAXParser().getXMLReader();
	        is = runtimeURL.openStream();
	        Source source = new SAXSource(xmlReader, new InputSource(is));
	        AlgoAPIU algoAPI = (AlgoAPIU)unmarshaller.unmarshal(source);
			return algoAPI;
		} catch (Exception e) {
			e.getStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}

		return null;
	}
	
	public ParamsU loadParams() {
		InputStream is = null;
		try {
			Bundle bundle = ((DefaultClassLoader) this.getClass().getClassLoader()).getBundle();
			URL url = bundle.getEntry("META-INF/config/keyword/keyparam.xml");
			URL runtimeURL = FileLocator.resolve(url);
			JAXBContext jaxbContext = JAXBContext.newInstance(ParamsU.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        SAXParserFactory sax = SAXParserFactory.newInstance();
	        sax.setNamespaceAware(false);
	        XMLReader xmlReader = sax.newSAXParser().getXMLReader();
	        is = runtimeURL.openStream();
	        Source source = new SAXSource(xmlReader, new InputSource(is));
	        ParamsU params = (ParamsU)unmarshaller.unmarshal(source);
//			jProc = new JAXBProcessor();
//			AlgoAPIU algoAPI = new AlgoAPIU();
//			algoAPI = (AlgoAPIU) jProc.unMarshal(algoAPI, runtimeURL);
			return params;
		} catch (Exception e) {
			e.getStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}

		return null;
	}

	public static void main(String[] args)throws Exception{
//		JAXBContext jaxbContext = JAXBContext.newInstance(AlgoAPIU.class);
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        SAXParserFactory sax = SAXParserFactory.newInstance();
//        sax.setNamespaceAware(false);
//        XMLReader xmlReader = sax.newSAXParser().getXMLReader();
//        InputStream is = new FileInputStream(new File("D:\\AlgoApi.xml"));
//        Source source = new SAXSource(xmlReader, new InputSource(is));
//        AlgoAPIU api = (AlgoAPIU)unmarshaller.unmarshal(source);
//        System.out.println(api.getAlgos());
//		AlgoAPIXMLParse a = new AlgoAPIXMLParse();
//		System.out.println(a.load());
	}
}
