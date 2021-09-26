package com.yss.ams.sec.information.modules.sv.base.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 封装setWhereSql方法
 * @author CL 20121120
 *
 */
public class SecBaseCondition {
		
	/**
	 * 设置查询条件
	 * 注意：调用setWhereSql方法时注意前台传参个数
	 * @param buf
	 * @param paraNameList
	 */
	public void setWhereSql(StringBuffer buf, List<String> paraNameList)
	{ 
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		
		StringBuffer orBuf = new StringBuffer();
		boolean isOrBuf = false;
		orBuf.append(" ( ");
		for (String fieldedName : paraNameList){
			if(fieldedName.equals("C_SEC_CODE")){
				orBuf.append(" a.C_SEC_CODE like ? OR ");
				isOrBuf = true;
			}else if(fieldedName.equals("C_SEC_MKT_CODE")){
				orBuf.append(" a.C_SEC_MKT_CODE like ? OR ");
				isOrBuf = true;
			}else if(fieldedName.equals("C_SEC_ISIN_CODE")){
				orBuf.append(" a.C_SEC_ISIN_CODE like ? OR ");
				isOrBuf = true;
			}
		}
		
		StringUtil.delLastSplitMark(orBuf, "OR ");
		
		if(isOrBuf){
			orBuf.append(" ) AND ");
			buf.append(orBuf);
		}else{
			StringUtil.clearStringBuffer(orBuf);
		}
		
		for (String fieldedName : paraNameList)
		{
			if (fieldedName.equals("ARRAY_C_MKT_CODE"))
			{
				buf.append("C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
			else if (fieldedName.equals("C_DA_CODE"))
			{
				buf.append("b.C_DA_CODE like ? AND ");
			}
			else if (fieldedName.equals("C_SEC_CODE_TRG"))
			{
				buf.append("a.C_SEC_CODE_TRG like ? AND ");
			}
			else if (fieldedName.equals(("C_SEC_NAME")))
			{
				buf.append("a.C_SEC_NAME like ? AND ");
			}
			else if (fieldedName.equals("C_SEC_VAR_CODE"))
			{
				buf.append("a.C_SEC_VAR_CODE like ? AND ");
			}
			else if (fieldedName.equals("C_DEL_TIME_START"))
			{
				buf.append("a.C_DEL_TIME >= ? AND ");
			}
			else if (fieldedName.equals("C_DEL_TIME_END"))
			{
				buf.append("a.C_DEL_TIME <= ? AND ");
			}
			else if(fieldedName.equals("D_TO_LIST")){
				buf.append("a.D_TO_LIST = to_date(?,'yyyy-MM-dd')  AND ");
			}
			else if(fieldedName.equals("D_OFF_LIST")){
				buf.append("a.D_OFF_LIST = to_date(?,'yyyy-MM-dd') AND ");
			}
			else if (fieldedName.equals("ORD_C_SEC_CODE"))
			{
				buf.append("a.C_SEC_CODE = ? AND ");
			}
			else if (fieldedName.equals("C_ORG_CODE"))
			{
				// 添加新的查询条件byleeyu20150105
				buf.append("a.C_ORG_CODE = ? AND ");
			} 
			// 合并代码： STORY #97948 富国基金-债券基本信息界面新增制作时间和审核时间的查询条件选项
			else if(fieldedName.equals("D_CREATE_BEGIN"))
			{
				buf.append(" a.C_UPDATE_TIME >= ? AND ");
			}	
			else if(fieldedName.equals("D_CREATE_END"))
			{
				buf.append(" a.C_UPDATE_TIME <= ? AND ");
			}	
			else if(fieldedName.equals("C_CHECK_BEGIN"))
			{
				buf.append(" a.C_CHECK_TIME >= ? AND ");
			}
			else if(fieldedName.equals("C_CHECK_END"))
			{
				buf.append(" a.C_CHECK_TIME <= ? AND ");
			}
		}
		StringUtil.delLastSplitMark(buf, "AND ");
	}
}
