package com.yss.ifa.szt.tool.thread;

import com.yss.ifa.szt.tool.pojo.TransPojo;


/**
 * 将接收到的伺服器返回数据更新数据库状态
 * 
 * @author weijj
 * 
 */
public class ErSendExecuter {
	private static ErSendExecuter executer = null;
//	private Logger logger = LogManager.getLogger(ErSendExecuter.class);

	private ErSendExecuter() {
	}

	public static ErSendExecuter newInstance() {
		if (executer == null) {
			executer = new ErSendExecuter();
		}
		return executer;
	}

	private TransPojo pojo = null;

	public void setPojo(TransPojo pojo) {
		this.pojo = pojo;
	}

	/**连不上伺服器或者深圳通，修改状态和数据的提示信息，此提示信息是我信自己定义的，状态为发送失败
	 * @param data
	 * @throws Exception
	 */
	public void connectFail(String data) throws Exception {
		IErExecuterService service = ErRuleService.getExecuter(data);
		if(service instanceof ErExecuterBase){
			ErExecuterBase base = (ErExecuterBase) service;
			base.setPojo(pojo);
			base.setSendStr(data);
		}
		service.connectFail(data);
	}

	/**深圳通返回的失败信息，修改状态和数据提示信息，状态为发送失败，可能参数不对或者托管行不在线
	 * @param errInfo
	 * @param data
	 * @throws Exception
	 */
	public void sendFail(String errInfo,String data) throws Exception {
		IErExecuterService service = ErRuleService.getExecuter(data);
		if(service instanceof ErExecuterBase){
			ErExecuterBase base = (ErExecuterBase) service;
			base.setPojo(pojo);
			base.setSendStr(data);
		}
		service.sendFail(errInfo,data);
	}

	/**深圳通返回的成功信息，说明深圳通已经收到数据，此状态为已发送
	 * @param data
	 * @throws Exception
	 */
	public void sendSucc(String data) throws Exception {
		IErExecuterService service = ErRuleService.getExecuter(data);
		if(service instanceof ErExecuterBase){
			ErExecuterBase base = (ErExecuterBase) service;
			base.setPojo(pojo);
			base.setSendStr(data);
		}
		service.sendSucc(data);
	}
}
