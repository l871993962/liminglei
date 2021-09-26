package com.yss.uco.elecreco.er.task.manager.service.impl;

import com.yss.uco.elecreco.er.task.command.impl.ErMrCheck;
import com.yss.uco.elecreco.er.task.command.service.IErTaskCommand;
import com.yss.uco.elecreco.er.task.cons.TaskCodeCons;
import com.yss.uco.elecreco.er.task.manager.pojo.Jop;
import com.yss.uco.elecreco.er.task.manager.service.IJopService;
import com.yss.uco.elecreco.support.bean.ErTask;

public class JopService implements IJopService {

	@Override
	public Jop getJopByTask(ErTask task) {
		Jop jop = new Jop();
		jop.setTask(task);
		jop.setCommand(getCommand(task));
		return jop;
	}
	
	private IErTaskCommand getCommand(ErTask task)
	{
		IErTaskCommand command = null;
		if(TaskCodeCons.ER_MR_CHECK.equals(task.getC_DV_TASK_CODE()))
		{
			command = new ErMrCheck(task);
		}
		return command;
	}

}
