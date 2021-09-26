package com.yss.ams.sec.information.modules.sv.base.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 存放品种信息 SQL构造器
 * @author 马向峰
 *
 */
public class SecBaseCkSqlBuilder implements SQLBuilder
{

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception
	{
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try
		{
			//CL 20121120 setWhereSql 方法封装为公用方法，避免每个类中写一个
			SecBaseCondition secbase = new SecBaseCondition();
			secbase.setWhereSql(valueFieldbuf, paraNameList);

			buf.append("select count(*) as CNT ");
			buf.append("from T_P_SV_SEC_BASE a ");
			buf.append("left join (select c_da_code,c_sec_var_code from V_S_DA_SEC_VAR )b on a.c_sec_var_code = b.c_sec_var_code");
			buf.append(" where (b.c_sec_var_code like 'CK%' or b.c_sec_var_code like 'DK%') ");
			//add by zhd 2016-09-21
			//STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
			buf.append(" and a.c_sjsszq is null ");
			
			if (valueFieldbuf.length() > 0)
			{
				buf.append(" AND ");
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
			//CL 20121120 setWhereSql 方法封装为公用方法，避免每个类中写一个
			SecBaseCondition secbase = new SecBaseCondition();
			secbase.setWhereSql(valueFieldbuf, paraNameList);

			buf.append("select a.* ,b.c_da_code ");
			buf.append("from T_P_SV_SEC_BASE a ");
			buf.append("left join (select c_da_code,c_sec_var_code from V_S_DA_SEC_VAR ) b on a.c_sec_var_code = b.c_sec_var_code");
			// 添加对贷款的支持  modify by fjl 20131119
			// buf.append(" where b.c_sec_var_code like 'CK%' ");
			buf.append(" where (b.c_sec_var_code like 'CK%' or b.c_sec_var_code like 'DK%') ");
			//add by zhd 2016-09-21
			//STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
			buf.append(" and a.c_sjsszq is null ");
			
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
		return dbnameresolver.getTableName(SecBaseTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver)
	{
		return dbnameresolver.getTableName(SecBaseTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(SecBaseTableName.userInfo);
	}
	
	/**
	 * add by zhd 2016-09-07
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 查询实际所属证券
	 * @return
	 */
	public String getQueryForSjsszq() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * ");
		buf.append(" FROM T_P_SV_SEC_BASE A ");
		buf.append(" WHERE (A.C_SEC_VAR_CODE LIKE 'CK%' ");
		buf.append(" OR A.C_SEC_VAR_CODE LIKE 'DK%') ");
		buf.append(" AND A.C_SJSSZQ IS NULL "); 
		buf.append(" AND A.N_CHECK_STATE = 1 ");
		return buf.toString();
	}
	
	/**
	 * add by zhd 2016-09-09
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 获取实际所属证券的证券信息
	 */
	public String getQueryBySjsszq() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * ");
		buf.append(" FROM T_P_SV_SEC_BASE A ");
		buf.append(" WHERE (A.C_SEC_VAR_CODE LIKE 'CK%' ");
		buf.append(" OR A.C_SEC_VAR_CODE LIKE 'DK%') ");
		buf.append(" AND A.C_SJSSZQ IS NULL "); 
		buf.append(" AND A.N_CHECK_STATE = 1 ");
		buf.append(" AND A.C_SEC_CODE = ? ");
		return buf.toString();
	}
	
	/**
	 * add by zhd 2016-09-19
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 获取虚拟证券信息
	 */
	public String getQueryBySecCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * ");
		buf.append(" FROM T_P_SV_SEC_BASE A ");
		buf.append(" WHERE (A.C_SEC_VAR_CODE LIKE 'CK%' ");
		buf.append(" OR A.C_SEC_VAR_CODE LIKE 'DK%') ");
		buf.append(" AND A.N_CHECK_STATE = 1 ");
		buf.append(" AND A.C_SJSSZQ = ? ");
		return buf.toString();
	}

}
