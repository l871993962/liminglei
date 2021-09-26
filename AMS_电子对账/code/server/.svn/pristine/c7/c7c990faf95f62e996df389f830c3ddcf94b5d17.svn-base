package com.yss.uco.elecreco.service.automatic.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class AutomaticParamSqlBuilder implements SQLBuilder {

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
		return null;
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
	
	/**
	 * STORY #90284 【富国基金】ETF重新跑账后对应的联接基金自动重新跑
	 * 通过ETF基金获取联接基金
	 * @return
	 */
	public String getLinkPortbyEtfPortSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.C_DV_PARAMS_VALUE, A.C_PORT_CODE FROM V_P_AO_PARAMS A ");
		buf.append(" WHERE A.C_DSP_CODE = 'LJ_YS_001' AND A.N_CHECK_STATE = 1 ");
		buf.append(" AND A.C_DV_PARAMS_VALUE IN (SELECT * FROM TABLE(?)) AND A.D_BEGIN <= ? AND A.D_END >= ? ");
		return buf.toString();
	}

}
