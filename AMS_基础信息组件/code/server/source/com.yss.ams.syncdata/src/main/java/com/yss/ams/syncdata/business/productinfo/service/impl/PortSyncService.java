package com.yss.ams.syncdata.business.productinfo.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.util.ReflectionUtils;

import com.yss.ams.syncdata.business.productinfo.dao.PortDao;
import com.yss.ams.syncdata.business.productinfo.dao.PortSqlBuilder;
import com.yss.ams.syncdata.business.productinfo.pojo.PortPdAttribute;
import com.yss.ams.syncdata.business.productinfo.pojo.PortRela;
import com.yss.ams.syncdata.business.productinfo.service.IPortSyncService;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncPort;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncPortCls;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheRefresh;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.ReflectionUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.DbPoolFactory;

@DefaultCacheRefresh(group = CacheGroup.PORT)
public class PortSyncService implements IPortSyncService{
	
	private PortDao serviceDao = null;
	
	public PortSyncService() throws Exception {
		serviceDao = new PortDao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new PortSqlBuilder());
	}

	
	/**
	 * 数据同步方法
	 * @param pojo 同步数据
	 * @param operType 操作类型：SYNC_ADD(新增)、SYNC_DEL(删除)、SYNC_UPD(修改)
	 * @return
	 * @throws Exception
	 */
	@Override
	@CacheRefresh(group = CacheGroup.PORT)
	public String syncHandleData(List<Object> pojos, String operType)
			throws Exception {
		String result = MvcConstant._Success;
		Connection conn = null;
		try {
			conn = serviceDao.loadNewConnection();
			conn.setAutoCommit(false);
			SyncPort syncPort = (SyncPort)pojos.get(0);
			Port port = new Port();
			Bean2Bean(port, syncPort);
			completePort(port);
			Port cachePort = serviceDao.getPortInfo(syncPort.getC_PORT_CODE());
			
			if ("SYNC_DEL".equals(operType)) {
				if (cachePort !=null ) {
					port.setId(cachePort.getId());
					serviceDao.deleteById(port,conn);
				}			
			} else {
				if (cachePort !=null ) {
					port.setId(cachePort.getId());
					serviceDao.updateById(port,conn);
				} else {
					serviceDao.insert(port,conn);
				}
				
				//同步产品属性
				PortPdAttribute pdPort = new PortPdAttribute();
				Bean2Bean(pdPort, syncPort);
				// 明细资产类型字段名称不一致
				pdPort.setC_DAT_MXCODE(syncPort.getC_DAT_TYPE());
				pdPort.setModifier(AppContext.getInstance().getUserCode());
				pdPort.setModifyDate(DateUtil.getCurrDate("yyyyMMdd HH:mm:ss"));
				serviceDao.insertPortPd(pdPort,conn);
				//同步产品关联机构
				List<PortRela> prLisr = getPrLIst(syncPort);
				serviceDao.insertPortRela(prLisr,conn);
				
				//同步分级产品参数
				if(null != syncPort.getSyncPortCls() && !syncPort.getSyncPortCls().isEmpty()){
					List<SyncPortCls> portCls = syncPort.getSyncPortCls();
					serviceDao.insertPortCls(portCls,conn);
				}
			}

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			result = MvcConstant._Fault;
			throw e;
		} finally {
			serviceDao.releaseConnection(conn);
		}
		
		return result;
	}
	
	/**
	 * 完善port对象
	 * @param port
	 * @throws Exception
	 */
	private void completePort(Port port) throws Exception {
		if(port.getD_CLOSE() == null) {
			port.setD_CLOSE("1900-01-01 00:00:00");
		}
		port.setModifier(AppContext.getInstance().getUserCode());
		port.setModifyDate(DateUtil.getCurrent());
		port.setD_CLEAR(YssFun.parseDate("9998-12-31", "yyyy-MM-dd"));
	}
	
	/**
     * 复制两个对象相同属性的值
     * @param targetObj
     * @param SourceObj
     * @throws Exception
     */
	public void Bean2Bean(Object targetObj, Object SourceObj) throws Exception {
		Field[] tarFields = targetObj.getClass().getDeclaredFields();
		Field[] surFields = SourceObj.getClass().getDeclaredFields();

		Field tarField, surField;
		Object fieldValue;

		// 对目标ValueObject的字段数组进行循环，循环中欠套对源ValueObject的字段数组进行循环，如果属性名称相同则复制属性值。
		for (int i = 0; i < tarFields.length; i++) {
			tarField = tarFields[i];
			fieldValue = "";
			for (int j = 0; j < surFields.length; j++) {
				surField = surFields[j];
				if(!"serialVersionUID".equals(surField.getName())){
					if (surField.getName().equals(tarField.getName())) {
						//Fortify 规范代码改造避免空指针异常
						//tarField.setAccessible(true);
						 ReflectionUtils.makeAccessible(tarField);
						// 获得ValueObject的get和set方法
						fieldValue = ReflectionUtil.getFieldValueObjWithGetMethod(SourceObj, surField);
						tarField.set(targetObj, fieldValue);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * 构建产品关联机构list
	 * @param syncPort
	 * @return
	 * @throws Exception
	 */
	private List<PortRela> getPrLIst(SyncPort syncPort) throws Exception {
		List<PortRela> prList = new ArrayList<PortRela>();
		//目前关联机构只有这三种，通过map方便日后添加
		HashMap<String, String> dvMap = new HashMap<String, String>();
		dvMap.put("MANAGER", "C_DV_MANAGER");
		dvMap.put("TRUSTEE", "C_DV_TRUSTEE");
		dvMap.put("CONSIGNER", "C_DV_CONSIGNER");
		Class<?>[] getMethodArgs = new Class<?>[] {};
		Object[] invokeArgs = new Object[] {};
		PortRela pr = null;
		try {
			for(Entry<String, String> entry : dvMap.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				//通过反射获取关联代码，如果值为空就不插入
				Method methGet = syncPort.getClass().getMethod("get"+value,getMethodArgs);
				String dvValue = (String)methGet.invoke(syncPort, invokeArgs);
				if(StringUtil.IsNullOrEmptyT(dvValue)) {
					continue;
				}
				pr = new PortRela();
				pr.setC_DV_TYPE_CODE(key);
				pr.setC_RELA_CODE(dvValue);
				pr.setC_PORT_CODE(syncPort.getC_PORT_CODE());
				pr.setC_RELA_TYPE("RELA_ORG");
				pr.setAuditState(1);
				pr.setModifier(AppContext.getInstance().getUserCode());
				pr.setModifyDate(DateUtil.getCurrDate("yyyyMMdd HH:mm:ss"));
				prList.add(pr);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return prList;
	}
	
}
