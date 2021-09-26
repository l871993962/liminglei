package com.yss.uco.elecreco.er.task.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.YssCons;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.service.IErBbInfoService;

/**
 * 电子对账定时发送任务
 * STORY #35703 估值表自检以及自动生成发送电子对账
 * @author wlx 
 *
 */
public class ErSendTask extends TimerTask{

	/**
	 * 
	 */
	private ErSendTimer sendTimer = null;
	/**
	 * 最多执行次数
	 */
	private int maxRunTime = 1;
	/**
	 * 当前执行次数
	 */
	private int currTime = 0;
	
	private Connection conn = null;
	
	private ErBbInfo erInfo = null;
	
	private IErBbInfoService erBbInfoService = YssServiceFactory.getInstance().createService(IErBbInfoService.class);
	
	private List<ErBbInfo> dataList = new ArrayList<ErBbInfo>();
	
	private Logger logger = LogManager.getLogger(ErSendTask.class);
	
	/**
	 * 发送结果
	 */
	private HashMap<String, Object> sendStatusMap = new HashMap<String, Object>();
	
	public ErSendTask(ErSendTimer sendTimer, int maxRunTime, Connection conn, ErBbInfo erInfo){
		this.sendTimer = sendTimer;
		this.maxRunTime = maxRunTime;
		this.conn = conn;
		this.erInfo = erInfo;
		dataList.add(erInfo);
	}
	@Override
	public void run() {
		 try {
			//BUG #187793::【鹏华基金】统计分析自动发送电子对账反馈失败  设置等待时间为1秒时，都认为成功
			if(sendTimer.getPeriod() == 1000){
				sendStatusMap.put("POJO", erInfo);
				sendStatusMap.put("STATUS", YssCons.YSS_DBUPDATE_SUCCESS);
				sendTimer.getTimer().cancel();
				sendTimer.setSendStatusMap(sendStatusMap);
				return;
			}
			currTime++;
			String status = getSendStatus();
			// edit by huangjin 2017-1-5 STORY35703估值表自检以及自动生成发送电子对账 回归测试问题 currTime等于的时候会把最后的那一次发送取消。
			if(currTime > maxRunTime || YssCons.YSS_DBUPDATE_SUCCESS.equalsIgnoreCase(status)){
				sendTimer.getTimer().cancel();
				sendTimer.setSendStatusMap(sendStatusMap);
			}else{
				erBbInfoService.sendBbInfo(dataList);
			}
		 } catch (Exception e) {
            logger.error("ErSendTask error", e);
		    sendStatusMap.put("POJO", erInfo);
            sendStatusMap.put("STATUS", YssCons.YSS_DBUPDATE_FAIL);
            sendStatusMap.put("ERRINFO", e.getMessage());
            sendTimer.getTimer().cancel();
            sendTimer.setSendStatusMap(sendStatusMap);
         }catch(Throwable e){
            logger.error("ErSendTask error", e);
		    sendStatusMap.put("POJO", erInfo);
            sendStatusMap.put("STATUS", YssCons.YSS_DBUPDATE_FAIL);
            sendStatusMap.put("ERRINFO", e.getMessage());
            sendTimer.getTimer().cancel();
            sendTimer.setSendStatusMap(sendStatusMap);
         }
	}
	
	private String getSendStatus(){
		PreparedStatement pst = null;
		ResultSet rs = null;
		String Status = "";
		try {
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT O.C_STATE,O.C_ERR_INFO FROM T_D_ER_INFO O WHERE O.C_IDEN = ?");
			pst = conn.prepareStatement(sqlBuilder.toString());
			
			pst.setString(1, erInfo.getId());
			rs = pst.executeQuery();
			while(rs.next()){
				Status = rs.getString(1);
				String errInfo = rs.getString(2) == null?"":rs.getString(2);
				if("ER_ACCECPED".equalsIgnoreCase(Status) || "ER_IDENTICAL".equalsIgnoreCase(Status) || "ER_SENDED_SECCUSS".equalsIgnoreCase(Status) || "ER_SENDED".equalsIgnoreCase(Status)){
					Status = YssCons.YSS_DBUPDATE_SUCCESS;
				}else{
					Status = YssCons.YSS_DBUPDATE_FAIL;
					sendStatusMap.put("ERRINFO", errInfo);
				}
				sendStatusMap.put("POJO", erInfo);
				sendStatusMap.put("STATUS", Status);
			}
		} catch (SQLException e) {
			logger.error("ErSendTask getSendStatus error", e);
			throw new ServiceException(e);
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return Status;
	}

}
