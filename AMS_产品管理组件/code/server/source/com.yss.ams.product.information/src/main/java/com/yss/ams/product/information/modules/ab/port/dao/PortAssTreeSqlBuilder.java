package com.yss.ams.product.information.modules.ab.port.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * <产品基本信息>组合树SQL语句构造类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortAssTreeSqlBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbNameResolver) {
		return null;
	}

	public String buildSelectSql(DBNameResolver dbNameResolver) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver
				.getColumnName(PortAssTreeColumnName.valueOf(name));
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		return null;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		return null;
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return null;
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		return null;
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
