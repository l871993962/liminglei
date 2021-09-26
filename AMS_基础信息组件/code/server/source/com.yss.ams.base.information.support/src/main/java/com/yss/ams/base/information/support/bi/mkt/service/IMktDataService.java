package com.yss.ams.base.information.support.bi.mkt.service;


import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * 交易市场设置数据服务接口类，主要进行跨应用数据获取
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@RestfulSupported
@GenericPojo(pojo=Mkt.class)
public interface IMktDataService extends IDataService,
		IControlDataService,IKeyConvertDataService,IDataServiceForCache {
	/**
	 * 从缓存中取得所有交易市场数据(只包含代码和名称)
	 * @author liuxiang
	 * @date 2014-6-9
	 * @return
	 */
	public HashMap<String, String> getShortDataMap() throws ServiceException;
	
	/**
	 * 根据市场代码取值
	 * @author dingshalu
	 * @date 2015-8-5
	 * @return
	 */
	public <K extends BasePojo> List<K> getAllDataSqlByKeys(String[] keys)
			throws ServiceException;
	
	/**
	 * 关联结算机构查询交易市场
	 * by guohui 2016-12-09 STORY36399【招商基金】【紧急】股票、债券、基金等交易流水界面增加“交易市场”字段供TB导出接口取对应的清算信息
	 * @param <K>
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListAux() throws ServiceException;
	
	/**
	 * 资讯等组件的SQLBuilder调用，返回查询所有交易市场数据的SQL语句
	 * @author 马向峰 20170622
	 * @return
	 */
	public String getAllDataSql();
}
