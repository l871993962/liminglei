package com.yss.ams.base.information.modules.sys.mktsdi.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 交易市场字典t_s_mkt_var SQLBuilder
 *
 */
public class MktsdiSqlBuilder implements SQLBuilder{

	public String buildDeleteSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver, String column) {
		return dbNameResolver.getColumnName(MktsdiColumnName.valueOf(column));
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(MktsdiTableName.userInfo);
	}

	/**
	 * 获取所有交易市场字典t_s_mkt_var数据总数
	 */
	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, arg0);
		sqlBuff.append(" select count(*) ");
		sqlBuff.append(" from ");
		sqlBuff.append(MktsdiTableName.userInfo);
		if (valueFieldbuf.length() > 0) {
			sqlBuff.append(" where ").append(valueFieldbuf);
		}
		return sqlBuff.toString();
	}

	/**
	 * 获取所有交易市场字典t_s_mkt_var数据
	 */
	public String getQueryConditionSql(List<String> arg0) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, arg0);
		sqlBuff.append(" select * ");
		sqlBuff.append(" from ");
		sqlBuff.append(MktsdiTableName.userInfo);
		if (valueFieldbuf.length() > 0) {
			sqlBuff.append(" where ").append(valueFieldbuf);
		}
		sqlBuff.append(" order by C_MKT_CODE desc");
		return sqlBuff.toString();
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf, List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_MKT_CODE")) {
				valueFieldbuf.append(" C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogTableName(MktsdiTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(MktsdiTableName.userInfo);
	}

}
