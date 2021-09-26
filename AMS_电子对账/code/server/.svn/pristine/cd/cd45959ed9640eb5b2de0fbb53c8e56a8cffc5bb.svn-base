package com.yss.uco.elecreco.er.reverse.out.ergzb.dao;
import java.util.List;
import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class ErGzbOutSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(ErGzbOutColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErGzbOutTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErGzbOutTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErGzbOutTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_OD_GZ_OUT A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_OD_GZ_OUT A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		//不需要审核机制
		//valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		SqlUtil.getCheckStateClause(paraNameList, "A");
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_ASS_CODE")) {
				valueFieldbuf.append(" A.C_ASS_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_PORT_CODE")) {//
				valueFieldbuf.append(" A.C_ASS_CODE in ( select C_PORT_CODE_OUT from T_D_ER_REVE_ASS_MAP where C_PORT_CODE = ? ) AND ");
			}else if (fieldedName.equalsIgnoreCase("D_GZ_DATE")) {//
				valueFieldbuf.append(" A.D_GZ_DATE = to_date(?,'yyyy-MM-dd') AND ");
			}else if (fieldedName.equalsIgnoreCase("C_TGH_CODE")) {//
				valueFieldbuf.append(" A.C_TGH_CODE = ? AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

}