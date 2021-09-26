package com.yss.uco.elecreco.er.generate.util;

import java.math.BigDecimal;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;

/**
 * 库存统计工具类
 * @author chenyoulong 20120711
 * @version v4.5.0.1
 */
public class StatsUtil {
	/**
	 * 根据保留位数和截位方式格式化实收资本数量
	 * add by qiantao STORY #83025 产品估值参数控制实收资本小数位 
	 * @param value 实收资本数量
	 * @param jwfs 截位方式
	 * @param blws 保留位数
	 * @return
	 * @throws YssException 
	 */
	public static String formatSSZBValue(double value,int blws,String jwfs)
	{
		if(StringUtil.IsNullOrEmpty(jwfs))
		{
			jwfs = "JW_ROUND_SSZB";
		}
		String strformat = "#0.";
		
		for(int i=0;i<blws;i++){
			strformat += "0";
		}
		
		if (jwfs.equalsIgnoreCase("JW_TRUNC_SSZB")) {
			// 截位
			value = YssFun.roundIt(value, blws, true);
		} else if (jwfs.equalsIgnoreCase("JW_ZSFR_SSZB")) {
			// 正舍负入
			if (value<0) {
				value = YssFun.roundIt(value, blws, false);
			} else {
				value = YssFun.roundIt(value, blws, true);
			}
		} else if (jwfs.equalsIgnoreCase("JW_ROUND_SIX_SSZB")){
				value = StatsUtil.calRoundSixMethod(value,blws).doubleValue();
		}else {
			// 四舍五入
			value = YssFun.roundIt(value, blws, false);
		}
		
		String formatValue = YssFun.formatNumber(value, strformat);
		return formatValue;
	}
	
	/**
	 * 四舍六入五成双计算法 
	 * @param amount
	 * @param blws
	 * @return
	 * @throws YssException 
	 */
	public static BigDecimal calRoundSixMethod(double amount, int blws) 
	{
		BigDecimal result = BigDecimal.valueOf(0);  
	    try {
	    	//通过保留位数先扩值，最后再缩值
	        double ratio = Math.pow(10, blws);  
	        double num = amount * ratio;  
	        double mod = num % 1;  
	        double integer = Math.floor(num);  
	        double returnNum;  
	        if(mod > 0.5){  
	            returnNum=(integer + 1) / ratio;  
	        }else if(mod < 0.5){  
	            returnNum=integer / ratio;  
	        }else{  
	            returnNum=(integer % 2 == 0 ? integer : integer + 1) / ratio;  
	        }  
	        BigDecimal bg = new BigDecimal(returnNum);   
	        result = bg.setScale(blws, BigDecimal.ROUND_HALF_UP);  
	    } catch (Exception e) {  
	    	Logger logger = LogManager.getLogger("StatsUtil");
	    	logger.error("四舍六入五成双计算法计算异常!", e);
	    	result = BigDecimal.valueOf(0);  
	    }  
	    return result; 
	}
}
