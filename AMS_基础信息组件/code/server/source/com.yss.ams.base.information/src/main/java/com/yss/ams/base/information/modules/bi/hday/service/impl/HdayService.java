package com.yss.ams.base.information.modules.bi.hday.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.modules.bi.hday.dao.HdayDao;
import com.yss.ams.base.information.modules.bi.hday.dao.HdaySqlBuilder;
import com.yss.ams.base.information.support.bi.hday.pojo.Hday;
import com.yss.ams.base.information.support.bi.hday.service.IHdayService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**
 * 节假日群设置服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@DefaultCacheRefresh(group = CacheGroup.HDAY)
public class HdayService extends ServiceBus<HdayService> implements
		IHdayService {

	private HdayDao serviceDao = null;

	public HdayService() throws Exception {
		serviceDao = new HdayDao(DbPoolFactory.getInstance().getPool(), new HdaySqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 重写删除方法
	 * @param pojoList
	 * @return
	 */
	@Override
	public String deleteById(List<BasePojo> pojoList) {
		saveDelRecord(pojoList);
		return serviceDao.deleteByList(pojoList);
		
	}
	
	/**
	 * code c_HDAY_CODE 节假日代码
	 */
	public List<String> getAllYear(String code){
		return serviceDao.getAllYear(code);
	}

	/**
	  * 根据条件查询符合条件的所有节假日信息
	  * @param hashMap
	  * @return
	  */
	@Override
	public List<String> getAllHoiday(HashMap<String,Object> hashMap) {
		
		return serviceDao.getAllHoiday(hashMap);
	}

	/**
	 * 根据节假日代码查询符合条件的所有节假日日期信息
	 * @param C_HDAY_CODE
	 * @return
	 */
	public List<Date> getHoidayByCode(String C_HDAY_CODE) {
		return serviceDao.getHoidayByCode(C_HDAY_CODE);
	}
	
	/**
	 * 根据节假日代码查询符合条件的所有节假日日期信息
	 * @param C_HDAY_CODE
	 * @return
	 */
	public List<Date> getAuditHoidayByCode(String C_HDAY_CODE) {
		return serviceDao.getAuditHoidayByCode(C_HDAY_CODE);
	}
	
	/**
	 * 根据节假日代码和审核状态查询符合条件的所有节假日日期信息
	 * @param C_HDAY_CODE
	 * @return
	 */
	public List<Date> getHoidayByCodeAndCheck(String C_HDAY_CODE, String N_CHECK_STATE) {
		return serviceDao.getHoidayByCodeAndCheck(C_HDAY_CODE, N_CHECK_STATE);
	}
	
	/**
	 * 根据条件查询节假日信息记录数
	 * @param hashMap
	 * @return
	 */
	@Override
	public String getSameHoiday(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		return serviceDao.getSameHoiday(hashMap);
	}
	
	/**
	 * 当所选的日期区间的节假日全部存在，则抛出记录已经存在的异常
	 * ，如果如期区间中有部分节假日存在，则忽略已经存在的节假日将当前
	 * 不存在的节假日进行保存 
	 * add by fjl 20131223
	 */
	@Override
	public String insert(List<BasePojo> pojoList) {
		try {
			if(pojoList != null && pojoList.size() > 0) {
				// 根据节假日群和日期获取对应的记录是否已经存在
				Map<String, Boolean> isExistMap = serviceDao.getExistMaps(pojoList);
				// 当全部已经存在时则直接执行插入，让系统抛异常
				if(pojoList.size() > isExistMap.keySet().size()) {
					// 如果部分存在则过滤掉已经存在的记录
					for(Iterator<BasePojo> iterator = pojoList.iterator(); iterator.hasNext();) {
						Hday hday = (Hday) iterator.next();
						if(isExistMap.containsKey(hday.getC_HDAY_CODE() + "/" + hday.getD_HDAY())) {
							iterator.remove();
						}
					}
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("节假日群设置：新增节假日群数据失败", e);
		}
		return super.insert(pojoList);
	}
	
	/**
     * 如果date是工作日则返回date,如果date是节假日则返回下一个工作日。
     * add by chenwenhai 20140514 
     * @param specifiedDate：指定日期
     * @param offset:期限
     * @param portCode：组合代码
     * @return 指定日期+期限 （节假日顺延到下个工作日）
     */
    public String getWorkday(String specifiedDate,String offset, String portCode){
    	return serviceDao.getWorkday(specifiedDate, offset, portCode);
    }
	
	/**
     * 根据条件获取日期
     * @param date
     * @param offset
     * @param hdayCode
     * @param hdayType
     * @return
     */
	@Override
	public String getDay(String date, String offset, String hdayCode,
			String hdayType) {
		return serviceDao.getDay(date, offset, hdayCode, hdayType);
	}
	

	@Override
	/**
	 * 获取目标月份的第N个工作日日期
	 * add by lixiang
	 */
	public String getWorkDayInMonth(String date, int indexDay,
			String C_HDAY_CODE) {
		return serviceDao.getWorkDayInMonth(date,indexDay,C_HDAY_CODE);
	}
	
	/***
	 * hwh
	 * 获取已审核节假日代码/名称
	 * STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @return
	 */
	@Override
	public HashMap<String,String> getHdayMap(){
		return serviceDao.getHdayInfo();
	}
	/**
	 * STORY #73552 节假日信息设置restful接口 
	 * add by zuomingke
	 * date 20190713
	 * @return Hday
	 */
	@Override
	public Hday getHdayByHday(String hdayCode ,String hday){
		return serviceDao.queryHdayByHday(hdayCode,hday);
	}
	
	/**
	 * STORY #73552 节假日信息设置restful接口 
	 * add by zuomingke
	 * date 20190713
	 * @return List<Hday>
	 */
	@Override
	public List<Hday> getHdayByYearOrMonth(String hdayCode ,String yearOrMonth){
		
		return serviceDao.queryHdayByYearOrMonth(hdayCode, yearOrMonth);
	}
}
