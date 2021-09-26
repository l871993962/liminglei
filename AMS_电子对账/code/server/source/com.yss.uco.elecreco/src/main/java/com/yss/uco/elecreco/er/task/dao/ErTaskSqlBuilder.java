package com.yss.uco.elecreco.er.task.dao;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
public class ErTaskSqlBuilder implements SQLBuilder  {

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,String s) {
		return dbnameresolver.getColumnName(ErTaskColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErTaskTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErTaskTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErTaskTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_TASK A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_TASK A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		
	}

	public String getAllTasksSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.* from T_D_ER_TASK A ");
		return sb.toString();
	}

	public String getErTaskByCodeSql(String taskCode) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.* from T_D_ER_TASK A ");
		sb.append(" where A.C_DV_TASK_CODE = ? ");
		return sb.toString();
	}

}