package com.yss.ifa.szt.tool.para.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class DzPortRelaSqlBuilder implements SQLBuilder {

	public DzPortRelaSqlBuilder() {

	}

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getTableName(DzPortRelaTableName.userInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String s) {
		return dbNameResolver.getColumnName(DzPortRelaColumnName.valueOf(s));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(DzPortRelaTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		//BUG #212017::银华基金-支付平台-电子对账参数查询时报错  T_P_AB_PORT_RELA 新增
		buf.append(" SELECT A.C_IDEN,A.C_PORT_CODE,A.C_RELA_CODE,A.C_RELA_TYPE,A.C_DV_TYPE_CODE,A.C_CHECK_BY,A.C_CHECK_TIME,A.C_UPDATE_BY,A.C_UPDATE_TIME,A.N_CHECK_STATE, ");
		buf.append("  B.C_ORG_CODE, B.C_ORG_CODE_P AS C_ORG_NAME_P  ");
		buf.append("  ,B.C_ORG_NAME, B.C_DV_ORG_TYPE FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * FROM T_P_BI_ORG WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_ORG_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");
		return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT COUNT(*) AS CNT FROM (");
		buf.append(this.getQueryConditionSql(paraNameList));
		buf.append(" ) A ");

		return buf.toString();
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_RELA_TYPE")) {
				valueFieldbuf.append(" a.C_RELA_TYPE = ?  AND ");
			} else if (fieldedName.equalsIgnoreCase("C_RELA_CODE")) {//查询属于该托管机构下的组合
				valueFieldbuf.append(" a.C_RELA_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DV_TYPE_CODE")) {
				valueFieldbuf.append("a.C_DV_TYPE_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_START")) {
				valueFieldbuf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_END")) {
				valueFieldbuf.append(" a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_RELA_CODE_P")) {
				valueFieldbuf.append(" A.C_RELA_CODE IN (SELECT C_ORG_CODE FROM T_P_BI_ORG START WITH C_ORG_CODE = ? ");
				valueFieldbuf.append(" CONNECT BY PRIOR C_ORG_CODE =C_ORG_CODE_P) AND ");
			} else if(fieldedName.equalsIgnoreCase("C_MANAGE_CODE")){//查询属于该管理人机构下的组合
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT C_PORT_CODE FROM T_P_AB_PORT_RELA WHERE C_RELA_CODE = ? ");
				valueFieldbuf.append(" AND C_RELA_TYPE='RELA_ORG') AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

}
