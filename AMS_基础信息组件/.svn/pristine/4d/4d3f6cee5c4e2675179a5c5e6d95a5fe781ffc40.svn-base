package com.yss.ifa.szt.tool.msgcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.yss.framework.api.cache.fomp.FompDistributedLock;
import com.yss.framework.api.cache.fomp.IFompDistributedLock;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.restful.RestfulConfigServiceImpl;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.msg.MQFactory;
import com.yss.framework.msg.mq.TopicListener;
import com.yss.ifa.szt.tool.pojo.ActionPojo;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.thread.ErRuleService;
import com.yss.ifa.szt.tool.thread.IErExecuterService;
import com.yss.ifa.szt.tool.thread.IMessageProcess;
import com.yss.ifa.szt.tool.thread.ReviceThread;
import com.yss.ifa.szt.tool.zip.BaoWenTool;

/**
 * 电子对账消息管理器
 * 
 * @author weijj
 * 
 */
public class DzdzListener implements TopicListener {

	private static DzdzListener dzdzListener = null;
	public IMessageProcess messageProcess = null;
	private static Logger logger = LogManager.getLogger(DzdzListener.class);
	
	/**
     * 操作分布式锁
    */
   private IFompDistributedLock lock=new FompDistributedLock();

	public static DzdzListener newInstance() {
		if (dzdzListener == null) {
			try {
				dzdzListener = new DzdzListener();
			} catch (Exception e) {
				logger.log("创建DzdzListener出错:" + e.getMessage(), e);
			}
		}
		return dzdzListener;
	}

	/**
	 * 接收到报文系统传来的数据 msg 深证通数据
	 * 返回时MQ类型封装到和伺服器一样的结构
	 */
	@Override
	public void doMessage(String msg) {
		try {
			if (msg == null || msg.trim().length() == 0) {
				return;
			}
			///添加通道类型  by weijj STORY #24165 20150715  判断是否是伺服器返回的数据，根据字符串结构判断
			if (msg.trim().startsWith("{") && msg.trim().endsWith("}")) {
				ActionPojo pojo = JsonUtil.toBean(msg, ActionPojo.class);
				if (pojo != null) {
					switch (pojo.getAction()) {
					case BaoWenTool.START:
						messageProcess.start(pojo.getSender());
						break;
					case BaoWenTool.INIT:
						messageProcess.init(pojo.getSender(), pojo.getDataList());
						break;
					case BaoWenTool.SEND:
						 if(new RestfulConfigServiceImpl().getConfig().isShell()) {
							 //先判断所在服务内存中是否包含了该文件的执行类
							 List<TransPojo> dataList = new ArrayList<TransPojo>();
							 if(null != pojo.getDataList()){
								 ReviceThread rt = new ReviceThread();
								 for (TransPojo transPojo : pojo.getDataList()) {
									 String data = rt.decryptStr(transPojo);
									 if(ErRuleService.hasExecuter(data)){
										 dataList.add(transPojo);
									 }
								}
							 }
							 
							 if(dataList.size() > 0){
								 String lockKey = "executor/"+AppContext.getInstance().getCurrentNode().getName() + "/" + pojo.getKey();
	                             try {
	                                 if(lock.tryLock(lockKey,TimeUnit.MINUTES,30,0)) {
	                                     messageProcess.receive(pojo.getSender(), dataList);
	                                 }else {
	                                     logger.warn("没有获取到分布式锁,丢弃"+lockKey);
	                                 }
	                             }catch(Throwable ex) {
	                                 logger.error("获取分布式锁失败",ex);
	                             }
							 }else{
								 logger.warn("没有找到IErExecuterService接口的实现类,key:"+pojo.getKey());
							 }
                         }else{
                        	 messageProcess.receive(pojo.getSender(), pojo.getDataList());
                         }
						break;
					case BaoWenTool.STOP:
						messageProcess.stop(pojo.getSender());
						break;
					case BaoWenTool.CHECKMR:
						messageProcess.checkMr(pojo.getSender(),pojo.getDataList());
						break;
					default:
						messageProcess.connect(pojo.getSender());
						break;
					}
				}
			} else {
				//////返回时MQ类型封装到和伺服器一样的结构，后面调用相同的方法处理
				List<TransPojo> list = new ArrayList<TransPojo>();
				TransPojo tempPojo = new TransPojo();
				tempPojo.setSendStr(msg);
				tempPojo.setResult("-1");
				list.add(tempPojo);
				messageProcess.receive(MQFactory.IBM_MQ, list);
			}
		} catch (Exception e) {
			logger.log("错误：" + msg, e);
		}
	}

	public void setMessageProcess(IMessageProcess messageProcess) {
		this.messageProcess = messageProcess;
	}

}
