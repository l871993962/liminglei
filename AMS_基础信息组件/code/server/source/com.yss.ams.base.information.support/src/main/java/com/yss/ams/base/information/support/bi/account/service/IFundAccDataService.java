package com.yss.ams.base.information.support.bi.account.service;

import java.util.List;

import com.yss.ams.base.information.support.bi.account.pojo.AreaCity;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * 资金账户信息数据服务
 * 
 * @desc 实现数据的转换,下拉框功能
 * @author meip
 * @date 2014-10-08
 * @version V4.5.0.1
 */
/**
 * <产品账户信息>数据服务接口，主要进行跨应用数据获取
 * 
 * Added by shiliang,产品管理组件拆分2017-06-23
 * 
 * */
@RestfulSupported
@GenericPojo(pojo = FundAcc.class)
public interface IFundAccDataService extends IDataService,IControlDataService,IKeyConvertDataService,IDataServiceForCache{
	
	/**
	 * 根据机构代码获取产品账户信息数据
	 * 
	 * @param orgCode 机构代码
	 * @return List 产品账户信息数据列表
	 * @throws ServiceException
	 */
	<K extends BasePojo> List<K> getDataListByOrg(String orgCode)
			throws ServiceException;
	
	/**
	 * 根据组合代码获取产品账户信息数据
	 * 
	 * @param orgCode 机构代码
	 * @return List 产品账户信息数据列表
	 * @throws ServiceException
	 */
	<K extends BasePojo> List<K> getAllDataByPort(String portCode)
			throws ServiceException;
	
	/**
	 * 根据组合代码获取产品账户信息数据
	 * 
	 * @param portCode 组合代码
	 * @return List 产品账户信息数据列表
	 * @throws ServiceException
	 */
	<K extends BasePojo> List<K> getDataListByPort(String portCode)
			throws ServiceException;
	
	/**
	 * 根据资产代码获取产品账户信息数据
	 * 
	 * @param assCode 资产代码
	 * @return List 产品账户信息数据列表
	 * @throws ServiceException
	 */
	<K extends BasePojo> List<K> getDataListByAssCode(String assCode)
			throws ServiceException;
	
	/**
	 * 根据主键ID获取产品账户信息数据
	 * 
	 * @param ids C_IDEN
	 * @return List 产品账户信息数据列表
	 * @throws ServiceException
	 */
	<K extends BasePojo> List<K> getDataListByIds(String ids)
			throws ServiceException;
	
//	public <K extends BasePojo> String insert(K pojo)
//			throws ServiceException;
	public List<FundAcc> getAccNameAndCaCode();
}
