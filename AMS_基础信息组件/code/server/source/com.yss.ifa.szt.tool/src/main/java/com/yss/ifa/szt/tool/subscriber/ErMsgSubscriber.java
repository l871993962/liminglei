package com.yss.ifa.szt.tool.subscriber;

import java.util.List;

import com.yss.fast.mq.common.message.MessageExt;
import com.yss.fast.mq.common.remoting.common.RemotingHelper;
import com.yss.fast.mq.support.api.YssMsgListener;
import com.yss.fast.mq.support.consumer.listener.ConsumeConcurrentlyContext;
import com.yss.fast.mq.support.consumer.listener.ConsumeConcurrentlyStatus;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.ifa.szt.tool.cache.ErMsgCacheManager;
import com.yss.ifa.szt.tool.subscriber.cons.Constants;
import com.yss.ifa.szt.tool.subscriber.pojo.ErMsgPojo;

public class ErMsgSubscriber implements YssMsgListener{
	protected Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
			ConsumeConcurrentlyContext context) {
		try{
			if (msgs != null && !msgs.isEmpty()) {
				String syncMsgStr =  new String(msgs.get(0).getBody(), RemotingHelper.DEFAULT_CHARSET);
				logger.debug("ErMsgSubscriber msg:" + syncMsgStr);
				if(!StringUtil.IsNullOrEmpty(syncMsgStr)){
					String action = msgs.get(0).getTags();
					if(Constants.erMsgTimeMillis.equalsIgnoreCase(action)){
						ErMsgPojo erMsgPojo = JsonUtil.toBean(syncMsgStr, ErMsgPojo.class);
						if(null != erMsgPojo){
							ErMsgCacheManager.newInstance().set(erMsgPojo.getPrimaryKey(), erMsgPojo.getTimeMillis(), erMsgPojo.getCatchSaveTime());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.debug("对账数据同步消息出错！",e);
		}
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
