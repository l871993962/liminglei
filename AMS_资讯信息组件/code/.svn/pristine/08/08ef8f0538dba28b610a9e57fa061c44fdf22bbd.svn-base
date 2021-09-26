package com.yss.ams.sec.information.modules.sv.base.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 理财产品信息SQL构造器
 * @author 马向峰
 *
 */
public class SecBaseLcSqlBuilder implements SQLBuilder
{

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception
	{
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try
		{
			SecBaseCondition secbase = new SecBaseCondition();
			secbase.setWhereSql(valueFieldbuf,paraNameList);

			buf.append("select count(*) as CNT ");
			buf.append("from T_P_SV_SEC_BASE a ");
			buf.append(" join (select c_da_code,c_sec_var_code from V_S_DA_SEC_VAR )b on a.c_sec_var_code = b.c_sec_var_code");
			
			if (valueFieldbuf.length() > 0)
			{
				buf.append(" where ");
				buf.append(valueFieldbuf);
			}
			buf.append(" AND (b.C_DA_CODE like 'LC%' or b.C_DA_CODE like 'JJ%') ");
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
			SecBaseCondition secbase = new SecBaseCondition();
			secbase.setWhereSql(valueFieldbuf,paraNameList);

			buf.append("select a.* ,b.c_da_code ");
			buf.append("from T_P_SV_SEC_BASE a ");
			buf.append(" join (select c_da_code,c_sec_var_code from V_S_DA_SEC_VAR )b on a.c_sec_var_code = b.c_sec_var_code");

			if (valueFieldbuf.length() > 0)
			{
				buf.append(" where ");
				buf.append(valueFieldbuf);
			}
			buf.append(" AND (b.C_DA_CODE like 'LC%' or b.C_DA_CODE like 'JJ%') ");			
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

	public String getRemindFhztDateSql() throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		try
		{
			buf.append("SELECT A.*, D.C_PORT_CODE, D.C_PORT_NAME                                      ");
			buf.append("  FROM (SELECT *                                                              ");
			buf.append("          FROM T_P_SV_SEC_BASE                                                ");
			buf.append("         WHERE (C_SEC_VAR_CODE LIKE 'LC%' OR C_SEC_VAR_CODE LIKE 'JJ%')       ");
			buf.append("           AND TRIM(C_DV_ISSUE) IS NOT NULL                                   ");
			buf.append("           AND TRIM(C_DV_ASSURE) IS NOT NULL                                  ");
			buf.append("           AND TRIM(N_PRICE_ISSUE) IS NOT NULL                                ");
			buf.append("           AND N_CHECK_STATE = 1) A                                           ");
			buf.append("  JOIN (SELECT MAX(D_STOCK) AS D_STOCK, C_SEC_CODE, C_PORT_CODE               ");
			buf.append("          FROM T_D_AI_STOCK                                                   ");
			buf.append("         WHERE D_STOCK < SYSDATE                                              ");
			buf.append("         GROUP BY C_SEC_CODE, C_PORT_CODE) B ON A.C_SEC_CODE = B.C_SEC_CODE   ");
			buf.append("  JOIN (SELECT D_STOCK, C_SEC_CODE, C_PORT_CODE                               ");
			buf.append("          FROM T_D_AI_STOCK                                                   ");
			buf.append("         WHERE N_AMOUNT > 0                                                   ");
			buf.append("         GROUP BY C_SEC_CODE, D_STOCK, C_PORT_CODE) C ON A.C_SEC_CODE =       ");
			buf.append("                                                         C.C_SEC_CODE         ");
			buf.append("                                                     AND B.D_STOCK =          ");
			buf.append("                                                         C.D_STOCK            ");
			buf.append("                                                     AND B.C_PORT_CODE =      ");
			buf.append("                                                         C.C_PORT_CODE        ");
			buf.append("  JOIN (SELECT C_PORT_CODE, C_PORT_NAME FROM T_P_AB_PORT) D ON B.C_PORT_CODE =");
			buf.append("                                                               D.C_PORT_CODE  ");
			
			retSql = buf.toString();
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}
}
