package com.yss.uco.elecreco.er.reverse.map.kmrela.dao;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
public class KmRelaRecordSqlBuilder implements SQLBuilder  {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInnerKMBSql(HashMap<String, Object> paraMap){
		StringBuilder builder = new StringBuilder();
		
		builder.append("select  A.* from ( \n");

		builder.append("select  C_KM_CODE, C_KM_NAME, C_KM_CODE_P, \n");
		builder.append("        to_number(PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_CODE)) as C_KM_LEVEL, \n"); 
		builder.append("        N_DETAIL, C_DV_KM_CLS, C_DV_JD_WAY \n"); 
		builder.append("       from (select A.C_KM_CODE, A.C_KM_NAME, A.C_KM_CODE_P, \n"); 
		builder.append("                     A.N_DETAIL, A.C_DV_KM_CLS, A.C_DV_JD_WAY \n"); 
		builder.append("               from T_F_SC_KM A \n"); 
		builder.append("               join (select C_PLAN_CODE \n"); 
		builder.append("                      from T_E_EXEC_PLAN_RELA \n"); 
		builder.append("                     where C_PLAN_Type = 'AO_LEVEL' \n"); 
		builder.append("                       and n_check_state = 1 and C_PORT_CODE = ? \n"); 
		builder.append("                       and d_begin <= to_date(?, 'yyyy-MM-dd') \n"); 
		builder.append("                       and d_end >= to_date(?, 'yyyy-MM-dd')) b \n"); 
		builder.append("                 on a.c_plan_code = b.c_plan_code \n"); 
		builder.append("              where a.n_check_state = 1 and a.c_km_code not like '%>'and a.n_order = 0 \n"); 
		builder.append("             union all \n"); 
		builder.append("             select distinct C_KM_CODE, C_KM_NAME, C_KM_CODE_P, \n"); 
		builder.append("                              N_DETAIL, C_DV_KM_CLS, C_DV_JD_WAY \n"); 
		builder.append("               from (select a.C_KM_CODE, a.C_KM_NAME,a.C_KM_CODE_T, \n"); 
		builder.append("                             b.C_KM_CODE_P, b.N_DETAIL, b.C_DV_KM_CLS, b.C_DV_JD_WAY \n"); 
		builder.append("                        from T_D_Ai_Stock a \n"); 
		builder.append("                        join T_F_SC_KM b ON a.C_KM_CODE_T = b.C_KM_CODE \n"); 
		builder.append("                       where a.C_Port_Code = ? \n"); 
		builder.append("                         and a.D_Stock = To_DATE(?, 'yyyy-MM-dd') \n"); 
		builder.append("                         and b.C_KM_CODE like '%>' and b.n_order = 0 \n"); 
		builder.append("                      union \n"); 
		builder.append("                      select a.C_KM_CODE,a.C_KM_NAME, a.C_KM_CODE_T, \n"); 
		builder.append("                             b.C_KM_CODE_P,b.N_DETAIL, b.C_DV_KM_CLS, b.C_DV_JD_WAY \n"); 
		builder.append("                        from T_D_AI_ACT_VAL a \n"); 
		builder.append("                        join T_F_SC_KM b ON a.C_KM_CODE_T = b.C_KM_CODE \n"); 
		builder.append("                       where a.C_Port_Code = ? \n"); 
		builder.append("                         and a.D_CHK_ACC = To_DATE(?, 'yyyy-MM-dd') \n"); 
		builder.append("                         and b.C_KM_CODE like '%>' and b.n_order = 0 )\n "); 
		builder.append("           ) \n");

		builder.append("           ) A where C_DV_KM_CLS = ? \n");
		if(Boolean.parseBoolean((String) paraMap.get("IS_FLITER")))
		{
			builder.append("           and not exists ( select 1 from T_D_ER_REVE_KM_MAP R,T_D_ER_REVE_KM_RELA L where R.C_KM_CODE = A.C_KM_CODE and R.C_DV_KM_SCOPE='REVE_KMFW_INNER' and R.C_IDEN_RELA = L.C_IDEN and L.C_DV_MAP_SCOPE = ? ) \n");
		}
		
		return builder.toString();
	}
	
	public String getOutKMBSql(HashMap<String, Object> paraMap){
		StringBuilder builder = new StringBuilder();
		
		builder.append(" select  A.* from T_D_OD_KM_OUT A \n");
		//BUG229428科目映射管理界面新增数据是可以取到资托资产映射界面反审核数据
		builder.append(" where A.C_ASS_CODE in ( select C_PORT_CODE_OUT from T_D_ER_REVE_ASS_MAP where N_CHECK_STATE = 1 and C_PORT_CODE = ? )  \n");
		builder.append(" and A.C_DV_KM_CLS = ? \n");
		if(Boolean.parseBoolean((String) paraMap.get("IS_FLITER")))
		{
			builder.append("           and not exists ( select 1 from T_D_ER_REVE_KM_MAP R,T_D_ER_REVE_KM_RELA L where R.C_KM_CODE = A.C_KM_CODE and R.C_DV_KM_SCOPE='REVE_KMFW_OUT' and R.C_IDEN_RELA = L.C_IDEN and L.C_DV_MAP_SCOPE = ? ) \n");
		}
		
		return builder.toString();
	}
	

}