package com.yss.ams.syncdata.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.dom4j.Element;
import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;

import com.yss.ams.syncdata.activator.SyncDataActivator;
import com.yss.ams.syncdata.consts.TopicAndTagCons;
import com.yss.ams.syncdata.support.modules.base.pojo.XMLFuncodeCfg;
import com.yss.ams.syncdata.support.modules.base.pojo.XMLSyncData;
import com.yss.framework.api.bundle.BundleContextWrapper;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.JAXBProcessor;
import com.yss.framework.api.util.file.XmlProcessor;
import com.yss.framework.api.webservice.pojo.WebService;
import com.yss.framework.api.webservice.service.IWebserviceService;

/**
 * 工具类
 * @author chenyoucai
 *
 */
public class SyncDataUtils {
	private static Logger logger = LogManager.getLogger(SyncDataUtils.class);
	
	/**
	 * 消息监听ip
	 * @return
	 * @throws YssException
	 */
	public static XMLSyncData getSyncDataIp(){
		XMLSyncData syncDataIp = null;
		FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.GLOABL_PATH);
		String filePath = fileUtil.getFilePath() + TopicAndTagCons.SYNCDATA_XML;
		File file = new File(filePath);
		if(file.exists()){
			 JAXBProcessor jaxbPro = new JAXBProcessor();
			 syncDataIp = new XMLSyncData();
			 try {
				syncDataIp = (XMLSyncData)jaxbPro.unMarshal(syncDataIp, new File(filePath));
			} catch (YssException e) {
				//e.printStackTrace();
				logger.log("加载数据同步监听配置文件syncdata.xml出错", e);
			}
		}
		 
		return syncDataIp;
	}

	/**
	 * 获取webservice的url
	 * @return
	 */
	public static String getWebserviceUrl() {
//		FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.PLATFORM_PATH);
//		String filePath = fileUtil.getFilePath() + YssConstant.YSSUCO_WEBSERVICE;
//		XmlProcessor xmlp = new XmlProcessor(filePath);
//		
//		// 根据ID加载WebService信息, type为WebServer服务
//		List<Element> els = xmlp.getNodes("//server[@id='SyncData']");
//		if (els == null || els.size() == 0)
//			return null;
//		
//		Element e = els.get(0);
		IWebserviceService service = YssServiceFactory.getInstance().createService(IWebserviceService.class);
		WebService iwService = service.getIWServiceById("SyncData");

		StringBuffer urlBuf = new StringBuffer();
		urlBuf.append("http://")
			  .append(iwService.getC_IP())
			  .append(":")
			  .append(iwService.getC_PORT())
			  .append("/")
			  .append(iwService.getC_SERVER())
			  .append("?wsdl");
		
		return urlBuf.toString();
	}
	
	/**
	 * 注册funcode
	 */
	public static XMLFuncodeCfg loadFuncodeCFG() {
		XMLFuncodeCfg systemCodes = null;
		BundleContextWrapper bundleContextWrapper = YssContextFactory.getInstance().getBundleContext(SyncDataActivator.class);
		Bundle bundle = bundleContextWrapper.getBundle();
		URL url = bundle.getEntry("META-INF/config/business/funcode.xml");
		if (url != null) {
			URL runtimeURL = null;
			try {
				runtimeURL = FileLocator.resolve(url);
			} catch (IOException e) {
				logger.log("加载funcode.xml文件出错", e);
			}
			 
			/* 初始化Service配置文件 */
			if (runtimeURL == null) {
				logger.log("未找到表达式配置文件");
			} else {
				try {
					JAXBProcessor jaxbPro = new JAXBProcessor();
					systemCodes = new XMLFuncodeCfg();
					systemCodes = (XMLFuncodeCfg)jaxbPro.unMarshal(systemCodes, runtimeURL);
				} catch (Exception ex) {
					logger.log("加载表达式出错", ex);
				}
			}
		}
		
		return systemCodes;
	}
	
}
