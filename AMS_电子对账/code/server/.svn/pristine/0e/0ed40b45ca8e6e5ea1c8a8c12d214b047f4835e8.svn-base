package com.yss.uco.elecreco.support.dzdz.bus.result;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.uco.elecreco.support.dzdz.bus.yue.pojo.ErYuebColumnName;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;

public class ResultSqlBuilder implements IRecordSqlBuilder{
	public String getRecordSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT *");
		buf.append(" FROM T_D_ER_RESULT A").append(" WHERE A.C_ASS_CODE = ? AND A.C_SN = ? order by C_KM_CODE");
		//对账方向为反向对账
		return buf.toString();
	}
	

	

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(ErYuebColumnName.valueOf(name));
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
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
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
