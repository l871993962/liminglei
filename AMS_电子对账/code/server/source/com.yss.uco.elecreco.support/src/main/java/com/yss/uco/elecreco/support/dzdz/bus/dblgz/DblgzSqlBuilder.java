package com.yss.uco.elecreco.support.dzdz.bus.dblgz;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.uco.elecreco.support.dzdz.bus.dblgz.pojo.ErDblgzbColumnName;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;

public class DblgzSqlBuilder implements IRecordSqlBuilder{
	public String getRecordSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT CASE WHEN LENGTH(A.C_KM_CODE) > 100 THEN  SUBSTR(A.C_KM_CODE, 1, 100)  ELSE A.C_KM_CODE END AS C_KM_CODE,");
		buf.append("A.C_KM_NAME,N_VA_PRICE,N_QUOT_LOGO,N_AMOUNT,N_PORT_COST,N_PORT_MV,N_PORT_IV");
		buf.append(",N_CB_JZ_BL, N_SZ_JZ_BL,N_DETAIL,C_PORT_CLS_CODE");
		
		buf.append(" FROM T_D_ER_DBLGZ A ");
		buf.append(" WHERE A.C_ASS_CODE = ? AND A.C_SN = ? ");
		buf.append(" order by C_KM_CODE ");
		return buf.toString();
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
		// TODO Auto-generated method stub
		return dbNameResolver.getColumnName(ErDblgzbColumnName.valueOf(name));
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

	
}
