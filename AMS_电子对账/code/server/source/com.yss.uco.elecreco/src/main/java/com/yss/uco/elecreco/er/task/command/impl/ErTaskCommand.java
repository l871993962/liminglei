package com.yss.uco.elecreco.er.task.command.impl;

import com.yss.uco.elecreco.er.task.command.service.IErTaskCommand;
import com.yss.uco.elecreco.support.bean.ErTask;
import com.yss.uco.elecreco.support.service.IErTaskService;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;

public abstract class ErTaskCommand implements IErTaskCommand {
	protected Logger logger = LogManager.getLogger(ErTaskCommand.class);
	protected ErTask task = null;
	protected IErTaskService taskService = null;
	public ErTaskCommand(ErTask task)
	{
		this.task = task;
		taskService = YssServiceFactory.getInstance().createService(IErTaskService.class);
	}
	@Override
	public String toString() {
		return "ErTask[任务代码:" + task.getC_DV_TASK_CODE() + "运行频率：" + task.getN_RUN_INTERVAL() + "]";
	}
}
