package com.yss.ams.sec.information.modules.sv.base.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 期货&期权保证金 SQL构造类
 * @author shiliang
 * 资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
public class SecBaseQhbzjSqlBuilder implements SQLBuilder{

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
		return dbNameResolver.getTableName(SecBaseQhbzjTableName.userInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(SecBaseQhbzjColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(SecBaseTableName.userInfo);
	}

	/**
	 * 返回 根据前台查询条件获取期货&期权保证金所有数据 的SQL
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
		try
		{
			setWhereSql(valueFieldbuf, paraNameList);
	
			buf.append("SELECT a.c_iden,a.c_sec_code,a.n_rate*100 as n_rate,a.n_ratio*100 as n_ratio,a.n_price_issue,a.d_start  ");
			buf.append("from T_P_SV_SEC_BASE_BAIL a ");
//			buf.append("left join (select c_da_code,c_sec_var_code from V_S_DA_SEC_VAR )b on a.c_sec_var_code = b.c_sec_var_code");
			buf.append(" where 1=1 ");
			if (valueFieldbuf.length() > 0)
			{
				buf.append(" AND ");
				buf.append(valueFieldbuf);
			}
			
			buf.append(" order by a.D_START ");
			
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

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 根据前台传送过来参数判断添加筛选条件 的SQL
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));


		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf.append(" A.C_SEC_CODE = ? AND ");
			}else if(fieldedName.equals("D_START")){
				valueFieldbuf.append(" A.D_START = ? AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/**
	 * 返回根据条件删除期货&期权保证金的SQL
	 * @return sql
	 */
	public String getDeleteBySecCodeSql(){
		String sql = " DELETE FROM T_P_SV_SEC_BASE_BAIL A WHERE A.C_SEC_CODE = ? ";
		return sql;
	}

}
