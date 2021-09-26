package com.yss.ams.sec.information.modules.sv.base.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
/**
 * 现货品种信息SQL建造类
 * 
 * Added by shiliang,资讯信息组件拆分2017-06-26
 * 资讯信息拆分   STORY #42948 资讯信息管理组件化拆分
 * */
public class SecBaseXhSqlBuilder implements SQLBuilder {

	/**
	 * 返回 根据前台查询条件获取现货信息数据总数量 的SQL
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			// CL 20121120 setWhereSql 方法封装为公用方法，避免每个类中写一个
			SecBaseCondition secbase = new SecBaseCondition();
			secbase.setWhereSql(valueFieldbuf, paraNameList);

			buf.append("select count(*) as CNT ");
			buf.append("from T_P_SV_SEC_BASE a ");
			buf
					.append("left join (select c_da_code,c_sec_var_code from V_S_DA_SEC_VAR )b on a.c_sec_var_code = b.c_sec_var_code");
			buf.append(" where b.c_sec_var_code like 'XH%' ");

			if (valueFieldbuf.length() > 0) {
				buf.append(" AND ");
				buf.append(valueFieldbuf);
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
	 * 返回 根据前台查询条件获取现货信息所有数据 的SQL
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	public String getQueryConditionSql(List<String> paraNameList) throws Exception
	{
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try
		{
			//CL 20121120 setWhereSql 方法封装为公用方法，避免每个类中写一个
			SecBaseCondition secbase = new SecBaseCondition();
			secbase.setWhereSql(valueFieldbuf, paraNameList);
			
			buf.append("select a.* ,b.c_da_code ");
			buf.append("from T_P_SV_SEC_BASE a ");
			buf.append("left join (select c_da_code,c_sec_var_code from V_S_DA_SEC_VAR)b on a.c_sec_var_code = b.c_sec_var_code");
			buf.append(" where b.c_sec_var_code like 'XH%' ");
			
			if (valueFieldbuf.length() > 0)
			{
				buf.append(" AND ");
				buf.append(valueFieldbuf);
			}
			buf.append(" order by a.N_CHECK_STATE asc, a.rowId asc ");
			retSql = buf.toString();
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}
	public String buildDeleteSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getColumnNameByProperty(DBNameResolver dbnameresolver, String s)
	{
		return dbnameresolver.getColumnName(SecBaseColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver)
	{
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(SecBaseTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver)
	{
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(SecBaseTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(SecBaseTableName.userInfo);
	}

}
