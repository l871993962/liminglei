package com.yss.ifa.szt.tool.para.service;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.ifa.szt.tool.pojo.DzPara;
@RestfulSupported
public interface IDzParaService extends IServiceBus {
//	DzPara getParaByPort(String portCode);
//	DzPara getParaByAssCode(String assCode);
	/**
	 * 根据资产代码查询电子对账参数设置-资托互动
	 * add by chenyoucai 2018-9-13 STORY #30828 资管和托管互动
	 * @return
	 */
	DzPara getParaByAssCode(String c_ASS_CODE);
	/**
	 * 将密钥加密
	 * @param encryptStr
	 * @param charSet
	 * @param key
	 * @return 加密后的密钥
	 */
	public String encryptData(String encryptStr)throws Exception;
	/**
	 * 将密钥解密
	 * @param encryptStr
	 * @param charSet
	 * @param key
	 * @return 解密后的密钥
	 */
	public String decryptData(String encryptStr)throws Exception;
}
