package com.yss.ams.base.information.modules.bi.account.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.account.dao.AreaCityDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 
 * @ClassName AreaCityDataMapAdmin
 * @Description 省份城市转换类型设置
 * @author hehonghui@ysstech.com
 * @CreateDate 2017年11月29日下午7:03:42
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class AreaCityDataMapAdmin extends BaseAdmin{
	
	AreaCityDao svcDao = null;
	
	public AreaCityDataMapAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new AreaCityDao(pool, sqlBuilder);
	}
	
	/**
	 * 
	 * @Title getKeyConvertMap 
	 * @Description 获取省份城市的K-V
	 * @author hehonghui@ysstech.com
	 * @date 2017年11月29日下午7:04:57
	 * @return
	 * @throws Exception
	 * @return HashMap<String,String>
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}
	
	/**
	 * 
	 * @Title getKeyConvertMap 
	 * @Description 通过代码获取省份城市对应的K-V
	 * @author hehonghui@ysstech.com
	 * @date 2017年11月29日下午7:04:57
	 * @return
	 * @throws Exception
	 * @return HashMap<String,String>
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return svcDao.getKeyConvertMap(listKey);
	}
}
