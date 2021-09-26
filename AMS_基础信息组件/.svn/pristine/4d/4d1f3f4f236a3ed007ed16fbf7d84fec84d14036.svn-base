package com.yss.ams.base.information.modules.bi.account.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.account.dao.AreaCityDao;
import com.yss.ams.base.information.modules.bi.account.dao.AreaCitySqlBuilder;
import com.yss.ams.base.information.support.bi.account.pojo.AreaCity;
import com.yss.ams.base.information.support.bi.account.service.IAreaCityDataService;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.biz.ServiceBus;


/**
 * 
 * @ClassName AreaCityService
 * @Description 区域信息服务实现类
 * @author liangyilin
 * @CreateDate 2017年7月20日上午10:43:27
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class AreaCityDataService extends ServiceBus<AreaCity> implements IAreaCityDataService {
	
	private AreaCityDao areaDao = null;
	
	public AreaCityDataService(){
		areaDao = new AreaCityDao(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), new AreaCitySqlBuilder());
		dao = areaDao;
	}
	
	/**
	 * 
	 * @Title getKeyConvertMap 
	 * @Description 查询省份城市K-V
	 * @author hehonghui@ysstech.com
	 * @date 2017年11月29日下午7:06:28
	 * @return
	 * @throws Exception
	 * @return HashMap<String,String>
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		keyValueMap = areaDao.getKeyConvertMap();
		return keyValueMap;
	}
	
	/**
	 * 查询城市信息
	 * STORY #45935 招行银企要支持省市设置
	 * 2018-5-16 BUG203043招行银企直联-分行号格式不对导致银行账户查询失败
	 * 针对招行的城市做特殊处理，把城市名称中的'市'后面截掉。例如：南京市--> 南京
	 * @param accNo
	 * @return
	 */
	@Override
	public String queryAccCityByAccNo(String accNo) {
		return areaDao.queryAccCityByAccNo(accNo);
	}
	
	/**
	 * 查询省份信息
	 * STORY #45935 招行银企要支持省市设置
	 * 2018-5-16 BUG203043招行银企直联-分行号格式不对导致银行账户查询失败
	 * @param accNo
	 * @return
	 */
	@Override
	public String queryAccProvinceByAccNo(String accNo) {
		return areaDao.queryAccProvinceByAccNo(accNo);
	}
}
