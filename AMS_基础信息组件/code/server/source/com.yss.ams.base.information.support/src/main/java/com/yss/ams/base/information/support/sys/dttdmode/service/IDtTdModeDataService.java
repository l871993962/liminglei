package com.yss.ams.base.information.support.sys.dttdmode.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dttdmode.pojo.DttdMode;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

//import dataservice.comm.pojo.DttdMode;

/**
 * 交易方式字典数据服务接口，主要进行跨应用数据获取
 * 字典表：T_S_DT_TD_MODE
 */
@RestfulSupported
@GenericPojo(pojo = DttdMode.class)
public interface IDtTdModeDataService extends IControlDataService,
		IKeyConvertDataService, IDataServiceForCache {
	/**
	 * 加载交易方式树形结构
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getTreeDataList()
			throws ServiceException;

	/**
	 * 根据业务类型C_BUSI_TYPE数组 或者 交易方式代码C_DT_CODE数组，获取业务类型，交易方式联合树形结构
	 * @return
	 */
	public <K extends BasePojo> List<K> getTreeDataByTypes(String[] types)
			throws ServiceException;

	/**
	 * 取得所有数据(只包含代码和名称)
	 * 
	 * @author liuxiang
	 * @date 2014-6-10
	 * @param c_BUSI_TYPE
	 *            业务类型
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, String> getShortDataMap(String c_BUSI_TYPE)
			throws ServiceException;
	
	
	/**
	 * 加载分组拆分 交易方式 
	 * By Jinghehe 2015-11-08
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getTreeDataListForRule()
			throws ServiceException;
	
	/**
	 * 根据接口代码查询交易方式树形数据
	 * 恒生交易数据业务分类服务接口
	 * add by liyanjun 2016-2-20 STORY #28608 【广发证券】在分组恒生交易数据文件接口添加控制
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getTreeDataByCfgCode(String[] cfgCodes)
			throws ServiceException;

	/**
	 * add by wangtangyao 20160815 STORY #28887 保险资产证券清算款、其他应收款科目处理
	 * 根据词汇分类，获取非明细业务类型（针对证券清算款非T+1），不从销售方式表中获取
	 * 证券清算款非T+1核算项下拉列表要加载开放申赎业务类型
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getSQKDataListByTypes(String type[])
			throws ServiceException;
	
	/**
	 * add by wangtangyao 20160815 STORY #28887 保险资产证券清算款、其他应收款科目处理
	 * 根据词汇分类，获取非明细业务类型（针对证券清算款非T+1），不从销售方式表中获取
	 * 证券清算款非T+1核算项下拉列表要加载开放申赎业务类型
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<DttdMode> getSQKDataByCode(String[] codes)
			throws ServiceException;
	
	/**
	 * add by yuanyafeng 20180911 STORY #61545 【紧急】太平保险-附件管理优化（二期）
	 * 根据模块功能代码取对应模块的交易方式，没有的返回空
	 * @param funCode 功能代码
	 * @return 交易方式
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<DttdMode> getDataListByFun(String funCode)
			throws ServiceException;

	/**
	 * 先从交易方式缓存中获取数据，如果缓存中无数据
	 * 获取所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return 所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
//	public <K extends BasePojo> List<K> getCacheLists() throws ServiceException;
}
