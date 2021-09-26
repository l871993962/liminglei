package com.yss.ams.base.information.modules.sys.secvardi.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 证券品种字典T_S_DA_SEC_VAR SQLBuilder
 *
 */
public class SecVarDiSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(SecVarDiColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getLogTableName(SecVarDiTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(SecVarDiTableName.userInfo);
	}

	/**
	 * 查询证券品种字典T_S_DA_SEC_VAR所有数据总数
	 */
	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			buf.append(" select count(*) as CNT from V_S_DA_SEC_VAR a ");
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
	 * 查询证券品种字典T_S_DA_SEC_VAR所有数据
	 */
	public String getQueryConditionSql(List<String> arg0) throws Exception {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append(" select  a.* ");
		strBuff.append(" from V_S_DA_SEC_VAR a");
		return strBuff.toString();
	}


	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}
}
