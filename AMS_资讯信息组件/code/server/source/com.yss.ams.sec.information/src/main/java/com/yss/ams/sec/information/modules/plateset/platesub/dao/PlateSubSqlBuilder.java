package com.yss.ams.sec.information.modules.plateset.platesub.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * STORY #33682 彭博证券信息接口_重新设计
 * xiaozhilong 20161122
 */
public class PlateSubSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf
					.append(" select count(*) as CNT from T_P_BI_PLATE_SUB a left join (select C_SEC_NAME,C_SEC_CODE from T_P_SV_SEC_BASE) b");
			buf
					.append(" on b.c_sec_code = a.c_sec_code  where  a.N_CHECK_STATE >= 0 ");

			if (valueFieldbuf.length() > 0) {
				buf.append(" AND ").append(valueFieldbuf);
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

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf
					.append("select a.C_PLATE_CODE,a.C_SEC_CODE,a.C_MKT_CODE,a.C_Capital,a.C_Cir_Capital,a.D_BEGIN,");
			buf
					.append(" a.D_END,a.C_DESC,a.N_CHECK_STATE,a.C_UPDATE_BY,a.C_UPDATE_TIME,a.C_CHECK_BY,a.C_CHECK_TIME");
			buf
					.append(" ,b.c_sec_name as C_SEC_NAME,C_IDEN from T_P_BI_PLATE_SUB a left join (select C_SEC_NAME,C_SEC_CODE from T_P_SV_SEC_BASE) b");
			buf
					.append(" on b.c_sec_code = a.c_sec_code  where  a.N_CHECK_STATE >= 0 ");

			if (valueFieldbuf.length() > 0) {
				buf.append(" AND ").append(valueFieldbuf);
			}
			buf.append(" order by a.N_CHECK_STATE asc, a.rowId asc ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_PLATE_CODE")) {
				valueFieldbuf
						.append(" a.C_PLATE_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}else if (fieldedName.equals("C_SEC_CODE")) {   ///增加证券代码作为查询条件
				valueFieldbuf.append("a.C_SEC_CODE = ? AND ");
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
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(PlateSubColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(PlateSubTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(PlateSubTableName.userInfo);
	}

	public String getPlateSubExtendSql(List<String> paraNameList)
			throws Exception {
		return null;
	}

	public String getPlateSubExtendSqlCount(List<String> paraNameList)
			throws Exception {
		return null;
	}

	public String getOrgExtendColumnName(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(PlateSubColumnName.valueOf(name));
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(PlateSubTableName.userInfo);
	}

}
