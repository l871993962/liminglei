package com.yss.uco.elecreco.er.reverse.manager.result.dao;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
public class FBResRelaSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(FBResRelaColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return null;
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		return null;
		
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		return null;
		
	}
}