package com.yss.uco.elecreco.er.task.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.support.bean.ErTask;
import com.yss.uco.elecreco.support.controller.IErTaskServiceController;
import com.yss.uco.elecreco.support.service.IErTaskService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class ErTaskServiceControllerImpl extends
		AbstractBaseServiceBusController<ErTask, IErTaskService> implements
		IErTaskServiceController {

	@Override
	public void startTask(String taskCode) {
		getService().startTask(taskCode);
	}

	@Override
	public void stopTask(String taskCode) {
		getService().stopTask(taskCode);
	}

	@Override
	public void startTask(String taskCode, boolean compulsory) {
		getService().startTask(taskCode, compulsory);
	}

	@Override
	public List<ErTask> startAllTask() {
		return getService().startAllTask();
	}

	@Override
	public List<ErTask> stopAllTask() {
		return getService().stopAllTask();
	}

	@Override
	public ErTask getErTaskByCode(String taskCode) {
		return getService().getErTaskByCode(taskCode);
	}

	@Override
	public String updateTaskByCode(ErTask task) {
		return getService().updateTaskByCode(task);
	}

}