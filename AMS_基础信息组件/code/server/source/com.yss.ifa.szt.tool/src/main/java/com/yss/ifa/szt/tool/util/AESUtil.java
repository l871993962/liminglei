package com.yss.ifa.szt.tool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * @author limh
 * BUG #130668 YSS-深证通发送完民生银行余额明细查询后无法解析返回报文，更新民生银行加密方式
 */
public class AESUtil {
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	private static AESUtil instance = null;
	/**
	 * 通用密钥
	 */
	private String secretCode = "";
	/**
	 * 特殊资产类型
	 */
	private String specialAssType = "";
	/**
	 * 特殊密钥
	 */
	private String specialSecretCode = "";
	/**
	 * 特殊公司id
	 */
	private String specialDeptId = "";
	
	private AESUtil(){
		/*读取密码*/
		Properties properties = new Properties();
		try {
			FileStorePathUtil fileStorePathUtil = new FileStorePathUtil("");
			String yssAppPath = fileStorePathUtil.getFilePath();
			File f = new File(yssAppPath+"/transfer/AESPWD.properties");   
//			InputStream in = AESUtil.class.getClassLoader()
//					.getResourceAsStream("dzdz/common/AESPWD.properties");
			InputStream in = new FileInputStream(f);
			properties.load(in);
			if(in != null){
				in.close();
			}
			secretCode = properties.getProperty("AESPWD");
			specialAssType = properties.getProperty("SPC_ASS_TYPE");
			specialSecretCode = properties.getProperty("SPC_AESPWD");
			specialDeptId = properties.getProperty("SPC_DEPT_ID");
		} catch (IOException e) {
			throw new RuntimeException("读取民生加密密码失败");
		}
	}
	
	public static AESUtil getInstance(){
		if(instance == null){
			instance = new AESUtil();
		}
		return instance;
	}
	
	/*
	 * 读取秘钥文件
	 */
	public void readAespwd(){
		/*读取密码*/
		Properties properties = new Properties();
		try {
			FileStorePathUtil fileStorePathUtil = new FileStorePathUtil("");
			String yssAppPath = fileStorePathUtil.getFilePath();
			File f = new File(yssAppPath+"/transfer/AESPWD.properties");   
//			InputStream in = AESUtil.class.getClassLoader()
//					.getResourceAsStream("dzdz/common/AESPWD.properties");
			InputStream in = new FileInputStream(f);
			properties.load(in);
			if(in != null){
				in.close();
			}
		
			secretCode = properties.getProperty("AESPWD");
		    specialAssType = properties.getProperty("SPC_ASS_TYPE");
		    specialSecretCode = properties.getProperty("SPC_AESPWD");
		    specialDeptId = properties.getProperty("SPC_DEPT_ID");
		} catch (IOException e) {
			throw new RuntimeException("读取民生加密密码失败");
		}
	}
	
	/**
	 * BUG #163036 发送指令，指令状态显示发送中，后台日志报解析日期出错
	 * 博时基金升级配置了两个秘钥，需替换序列号SEQ_NO中的部门号，例如报文中发送的序列号是：ZLSBSZB20170619451，数据库中存的是：ZLSBSJJ20170619451
	 * @param xml
	 * @return
	 */
	public String replaceDeptcode(String xml){
		logger.log("replaceDeptcode(),xml="+xml);
		try{
			//读取秘钥文件
			readAespwd();
			String fundId = this.getXmlElementValue(xml, "FUND_ID");
			IPortDataService service = YssServiceFactory.getInstance().createService(IPortDataService.class);
			Port port = service.getDataByCode(fundId);
			String deptCode = "";
			/*若资产类型为特殊类型，则使用特殊密钥和公司ID*/
			/*BUG #190885【广发基金】民生银行报文加密解密失败     port加上非空判断*/
			if(!StringUtil.IsNullOrEmpty(specialAssType) 
					&& null != port && specialAssType.contains(port.getC_DAT_CODE())){
				if(xml.contains("<SEQ_NO>")){
					deptCode = this.getXmlElementValue(xml, "DEPT_CODE");
					// 修改指令编号SEQ_NO  
					String seqNo = this.getXmlElementValue(xml, "SEQ_NO");
					String before = "<SEQ_NO>"+seqNo+"</SEQ_NO>";
					String after = "<SEQ_NO>"+seqNo.replaceFirst(deptCode, specialDeptId)+"</SEQ_NO>";
					xml = xml.replace(before, after);
				}			
			}
			logger.log("deptCode="+deptCode+",specialDeptId="+specialDeptId);
			logger.log("replaceDeptcode(),xml="+xml);
		}catch(Exception e){
			logger.log("博时基金升级配置了两个秘钥，需替换序列号SEQ_NO中的部门号",e);
		}
		return xml;
	}
	
	/**AES加密
	 * STORY #31010 民生银行嘉实资本的公司ID和密钥是分开的
	 * @param xml
	 * @return
	 * @throws Exception 
	 */
	public String AESEncode(String xml) {
		xml = xml.replaceAll("\t", "");// STORY #28382 去除发送报文中的空格
		logger.log("AESEncode(),xml="+xml);
		//读取秘钥文件
		readAespwd();
		if(StringUtil.IsNullOrEmpty(secretCode)){
			throw new RuntimeException("密钥未设置");
		}
		try {
			CmbcEncryptor cmbcEncryptor = new CmbcEncryptor();
			String fundId = this.getXmlElementValue(xml, "FUND_ID");
			IPortDataService service = YssServiceFactory.getInstance().createService(IPortDataService.class);
			Port port = service.getDataByCode(fundId);
			/*若资产类型为特殊类型，则使用特殊密钥和公司ID*/
			if(!StringUtil.IsNullOrEmpty(specialAssType) 
					&& null != port && specialAssType.contains(port.getC_DAT_CODE())){
				cmbcEncryptor.setPassword(specialSecretCode);
				// 修改公司代码DEPT_CODE
				String deptCode = this.getXmlElementValue(xml, "DEPT_CODE");
				String before = "<DEPT_CODE>"+deptCode+"</DEPT_CODE>";
				String after = "<DEPT_CODE>"+specialDeptId+"</DEPT_CODE>";
				xml = xml.replace(before, after);
				// 修改业务流水号SERIAL_NO  ：DEPT_CODE+HK+……
				String serialNo = this.getXmlElementValue(xml, "SERIAL_NO");
				before = "<SERIAL_NO>"+serialNo+"</SERIAL_NO>";
				after = "<SERIAL_NO>"+serialNo.replaceFirst(deptCode, specialDeptId)+"</SERIAL_NO>";
				xml = xml.replace(before, after);
				// 修改指令编号SEQ_NO  :ZLS+DEPT_CODE+……
				String seqNo = this.getXmlElementValue(xml, "SEQ_NO");
				before = "<SEQ_NO>"+seqNo+"</SEQ_NO>";
				after = "<SEQ_NO>"+seqNo.replaceFirst(deptCode, specialDeptId)+"</SEQ_NO>";
				xml = xml.replace(before, after);
			}else{
				cmbcEncryptor.setPassword(secretCode);
			}
			
			String record = getXmlElementValue(xml,"RECORD");
			logger.log("AESEncode(),record="+record);
			String encodedRecord = cmbcEncryptor.encrypt(record);
			logger.log("AESEncode(),encodedRecord="+encodedRecord);
			xml = xml.replace(record, encodedRecord);
			logger.log("AESEncode(),xml="+xml);
		} catch (Exception e) {
			throw new ServiceException("加密民生报文失败",e);
		}
		return xml;
	}
	
	/**AES解密
	 * STORY #31010 民生银行嘉实资本的公司ID和密钥是分开的
	 * @param xml
	 * @return
	 * @throws Exception 
	 */
	public String AESDecode(String xml) {
		/* STORY #28382 已解密的数据不用解密 */
		if(xml.contains("<TIMESTMP>")){
			return xml;
		}
		//读取秘钥文件
		readAespwd();
		try {
			CmbcEncryptor cmbcEncryptor = new CmbcEncryptor();
			String fundId = this.getXmlElementValue(xml, "FUND_ID");
			IPortDataService service = YssServiceFactory.getInstance().createService(IPortDataService.class);
			Port port = service.getDataByCode(fundId);
			/*若资产类型为特殊类型，则使用特殊密钥和公司ID*/
			if(!StringUtil.IsNullOrEmpty(specialAssType) 
					&& null != port && specialAssType.contains(port.getC_DAT_CODE())){
				cmbcEncryptor.setPassword(specialSecretCode);
			}else{
				cmbcEncryptor.setPassword(secretCode);
			}
			String record = getXmlElementValue(xml,"RECORD");
			String encodedRecord = cmbcEncryptor.decrypt(record);
			xml = xml.replace(record, encodedRecord);
		} catch (Exception e) {
			throw new ServiceException("解密民生报文失败",e);
		}
		return xml;
	}
	
	/** 获取xml标签之间的值
	 * STORY #31010 民生银行嘉实资本的公司ID和密钥是分开的
	 * @param fileContent 报文
	 * @param element xml标签
	 * @return 标签值
	 */
	public String getXmlElementValue(String fileContent,String element){
		int beginIndex = fileContent.indexOf("<"+element+">") + element.length()+2;
		int endIndex = fileContent.indexOf("</"+element+">");
		if(endIndex > beginIndex){
			return fileContent.substring(beginIndex, endIndex);
		}else{
			return "";
		}
	}

	/**
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * AES解密数据
	 * @param data 需要解密的数据
	 * @param charSet 报文编码方式
	 * @param key 密钥
	 */
	public static String decryptData(String data, String charSet, String key) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(2, keySpec);
			byte[] res = cipher.doFinal(parseHexStr2Byte(data));
			return new String(res, charSet);
		} catch (Exception e) {
			throw new ServiceException("解密AES报文失败",e);
		}
	}

	/**
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * AES加密数据
	 * @param encryptStr
	 * @param charSet
	 * @param key
	 * @return
	 */
	public static String encryptData(String encryptStr, String charSet,
			String key)  {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(1, keySpec);
			byte[] res = cipher.doFinal(encryptStr.getBytes(charSet));
			return parseByte2HexStr(res);
		} catch (Exception e) {
			throw new ServiceException("加密AES报文失败",e);
		}
	}
	/**
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; ++i) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; ++i) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
