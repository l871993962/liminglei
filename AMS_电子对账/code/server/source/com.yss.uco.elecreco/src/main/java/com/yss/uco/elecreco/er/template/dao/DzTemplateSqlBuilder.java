package com.yss.uco.elecreco.er.template.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.er.tmplrela.dao.DzTmplRelaTableName;

public class DzTemplateSqlBuilder implements SQLBuilder {

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
		return dbNameResolver.getTableName(DzTemplateTableName.template);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(DzTemplateColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogTableName(DzTemplateTableName.template);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DzTemplateTableName.template);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select  a.* from ");
			buf.append(DzTemplateTableName.template.toString()).append(" a ");
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
			buf.append(" select  count(*) as CNT from ");
			buf.append(DzTemplateTableName.template.toString()).append(" a  ");
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

	public String getTemplateByTypeCodeAndPortCode() {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT A.* FROM ");
		buf.append(DzTemplateTableName.template.toString()).append(" A ");
		buf.append(" WHERE EXISTS (SELECT 1 FROM ");
		buf.append(DzTmplRelaTableName.templateRela.toString()).append(" B ");
		buf.append(" WHERE A.C_TMPL_TYPE = B.C_TMPL_TYPE ");
		buf.append(" AND A.C_TMPL_CODE = B.C_TMPL_CODE ");
		buf.append(" AND B.N_CHECK_STATE = 1 ");
		buf.append(" AND A.C_DV_TMPL_STATUS = 'TEMP_USABLE' ");
		buf.append(" AND B.C_TMPL_TYPE = ? ");
		buf.append(" AND B.C_PORT_CODE = ?) ");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_TMPL_CODE")) {
				valueFieldbuf.append(" a.C_TMPL_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_TMPL_TYPE")) {
				valueFieldbuf.append(" a.C_TMPL_TYPE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("ARRAY_C_TMPL_TYPE")) {
				valueFieldbuf.append(" a.C_TMPL_TYPE in (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DV_TMPL_STATUS")) {
				valueFieldbuf.append(" a.C_DV_TMPL_STATUS = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getTemplateByCodeSQL() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select a.* from ");
		buf.append(DzTemplateTableName.template.toString()).append(" a ");
		buf.append(" where a.C_TMPL_CODE in (select * from table(?))");
		return buf.toString();
	}

	public String getDeployTemplateSQL() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select C_TMPL_CODE from ");
		buf.append(DzTemplateTableName.template.toString()).append(" a ");
		buf.append(" where a.C_DV_TMPL_STATUS ='TEMP_USABLE' ");
		return buf.toString();
	}
	
	public String getDeleteByCodeSQL(){
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM ");
		buf.append(DzTemplateTableName.template.toString()).append(" a ");
		buf.append(" where a.C_TMPL_CODE = ? ");
		return buf.toString();
	}

	public String getTemplateByTypeCodeAndPortCodes() {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT A.*,B.C_PORT_CODE FROM ");
		buf.append(DzTemplateTableName.template.toString()).append(" A ");
		buf.append(" JOIN T_D_ER_TMPL_RELA B ");
		buf.append(" ON A.C_TMPL_TYPE = B.C_TMPL_TYPE ");
		buf.append(" AND A.C_TMPL_CODE = B.C_TMPL_CODE ");
		buf.append(" WHERE B.N_CHECK_STATE = 1");
		buf.append(" AND A.C_DV_TMPL_STATUS = 'TEMP_USABLE' ");
		buf.append(" AND B.C_TMPL_TYPE = ? ");
		buf.append(" AND B.C_PORT_CODE in (select * from table(?)) ");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
}
