package com.yss.ifa.szt.tool.util;

import com.yss.framework.api.util.StringUtil;
import com.yss.ifa.szt.tool.encrypt.sm.SM4Utils;

public class SecretKeyUtil {
	/**
	 * STORY #60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * 加密密钥的key
	 * 16位
	 */
	public final static String SECRET_KEY = "ELEC_AES_USE_KEY";
	
	/**
	 * STORY #60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * 加密密钥的key
	 * 16位
	 */
	public final static String SECRET_KEY_CHARSET = "UTF-8";
	
	/**
	 * 将密钥解密
	 * @param encryptStr
	 * @param charSet
	 * @param key
	 * @return 解密后的密钥
	 * @throws Exception 
	 */
	public static String decryptSecretKey(String key) throws Exception  {
		if(StringUtil.IsNullOrEmptyT(key))
		{
			return key;
		}
		////AES换成SM4
		//return AESUtil.decryptData(key, SecretKeyUtil.SECRET_KEY_CHARSET,SecretKeyUtil.SECRET_KEY);
		SM4Utils sm4 = new SM4Utils();  
        sm4.secretKey = SecretKeyUtil.SECRET_KEY;  
        sm4.hexString = false; 
        return sm4.decryptData_ECB(key);
	}
	
	/**
	 * 将密钥解密
	 * @param encryptStr
	 * @param charSet
	 * @param key
	 * @return 解密后的密钥
	 * @throws Exception 
	 */
	public static String encryptSecretKey(String key) throws Exception  {
		if(StringUtil.IsNullOrEmptyT(key))
		{
			return key;
		}
		//AES换成SM4
		//return AESUtil.encryptData(key, SecretKeyUtil.SECRET_KEY_CHARSET,SecretKeyUtil.SECRET_KEY);
		SM4Utils sm4 = new SM4Utils();  
        sm4.secretKey = SecretKeyUtil.SECRET_KEY;  
        sm4.hexString = false; 
        return sm4.encryptData_ECB(key); 
	}
	
}
