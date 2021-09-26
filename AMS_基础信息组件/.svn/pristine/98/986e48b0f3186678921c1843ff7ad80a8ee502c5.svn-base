package com.yss.ams.base.information.modules.sys.daeelem.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class DaeElemSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(DaeElemColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(DaeElemTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(DaeElemTableName.userInfo);

	}

	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		// TODO Auto-generated method stub
		return "select count(a.*) as CNT  from T_S_DAE_ELEM a";
	}

	public String getQueryConditionSql(List<String> arg0) throws Exception {

		return "SELECT '' as C_IDEN,C_DAE_CODE,C_DAE_NAME,C_DS_TYPE,C_DAI_FIELD from T_S_DAE_ELEM";
	}

	public String getDaeNameByCodeSql() {
		
		return " select * from T_S_DAE_ELEM a where a.c_dae_code = ? ";
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DaeElemTableName.userInfo);
	}


	/**
	 * 根据核算项目数量 进行拼接sql
	 * 收支结转业务存在多个核算项目，核算元素进行过滤时候取几个核算项目下的核算元素交集 By Jinghehe
	 * @param daiCount
	 * @return
	 */
	public String getDaeCodesByConditionSql(int daiCount) {
		StringBuffer buf = new StringBuffer();
		StringBuffer bufSql = new StringBuffer();
		buf.append("select dae_code from ( ");
		for(int i =1; i < 9;i++)
		{
			buf.append(" select c_dai_code, c_dae_code, c_dae_code_sub, C_LOAD_MODE, c_dae_code"+i+" as dae_code ");
			buf.append(" from t_s_dae_elem_detail ");
			if(8 != i)
			{
				buf.append(" union all ");	
			}	
		}
		buf.append(") where c_dae_code = ? ");
		buf.append(" and c_dae_code_sub = ? ");
		buf.append(" and c_dai_code = ? ");
		buf.append(" AND C_LOAD_MODE = ? "); //add by Yuntao Lau 2015.12.01 STORY #26998
		buf.append(" and dae_code <> ' ' ");
		String sql = buf.toString();
		bufSql.append(sql);
		for(int i = 1; i < daiCount;i++)
		{
			if(i != daiCount)
			{
				bufSql.append(" intersect ");
				bufSql.append(sql);
			}
		}
		
		return bufSql.toString();
	}
	
	/**
	 * 获得证券品种父类code
	 * @return
	 */
	public String getParentCodeByCodeSql() {
		return "select decode(C_DA_CODE_P,'[root]',' ',C_DA_CODE_P) as C_DA_CODE_P from V_S_DA_SEC_VAR where C_SEC_VAR_CODE = ? ";
	}
	
	
	/**
	 * 获得证券品种子类code
	 * @return
	 */
	public String getChildCodeByCodeSql() {
		return "select C_DA_CODE from V_S_DA_SEC_VAR where C_DA_CODE_P = ? ";
	}
}
