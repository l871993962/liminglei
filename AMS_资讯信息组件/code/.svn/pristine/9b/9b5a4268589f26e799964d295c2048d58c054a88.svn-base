package com.yss.ams.sec.information.modules.mp.preStockInterest.dao;


import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 优先股计息信息sql处理类
 * @author yuankai 
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 */
public class PreStockInterestSqlBuilder  implements SQLBuilder{

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
		return dbNameResolver.getTableName(PreStockInterestTableName.userInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(PreStockInterestColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(PreStockInterestTableName.userInfo);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(PreStockInterestTableName.userInfo);
	}

	/**
	 * 返回根据前台查询条件获取优先股计息信息所有记录的sql
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

			buf.append("select a.*  from ").append(PreStockInterestTableName.userInfo.toString()).append(" a ");
			buf.append(" left join (select c_sec_code,C_SEC_VAR_CODE from t_p_sv_sec_base) b on a.c_sec_code = b.c_sec_code ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			retSql = buf.toString();
		} catch (Exception ex) {
			throw new Exception("查询优先股信息出错",ex);
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	/**
	 * 返回根据前台查询条件获取优先股计息信息总记录数的sql
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

			buf.append("select count(*) AS CNT  from ").append(PreStockInterestTableName.userInfo.toString()).append(" a ");
			buf.append(" left join (select c_sec_code,C_SEC_VAR_CODE from t_p_sv_sec_base) b on a.c_sec_code = b.c_sec_code ");
			
			if (valueFieldbuf.length() > 0) {
				buf.append(" where   ").append(valueFieldbuf);
			}
			retSql = buf.toString();
		} catch (Exception ex) {
			throw new Exception("查询优先股信息出错",ex);
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}
	
	/**
	 * 根据前台传送过来参数判断添加筛选条件，返回获取优先股计息信息数据的sql
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_MKT_CODE")) {
				valueFieldbuf.append(" A.C_SEC_CODE IN  (SELECT C_SEC_CODE FROM T_P_SV_SEC_BASE WHERE C_MKT_CODE IN (SELECT * FROM TABLE(?)))  AND ");
			} else if (fieldedName.equals("D_BEGIN")) {
				valueFieldbuf.append("a.D_INCOME >= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("D_END")) {
				valueFieldbuf.append("a.D_INCOME <= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf.append(" a.C_SEC_CODE = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_SEC_CODE")) {
					valueFieldbuf.append(" a.C_SEC_CODE IN (SELECT * FROM TABLE(?)) AND ");//交易证券in
			}else
			{
				// 默认之后新增的优先股品种信息里面都包含YXG字段
				valueFieldbuf.append("b.C_SEC_VAR_CODE like '%YXG%' AND  ");
			}
		}
		
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, "AND ");
		}
	}
}