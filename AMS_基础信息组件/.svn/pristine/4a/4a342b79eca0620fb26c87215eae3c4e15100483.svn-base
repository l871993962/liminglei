package com.yss.ifa.szt.tool.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.file.PropertiesUtil;
import com.yss.ifa.szt.tool.pojo.ErRptLog;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.zip.BaoWenTool;

/**
 * Author : wuLongxing
 * Date   : 2016-08-02
 * Status : Add
 * Comment: 报文消息日志工厂 
 */
public class MessageLogFactory implements Runnable{
	/**
	 * 日志保存天数
	 */
	public static final String LOG_SAVE_DAY_NUM = "elec_data_retain";
	/**
	 * 日志保存路径
	 */
	public static final String LOG_SAVE_PATH = "elec_data_path";
	/**
	 * 日志保存方式
	 */
	public static final String LOG_SAVE_TYPE = "elec_data_type";
	
	/**
	 * 日志保存方式-文件
	 */
	public static final String LOG_SAVE_TYPE_FILE = "file";
	
	/**
	 * 日志保存方式-数据库
	 */
	public static final String LOG_SAVE_TYPE_DB = "DB";
	/**
	 * STORY73873深证通伺服器增加日志输出文件
	 */
	private static final Logger LOGGER = LogManager.getLogger(MessageLogFactory.class);
	
	/**
	 * 消息队列
	 */
	private LinkedBlockingQueue<Map<String,Object>> queue = new LinkedBlockingQueue<Map<String,Object>>();
	
	/**
	 * 文件名后缀 .txt
	 */
	private static final String FILE_SUFFIX = ".txt";
	
	private static final String SEND_PATH = "send";
	
	private static final String RECEIVE_PATH = "receive";
	
	public static final String FORMAT_DATETIME = "yyyyMMddHHmmssSSS";
	
	
	
	/**
	 * 设置保留几天的日志文件，默认保留3天
	 */
	private int maxLogFileDay = 3;
	
	private static class SingletonHolder{
		private static final MessageLogFactory MESSAGE_LOG_FACTORY= new MessageLogFactory();
	}
	private MessageLogFactory(){
		
	}
	/**
	 * 报文消息工厂单例
	 * @return
	 */
	public static final MessageLogFactory getInstance(){
		return SingletonHolder.MESSAGE_LOG_FACTORY;
	}
	/**
	 * 设置保留几天的日志文件
	 * @param maxLogFileDay
	 */
	public void setMaxLogFileDay(int maxLogFileDay){
		this.maxLogFileDay = maxLogFileDay;
	}
	
	/**
	 * 消息加入队列
	 * @param messInfo 消息对象
	 * @param path 文件路径
	 * @param executeType 执行类型
	 * @param operDateTime 操作时间点 yyyyMMddHHmmss
	 */
	public synchronized void addMessage(List<TransPojo> transPojos, String path, ExeTypeEnum executeType, String operTime){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("transPojos", transPojos);
		map.put("path", path);
		map.put("type", executeType);
		map.put("operTime", operTime);
		try {
			queue.put(map);
		} catch (InterruptedException ex) {
			LOGGER.log("消息加入队列失败!",ex);
			Thread.currentThread().interrupt();
		}
	}
	/**
	 * STORY #65624电子对账收发报文路径支持可配置
	 * 消息加入队列
	 * @param transPojos 消息对象
	 * @param executeType 执行类型
	 * @param operDateTime 操作时间点 yyyyMMddHHmmss
	 */
	public synchronized void addMessageWithConfig(List<TransPojo> transPojos, ExeTypeEnum executeType, String operTime){
		String logFileName = new FileStorePathUtil(YssConstant.YSSPERIPHERAL).getFilePath();
		FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.GLOABL_PATH);
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		String configFileName = fileUtil.getFilePath() + "runtime.properties";
		File file = new File(configFileName);
		String day = this.maxLogFileDay+"";
		String type = LOG_SAVE_TYPE_DB;//STORY #73968【华宝基金-伺服器】深证通-伺服器界面，前台界面需要，需要看到发送和接收的详细报文信息
		if(file.exists()){
			Properties properties;
			try {
				properties = propertiesUtil.Properties(configFileName);
				logFileName = properties.getProperty(LOG_SAVE_PATH);
				if(logFileName != null)//自定义目录，添加一层yss_log，防止误删其他数据
				{
					logFileName = logFileName+ File.separator + "yss_log"+File.separator;
				}else
				{
					logFileName = new FileStorePathUtil(YssConstant.YSSPERIPHERAL).getFilePath();
				}
				day = properties.getProperty(LOG_SAVE_DAY_NUM,day);
				type = properties.getProperty(LOG_SAVE_TYPE, type);
			} catch (Exception e) {
				LOGGER.log(e.getMessage(),e);
				logFileName = new FileStorePathUtil(YssConstant.YSSPERIPHERAL).getFilePath();
				day = this.maxLogFileDay+"";
				type = LOG_SAVE_TYPE_DB;
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("transPojos", transPojos);
		map.put("path", logFileName);
		map.put("type", executeType);
		map.put("operTime", operTime);
		map.put("day", day);
		map.put("logType", type);
		try {
			queue.put(map);
		} catch (InterruptedException ex) {
			LOGGER.log("消息加入队列失败!",ex);
			Thread.currentThread().interrupt();
		}
	}
	/**
	 * 日期 + 文件夹
	 * @param executeType
	 * @return
	 */
	private String getPath(ExeTypeEnum executeType){
		String fileName = "";
		if(executeType == ExeTypeEnum.SEND){
			fileName = DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT) + File.separator + MessageLogFactory.SEND_PATH + File.separator ;
		}else if(executeType == ExeTypeEnum.RECEIVE){
			fileName = DateUtil.getCurrDate(DateUtil.LONG_DATE_FORMAT) + File.separator + MessageLogFactory.RECEIVE_PATH + File.separator ;
		}
		return fileName;
	}
	
	/**
	 * 写操作日志
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void run(){
		Map<String,Object> map;
		BufferedWriter bufferWritter = null;
		FileWriter fileWritter = null;
		String errMessage = "";
		while(true){
			try{
				while((map = queue.take()) != null){
					List<TransPojo> transPojoList = (List<TransPojo>)map.get("transPojos");
					String path = (String)map.get("path");
					ExeTypeEnum executeType = (ExeTypeEnum)map.get("type");
					String operTime = (String)map.get("operTime");
					Object dayNum = map.get("day");
					boolean isDB = map.get("logType") != null && LOG_SAVE_TYPE_DB.equalsIgnoreCase(String.valueOf(map.get("logType")));
					for (TransPojo transPojo : transPojoList) {
						errMessage = executeType == ExeTypeEnum.RECEIVE ? "接收数据成功" : "发送数据成功";
						if(dayNum != null && "N".equalsIgnoreCase(String.valueOf(dayNum).trim()))
						{
							continue;
						}
						TransPojo clonepPojo = (TransPojo) transPojo.clone();
						try {
							String data = clonepPojo.getSendStr();
							String xml = data;
							if(data.indexOf("1008=FISP") < 0){
								try{
									////STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
									xml = BaoWenTool.unZip(clonepPojo,data);
									clonepPojo.setSendStr(xml);
//									if(data.contains("<IN>") && data.contains("<FUND_NAME>")){
//										//民生银行的解密方式不同，不需要解密
//									}else{
//										xml = BaoWenTool.unZip(data);
//										clonepPojo.setSendStr(xml);
//									}
								}catch(Throwable ex){
									errMessage = "解密日志数据出错 :"+ex.getMessage();
									LOGGER.log("解密日志数据出错 :" + xml, ex);
									clonepPojo.setSendStr(data); // 杭州银行的报文时明文，调用解密会报错，BUG175748伺服器中没有显示杭州银行记录日志
								}
							}
							
							//将解密后的数据更新到数据库
							if(isDB)
							{
								ErRptLog erLog = getRptLog(executeType, clonepPojo,xml);
								erLog.setC_ERR_INFO(errMessage);
								erLog.setC_ENCRYPT_LOG(transPojo.getSendStr());
								DBLogOper.getInstance().insertRptLog(erLog,dayNum);
								continue;
							}

							String fileType = MessageLogFactory.findFileType(xml);
							String fundId = MessageLogFactory.findFundId(xml);
							String serialNo = MessageLogFactory.findSerialNo(xml);
							String pathPre = getPath(executeType);
							//文件名为类型+产品代码+流水号+操作时间
							String fileName =  fileType + "_" + fundId + "_" +serialNo + "_" + operTime + FILE_SUFFIX;
							File dir = new File(path + pathPre);
							if(!dir.exists()){
								//STORY #65624电子对账收发报文路径支持可配置
								boolean b = dir.mkdirs();
								if(!b)
								{
									LOGGER.error("电子对账报文保存路径配置有误：" + path + pathPre);
								}
								//deleteFile(path);
								if(map.get("day") != null)//指定
								{
									String value = String.valueOf(map.get("day")).trim();
									if("Y".equalsIgnoreCase(value))
									{
										//永久保留
									}else if(Pattern.matches("\\d+", value))
									{
										deleteFile(path, Integer.parseInt(value));
									}else//非法字符默认3天
									{
										LOGGER.error("电子对账报文保存天数配置有误，采用默认配置，保留3天！");
										deleteFile(path, maxLogFileDay);
									}
								}else
								{
									deleteFile(path, maxLogFileDay);
								}
								
							}

							File file = new File(path + pathPre + fileName);
							if(!file.exists()){
								boolean btrue = file.createNewFile();
							}

							fileWritter = new FileWriter(file,true);
							try {
								bufferWritter = new BufferedWriter(fileWritter);

								//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置 追加日志
								StringBuffer sb = new StringBuffer();
								String old = JsonUtil.toString(transPojo);
								old = old.replaceAll("\\\\t", "");
								String pojoStr = JsonUtil.toString(clonepPojo);
								pojoStr = pojoStr.replaceAll("\\\\t", "");
								sb.append("原始数据：\r\n\r\n").append(old).append("\r\n\r\n");
								sb.append("解密后数据：\r\n\r\n").append(pojoStr);
								//STORY73873深证通伺服器增加日志输出文件
								String preStr = ExeTypeEnum.SEND == executeType ? "[发送数据至深圳通]" : "[发送数据至深圳通后返回值]";
								LOGGER.debug(preStr+pojoStr);
								bufferWritter.write(sb.toString());

								bufferWritter.flush();
								closeStream(fileWritter);
								closeStream(bufferWritter);
							} catch (Exception e) {
								closeStream(fileWritter);
								LOGGER.log("写入日志出错!" + clonepPojo.getSendStr(), e);
							}finally{
								closeStream(fileWritter);
							}
						} catch (Exception e) {
							LOGGER.log("写入日志出错!" + clonepPojo.getSendStr(), e);
							continue;
						}finally{
							closeStream(fileWritter);
							closeStream(bufferWritter);
						}
					}
				}
			}catch(Throwable e){
				LOGGER.log("写入日志出错!", e);
			}finally{
				closeStream(fileWritter);
				closeStream(bufferWritter);
			}
		}
	}
	
	private void closeStream(Writer stream){
		if(stream != null){
			try {
				stream.close();
			} catch (IOException e) {
				LOGGER.log("关闭流出错 :", e);
			}
		}
	}
	//STORY #65624电子对账收发报文路径支持可配置,历史数据没有清楚干净，走新的方法
//	/**
//	 * 删除历史日志文件，只保留最近3天的数据
//	 * @param path 日志文件路径
//	 */
//	private void deleteFile(String path){
//		File dir  = new File(path);
//		for(int i = maxLogFileDay; i < maxLogFileDay + 10; i++){
//			String contains = DateUtil.dateToString(DateUtil.nextDay(new Date(), -i), DateUtil.LONG_DATE_FORMAT);
//			MyFileNameFilter fileFilter = new MyFileNameFilter(contains);
//			File[] files = dir.listFiles(fileFilter);
//			if (files != null) {
//				for (File file : files) {
//					deleteAllFilesOfDir(file);
//				}
//			}
//		}
//	}
	
	/**
	 * STORY #65624电子对账收发报文路径支持可配置
	 * @param path	文件路径
	 * @param day	保留天数
	 */
	private void deleteFile(String path,int day){
		File dir  = new File(path);
		File[] files = dir.listFiles(new CustomFilenameFilter(day));
		if (files != null && files.length > 0) {
			for (File file : files) {
				deleteAllFilesOfDir(file);
			}
		}
	}
	/**
	 * 删除文件夹下的子文件夹及文件
	 * @param path
	 */
	private void deleteAllFilesOfDir(File path) {
		boolean btrue = true;
		if (!path.exists())
			return;
		if (path.isFile()) {
			 btrue = path.delete();
			return;
		}
		File[] files = path.listFiles();
		if(files != null){
			for (int i = 0; i < files.length; i++) {
				deleteAllFilesOfDir(files[i]);
			}
		}
		btrue = path.delete();
	}
	/**
	 * 获取文件类型
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
		}
		if (reval == null) {
			// 
			fileType = "file_type";
			reval = findNodeName(fileType, data);
		}
		
		if (reval == null) {
			//宁波银行
			fileType = "TRAN_CODE";
			reval = findNodeName(fileType, data);
		}
		
		if (reval == null) {
			//农业银行
			fileType = "TransCode";
			reval = findNodeName(fileType, data);
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
			//交行核算明细
			fileType = "OprtCd";// STORY78725【富国基金】电子对账支持对OprtCd的逻辑处理
			reval = findNodeName(fileType, data);
		}
		
		return reval;
	}
	/**
	 * 获取流水号
	 * @param data
	 * @return
	 */
	private static String findSerialNo(String data) {
		String reval = null;
		String seqNo = null;
		String fileType = "SERIAL_NO";// /其他行
		reval = findNodeName(fileType, data);
		if (reval == null) {
			fileType = "SerialNo";// 农行
			reval = findNodeName(fileType, data);
			if(reval != null){
				fileType = "CustInsCode";//农行 指令编号
				seqNo = findNodeName(fileType, data);
			}
		}
		if (reval == null) {
			// 宁波银行
			fileType = "SERIAL_NO";
			reval = findNodeName(fileType, data);
		}
		
		if(reval == null){
			fileType = "MsgNO";//交行核算明细
			reval = findNodeName(fileType, data);
		}
		
		if(seqNo == null){
			fileType = "SEQ_NO";//指令编号
			seqNo = findNodeName(fileType, data);
		}
		if(seqNo == null){
			fileType = "INSR_NO";//建行指令编号
			seqNo = findNodeName(fileType, data);
		}
		if(seqNo != null){
			reval = reval + "_" + seqNo;
		}
		return reval;
	}
	/**
	 * 获取资产代码
	 * @param data
	 * @return
	 */
	private static String findFundId(String data) {
		String reval = null;
		String fileType = "FUND_ID";// /其他行
		reval = findNodeName(fileType, data);
		if (reval == null) {
			fileType = "AssetId";// 农行
			reval = findNodeName(fileType, data);
		}
		if (reval == null) {
			// 宁波银行
			fileType = "fund_id";
			reval = findNodeName(fileType, data);
		}
		if (reval == null) {
			// 交行核算明细
			fileType = "ComCode";
			reval = findNodeName(fileType, data);
		}
		return reval;
	}

	private static String findNodeName(String nodeName, String data) {
		int startIndex = data.indexOf("<" + nodeName + ">") + nodeName.length()
				+ 2;
		int endIndex = data.indexOf("</" + nodeName + ">");
		if (startIndex > endIndex) {
			return null;
		}
		return data.substring(startIndex, endIndex).trim();
	}
	
	private ErRptLog getRptLog(ExeTypeEnum executeType, TransPojo transPojo, String xml)
	{
		ErRptLog log = new ErRptLog();
		String fileType = findFileType(xml);
		String fundId = findFundId(xml);
		String serialNo =findSerialNo(xml);
		String rptType = findNodeName("REPORT_TYPE", xml);
		String beginDate = findNodeName("BEGIN_DATE", xml);
		log.setC_FILE_TYPE(fileType);
		log.setC_ASS_CODE(fundId);
		log.setC_SN(serialNo);
		log.setC_RPT_TYPE(rptType);
		log.setC_BEGIN_DATE(beginDate);
		
		log.setC_DV_LOG_TYPE(executeType.toString());
		log.setC_DV_CHARSET(transPojo.getCharSet());
		log.setC_DV_SECRETTYPE(transPojo.getSecretType());
		log.setC_SECRETKEY(transPojo.getSecretKey());
		log.setD_HANDLE_TIME(new Timestamp(System.currentTimeMillis()));
		log.setC_PKG_PSD(transPojo.getPkgPassword());
		log.setC_SRC_APPID(transPojo.getFromApp());
		log.setC_SRC_USERID(transPojo.getFromUser());
		log.setC_TARGET_APPID(transPojo.getToApp());
		log.setC_TARGET_USERID(transPojo.getToUser());
		log.setC_DECRYPT_LOG(transPojo.getSendStr());
		log.setC_PKG_ID(transPojo.getPkgId());
		
		return log;
	}
	
//	public static void main(String[] args) throws Exception {
//		MessageLogFactory logFactory = MessageLogFactory.getInstance();
//
//		List<TransPojo> list= new java.util.ArrayList<TransPojo>();
//		for(int i=0;i<1; i++){
//
//			TransPojo pojo = new TransPojo();
//			pojo.setFromApp("dzdz02");
//			pojo.setFromUser("F_YINGSS02");
//			pojo.setToApp("dzdz01");
//			pojo.setToUser("F_YINGSS03");
//			pojo.setErrInfo("成功");
//			String sendStr = 
//					
//					"H4sIAAAAAAAAAE1QSQ6DMAw8+xf9QRxWFVlIlBgUia2QHtpL/v+LOrQQbvaMZzw2UGcH9u69cK2T\r\nQpOKPVD3moy3pna9RsR7hqnwfwxo5WVe3W8WRXntgR7c28mbxnGdoC6xwJLUBQRiMdpLUmcJZHhx\r\nvp2N5BHP2AG1LO6ymdRRAW282mbw01zrtESdk4pISNjOq4wBOTvy5sblzHLTWZXrKk/zIDppCJbP\r\noP4MWzz7UBXBf6fDoL/+LpO0VwTCQ/b1XzC/6W5kAQAA";
////					"H4sIAAAAAAAAAF2QsU7DQAyGZz9NfAm9A8k6Kdy5KFK5RCEMZblnYyorYkAoisTAhIqIWFh4hA4s\r\nDIgFp7RNxHb/78+/7QOaFwuOzbJiq9AoSkYNNL8MPhbeImZHJpPaTgPVXJV188ehdE010CmfFSH6\r\nvGGbojKocUbJxARiCfpXP1hAnqsmutJLNg7powZyLHNkB0r2L6ALrot8EUNp/ZUxCikZnWFXV9aC\r\nAeXOhV1Qlkq01voE1TEqVFmaarlwQhz4kJ+zfXq979ab9ebxp/26fXvu767b7+6za/v+pX/4eL9p\r\nV/vmLQ7Dl2zH/gL2bhmuYgEAAA==";
////					"H4sIAAAAAAAAAH1UTWvbQBA975+pdmXLjmERyNLGiMiykNYmzmXvOffUv5Mc0vRQWshXUzWnktgh\r\niUiKAy0USi89lRzbQqGzu/qyLarTzJvZ0ZvZt4Poph8wwacRs4nZJtSofEQ3x6EnfM+2CDZ7GGK5\r\nj2jMolHMdR6GU3Uf0T4b+KHwHM5sE5Mu7mCLGjUQUQaFVuIlhKjHIi7ckcfs5y92dzGWf6gwRF0G\r\n/wIe1CgsRBMW+04gwpHt7ZAO6fS61KgwIOyOYpkXTYdcRE7Mp8JxXRlrt4GEhQmBH1mmZfa6BOis\r\n562e5SIc2rO7xX5rA6fn94/ZzcXhycd5ujid/13sX72+OX6cn/9On1ZrqXNLxaC3frgl4fQgy2Y/\r\nv3w+WWTZw+H90duLpdNVIlyBO1nn2IImiGXhtuylg7HZNltwO02pugKMr6C0yM7+SN5Hl+8v02/X\r\n3x+yu6+fnq5uz15df5jdvds7WUhr7+DN6elx+rT3UtetFahXrFGVddOD2Y/7X9Ab1Jln9e4aj8jp\r\nCI/X1KH9fGrjhAkvcW3VZv5tENzrtfNpFQnAaBhvKRP+VJgg0CQUPNJygotXVw8aq8OI+mESy0Ht\r\nBJv6ARRsWiCtIlj2DAQTPpQQARX1MG7B+K2yvyosH4+r78A0lNidoO8HAcjYWJaF6041F2J1lmRQ\r\nBlStSWN6UwBezjiB9yK56+dTd/W/13qxNtSUTaIprDYTOdO8GahXc5oE3vddTaRJ1GVwqan1k/8J\r\nIhr40FAUlMnLPqJ8O5Q6okZuwPiDiUZyQyOFCjRcaWIQR0IeVdOrObAr40HItgcKULsKZAwbswEF\r\nFvGAy1txZZWag+gE1pVikxuI7niCOyKKJ0nxcKmxjqkH4wy53cXdZ111VcpV8lSWUmKOSUaxXLWw\r\ndQsTWI3UQ+ZDW943ECt96I4x+J8nYIlrfitAlaHWeD0jB2TXjsdEwhxezHYdydf0Pxdz87CXBgAA\r\n";
////					"H4sIAAAAAAAAAF1QzWqDQBA+z9PsqE23wiDY3UkRzCrGHNrLPlwKTR9B+gRCC9JDA0meIeeQU8b8\r\nKb3t9zszCzTNcvb1a8lJgDogNWCg6cJZn9kkxDDCSLQrBqq4LKr64kNJjTHQM79kztu0ZokGGh9x\r\nQmpEArEU/dPvFJDlsvamsNKNffuAgQzLHNmB1O0FNOcqS3PvisS+aa1jUgPT72qKSmxAqTHuWvQg\r\nzf1VYRwiTvApipHUSL+7XTrj5K9rjs17+/176LqfrtmtV18fy1W7326Wn7fY2Qj9V5zHnQAa8L4C\r\nWgEAAA==";
////"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";
////"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><ap>\\t<TransCode>TG301</TransCode>\\t<SerialNo>9271</SerialNo>\\t<Operator>sys</Operator>\\t<content>\\t\\t<AccNo>15-131101040033449</AccNo>\\t\\t<AccName>农行山东省农村信用社联合社企业年金计划投资资产２</AccName>\\t</content></ap>";
//
////"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><ap>\\t<TransCode>TG303</TransCode>\\t<SerialNo>9271</SerialNo>\\t<Operator>sys</Operator>\\t<content>\\t\\t<AccNo>15-131101040033449</AccNo>\\t\\t<AccName>农行山东省农村信用社联合社企业年金计划投资资产２</AccName>\\t\\t<StartDate>20170605</StartDate>\\t\\t<EndDate>20170605</EndDate>\\t</content></ap>";
//
////"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><ap>\\t<TransCode>TG126</TransCode>\\t<SerialNo>161816</SerialNo>\\t<Operator>yaoqing</Operator>\\t<content>\\t\\t<CustInsCode>000816201706052286</CustInsCode>\\t</content></ap>";
//
////					"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><ap>\\t<TransCode>TG127</TransCode>\\t<SerialNo>161855</SerialNo>\\t<Operator>yaoqing</Operator><content>\\t<CustInsCode>000086201706052287</CustInsCode>\\t<InsType>01</InsType>\\t<BizCode>05007</BizCode>\\t<DebitName>南方稳利资金结算专户</DebitName>\\t<DebitAccNo>100000560170015</DebitAccNo>\\t<DebitBank>银行间市场清算所</DebitBank>\\t<DebitBankNo>909290000007</DebitBankNo>\\t<CreditName>南方稳利１年定期开放债券型证券投资基金</CreditName>\\t<CreditAccNo>41-000500040032640</CreditAccNo>\\t<CreditBank>农业银行深圳市分行营业部</CreditBank>\\t<CreditBankNo>103584000050</CreditBankNo>\\t<Amt>20933598.28</Amt>\\t<Remarks>调出上清所DVP</Remarks>\\t<ExecDate>20170605</ExecDate>\\t<CustOperator>yaoqing</CustOperator>\\t<StockCode></StockCode>\\t<Xw>\\t\\t<XwID></XwID>\\t\\t<Fee></Fee>\\t\\t<Remark></Remark>\\t</Xw>\\t<Org>SQS</Org>\\t<ConstractNo>CR20170602006878|CR20170605007535|CR20170602005119|CR20170605005561</ConstractNo>\\t<CountType></CountType>\\t<AssetId>000086</AssetId></content></ap>";
//
//					//"H4sIAAAAAAAAAF2OQQ7CIBBFj8QA2m4mJFWmDUmlhOJCN9yuF9CNO7cexjsIotK4m//ezORjb0aK4eRIceAcWc3YH62ORiuAVnIhs/wQ9OQmH8oeJLHOuKPB2Ki7QEoAb6CFLbIVREpf/vQPoSYX4n7S6bEQG2Q140zedGO0k9Ln7yk0UgpkVaVuoZznWmXM7DAP6rrcn5fHbXmbDF6LZe24/wAAAA==";
//
//			pojo.setSendStr(sendStr);
//			list.add(pojo);
//
//
//		}
//		logFactory.addMessage(list, "c:\\ysstech\\", ExeTypeEnum.SEND,"11112233");
//		Thread thread = new Thread(logFactory);
//		thread.start();
//		Thread.sleep(1000);
////		System.out.println(list.get(0).getSendStr());
////		 MessageLogFactory.getInstance().deleteFile("c:\\ysstech\\log\\");
//	}

}
