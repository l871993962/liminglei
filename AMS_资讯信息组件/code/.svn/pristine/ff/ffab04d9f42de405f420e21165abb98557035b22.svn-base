package com.yss.ams.sec.information.modules.aa.etf.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
/**
 * ETF基本信息 SQL构造类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class EtfSqlBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver arg0) {
		return null;
	}

	public String buildInsertSql(DBNameResolver arg0) {
		return null;
	}

	/**
	 * 返回 根据前台查询条件获取ETF基本信息的所有数据 的SQL
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select a.* from T_P_AA_ETF a ");
		buf
				.append(" left join (select C_DAT_CODE,C_PORT_CODE from T_P_AB_PORT) b on a.C_PORT_CODE =b.C_PORT_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf
				.append(" order by a.N_CHECK_STATE, a.C_PORT_CODE , a.C_SR_CODE , a.C_DV_SUPPLY_MODE, a.C_IDEN ");
		retSql = buf.toString();

		return retSql;
	}

	/**
	 * 返回 根据前台查询条件获取ETF基本信息数据的总数量 的SQL
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select count(C_IDEN) AS CNT  from T_P_AA_ETF a ");
		buf
				.append(" left join (select C_DAT_CODE,C_PORT_CODE from T_P_AB_PORT) b on a.C_PORT_CODE =b.C_PORT_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		retSql = buf.toString();

		return retSql;
	}

	/**
	 * 根据前台传送过来参数判断添加筛选条件 的SQL
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_SR_CODE")) {
				valueFieldbuf.append(" a.C_SR_CODE like ?  AND ");
			} else if (fieldedName.equals("C_DV_SUPPLY_MODE")) {
				valueFieldbuf.append(" a.C_DV_SUPPLY_MODE = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf
						.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String buildSelectSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return dbNameResolver.getColumnName(EtfColumnName.valueOf(name));
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getTableName(EtfTableName.recycle);
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getTableName(EtfTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(EtfTableName.userInfo);
	}

}
