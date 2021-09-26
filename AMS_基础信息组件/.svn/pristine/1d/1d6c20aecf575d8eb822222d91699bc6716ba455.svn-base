package com.yss.ifa.szt.tool.thread;

/**
 * 每个文件的处理接口
 * 
 * @author weijj
 * 
 */
public interface IErExecuterService {
	////接口实现类的命名规则
	public static final String CLASS_NAME_PIX = "Executer";
	////接受到托管行发来的数据处理
	void execute(String data) throws Exception;
	////文件回执的消息处理
	void retMsg(String data) throws Exception;
	////发送到深证通成功后处理
	void sendSucc(String data) throws Exception;
	////发送到深证通失败处理
	void sendFail(String errInfo, String data) throws Exception;
	////伺服器连接失败处理
	void connectFail(String data) throws Exception;
}
