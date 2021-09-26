package com.yss.ams.sec.information.modules.sv.secSoldBack.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SecSoldBackBuilder implements SQLBuilder
{

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception
	{
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try
		{
			this.setWhereSql(valueFieldbuf, paraNameList);
			
			buf.append("select count(*) as CNT ");
			buf.append("from T_P_SV_SEC_SOLDBACK a ");
			
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

	public String getQueryConditionSql(List<String> paraNameList) throws Exception
	{
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try
		{
			this.setWhereSql(valueFieldbuf, paraNameList);
			
			buf.append("select a.* ");
			buf.append("from T_P_SV_SEC_SOLDBACK a ");
			
			if (valueFieldbuf.length() > 0)
			{
				buf.append(" where ");
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
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf
						.append(" a.C_SEC_CODE LIKE ?  AND ");
			} 
			else if(fieldedName.equals("C_SEC_MKT_CODE")) {
				valueFieldbuf.append(" a.C_SEC_MKT_CODE LIKE ? AND ");
			}
			else if (fieldedName.equals("ARRAY_C_MKT_CODE")) {
				valueFieldbuf.append(" a.C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
			else if (fieldedName.equals("D_SOLDBACK_BEGIN")) {
				valueFieldbuf.append(" a.D_SOLDBACK_BEGIN >= TO_DATE(?, 'YYYY-MM-DD') AND ");
			}
			else if (fieldedName.equals("D_SOLDBACK_END")) {
				valueFieldbuf.append(" a.D_SOLDBACK_BEGIN <= TO_DATE(?, 'YYYY-MM-DD') AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
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
		return dbnameresolver.getColumnName(SecSoldBackColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver)
	{
		return dbnameresolver.getLogTableName(SecSoldBackTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver)
	{
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(SecSoldBackTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(SecSoldBackTableName.userInfo);
	}

}
