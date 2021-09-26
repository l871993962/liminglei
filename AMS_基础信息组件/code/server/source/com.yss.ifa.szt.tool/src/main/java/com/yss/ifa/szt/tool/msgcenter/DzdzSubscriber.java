package com.yss.ifa.szt.tool.msgcenter;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.RestfulConfigServiceImpl;
import com.yss.framework.msg.MQFactory;
import com.yss.framework.msg.mq.TopicNames;
import com.yss.ifa.szt.tool.thread.IMessageProcess;

/**
 * 电子对账启动监听接收
 * @author weijj
 *
 */
public class DzdzSubscriber {
//添加通道类型  by weijj STORY #24165 20150715 MQFactory里面已经提供 
//	private TopicSubscriber topicSubscriber = null;

	public DzdzSubscriber(IMessageProcess messageProcess) throws YssException {
		try {
			DzdzListener dzdzListener = DzdzListener.newInstance();
//			this.topicSubscriber = new TopicSubscriber(TopicNames.DZDZR);
//			this.topicSubscriber.setListener(listener);
			//BUG #300869 【300.7】由于需要给银华需要提供负载均衡部署方案，调整电子对账的代码为队列模式的消费实现
			if(new RestfulConfigServiceImpl().getConfig().isShell()) {
				// 壳模式下使用广播消费逻辑
				MQFactory.getInstance(TopicNames.DZDZ,TopicNames.DZDZR).setListener(dzdzListener);
			}else {
				MQFactory.getInstance(TopicNames.DZDZ,TopicNames.DZDZR).setQueueListener(dzdzListener);
			}
			
			dzdzListener.setMessageProcess(messageProcess);
			DzdzPublisher.start();
		} catch (Exception ex) {
			throw new YssException("实例化对账主题监听器出错。", ex);
		}
	}
	
//
//	public TopicSubscriber getTopicSubscriber() {
//		return MQFactory.getInstance(TopicNames.DZDZR);
//	}
}
