package com.yss.ifa.szt.tool.msgcenter;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.msg.MQFactory;
import com.yss.framework.msg.mq.MessageQueueException;
import com.yss.framework.msg.mq.TopicNames;

/**
 * 电子对账启动发送服务
 * 
 * @author weijj
 * 
 */
public class DzdzPublisher {
	private static MQFactory mqFactory = null;
	private static Logger logger = LogManager.getLogger(DzdzPublisher.class);
	private static List<String> msgList = new CopyOnWriteArrayList<String>();
	private static List<String> ibmmqList = new CopyOnWriteArrayList<String>();
	private static TopicNames sTopicName = TopicNames.DZDZ;
	private static boolean flag = false;
	private DzdzPublisher(){
	}

	public static void start() {
		try {
			/**
			 * 防止重复调用产生多个线程
			 */
			if (!flag) {
				flag = true;
				mqFactory = MQFactory.getInstance(TopicNames.DZDZ,TopicNames.DZDZR);
				mqFactory.start();
				Thread t = new Thread(new SendTask());
				t.start();
			}
			
		} catch (Exception e) {
			logger.log("启动电子对账发布者出错:" + e.getMessage(),e);
		}
	}

	public static void stop() {
		flag = false;
	}

	public static void send(String content) throws MessageQueueException {
		msgList.add(content);
	}
	 ////添加通道类型  by weijj STORY #24165 20150715 MQ 与伺服器放到不同的list中，使用相同的线程发送
	public static void sendByIbmMQ(String content) throws MessageQueueException {
		ibmmqList.add(content);
	}
	
	// //防止发送太慢，超时，先把数据放到池子里，再发。
	static class SendTask implements Runnable {
		@Override
		public void run() {
			while (flag) {
				if (msgList.size() > 0) {
					//STORY31411电子对账连接逻辑修改。在伺服器端增加一个消息中心，用于接收socket请求
					DzdzSendPublisher sendPublisher = null;
					try {
						sendPublisher = new DzdzSendPublisher(sTopicName);
					} catch (MessageQueueException e) {
						logger.log("mq发送类DzdzSendPublisher创建出错：", e);
					}
					logger.debug("Mq发送数量：" + msgList.size());
					Iterator<String> iter = msgList.iterator();
					while (iter.hasNext()) {
						String message = iter.next();
						try {
//							mqFactory.send(message, MQFactory.YSS_MQ);
							if (sendPublisher != null) {
								sendPublisher.send(message);
							}
							msgList.remove(message);
						} catch (Exception e) {
							logger.log("发送 失败:" + message, e);
							//							msgList.clear();
						} 
					}
					if(sendPublisher != null){
						sendPublisher.close();
					}
				} else if(ibmmqList.size() > 0){
					logger.debug("ibmMq发送数量：" + ibmmqList.size());
					Iterator<String> iter = ibmmqList.iterator();
					while (iter.hasNext()) {
						String message = iter.next();
						try {
							if(mqFactory == null){
								mqFactory = MQFactory.getInstance(TopicNames.DZDZ,TopicNames.DZDZR);
								mqFactory.start();
							}
							mqFactory.send(message, MQFactory.IBM_MQ);
							ibmmqList.remove(message);
						} catch (Exception e) {
							logger.log("ibmMq发送失败:" + message, e);
//							ibmmqList.clear();
						} 
					}
				}
				else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						logger.log("SendTask线程睡眠意外：", e);
						Thread.currentThread().interrupt();
					}
				}
			}
			logger.log(" SendTask 线程关闭");
		}
	}

}
