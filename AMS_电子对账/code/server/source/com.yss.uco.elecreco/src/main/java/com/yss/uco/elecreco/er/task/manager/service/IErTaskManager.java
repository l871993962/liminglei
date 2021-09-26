package com.yss.uco.elecreco.er.task.manager.service;

import com.yss.uco.elecreco.support.bean.ErTask;

public interface IErTaskManager {
	
	/**
	 * 关闭定时任务
	 * @param task	电子对账定时调度任务
	 */
	public void stopTask(ErTask task);
	/**
	 * 任务是否已启动
	 * @param taskCode	任务代码
	 * @return
	 */
	public boolean isStartTask(String taskCode);
	/**
	 * 启动定时任务
	 * @param task	电子对账定时调度任务
	 * @param compulsory 是否强制重启
	 */
	public void startTask(ErTask task,boolean compulsory);
	
}
