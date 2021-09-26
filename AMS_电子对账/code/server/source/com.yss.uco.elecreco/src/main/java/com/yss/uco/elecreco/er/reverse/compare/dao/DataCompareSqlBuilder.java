package com.yss.uco.elecreco.er.reverse.compare.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public abstract class DataCompareSqlBuilder  implements SQLBuilder {

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
	
	public String getDeleteErInfoSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from T_D_ER_INFO A ");
		sb.append(" where A.C_DV_ER_WAY = ? ");
		sb.append(" and A.C_PORT_CODE = ? ");
		sb.append(" and A.D_DATE = to_date( ? , 'yyyy-MM-dd') ");
		sb.append(" and A.C_FILE_TYPE = ? ");
		sb.append(" and A.C_RPT_TYPE = ? ");
		return sb.toString();
	}
	/**
	 * 删除反向电子对账T_D_ER_REVE_RESRELA表数据
	 * 依赖T_D_ER_REVE_RESULT表里的数据
	 * 一定先删除T_D_ER_REVE_RESRELA表里的数据
	 * 再删除T_D_ER_REVE_RESULT表里的数据
	 * @return
	 */
	public String getDeleteReveResrelaSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE  FROM  T_D_ER_REVE_RESRELA R where R.C_RESULT_RELA in ( ");
		sb.append(" SELECT C_IDEN FROM  ");
		sb.append(" T_D_ER_REVE_RESULT A ");
		sb.append(" where A.C_PORT_CODE = ? ");
		sb.append(" and A.C_FILE_TYPE = ? ");
		sb.append(" and A.C_RPT_TYPE = ? ");
		sb.append(" and A.D_START_DATE <= to_date(?,'yyyy-MM-dd') ");
		sb.append(" and A.D_END_DATE >= to_date(?,'yyyy-MM-dd') ");
		sb.append(" ) ");
		return sb.toString();
	}
	
	/**
	 * 删除反向电子对账T_D_ER_REVE_RESULT表数据
	 * 一定先删除T_D_ER_REVE_RESRELA表里的数据
	 * 再删除T_D_ER_REVE_RESULT表里的数据
	 * @return
	 */
	public String getDeleteReveResultSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM T_D_ER_REVE_RESULT A ");
		sb.append(" where A.C_PORT_CODE = ?  ");
		sb.append(" and A.C_FILE_TYPE = ? ");
		sb.append(" and A.C_RPT_TYPE = ? ");
		sb.append(" and A.D_START_DATE <= to_date(?,'yyyy-MM-dd') ");
		sb.append(" and A.D_END_DATE >= to_date(?,'yyyy-MM-dd') ");
		return sb.toString();
	}

	public String getUpdateErInfoWaySql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" update T_D_ER_INFO A ");
		sb.append(" set A.C_DV_ER_WAY = ? ");
		sb.append(" where A.C_SN = ? ");
		sb.append(" and A.C_PORT_CODE = ? ");
		sb.append(" and A.D_DATE = to_date( ? , 'yyyy-MM-dd') ");
		sb.append(" and A.C_FILE_TYPE = ? ");
		sb.append(" and A.C_RPT_TYPE = ? ");
		return sb.toString();
	}
	
	public String deleteErReveInfoSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM T_D_ER_REVE_INFO A ");
		sb.append(" where A.C_PORT_CODE = ? ");
		sb.append(" and A.C_FILE_TYPE = ? ");
		sb.append(" and A.C_RPT_TYPE = ? ");
		sb.append(" and A.D_DATE = to_date(?, 'yyyy-MM-dd') ");
		return sb.toString();
	}
	
	/**
	 * 获取内部的指标项
	 * @return
	 */
	public String getInnerZbItemDataSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.* FROM T_Z_BI_RELA A ");
		sb.append(" where A.C_DZ_CODE = ? ");
		return sb.toString();
	}

	/**
	 * 获取内部数据
	 * @return
	 */
	public abstract String getInnerDataSql();

	/**
	 * 获取外部数据
	 * @return
	 */
	public abstract String getOutDataSql();

	/**
	 * 删除内部数据
	 * @return
	 */
	public abstract String getDeleteInnerDataSql();

	/**
	 * 修改报表的对账方向
	 * @return
	 */
	public abstract String getUpdateReportWaySql();
}
