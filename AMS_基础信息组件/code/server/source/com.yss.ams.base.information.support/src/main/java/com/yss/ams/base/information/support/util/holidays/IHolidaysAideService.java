package com.yss.ams.base.information.support.util.holidays;

import java.util.Date;

import com.yss.ams.base.information.support.util.holidays.vo.HolidaysAideVo;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 节假日的功能接口，根据系统节假日设置提供辅助获取的方法
 * 
 */
@RestfulSupported
public interface IHolidaysAideService {
	
	/**
	 * 根据日期和节假日群代码判断该参数日期是否为节假日
	 *    
	 * @param date 指定日期
	 *        holidaysCode 节假日代码
	 * @return boolean
	 * @throws YssException
	 */
	@LinkControllerMethod(value="isHoliday",arguTypes = HolidaysAideVo.class)
	public boolean isHoliday(@LinkControllerMethodArgu("date")Date date, @LinkControllerMethodArgu("holidaysCode")String holidaysCode)throws YssException;
	
	/**
	 * 根据指定日期、偏移量和节假日代码获取工作日日期
	 *    
	 * @param specifiedDate 指定日期
	 *        offset 偏移量
	 *        holidaysCode 节假日代码
	 * @return Date
	 * @throws YssException
	 */
	@LinkControllerMethod(value="getWorkDayByHolidayCode",arguTypes = HolidaysAideVo.class)
	public Date getWorkDayByHolidayCode(@LinkControllerMethodArgu("specifiedDate")Date specifiedDate, @LinkControllerMethodArgu("offset")int offset, @LinkControllerMethodArgu("holidayCode")String holidayCode) throws YssException;
	
	/**
	 * Author : ChenLong
	 * Date   : 2017-03-23
	 * Status : Add
	 * Comment: 标的期货合约交割月份前一个月的第5个交易日.
	 * 标的期货合约交割月份取文件中的‘标的合约’的后4位数字中的最后2位。特别注意如果后2位为01时，前一个月是上一前度12月。上一年度取后4位数字中的前2位。例如M1801,上一个月为2017年12月   
	 * @param bdhy
	 * @param marketCode
	 * @return Date
	 * @throws YssException
	 */
	public Date getLastTradeDate(String bdhy,String marketCode,String bdpz) throws YssException;
	
	/**
	 * 根据相关参数条件获取工作日
	 * 
	 * @Title getWorkDay 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年4月19日下午4:22:19
	 * @param specifiedDate 指定日期
	 * @param holidaysCode 节假日代码
	 * @param offset 偏移量
	 * @return
	 * @throws YssException
	 * @return Date
	 */
	@LinkControllerMethod(value="getWorkDay",arguTypes = HolidaysAideVo.class)
	public Date getWorkDay(@LinkControllerMethodArgu("specifiedDate")Date specifiedDate,@LinkControllerMethodArgu("holidaysCode")String holidaysCode, @LinkControllerMethodArgu("offset")int offset) throws YssException;
}