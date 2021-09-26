
package com.yss.ams.sec.information.support.util;

import java.text.DecimalFormat;

import org.wltea.expression.ExpressionEvaluator;

/**
 * @classDesc 核算计算过程的工作类
 * @version 1.0 2012-9-6
 * @author yh
 */
public class ComputeUtil {

	/**
	 * 根据汇率表达式计算本位币金额，其中防止因为科学计数法导致计算出错
	 * @param origMoney 原币金额
	 * @param er 汇率表达式
	 * @return
	 */
	public static Double calcPortMoneyBaseErExpr(double origMoney,String erExpr){
		double portMoney = 0;
		if(null == erExpr)
			return portMoney;
		java.text.NumberFormat nf = new DecimalFormat();
		nf.setGroupingUsed(false);
		nf.setMinimumFractionDigits(12);
		portMoney = (Double)ExpressionEvaluator.evaluate(nf.format(origMoney) + "*" + erExpr);
		return portMoney;
	}

	/**
	 * 计算汇率表达式
	 * @param erExpr
	 * @return
	 */
	public static Double calcErExpr(String erExpr){
		double money = 0;
		if(null != erExpr)
			money = calcPortMoneyBaseErExpr(1.0, erExpr);
		return money;
	}

}
