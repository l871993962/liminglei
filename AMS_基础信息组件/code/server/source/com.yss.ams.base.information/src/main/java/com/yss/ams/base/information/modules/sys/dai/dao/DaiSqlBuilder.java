package com.yss.ams.base.information.modules.sys.dai.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 核算项目字典表T_S_DAI_ITEM SQLBuilder
 *
 */
public class DaiSqlBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(DaiColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(DaiTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(DaiTableName.userInfo);
	}

	/**
	 * 根据条件得到 核算项目字典表T_S_DAI_ITEM 相关数据总数
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select count(*) as CNT from T_S_DAI_ITEM a");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	/**
	 * 根据条件得到 核算项目字典表T_S_DAI_ITEM 所有符合条件数据信息。
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf
					.append("SELECT '' as C_IDEN,C_DAI_CODE,C_DAI_NAME,C_DV_KM_CLS,N_FUND_WAY,");
			buf
					.append(" N_ORDER,C_DV_BOOL_TYPE_AM,C_STOCK_CLS,C_DAI_TYPE FROM T_S_DAI_ITEM a ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			buf.append("  ORDER BY C_DV_KM_CLS,N_ORDER ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	/**
	 * 拼接sql语句where条件
	 * @param valueFieldbuf 输出的sql语句
	 * @param paraNameList 条件参数
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DAI_CODE")) {
				valueFieldbuf.append(" a.C_DAI_CODE like ?  AND ");
			} else if (fieldedName.equals(("C_DAI_NAME"))) {
				valueFieldbuf.append("  a.C_DAI_NAME like ? AND ");
			} else if (fieldedName.equals("C_DV_KM_CLS")) {
				valueFieldbuf.append(" a.C_DV_KM_CLS like ? AND ");
			} else if (fieldedName.equals("N_FUND_WAY")) {
				valueFieldbuf.append(" a.N_FUND_WAY like ? AND ");
			} else if (fieldedName.equals("N_ORDER")) {
				valueFieldbuf.append(" a.N_ORDER like ? AND ");
			} else if (fieldedName.equals("C_DV_BOOL_TYPE_AM")) {
				valueFieldbuf.append(" a.C_DV_BOOL_TYPE_AM like ? AND ");
			} else if (fieldedName.equals("C_STOCK_CLS")) {
				valueFieldbuf.append(" a.C_STOCK_CLS like ? AND ");
			} else if (fieldedName.equals("C_DAI_TYPE")) {
				valueFieldbuf.append(" a.C_DAI_TYPE like ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DaiTableName.userInfo);
	}
}
