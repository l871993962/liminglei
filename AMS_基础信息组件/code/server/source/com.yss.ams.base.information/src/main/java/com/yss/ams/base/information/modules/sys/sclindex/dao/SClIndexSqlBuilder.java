package com.yss.ams.base.information.modules.sys.sclindex.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 合规指标项目字典T_S_CL_INDEX SQLBuilder
 *
 */
public class SClIndexSqlBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(SClIndexColumnName.valueOf(name));
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(SClIndexTableName.sClIndex);
	}

	/**
	 * 根据条件获取符合条件的  合规指标项目字典T_S_CL_INDEX数据总数
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_S_CL_INDEX A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	/**
	 * 根据条件获取符合条件的  合规指标项目字典T_S_CL_INDEX数据
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		 buf.append("select A.*");
		 buf.append(" from t_s_cl_index A");

//		buf.append("select  A.*");
//		buf.append(" from t_s_cl_index A");
//		buf.append(" start with A.c_index_code_p = '[root]'");
//		buf.append(" connect by prior A.c_index_code = A.c_index_code_p");

		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		buf.append(" ORDER BY A.c_index_code desc ");

		return buf.toString();
	}

	/**
	 * 根据条件拼接where条件语句
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_INDEX_TYPE")) {
				valueFieldbuf.append(" A.C_INDEX_TYPE = ?  AND ");
			}else if (fieldedName.equals("C_DV_IDX_TYPE")) {
				valueFieldbuf.append(" A.C_DV_IDX_TYPE = ?  AND ");
			}else if (fieldedName.equals("C_DV_IDX_STATE")) {
				valueFieldbuf.append(" A.C_DV_IDX_STATE = ?  AND ");
			}else if(fieldedName.equals("ARRAY_C_INDEX_CODE")){
				valueFieldbuf.append(" A.C_INDEX_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equals("C_INDEX_CODE")) {
				valueFieldbuf.append(" A.C_INDEX_CODE = ?  AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
