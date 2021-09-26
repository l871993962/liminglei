package com.yss.ifa.szt.tool.zip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Element;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.file.XmlProcessor;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.msg.mq.MessageQueueException;
import com.yss.framework.msg.mq.TopicNames;
import com.yss.ifa.szt.tool.cons.SecretTypeCons;
import com.yss.ifa.szt.tool.msgcenter.DzdzProducer;
import com.yss.ifa.szt.tool.msgcenter.DzdzPublisher;
import com.yss.ifa.szt.tool.pojo.ActionPojo;
import com.yss.ifa.szt.tool.pojo.EncryptPojo;
import com.yss.ifa.szt.tool.pojo.MrInfo;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.util.AESUtil;
import com.yss.ifa.szt.tool.util.SecretKeyUtil;

public class BaoWenTool {
	public static final String YSSUCO = "UCO";
	public static final String MRAPI = "MRAPI";

	// /事件
	public static final int START = 10;// 启动
	public static final int INIT = 11; // 初始化完成
	public static final int SEND = 12; // 发送数据
	public static final int STOP = 13; // 停止
	public static final int OK = 14; // 链接确认
	//STORY57549嘉实基金-支付平台-深证通链接检测
	public static final int CHECKMR = 15;//深证通连接检测
	/**
	 * STORY39802南方基金-支持设置通过深证通发送指令、余额明细查询报文的发送优先级
	 * 发送优先级 0为普通；1为紧急
	 */
	public static final int COMMON = 0;
	public static final int URGENT = 1;

	private static String encode = "GBK";

	private static Logger logger = LogManager.getLogger(BaoWenTool.class);
	private static Logger reportLogger = LogManager.getLogger("com.report.test");
	//BUG272910深证通伺服器给估值反馈响应消息，报No buffer space available (maximum connections reached?)
	private static ConcurrentHashMap<String, DzdzProducer> mqProducerMap = new ConcurrentHashMap<String, DzdzProducer>();
	
	/**
	 * 把文件读入 解压输出
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws IOException
	 */
	public static String unZipFile(String fileName) throws IOException {
		XmlProcessor xmlUtil = new XmlProcessor(fileName);
		String encode = xmlUtil.getDocument().getXMLEncoding();
		Element root = xmlUtil.getRootNode();
		String fileContent = root.getText();
		fileContent = StringUtil.uncompress(fileContent, encode);
		return fileContent;
	}

	/**
	 * 把文件读入 解压输出
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws IOException
	 */
	public static String unZip(String fileContent) throws IOException {
		int beginIndex = 0;
		int endIndex = 0;
		// STORY #28382 民生银行返回报文不需解密
		if(fileContent.contains("<OUT>") && fileContent.contains("<FUND_NAME>")){
			return fileContent;
		}
		
		//BUG #145555 南方基金-发送招行基金申购认购指令伺服器返回解密数据出错 bylfd
		if(!(fileContent.contains("<FILE_TYPE>1521") || fileContent.contains("<FILE_TYPE>1522"))){
			// <IN>报文中解密RECORD标签内容
			if(fileContent.contains("<IN>") && fileContent.contains("<FUND_NAME>")){
				/*STORY #31010 民生银行嘉实资本的公司ID和密钥是分开的*/
				return AESUtil.getInstance().AESDecode(fileContent);
			}
		}
		String result = "";// 解密后的结果
		// //要解密的数据
		String data = fileContent;
		if (fileContent.contains("<IN>")) {
			beginIndex = fileContent.indexOf("<IN>") + 4;
			endIndex = fileContent.indexOf("</IN>");
			data = fileContent.substring(beginIndex, endIndex);
		} else if (fileContent.contains("<OUT>")) {
			beginIndex = fileContent.indexOf("<OUT>") + 5;
			endIndex = fileContent.indexOf("</OUT>");
			data = fileContent.substring(beginIndex, endIndex);
		}else if(fileContent.contains("<ap>")){
			////农行数据不压缩
			return fileContent;
		}
		result = StringUtil.uncompress(data, encode);
		return fileContent.replace(data, result);
	}
	
	/**
	 * 所有的数据发送调用该方法
	 * 本方法一般是多线程调用
	 * @param dataList
	 * @param action
	 * 添加通道类型  by weijj STORY #24165 20150715 
	 * TransPojo 中添加通道类型，根据通道类型判断发送通道，放到不同的发送通道里面发送
	 */
	public static boolean send(List<TransPojo> dataList, String sender,
			int action) {
		boolean res = true;
		try {
			if(action == BaoWenTool.SEND && dataList != null){
				//////首先做一层通道处理
				List<TransPojo> sztList = new ArrayList<TransPojo> ();
				for(TransPojo pojo : dataList){
					if(StringUtil.IsNullOrEmptyT(pojo.getPrimaryKey()))
					{
						String primaryKey = genePrimaryKey();
						pojo.setPrimaryKey(primaryKey);
					}
					logger.log("消息primaryKey：" + pojo.getPrimaryKey());
					/*BUG #131955 钜盛华- 深证通查询发送指令报错
					 * 加入控制判断,若为空默认发送深证通*/
					if(!StringUtil.IsNullOrEmpty(pojo.getCommType())){
						if(pojo.getCommType().equalsIgnoreCase("IBM_MQ")){
							DzdzPublisher.sendByIbmMQ(pojo.getSendStr());
						}else if(pojo.getCommType().equalsIgnoreCase("SZT")){
							sztList.add(pojo);
						}else if("socket".equalsIgnoreCase(pojo.getCommType())){//STORY52785上海银行---银行电子对账特殊处理
							sztList.add(pojo);
						}else {
							sztList.add(pojo);
						}
					}else{
						sztList.add(pojo);
					}
				}

				dataList = sztList;
			}
			if(dataList == null || dataList.size() == 0){
				return false;
			}
			HashMap<String, List<TransPojo>> transHashMap = new HashMap<String, List<TransPojo>>();
			for (TransPojo transPojo : dataList) {
				StringBuilder builder = new StringBuilder();
				for (MrInfo mrInfo : transPojo.getMrInfoList()) {
					builder.append(mrInfo.getC_Mr_Ip()).append("_").append(mrInfo.getC_Mr_Port()).append(";");
				} 
				String mapKey = builder.toString();
				if(transHashMap.containsKey(mapKey)){
					transHashMap.get(mapKey).add(transPojo);
				}else{
					List<TransPojo> pojoList = new ArrayList<TransPojo>();
					pojoList.add(transPojo);
					transHashMap.put(mapKey, pojoList);
				}
			}
			for(Map.Entry<String, List<TransPojo>> entry : transHashMap.entrySet()){
				String mapKey = entry.getKey();
				//STORY42660【中国银行】深证通伺服器要求采用热备模式
				try {
					ActionPojo pojo = new ActionPojo();
					pojo.setSender(sender);
					pojo.setAction(action);
					pojo.setDataList(entry.getValue());
					pojo.setKey("");
					String sendStr = JsonUtil.toString(pojo);
					logger.debug("开始发送信息"+sendStr);
					boolean sendResult = false;
					HashMap<String, String> sendMap = new HashMap<String, String>();
					String [] mainStrAry = mapKey.split(";");
					for(int i = 0; i< mainStrAry.length; i++){
						String mainStr = mainStrAry[i];
						if(sendMap.containsKey(mainStr)){
							continue;
						}
						sendMap.put(mainStr, mainStr);
						String ip = mainStrAry[i].split("_")[0];//主机IP
						String port =  mainStrAry[i].split("_")[1];//主机端口
						logger.log("发送消息：" + mainStrAry[i]);
						sendResult = sendMessage(ip, port, sendStr);
						if(action == BaoWenTool.SEND){//如果发送的是数据，而不是初始化或关闭系统信息时
							if(sendResult){//主机并没有发送成功，则备机继续发送，直到发送成功
								break;
							}
						}else{//如果发送的是初始化或关闭系统信息时，主备机都要发送
						}
						//发送失败给返回值
						//BUG293807【300.7--20191231版本】深证通发送指令，当中途关闭掉伺服器后，再次进行发送不提示伺服器未连接
						if(!sendResult)
						{
							res = false;
						}
					}
						
				} catch (Throwable e) {
					logger.log("发送数据出错："+e.getMessage(),e);
					res = false;
				}
			}
			
			return res;
		} catch (MessageQueueException e) {
			logger.log("发送数据到伺服器出错："+e.getMessage(), e);
			res = false;
		}
		return res;
	}
	/**
	 * STORY42660【中国银行】深证通伺服器要求采用热备模式
	 * @Description: 发送数据至消息中心
	 * @param ip IP地址
	 * @param port 端口
	 * @param message 发送数据
	 * @return    返回发送结果，成功或失败
	 */
	public static synchronized boolean sendMessage(String ip, String port, String message){
		boolean success = true;
		DzdzProducer dzdzProducer = null;
		String remoteAddr = ip + ":" + port;
		try{
			//BUG272910深证通伺服器给估值反馈响应消息，报No buffer space available (maximum connections reached?)
			if(!mqProducerMap.containsKey(remoteAddr)){
				dzdzProducer = new DzdzProducer(TopicNames.DZDZ, ip, port);
				mqProducerMap.put(remoteAddr, dzdzProducer);
			}else {
				dzdzProducer = mqProducerMap.get(remoteAddr);
				if(System.currentTimeMillis() - dzdzProducer.getLastSendTime().get() > 10*60*1000){
					dzdzProducer.close();
					dzdzProducer = new DzdzProducer(TopicNames.DZDZ, ip, port);
					mqProducerMap.put(remoteAddr, dzdzProducer);
				}else{
					dzdzProducer = mqProducerMap.get(remoteAddr);
				}
			}
			success = dzdzProducer.send(message);
			if(!success){
				dzdzProducer.close();
				dzdzProducer = new DzdzProducer(TopicNames.DZDZ, ip, port);
				mqProducerMap.put(remoteAddr, dzdzProducer);
			}else {
				dzdzProducer.getLastSendTime().getAndSet(System.currentTimeMillis());
			}
		} catch (Exception e) {
			logger.log("连接消息中心【"+ ip + ":" + port + "】出错："+e.getMessage(), e);
			success = false;
			try {
				if (dzdzProducer != null) {
					dzdzProducer.close();
				}
				dzdzProducer = new DzdzProducer(TopicNames.DZDZ, ip, port);
				mqProducerMap.put(remoteAddr, dzdzProducer);
			} catch (Exception ex) {
				logger.log("创建【"+remoteAddr+"】连接出错："+ex.getMessage(), ex);
			}
		}finally{
		}
		return success;
	}

	///压缩返回结果文件
	public static String zip(String fileContent,String encode) throws Exception {
		String result = fileContent;
		try {
			int beginIndex = 0;
			int endIndex = result.length() - 1;
			//BUG #145555 南方基金-发送招行基金申购认购指令伺服器返回解密数据出错 bylfd
			if(!(fileContent.contains("<FILE_TYPE>1521") || fileContent.contains("<FILE_TYPE>1522"))){
				// 民生银行发送报文使用AES方式加密RECORD标签中的内容
				//BUG #163686 社保产品发送招商银行深证通失败
				if(result.contains("<IN>") && result.contains("<FUND_NAME>") && !(result.contains("1521")) ){
					/*STORY #31010 民生银行嘉实资本的公司ID和密钥是分开的*/
					return AESUtil.getInstance().AESEncode(result);
				}
				// <OUT>报文不用加解密
			}
			if(fileContent.contains("<OUT>") && fileContent.contains("<FUND_NAME>")){
				return fileContent;
			}
			
			//包商银行处理
			if(result.contains("<FUND_NAME_BS>")){
				result = result.replaceAll("<FUND_NAME_BS>", "<FUND_NAME>").replaceAll("</FUND_NAME_BS>", "</FUND_NAME>");
			//包商数据不压缩
				return result;
			}
			
			if (result.contains("<OUT>")) {
				beginIndex = result.indexOf("<OUT>") + 5;
				endIndex = result.indexOf("</OUT>");
			}else if(result.contains("<IN>")){
				beginIndex = result.indexOf("<IN>") + 4;
				endIndex = result.indexOf("</IN>");
			}else if(result.contains("<ap>")){
				////农行数据不压缩
				return fileContent;
			}
			
			String res = result.substring(beginIndex, endIndex);
			String data = StringUtil.compress(res, encode);
			result = result.replace(res, data);
			result = result.replaceAll("\r\n", "");
			result = result.replaceAll("\n", "");
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	///压缩返回结果文件
	public static String zip(String fileContent,String encode,String orgCode) throws Exception {
		String result = fileContent;
		try {
			int beginIndex = 0;
			int endIndex = result.length() - 1;
			//BUG #145555 南方基金-发送招行基金申购认购指令伺服器返回解密数据出错 bylfd
			if(!(fileContent.contains("<FILE_TYPE>1521") || fileContent.contains("<FILE_TYPE>1522"))){
				// 民生银行发送报文使用AES方式加密RECORD标签中的内容
				//BUG #163686 社保产品发送招商银行深证通失败
				if(!StringUtil.IsNullOrEmptyT(orgCode) && ("MSBANK".equalsIgnoreCase(orgCode) || "ZZLC".equalsIgnoreCase(orgCode))
						&& result.contains("<IN>") && result.contains("<FUND_NAME>") && !(result.contains("1521")) ){
					/*STORY #31010 民生银行嘉实资本的公司ID和密钥是分开的*/
					//BUG #203557 民生银行秘钥文件放到yss_app/transfer 后，所有银行都要求读取AESPWD.properties 不合理，需处理
					result = AESUtil.getInstance().replaceDeptcode(result);
					return AESUtil.getInstance().AESEncode(result);
				}
				// <OUT>报文不用加解密
			}
			if(fileContent.contains("<OUT>") && fileContent.contains("<FUND_NAME>")){
				return fileContent;
			}
			
			//包商银行处理
			if(result.contains("<FUND_NAME_BS>")){
				result = result.replaceAll("<FUND_NAME_BS>", "<FUND_NAME>").replaceAll("</FUND_NAME_BS>", "</FUND_NAME>");
			//包商数据不压缩
				return result;
			}
			
			if (result.contains("<OUT>")) {
				beginIndex = result.indexOf("<OUT>") + 5;
				endIndex = result.indexOf("</OUT>");
			}else if(result.contains("<IN>")){
				beginIndex = result.indexOf("<IN>") + 4;
				endIndex = result.indexOf("</IN>");
			}else if(result.contains("<ap>")){
				////农行数据不压缩
				return fileContent;
			}
			
			String res = result.substring(beginIndex, endIndex);
			String data = StringUtil.compress(res, encode);
			result = result.replace(res, data);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	// /加密文件
//	public String zip(String fileName) throws Exception {
//		String sendStr = readFileByLines(fileName);
//		if (sendStr == null)
//			return "";
//		JAXBProcessor jproc = new JAXBProcessor();
//		String result = sendStr;
//		XmlResult root = new XmlResult();
//		try {
//			root = jproc.unMarshal(root, sendStr);
//			result = JAXBProcessor.marshalWithReturnString2(root.getClass(), root,
//					encode);
//			int beginIndex = result.indexOf("<OUT>") + 5;
//			int endIndex = result.indexOf("</OUT>");
//			String res = result.substring(beginIndex, endIndex).replaceAll(" ",
//					"");
//			String data = StringUtil.compress(res, encode);
//			result = result.replace(res, data);
//			// 保存到本地
//		} catch (Exception e) {
//			throw e;
//		}
//		return result;
//	}


	/**
	 * 加密数据
	 * @param root
	 * @return
	 */
	public static String unZip(String inXml,String charsetCode) throws Exception {
		String result = "";
		try {
			int beginIndex = inXml.indexOf("<IN>") + 4;
			int endIndex = inXml.indexOf("</IN>");
			String res =  inXml.substring(beginIndex, endIndex);
			String data = StringUtil.uncompress(res, charsetCode); // 对数据压缩再加密
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			buffer.append("<IN>");
			buffer.append(data);
			buffer.append("</IN>");
			result = buffer.toString();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	/**
	 * 加密数据
	 * @param root
	 * @return
	 */
	public static String unZipAppendHead(TransPojo pojo ,String inXml) throws Exception {
		String result = "";
		//加密方式，默认BASE64
		String secretType = pojo.getSecretType();
		secretType = StringUtil.IsNullOrEmptyT(secretType) ? SecretTypeCons.BASE64 
						: secretType;
		String charSet = pojo.getCharSet();
		charSet = StringUtil.IsNullOrEmptyT(charSet)? "GBK" : charSet;
		String key = SecretKeyUtil.decryptSecretKey(pojo.getSecretKey());
		try {
			int beginIndex = inXml.indexOf("<IN>") + 4;
			int endIndex = inXml.indexOf("</IN>");
			String res =  inXml.substring(beginIndex, endIndex);
			String data = decryptData(secretType, key, res, charSet);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"").append("UTF-8").append("\"?>");
			buffer.append("<IN>");
			buffer.append(data);
			buffer.append("</IN>");
			result = buffer.toString();
		} catch (Exception e) {
			throw e;
		}
		reportLogger.log("解密成功（解密方式:"+secretType+";解密密钥:"+key+"报文编码:"+charSet+"）!");
		reportLogger.debug("加密前:"+inXml);
		reportLogger.debug("加密后:"+result);
		return result;
	}

	/**
	 * 整体压缩
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public String zipFile(String fileName) throws Exception {
		String sendStr = readFileByLines(fileName);
		if (sendStr == null)
			return "";
		String result = sendStr;
		try {
			result = StringUtil.compress(result, encode);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * @throws IOException 
	 */
	public static String readFileByLines(String fileName) throws IOException {
		File file = new File(fileName);
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			if (file.exists()) {
				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
					// 显示行号
					sb.append(tempString);
				}
				reader.close();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取本系统和其他系统的资产代码映射
	 * @param conn
	 * @param c_ass_code 要转换的资产代码
	 * @param flag true:本系统资产代码转换为其他系统资产代码
	 * 				false:其他系统资产代码转换为本系统资产代码
	 * @return 返回对应的资产代码映射，没有（不需要转换）则返回""
	 */
	public String getTransferC_Ass_CodeMap(Connection conn, String c_ass_code, boolean flag) {
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql = "SELECT C_S_CODE, C_T_CODE FROM T_V_D_GROUP_DETAIL WHERE C_GROUP_CODE = 'DZDZ_ZCDM' AND N_CHECK_STATE = 1 ";
		if(flag) {
			sql += " AND C_S_CODE = '" + c_ass_code + "'";
		}
		else {
			sql += " AND C_T_CODE = '" + c_ass_code + "'";
		}
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while (rs.next()) {
				if(flag) {
					return rs.getString("C_T_CODE");
				}
				else {
					return rs.getString("C_S_CODE");
				}
			}
		} catch (Exception e) {
			logger.log(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return "";
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 通过拆分代码获取资产代码
	 * @param conn
	 * @param splitCode 
	 * @return
	 */
	public String getAssCodeBySplitCode(Connection conn, String splitCode) {
		PreparedStatement stat = null;
		ResultSet rs = null;
		String sql = "SELECT C_ASS_CODE FROM T_D_ER_INFO WHERE C_SPLIT_CODE = ? ";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, splitCode);
			rs = stat.executeQuery();
			if (rs.next()) {
				String assCode = rs.getString("C_ASS_CODE");
				if(!StringUtil.IsNullOrEmptyT(assCode))
				{
					return assCode;
				}
			}
		} catch (Exception e) {
			logger.log(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return null;
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 通过资产代码获取组合代码
	 * @param conn
	 * @param assCode
	 * @return
	 */
	public String getPortCodeByAssCode(Connection conn, String assCode) {
		String portCode = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		//STORY73476【鹏华基金】并行组合电子对账需求 通过电子对账表关联
		String sql = " SELECT C_PORT_CODE FROM T_D_ER_INFO A where C_ASS_CODE = ? ";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, assCode);
			rs = stat.executeQuery();
			if (rs.next()) {
				portCode = rs.getString("C_PORT_CODE");
			}
		} catch (Exception e) {
			logger.log(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return portCode;
	}
	
//	/**
//	 * 
//	 * @param pojo	加密参数
//	 * @param context 需要加密的报文
//	 * @return 加密后的数据
//	 * @throws Exception 
//	 */
//	public static String zip(TransPojo pojo,XmlInRoot root) throws Exception
//	{
//		String result = "";
//		//加密方式，默认BASE64
//		String secretType = pojo.getSecretType();
//		secretType = StringUtil.IsNullOrEmptyT(secretType) ? SecretTypeCons.BASE64 
//				: secretType;		
//		String charSet = pojo.getCharSet();
//		charSet = StringUtil.IsNullOrEmptyT(charSet)? "GBK" : charSet;
//		String key = SecretKeyUtil.decryptSecretKey(pojo.getSecretKey());
//		String sendStr = JAXBProcessor.marshalWithReturnString2(root.getClass(), root, "GBK");
//		int beginIndex = sendStr.indexOf("<IN>") + 4;
//		int endIndex = sendStr.indexOf("</IN>");
//		String res =  sendStr.substring(beginIndex, endIndex);
//		String data = encryptData(secretType, key, res, charSet);
//		data = data.replaceAll("\r\n", "");
//		data = data.replaceAll("\n", "");
//		StringBuffer buffer = new StringBuffer();
//		buffer.append("<?xml version=\"1.0\" encoding = \"GBK\"?>");
//		buffer.append("<IN>");
//		buffer.append(data);
//		buffer.append("</IN>");
//		result = buffer.toString();
//		logger.log("组合"+root.getC_ASS_CODE()+":加密成功（加密方式:"+secretType+";加密密钥:"+pojo.getSecretKey()+"报文编码:"+charSet+"）!");
//		logger.debug("加密前:"+sendStr);
//		logger.debug("加密后:"+result);
//		return result;
//	}
	
	/**
	 * 
	 * @param pojo	加密参数
	 * @param context 需要加密的报文
	 * @return 加密后的数据
	 * @throws IOException 
	 */
	public static String zip(TransPojo pojo,String context) throws Exception
	{
		String result = "";
		//加密方式，默认BASE64
		String secretType = pojo.getSecretType();
		secretType = StringUtil.IsNullOrEmptyT(secretType) ? SecretTypeCons.BASE64 : secretType;
		//编码方式，默认GBK
		String charSet = pojo.getCharSet();
		charSet = StringUtil.IsNullOrEmptyT(charSet)? "GBK" : charSet;
		//解密key
		String key = SecretKeyUtil.decryptSecretKey(pojo.getSecretKey());
		//不需要加密的
		if(SecretTypeCons.NO_SECRET.equals(secretType))
		{
			reportLogger.log("加密成功（加密方式:"+secretType+";加密密钥:"+key+"报文编码:"+charSet+"）!");
			reportLogger.debug("加密前:"+context);
			reportLogger.debug("加密后:"+result);
			return context;
		}
		
		//BUG #145555 南方基金-发送招行基金申购认购指令伺服器返回解密数据出错 bylfd
		if(SecretTypeCons.AES_ECB_CS5P.equals(secretType) && !(context.contains("<FILE_TYPE>1521") || context.contains("<FILE_TYPE>1522"))){
			//BUG255166大成基金-渣打银行电子指令加密报文不正确	不再判断报文标签
			// 民生银行发送报文使用AES方式加密RECORD标签中的内容
			//if(context.contains("<IN>") && context.contains("<FUND_NAME>")){
				//BUG #203557 民生银行秘钥文件放到yss_app/transfer 后，所有银行都要求读取AESPWD.properties 不合理，需处理
				EncryptPojo encryptPojo = getElementValue(context, "RECORD");
				if(encryptPojo == null || StringUtil.IsNullOrEmptyT(encryptPojo.getEncryptStr()))
				{
					reportLogger.log("加密失败,没有需要加密的数据！（加密方式:"+secretType+";加密密钥:"+key+"报文编码:"+charSet+"）!");
					reportLogger.debug("加密前:"+context);
					reportLogger.debug("加密后:"+context);
					return context;
				}
				result = encryptData(secretType, key, encryptPojo.getEncryptStr(), charSet);
				reportLogger.log("加密成功（加密方式:"+secretType+";加密密钥:"+key+"报文编码:"+charSet+"）!");
				reportLogger.debug("加密前:"+context);
				reportLogger.debug("加密后:"+result);
				return encryptPojo.getPrefixStr()+result+encryptPojo.getSuffix();
			//}
			// <OUT>报文不用加解密
		}
		if(context.contains("<OUT>") && context.contains("<FUND_NAME>")){
			reportLogger.log("加密失败，<OUT>报文不用加解密！（加密方式:"+secretType+";加密密钥:"+key+"报文编码:"+charSet+"）!");
			reportLogger.debug("加密前:"+context);
			reportLogger.debug("加密后:"+context);
			return context;
		}
		//取出需要加密的数据
		EncryptPojo encryptPojo = getEncryPojo(context);
		//没有需要加密的数据
		if(encryptPojo == null || StringUtil.IsNullOrEmptyT(encryptPojo.getEncryptStr()))
		{
			reportLogger.log("加密失败,没有需要加密的数据！（加密方式:"+secretType+";加密密钥:"+key+"报文编码:"+charSet+"）!");
			reportLogger.debug("加密前:"+context);
			reportLogger.debug("加密后:"+context);
			return context;
		}
		result = encryptData(secretType, key, encryptPojo.getEncryptStr(), charSet);
		reportLogger.log("加密成功（加密方式:"+secretType+";加密密钥:"+key+"报文编码:"+charSet+"）!");
		reportLogger.debug("加密前:"+context);
		reportLogger.debug("加密后:"+result);
		return encryptPojo.getPrefixStr()+result+encryptPojo.getSuffix();
	}
	/**
	 * 加密数据
	 * @param secretType
	 * @param key
	 * @param encryptStr
	 * @param charSet
	 * @return
	 * @throws IOException
	 */
	private static String encryptData(String secretType,String key,String encryptStr,String charSet) throws IOException
	{
		String result = "";
		if(SecretTypeCons.AES_ECB_CS5P.equals(secretType))//AES加密
		{
			result = AESUtil.encryptData(encryptStr,charSet,key);
		}
		else if(SecretTypeCons.NO_SECRET.equals(secretType)) //不加密
		{
			result = encryptStr;
		}
		else//Base64位压缩
		{
			result = StringUtil.compress(encryptStr, charSet);
			result = result.replaceAll("\r\n", "");
			result = result.replaceAll("\n", "");
		}
		return result;
	}
	
	/**
	 * 解密数据
	 * @param secretType
	 * @param key
	 * @param encryptStr
	 * @param charSet
	 * @return 解密后的数据
	 * @throws IOException
	 */
	private static String decryptData(String secretType,String key,String encryptStr,String charSet) throws Exception
	{
		String result = "";
		if(SecretTypeCons.AES_ECB_CS5P.equals(secretType))//AES解密
		{
			result = AESUtil.decryptData(encryptStr,charSet,key);
		}
		else if(SecretTypeCons.NO_SECRET.equals(secretType)) //不解密
		{
			result = encryptStr;
		}
		else if(SecretTypeCons.BASE64.equals(secretType))//Base64位解压缩
		{
			result = StringUtil.uncompress(encryptStr, charSet);
		}else
		{
			throw new Exception("不支持的解密格式");
		}
		return result;
	}
	/**
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * @param pojo	加密参数
	 * @param context 需要加密的报文
	 * @return 加密后的数据
	 * @throws IOException 
	 */
	public static String unZip(TransPojo pojo,String context) throws Exception
	{
		String result = "";
		//加密方式，默认BASE64
		String secretType = pojo.getSecretType();
		secretType = StringUtil.IsNullOrEmptyT(secretType) ? SecretTypeCons.BASE64 
						: secretType;
		String charSet = pojo.getCharSet();
		//编码方式，空值默认GBK
		charSet = StringUtil.IsNullOrEmptyT(charSet)? "GBK" : charSet;
		//解密key
		String key = SecretKeyUtil.decryptSecretKey(pojo.getSecretKey());
		//不需要加密的
		if(SecretTypeCons.NO_SECRET.equals(secretType))
		{
			reportLogger.log("解密成功（解密方式:"+secretType+";解密密钥:"+key+"报文编码:"+charSet+"）!");
			reportLogger.debug("解密前:"+context);
			reportLogger.debug("解密后:"+context);
			return context;
		}
		// STORY #28382 民生银行返回报文不需解密
		if(context.contains("<OUT>") && context.contains("<FUND_NAME>")){
			reportLogger.log("解密失败，民生银行返回报文不需解密！（解密方式:"+secretType+";解密密钥:"+key+"报文编码:"+charSet+"）!");
			reportLogger.debug("解密前:"+context);
			reportLogger.debug("解密后:"+context);
			return context;
		}
		//BUG #145555 南方基金-发送招行基金申购认购指令伺服器返回解密数据出错 bylfd
		if(SecretTypeCons.AES_ECB_CS5P.equals(secretType) && !(context.contains("<FILE_TYPE>1521") || context.contains("<FILE_TYPE>1522"))){
			//BUG255166大成基金-渣打银行电子指令加密报文不正确	不再判断报文标签
			// <IN>报文中解密RECORD标签内容
			//if(context.contains("<IN>") && context.contains("<FUND_NAME>")){
				/*STORY #31010 民生银行嘉实资本的公司ID和密钥是分开的*/
				EncryptPojo encryptPojo = getElementValue(context, "RECORD");
				if(encryptPojo == null || StringUtil.IsNullOrEmptyT(encryptPojo.getEncryptStr()))
				{
					reportLogger.log("解密失败，没有需要解密的数据！（解密方式:"+secretType+";解密密钥:"+key+"报文编码:"+charSet+"）!");
					reportLogger.debug("解密前:"+context);
					reportLogger.debug("解密后:"+context);
					return context;
				}
				result = decryptData(secretType, key, encryptPojo.getEncryptStr(), charSet);
				reportLogger.log("解密成功（解密方式:"+secretType+";解密密钥:"+key+"报文编码:"+charSet+"）!");
				reportLogger.debug("解密前:"+context);
				reportLogger.debug("解密后:"+result);
				return encryptPojo.getPrefixStr()+result+encryptPojo.getSuffix();
			//}
		}
		//取出需要加密的数据
		EncryptPojo encryptPojo = getEncryPojo(context);
		//没有需要加密的数据
		if(encryptPojo == null || StringUtil.IsNullOrEmptyT(encryptPojo.getEncryptStr()))
		{
			reportLogger.log("解密失败，没有需要解密的数据！（解密方式:"+secretType+";解密密钥:"+key+"报文编码:"+charSet+"）!");
			reportLogger.debug("解密前:"+context);
			reportLogger.debug("解密后:"+context);
			return context;
		}
		result = decryptData(secretType, key, encryptPojo.getEncryptStr(), charSet);
		reportLogger.log("解密成功（解密方式:"+secretType+";解密密钥:"+key+"报文编码:"+charSet+"）!");
		reportLogger.debug("解密前:"+context);
		reportLogger.debug("解密后:"+result);
		return encryptPojo.getPrefixStr()+result+encryptPojo.getSuffix();		
	}
	/**
	 * 如果pojo中没有加密方式，通过源用户，源APPID，目标用户，目标应用id取加密信息
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * @param pojo
	 */
	public static void initTransPojo(TransPojo pojo) {
		if(!StringUtil.IsNullOrEmptyT(pojo.getSecretType()))
		{
			return;
		}
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer(" SELECT C_SECRETKEY,C_DV_SECRETTYPE,C_DV_CHARSET FROM T_D_ER_RELA  where  ");
		sql.append(" C_TARGET_USER = ? ");
		sql.append(" and C_TARGET_APP_LOGO = ? ");
		sql.append(" and exists (select 1 FROM T_D_ER_PARA P where P.C_SRC_USERID = ? and P.C_SRC_APPID = ? ) ");
		try {
			conn = DbPoolFactory.getInstance().getPool().getConnection();
			stat = conn.prepareStatement(sql.toString());
			int i = 1;
			if("-1".equalsIgnoreCase(pojo.getResult()))
			{
				stat.setString(i++, pojo.getFromUser());
				stat.setString(i++, pojo.getFromApp());
				stat.setString(i++, pojo.getToUser());
				stat.setString(i++, pojo.getToApp());
			}else
			{
				stat.setString(i++, pojo.getToUser());
				stat.setString(i++, pojo.getToApp());
				stat.setString(i++, pojo.getFromUser());
				stat.setString(i++, pojo.getFromApp());
			}
			rs = stat.executeQuery();
			if (rs.next()) {
				pojo.setSecretKey(rs.getString("C_SECRETKEY"));
				pojo.setSecretType(rs.getString("C_DV_SECRETTYPE"));
				pojo.setCharSet(rs.getString("C_DV_CHARSET"));
			}
		} catch (Exception e) {
			logger.log(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			DbFun.releaseConnection(conn);
		}
	}
	
	/**
	 * BUG #305577 银华基金-估值核算-【300.7-1130】民生电子对账无法接收
	 * 不同的业务系统独立部署，设置多个电子对账和电子指令用的userId和appid完全一样
	 * @param pojo
	 * @return
	 */
	public static List<TransPojo> initTransPojoList(TransPojo pojo) {
		List<TransPojo> pojoList = new ArrayList<TransPojo>();
		if(!StringUtil.IsNullOrEmptyT(pojo.getSecretType())){
			return pojoList;
		}
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer(" SELECT C_SECRETKEY,C_DV_SECRETTYPE,C_DV_CHARSET FROM T_D_ER_RELA  where  ");
		sql.append(" C_TARGET_USER = ? ");
		sql.append(" and C_TARGET_APP_LOGO = ? ");
		sql.append(" and exists (select 1 FROM T_D_ER_PARA P where P.C_SRC_USERID = ? and P.C_SRC_APPID = ? ) ");
		try {
			conn = DbPoolFactory.getInstance().getPool().getConnection();
			stat = conn.prepareStatement(sql.toString());
			int i = 1;
			if("-1".equalsIgnoreCase(pojo.getResult()))
			{
				stat.setString(i++, pojo.getFromUser());
				stat.setString(i++, pojo.getFromApp());
				stat.setString(i++, pojo.getToUser());
				stat.setString(i++, pojo.getToApp());
			}else
			{
				stat.setString(i++, pojo.getToUser());
				stat.setString(i++, pojo.getToApp());
				stat.setString(i++, pojo.getFromUser());
				stat.setString(i++, pojo.getFromApp());
			}
			rs = stat.executeQuery();
			TransPojo pojoTmp = null;
			while (rs.next()) {
				pojoTmp = new TransPojo();
				pojoTmp.setSecretKey(rs.getString("C_SECRETKEY"));
				pojoTmp.setSecretType(rs.getString("C_DV_SECRETTYPE"));
				pojoTmp.setCharSet(rs.getString("C_DV_CHARSET"));
				pojoList.add(pojoTmp);
			}
		} catch (Exception e) {
			logger.log(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			DbFun.releaseConnection(conn);
		}
		
		return pojoList;
	}
	
	/**
	 * 将报文拆分成前缀+加密报文+后缀
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * @param fileContent  报文数据
	 * @return
	 */
	public static EncryptPojo getEncryPojo(String fileContent) {
		EncryptPojo pojo = new EncryptPojo();
		String[] tags = new String[]{"IN","OUT","ap"};
		for(String tag : tags)
		{
			pojo = getElementValue(fileContent, tag);
			if(pojo != null)
			{
				return pojo;
			}
		}
		return null;
	}
	/** 获取xml标签之间的值
	 * @param fileContent 报文
	 * @param tagName 标签名称
	 * @return 标签值
	 */
	public static EncryptPojo getElementValue(String fileContent,String tagName){
		//捕获标签值，<tagName>(.*)</tagName>
		StringBuffer regex = new StringBuffer("<").append(tagName)
				.append(">([\\w\\W]*)</").append(tagName).append(">");
		Pattern pattern = Pattern.compile(regex.toString());
		Matcher matcher = pattern.matcher(fileContent);
		if(matcher.find())
		{
			EncryptPojo pojo = new EncryptPojo();
			pojo.setEncryptStr(matcher.group(1));//标签值
			pojo.setPrefixStr(fileContent.substring(0, matcher.start(1)));//前缀
			pojo.setSuffix(fileContent.substring(matcher.end(1)));//后缀
			return pojo;
		}
		return null;
	}
	
	/**
	 * 生成一个唯一的消息ID
	 * @return
	 */
	public static String genePrimaryKey()
	{
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
}
