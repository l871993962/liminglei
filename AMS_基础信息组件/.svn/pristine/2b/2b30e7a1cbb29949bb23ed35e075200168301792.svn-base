package com.yss.ams.syncdata.modules.base.service.impl;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncData;
import com.yss.ams.syncdata.support.modules.base.pojo.XMLFuncode;
import com.yss.ams.syncdata.support.modules.base.pojo.XMLFuncodeCfg;
import com.yss.ams.syncdata.support.modules.base.pojo.XMLSystemCode;
import com.yss.ams.syncdata.support.modules.base.service.ISyncDataOperService;
import com.yss.ams.syncdata.support.modules.base.service.ISyncDataService;
import com.yss.ams.syncdata.support.modules.base.service.ISyncGenerateService;
import com.yss.ams.syncdata.util.SyncConfig;
import com.yss.framework.api.busoperservice.BaseOper;
import com.yss.framework.api.busoperservice.BizItem;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.protocol.webService.WebServiceFactory;

/**
 * 数据同步 普通服务类
 * @author chenyoucai
 */
public class SyncGenerateService extends BaseOper implements
ISyncGenerateService {

	private HashMap<String, Object> paramMap; // 原始参数
	private List<BEN_RECORD> recordLst = new ArrayList<BEN_RECORD>();
	private String menuId = ""; // 功能代码
	private String executeId;
	//同步模块code
	private String c_DV_MODULE_CODE;
	//同步模块name
	private String c_DV_MODULE_NAME;
	//系统代码
	private String c_SYSTEM_CODE;
	//操作类型
	private String c_DV_OPER_TYPE;
	//数据id
	private String c_DATA_ID;
	//接收时间
	private String c_RECEIVE_TIME;
	
	/**
	 * 数据同步配置类
	 */
	private SyncConfig  syncConfig = null;
	
	@Override
	public String getMenuId() {
		return this.menuId;
	}

	@Override
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public void init(HashMap<String, Object> paraMap) {
		this.paramMap = paraMap;
	}

	@Override
	public List<BizItem> getBizItems() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BizItem> getBizItems(List<String> codes)
			throws ServiceException {
		return this.getBizItems();
	}

	@Override
	public List<BizItem> getRootBizItems() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<String, List<BEN_RECORD>> execute() throws Exception {
		String result = doBusOper(this.paramMap);
		Map<String, List<BEN_RECORD>> map = new HashMap<String, List<BEN_RECORD>>();
		map.put(result, recordLst);
		//Map.Entry<String, List<BEN_RECORD>> retVal = new Map.Entry<String, List<BEN_RECORD>>();
		//recList = lstRecord;
		return (Map.Entry<String, List<BEN_RECORD>>)map.entrySet().toArray()[0];
	}

	@Override
	public String doBusOper(HashMap<String, Object> hmData)
			throws ServiceException {
		String result = "";
		BEN_RECORD record = new BEN_RECORD(YssContextFactory.getInstance()
				.getUserCode());
		ISyncDataService syncDataService = YssServiceFactory.getInstance().createService(ISyncDataService.class);
		try {
			executeId = String.valueOf(hmData.get("C_OPER_CODE"));
			c_DV_MODULE_CODE = String.valueOf(hmData.get("C_DV_MODULE_CODE"));
			c_SYSTEM_CODE = String.valueOf(hmData.get("C_SYSTEM_CODE"));
			c_DV_OPER_TYPE = String.valueOf(hmData.get("C_DV_OPER_TYPE"));
			menuId = String.valueOf(hmData.get("C_FUN_CODE"));
			c_DATA_ID = String.valueOf(hmData.get("C_DATA_ID"));
			c_RECEIVE_TIME = String.valueOf(hmData.get("C_RECEIVE_TIME"));
			c_DV_MODULE_NAME = String.valueOf(hmData.get("C_DV_MODULE_NAME"));
			String c_iden =String.valueOf(hmData.get("C_IDEN"));
			record.setD_Trade(YssFun.toDate(c_RECEIVE_TIME));
			record.setC_Port_Code(" ");
			record.setC_Fun_Code(menuId);
			record.setC_Report_Code(c_iden);
			record.setC_Item_Code(c_SYSTEM_CODE);
			record.BeginLog("开始同步[" + c_RECEIVE_TIME + " " + c_DV_MODULE_NAME + "]数据...");
			record.appendDetailMes("开始同步[" + c_RECEIVE_TIME + " " + c_DV_MODULE_NAME + "]数据...");
			sender.write(executeId, record);
			
			this.syncConfig = SyncConfig.newInstance();
			WebServiceFactory factory = new WebServiceFactory();
			
			//开始同步
			XMLFuncodeCfg systemCodes = syncConfig.getSyncDataFuncodeCfg();
			for (XMLSystemCode systemCode : systemCodes.getSystemCode()) {
				if(systemCode.getCode().equals(c_SYSTEM_CODE)){
					for (XMLFuncode funcode : systemCode.getFuncodes()) {
						if(funcode.getCode().equals(c_DV_MODULE_CODE)){
							//构建webservice参数
							List<String> list = new ArrayList<String>();
							list.add(c_DATA_ID);
							Object[] param = new Object[]{list,c_DV_MODULE_CODE,c_SYSTEM_CODE};
							
							//发送webservice获取同步数据
							String resultStr = factory.invoke(syncConfig.getSyncDataWebServiceUrl(), param, "getSyncDataByIds", "3000");
//							if(resultStr.startsWith("[")){
//								resultStr = resultStr.substring(1);
//							}
//							if(resultStr.endsWith("]")){
//								resultStr = resultStr.substring(0,resultStr.length()-1);
//							}
							logger.log("接收到消息->"+resultStr);
							//解析同步数据
							List syncInfoList = JSONObject.parseArray(resultStr, Class.forName(funcode.getPojo()));
							//Object syncInfo = JsonUtil.toBean(resultStr, Class.forName(funcode.getPojo()));
							ISyncDataOperService serviceTarget = YssServiceFactory.getInstance().createService(funcode.getServiceId());
							List<Object> basePojos = new ArrayList<Object>();
							for(Object syncInfo : syncInfoList){
								basePojos.add(syncInfo);
							}
							//basePojos.add((Object)syncInfo);
							//开始同步
							result = serviceTarget.syncHandleData(basePojos,c_DV_OPER_TYPE);
							if(result.equalsIgnoreCase(MvcConstant._Success)){
								List<String> ids = new ArrayList<String>();
								ids.add(c_iden);
								syncDataService.syncSuccess(ids);
							}
							break;
						}
					}
				}
			}
			
			if(result.equalsIgnoreCase(MvcConstant._Success)){
				result = MvcConstant._Success;
				record.appendDetailMes_Green("接收时间:" + c_RECEIVE_TIME + " " + c_DV_MODULE_NAME + " 同步成功！");
				record.EndLog_Success("接收时间:" + c_RECEIVE_TIME + " " + c_DV_MODULE_NAME + " 同步成功！");
			}else{
				result = MvcConstant._Fault;
				record.appendDetailMes_Red("接收时间:" + c_RECEIVE_TIME + " " + c_DV_MODULE_NAME + " 同步失败！");
				record.EndLog_Fail("接收时间:" + c_RECEIVE_TIME + " " + c_DV_MODULE_NAME + " 同步失败！");
			}

		} catch (Exception ex) {
			//ex.printStackTrace();
			logger.debug(ex.getMessage());
			record.appendDetailMes_Red(ex.getMessage());
			record.EndLog_Fail("接收时间:" + c_RECEIVE_TIME + " " + c_DV_MODULE_NAME + " 同步失败！");
			result = MvcConstant._Fault;
		} finally {
			sender.write(executeId, record);
			recordLst.add(record);
		}
		return result;
	}
	
	
	/**
	 * 前台触发：数据同步
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws Exception 
	 */
	public String syncData(List<SyncData> syncDatas) throws Exception {
		// ISyncPlmDataService(String ids[],String funCode,String systemId)
		//WebServiceFactory factory = new WebServiceFactory();
//		String result = "[<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><SYNCData><Header/><Datas/></SYNCData>]";
//		if(result.startsWith("[")  && result.endsWith("]")){
//			result = result.substring(1,result.length() -1);
//		}
		
		//System.out.println(aString);
		

		
		return null;
	}

	@Override
	public void init(Object... args) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	
}
