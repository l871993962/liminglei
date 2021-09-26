package com.yss.ams.sec.information.modules.sv.fiincome.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 债券每日利息sql处理类
 * @author yuankai 
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 */
public class FiIncomeSqlBuilder implements SQLBuilder {

	/**
	 * 返回根据前台查询条件获取债券每日利息总记录数的sql
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select count(*) as CNT from T_D_SV_FI_INCOME a ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.N_CHECK_STATE,A.C_SEC_CODE ASC,A.D_INCOME ASC");
		retSql = buf.toString();

		return retSql;
	}

	/**
	 * 返回根据前台查询条件获取债券每日利息所有记录的sql，并按照是否审核，证券代码，计息日期进行升序排序
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.C_IDEN,a.C_SEC_CODE,a.D_INCOME,a.C_DV_QUT_MOD,a.N_INCOME_DAYS,a.N_COUP_RATE,a.N_REM_COR, ");
		buf.append("  a.N_INCOME_PT,a.N_INCOME_AT,a.N_INCOME_PT_DUE,a.N_INCOME_AT_DUE,a.C_DESC,a.C_UPDATE_BY,a.C_UPDATE_TIME,a.C_CHECK_BY,a.C_CHECK_TIME,a.N_CHECK_STATE from T_D_SV_FI_INCOME a ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.N_CHECK_STATE,A.C_SEC_CODE ASC,A.D_INCOME ASC ");
		retSql = buf.toString();

		return retSql;
	}

	/**
	 * 根据前台传送过来参数判断添加筛选条件，返回获取债券每日利息数据的sql
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_MKT_CODE")) {
				valueFieldbuf.append(" A.C_SEC_CODE IN  (SELECT C_SEC_CODE FROM T_P_SV_SEC_BASE WHERE C_MKT_CODE IN (SELECT * FROM TABLE(?)))  AND ");
			} else if (fieldedName.equals("D_BEGIN")) {
				valueFieldbuf.append("a.D_INCOME >= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("D_END")) {
				valueFieldbuf.append("a.D_INCOME <= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf.append(" a.C_SEC_CODE = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_SEC_CODE")) {
					valueFieldbuf.append(" a.C_SEC_CODE IN (SELECT * FROM TABLE(?)) AND ");//交易证券in
			}else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
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
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(FiIncomeColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(FiIncomeTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(FiIncomeTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(FiIncomeTableName.userInfo);
	}

}
