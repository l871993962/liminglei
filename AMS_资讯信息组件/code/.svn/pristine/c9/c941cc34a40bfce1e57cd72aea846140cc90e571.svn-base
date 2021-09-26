package com.yss.ams.sec.information.modules.sv.base.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 期货结算债券转换信息 SQL构造类
 * @author gongyue
 * 资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
public class FutureFactorSqlBuilder implements SQLBuilder{
	
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
		return dbNameResolver.getTableName(FutureFactorTabName.futurefactor);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(FutureFactorColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(FutureFactorTabName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(FutureFactorTabName.futurefactor);
	}

	/**
	 * 返回 根据前台查询条件获取期货结算债券转换信息所有数据 的SQL
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select a.* from T_P_SV_TF_CF a ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);

			}

			buf.append(" order by a.N_CHECK_STATE asc");

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
	 * 返回 根据前台查询条件获取 期货结算债券转换信息 数据总数量 的SQL
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from T_P_SV_TF_CF a ");
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
	 * 根据前台传送过来参数判断添加筛选条件 的SQL
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_CONTRACT_CODE")) {
				valueFieldbuf.append(" a.C_CONTRACT_CODE = ? AND ");
			}else if(fieldedName.equals("ARRAY_C_MKT_CODE")) {
				valueFieldbuf.append(" a.C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if(fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf.append("a.C_SEC_CODE = ? AND ");
			/*- Fixed by huangsq Start BUG #133162 回收站不能按日期查询 */
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}
			/*- Fixed by huangsq End BUG #133162 回收站不能按日期查询 */
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

}
