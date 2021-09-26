package com.yss.ams.base.information.modules.bi.mkt.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 交易市场(包含节假日群设置)sql处理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MktHDayDataSqlBuilder implements SQLBuilder {

	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameRsv,
			String propName) {
		return dbNameRsv.getColumnName(MktColumnName.valueOf(propName));
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogTableName(MktTableName.userInfo);
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回获取交易市场的所有信息的sql
	 * @return
	 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 返回根据市场代码获取交易市场所有信息的sql
	 * @return
	 */
	public String getDataByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);

		buf.append(" AND C_MKT_CODE = ? ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 返回根据节假日群代码和日期类型获取节假日群设置信息的sql
	 * @return
	 */
	public String getHoliDayDataByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append(" SELECT * ");
		buf.append(" FROM T_P_BI_HDAY_SUB ");
		buf.append(" WHERE N_CHECK_STATE = 1 ");
		buf.append(" AND C_DATE_TYPE = 'H' ");
		buf.append(" AND C_HDAY_CODE = ? ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getHoliDayDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append(" SELECT C_HDAY_CODE,N_YEAR,D_HDAY ");
		buf.append(" FROM T_P_BI_HDAY_SUB ");
		buf.append(" WHERE N_CHECK_STATE = 1 ");
		buf.append(" AND C_DATE_TYPE = 'H' ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 拼接根据多个交易市场代码获取交易市场信息的sql
	 * @return
	 */
	public String getDataByTypes() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);

		buf.append(" AND C_MKT_CODE IN (SELECT * FROM TABLE(?)) ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	private void getCommonQuerySqlBuf(StringBuffer buf) {
		buf.append(" select * ");
		buf.append(" from T_P_BI_MKT ");
		buf.append(" where N_CHECK_STATE =1  ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
