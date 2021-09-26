package com.yss.ams.sec.information.modules.mp.FwMktValue.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
/**
 * 远期外汇行情SQL构造类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class FwMktValueSqlBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回 根据前台查询条件获取远期外汇行情数据的总数量 的SQL
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select count(*) as CNT from T_D_MP_SEC_FW a  ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.N_CHECK_STATE , a.C_IDEN ");
		retSql = buf.toString();

		return retSql;
	}

	/**
	 * 返回 根据前台查询条件获取远期外汇行情的所有数据 的SQL
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
		buf.append(" select a.C_IDEN,a.C_SEC_CODE,a.D_MKT,a.C_MKT_CLS,a.C_DV_VAR_DUR,a.C_MKT_TIME,");
		buf.append(" a.D_SPOT,a.D_FW,a.N_PRICE_BUY,a.N_PRICE_SELL,a.N_POINT_BUY,a.N_POINT_SELL, ");
		buf.append(" a.C_DESC,a.C_DATA_IDF,a.N_CHECK_STATE,a.C_UPDATE_BY,a.C_UPDATE_TIME,a.C_CHECK_BY,a.C_CHECK_TIME,a.N_STATE from T_D_MP_SEC_FW a  ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.N_CHECK_STATE , a.C_IDEN ");
		retSql = buf.toString();

		return retSql;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(FwMktValueColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(FwMktValueTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(FwMktValueTableName.userInfo);
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
			if (fieldedName.equals("C_MKT_CLS")) {
				valueFieldbuf.append(" a.C_MKT_CLS = ?  AND ");
			} else if (fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf.append(" a.C_SEC_CODE = ?  AND ");
			} else if (fieldedName.equals("D_BEGIN")) {
				valueFieldbuf.append("a.D_MKT >= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("D_END")) {
				valueFieldbuf.append("a.D_MKT <= TO_DATE(?,'yyyy-MM-dd') AND ");
			}else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}

		}
		
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(FwMktValueTableName.userInfo);
	}
}
