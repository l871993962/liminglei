package com.yss.ams.syncdata.modules.base.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.yss.ams.syncdata.activator.SyncDataActivator;
import com.yss.ams.syncdata.modules.base.dao.SyncDataDao;
import com.yss.ams.syncdata.modules.base.dao.SyncDataSqlBuilder;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncData;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncMasg;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncModule;
import com.yss.ams.syncdata.support.modules.base.service.ISyncDataService;
import com.yss.ams.syncdata.util.SyncConfig;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.protocol.webService.WebServiceFactory;

/**
 * 数据同步 普通服务类
 * @author chenyoucai
 */
public class SyncDataService extends ServiceBus<SyncDataService> implements
		ISyncDataService {

	private SyncDataDao serviceDao = null;
	
	/**
	 * 数据同步配置类
	 */
	private SyncConfig  syncConfig = null;
	
	/**
	 * webservice工厂
	 */
	WebServiceFactory factory = null;
	
	public SyncDataService() throws Exception {
		serviceDao = new SyncDataDao(YssDbPoolFactory.getInstance().getDbPool(SyncDataActivator.class),
				new SyncDataSqlBuilder());
		dao = serviceDao;
		factory = new WebServiceFactory();
	}
	
	/**
	 * 获取系统应用代码
	 * @throws YssException 
	 */
	@Override
	public String getSystemCode() throws YssException {
		if(this.syncConfig == null){
			this.syncConfig = SyncConfig.newInstance(); 
		}
		return syncConfig.getSyncDataIpAndBusiCode().getSystemCode();
	}

	/**
	 * 保存数据同步模块设置
	 * @param syncModule
	 * @return
	 * @throws Exception
	 */
	@Override
	public String saveSyncModule(List<SyncModule> syncModules) throws Exception {
		if(this.syncConfig == null){
			this.syncConfig = SyncConfig.newInstance(); 
		}
		return serviceDao.saveSyncModule(syncModules,syncConfig.getSyncDataIpAndBusiCode());
	}

	/**
	 * 获取同步模块配置信息
	 */
	@Override
	public List<SyncModule> queryAllFuncodeCfg() {
		if(this.syncConfig == null){
			this.syncConfig = SyncConfig.newInstance(); 
		}
		return serviceDao.queryAllFuncodeCfg(syncConfig.getSyncDataIpAndBusiCode().getSystemCode());
	}
	
	/**
	 * 前台触发：忽略消息（只有已接收状态下的消息才可忽略）
	 * @param ids
	 * @return
	 */
	@Override
	public String ignoreMessages(List<SyncData> syncDatas) {
		if(this.syncConfig == null){
			this.syncConfig = SyncConfig.newInstance(); 
		}
		String result = "";
		try {
		     HashMap<String, List<String>> modules = new HashMap<String, List<String>>();
             List<String> dataIds = null;
             List<String> c_idens = new ArrayList<String>();
             for (SyncData syncData : syncDatas)
             {
                 String moduleCode = syncData.getC_DV_MODULE_CODE();

                 if (modules.containsKey(moduleCode)){
                     dataIds = modules.get(moduleCode);
                     dataIds.add(syncData.getC_DATA_ID());
                 }
                 else {
                     dataIds = new ArrayList<String>();
                     dataIds.add(syncData.getC_DATA_ID());
                     modules.put(moduleCode, dataIds);
                 }

                 c_idens.add(syncData.getId());
             }
			
			List<SyncMasg> syncMasgs = new ArrayList<SyncMasg>();
			for (Entry<String, List<String>> entry : modules.entrySet()) {
				SyncMasg syncMasg = new SyncMasg();
				syncMasg.setC_FUN_CODE(entry.getKey());
				syncMasg.setC_SYS_ID(syncConfig.getSyncDataIpAndBusiCode().getSystemCode());
				syncMasg.setN_SYNC_STATE(0);//0-未同步；1-同步；2-已退回
				syncMasg.setIdList(entry.getValue());
				syncMasgs.add(syncMasg);
			}
			
			//构建webservice参数：忽略消息
			Object[] param = new Object[]{JsonUtil.toString(syncMasgs)};
			String resultStr = factory.invoke(syncConfig.getSyncDataWebServiceUrl(), param, "getSyncStateData", "1000");
			//更新本地数据状态
			result = serviceDao.ignoreMessages(c_idens);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			result = "false";
			throw new BusinessException("数据同步忽略消息失败!" + e.getMessage(), e);
		}

		return result;
	}
	
	/**
	 * 数据同步成功，将消息状态改成已更新
	 * @param ids
	 * @return
	 */
	@Override
	public void syncSuccess(List<String> ids) {
		serviceDao.syncSuccess(ids);
	}

}
