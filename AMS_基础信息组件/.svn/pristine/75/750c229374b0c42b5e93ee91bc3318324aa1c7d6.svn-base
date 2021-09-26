package com.yss.ams.syncdata.util;

import com.yss.ams.syncdata.support.modules.base.pojo.XMLFuncodeCfg;
import com.yss.ams.syncdata.support.modules.base.pojo.XMLSyncData;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;

/**
 * 数据同步配置文件处理类
 * 
 * @author chenyoucai 20180624
 */
public class SyncConfig {
	private static Logger logger = LogManager.getLogger(SyncConfig.class);
	
	private static SyncConfig simple = new SyncConfig();
	
	/**
	 * webservice地址
	 */
	private String webServiceUrl = "";
	
	/**
	 * 应用系统代码配置
	 */
	private XMLSyncData syncDataIp = null;
	
	/**
	 * 应用系统与同步模块配置关系
	 */
	private XMLFuncodeCfg funcodeCfg = null;
	
	private  SyncConfig() {
		//webservice路径
		webServiceUrl = SyncDataUtils.getWebserviceUrl();
		
		//加载应用系统与同步模块配置关系
		funcodeCfg = SyncDataUtils.loadFuncodeCFG();
		
		//获取应用系统code
		syncDataIp = SyncDataUtils.getSyncDataIp();
	}
	
	public static SyncConfig newInstance(){
		return simple;
	}
	
	/**
	 * 消息监听ip和应用系统代码配置
	 * @return
	 */
	public synchronized XMLSyncData getSyncDataIpAndBusiCode(){
		return syncDataIp;
	}
	
	/**
	 * 应用系统与同步模块配置关系
	 * @return
	 */
	public synchronized XMLFuncodeCfg getSyncDataFuncodeCfg(){
		return funcodeCfg;
	}
	
	/**
	 * 数据同步webservice地址
	 * @return
	 */
	public synchronized String getSyncDataWebServiceUrl(){
		return webServiceUrl;
	}
	
}
