package com.yss.ams.sec.information.modules.mp.hggthq.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 回购收益行情 SQL构造类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class CounterRateSqlBuilder implements SQLBuilder {

	/**
	 * 返回 根据公共回购收益行情变更对应证券利率 的SQL
	 * @return sqlString
	 */
	public String UpdateSetRateSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE T_D_MP_HG_MKT SET N_RATE = ? "); 
		buf.append(" WHERE D_MKT = ? AND N_DURATION = ? AND C_IS_PUBLIC = '0' "); 
		buf.append(" AND C_BIZ_TYPE = 'TRY' ");
		String sqlString = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sqlString;
	}
	
	/**
	 * 根据操作类型 返回证券回购收益行情数据 应处理的 SQL
	 * @param operType 操作类型
	 * @return sqlString
	 */
	public String syncSecRateSql(String operType) {
		StringBuffer buf = new StringBuffer();
		if (operType.equals("Audit")) {
			buf.append(" UPDATE T_D_MP_HG_MKT SET N_CHECK_STATE = 1 ");
		}else if (operType.equals("UnAudit")) {
			buf.append(" UPDATE T_D_MP_HG_MKT SET N_CHECK_STATE = 0 ");
		}else if (operType.equals("Delete")) {
			buf.append(" DELETE T_D_MP_HG_MKT ");
		}
		buf.append(" WHERE D_MKT = ? AND N_DURATION = ? AND C_IS_PUBLIC = '0' "); 
		buf.append(" AND C_BIZ_TYPE = 'TRY' ");
		String sqlString = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sqlString;
	}
	
	/**
	 * 返回 根据前台查询条件获取回购收益行情数据的数量 的SQL
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
		buf.append(" select count(*) as CNT from T_D_MP_HG_MKT a ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.N_CHECK_STATE,A.C_SEC_CODE ASC");
		retSql = buf.toString();

		return retSql;
	}

	/**
	 * 返回 根据前台查询条件获取回购收益行情的所有数据 的SQL
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
		// buf.append(" select A.C_IDEN,a.C_SEC_CODE,A.D_MKT,a.D_BEGIN,a.D_END,a.N_DURATION,a.N_RATE,a.C_BUSI_TYPE, ");
		// buf.append("  a.C_IS_PUBLIC,a.C_CREATE_BY,a.C_CREATE_TIME,a.C_CHECK_BY, ");
		// buf.append(" a.C_CHECK_TIME,a.C_UPDATE_BY,a.C_UPDATE_TIME,a.N_CHECK_STATE");
		buf.append("SELECT * FROM T_D_MP_HG_MKT A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.D_MKT,A.N_DURATION ASC,a.C_BIZ_TYPE,a.N_CHECK_STATE,A.C_SEC_CODE ASC ");
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
			if (fieldedName.equals("N_DURATION")) {
				valueFieldbuf.append(" A.N_DURATION = TO_CHAR(?) AND ");
			} else if (fieldedName.equals("D_BEGIN")) {
				valueFieldbuf.append("a.D_MKT >= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("C_BIZ_TYPE")){
				valueFieldbuf.append(" a.C_BIZ_TYPE = ? AND");//added by dingxk
			} else if (fieldedName.equals("D_END")) {
				valueFieldbuf.append("a.D_MKT <= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf.append(" a.C_SEC_CODE = ? AND ");
			} else if (fieldedName.equals("C_IS_PUBLIC")) {
				valueFieldbuf.append(" a.C_IS_PUBLIC = ? AND ");
			} else if (fieldedName.equals("D_MKT")) {
				valueFieldbuf.append(" a.D_MKT = TO_DATE(?,'yyyy-MM-dd') AND ");
			} 
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
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
		return dbnameresolver.getColumnName(CounterRateColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(CounterRateTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(CounterRateTableName.counterrate);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver
				.getLogSequenceName(CounterRateTableName.counterrate);
	}

}
