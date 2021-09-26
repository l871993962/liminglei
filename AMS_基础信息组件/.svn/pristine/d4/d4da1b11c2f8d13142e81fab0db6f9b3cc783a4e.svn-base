package com.yss.ifa.szt.tool.thread;

import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.ifa.szt.tool.msgcenter.DzdzPublisher;
import com.yss.ifa.szt.tool.msgcenter.DzdzSubscriber;

public class DzdzMgr {
	private Logger logger = LogManager.getLogger(DzdzMgr.class);
	private static final DzdzMgr dzdz = new DzdzMgr();
	private MessageProcess messageProcess;
	private DzdzSubscriber dzdzSub = null;

	public static DzdzMgr newInstence() {
		return dzdz;
	}

	private DzdzMgr() {
		messageProcess = new MessageProcess();
		try {
			//启动电子对账发送、接收数据服务
			this.dzdzSub = new DzdzSubscriber(messageProcess);
			ReviceThreadPool.start();
		} catch (YssException e) {
			logger.log("电子对账管理器创建失败 ："+e.getMessage(), e);
		}
	}

	public void start() {
		if(dzdzSub == null){
			try {
				this.dzdzSub = new DzdzSubscriber(messageProcess);
				ReviceThreadPool.start();
				logger.debug("电子对账管理器已启动");
			} catch (YssException e) {
				logger.log("电子对账管理器启动失败 ："+ e.getMessage(), e);
			}
		}
		
		if (!isConnect()) {
			messageProcess.start("");
		}
	}

	public void stop() {
		DzdzPublisher.stop();
		ReviceThreadPool.stop();
		messageProcess.stop("");
		dzdzSub = null;
		logger.debug("电子对账管理器已停止");
	}

	public boolean isConnect() {
		if (messageProcess != null) {
			return messageProcess.isConnect();
		} else {
			return false;
		}
	}
}
