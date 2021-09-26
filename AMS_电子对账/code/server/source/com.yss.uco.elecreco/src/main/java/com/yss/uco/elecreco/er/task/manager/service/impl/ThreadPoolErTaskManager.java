package com.yss.uco.elecreco.er.task.manager.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.yss.uco.elecreco.er.task.manager.pojo.Jop;
import com.yss.uco.elecreco.er.task.manager.service.IErTaskManager;
import com.yss.uco.elecreco.er.task.manager.service.IJopService;
import com.yss.uco.elecreco.support.bean.ErTask;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
/**
 * 单例
 * @author Lenovo
 *
 */
public class ThreadPoolErTaskManager implements IErTaskManager {
	
	private Logger logger = LogManager.getLogger(ThreadPoolErTaskManager.class);
	
	private Map<String,Jop> jops = null;
	
	private ScheduledThreadPoolExecutor pool = null;
	
	private IJopService jopService = null;
	
	private final static ThreadPoolErTaskManager manager = new ThreadPoolErTaskManager();
	
	private ThreadPoolErTaskManager()
	{
		jopService = new JopService();
		pool = new ScheduledThreadPoolExecutor(5);
		jops = new HashMap<String,Jop>();
	}
	
	public static ThreadPoolErTaskManager newInstate()
	{
		return manager;
	}

	@Override
	public synchronized void stopTask(ErTask task) {
		this.logger.info("停止任务前:"+getPoolInfo(pool));
		String taskCode = task.getC_DV_TASK_CODE();
		Jop jop = jops.get(taskCode);
		if(jop != null)
		{
			boolean cancel = jop.getFuture().cancel(false);
			pool.purge();
			if(!cancel)
			{
				logger.error("停止任务失败，无法终止定时任务！");
				return;
			}
			jops.remove(taskCode);
		}
		this.logger.info("停止任务后:"+getPoolInfo(pool));
	}

	@Override
	public synchronized boolean isStartTask(String taskCode) {
		return jops.containsKey(taskCode);
	}

	@Override
	public synchronized void startTask(ErTask task, boolean compulsory) {
		this.logger.info("添加任务前:"+getPoolInfo(pool));
		Jop jop = jopService.getJopByTask(task);
		boolean checkJop = checkJop(jop);
		if(!checkJop)
		{
			this.logger.error("请检查电子对账定时任务配置！");
			return;
		}
		String taskCode = task.getC_DV_TASK_CODE();
		if(isStartTask(taskCode))
		{
			if(!compulsory)
			{
				return;
			}else
			{
				stopTask(task);
			}
		}
		ScheduledFuture<?> future = pool.scheduleWithFixedDelay(jop.getCommand(), 0, jop.getTask().getN_RUN_INTERVAL(), TimeUnit.MINUTES);
		jop.setFuture(future);
		jops.put(task.getC_DV_TASK_CODE(), jop);
		this.logger.info("添加任务后:"+getPoolInfo(pool));
	}
	
	private synchronized boolean checkJop(Jop jop)
	{
		if(jop.getCommand() != null)
		{
			return true;
		}
		return false;
	}
	
	private String getPoolInfo(ScheduledThreadPoolExecutor pool)
	{
		StringBuffer sb = new StringBuffer();
		Iterator<Runnable> iterator = pool.getQueue().iterator();
		sb.append("待执行队列数量：").append(pool.getQueue().size());
		while(iterator.hasNext())
		{
			sb.append("[");
			ScheduledFuture<?> next = (ScheduledFuture<?>) iterator.next();
			sb.append(next.toString());
			sb.append("]");
		}
		return sb.toString();
	}

}
