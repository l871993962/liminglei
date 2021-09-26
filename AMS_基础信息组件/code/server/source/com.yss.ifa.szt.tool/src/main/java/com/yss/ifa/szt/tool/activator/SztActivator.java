package com.yss.ifa.szt.tool.activator;

import org.osgi.framework.BundleContext;

import com.yss.fast.mq.common.exception.MQClientException;
import com.yss.fast.mq.support.api.IConsumer;
import com.yss.fast.mq.support.constants.TopicConstants;
import com.yss.fast.mq.support.reqvo.ConsumerReqInfo;
import com.yss.framework.api.bundle.BaseApplicationActivator;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.ifa.szt.tool.log.MessageLogFactory;
import com.yss.ifa.szt.tool.subscriber.ErMsgSubscriber;
import com.yss.ifa.szt.tool.subscriber.cons.TopicCons;
import com.yss.ifa.szt.tool.thread.DzdzMgr;

public class SztActivator extends BaseApplicationActivator {
	private Logger logger = LogManager.getLogger(SztActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}
	
	private void doStartDzdzMgr() {
		//STORY41692估值系统，伺服器发送接收日志单独存放，不输出至log日志中
		Thread logThread = new Thread(MessageLogFactory.getInstance(), "MessageLogFactory");
		logThread.start();
		logger.log("深证通记录日志线程启动成功！");
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				DzdzMgr.newInstence().start();
				logger.log("深证通消息监听启动成功！");
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}
	
	/**
	 * BUG #357085 [300.7]static相关代码修改以便支持集群部署
	 */
	private void consumer(){
		try {
			// 消费者服务
			IConsumer consumerService = (IConsumer) YssServiceFactory.getInstance().createService(IConsumer.class);
			// 配置主题和监听器
			ConsumerReqInfo req = new ConsumerReqInfo(TopicCons.ER_INFO.toString(), new ErMsgSubscriber());
			// 监听器上线
			consumerService.consumeBroadCastMsg(req);
		} catch (MQClientException e) {
			LogManager.getLogger(getClass()).error("对账数据创建消息总线监听失败", e);
		}
	}

	@Override
	protected void doStop(BundleContext context) {
		// TODO Auto-generated method stub
		DzdzMgr.newInstence().stop();
	}

	@Override
	protected void registerBundleOperInterface(BundleContext context) {
		// TODO Auto-generated method stub

	}

	/**
	 * BUG266596深证通伺服器YssMrApi3.6版本目前存在的两个问题【生产优化】 放到组件加载完成后
	 */
	@Override
	protected void afterStart(BundleContext bundleContext) {
		consumer();
		doStartDzdzMgr();
	}

	@Override
	protected void registerManageInterface(BundleContext context) {
		// TODO Auto-generated method stub
		
	}
}
