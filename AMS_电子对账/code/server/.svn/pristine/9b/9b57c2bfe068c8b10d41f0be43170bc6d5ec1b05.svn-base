package com.yss.uco.elecreco.er.reverse.map.kmrela.dao;
import java.util.List;
import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class KmRelaSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(KmRelaColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(KmRelaTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(KmRelaTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(KmRelaTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_REVE_KM_RELA A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_REVE_KM_RELA A ");
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
			}else if (fieldedName.equalsIgnoreCase("C_DV_KM_CLS")) {//科目类型
				valueFieldbuf.append(" A.C_DV_KM_CLS = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_DV_MAP_SCOPE")) {//映射范围
				valueFieldbuf.append(" A.C_DV_MAP_SCOPE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_TGH_CODE")) {//托管行
				valueFieldbuf.append(" A.C_TGH_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_IDEN")) {//ID
				valueFieldbuf.append(" A.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

}