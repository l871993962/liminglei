package com.yss.uco.elecreco.support.service;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.support.bean.ErTask;

@RestfulSupported
public interface IErTaskService extends IServiceBus  {
	/**
	 * 启动定时任务
	 * @param taskCode
	 */
	public void startTask(String taskCode);
	/**
	 * 关闭定时任务
	 * @param taskCode
	 */
	public void stopTask(String taskCode);
	/**
	 * 启动定时任务
	 * @param taskCode	任务代码
	 * @param compulsory	是否强制重启
	 */
	public void startTask(String taskCode,boolean compulsory);
	/**
	 * 启动所有未启动的定时任务
	 * @return	启动的任务代码
	 */
	public List<ErTask> startAllTask();
	/**
	 * 关闭所有启动的定时任务
	 * @return	关闭的任务代码
	 */
	public List<ErTask> stopAllTask();
	/**
	 * 通过任务代码获取电子对账任务
	 * @param taskCode
	 * @return
	 */
	public ErTask getErTaskByCode(String taskCode);
	/**
	 * 通过任务代码更新电子对账任务
	 * 不存在，新建一条
	 * 会触发任务重启
	 * @param task
	 * @return
	 */
	public String updateTaskByCode(ErTask task);
	
}