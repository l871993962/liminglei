package com.yss.uco.elecreco.er.special.zb.dao;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class ErSpecialZbSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(ErSpecialZbColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErSpecialZbTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErSpecialZbTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErSpecialZbTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_SPECIAL_ZB A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_SPECIAL_ZB A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.N_CHECK_STATE DESC  ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
//		for (String fieldedName : paraNameList) {
//			if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
//				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
//			} else if (fieldedName.equals("IDEN")) {//
//				valueFieldbuf.append(" A.IDEN LIKE ? AND ");
//			}
//		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getAllDataSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.* from T_D_ER_SPECIAL_ZB A ");
		buf.append(" where A.N_CHECK_STATE = 1 ");
		return buf.toString();
	}

}