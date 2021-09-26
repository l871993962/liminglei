package com.yss.ams.base.information.support.bi.hday.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hday.pojo.Hday;
import com.yss.framework.api.mvc.biz.IServiceBus;
/**
 * 节假日查询服务类，从组件 com.yss.uco.support中迁移过来，解除流程组件对com.yss.uco.support依赖
 * 主要进行增删改查操作
 * 
 * Added by LP,2016-08-22
 * 
 * */
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
@RestfulSupported
public interface IHdayService extends IServiceBus {
  
  /**
    * 根据节假日群代码查询所有年份
	* code c_HDAY_CODE 节假日代码
	*/
  List<String> getAllYear(String code);

	/**
	  * 根据条件查询符合条件的所有节假日信息
	  * @param hashMap
	  * @return
	  */
  List<String> getAllHoiday(HashMap<String, Object> hashMap);

  /**
   * 根据节假日编码获取节假日的日期
   * @param C_HDAY_CODE
   * @return
   */
  List<Date> getHoidayByCode(String C_HDAY_CODE);
  
  /**
   * 根据节假日编码获取节假日的日期
   * @param C_HDAY_CODE
   * @return
   */
  List<Date> getAuditHoidayByCode(String C_HDAY_CODE);
  
  /**
   * 根据节假日编码和审核状态获取节假日的日期
   * @param C_HDAY_CODE
   * @return
   */
  List<Date> getHoidayByCodeAndCheck(String C_HDAY_CODE, String N_CHECK_STATE);

  /**
	 * 根据条件查询节假日信息记录数
	 * @param hashMap
	 * @return
	 */
  String getSameHoiday(HashMap<String, Object> hashMap);

  /**
   * 根据参数对应条件获取日期
   * @param date
   * @param offset
   * @param hdayCode
   * @param hdayType
   * @return
   */
  String getDay(String date, String offset, String hdayCode, String hdayType);
  

  /**
   * 
   * @Title getWorkDayInMonth 
   * @Description 获取目标月份的第N个工作日日期
   * @author Administrator@ysstech.com
   * @date 2017年7月17日上午11:10:47
   * @param date
   * @param indexDay
   * @param C_HDAY_CODE
   * @return
   * @return String
   */
  String getWorkDayInMonth(String date, int indexDay, String C_HDAY_CODE);
  
  /**
   *  如果date是工作日则返回date,如果date是节假日则返回下一个工作日。
   * add by chenwenhai 20140514 
   * @param specifiedDate：指定日期
   * @param offset:期限
   * @param portCode：组合代码
   * @return 指定日期+期限 （节假日顺延到下个工作日）
   */
  String getWorkday(String specifiedDate,String offset, String portCode);
  
  /***
	 * hwh
	 * 获取已审核节假日代码/名称
	 * STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @return
	 */
  HashMap<String, String> getHdayMap();
	
	/**
	 * STORY #73552 节假日信息设置restful接口 
	 * 根据节假日代码和日期 查询该节假日是否存在
	 * @Date 20190714
	 * @author zuomingke
	 * @param hdayCode
	 * 			节假日代码
	 * @param hday
	 * 			节假日日期
	 * @return Hday
	 * 			节假日
	 */
	public Hday getHdayByHday(String hdayCode,String hday);
	
	/**
	 * STORY #73552 节假日信息设置restful接口 
	 * 根据节假日代码和年或年月查询节假日
	 * @Date 20190714
	 * @author zuomingke
	 * @param hdayCode
	 * 			节假日代码
	 * @param yearOrMonth
	 * 			年或者年月
	 * @return List<Hday>
	 * 			节假日List
	 */
	public List<Hday> getHdayByYearOrMonth(String hdayCode,String yearOrMonth);
}
