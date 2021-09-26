package com.yss.uco.elecreco.er.task.command.impl;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.msg.mq.MessageQueueException;
import com.yss.ifa.szt.tool.para.service.IMrInfoService;
import com.yss.ifa.szt.tool.pojo.ActionPojo;
import com.yss.ifa.szt.tool.pojo.MrInfo;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.util.ElecMessageBoxUtil;
import com.yss.ifa.szt.tool.zip.BaoWenTool;
import com.yss.uco.elecreco.support.bean.ErTask;

public class ErMrCheck extends ErTaskCommand {
	
	public final static int MR_MESSAGE_FAIL_SFQ = 1;
	
	public final static int MR_MESSAGE_FAIL_SZT = 0;
	
	public final static int MR_MESSAGE_SUCCESS = -1;
	
	protected IMrInfoService mrInfoService = null;
	
	public ErMrCheck(ErTask task) {
		super(task);
		mrInfoService = YssServiceFactory.getInstance().createService(IMrInfoService.class);
	}

	@Override
	public void run() {
		String runInfo = "执行成功！";
		int status = 1;//记录运行状态
		//需要捕获异常，否则会中断任务的运行
		try{
			doBus();
		}catch(Exception e)
		{
			status = 0;
			runInfo = e.getMessage();
			this.logger.error("深证通链路检测失败！"+e.getMessage(), e);
		}
		task.setN_RUN_STATE(status);
		task.setC_RUN_INFO(runInfo);
		taskService.updateById(task);
	}
	
	private void doBus() throws MessageQueueException
	{
		List<MrInfo> mrs = getParaMrs();
		for (MrInfo mrInfo : mrs) {
			boolean isOk = false;
			TransPojo trans = new TransPojo();
			List<TransPojo> list = new ArrayList<TransPojo>();
			List<MrInfo> mrlist = new ArrayList<MrInfo>();
			mrlist.add(mrInfo);
			trans.setMrInfoList(mrlist);
			trans.setSendStr(task.getC_CALL_USER()+":"+mrInfo.getN_Link_Status());
			list.add(trans);
			ActionPojo pojo = new ActionPojo();
			pojo.setSender(BaoWenTool.YSSUCO);
			pojo.setAction(BaoWenTool.CHECKMR);
			pojo.setDataList(list);
			pojo.setKey("");
			String sendStr = JsonUtil.toString(pojo);
			isOk = BaoWenTool.sendMessage(mrInfo.getC_Mr_Ip(), mrInfo.getC_Mr_Port(), sendStr);
			if(!isOk)//发送伺服器不成功，飘窗提醒
			{
				String message = "伺服器["+mrInfo.getC_Mr_Ip()+":"+mrInfo.getC_Mr_Port()+"]检测结果：不通!连接不到伺服器，请检查！";
				//飘窗
				//状态没有变化不提醒
				if(MR_MESSAGE_FAIL_SFQ != mrInfo.getN_Link_Status())
				{
					mrInfo.setN_Link_Status(MR_MESSAGE_FAIL_SFQ);
					mrInfoService.updateCheckState(mrInfo);
					ElecMessageBoxUtil.sendCheckMrMessage(task.getC_CALL_USER(),message);
				}
			}
		}
		
	}
	
	/***
	 * 获取伺服器IP
	 * @return
	 */
	private List<MrInfo> getParaMrs() {
		return mrInfoService.queryAllMrInfos();
	}
}
