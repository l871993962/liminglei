package com.yss.uco.elecreco.er.tmplrela.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class DzTmplRelaSqlBuilder implements SQLBuilder {

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(DzTmplRelaTableName.templateRela);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(DzTmplRelaColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogTableName(DzTmplRelaTableName.templateRela);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver
				.getLogSequenceName(DzTmplRelaTableName.templateRela);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select  a.* from ")
					.append(DzTmplRelaTableName.templateRela.toString())
					.append(" a ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
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

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select  count(*) as CNT from ")
					.append(DzTmplRelaTableName.templateRela.toString())
					.append(" a  ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
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

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_PORT_CODE")) {
				valueFieldbuf.append(" a.C_PORT_CODE like ? AND ");
			} else if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf
						.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_TMPL_CODE")) {
				valueFieldbuf.append(" a.C_TMPL_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_TMPL_TYPE")) {
				valueFieldbuf.append(" a.C_TMPL_TYPE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("ARRAY_C_TMPL_TYPE")) {
				valueFieldbuf
						.append(" a.C_TMPL_TYPE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_START")) {
				valueFieldbuf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_END")) {
				valueFieldbuf.append(" a.C_DEL_TIME <= ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
}
