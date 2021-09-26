package com.yss.uco.elecreco.er.erdztype.dao;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
public class ErDzTypeSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(ErDzTypeColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErDzTypeTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErDzTypeTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErDzTypeTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_DZ_TYPE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_DZ_TYPE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("IDEN")) {//
				valueFieldbuf.append(" A.IDEN LIKE ? AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getAllDataByTypesSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.* from T_D_ER_DZ_TYPE A ");
		buf.append(" where A.C_DZ_CODE in ( select * from table(?) ) ORDER BY A.C_IDEN");
		return buf.toString();
	}

	public String getDataByCodeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.* from T_D_ER_DZ_TYPE A ");
		buf.append(" where A.C_DZ_CODE = ? ");
		return buf.toString();
	}

	public String getAllDzTypeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.* from T_D_ER_DZ_TYPE A ");
		return buf.toString();
	}

}