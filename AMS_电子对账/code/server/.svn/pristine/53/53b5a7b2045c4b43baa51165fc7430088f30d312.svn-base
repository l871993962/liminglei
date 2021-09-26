package com.yss.uco.elecreco.er.spilt.rela.dao;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class ErSplitRelaSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(ErSplitRelaColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErSplitRelaTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErSplitRelaTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErSplitRelaTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_SPLIT_RELA A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.*, ");
		buf.append(" case when trim(C_TGH_CODE) is null then 'IGNORE' else 'SPLIT' end as C_SHOW_TYPE ");
		buf.append(" from T_D_ER_SPLIT_RELA A  ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.N_CHECK_STATE DESC  ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_TGH_CODE")) {//
				valueFieldbuf.append(" A.C_TGH_CODE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_SPLIT_CODE")) {//
				valueFieldbuf.append(" A.C_SPLIT_CODE LIKE ? AND ");
			}else if (fieldedName.equalsIgnoreCase("D_START_DATE")) {//
				valueFieldbuf.append(" A.D_START_DATE <= to_date(?,'yyyy-MM-dd') AND ");
			}else if (fieldedName.equalsIgnoreCase("D_END_DATE")) {//
				valueFieldbuf.append(" A.D_END_DATE >= to_date(?,'yyyy-MM-dd') AND ");
			}else if (fieldedName.equalsIgnoreCase("C_SHOW_TYPE")) {//虚拟字段，取值范围（ALL,IGNORE,SPLIT）
				valueFieldbuf.append(" ( 'IGNORE' = ? and trim(A.C_TGH_CODE) is null ) AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getErSplitRelasByPortCodeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.* FROM T_D_ER_SPLIT_RELA A ");
		buf.append("  ");
		buf.append(" where A.C_PORT_CODE = ? ");
		buf.append(" and A.N_CHECK_STATE >= 1 ");
		return buf.toString();
	}

}