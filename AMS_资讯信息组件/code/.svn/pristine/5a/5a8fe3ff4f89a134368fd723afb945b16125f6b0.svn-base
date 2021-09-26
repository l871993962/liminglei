package com.yss.ams.sec.information.modules.sv.indexstock.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;


/**
 * 
 * @author chenbo
 *2017-06-22
 *#42948 资讯信息管理组件化拆分
 */
public class IndexStockSqlBuilder implements SQLBuilder {

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(IndexStockTableName.indexStock);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(IndexStockColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogTableName(IndexStockTableName.indexStock);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver
				.getLogSequenceName(IndexStockTableName.indexStock);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();
		try {
			buf.append(" select a.* from ");
			buf.append(IndexStockTableName.indexStock).append(" a ");
			setWhereSql(fieldValueBuf, paraNameList);
			if (fieldValueBuf.length() > 1) {
				buf.append(" where ").append(fieldValueBuf);
			}
			buf.append(" order by a.N_CHECK_STATE asc, a.C_INDEX_CODE, A.D_BEGIN ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();

		try {
			buf.append(" select COUNT(*) AS CNT from ");
			buf.append(IndexStockTableName.indexStock).append(" a ");
			setWhereSql(fieldValueBuf, paraNameList);
			if (fieldValueBuf.length() > 1) {
				buf.append(" where ").append(fieldValueBuf);
			}
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_INDEX_CODE")) {
				valueFieldbuf.append(" a.C_INDEX_CODE like ? AND ");
			} else if (fieldedName.equals("ARRAY_INDEX_CODE")) {
				valueFieldbuf
						.append(" a.C_INDEX_CODE IN (select * from table(?)) AND ");
			} else if (fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf.append(" a.C_SEC_CODE like ? AND ");
			} else if (fieldedName.equals("D_START")) {
				valueFieldbuf
						.append(" a.D_BEGIN >= to_date(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("D_END")) {
				valueFieldbuf
						.append(" a.D_BEGIN <= to_date(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("D_BEGIN")) {
				valueFieldbuf
						.append(" a.D_BEGIN = to_date(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append(" a.C_DEL_TIME <= ? AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getSelectedSecMapSql() throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		try {
			buf.append(" SELECT C_SEC_CODE ");
			buf.append(" FROM T_P_SV_INDEX_STOCK B ");
			buf.append(" WHERE B.C_INDEX_CODE = ? ");
			buf.append(" AND B.D_BEGIN = to_date (?, 'yyyy-MM-dd') ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	public String getDeleteByCodeAndDateSql() throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		try {
			buf.append(" DELETE FROM ");
			buf.append(IndexStockTableName.indexStock);
			buf.append(" WHERE C_INDEX_CODE = ? ");
			buf.append(" AND D_BEGIN = ? ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}
	
	/**
	 * 获取最近的成分券信息
	 * @return
	 * @throws Exception
	 */
	public String getLastSelectedSecMapSql() throws Exception{
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		try {
			buf.append(" SELECT C_SEC_CODE FROM T_P_SV_INDEX_STOCK A ");
			buf.append(" WHERE A.D_BEGIN = (SELECT MAX(B.D_BEGIN) ");
			buf.append(" FROM T_P_SV_INDEX_STOCK B ");
			buf.append(" WHERE B.C_INDEX_CODE = ? ");
			buf.append(" AND B.D_BEGIN < TO_DATE(?, 'yyyy-MM-dd') ");
			buf.append(" AND A.C_INDEX_CODE = B.C_INDEX_CODE) ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}
}
