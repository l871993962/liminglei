package com.yss.ams.syncdata.support.modules.base.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.syncdata.support.modules.base.pojo.SyncData;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncModule;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
/**
 * 数据同步-服务接口，主要进行增删改查操作
 * @author chenyoucai 
 */
@RestfulSupported
public interface ISyncDataService extends IServiceBus{
	
	/**
	 * 获取系统应用代码
	 * @throws Exception
	 */
	public String getSystemCode() throws Exception;
	
	/**
	 * 前台触发：忽略消息（只有已接收状态下的消息才可忽略）
	 * @param syncDatas
	 * @return
	 */
	public String ignoreMessages(List<SyncData> syncDatas);
	/**
	 * 数据同步成功，将消息状态改成已更新
	 * @param ids
	 * @return
	 */
	public void syncSuccess(List<String> ids);

	/**
	 * 获取同步模块配置信息
	 * @return
	 */
	public List<SyncModule> queryAllFuncodeCfg();
	
	/**
	 * 保存数据同步模块设置
	 * @param syncModule
	 * @return
	 * @throws Exception
	 */
	public String saveSyncModule(List<SyncModule> syncModule) throws Exception;
    
}
