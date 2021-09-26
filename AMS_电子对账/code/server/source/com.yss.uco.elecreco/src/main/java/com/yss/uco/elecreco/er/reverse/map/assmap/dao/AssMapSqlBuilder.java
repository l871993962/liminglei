package com.yss.uco.elecreco.er.reverse.map.assmap.dao;
import java.util.List;
import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class AssMapSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(AssMapColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(AssMapTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(AssMapTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(AssMapTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_REVE_ASS_MAP A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_REVE_ASS_MAP A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.N_CHECK_STATE DESC  ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE_OR_NULL")) {//查找特定的组合代码和组合为空的
				valueFieldbuf.append(" ( A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) or trim(A.C_PORT_CODE) is null ) AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {//组合代码
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_PORT_CODE_OUT")) {//托管行资产代码
				valueFieldbuf.append(" A.C_PORT_CODE_OUT LIKE ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DV_DZ_MODE")) {//对账模式
				valueFieldbuf.append(" A.C_DV_DZ_MODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_TGH_CODE")) {//托管银行
				valueFieldbuf.append(" A.C_TGH_CODE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_FILE_TYPE")) {//对账类型 
				valueFieldbuf.append(" A.C_FILE_TYPE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_FILE_TYPE_OR_NULL")) {//查找特定的组合代码和组合为空的
				valueFieldbuf.append(" ( A.C_FILE_TYPE = ? or trim(A.C_FILE_TYPE) is null ) AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	public String getDzModeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.C_DV_DZ_MODE, ");
		buf.append(" case when Trim(A.C_FILE_TYPE) is null then 2 else 1 end as N_ORDER ");
		buf.append(" from T_D_ER_REVE_ASS_MAP A ");
		buf.append(" where A.C_PORT_CODE = ?");
		buf.append(" and (A.C_FILE_TYPE = ? or trim(A.C_FILE_TYPE) is null ) ");//对账类型为空，适用于所有
		buf.append(" and A.N_CHECK_STATE = '1' ");
		buf.append(" order by N_ORDER asc ");
		return buf.toString();
	}
	
	public String getAssMapSqlByPortCodeAndFileType() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.* ");
		buf.append(" from T_D_ER_REVE_ASS_MAP A ");
		buf.append(" where A.C_PORT_CODE = ?");
		buf.append(" and A.C_FILE_TYPE = ? ");
		buf.append(" and A.N_CHECK_STATE = '1' ");
		return buf.toString();
	}
	public String getCommonAssMapSqlByPortCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.* ");
		buf.append(" from T_D_ER_REVE_ASS_MAP A ");
		buf.append(" where A.C_PORT_CODE = ?");
		buf.append(" and trim(A.C_FILE_TYPE) is null ");//对账类型为空，适用于所有
		buf.append(" and A.N_CHECK_STATE = '1' ");
		return buf.toString();
	}
	/**
	 * 根据组合代码获取托管行(去重)
	 * @return
	 */
	public String getTghCodesByPortCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select distinct C_TGH_CODE ");
		buf.append(" from T_D_ER_REVE_ASS_MAP A ");
		buf.append(" where A.C_PORT_CODE = ?");
		buf.append(" and A.N_CHECK_STATE = '1' ");
		return buf.toString();
	}

}