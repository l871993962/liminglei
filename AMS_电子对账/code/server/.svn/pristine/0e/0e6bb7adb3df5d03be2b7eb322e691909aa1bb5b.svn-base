package com.yss.uco.elecreco.er.transrepcfg.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class DzTransRepCfgSqlBuilder implements SQLBuilder {

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
		return dbNameResolver.getTableName(DzTransRepCfgTableName.userInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(DzTransRepCfgColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		//STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
		//添加回收表
		return dbNameResolver.getTableName(DzTransRepCfgTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DzTransRepCfgTableName.userInfo);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select t.* ")
		.append(" from T_P_ER_TRANSREPCFG t ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		//BUG237025电子对账报表配置页面数据展示有误		按审核状态排序
		buf.append(" order by n_check_state ");
		return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT FROM T_P_ER_TRANSREPCFG T ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf, List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "t"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_ORG_CODE")) {
				valueFieldbuf.append(" t.C_ORG_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_TRANS_CODE")) {
				valueFieldbuf.append(" t.C_TRANS_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_TPL_CODE")) {
				valueFieldbuf.append(" t.C_TPL_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_PORT_CODE")) {
				valueFieldbuf.append(" t.C_PORT_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("ARRAY_PORT_CODE")) {
				valueFieldbuf.append(" t.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DAT_CODE")) {
				valueFieldbuf.append(" t.C_DAT_CODE = ? AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

}
