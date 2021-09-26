package com.yss.uco.elecreco.support.dzdz.bus.erjzcbdb;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.uco.elecreco.support.dzdz.bus.erjzcbdb.pojo.ErJzcbdbColumnName;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;

public class ErJzcbdbSqlBuilder implements IRecordSqlBuilder {
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
		return dbNameResolver.getColumnName(ErJzcbdbColumnName.valueOf(name));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecordSql() {
		StringBuffer buf = new StringBuffer();
		//BUG296998电子对账生成财务报表数据时期初日期和期末日期取值错误
		buf.append(" SELECT C_INDEX_CODE,N_CUR_VALUE,N_TOL_VALUE,D_START_DATE,D_END_DATE ");
		buf.append(" FROM T_D_ER_JZCBD WHERE C_ASS_CODE = ?");
		buf.append(" AND C_SN = ? ");
		buf.append(" AND C_RPT_TYPE = ? ");
		buf.append(" AND C_INDEX_CODE IN ");
		buf.append(" (SELECT C_ZB_CODE FROM T_Z_BI_RELA WHERE C_DZ_CODE='1903') ");
		return buf.toString();
	}
}
