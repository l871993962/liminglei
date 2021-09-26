package com.yss.ifa.szt.tool.thread;

import java.util.List;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.msg.MQFactory;
import com.yss.framework.msg.mq.MessageQueueException;
import com.yss.ifa.szt.tool.para.service.IMrInfoService;
import com.yss.ifa.szt.tool.para.service.impl.DzParaService;
import com.yss.ifa.szt.tool.pojo.MrInfo;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.util.ElecMessageBoxUtil;
import com.yss.ifa.szt.tool.zip.BaoWenTool;

/**
 * 伺服器消息接收处理类，用于接收伺服器的消息，并处理 
 * 此类应该单实例存在
 * @author weijj
 * 
 */
public class MessageProcess implements IMessageProcess {
	private int num = 0;
	private Logger logger = LogManager.getLogger(MessageProcess.class);

	/**
	 * 监听器监听到伺服器发送来的数据
	 */
	@Override
	public String receive(String sender, List<TransPojo> list) {
		String data = "";
		// 判断如果是伺服器发送的数据
	    /////添加通道类型  by weijj STORY #24165 20150715 添加MQ类型处理
		if (sender.equalsIgnoreCase(BaoWenTool.MRAPI) || sender.equalsIgnoreCase(MQFactory.IBM_MQ)) {
			num = 0;
			this.isConnect = true;
			ReviceThreadPool.reviceData(list);
		} else if (sender.equalsIgnoreCase(BaoWenTool.YSSUCO) && !isConnect) {
			if (list != null && list.size() > 0) {
				try {
					logger.debug("伺服器没有连通");
					for (TransPojo pojo : list) {
						//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
						//data = BaoWenTool.unZip(pojo.getSendStr());
						//没有加密信息的话，通过用户信息加载
						if(StringUtil.IsNullOrEmptyT(pojo.getSecretType()))
						{
							BaoWenTool.initTransPojo(pojo);
						}
						data = BaoWenTool.unZip(pojo,pojo.getSendStr());
						ErSendExecuter.newInstance().setPojo(pojo);
						ErSendExecuter.newInstance().connectFail(data);
					}
				} catch (Exception e) {
					logger.debug("处理异常数据："+e.getMessage(), e);
				}
			}
		} 
		return null;
	}

	/**
	 * 深证通伺服器是否连通
	 */
	private boolean isConnect = false;

	public boolean isConnect() {
		return isConnect;
	}

	public void setConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}

	@Override
	public String connect(String sender) {
		// 测试连接
		if (sender.equalsIgnoreCase(BaoWenTool.MRAPI)) {
			num = 0;
			this.isConnect = true;
		} else {
			if (num > 2) {
				this.isConnect = false;
			} else {
				num++;
			}
		}
		return null;
	}

	@Override
	public String init(String sender, List<TransPojo> list) {
		// 如果伺服器初始化
		if (sender.equalsIgnoreCase(BaoWenTool.MRAPI)) {
			num = 0;
			this.isConnect = true;
			logger.debug("电子对账接收到伺服器初始化成功消息！");
		}
		return null;
	}

	@Override
	public String start(String sender) {
		// 如果伺服器启动就初始化
		if (sender.equalsIgnoreCase(BaoWenTool.MRAPI)) {
			// //发送初始化动作 查询数据库获得数据，通知伺服器初始化
			List<TransPojo> userList = null;
			try {
				userList = new DzParaService().getInitMrApiData();
			} catch (Exception e) {
				logger.error("获取托管行用户出错：" + e.getMessage());
			}
			BaoWenTool.send(userList, BaoWenTool.YSSUCO, BaoWenTool.INIT);
			logger.debug("电子对账发送初始化消息");
		} else if (sender.isEmpty()) {
			// 如果伺服器没有连接，通知伺服器可以连接了
			List<TransPojo> userList = null;
			try {
				userList = new DzParaService().getInitMrApiData();
			} catch (Exception e) {
				logger.error("获取托管行用户出错：" + e.getMessage());
			}
			BaoWenTool.send(userList, BaoWenTool.YSSUCO, BaoWenTool.START);
			logger.debug("电子对账发送启动消息");
		}
		return null;
	}

	@Override
	public String stop(String sender) {
		if (sender.equalsIgnoreCase(BaoWenTool.MRAPI)) {
			num = 0;
			this.isConnect = false;
			logger.debug("电子对账接收到伺服器停止消息！");
		} else if (sender.isEmpty()) {
			//停止电子对账组件，向伺服器发通知，让伺服器停止
			List<TransPojo> userList = null;
			try {
				userList = new DzParaService().getInitMrApiData();
			} catch (Exception e) {
				logger.error("获取托管行用户出错：" + e.getMessage());
			}
			BaoWenTool.send(userList, BaoWenTool.YSSUCO, BaoWenTool.STOP);
			logger.debug("电子对账发送停止消息！");
		}
		return null;
	}

	@Override
	public String checkMr(String sender, List<TransPojo> list) {
		if(sender.equalsIgnoreCase(BaoWenTool.MRAPI))//收到伺服器的心跳答复
		{
			if(list != null && list.size()>0)
			{
				IMrInfoService mrInfoService = YssServiceFactory.getInstance().createService(IMrInfoService.class);
				TransPojo pojo = list.get(0);
				MrInfo mrInfo = pojo.getMrInfoList().get(0);
				String users = "";
				String status = "";
				if(!StringUtil.IsNullOrEmptyT(pojo.getSendStr()))
				{
					String[] ss = pojo.getSendStr().split(":");
					users = ss[0];
					if(ss.length == 2)
					{
						status = ss[1];
					}
				}
				//-深证通链接检测时：-1： 链路通畅；0：估值到伺服器通畅，伺服器到深圳通不通；1：估值到伺服器不通
				if("-1".equalsIgnoreCase(pojo.getResult()))// 链路通畅
				{
					String message = "伺服器["+mrInfo.getC_Mr_Ip()+":"+mrInfo.getC_Mr_Port()+"]检测结果：通畅!";
					//链路通畅
					try {
						if(!"-1".equalsIgnoreCase(status))
						{
							mrInfo.setN_Link_Status(-1);
							mrInfoService.updateCheckState(mrInfo);
							ElecMessageBoxUtil.sendCheckMrMessage(users,message);
						}
					} catch (MessageQueueException e) {
						logger.error("深证通链路通畅，提醒用户失败：" + e.getMessage());
					}
				}else
				{
					String message = "伺服器["+mrInfo.getC_Mr_Ip()+":"+mrInfo.getC_Mr_Port()+"]检测结果：不通!";
					if("0".equalsIgnoreCase(pojo.getResult()))//估值到伺服器通畅，伺服器到深圳通不通
					{
						message += pojo.getErrInfo();
					}
					try {
						if(!"0".equalsIgnoreCase(status))
						{
							mrInfo.setN_Link_Status(0);
							mrInfoService.updateCheckState(mrInfo);
							ElecMessageBoxUtil.sendCheckMrMessage(users,message);
						}
					} catch (MessageQueueException e) {
						logger.error("深证通链路不通，提醒用户失败：" + e.getMessage());
					}
				}
			}
		}else//发送心跳测试到伺服器
		{
			BaoWenTool.send(list, BaoWenTool.YSSUCO, BaoWenTool.CHECKMR);
		}
		return null;
	}

}
