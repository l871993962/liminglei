package com.yss.uco.elecreco.er.task.service;

import java.util.HashMap;



import com.yss.framework.api.busoperservice.IBusiness;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.task.api.I_Task;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.util.DateUtil;
import com.yss.fast.task.support.task.support.pojo.BaseTask;
import com.yss.uco.elecreco.support.service.IElecTaskService;

/**
 * 201507278 added by liubo.STORY #24163 #26344任务调度日志修改成前台可查看
 * STORY55472日常运营可以配置电子对账发送
 * 调度方案处理 电子对账发送
 * 电子对账的自动处理实现类
 */
public class TaskSendDzdz extends BaseTask implements I_Task
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1009136628174394537L;

	@Override
	public HashMap<String, Object> buildParams(String execOperCode) throws Exception 
	{
		HashMap<String, Object> hmData = new HashMap<String, Object>();
		hmData.put("ARRAY_C_PORT_CODE", listToString(ports));
		hmData.put("ARRAY_C_DZ_CODE", listToString(items));
		hmData.put("D_START_DATE", DateUtil.dateToString(dDate, "yyyy-MM-dd"));
		hmData.put("D_END_DATE", DateUtil.dateToString(dDate, "yyyy-MM-dd"));
		hmData.put("C_OPER_CODE", execOperCode);
		hmData.put("C_DISPATCH_ID", execOperCode);
		hmData.put("C_FUN_CODE", "elecSend");
		hmData.put("C_IDF_CODE", StringUtil.IsNullOrEmpty(idfCode) ? "自动任务"
				: idfCode);
		hmData.put("C_RELA_ID", StringUtil.IsNullOrEmpty(funRelaId) ? " "
				: funRelaId);
		hmData.put("OPER_TYPE", "SEND");
		hmData.put("AUTOSEND","1");
		return hmData;
	
	}

	@Override
	public void execute(HashMap<String, Object> params) throws Exception 
	{
		IElecTaskService service = YssServiceFactory.getInstance().createService(IElecTaskService.class);
		service.init(params);
		service.doBusOper(params);
		this.listRecord = ((IBusiness)service).getListRecord();
	}

	@Override
	public void destrory() {
		// TODO Auto-generated method stub
		
	}

}
