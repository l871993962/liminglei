package com.yss.ams.base.information.support.bi.tdchan.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.tdchan.pojo.TdChan;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
@GenericPojo(pojo=TdChan.class)
public interface ISecSeatDataService extends IDataService,
		IControlDataService,IKeyConvertDataService,IDataServiceForCache {
	public <T extends BasePojo> List<T> getTdChanDataList() throws Exception;
	public QueryRes getTdChanDataListRes() throws Exception;
	
	/**
	 * 取得所有数据(只包含代码和名称)
	 * @author liuxiang
	 * @date 2014-6-9
	 * @return
	 */
	public HashMap<String, String> getShortDataMap() throws ServiceException;
	
	/**
	 * 插入数据到交易渠道信息表
	 * @param pojo
	 * @return
	 * @throws ServiceException
	 */
	public String insertTdChan(BasePojo pojo) throws ServiceException;
	
	/**
	 * 查询最大交易渠道代码
	 * @return 查询最大交易渠道代码
	 * @throws ServiceException
	 */
	public String getMaxTdChanCode() throws ServiceException;
	
	/**
	 * 根据机构代码获取交易席位对象
	 * @param <K>
	 * @param orgCode 机构代码
	 * @return
	 * @throws Exception
	 */
	public BasePojo getDataByOrgCode(String orgCode)
			throws ServiceException;
	
	
	/**
	 * By Jinghehe 2015-9-29 
	 * 获取所有渠道数据，包括ALL 构造的数据
	 * @param types
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataListByComm(String[] types) throws ServiceException;
	/**	
	 * 根据组合代码获取组合对应席位
	 * zhoushuhang 2016-4-7  在ETF补票日期界面中增加补票方式。通过选择组合带出组合对应补票席位。
	 * @param portCode 
	 * @return
	 */
	public List<BasePojo> queryPortRelaTradeSeat(String portCode);
	
	/**	
	 * 根据组合代码获取组合对应席位,以及所有机构信息
	 * zouyuan 20170913  BUG #172866 【加急】【南方基金】支持场外申赎业务流水的EXCEL格式的导入--存在问题汇总
	 * @param portCode 
	 * @return
	 */
	public List<BasePojo> getDataListByPort(String[] portCode);
	/**
	 * 根据机构代码获取交易席位对象
	 * @param <K>
	 * @param orgCode 机构代码
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByPorts(String[] types)
			throws ServiceException;
}
