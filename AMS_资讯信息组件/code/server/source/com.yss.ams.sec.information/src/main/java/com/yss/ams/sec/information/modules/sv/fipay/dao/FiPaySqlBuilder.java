package com.yss.ams.sec.information.modules.sv.fipay.dao;


import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 债券历史付息信息sql处理类
 * @author yuankai 
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 */
public class FiPaySqlBuilder implements SQLBuilder
{

	/**
	 * 返回根据前台查询条件获取债券历史付息信息总记录数的sql
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception
	{
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try
		{
			setWhereSql(valueFieldbuf,paraNameList);

			buf.append("select count(*) as CNT from T_D_SV_FI_PAY a ");
			if (valueFieldbuf.length() > 0)
			{
				buf.append(" where ");
				buf.append(valueFieldbuf);
			}
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

	/**
	 * 返回根据前台查询条件获取债券历史付息信息所有记录的sql
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
			setWhereSql(valueFieldbuf,paraNameList);

			buf.append("select * from T_D_SV_FI_PAY a ");
			if (valueFieldbuf.length() > 0)
			{
				buf.append(" where ");
				buf.append(valueFieldbuf);
				buf.append(" order by a.N_CHECK_STATE asc, a.rowId asc ");
			}
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
	
	/**
	 * 根据前台传送过来参数判断添加筛选条件，返回获取债券历史付息信息数据的sql
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer buf, List<String> paraNameList)
	{
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList)
		{
			if (fieldedName.equals("ARRAY_C_MKT_CODE"))
			{
				buf.append(" a.C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
			else if (fieldedName.equals(("D_START")))
			{
				buf.append(" a.D_ADJ >= to_date(?,'yyyy-MM-dd') AND ");
			}
			else if (fieldedName.equals(("D_END")))
			{
				buf.append(" A.D_ADJ <= to_date(?,'yyyy-MM-dd') AND ");
			}
			else if (fieldedName.equals("C_SEC_CODE"))
			{
				buf.append(" a.C_SEC_CODE = ? AND ");
			}
			else if (fieldedName.equals("C_DEL_TIME_START"))
			{
				buf.append("a.C_DEL_TIME >= ? AND ");
			}
			else if (fieldedName.equals("C_DEL_TIME_END"))
			{
				buf.append("a.C_DEL_TIME <= ? AND ");
			}
			else if(fieldedName.equals("D_PAY_BEGIN")){
				buf.append("a.D_BEGIN <= TO_DATE(?,'yyyy-MM-dd') AND ");
			}
			else if(fieldedName.equals("D_PAY_END")){
				buf.append("a.D_END >= TO_DATE(?,'yyyy-MM-dd') AND ");
			}
		}
		StringUtil.delLastSplitMark(buf, "AND ");
	}
	
	
	public String buildDeleteSql(DBNameResolver dbnameresolver)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver, String s)
	{
		return dbnameresolver.getColumnName(FiPayColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver)
	{
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(FiPayTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver)
	{
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(FiPayTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(FiPayTableName.userInfo);
	}

}
