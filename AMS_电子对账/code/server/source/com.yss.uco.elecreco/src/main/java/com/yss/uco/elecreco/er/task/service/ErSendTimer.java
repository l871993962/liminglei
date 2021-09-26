package com.yss.uco.elecreco.er.task.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.Callable;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.uco.elecreco.support.bean.ErBbInfo;

/**
 * 电子对账发送数据定时器
 * STORY #35703 估值表自检以及自动生成发送电子对账
 * @author wlx
 *
 */
public class ErSendTimer implements Callable<HashMap<String, Object>>{
	/**
	 * 
	 */
	private Timer timer = null;
	/**
	 * 发送结果
	 */
	private HashMap<String, Object> sendStatusMap = null;
	/**
	 * 延迟多长时间执行  单位毫秒
	 */
	private long delay = 1L;
	/**
	 * 执行频率  单位毫秒
	 */
	private long period = 1L;
	/**
	 * 最大发送次数
	 */
	private int maxRunTime = 0;
	
	private Connection conn = null;
	
	private ErBbInfo erInfo = null;
	
	private Logger logger = LogManager.getLogger(ErSendTimer.class);
	
	public ErSendTimer(long delay, long period, int maxRunTime, Connection conn, ErBbInfo erInfo){
		this.delay = delay;
		this.period = period;
		this.maxRunTime = maxRunTime;
		this.conn = conn;
		this.erInfo = erInfo;
	}

	public HashMap<String, Object> getSendStatusMap() {
		return sendStatusMap;
	}

	public void setSendStatusMap(HashMap<String, Object> sendStatusMap) {
		this.sendStatusMap = sendStatusMap;
	}

	public Timer getTimer() {
		return timer;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	@Override
	public HashMap<String, Object> call() throws Exception {
		timer = new Timer();
		ErSendTask task = new ErSendTask(this, maxRunTime, conn, erInfo);
		timer.scheduleAtFixedRate(task, delay, period);
		long sleep = 2000L;
		long loop = calcLoop(maxRunTime, period, sleep);
		for (int i = 0; i < loop; i++) {
			try {
				if(sendStatusMap != null){
					break;
				}
				Thread.sleep(sleep);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} catch (Throwable e){
			    logger.error(e.getMessage(), e);
			}
		}
		return sendStatusMap;
	}
	
	/**
	 * 根据发送次数和时长计算循环次数
	 * @param maxRunTime
	 * @param period
	 * @return
	 */
	private long calcLoop(int maxRunTime, long period, long sleep) {
		long loop = 0;
		try {
			long frequency = maxRunTime * period;
			if(frequency > 0) {
				loop = frequency/2000L;
			}
		}catch(Exception e) {
			logger.error("calcLoop error", e);
		}
		logger.info("calcLoop loop:" + loop);
		return loop;
	}
}
