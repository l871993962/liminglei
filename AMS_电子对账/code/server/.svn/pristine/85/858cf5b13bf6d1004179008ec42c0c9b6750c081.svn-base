package com.yss.uco.elecreco.support.dzdz.bus.yue;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.uco.elecreco.support.dzdz.bus.yue.pojo.ErYuebColumnName;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;

public class YueSqlBuilder implements IRecordSqlBuilder{
	/**
	 * BY  Jinghehe 2015-4-30 过滤掉期末余额是0 的数据，既是 原币金额 本币金额 数量都是0 的数据 
	 * (non-Javadoc)
	 * @see dzdz.common.IRecordSqlBuilder#getRecordSql()
	 */
	public String getRecordSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.* FROM T_D_ER_YE A WHERE A.C_ASS_CODE = ? AND A.C_SN = ? ");
		buf.append(" ORDER BY C_KM_CODE");
		return buf.toString();
	}
	
	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
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
