package com.yss.uco.elecreco.er.generate.split.dao;

import com.yss.framework.api.util.StringUtil;

public class GenerateSplitSqlBuilder {

	public String getErSplitRelaSql(String splitCode) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.C_IDEN,A.C_PORT_CODE,A.C_TGH_CODE,A.C_SPLIT_CODE,A.D_START_DATE,A.D_END_DATE,A.N_CHECK_STATE,A.C_UPDATE_BY,A.C_UPDATE_TIME,A.C_CHECK_BY,A.C_CHECK_TIME FROM T_D_ER_SPLIT_RELA A ");
		sb.append(" where A.C_PORT_CODE = ? ");
		sb.append(" and A.D_START_DATE <= to_DATE(?,'yyyy-MM-dd') ");
		sb.append(" and A.D_END_DATE >= to_DATE(?,'yyyy-MM-dd') ");
		sb.append(" and A.C_TGH_CODE = ? ");
		sb.append(" and N_CHECK_STATE >= 1 ");
		if(StringUtil.IsNullOrEmptyT(splitCode))
		{
			sb.append(" and trim(A.C_SPLIT_CODE) is null ");
		}else
		{
			sb.append(" and A.C_SPLIT_CODE = ? ");
		}
		return sb.toString();
	}

	public String getErSplitRelasSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.C_IDEN,A.C_PORT_CODE,A.C_TGH_CODE,A.C_SPLIT_CODE,A.D_START_DATE,A.D_END_DATE,A.N_CHECK_STATE,A.C_UPDATE_BY,A.C_UPDATE_TIME,A.C_CHECK_BY,A.C_CHECK_TIME FROM T_D_ER_SPLIT_RELA A ");
		sb.append(" where A.C_PORT_CODE = ? ");
		sb.append(" and A.D_START_DATE <= to_DATE(?,'yyyy-MM-dd') ");
		sb.append(" and A.D_END_DATE >= to_DATE(?,'yyyy-MM-dd') ");
		sb.append(" and N_CHECK_STATE >= 1 ");
		return sb.toString();
	}

	public String getUnSplitRuleDetailKmSql() {
		StringBuilder builder = new StringBuilder();
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
		builder.append("              where a.n_check_state = 1 and a.c_km_code not like '%>'and a.n_order = 0 and a.C_DV_KM_CLS != 'KC_SY' \n"); 
		builder.append("             union all \n"); 
		builder.append("             select distinct C_KM_CODE, C_KM_NAME, C_KM_CODE_P, \n"); 
		builder.append("                              N_DETAIL, C_DV_KM_CLS, C_DV_JD_WAY \n"); 
		builder.append("               from (select a.C_KM_CODE, a.C_KM_NAME,a.C_KM_CODE_T, \n"); 
		builder.append("                             b.C_KM_CODE_P, b.N_DETAIL, b.C_DV_KM_CLS, b.C_DV_JD_WAY \n"); 
		builder.append("                        from T_D_Ai_Stock a \n"); 
		builder.append("                        join T_F_SC_KM b ON a.C_KM_CODE_T = b.C_KM_CODE \n"); 
		builder.append("                       where a.C_Port_Code = ? \n"); 
		builder.append("                         and a.D_Stock = To_DATE(?, 'yyyy-MM-dd') \n"); 
		builder.append("                         and b.C_KM_CODE like '%>' and b.n_order = 0 and b.C_DV_KM_CLS != 'KC_SY' and a.N_ORIG_MONEY != 0  \n"); 
		builder.append("                      union \n"); 
		builder.append("                      select a.C_KM_CODE,a.C_KM_NAME, a.C_KM_CODE_T, \n"); 
		builder.append("                             b.C_KM_CODE_P,b.N_DETAIL, b.C_DV_KM_CLS, b.C_DV_JD_WAY \n"); 
		builder.append("                        from T_D_AI_ACT_VAL a \n"); 
		builder.append("                        join T_F_SC_KM b ON a.C_KM_CODE_T = b.C_KM_CODE \n"); 
		builder.append("                       where a.C_Port_Code = ? \n"); 
		builder.append("                         and a.D_CHK_ACC = To_DATE(?, 'yyyy-MM-dd') \n"); 
		builder.append("                         and b.C_KM_CODE like '%>' and b.n_order = 0 and b.C_DV_KM_CLS != 'KC_SY' )\n "); 
		builder.append("           ) \n");

		return builder.toString();
	}

	public String getHaveRuleKmSqlByPortCode() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.C_IDEN,A.C_IDEN_RELA,A.C_KM_CODE,A.C_KM_NAME,A.N_CHECK_STATE,A.C_UPDATE_BY,A.C_UPDATE_TIME,A.C_CHECK_BY,A.C_CHECK_TIME FROM T_D_ER_SPLIT_RULE A ");
		sb.append(" where A.C_IDEN_RELA in ( ");
		sb.append(" select C_IDEN from T_D_ER_SPLIT_RELA where C_PORT_CODE = ? and N_CHECK_STATE >= 1 ");
		sb.append(" ) ");
		return sb.toString();
	}

	public String getSplitRuleDetailKmBySplitRelaSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.C_IDEN,A.C_IDEN_RELA,A.C_KM_CODE,A.C_KM_NAME,A.N_CHECK_STATE,A.C_UPDATE_BY,A.C_UPDATE_TIME,A.C_CHECK_BY,A.C_CHECK_TIME FROM T_D_ER_SPLIT_RULE A ");
		sb.append(" where A.C_IDEN_RELA = ? ");
		return sb.toString();
	}

}
