package com.yss.ams.product.information.modules.ab.port.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.msg.TopicPublisher;
import com.yss.framework.msg.mq.MessageQueueException;
import com.yss.framework.msg.mq.TopicNames;

public class PortAlterPublisher {

	private Logger logger = LogManager.getLogger(this.getClass());
	private TopicPublisher publisher = null;

	public PortAlterPublisher() {
		try {
			publisher = new TopicPublisher(TopicNames.PRODUCT_INFO);
		} catch (MessageQueueException e) {
			//e.printStackTrace();
			logger.log("实例化消息生产者实例出错。", e);
		}
	}

	/**
	 * 发送任务启动消息
	 * @param pojoList 投资组合集合
	 * @param state 操作状态
	 */
	public void send(List<BasePojo> pojoList, ProductInfoState state) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state.toString());
		map.put("pojoList", pojoList);
		
		this.send(JsonUtil.toString(map));
	}
	
	/**
	 * 发送任务启动消息
	 * @param message 投资组合 实例的json形式
	 * @throws MessageQueueException
	 */
	public void send(String message){
		try{
			publisher.send(message);
		}catch(MessageQueueException ex){
			try{
				publisher = new TopicPublisher(TopicNames.PRODUCT_INFO);
				publisher.send(message);
			}
			catch(Exception e){
				Logger logger = LogManager.getLogger(getClass());
				logger.log("任务主题发送创建失败："+ex.getMessage()+e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 新产品成立操作状态
	 * 增、删、改、查、审核、反审核
	 */
	public enum ProductInfoState {
		
		INSERT,
		UPDATE,
		DELETE,
		SELECT,
		AUDLI,
		UNAUDLI;
		
	}
	
}
