package com.yss.uco.elecreco.er.reverse.out.erkmb.dao;
import java.util.List;
import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class ErKmbOutSqlBuilder implements SQLBuilder  {

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		return null;
	}
	
	public String getInsertSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into T_D_OD_KM_OUT(C_ASS_CODE,C_ASS_NAME,C_TGH_NAME_OUT,C_TGH_CODE,C_KM_CODE,C_KM_NAME,C_DV_KM_CLS) values(?,?,?,?,?,?,?) ");
		return sb.toString();
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,String s) {
		return dbnameresolver.getColumnName(ErKmbOutColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErKmbOutTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErKmbOutTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErKmbOutTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_OD_KM_OUT A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_OD_KM_OUT A ");
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
			if (fieldedName.equalsIgnoreCase("ARRAY_C_TGH_CODE")) {
				valueFieldbuf.append(" A.C_TGH_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_ASS_CODE")) {//
				valueFieldbuf.append(" A.C_ASS_CODE LIKE ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_ASS_NAME")) {//
				valueFieldbuf.append(" A.C_ASS_NAME LIKE ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_TGH_CODE")) {//
				valueFieldbuf.append(" A.C_TGH_CODE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_DV_KM_CLS")) {//
				valueFieldbuf.append(" A.C_DV_KM_CLS = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_PORT_CODE")) {//
				valueFieldbuf.append(" A.C_ASS_CODE in ( select C_PORT_CODE_OUT from T_D_ER_REVE_ASS_MAP where C_PORT_CODE = ? ) AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

}