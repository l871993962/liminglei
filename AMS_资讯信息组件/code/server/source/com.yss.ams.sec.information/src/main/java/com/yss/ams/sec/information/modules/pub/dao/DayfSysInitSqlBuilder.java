package com.yss.ams.sec.information.modules.pub.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 *  删除一些无用的import by lihaizhi 20130620
 */
public class DayfSysInitSqlBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbNameResolver) {
		return null;
	}

	public String buildSelectSql(DBNameResolver dbNameResolver) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(SysInitItemColumnName.valueOf(name));
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return null;
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer quyCondBuf = new StringBuffer();
		String sql = "";

		buf.append(" SELECT COUNT (C_DV_CODE) AS CNT ");
		buf.append(" FROM v_s_dv_voc a ");

		setWhereClauseSql(quyCondBuf, paraNameList);

		if (quyCondBuf.length() > 0) {
			buf.append(" WHERE ").append(quyCondBuf);
		}

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer quyCondBuf = new StringBuffer();
		String sql = "";

		buf.append(" SELECT ");
		buf.append(" 	a.C_DV_CODE AS C_DV_ITEM_CODE, ");
		buf.append(" 	a.C_DESC, ");
		buf.append(" 	1 AS N_CHECK_STATE ");
		buf.append(" FROM v_s_dv_voc a ");

		setWhereClauseSql(quyCondBuf, paraNameList);

		if (quyCondBuf.length() > 0) {
			buf.append(" WHERE ").append(quyCondBuf);
		}

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	private void setWhereClauseSql(StringBuffer sqlBuf, List<String> paramList) {
		sqlBuf.append(SqlUtil.getCheckStateClause(paramList, "a"));

		for (String fieldedName : paramList) {
			if (fieldedName.equals("C_DV_TYPE")) {
				sqlBuf.append("  a.C_DV_TYPE = ? AND ");
			}
		}

		if (sqlBuf.length() > 0) {
			StringUtil.delLastSplitMark(sqlBuf, " AND ");
		}
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
