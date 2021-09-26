package com.yss.ams.syncdata.mq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.yss.ams.syncdata.activator.SyncDataActivator;
import com.yss.ams.syncdata.consts.TopicAndTagCons;
import com.yss.ams.syncdata.modules.base.dao.SyncDataDao;
import com.yss.ams.syncdata.modules.base.dao.SyncDataSqlBuilder;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncData;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncInfo;
import com.yss.ams.syncdata.support.modules.base.pojo.XMLSyncData;
import com.yss.ams.syncdata.util.SyncDataUtils;
import com.yss.fast.mq.client.api.IConsumer;
import com.yss.fast.mq.client.api.YssMsgListener;
import com.yss.fast.mq.client.consumer.ConsumerImpl;
import com.yss.fast.mq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.yss.fast.mq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.yss.fast.mq.client.reqvo.ConsumerReqInfo;
import com.yss.fast.mq.common.FastMqConfig;
import com.yss.fast.mq.common.exception.MQClientException;
import com.yss.fast.mq.common.message.MessageExt;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.util.DateUtil;

/**
 * 数据同步消息监听
 * @author Administrator
 *
 */
public class SyncDataConsumer{
	private Logger logger = LogManager.getLogger(SyncDataConsumer.class);
	static int groupName=0;
	static AtomicInteger count=new AtomicInteger(1);
	static long beginTime;
	static XMLSyncData syncDataIp = null;
	static List<String> funcodes = new ArrayList<String>();
	static SyncDataDao serviceDao = null;
	
	public SyncDataConsumer() throws MQClientException, YssException{
		 FastMqConfig.init();
		 syncDataIp = SyncDataUtils.getSyncDataIp();	
		 
		 serviceDao = new SyncDataDao(YssDbPoolFactory.getInstance().getDbPool(SyncDataActivator.class),
					new SyncDataSqlBuilder());
			
		 //根据业务系统code获取该业务系统监听的功能模块
		 if (syncDataIp != null) {			 
			 funcodes = serviceDao.getModuleCfg(syncDataIp.getSystemCode());
		 }
	}
	
	/**
	 * 启动消息监听
	 * @throws Exception
	 */
	public void startConsumer() throws Exception{
		 if(syncDataIp != null && syncDataIp.getStartSync() != null && syncDataIp.getStartSync().equalsIgnoreCase("true") 
				 && syncDataIp.getAddress() != null){
			 if(StringUtil.IsNullOrEmptyT(syncDataIp.getAddress())){
			     return;	 
			 }
			 
			 if(StringUtil.IsNullOrEmptyT(syncDataIp.getSystemCode())){
			     return;	 
			 }
			 
			 FastMqConfig.setNamesrvAddress(syncDataIp.getAddress());
			 fastmqconsumer(syncDataIp.getSystemCode());
		 }
	}
	 
	/**
	 * 消息监听
	 * @param systemCode
	 * @throws Exception
	 */
	private void fastmqconsumer(String systemCode) throws Exception{
		 IConsumer c=new ConsumerImpl();
		 ConsumerReqInfo req=new ConsumerReqInfo(TopicAndTagCons.SyncData,new YssMsgListener(){
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> msgs,
					ConsumeConcurrentlyContext context) {
				try{
					String tag = msgs.get(0).getTags();
					//System.out.println("数据同步原始消息功能代码：="+tag);
					logger.log("数据同步原始消息功能代码：="+tag);
					if(!funcodes.contains(tag)){
						return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
					}
					
					String body=new String(msgs.get(0).getBody());
					//System.out.println("数据同步接收到消息tag+body->"+ tag + ":" + body);
					logger.log("数据同步接收到消息tag+body->"+ tag + ":" + body);
					SyncInfo syncInfo = new SyncInfo();
					syncInfo = JsonUtil.toBean(body, SyncInfo.class);
					
					SyncData syncData = new SyncData();
					//消息状态
					syncData.setC_DV_STATE("RECEIVED");
					//接收时间
					syncData.setC_RECEIVE_TIME(DateUtil.getNow(DateUtil.FORMAT_ONE));
					//发送时间
					syncData.setC_SEND_TIME(syncInfo.getSendTime());
					//发送人
					syncData.setC_SENDER(syncInfo.getSender());
					//数据ID
					syncData.setC_DATA_ID(syncInfo.getDataId());
					//操作类型
					syncData.setC_DV_OPER_TYPE(syncInfo.getDataStatus());
					//同步系统
					syncData.setC_SYSTEM_CODE(syncDataIp.getSystemCode());
					//同步模块code
					syncData.setC_DV_MODULE_CODE(syncInfo.getFunCode());
					//消息
					syncData.setC_MESSAGE(syncInfo.getTheme());
					
					//保存
					serviceDao.insert(syncData);
					
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;					
				}
				catch(Exception e){
					//e.printStackTrace();
					logger.error("数据同步消息处理异常");
					return ConsumeConcurrentlyStatus.RECONSUME_LATER;
				}
			}});
		 req.setInstanceName(TopicAndTagCons.SyncData.toString()+systemCode);
		 c.consumeBroadCastMsg(req);
//		 c.consumeQueueMsg(req);
	}
}
