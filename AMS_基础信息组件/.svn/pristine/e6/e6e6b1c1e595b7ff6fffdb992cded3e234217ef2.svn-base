package com.yss.ifa.szt.tool.thread;

import com.yss.framework.api.bundle.ClassServiceHandler;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.restful.RestfulConfigServiceImpl;

public class ErRuleService {

	/**
	 * 文件类型处理器规则
	 * 
	 * @param data
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws YssException 
	 */
	public static IErExecuterService getExecuter(String data)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, YssException {
		String className = IErExecuterService.CLASS_NAME_PIX
				+ findFileType(data);
		  if(new RestfulConfigServiceImpl().getConfig().isShell()) {
			  if(ClassServiceHandler.hasInstance(className)) {
                  Object o = ClassServiceHandler.getNewInstance(className);
                  return (IErExecuterService) o;
	          }else {
	                  throw new YssRuntimeException("无法找到executor:" + className);
	          }
		  }else{
			  Object o = ClassServiceHandler.getNewInstance(className);
			  return (IErExecuterService) o;
		  }
	}
	
	/**
	 * 判断executer是否存在实现类
	 * @param data
	 * @return
	 */
	public static boolean hasExecuter(String data){
		String className = IErExecuterService.CLASS_NAME_PIX
				+ findFileType(data);
		return ClassServiceHandler.hasInstance(className);
	}

	/**
	 * 获取对账类型
	 * STORY78725【富国基金】电子对账支持对OprtCd的逻辑处理
	 * @param data
	 * @return
	 */
	private static String findFileType(String data) {
		String reval = null;
		String fileType = "FILE_TYPE";// /其他行
		reval = findNodeName(fileType, data);
		if (reval == null) {
			fileType = "TransCode";// 农行
			reval = findNodeName(fileType, data);
			if(reval!=null){
				reval = reval.toUpperCase();
			}
		}
		
		////BUG #188693 发送宁波银行的指令，模拟返回指令发送成功报文时系统无反应
		if (reval == null) {
			fileType = "TRAN_CODE";// 农行
			reval = findNodeName(fileType, data);
			if(reval!=null){
				reval = reval.toUpperCase();
			}
		}
		if(reval == null){
			// STORY #57746 增加民生银行深证通发送附件的功能 
			// 民生银行附件发送
			fileType = "txcode";
			reval = findNodeName(fileType, data);
			if(reval != null){
				reval = reval.replace("<![CDATA[", "").replace("]]>", "");
			}
		}
		if (reval == null) {
			//STORY #67631 新增农行和浦发银行接口模板对接账户交易明细信息
			fileType = "file_type";// 浦发银行1364、1365是小写的
			reval = findNodeName(fileType, data);
		}
		if (reval == null) {
			fileType = "OprtCd";// STORY78725【富国基金】电子对账支持对OprtCd的逻辑处理
			reval = findNodeName(fileType, data);
		}
		return reval;
	}

	public static String findNodeName(String nodeName, String data) {
		int startIndex = data.indexOf("<" + nodeName + ">") + nodeName.length()
				+ 2;
		int endIndex = data.indexOf("</" + nodeName + ">");
		if (startIndex > endIndex) {
			return null;
		}
		return data.substring(startIndex, endIndex).trim();
	}

	/**
	 * 对账的收发数据保存成落地文件 文件名的命名规则
	 * 
	 * @return
	 */
	public static String getSaveFileName(String data) {
		String fileType = findFileType(data);
		String FUND_ID = findNodeName("FUND_ID", data);
		String BEGIN_DATE = findNodeName("BEGIN_DATE", data);
		return fileType + "_" + FUND_ID + "_" + BEGIN_DATE;
	}
}
