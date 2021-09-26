package com.yss.uco.elecreco.automic.cons;

import java.util.HashMap;
import java.util.Map;

public class ParamDZCons {

	public static final String DZ_TYPE = "dzType";
	/**
	 * 最新对账结果依据 STORY72797华宝兴业：检查对账结果节点新增参数判断是否检查最新的对账结果以及是否需要等待对账结果
	 */
	public static final String RESULT_NEW = "dzNewResult";
	/**
	 * 对账时间最新（最后一次接收）
	 */
	public static final String DZ_TIME_NEW = "DZ_TIME_NEW";
	/**
	 * 资产净值市值最新且最后一次接收
	 */
	public static final String PORT_MV_NEW = "PORT_MV_NEW";
	
	/**
	 * 资产净值市值最新且最后一次生成
	 */
	public static final String PORT_MV_NEW_SC = "PORT_MV_NEW_SC";
	
	/**
	 * 检查无结果允许通过参数
	 * wulongxing 20191023 STORY80030【鹏华基金】对账结果检查节点对反馈结果的检查增加无反馈结果的判断
	 */
	public static final String CheckNoResultPass = "CheckNoResultPass";
	/**
	 * 无结果路由词汇值
	 * wulongxing 20191023 STORY80030【鹏华基金】对账结果检查节点对反馈结果的检查增加无反馈结果的判断
	 */
	public static final String RESULT_NORESULT = "NoResult";
	
	/**
	 * 日期格式:yyyy-MM-dd
	 */
	public static final String FORMAT = "yyyy-MM-dd";
	
	/**
	 * key:开始日期
	 */
	public static final String KEY_D_START_DATE = "D_START_DATE";
	
	/**
	 * key:结束日期
	 */
	public static final String KEY_D_END_DATE = "D_END_DATE";
	
	/**
	 * 对账类型参数key
	 */
	public static final String KEY_ARRAY_C_DZ_CODE = "ARRAY_C_DZ_CODE";
	

	/**
	 * 对账一致RESULT_SAME
	 */
	public static final String RESULT_SAME = "RESULT_SAME";
	
	/**
	 * 对账不一致RESULT_DIFF
	 */
	public static final String RESULT_DIFF = "RESULT_DIFF";
	
	/**
	 * 无结果RESULT_NO
	 */
	public static final String RESULT_NO = "RESULT_NO";
	
	/**
	 * 特殊指标SelectZbInfo
	 */
	public static final String KEY_SPECIAL_ZB = "SelectZbInfo";
	
	/**
	 * 绿色通过条件SelectGreenWhere
	 */
	public static final String KEY_GREEN_WHERE = "SelectGreenWhere";
	
	/**
	 * 轮询终止条件SelectEndWhere
	 */
	public static final String KEY_END_WHERE = "SelectEndWhere";
	
	/**
	 * 超时/ 强制通过结果
	 */
	public static final String KEY_OVER_FORCE_RES = "overOrForceRes";
	
	/**
	 * 特殊指标检查条件
	 */
	public static final String KEY_CHECK_CONDITON = "SelectZbCheckCondition";
	
	/**
	 * 无法对账时的节点状态
	 */
	public static final String DZ_NODE_STATE = "dzNodeState";
	
	/**
	 * 失败
	 */
	public static final String FAIL = "FAIL";
	
	/**
	 * 警告
	 */
	public static final String WARNING = "WARNING";
	
	private static Map<String,String> kv = new HashMap<String, String>();
	
	static{
		kv.put(RESULT_SAME, "对账一致");
		kv.put(RESULT_DIFF, "对账不一致");
		kv.put(RESULT_NO, "无结果");
	}
	
	/**
	 * 根据值获取名称
	 * @param value
	 * @return
	 */
	public static String getNameByValue(String value)
	{
		if(kv.containsKey(value))
		{
			return kv.get(value);
		}else{
			return "";
		}
	}
	
}
