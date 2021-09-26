package com.yss.ifa.szt.tool.para.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class ErVocSqlBuilder implements SQLBuilder {

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
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(ErVocColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		return null;
	}

	public String getAllDataSql() {
		return " select * from T_D_ER_DV_VOC order by N_ORDER asc ";
	}

	public String getDataByKeysSql() {
		return " select * from T_D_ER_DV_VOC where C_DV_CODE in (select * from table(?)) order by N_ORDER asc ";
	}

	public String getDataByTypesSql() {
		return " select * from T_D_ER_DV_VOC where C_DV_TYPE in (select * from table(?)) order by C_DV_TYPE,N_ORDER asc ";
	}

	public String getDataByCodeSql() {
		return " select * from T_D_ER_DV_VOC where C_DV_CODE = ? order by N_ORDER asc ";
	}

}
