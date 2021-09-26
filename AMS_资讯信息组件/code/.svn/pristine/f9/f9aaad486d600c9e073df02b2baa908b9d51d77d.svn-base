package com.yss.ams.sec.information.modules.sv.secbasejf.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 计费证券信息sql处理类
 * @author yuankai 
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 */
public class SecBaseJfSqlBuilder implements SQLBuilder {

	/**
	 * 【计费证券信息】功能菜单LIST界面查询数量的SQL
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraNameList
	 * @return
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			// CL 20121120 setWhereSql 方法封装为公用方法，避免每个类中写一个
			// SecBaseCondition secbase = new SecBaseCondition();
			// secbase.setWhereSql(valueFieldbuf, paraNameList);
			
			// 由于参数中有C_PORT_CODE，公用方法中没有，故用自身的setWhereSql方法
			this.setWhereSql(valueFieldbuf, paraNameList);

			buf.append(" SELECT COUNT(*) AS CNT ");
			buf.append(" FROM T_P_SV_SEC_FEEINFO A ");
			buf.append(" LEFT JOIN (SELECT C_DA_CODE,C_SEC_VAR_CODE FROM V_S_DA_SEC_VAR ");
			buf.append("            WHERE C_SEC_VAR_CODE LIKE 'GP%' ");
			buf.append("            OR C_SEC_VAR_CODE LIKE 'ZQ%' ");
			buf.append("            OR C_SEC_VAR_CODE LIKE 'LC%' ");
			buf.append("            OR C_SEC_VAR_CODE LIKE 'QZ%' ");
			buf.append(" ) B ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE ");
			buf.append(" LEFT JOIN T_P_SV_SEC_BASE C ");
			buf.append(" ON A.C_SEC_CODE = C.C_SEC_CODE ");	

			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ");
				buf.append(valueFieldbuf);
			}

			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	/**
	 * 【计费证券信息】功能菜单LIST界面查询的SQL
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraNameList
	 * @return
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			// CL 20121120 setWhereSql 方法封装为公用方法，避免每个类中写一个
			// SecBaseCondition secbase = new SecBaseCondition();
			// secbase.setWhereSql(valueFieldbuf, paraNameList);
			
			// 由于参数中有C_PORT_CODE，公用方法中没有，故用自身的setWhereSql方法
			this.setWhereSql(valueFieldbuf, paraNameList);
			
			buf.append(" SELECT A.*, C.C_MKT_CODE "); // modified by HeLiang.2016-11-22.BUG #144950 运营费用-支持资产净值扣不计费证券需求
			buf.append(" FROM T_P_SV_SEC_FEEINFO A ");
			buf.append(" LEFT JOIN (SELECT C_DA_CODE,C_SEC_VAR_CODE FROM V_S_DA_SEC_VAR ");
			buf.append("            WHERE C_SEC_VAR_CODE LIKE 'GP%' ");
			buf.append("            OR C_SEC_VAR_CODE LIKE 'ZQ%' ");
			buf.append("            OR C_SEC_VAR_CODE LIKE 'LC%' ");
			buf.append("            OR C_SEC_VAR_CODE LIKE 'QZ%' ");
			buf.append(" ) B ON A.C_SEC_VAR_CODE = B.C_SEC_VAR_CODE ");
			buf.append(" LEFT JOIN T_P_SV_SEC_BASE C ");
			buf.append(" ON A.C_SEC_CODE = C.C_SEC_CODE ");			
			
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ");
				buf.append(valueFieldbuf);
				buf.append(" ORDER BY A.N_CHECK_STATE ASC, A.C_UPDATE_TIME DESC, A.C_CHECK_TIME DESC, A.C_IDEN ");
			}			
			
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
//			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}
	
//	public String getPortRelaChargingSecSql(List<String> paraNameList) {
//		StringBuffer buf = new StringBuffer();
//		buf.append(" SELECT A.* ");
//		buf.append(" FROM T_P_SV_SEC_BASE A ");
//		buf.append(" WHERE NOT EXISTS (SELECT * ");
//		buf.append("          FROM T_P_AB_PORT_RELA B ");
//		buf.append("          WHERE A.C_SEC_CODE = B.C_RELA_CODE ");
//		buf.append("          AND B.C_PORT_CODE = ? ");
//		buf.append("          AND B.N_CHECK_STATE >= 0) ");
//		buf.append("          AND A.N_CHECK_STATE = 1   ");
//		
//		return buf.toString();
//	}
	
	/**
	 * 嵌套窗体LIST界面查询计费证券信息的SQL
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraNameList
	 * @return
	 */
	public String getQueryRelaChargingSecSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.* FROM T_P_SV_SEC_FEEINFO A ");
		buf.append(" WHERE ");
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		buf.append(" A.C_SEC_CODE IN (SELECT * FROM TABLE(?)) ");

		return buf.toString();
	}
	
	/**
	 * 嵌套窗体LIST界面查询计费证券信息数量的SQL
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraNameList
	 * @return
	 */
	public String getQueryRelaChargingSecCountSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT COUNT(*) AS CNT FROM T_P_SV_SEC_FEEINFO A ");
		buf.append(" WHERE ");
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		buf.append(" A.C_SEC_CODE IN (SELECT * FROM TABLE(?)) ");

		return buf.toString();
	}
	
	/**
	 * 嵌套窗体【选择】窗体查询计费证券信息的SQL
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryPortRelaChargingSecSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		// StringBuffer valueFieldbuf = new StringBuffer();
		// this.setWhereSql(valueFieldbuf, paraNameList);
		
		buf.append(" SELECT A.* ");
		buf.append(" FROM T_P_SV_SEC_FEEINFO A ");
		buf.append(" JOIN (SELECT * ");
		buf.append("       FROM T_P_SV_SEC_BASE ");
    	buf.append("       WHERE N_CHECK_STATE = 1 ");
		buf.append("       AND C_SEC_VAR_CODE LIKE ? ) B ");
		buf.append(" ON B.C_SEC_CODE = A.C_SEC_CODE ");
		buf.append(" WHERE (A.C_PORT_CODE NOT IN (SELECT * FROM TABLE(?)) ");
		buf.append(" OR A.C_PORT_CODE IS NULL) ");
		if (paraNameList.contains("C_SFJT")) {
			buf.append(" AND A.C_SFJT = ? ");
		}
		// if (valueFieldbuf.length() > 0) {
		// buf.append(" WHERE ").append(valueFieldbuf);
		// }
		buf.append(" ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}
	
	/**
	 * 嵌套窗体【选择】窗体查询计费证券信息数量的SQL
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryPortRelaChargingSecCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		// StringBuffer valueFieldbuf = new StringBuffer();
		// this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append(" FROM T_P_SV_SEC_FEEINFO A ");
		buf.append(" JOIN (SELECT * ");
		buf.append("       FROM T_P_SV_SEC_BASE ");
		buf.append("       WHERE N_CHECK_STATE = 1 ");
		buf.append("       AND C_SEC_VAR_CODE LIKE ? ) B ");
		buf.append(" ON B.C_SEC_CODE = A.C_SEC_CODE ");
		buf.append(" WHERE (A.C_PORT_CODE NOT IN (SELECT * FROM TABLE(?)) ");
		buf.append(" OR A.C_PORT_CODE IS NULL) ");
		if (paraNameList.contains("C_SFJT")) {
			buf.append(" AND A.C_SFJT = ? ");
		}
		// if (valueFieldbuf.length() > 0) {
		// buf.append(" WHERE ").append(valueFieldbuf);
		// }
		buf.append(" ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}
	
	/**
	 * 根据前台传送过来参数判断添加筛选条件，返回获取计费证券信息数据的sql
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf
						.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_MKT_CODE")) {
				valueFieldbuf
						.append(" C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND "); // modified by HeLiang.2016-11-22.BUG #144950 运营费用-支持资产净值扣不计费证券需求
			} else if (fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf.append(" A.C_SEC_CODE = ? AND ");
			} else if (fieldedName.equals("C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(SecBaseJfColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(SecBaseJfTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(SecBaseJfTableName.userInfo);
	}

}
