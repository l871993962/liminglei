package com.yss.uco.elecreco.support.dzdz.common;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;

/**
 * 
 * @author 科目代码转换
 * 
 */
public class RecordElement {
	/**
	 * 参数键值对
	 * wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）产品估值参数“是否将科目末位为五位补齐为六位进行对账”，  1为是；0为否 默认为是
	 */
	protected Map<String, String> paraMap = new HashMap<String, String>();
	@XmlTransient
	public Map<String, String> getParaMap() {
		return paraMap;
	}

	public void setParaMap(Map<String, String> paraMap) {
		this.paraMap = paraMap;
	}

	public String getKmCode(String kmCode) throws Exception {
		String newCode = kmCode;
		//BUG204965【建信基金】电子对账发送失败   获取明细数据出错：[DZ2018053001141:1031:536315]没有生成明细数据或数据格式有问题
		try{
			if (kmCode.contains(".")) {
				newCode = "";
				String s[] = kmCode.split("\\.");
				for (int i = 0; i < s.length; i++) {

					if (StringUtil.isInteger(s[i])) {
						////正常的科目代码
						newCode += s[i];
					} else if (s[i].contains(" ")) {

						//Orlando 20150909  增加trycatch输出错误日志以便确定是哪个科目有问题
						try{
							String code = "";
							////证券代码
							///4226规则的科目 判断最后一级科目，是否有6位  期货只有5位
							code = s[i].split(" ")[0];
							
							/**
							 * STORY #91033 【鹏华基金】境外交易证券发送电子对账时证券代码问题优化 
							 * 【电子对账综合参数】新增参数“电子对账估值表境外债券科目代码使用ISIN码匹配”
							 *	参数属性：下拉单选，默认值：否
							 *	是：估值表对账时，境外债券科目将证券内码转换为ISIN码。
							 *	否：系统现有规则
							 */
							String isinCode = "";
							if(paraMap != null && "1".equalsIgnoreCase(paraMap.get("SV_BB_DZDZ_KMISIN"))){
								ISecBaseService secBaseService = YssServiceFactory.getInstance().createService(ISecBaseService.class);
								HashMap<String, Object> paraMap = new HashMap<String, Object>();
								paraMap.put("C_SEC_CODE", s[i]);
								paraMap.put("dataClass", "SecBase");
								QueryRes res = secBaseService.queryByCondition(paraMap);
								if(res != null && res.getDataList() != null && res.getDataList().size() > 0){
									for (BasePojo basePojo : res.getDataList()) {
										SecBase sec = (SecBase) basePojo;
										if(!StringUtil.IsNullOrEmptyT(sec.getC_SEC_ISIN_CODE())){
											isinCode = sec.getC_SEC_ISIN_CODE();
										}
									}
								}
							}
							if (StringUtil.IsNullOrEmptyT(isinCode)) {
								if (s.length == 4 && i == 3 && code.length() < 6) {
									// wlx 20171228
									// STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）产品估值参数“是否将科目末位为五位补齐为六位进行对账”，
									// 1为是；0为否 默认为是
									if (paraMap != null && "1".equalsIgnoreCase(paraMap.get("SV_BB_DZDZ_KMBW"))) {
										for (int j = 0; j < 6 - code.length(); j++) {
											newCode += "0";
										}
									}
								}
								
								//STORY #103067 【长江养老】增加参数控制区分深港通和沪港通股票发送电子对账
								if(paraMap != null && "1".equalsIgnoreCase(paraMap.get("DZ_BB_DZDZ_SENDSCJM")) 
										&& (s[i].contains("HG") || s[i].contains("HS"))){
									if(s[i].contains("HG")){
										code = code + "HG";
									}
									
									if(s[i].contains("HS")){
										code = code + "HS";
									}
								}
							} else {
								code = isinCode;
							}
							
							newCode += code;
						}
						catch(Exception ex){
							Logger logger = LogManager.getLogger(getClass());
							logger.error("科目代码["+kmCode+"]解析出错:"+ex.getMessage(),ex);
							throw ex;
						}
						//				} else if (s[i].contains("_")) {//BUG140428电子对账，发送科目表后，出现科目重复  将下划线去掉之后科目代码重复 比如1002.01.17_01 变成1002.01.01
						//					////分级组合
						//					newCode += s[i].split("_")[1];
					} else if(!s[i].contains("<")){
						////备付金科目，1021.QH05
						///4226规则的科目 判断最后一级科目，是否有6位  期货只有5位
						if(s.length == 4 && i == 3 && s[i].length() < 6){
							//wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）产品估值参数“是否将科目末位为五位补齐为六位进行对账”，  1为是；0为否 默认为是
							if(paraMap != null && "1".equalsIgnoreCase(paraMap.get("SV_BB_DZDZ_KMBW"))){
								for(int j = 0;j < 6-s[i].length();j++){
									newCode += "0";
								}
							}
						}

						newCode += s[i];
					}
				}
			} else if (newCode.equalsIgnoreCase("[root]")) {
				newCode = "-1";
			}
		}catch(Throwable ex){
			Logger logger = LogManager.getLogger(getClass());
			logger.error("科目代码["+kmCode+"]解析出错:"+ex.getMessage(),ex);
			newCode="-1";
		}
		
		return newCode;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	protected String getDoubleValue(String val) {
		DecimalFormat nFormat = new DecimalFormat("#####.##");
		nFormat.setMaximumFractionDigits(6);// 设置小数点后面位数为
		nFormat.setMinimumFractionDigits(6);
		return nFormat.format(new Double(val));
	}
}
