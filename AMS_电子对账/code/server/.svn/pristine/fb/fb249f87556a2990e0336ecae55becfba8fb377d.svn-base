package com.yss.uco.elecreco.er.spilt.rule.dao;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class ErSplitRuleSqlBuilder implements SQLBuilder  {

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,String s) {
		return dbnameresolver.getColumnName(ErSplitRuleColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErSplitRuleTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErSplitRuleTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErSplitRuleTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_SPLIT_RULE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_SPLIT_RULE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.N_CHECK_STATE DESC  ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_KM_CODE")) {//
				valueFieldbuf.append(" A.C_KM_CODE LIKE ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_IDEN_RELA")) {//
				valueFieldbuf.append(" A.C_IDEN_RELA = ? AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getSplitRulesBySplitRelaSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.* FROM T_D_ER_SPLIT_RULE A ");
		sb.append(" where A.C_IDEN_RELA = ? ");
		return sb.toString();
	}
	
	public String getSplitRulesSqlByPortCode() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.* FROM T_D_ER_SPLIT_RULE A ");
		sb.append(" where A.C_IDEN_RELA in ( ");
		sb.append(" select C_IDEN from T_D_ER_SPLIT_RELA where C_PORT_CODE = ? ");
		sb.append(" ) ");
		return sb.toString();
	}

	public String getKmInfoSqlByPortWithDate() {
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
		builder.append("              where a.n_check_state = 1 and a.c_km_code not like '%>'and a.n_order = 0 \n"); 
		//builder.append("           and not exists ( select 1 from T_D_ER_SPLIT_RULE R where R.C_KM_CODE = a.C_KM_CODE ) \n");
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
		//builder.append("                         and not exists ( select 1 from T_D_ER_SPLIT_RULE R where R.C_KM_CODE = b.C_KM_CODE ) \n");
		builder.append("                      union \n"); 
		builder.append("                      select a.C_KM_CODE,a.C_KM_NAME, a.C_KM_CODE_T, \n"); 
		builder.append("                             b.C_KM_CODE_P,b.N_DETAIL, b.C_DV_KM_CLS, b.C_DV_JD_WAY \n"); 
		builder.append("                        from T_D_AI_ACT_VAL a \n"); 
		builder.append("                        join T_F_SC_KM b ON a.C_KM_CODE_T = b.C_KM_CODE \n"); 
		builder.append("                       where a.C_Port_Code = ? \n"); 
		builder.append("                         and a.D_CHK_ACC = To_DATE(?, 'yyyy-MM-dd') \n"); 
		builder.append("                         and b.C_KM_CODE like '%>' and b.n_order = 0 )\n "); 
		//builder.append("           				 and not exists ( select 1 from T_D_ER_SPLIT_RULE R where R.C_KM_CODE = b.C_KM_CODE ) \n");
		builder.append("           ) \n");
		return builder.toString();
	}

}