package com.yss.uco.elecreco.er.erresview.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class ErResviewSqlBuilder  implements SQLBuilder {

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
		return dbNameResolver.getTableName(ErResviewTableName.userInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return dbNameResolver.getColumnName(ErResviewColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getTableName(ErResviewTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(ErResviewTableName.userInfo);
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

	public String getDataByCodeSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		setCommonQuyDataSql(buf);
		buf.append(" WHERE A.C_PLAN_CODE = ? ");
		setCommonQuyOrderSql(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getDataByKeysSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		setCommonQuyDataSql(buf);
		buf.append(" WHERE A.C_PLAN_CODE IN ");
		buf.append(" 	(SELECT * FROM TABLE(?) ) ");
		setCommonQuyOrderSql(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 拼接sql，查询对账类型字典所有数据
	 * @param buf
	 */
	protected void setCommonQuyDataSql(StringBuffer buf) {
		//从T_D_ER_RESVIEW表中取出方案代码及名称，方案对应多条指标，需去重
		buf.append(" SELECT ");
		buf.append(" distinct ");
		buf.append("   A.C_PLAN_CODE, ");
		buf.append("   A.C_PLAN_CODE||'_'||A.C_PLAN_NAME as C_PLAN_NAME ");
		buf.append(" FROM T_D_ER_RESVIEW A ");
	}
	
	/**
	 * 拼接sql，按照对账类型字典代码升序的方式查询
	 */
	protected void setCommonQuyOrderSql(StringBuffer buf) {
		buf.append(" ORDER BY A.C_PLAN_CODE ");
	}
	
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		setCommonQuyDataSql(buf);
		setCommonQuyOrderSql(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getItemCodesSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" select ");
		buf.append(" A.C_ITEM_CODE ");
		buf.append(" from ");
		buf.append(" T_D_ER_RESVIEW A ");
		buf.append(" where ");
		buf.append(" A.C_PLAN_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getdeleteByPlanCodeSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" delete ");
		buf.append(" from ");
		buf.append(" T_D_ER_RESVIEW A ");
		buf.append(" where ");
		buf.append(" A.C_PLAN_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
}
