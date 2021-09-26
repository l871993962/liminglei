package com.yss.ams.base.information.support.bi.mkt.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 节假日数据服务接口，主要进行跨应用数据获取
 *
 * @author yuankai 公共信息拆分 2017.5.31
 */
@RestfulSupported
public interface IMktHDayDataService extends IDataService {

	/**
	 * 获取节假日群数据
	 * 
	 * @return HashMap<String, String>
	 * @throws YssException
	 */
	public HashMap<String, String> getMarketHDaysData() throws YssException;
	
	/**
	 * 获取所有节假日信息
	 * 
	 * @return HashMap<String,HashMap<Integer,List<Date>>>
	 * @throws YssException
	 */
	@LinkControllerMethod(value = "getHolidays")
	public HashMap<String,HashMap<Integer,List<Date>>> getHolidays() throws YssException;

	/**
	 * 根据节假日群代码获取节假日
	 * 
	 * @param code 节假日群代码
	 * @return <"节假日群代码",<"年份",节假日列表>>
	 * @throws YssException
	 */
	@LinkControllerMethod(value = "getHolidays", arguTypes = String.class)
	public HashMap<String, HashMap<Integer, List<Date>>> getHolidays(String code)
			throws YssException;
}
