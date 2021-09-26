package com.yss.ams.base.information.modules.sys.dttdmode.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.dttdmode.admin.DtTdModeDataAdmin;
//import com.yss.ams.base.information.modules.sys.dttdmode.cache.DtTdModeCache;
import com.yss.ams.base.information.modules.sys.dttdmode.dao.DttdModeDaoSqlBuilder;
import com.yss.ams.base.information.support.cache.DtTdModeCache;
import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.ams.base.information.support.sys.dttdmode.service.IDtTdModeDataService;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.cache.DtTdModeCache;
//import com.yss.dict.dttdmode.admin.DtTdModeDataAdmin;
//import com.yss.dict.dttdmode.dao.DttdModeDaoSqlBuilder;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

//import dataservice.comm.pojo.DttdMode;
//import dataservice.service.IDtTdModeDataService;


/**
 * 交易方式字典表T_S_DT_TD_MODE DataService
 *
 */
public class DtTdModeDataService implements IDtTdModeDataService {
	private DtTdModeDataAdmin dtTdModeAdmin = null;

	public DtTdModeDataService() {
		dtTdModeAdmin = new DtTdModeDataAdmin(DbPoolFactory.getInstance()
				.getPool(),
				new DttdModeDaoSqlBuilder());
	}

	/**
	 * 获取所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return 所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return dtTdModeAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 先从交易方式缓存中获取数据，如果缓存中无数据
	 * 获取所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return 所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
/*	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> getCacheLists() throws ServiceException {
		try {
			DtTdModeCache dtTdModeCache = CacheManager.getInstance().getCache(CacheGroup.BASEDTTDMODE);
			List<DttdMode> dtList = dtTdModeCache.getCacheList();
			if(null == dtList){
				dtList = dtTdModeAdmin.getAllDataList();
			}
			return (List<K>) dtList;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}*/

	/**
	 *  联合查询业务类型，交易方式数据,每个业务类型下的交易方式数据
	 */
	public <K extends BasePojo> List<K> getTreeDataList() throws ServiceException {
		try {
			return dtTdModeAdmin.getTreeData();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据业务类型C_BUSI_TYPE数组 或者 交易方式代码C_DT_CODE数组，获取业务类型，交易方式联合树形结构
	 * @return
	 */
	public <K extends BasePojo> List<K> getTreeDataByTypes(String[] types)
			throws ServiceException {
		try {
			return dtTdModeAdmin.getTreeDataByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return 所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = dtTdModeAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_dttdmode");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_dttdmode",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 先从交易方式缓存中获取数据，如果缓存中无数据
	 *  根据 交易方式代码C_DT_CODE 获取交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	/*@SuppressWarnings("unchecked")
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			DtTdModeCache dttdmodeCache = CacheManager.getInstance().getCache(CacheGroup.BASEDTTDMODE);
			DttdMode dttdMode = dttdmodeCache.getCacheByKey(dataCode);
			if(null == dttdMode){
				dttdMode = dtTdModeAdmin.getDataByCode(dataCode);
			}
			return (K) dttdMode;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}*/
	
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return dtTdModeAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 *  根据 业务类型C_BUSI_TYPE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return dtTdModeAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 *  根据 业务类型C_BUSI_TYPE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息，并初始化QueryRes对象
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = dtTdModeAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_dttdmode");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_dttdmode",InformationActivator.class));
			return res;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 *  联合查询业务类型，交易方式所有数据
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return dtTdModeAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 *  根据 交易方式代码C_DT_CODE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return dtTdModeAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 *  根据 交易方式代码C_DT_CODE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息，并初始化QueryRes对象
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = dtTdModeAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_dttdmode");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_dttdmode",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}
	
	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 根据业务代码获取POJO对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return (K) CacheManager.getInstance().getCache(CacheGroup.DTTDMODE)
				.getCacheByKey(pojoCode);
	}

	/**
	 * 根据代码获取对应名称的转换字典
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return CacheManager.getInstance().getCache(CacheGroup.DTTDMODE)
		.getKeyConvertMap(listKey);
	}

	/**
	 * 更新时间戳
	 */
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);//带上时间戳
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = dtTdModeAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		return data;
	}
	
	/**
	 * 取得所有数据(只包含代码和名称)
	 */
	@Override
	public HashMap<String, String> getShortDataMap(String c_BUSI_TYPE) throws ServiceException{
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			DtTdModeCache cache = CacheManager.getInstance().getCache(CacheGroup.DTTDMODE);
			for (DttdMode dttdMode : cache.getCacheList()) {
				if(!map.containsKey(dttdMode.getC_DT_CODE())){
					if(c_BUSI_TYPE == null || dttdMode.getC_BUSI_TYPE().equals(c_BUSI_TYPE)){
						map.put(dttdMode.getC_DT_CODE(), dttdMode.getC_DT_NAME());
					}
				}
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		}
		return map;
	}

	/**
	 * 加载分组拆分 交易方式 
	 * By Jinghehe 2015-11-08
	 * @return
	 */
	@Override
	public <K extends BasePojo> List<K> getTreeDataListForRule()
			throws ServiceException {
		try {
			return dtTdModeAdmin.getTreeDataForRule();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询分组拆分 交易方式中父节点的业务 
	 * By wangyaokang 2015-11-26
	 * @return
	 */
	public <K extends BasePojo> K getDataByCodeForRule(String dataCode)
			throws ServiceException {
		try {
			return dtTdModeAdmin.getDataByCodeForRule(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/***
	 * 恒生交易数据业务分类服务接口
	 * add by liyanjun 2016-2-20 STORY #28608 【广发证券】在分组恒生交易数据文件接口添加控制
	 * @param dataCode
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getTreeDataByCfgCode(String[] cfgCodes)
			throws ServiceException {
		try {
			return dtTdModeAdmin.getTreeDataByCfgCode(cfgCodes);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * add by wangtangyao 20160815 STORY #28887 保险资产证券清算款、其他应收款科目处理
	 * 证券清算款非T+1核算项下拉列表要加载开放申赎业务类型
	 * 支持多参数以数组形式传入
	 * @param type 词汇分类
	 * @return
	 */
	public <K extends BasePojo> List<K> getSQKDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return dtTdModeAdmin.getSQKDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * add by wangtangyao 20160815 STORY #28887 保险资产证券清算款、其他应收款科目处理
	 * 证券清算款非T+1核算项下拉列表要加载开放申赎业务类型
	 * @param type 词汇分类
	 * @return 
	 * @return
	 */
	public <K extends BasePojo>  List<DttdMode> getSQKDataByCode(String[] codes)
			throws ServiceException {
		List<DttdMode> dttdModes= null;
		DttdMode dttdMode = null;
		try {
			if(codes.length<2){
				return null;
			}
			String code = codes[1];
			List<DttdMode> dttds = dtTdModeAdmin.getSQKDataListByTypes(codes[0]);
			dttdModes = new ArrayList<DttdMode>();
			for(DttdMode dttd:dttds){
				if(code.equals(dttd.getC_DT_CODE())){
					dttdMode = dttd;
					break;
				}
			}
			dttdModes.add(dttdMode);
			return dttdModes;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据IDs查询数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:47:12
	 * @param ids
	 * @param clazz
	 * @return
	 * @return List<T>
	 */
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return dtTdModeAdmin.queryByIds(ids, DttdMode.class);
	}
	
	/**
	 * add by yuanyafeng 20180911 STORY #61545 【紧急】太平保险-附件管理优化（二期）
	 * 根据模块功能代码取对应模块的交易方式，没有的返回空
	 * @param funCode 功能代码
	 * @return 交易方式
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<DttdMode> getDataListByFun(String funCode)
			throws ServiceException {
		try {
			return dtTdModeAdmin.getDataListByFun(funCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
