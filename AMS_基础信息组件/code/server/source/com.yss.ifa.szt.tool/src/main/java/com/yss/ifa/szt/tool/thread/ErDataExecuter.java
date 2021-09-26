package com.yss.ifa.szt.tool.thread;

import com.yss.ifa.szt.tool.pojo.TransPojo;

/**
 * 对托管行返回的数据对账结果进行处理
 * 
 * @author
 * 
 */
public class ErDataExecuter {

	private static ErDataExecuter executer = null;
//	private Logger logger = LogManager.getLogger(ErDataExecuter.class);

	public static ErDataExecuter newInstance() {
		if (executer == null) {
			executer = new ErDataExecuter();
		}
		return executer;
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * @param data
	 *            返回的数据，解压后的
	 * @param pojo 
	 * @param fUND_ID 托管行返回的资产代码，有可能是拆分代码
	 * @return
	 * @throws Exception
	 */
	public String execute(String data, TransPojo pojo, String fUND_ID, boolean isSplit) throws Exception {
		IErExecuterService service = ErRuleService.getExecuter(data);
		if(isSplit && (service instanceof SplitErExecuterService))
		{
			((SplitErExecuterService) service).initSplitParams(pojo,fUND_ID,isSplit);
		}
		
		if(service instanceof ErExecuterBase){
			ErExecuterBase base = (ErExecuterBase) service;
			base.setPojo(pojo);
			base.setSendStr(data);
		}
		
		boolean isret = !check(data);
		if (isret) {
			//托管行返回的数据，标识发送的数据已经到托管行了，返回托管行正常接收状态或者非正常接收：参数未配置或者没有开通通道
			service.retMsg(data);
		} else {
			//托管行返回的对账结果，一致或者不一致，保存数据库
			service.execute(data);
		}
		return "";
	}


	/**
	 * 判断接口类型
	 * 
	 * @return
	 */
	private boolean check(String data) {
		if (data.toUpperCase().contains("<RECORD>")) {
			return true;
		} else if (data.toUpperCase().contains("<RETCODE>")) {
			return false;
		}
		return true;
	}
}
