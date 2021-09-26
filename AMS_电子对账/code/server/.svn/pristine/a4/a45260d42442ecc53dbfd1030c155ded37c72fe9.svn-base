package com.yss.uco.elecreco.er.reverse.map.zbmap.dao;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class ZbMapSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(ZbMapColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ZbMapTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ZbMapTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ZbMapTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_REVE_ZB_MAP A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_REVE_ZB_MAP A ");
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
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_FILE_TYPE")) {//对账类型
				valueFieldbuf.append(" A.C_FILE_TYPE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_ZB_CODE")) {//内部指标代码
				valueFieldbuf.append(" A.C_ZB_CODE LIKE ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_ZB_CODE_OUT")) {//外部指标代码
				valueFieldbuf.append(" A.C_ZB_CODE_OUT LIKE ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_TGH_CODE")) {//托管机构
				valueFieldbuf.append(" A.C_TGH_CODE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_TGH_CODE_OR_NULL")) {//托管机构
				valueFieldbuf.append(" ( A.C_TGH_CODE IN (SELECT * FROM TABLE(?)) or trim(A.C_TGH_CODE) is null ) AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/**
	 * 获取内部的指标项
	 * @return
	 */
	public String getInnerZbItemDataSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.* FROM T_Z_BI_RELA A ");
		sb.append(" where A.C_DZ_CODE = ? ");
		return sb.toString();
	}
	/**
	 * 按照级别排序，级别高的排在后面
	 * @return
	 * @throws Exception
	 */
	public String getCompareZbMapSql() throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.* from ( ");
		buf.append(" select A.*, ");
		buf.append(" case when Trim(A.C_PORT_CODE) is not null then 3  ");
		buf.append(" when Trim(A.C_PORT_CODE) is null and Trim(A.C_TGH_CODE) is not null then 2 ");
		buf.append(" else 1 end ");
		buf.append(" as ZBMAP_LEVEL ");
		buf.append(" from T_D_ER_REVE_ZB_MAP A ");
		buf.append(" where A.N_CHECK_STATE > 0 ");
		buf.append("  and ( A.C_PORT_CODE = ? or trim(A.C_PORT_CODE) is null ) ");//组合
		buf.append("  and ( A.C_TGH_CODE = ? or trim(A.C_TGH_CODE) is null ) ");//托管行
		buf.append("  and A.C_FILE_TYPE = ? ");//对账类型
		buf.append(" ) A ");
		buf.append(" ORDER BY A.ZBMAP_LEVEL ASC  ");
		return buf.toString();
	}

}