package com.yss.ams.base.information.modules.sys.indexdi.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 合规指标类型字典T_S_INDEX SQLBuilder
 *
 */
public class IndexdiSqlBuilder implements SQLBuilder{

	public String buildDeleteSql(DBNameResolver arg0) {
		return null;
	}

	public String buildInsertSql(DBNameResolver arg0) {
		return null;
	}

	public String buildSelectSql(DBNameResolver arg0) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver arg0) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver, String column) {
		return dbNameResolver.getColumnName(IndexdiColumnName.valueOf(column));
	}

	public String getLogSequenceName(DBNameResolver arg0) {
		return null;
	}

	/**
	 * 获取所有 合规指标类型字典T_S_INDEX数据总数
	 */
	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" select count(*) ");
		sqlBuff.append(" from ");
		sqlBuff.append(IndexdiTableName.userInfo);
		return sqlBuff.toString();
	}

	/**
	 * 获取所有 合规指标类型字典T_S_INDEX数据
	 */
	public String getQueryConditionSql(List<String> arg0) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" select * ");
		sqlBuff.append(" from ");
		sqlBuff.append(IndexdiTableName.userInfo);
		sqlBuff.append(" order by C_INDEX_CODE ");
		return sqlBuff.toString();
	}

	/**
	 * 合规指标类型Insert SQL
	 * @param tableSequence
	 * @return
	 */
	public String getInsertSql(String tableSequence){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" insert into ");
		sqlBuff.append(IndexdiTableName.userInfo);
		sqlBuff.append(" ( ");
		sqlBuff.append(IndexdiColumnName.id).append(",");
		sqlBuff.append(IndexdiColumnName.c_INDEX_CODE).append(",");
		sqlBuff.append(IndexdiColumnName.c_INDEX_NAME).append(",");
		sqlBuff.append(IndexdiColumnName.c_DATA_SOURCE).append(",");
		sqlBuff.append(IndexdiColumnName.c_DATA_TYPE).append(",");
		sqlBuff.append(IndexdiColumnName.n_STATE).append(",");
		sqlBuff.append(IndexdiColumnName.n_ORDER).append(",");
		sqlBuff.append(IndexdiColumnName.c_NAV_TYPE).append(",");
		sqlBuff.append(IndexdiColumnName.n_DETAIL).append(",");
		sqlBuff.append(IndexdiColumnName.c_KEY_CODE).append(",");
		sqlBuff.append(IndexdiColumnName.c_KEY_NAME).append(",");
		sqlBuff.append(IndexdiColumnName.c_IS_SYS).append(",");
		sqlBuff.append(IndexdiColumnName.c_TRU).append(",");
		sqlBuff.append(IndexdiColumnName.c_MODE).append(",");
		sqlBuff.append(IndexdiColumnName.c_RET);
		sqlBuff.append(" ) ");
		sqlBuff.append(" values ");
		sqlBuff.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return sqlBuff.toString();
	}
	
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogTableName(IndexdiTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(IndexdiTableName.userInfo);
	}

}
