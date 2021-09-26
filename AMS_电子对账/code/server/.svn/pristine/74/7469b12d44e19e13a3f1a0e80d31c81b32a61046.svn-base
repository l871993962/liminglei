package com.yss.uco.elecreco.er.repcolcfg.dao;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class DzRepColCfgSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(DzRepColCfgColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(DzRepColCfgTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(DzRepColCfgTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DzRepColCfgTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_P_ER_REPCOLCFG A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_P_ER_REPCOLCFG A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.N_CHECK_STATE DESC  ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_DZ_CODE")) {//电子对账报表
				valueFieldbuf.append(" A.C_DZ_CODE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_REPORT_CODE")) {//财务报表代码
				valueFieldbuf.append(" A.C_REPORT_CODE = ? AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	public String getDzRepColCfgsSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.* from T_P_ER_REPCOLCFG A ");
		sb.append(" where A.C_DZ_CODE = ? ");
		sb.append(" AND A.C_REPORT_CODE = ? ");
		sb.append(" AND A.N_CHECK_STATE = 1 ");
		sb.append(" order by C_ELEC_COL asc ");//按照列顺序排序
		return sb.toString();
	}

	public String getIsHaveCfgSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.* from T_P_ER_REPCOLCFG A ");
		sb.append(" where A.C_REPORT_CODE = ? ");
		sb.append(" order by C_ELEC_COL asc ");//按照列顺序排序
		return sb.toString();
	}

}