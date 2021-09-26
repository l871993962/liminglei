package com.yss.ifa.szt.tool.log;

import java.util.regex.Pattern;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.DateUtil;
import com.yss.ifa.szt.tool.pojo.ErRptLog;
import com.yss.ifa.szt.tool.rptlog.service.IErRptLogService;

public class DBLogOper {
	private Logger logger = LogManager.getLogger(DBLogOper.class);
	private IErRptLogService logService = null;
	private DBLogOper()
	{
		logService = YssServiceFactory.getInstance().createService(IErRptLogService.class);
	}
	
	private static class SingletonHolder{
		private static final DBLogOper DB_LOG_OPER= new DBLogOper();
	}
	
	private String lastDeleTime = "";
	
	public static DBLogOper getInstance()
	{
		return SingletonHolder.DB_LOG_OPER;
	}
	
	private synchronized boolean isDeleteLog()
	{
		String now = DateUtil.getNow("yyyyMMdd");
		if(!lastDeleTime.equalsIgnoreCase(now))
		{
			lastDeleTime = now;
			return true;
		}
		return false;
	}

	public void insertRptLog(ErRptLog log,Object dayNum) {
		if(dayNum != null)//指定
		{
			String value = String.valueOf(dayNum).trim();
			if("Y".equalsIgnoreCase(value))
			{
				//永久保留
				logService.insert(log);
			}else if("N".equalsIgnoreCase(value))
			{
				//不保留，不删除以前的日志,不做操作
			}else if(Pattern.matches("\\d+", value))
			{
				if(isDeleteLog())
				{
					logService.deleteRptLog(Integer.parseInt(value));
				}
				logService.insert(log);
			}else//非法字符默认3天
			{
				this.logger.error("电子对账报文保存天数配置有误，采用默认配置，保留3天！");
				if(isDeleteLog())
				{
					logService.deleteRptLog(3);
				}
				logService.insert(log);
			}
		}
	}
}
