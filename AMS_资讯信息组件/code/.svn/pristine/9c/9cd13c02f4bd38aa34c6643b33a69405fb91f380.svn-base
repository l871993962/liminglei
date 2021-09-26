package com.yss.ams.sec.information.modules.sv.suspendedcond.dao;

import java.util.List;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SuspendedCondSqlBuilder implements SQLBuilder{

	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldBuf = new StringBuffer();
		try {

			buf.append("UPDATE T_P_SV_SUSPENDED_COND SET C_ITEM_CODE=?,C_ITEM_NAME=?,C_ITEM_VALUE = ?,C_LOGICAL_JUDGMENT =?,C_VALUE_TYPE=? WHERE C_IDEN = ? ");

			retSql = buf.toString();

		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("停牌股票信息：拼接更新停牌股票信息SQL出错", e);
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldBuf);
		}
		return retSql;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(SuspendedCondTableName.suspendedCond);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(SuspendedCondColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldBuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldBuf, paraNameList);
			if (paraNameList.contains("C_PORT_CODE")) {
				buf.append("SELECT  A.C_ITEM_CODE,A.C_ITEM_NAME,A.C_ITEM_VALUE,A.C_VALUE_TYPE,A.C_LOGICAL_JUDGMENT,A.C_DEFAULT_VALUE,A.C_ZB_TYPE,C_PORT_CODE, ");
				buf.append("(CASE A.C_ITEM_CODE ");
//				buf.append("WHEN 'ZHYGTPR_T' THEN '1'  ");
//				buf.append("WHEN 'SUSPENDED_EXCEED_DAY' THEN '2' ");
//				buf.append("WHEN 'ASSET_VALUE_RATE' THEN '3' ");
//				buf.append("WHEN 'DAILY_FLUCTUATE_RATE' THEN '4' ");
//				buf.append("WHEN 'INDEX_TOTAL_RANGE' THEN '5' ");
//				buf.append("WHEN 'ZRSZZZCJZB' THEN '6' ");
//				buf.append("WHEN 'TPJGSZ_DRZCJZ_RATE' THEN '7' ");
//				buf.append("WHEN 'TPJGSZHZ_DRZCJZ_RATE' THEN '7' ");
//				buf.append("WHEN 'JRGZJGXYTPJ' THEN '8' END) AS C_IDEN FROM T_P_SV_SUSPENDED_COND A ");
				// add by zhd 2016-12-22
				// STORY36994【南方基金】停牌股票指数收益法逻辑变更
				buf.append("WHEN 'ASSESS_MARKET_PRICE' THEN '1' ");
				buf.append("WHEN 'ASSET_VALUE_RATE' THEN '2' ");
				buf.append("WHEN 'GLXGXX' THEN '3' ");
				buf.append("WHEN 'SGGZRTZGDGPMRTZ' THEN '4' ");
				buf.append("WHEN 'SFATPZHJYRZCJZ' THEN '5' ");
				buf.append("WHEN 'JXSJRXZQMZTJDGP' THEN '6' ");
				buf.append("WHEN 'HQTZZGGHQ' THEN '7' ");
				buf.append("WHEN 'GZJGSFKL_FPQJSG' THEN '8' ");
				buf.append("WHEN 'FPGPDRZFQGZJ_SFJTZ' THEN '9' ");
				buf.append("WHEN 'FPGPDRDFQGZJ_SFJTZ' THEN '10'  ");
				buf.append("WHEN 'ZHYGTPR_T' THEN '11' ");
				buf.append("WHEN 'SUSPENDED_EXCEED_DAY' THEN '12' ");
				buf.append("WHEN 'DAILY_FLUCTUATE_RATE' THEN '13' ");
				buf.append("WHEN 'ZRSZZZCJZB' THEN '14' ");
				buf.append("WHEN 'INDEX_TOTAL_RANGE' THEN '15' ");
				buf.append("WHEN 'TPJGSZHZ_DRZCJZ_RATE' THEN '16' ");
				buf.append("WHEN 'TPJGSZ_DRZCJZ_RATE' THEN '16' ");
				buf.append("WHEN 'JRGZJGXYTPJ' THEN '17' END) AS C_IDEN FROM T_P_SV_SUSPENDED_COND A ");
				buf.append("WHERE A.C_ZB_TYPE IS NULL ");
				if (valueFieldBuf.length() > 0) {
					buf.append("AND").append(valueFieldBuf);
				}
				//buf.append("ORDER BY C_IDEN");
				// add by zhd 2016-12-22
				// STORY36994【南方基金】停牌股票指数收益法逻辑变更
				buf.append(" ORDER BY TO_NUMBER(C_IDEN) ");
//			}else if(paraNameList.contains("C_ZB_TYPE")){
//				buf.append("SELECT  A.C_ITEM_CODE,A.C_ITEM_NAME,A.C_ITEM_VALUE,A.C_VALUE_TYPE,A.C_LOGICAL_JUDGMENT,A.C_DEFAULT_VALUE,A.C_ZB_TYPE,C_PORT_CODE, ");
//				buf.append("(CASE A.C_ITEM_CODE ");
//				buf.append("WHEN 'ZHYGTPR_T' THEN '1'  ");
//				buf.append("WHEN 'SUSPENDED_EXCEED_DAY' THEN '2' ");
//				buf.append("WHEN 'ASSET_VALUE_RATE' THEN '3' ");
//				buf.append("WHEN 'DAILY_FLUCTUATE_RATE' THEN '4' ");
//				buf.append("WHEN 'INDEX_TOTAL_RANGE' THEN '5' ");
//				buf.append("WHEN 'ZRSZZZCJZB' THEN '6' ");
//				buf.append("WHEN 'TPJGSZ_DRZCJZ_RATE' THEN '7' ");
//				buf.append("WHEN 'TPJGSZHZ_DRZCJZ_RATE' THEN '7' ");
//				buf.append("WHEN 'JRGZJGXYTPJ' THEN '8' END) AS C_IDEN FROM T_P_SV_SUSPENDED_COND A WHERE C_PORT_CODE IS NULL ");
//				if (valueFieldBuf.length() > 0) {
//					buf.append("AND").append(valueFieldBuf);
//				}
//				buf.append(" ORDER BY C_IDEN");
			}else {
				buf.append("SELECT  A.C_ITEM_CODE,A.C_ITEM_NAME,A.C_ITEM_VALUE,A.C_VALUE_TYPE,A.C_LOGICAL_JUDGMENT,A.C_DEFAULT_VALUE,A.C_ZB_TYPE,C_PORT_CODE, ");
//				buf.append("(CASE A.C_IDEN ");
//				buf.append("WHEN '18' THEN '16' ");
//				buf.append("ELSE A.C_IDEN END) AS C_IDEN");
				// add by zhd 2016-12-22
				// STORY36994【南方基金】停牌股票指数收益法逻辑变更
				buf.append(" C_IDEN ");
				buf.append(" FROM T_P_SV_SUSPENDED_COND A WHERE C_PORT_CODE IS NULL ");
				// add by zhd 2017-01-16
				// STORY36994【南方基金】停牌股票指数收益法逻辑变更
				// 16的二选一是通过词汇来筛选的
//				// add by zhd 2016-12-22
//				// STORY36994【南方基金】停牌股票指数收益法逻辑变更
//				// 18与16是二选一的关系，初始加载时默认是16
//				buf.append(" AND C_IDEN != '18' ");
				if (valueFieldBuf.length() > 0) {
					buf.append("AND").append(valueFieldBuf);
				}
			}

			retSql = buf.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldBuf);
		}
		return retSql;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 拼接查询条件的方法
	 * 
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		for (String fieldName : paraNameList) {
			if (fieldName.equals("C_ITEM_CODE")) {
				valueFieldbuf.append(" A.C_ITEM_CODE = ? AND ");
			}
			// 私有指标设置界面查询条件 by wzh STORY #32313 停牌股票信息生成做成公共层面，不关联组合
			if(paraNameList.contains("C_ZB_TYPE")){
				valueFieldbuf.append(" C_ZB_TYPE = 'SY' AND ");
			}
			if(fieldName.equals("C_PORT_CODE")){
				valueFieldbuf.append(" A.C_PORT_CODE = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}

	}
	
	public String getGgCondList() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_P_SV_SUSPENDED_COND WHERE C_PORT_CODE IS NULL AND C_ZB_TYPE = 'GY' ");
		return buf.toString();
	}
	
	public String getSyCondList() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_P_SV_SUSPENDED_COND WHERE C_PORT_CODE IS NULL AND C_ZB_TYPE = 'SY' ");
		return buf.toString();
	}
	
	public String getCondPortList() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_P_SV_SUSPENDED_COND WHERE C_PORT_CODE IN (SELECT * FROM (TABLE(?)))");
		return buf.toString();
	}

}
