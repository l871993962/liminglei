package com.yss.ifa.szt.tool.rptlog.service;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface IErRptLogService extends IServiceBus  {
	/**
	 * 删除指定天数前的数据
	 * @param day 日志保留天数
	 */
	public void deleteRptLog(int day);
	
	/**
	 * 根据id查询报文数据
	 * @param id
	 * @return 解密过的报文日志
	 */
	String queryLogById(String id);

	/**
	 * 更新报文序号
	 * @param oldSn
	 * @param newSn
	 * @return 
	 */
	void updateSN(String oldSn, String newSn);
}