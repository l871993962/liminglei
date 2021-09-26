package com.yss.ifa.szt.tool.msgcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.yss.fast.mq.common.ServiceState;
import com.yss.fast.mq.support.api.IProducer;
import com.yss.fast.mq.support.producer.SendResult;
import com.yss.fast.mq.support.producer.SendStatus;
import com.yss.fast.mq.support.reqvo.ProducerReqInfo;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.msg.client.MessageProducer;
import com.yss.framework.msg.client.SocketFactory;
import com.yss.framework.msg.mq.MessageQueueException;
import com.yss.framework.msg.mq.TopicNames;
import com.yss.framework.msg.server.Message;

public class DzdzProducer {
	private MessageProducer producer;
	private String topic = "";
	private SocketFactory factory = null;
	private Logger log = LogManager.getLogger(this.getClass());
	private String remoteAddr = "";
	private ProducerReqInfo producerReqInfo = null;
	private IProducer mqProducer = null;
	private AtomicLong lastSendTime = new AtomicLong(System.currentTimeMillis());
	public AtomicLong getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(AtomicLong lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public DzdzProducer(TopicNames topicName,String ip, String port) throws MessageQueueException {
		topic = topicName.toString();
		if (AppContext.getInstance().getUseFastMq()) {//消息总线模式
			remoteAddr = ip + ":" + port;
			mqProducer = YssServiceFactory.getInstance().createService(IProducer.class);
		}else{
			try {
				factory = new SocketFactory(ip,Integer.parseInt(port));
				MessageProducer producer = factory.createProducer(topic);
				this.producer = producer;
			} catch (Exception ex) {
				log.log(ex.getMessage(), ex);
				throw new MessageQueueException(ex);
			}
		}
	}

	public boolean send(String msg) throws MessageQueueException {
		boolean sendSucc = true;
		if (AppContext.getInstance().getUseFastMq()) {//消息总线模式
//			IFrameworkPublisher ifc = YssServiceFactory.getInstance().createService(IFrameworkPublisher.class);
//			ifc.sendToSpecificFastMq(msg, topic, remoteAddr);
			//BUG272910深证通伺服器给估值反馈响应消息，报No buffer space available (maximum connections reached?)
			try {
				if(producerReqInfo == null){
					producerReqInfo = new ProducerReqInfo(topic);
					producerReqInfo.setRemoteAddr(remoteAddr);
					producerReqInfo.setProducer(mqProducer.start(producerReqInfo));
				}else{
					if(producerReqInfo !=null && producerReqInfo.getProducer()!= null && producerReqInfo.getProducer().getDefaultMQProducerImpl() !=null){
						ServiceState serverState = producerReqInfo.getProducer().getDefaultMQProducerImpl().getServiceState();
						if(serverState == ServiceState.SHUTDOWN_ALREADY
								||serverState == ServiceState.START_FAILED){
							log.log(remoteAddr + "消息总线状态为：" + serverState);
							producerReqInfo = new ProducerReqInfo(topic);
							producerReqInfo.setRemoteAddr(remoteAddr);
							producerReqInfo.setProducer(mqProducer.start(producerReqInfo));
						}
					}
				}
				List<String> msgList = new ArrayList<String>();
				msgList.add(msg);
				producerReqInfo.setMsgs(msgList);
				SendResult sendResult = mqProducer.produceMultiMsg(producerReqInfo);
				if(sendResult != null){
					if(SendStatus.SEND_OK == sendResult.getSendStatus()){
						sendSucc = true;
					}else {
						sendSucc = false;
					}
				}else {
					
				}
				msgList.clear();
				msgList = null;
			} catch (Exception ex) {
//				log.log(ex.getMessage(), ex);
				throw new MessageQueueException("消息发送到主题【" + topic
						+ "】失败" + ex.getMessage(), ex);
			}
		}else{
			Message message = null;
			try {
				message = producer.createMessage();
				message.setMsg(msg);
				producer.send(message);
			} catch (Exception ex) {
//				try {
//					this.producer.close(true);
//				} catch (Exception e) {
//					log.log(e.getMessage(), e);
//				}
//				log.log(ex.getMessage(), ex);
				throw new MessageQueueException("消息发送到主题【" + topic
						+ "】失败" + ex.getMessage(), ex);
			}
		}
		return sendSucc;
	}
	public void close(){
		if(AppContext.getInstance().getUseFastMq()){//消息总线模式
			if(producerReqInfo != null){
				try {
					mqProducer.shutdown(producerReqInfo.getProducer());
				} catch (Exception e) {
					log.log(e.getMessage(), e);
				}
			}
		}else {
			try {
				this.producer.close(true);
			} catch (Exception e) {
				log.log(e.getMessage(), e);
			}
		}
	}
}
