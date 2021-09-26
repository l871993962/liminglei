package com.yss.uco.elecreco.er.mrapi.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ifa.szt.tool.log.ExeTypeEnum;
import com.yss.ifa.szt.tool.log.MessageLogFactory;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.zip.BaoWenTool;
import com.yss.uco.elecreco.er.erbbinfo.dao.ErBbInfoDao;
import com.yss.uco.elecreco.er.erbbinfo.dao.ErBbInfoSqlBuilder;
import com.yss.uco.elecreco.er.erinfostate.service.ErStepStateService;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErStepState;

/**发送线程类
 * 从等待发送的数据池中获取数据发给伺服器，如果没有数据就进入等待状态
 * @author Orlando
 *
 */
public class SendThread implements Callable<Object> {
	
	private Logger logger = LogManager.getLogger(getClass());
	/**
	 * 线程名字
	 */
	private String name = "";
	/**
	 * 线程开关
	 */
	private boolean flag = true;
	
	private ErBbInfoDao serviceDao = new ErBbInfoDao(DbPoolFactory
			.getInstance().getPool(), new ErBbInfoSqlBuilder());
	//STORY41692估值系统，伺服器发送接收日志单独存放，不输出至log日志中
	//STORY #65624电子对账收发报文路径支持可配置
	//private static String logFileName = new FileStorePathUtil(YssConstant.YSSPERIPHERAL).getFilePath();
	/**
	 * 构造函数
	 * @param num
	 * @param sender
	 */
	public SendThread(int num) {
		this.name = "SendThread-" + num;
	}

	@Override
	public Object call() throws Exception {
		
		List <ErBbInfo> dataList = new ArrayList<ErBbInfo>();
		List<TransPojo> sendList = null;
		Thread.currentThread().setName(name);
		while (flag) {
			//Orlando 20150824 线程未处理异常，导致所有线程全部挂起，这里捕获后输出日志
			try{
				////获取当前线程发送的主数据
				dataList = SendThreadPool.getThreadDataList();
				if (dataList.size() > 0) {
					//从数据库中查出明细数据并发送到伺服器
					sendList = serviceDao.getSendData(dataList);
					if (sendList.size() > 0) {
						//发送前对发送状态进行记录
						ErStepStateService erStepService = new ErStepStateService();
						for (ErBbInfo erBbInfo : dataList) {
							ErStepState erStepState = erStepService.buildPojo(erBbInfo.getC_ASS_CODE(),erBbInfo.getC_SN(),erBbInfo.getC_FILE_TYPE(),erBbInfo.getC_RPT_TYPE(),
									erBbInfo.getC_STATE(),erBbInfo.getD_DATE(),"发送深证通");
							if (null == erStepState) {
								continue;
							}
							erStepService.insertPojo(erStepState);
						}
						//STORY41692估值系统，伺服器发送接收日志单独存放，不输出至log日志中
						//STORY #65624电子对账收发报文路径支持可配置
						//MessageLogFactory.getInstance().addMessage(sendList, logFileName, ExeTypeEnum.SEND, DateUtil.dateToString(new Date(), MessageLogFactory.FORMAT_DATETIME));
						MessageLogFactory.getInstance().addMessageWithConfig(sendList, ExeTypeEnum.SEND, DateUtil.dateToString(new Date(), MessageLogFactory.FORMAT_DATETIME));
						BaoWenTool.send(sendList, BaoWenTool.YSSUCO,BaoWenTool.SEND);
					}
//					dataList.clear();
				} else {
					Thread.sleep(1000);
				}
			}
			catch(Throwable ex){
				logger.error("发送电子对账数据到消息中心出错:" + ex.getMessage(), ex);
			}
		}
		return null;
	}
	
	
	public void stop(){
		flag = false;
		SendThreadPool.signalAll();
	}
}
