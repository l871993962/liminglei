package com.yss.uco.elecreco.er.task.service.impl;
import java.util.List;

import com.yss.uco.elecreco.er.task.dao.ErTaskDao;
import com.yss.uco.elecreco.er.task.dao.ErTaskSqlBuilder;
import com.yss.uco.elecreco.er.task.manager.service.IErTaskManager;
import com.yss.uco.elecreco.er.task.manager.service.impl.ThreadPoolErTaskManager;
import com.yss.uco.elecreco.support.bean.ErTask;
import com.yss.uco.elecreco.support.service.IErTaskService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.returninfo.ReturnInfoGenerator;

public class ErTaskService extends ServiceBus<ErTaskService> implements IErTaskService {

	private ErTaskDao serviceDao = null;

	private IErTaskManager taskManager = null;
	
	public ErTaskService() throws Exception {
		serviceDao = new ErTaskDao(DbPoolFactory.getInstance().getPool(),new ErTaskSqlBuilder());
		dao = serviceDao;
		taskManager = ThreadPoolErTaskManager.newInstate();
	}

	@Override
	public void startTask(String taskCode) {
		ErTask task = serviceDao.getErTaskByCode(taskCode);
		taskManager.startTask(task, false);
		this.logger.info("启动电子对账调度任务："+task.toString());
	}

	@Override
	public void stopTask(String taskCode) {
		ErTask task = serviceDao.getErTaskByCode(taskCode);
		taskManager.stopTask(task);
		this.logger.info("关闭电子对账调度任务："+task.toString());
	}

	@Override
	public void startTask(String taskCode, boolean compulsory) {
		ErTask task = serviceDao.getErTaskByCode(taskCode);
		taskManager.startTask(task, compulsory);
		this.logger.info("启动电子对账调度任务："+task.toString());
	}

	@Override
	public List<ErTask> startAllTask() {
		List<ErTask> tasks = serviceDao.getErAllTasks();
		int i = 1;
		while(tasks == null)
		{
			i++;
			this.logger.info("第"+i+"次启动电子对账定时任务！");
			try {
				Thread.sleep(120000L);
			} catch (InterruptedException e) {
				this.logger.error(e.getMessage(), e);
			}
			tasks = serviceDao.getErAllTasks();
		}
		for (ErTask erTask : tasks) {
			startTask(erTask.getC_DV_TASK_CODE());
		}
		this.logger.info("启动电子对账定时任务成功！");
		return tasks;
	}

	@Override
	public List<ErTask> stopAllTask() {
		List<ErTask> tasks = serviceDao.getErAllTasks();
		for (ErTask erTask : tasks) {
			stopTask(erTask.getC_DV_TASK_CODE());
		}
		return tasks;
	}

	@Override
	public ErTask getErTaskByCode(String taskCode) {
		return serviceDao.getErTaskByCode(taskCode);
	}

	@Override
	public String updateTaskByCode(ErTask task) {
		if(!StringUtil.IsNullOrEmptyT(task.getC_DV_TASK_CODE()))
		{
			ErTask erTask = serviceDao.getErTaskByCode(task.getC_DV_TASK_CODE());
			if(erTask == null)
			{
				serviceDao.insert(task);
			}else
			{
				task.setId(erTask.getId());
				serviceDao.updateById(task);
			}
			startTask(task.getC_DV_TASK_CODE(), true);
		}
		return ReturnInfoGenerator.getUpdateOKStr(menuId);
	}
	
}