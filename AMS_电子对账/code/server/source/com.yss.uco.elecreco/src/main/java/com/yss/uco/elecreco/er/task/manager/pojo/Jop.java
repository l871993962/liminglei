package com.yss.uco.elecreco.er.task.manager.pojo;

import java.util.concurrent.ScheduledFuture;
import com.yss.uco.elecreco.er.task.command.service.IErTaskCommand;
import com.yss.uco.elecreco.support.bean.ErTask;

public class Jop {
	/**
	 * 电子对账定时调度任务
	 */
	private ErTask task = null;
	/**
	 * 定时任务执行的命令
	 */
	private IErTaskCommand command = null;
	/**
	 * 任务执行对象，可用来取消，关闭任务
	 */
	private ScheduledFuture<?> future = null;
	
	public ErTask getTask() {
		return task;
	}
	public void setTask(ErTask task) {
		this.task = task;
	}
	public IErTaskCommand getCommand() {
		return command;
	}
	public void setCommand(IErTaskCommand command) {
		this.command = command;
	}
	public ScheduledFuture<?> getFuture() {
		return future;
	}
	public void setFuture(ScheduledFuture<?> future) {
		this.future = future;
	}
	
}
