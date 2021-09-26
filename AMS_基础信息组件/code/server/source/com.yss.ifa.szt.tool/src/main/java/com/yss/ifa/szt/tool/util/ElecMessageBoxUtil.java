package com.yss.ifa.szt.tool.util;

import java.util.List;

import com.yss.framework.api.app.ApplicationWrapper;
import com.yss.framework.api.common.co.Application;
import com.yss.framework.api.common.co.User;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.dataservice.IUserDataService;
import com.yss.framework.api.msgcenter.UserInfoProducer;
import com.yss.framework.api.msgcenter.UserMessage;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.msg.mq.MessageQueueException;
import com.yss.ifa.szt.tool.activator.SztActivator;
/**
 * 向消息盒子发送消息
 * @author Lenovo
 *
 */
public class ElecMessageBoxUtil {
	
//	public final static int MESSAGE_TYPE_FAIL_SFQ = 1;
//	
//	public final static int MESSAGE_TYPE_FAIL_SZT = 0;
//	
//	public final static int MESSAGE_TYPE_SUCCESS = -1;
//	/**
//	 * 记录上次检测的状态
//	 */
//	private static int lastCheckFlag = 9999;
	
	
	/**
	 * 发送消息给指定用户
	 * @param users
	 * @param message
	 * @throws MessageQueueException
	 */
	public synchronized static void sendCheckMrMessage(String users, String message) throws MessageQueueException 
	{
		if(users == null || "".equalsIgnoreCase(users))
		{
			return;
		}
		String userCodes[] = users.split(",");
		if(userCodes == null || userCodes.length == 0)
		{
			return;
		}
//		if(lastCheckFlag == messageType)
//		{
//			return;
//		}
//		lastCheckFlag = messageType;
		Context context = YssContextFactory.getInstance().getAppContext(SztActivator.class);
		Application app =  ((ApplicationWrapper)context.getLocalAppInfo()).getNativeApplication();
		UserMessage msg = UserInfoProducer.createMessage(message, app, null, "");
		IUserDataService userDataService = YssServiceFactory.getInstance().createService(IUserDataService.class);
		List<User> userInfos = userDataService.getDataListByKeys(userCodes);
		// 发送消息给指定岗位，弹出泡泡
		UserInfoProducer.getInstance().sendtoUsers(msg, userInfos);    
	}
	
}
