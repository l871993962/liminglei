package com.yss.ams.base.information.modules.bi.region.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 地区信息SQL构造器
 * @author 马向峰 拆分
 *@Date 20170601
 */
public class AreaSqlBuilder implements SQLBuilder {

	/**
	 * 查询地区信息的数量的
	 * @param paraNameList 查询参数
	 * @return SQL
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();

		try {
			buf.append(" select COUNT(*) AS CNT from T_P_BI_AREA a ");

			setWhereSql(fieldValueBuf, paraNameList);
			if (fieldValueBuf.length() > 1) {
				buf.append(" where ").append(fieldValueBuf);
			}

			// buf.append(" start with a.C_AREA_CODE_P='[root]' connect by prior C_AREA_CODE=C_AREA_CODE_P ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	/**
	 * 查询地区信息
	 * @param paraNameList 查询参数
	 * @return SQL
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();

		try {
			buf.append(" select a.* from T_P_BI_AREA a ");
			setWhereSql(fieldValueBuf, paraNameList);

			if (fieldValueBuf.length() > 1) {
				buf.append(" where ").append(fieldValueBuf);
			}

			// buf.append(" start with a.C_AREA_CODE_P='[root]' connect by prior C_AREA_CODE=C_AREA_CODE_P ");
			// buf.append(" order by a.N_CHECK_STATE asc , Level,C_AREA_CODE_P,C_AREA_CODE ,a.C_IDEN ASC");
			buf
					.append(" order by a.N_CHECK_STATE asc ,C_AREA_CODE_P,C_AREA_CODE ,a.C_IDEN ASC");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	/**
	 * 组装where查询条件
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("ARRAY_C_AREA_CODE")) {
				valueFieldbuf
						.append("a.C_AREA_CODE in (SELECT * FROM TABLE(?)) AND ");
			}
		}
		valueFieldbuf.append("a.C_AREA_CODE_P <> '[root]' AND ");

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/**
	 * 获取地区信息SQL
	 * 
	 * @param dbnameresolver
	 * @return
	 */
	public String getAllAreasByTypeSql(DBNameResolver dbnameresolver) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" select a.* from ");
		sqlBuff.append(getTableName(dbnameresolver));
		sqlBuff.append(" a ");
		sqlBuff.append(" where N_CHECK_STATE = 1 ");
		sqlBuff.append(" and a.C_AREA_CODE not in ");
		sqlBuff.append(" (select e.C_AREA_CODE ");
		sqlBuff.append(" from T_P_BI_AREA e ");
		sqlBuff.append(" where e.C_AREA_CODE_P = '[root]' ");
		sqlBuff.append(" and not exists ");
		sqlBuff.append(" (select 1 ");
		sqlBuff.append(" from T_P_BI_AREA x ");
		sqlBuff.append(" where x.C_AREA_CODE_P = e.C_AREA_CODE)) ");
		sqlBuff.append(" start with a.C_AREA_CODE_P = '[root]' ");
		sqlBuff.append(" connect by prior C_AREA_CODE = C_AREA_CODE_P ");
		sqlBuff
				.append(" order by Level, C_AREA_CODE_P, C_AREA_CODE, a.N_CHECK_STATE asc ");
		return sqlBuff.toString();
	}

	public String getAllTopAreasSql() {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff
				.append("select a.* from T_P_BI_AREA a  where  N_CHECK_STATE =1 and a.C_AREA_CODE_P='[root]' ");
		return sqlBuff.toString();
	}
	
	public String getAllNotTopAreasSql() {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff
				.append("select a.* from T_P_BI_AREA a  where  N_CHECK_STATE = 1 and a.C_AREA_CODE_P != '[root]' ");
		return sqlBuff.toString();
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
		return dbnameresolver.getColumnName(AreaColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(AreaTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(AreaTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(AreaTableName.userInfo);
	}

	/**
	 * 查询审核通过的地区信息
	 * @return	SQL
	 */
	public String getCommonSql() {
		String sql = "select * from T_P_BI_AREA a where  a.N_CHECK_STATE =1";
		return sql;
	}
	
	/**
	 * 通过地区编号查找地区信息
	 * @return	SQL
	 */
	public String getAllDataSqlByKeys(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" select * from T_P_BI_AREA a where  a.N_CHECK_STATE =1 ");
		buf.append(" and a.C_AREA_CODE IN (SELECT * FROM TABLE(?)) ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 通过地区编号查找地区信息
	 * @return	SQL
	 */
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" select a.* ");
		buf.append(" from T_P_BI_AREA a ");
		buf.append(" where a.C_AREA_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

}
