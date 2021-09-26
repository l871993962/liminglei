package com.yss.ams.base.information.modules.bi.account.dao;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;

/**
 * @ClassName FundAccUnifypaySqlBuilder
 * @Description 产品账户信息
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
public class FundAccSqlBuilder implements SQLBuilder {

	public static String getT_P_BI_FUND_ACC(){
		StringBuffer buf = new StringBuffer();
		buf.append(" ( SELECT C_IDEN,C_OPEN_ACC_NAME,C_CA_CODE,C_DC_CODE,C_OPEN_ADDR,C_OPEN_ACC_NO, " );
		buf.append("        C_ORG_CODE,C_USAGE,C_DESC,C_HOLDER,C_ASS_CODE, " );
		buf.append("        C_PAY_CODE,C_INTER_ORG_CODE,C_ACCOUNT_TYPE,C_PROVINCE, " );
		buf.append("        C_CITY,D_BEGIN,D_END,C_HAVEUSED,C_OPEN_MODE,C_BC_ORG_CODE,C_RUNNING_ACC, C_FLOW_ACC_NO," );
		buf.append("        C_BC_LINK_NO,C_CNX,C_SWIFT_CODE,C_BANK_ADDR,C_CREATE_BY,C_CREATE_TIME,C_CHECK_BY, " );
		buf.append("        C_CHECK_TIME,C_UPDATE_BY,C_UPDATE_TIME,N_CHECK_STATE,C_DISCARD_STATUS,C_PAYMENT_KEY, C_OPEN_JC, C_PORT_CODE as portCode" );
		buf.append(" FROM T_P_BI_FUND_ACC TACC ) ");
		return buf.toString();
	}
	
	public static String getR_P_BI_FUND_ACC(){
		StringBuffer buf = new StringBuffer();
		buf.append(" ( SELECT C_IDEN,C_OPEN_ACC_NAME,C_CA_CODE,C_DC_CODE,C_OPEN_ADDR,C_OPEN_ACC_NO, " );
		buf.append("        C_ORG_CODE,C_USAGE,C_DESC,C_HOLDER,C_ASS_CODE, " );
		buf.append("        C_PAY_CODE,C_INTER_ORG_CODE,C_ACCOUNT_TYPE,C_PROVINCE, " );
		buf.append("        C_CITY,D_BEGIN,D_END,C_HAVEUSED,C_OPEN_MODE,C_BC_ORG_CODE,C_RUNNING_ACC, C_FLOW_ACC_NO," );
		buf.append("        C_BC_LINK_NO,C_CNX,C_SWIFT_CODE,C_BANK_ADDR,C_CREATE_BY,C_CREATE_TIME,C_CHECK_BY, " );
		buf.append("        C_CHECK_TIME,C_UPDATE_BY,C_UPDATE_TIME,N_CHECK_STATE,C_DISCARD_STATUS,C_PAYMENT_KEY, C_DEL_TIME, C_PORT_CODE as portCode" );
		buf.append(" FROM R_P_BI_FUND_ACC_R RACC ) ");
		return buf.toString();
	}
	
	/*public String buildQueryAccSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT a.*,R.C_PORT_CODE AS C_PORT_CODE,C.C_DC_NAME AS C_DC_NAME " );
		buf.append(" FROM "+ getT_P_BI_FUND_ACC() + " a ");
		buf.append(" left join T_P_AB_PORT_ACC_RELA r  on a.c_iden = r.c_rela_code ");
		buf.append(" left join T_S_DC_CURY C on A.C_DC_CODE = C.C_DC_CODE ");
		buf.append(" WHERE r.C_PORT_CODE = ? ");
		buf.append(" AND (C_OPEN_ACC_NO = ? OR C_OPEN_ACC_NO = ?) ");
		
		return buf.toString();
	}*/
	
	public String buildQueryAccTwoSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT a.*,R.C_PORT_CODE AS C_PORT_CODE,C.C_DC_NAME AS C_DC_NAME " );
		buf.append(" FROM "+ getT_P_BI_FUND_ACC() + "a ");
		buf.append(" left join T_P_AB_PORT_ACC_RELA r  on a.c_iden = r.c_rela_code ");
		buf.append(" left join T_S_DC_CURY C on A.C_DC_CODE = C.C_DC_CODE ");
		buf.append(" WHERE r.C_PORT_CODE = ? and a.n_check_state = 1 ");
		buf.append(" AND EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  a.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE IN ('CPZH_TGH') ) ");
		//STORY #51553 一个组合存在多个托管账户，EXCLE导入时会出现多条指令，要求区分托管账户
		buf.append(" UNION  ");
		buf.append(" SELECT tc.*,tp.c_port_code as c_port_code,C.C_DC_NAME AS C_DC_NAME FROM  "+ getT_P_BI_FUND_ACC() + "tc ");
		buf.append(" LEFT JOIN T_P_AB_PORT_ACC_RELA tp ");
		buf.append(" ON tc.c_iden = tp.c_rela_code ");
		buf.append(" left join T_S_DC_CURY C on tc.C_DC_CODE = C.C_DC_CODE ");
		buf.append(" WHERE  tP.c_port_code = ? ");
		buf.append(" AND EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  tc.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE IN ('CPZH_TGH_SEC') ) ");
		buf.append(" AND TC.N_CHECK_STATE = 1 ");
		return buf.toString();
	}
	
	public String buildQueryAccListSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT a.*,R.C_PORT_CODE AS C_PORT_CODE,C.C_DC_NAME AS C_DC_NAME " );
		buf.append(" FROM "+ getT_P_BI_FUND_ACC() + "a ");
		buf.append(" left join T_P_AB_PORT_ACC_RELA r  on a.c_iden = r.c_rela_code ");
		buf.append(" left join T_S_DC_CURY C on A.C_DC_CODE = C.C_DC_CODE ");
		buf.append(" WHERE r.C_PORT_CODE IN (SELECT C_PORT_CODE FROM R_D_UNIFY_PARAM) and a.n_check_state = 1 ");
		buf.append(" AND EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  a.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE IN ('CPZH_TGH') ) ");
		//STORY #51553 一个组合存在多个托管账户，EXCLE导入时会出现多条指令，要求区分托管账户
		buf.append(" UNION  ");
		buf.append(" SELECT tc.*,tp.c_port_code as c_port_code,C.C_DC_NAME AS C_DC_NAME FROM  "+ getT_P_BI_FUND_ACC() + "tc ");
		buf.append(" LEFT JOIN T_P_AB_PORT_ACC_RELA tp ");
		buf.append(" ON tc.c_iden = tp.c_rela_code ");
		buf.append(" left join T_S_DC_CURY C on tc.C_DC_CODE = C.C_DC_CODE ");
		buf.append(" WHERE  tP.c_port_code  IN (SELECT C_PORT_CODE FROM R_D_UNIFY_PARAM) ");
		buf.append(" AND EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  tc.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE IN ('CPZH_TGH_SEC') ) ");
		buf.append(" AND TC.N_CHECK_STATE = 1 ");
		return buf.toString();
	}
	
	
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
		return dbnameresolver.getColumnName(FundAccColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(FundAccTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(FundAccTableName.fundAcc);
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from  ");
		buf.append(" (SELECT A.C_IDEN FROM ");
		if(paraNameList.contains("SHOW_WITHOUT_PORT")){
			buf.append("  (select a.C_IDEN from  ");
		}
		
		//包含删除日期用R表,条件：C_DEL_TIME_START，C_DEL_TIME_END
		//BUG #242815 【交银施罗德】_回收站_支付产品账户信息模块查询报错
		if(paraNameList.contains("C_DEL_TIME_START") && paraNameList.contains("C_DEL_TIME_END")){
			buf.append(" R_P_BI_FUND_ACC_R A ");
		}else{
			buf.append(" T_P_BI_FUND_ACC A ");
		}
		if(valueFieldbuf.toString().contains("C_PORT_CODE")){
			buf.append(" left join T_P_AB_PORT_ACC_RELA R on R.C_RELA_CODE = A.C_IDEN ");
		}
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		if(paraNameList.contains("SHOW_WITHOUT_PORT")){
			buf.append("  group by a.C_IDEN ) A where 'SHOW_WITHOUT_PORT'='SHOW_WITHOUT_PORT' ");
		}
		if(paraNameList.size()>1 && paraNameList.contains("SHOW_PUB_ACC")){
			buf.append(" UNION ");
			buf.append(" SELECT A.C_IDEN FROM ");
			buf.append(" T_P_BI_FUND_ACC A ");
			buf.append(" WHERE A.C_IDEN NOT IN (SELECT R.C_RELA_CODE FROM T_P_AB_PORT_ACC_RELA R) ");
		}else if(paraNameList.size()==1 && paraNameList.contains("SHOW_PUB_ACC")){
			buf.setLength(0);
			buf.append(" select COUNT(*) AS CNT from  ");
			buf.append(" (SELECT A.C_IDEN FROM ");
			buf.append(" T_P_BI_FUND_ACC A ");
			buf.append(" WHERE A.C_IDEN NOT IN (SELECT R.C_RELA_CODE FROM T_P_AB_PORT_ACC_RELA R) ");
		}
		buf.append(" ) ");
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		//STORY #76264 【产品账户信息】新增账户功能优化 
		if(paraNameList.size()>1 && paraNameList.contains("SHOW_PUB_ACC")){
			buf.append(" SELECT * FROM( ");
		}
		//BUG #157248 支付产品账户信息list界面增加大额支付号列，大额支付号取值逻辑错误
		//BUG #157433 清算指令界面报错
		if(paraNameList.contains("SHOW_WITHOUT_PORT")){
			buf.append(" select  ");
			buf.append("  C_IDEN,C_OPEN_ACC_NAME,C_CA_CODE,C_DC_CODE,C_OPEN_ADDR,C_OPEN_ACC_NO, " );
			buf.append("  C_ORG_CODE,C_USAGE,C_DESC,C_HOLDER,C_ASS_CODE, " );
			buf.append("  C_PAY_CODE,C_INTER_ORG_CODE,C_ACCOUNT_TYPE,C_PROVINCE, " );
			buf.append("  C_CITY,D_BEGIN,D_END,C_HAVEUSED,C_OPEN_MODE,C_BC_ORG_CODE,C_RUNNING_ACC, C_FLOW_ACC_NO," );
			buf.append("  C_BC_LINK_NO,C_CNX,C_SWIFT_CODE,C_BANK_ADDR,C_CREATE_BY,C_CREATE_TIME,C_CHECK_BY, " );
			buf.append("  C_CHECK_TIME,C_UPDATE_BY,C_UPDATE_TIME,N_CHECK_STATE,C_DC_NAME,C_DISCARD_STATUS,C_PAYMENT_KEY,C_OPEN_JC,1 AS N_KEY_ORDER " );
			buf.append(" from ( ");
		}
		buf.append(" select  ");
		if(valueFieldbuf.toString().contains("C_PORT_CODE") 
				|| valueFieldbuf.toString().contains("PORT_AND_PUP") 
				|| valueFieldbuf.toString().contains("PUBLIC_ACC")
				|| valueFieldbuf.toString().contains("NOT_PUBLIC_ACC")){
			buf.append(" R.C_PORT_CODE, ");
		}
//		buf.append(" A.C_PAY_CODE,A.c_iden,A.c_ca_code, ");
//		buf.append(" A.c_dc_code, A.c_open_addr,A.c_open_acc_no, ");
//		buf.append(" A.c_sys_code, A.c_usage,A.c_desc, ");
//		buf.append(" A.n_check_state, A.c_update_by,A.c_update_time, ");
//		buf.append(" A.c_check_by, A.c_check_time,A.c_haveused, ");
//		buf.append(" A.d_begin, A.d_end,A.c_org_code, ");
//		buf.append(" A.c_holder, A.c_ass_code,A.c_open_acc_name, C_INTER_ORG_CODE, ");
//		//BUG #174483 对接风控系统，导入定存数据时导入的账户信息，前台显示有误
//		buf.append(" A.c_account_type, B.c_bank_code,A.C_PROVINCE,A.C_CITY ");	
		buf.append(" C.C_DC_NAME,");	
		if(paraNameList.size()>1 && paraNameList.contains("SHOW_PUB_ACC") && !paraNameList.contains("SHOW_WITHOUT_PORT")){
			buf.append(" A.*,1 AS N_KEY_ORDER ");
		}else{
		buf.append(" A.* ");
		}
		//包含删除日期用R表,条件：C_DEL_TIME_START，C_DEL_TIME_END
		//BUG #242815 【交银施罗德】_回收站_支付产品账户信息模块查询报错
		if(paraNameList.contains("C_DEL_TIME_START") && paraNameList.contains("C_DEL_TIME_END")){
			buf.append(" FROM "+ getR_P_BI_FUND_ACC() + "a ");
		}else{
			buf.append(" FROM "+ getT_P_BI_FUND_ACC() + "a ");
		}
		
		//buf.append(" left join T_P_BI_ORG B on A.C_ORG_CODE = B.C_ORG_CODE ");
		buf.append(" left join T_S_DC_CURY C on A.C_DC_CODE = C.C_DC_CODE ");
		if(valueFieldbuf.toString().contains("C_PORT_CODE") 
				|| valueFieldbuf.toString().contains("PORT_AND_PUP") 
				|| valueFieldbuf.toString().contains("PUBLIC_ACC")
				|| valueFieldbuf.toString().contains("NOT_PUBLIC_ACC")){
			buf.append(" left join T_P_AB_PORT_ACC_RELA R on R.C_RELA_CODE = A.C_IDEN ");
		}
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.N_CHECK_STATE,A.C_UPDATE_TIME  DESC ");
		
		if(paraNameList.contains("SHOW_WITHOUT_PORT")){
			buf.append(" ) where 'SHOW_WITHOUT_PORT'='SHOW_WITHOUT_PORT' group by  ");
			buf.append("  C_IDEN,C_OPEN_ACC_NAME,C_CA_CODE,C_DC_CODE,C_OPEN_ADDR,C_OPEN_ACC_NO, " );
			buf.append("  C_ORG_CODE,C_USAGE,C_DESC,C_HOLDER,C_ASS_CODE, " );
			buf.append("  C_PAY_CODE,C_INTER_ORG_CODE,C_ACCOUNT_TYPE,C_PROVINCE, " );
			buf.append("  C_CITY,D_BEGIN,D_END,C_HAVEUSED,C_OPEN_MODE,C_BC_ORG_CODE,C_RUNNING_ACC, C_FLOW_ACC_NO," );
			buf.append("  C_BC_LINK_NO,C_CNX,C_SWIFT_CODE,C_BANK_ADDR,C_CREATE_BY,C_CREATE_TIME,C_CHECK_BY, " );
			buf.append("  C_CHECK_TIME,C_UPDATE_BY,C_UPDATE_TIME,N_CHECK_STATE,C_DC_NAME,C_DISCARD_STATUS,C_PAYMENT_KEY,C_OPEN_JC  " );
		}
		if(paraNameList.contains("SHOW_WITHOUT_PORT") && !paraNameList.contains("SHOW_PUB_ACC")){
			buf.append(" ORDER BY N_CHECK_STATE ");
		}
		//STORY #76264 【产品账户信息】新增账户功能优化 
		if(paraNameList.contains("SHOW_WITHOUT_PORT") && paraNameList.contains("SHOW_PUB_ACC")){
			buf.append(" UNION ");
			buf.append(" SELECT  ");
			buf.append("  A.C_IDEN,A.C_OPEN_ACC_NAME,A.C_CA_CODE,A.C_DC_CODE,A.C_OPEN_ADDR,A.C_OPEN_ACC_NO, ");
			buf.append("  A.C_ORG_CODE,A.C_USAGE,A.C_DESC,A.C_HOLDER,A.C_ASS_CODE, ");
			buf.append("  A.C_PAY_CODE,A.C_INTER_ORG_CODE,A.C_ACCOUNT_TYPE,A.C_PROVINCE, ");
			buf.append("  A.C_CITY,A.D_BEGIN,A.D_END,A.C_HAVEUSED,A.C_OPEN_MODE,A.C_BC_ORG_CODE,A.C_RUNNING_ACC,A.C_FLOW_ACC_NO, ");
			buf.append("  A.C_BC_LINK_NO,A.C_CNX,A.C_SWIFT_CODE,A.C_BANK_ADDR,A.C_CREATE_BY,A.C_CREATE_TIME,A.C_CHECK_BY, ");
			buf.append("  A.C_CHECK_TIME,A.C_UPDATE_BY,A.C_UPDATE_TIME,A.N_CHECK_STATE,C.C_DC_NAME,A.C_DISCARD_STATUS,A.C_PAYMENT_KEY, C_OPEN_JC,2 AS N_KEY_ORDER ");
			buf.append(" FROM T_P_BI_FUND_ACC A ");
			buf.append(" LEFT JOIN T_S_DC_CURY C ");
			buf.append(" ON A.C_DC_CODE = C.C_DC_CODE ");
			buf.append(" WHERE A.C_IDEN NOT IN (SELECT R.C_RELA_CODE FROM T_P_AB_PORT_ACC_RELA R)) ");
			buf.append(" ORDER BY N_CHECK_STATE,N_KEY_ORDER ");
		}else if(paraNameList.size()>1 && paraNameList.contains("SHOW_PUB_ACC")){
			StringBuffer buffer = new StringBuffer();
			buffer.append("  select * from ( ");
			buffer.append(buf);
			buffer.append(" ) ");
			buffer.append(" UNION ");
			buffer.append(" SELECT ");
			
			if (valueFieldbuf.toString().contains("C_PORT_CODE")
					|| valueFieldbuf.toString().contains("PORT_AND_PUP")
					|| valueFieldbuf.toString().contains("PUBLIC_ACC")
					|| valueFieldbuf.toString().contains("NOT_PUBLIC_ACC")) {
				buffer.append(" '' as C_PORT_CODE, ");
			}
			
			buffer.append(" C.C_DC_NAME,A.C_IDEN,A.C_OPEN_ACC_NAME,A.C_CA_CODE,A.C_DC_CODE,A.C_OPEN_ADDR,A.C_OPEN_ACC_NO,A.C_ORG_CODE,A.C_USAGE,A.C_DESC, ");
			buffer.append(" A.C_HOLDER,A.C_ASS_CODE,A.C_PAY_CODE,A.C_INTER_ORG_CODE,A.C_ACCOUNT_TYPE,A.C_PROVINCE,A.C_CITY,A.D_BEGIN,A.D_END,A.C_HAVEUSED, ");
			buffer.append(" A.C_OPEN_MODE,A.C_BC_ORG_CODE,A.C_RUNNING_ACC,A.C_FLOW_ACC_NO,A.C_BC_LINK_NO,A.C_CNX,A.C_SWIFT_CODE,A.C_BANK_ADDR,A.C_CREATE_BY, ");
			buffer.append(" A.C_CREATE_TIME,A.C_CHECK_BY,A.C_CHECK_TIME,A.C_UPDATE_BY,A.C_UPDATE_TIME,A.N_CHECK_STATE,A.C_DISCARD_STATUS,A.C_PAYMENT_KEY, C_OPEN_JC,2 AS N_KEY_ORDER ");
			buffer.append(" FROM T_P_BI_FUND_ACC A ");
			buffer.append(" LEFT JOIN T_S_DC_CURY C ");
			buffer.append(" ON A.C_DC_CODE = C.C_DC_CODE ");
			if (valueFieldbuf.toString().contains("C_PORT_CODE")
					|| valueFieldbuf.toString().contains("PORT_AND_PUP")
					|| valueFieldbuf.toString().contains("PUBLIC_ACC")
					|| valueFieldbuf.toString().contains("NOT_PUBLIC_ACC")) {
				buffer.append(" left join T_P_AB_PORT_ACC_RELA R on R.C_RELA_CODE = A.C_IDEN ");
			}
			buffer.append(" WHERE NOT EXISTS (SELECT 1 FROM T_P_AB_PORT_ACC_RELA R where R.C_RELA_CODE = A.C_IDEN)) ");
			buffer.append(" ORDER BY N_CHECK_STATE,N_KEY_ORDER ");
			buf = buffer;
		}else if(paraNameList.size()==1 && paraNameList.contains("SHOW_PUB_ACC")){
			StringBuffer buffer = new StringBuffer();

			buffer.append(" SELECT a.C_IDEN,a.C_OPEN_ACC_NAME,a.C_CA_CODE,a.C_DC_CODE,a.C_OPEN_ADDR,a.C_OPEN_ACC_NO,a.C_ORG_CODE,a.C_USAGE,a.C_DESC, ");
			buffer.append(" a.C_HOLDER,a.C_ASS_CODE,a.C_PAY_CODE,a.C_INTER_ORG_CODE,a.C_ACCOUNT_TYPE,a.C_PROVINCE,a.C_CITY,a.D_BEGIN,a.D_END,a.C_HAVEUSED, ");
			buffer.append(" a.C_OPEN_MODE,a.C_BC_ORG_CODE,a.C_RUNNING_ACC,a.C_FLOW_ACC_NO,a.C_BC_LINK_NO,C_CNX,a.C_SWIFT_CODE,a.C_BANK_ADDR,a.C_CREATE_BY, ");
			buffer.append(" a.C_CREATE_TIME,a.C_CHECK_BY,a.C_CHECK_TIME,a.C_UPDATE_BY,a.C_UPDATE_TIME,a.N_CHECK_STATE,a.C_DISCARD_STATUS,c.c_dc_name,A.C_PAYMENT_KEY, C_OPEN_JC ");
			buffer.append(" FROM T_P_BI_FUND_ACC A ");
			buffer.append(" LEFT JOIN T_S_DC_CURY C ");
			buffer.append(" ON A.C_DC_CODE = C.C_DC_CODE ");
			buffer.append(" WHERE  NOT EXISTS (SELECT 1 FROM T_P_AB_PORT_ACC_RELA R where R.C_RELA_CODE = A.C_IDEN) ");
			buffer.append(" ORDER BY N_CHECK_STATE ");
			buf=buffer;
		}
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf
						.append(" R.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_CA_CODE")) {
				valueFieldbuf.append(" A.C_CA_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_OPEN_ACC_NAME")) {
				valueFieldbuf.append(" A.C_OPEN_ACC_NAME LIKE ?  AND ");
			} else if (fieldedName.equals("C_DC_CODE")) {
				valueFieldbuf.append(" A.C_DC_CODE = ? AND ");
			} else if(fieldedName.equals("C_PORT_CODE")){
				valueFieldbuf.append(" R.C_PORT_CODE = ? AND ");
			} else if(fieldedName.equals("N_STATE")){
				valueFieldbuf.append(" A.N_CHECK_STATE = ? AND ");
			} else if(fieldedName.equals("C_OPEN_NAME")){
				valueFieldbuf.append(" A.C_OPEN_ACC_NAME = ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append(" a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("C_CA_CODE")) {
				valueFieldbuf.append(" a.C_CA_CODE = ? AND ");
			} else if(fieldedName.equals("C_ORG_CODE")){
				valueFieldbuf.append("a.C_ORG_CODE = ? AND ");
			} else if(fieldedName.equals("C_OPEN_ADDR")){
				valueFieldbuf.append(" A.C_OPEN_ADDR LIKE ? AND ");
			} else if(fieldedName.equals("C_ORG_CODE_HOLDER1")){
				valueFieldbuf.append("(a.C_ORG_CODE = ? OR a.C_HOLDER = ? ) AND ");
			} else if(fieldedName.equals("C_ORG_CODE_HOLDER2")){
				///不 加 条件 占位 
			} else if(fieldedName.equals("C_OPEN_ACC_NO")){
				valueFieldbuf.append("a.C_OPEN_ACC_NO = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_OPEN_ACC_NO")) {
				valueFieldbuf
				.append("a.C_OPEN_ACC_NO IN (select * from table(?)) AND ");
			} else if (fieldedName.equals("C_OPEN_ACC_NO_LIKE")){
				valueFieldbuf.append("a.C_OPEN_ACC_NO like ? AND ");
			} else if(fieldedName.equals("C_HOLDER")){
				valueFieldbuf.append("a.C_HOLDER = ? AND ");
			}else if (fieldedName.equals("N_DAYS2")){
				// /不 加 条件 占位
			} else if (fieldedName.equals("ARRAY_ACCOUNT_TYPE")){
				//STORY #32606 组合未关联导出模板时指令应显示完整付方账户信息
				valueFieldbuf.append("EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  A.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE IN (SELECT * FROM TABLE(?)) )	 AND ");
			}else if (fieldedName.equals("C_ACCOUNT_TYPE_TGH")){
				//BUG #206086 【富国基金】余额查询托管行正常回值-前台不能显示
				valueFieldbuf.append(" EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  A.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE IN ('CPZH_TGH','CPZH_TGH_SEC') ) AND 1= ? AND ");
			}else if(fieldedName.equals("NONE_PORT")){
				// 若不勾选组合则查询组合为空的
				//BUG #144042 招商基金-支付产品账户信息输入开户账号，点击查询报错。
				////BUG #185617 银行账户信息界面当不勾选组合点击查询是应该只查询出公共账户（没关联组合的账户）
				//valueFieldbuf.append("'NONE' = ? and trim(r.C_PORT_CODE) is null AND ");
				/*BUG #144321 招商基金-支付产品账户信息不选组合时不能查询已关联组合的账户信息*/
//				valueFieldbuf.append("  r.c_port_code in (select distinct c_port_code from t_s_user_rela "
//						+ "where n_check_state = 1 and c_user_code = '"+ContextFactory.getContext().getUserCode()+"')) AND ");
			}else if(fieldedName.equals("ARRAY_C_IDEN")){
				valueFieldbuf.append(" A.C_IDEN in (select * from table (?)) AND ");
			}else if (fieldedName.equals("PORT_AND_PUP")){
				valueFieldbuf.append(" (R.C_PORT_CODE = ? OR TRIM(R.C_PORT_CODE) IS NULL) AND ");
			}else if (fieldedName.equals("PUBLIC_ACC")){
				valueFieldbuf.append(" ( TRIM(R.C_PORT_CODE) IS NULL) AND 1= ? AND ");
			}else if (fieldedName.equals("NOT_PUBLIC_ACC")){
				////BUG #206086 【富国基金】余额查询托管行正常回值-前台不能显示
				valueFieldbuf.append(" ( TRIM(R.C_PORT_CODE) IS NOT NULL) AND 1= ? AND ");
			}else if (fieldedName.equals("C_ACCOUNT_TYPE_FALSE")) {
				// add by liyanjun 20160518 STORY #30595
				// 产品基本信息界面增加“资产代码”字段、“组合级别”下拉列表--默认值“单元层、组合层”，且可以选择过滤
				valueFieldbuf.append("a.C_IDEN NOT IN (SELECT A1.C_IDEN FROM T_P_BI_FUND_ACC A1 JOIN T_P_AB_PORT_ACC_RELA B1"
						+ " ON A1.C_IDEN=B1.C_RELA_CODE WHERE EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  A1.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE =? ))  AND ");
			} else if (fieldedName.equals("C_DISCARD_STATUS")){
				valueFieldbuf.append("a.C_DISCARD_STATUS = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_NODE_CODE_P_FALSE")){
				valueFieldbuf.append("a.C_IDEN not in (select c_iden_rela from T_P_AB_ACC_TREE_RELA "
						+ " where c_node_code_p in (select * from table(?))) AND ");
			} else if (fieldedName.equals("ARRAY_PORT_AND_PUP")){
			    valueFieldbuf.append(" (R.C_PORT_CODE IN (SELECT * FROM TABLE(?)) OR TRIM(R.C_PORT_CODE) IS NULL) AND ");
			} else if(fieldedName.equals("SHOW_PUB_ACC")){
				valueFieldbuf.append(" 'SHOW_PUB_ACC'= ? AND ");
			} else if(fieldedName.equalsIgnoreCase("ARRAY_C_IDEN")){
				valueFieldbuf.append(" A.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
			} else if(fieldedName.equalsIgnoreCase("ARRAY_NOT_IDEN")){
				valueFieldbuf.append(" A.C_IDEN NOT IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("SHOW_WITHOUT_PORT")){
				valueFieldbuf.append(" 'SHOW_WITHOUT_PORT' = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_ACCOUNT_TYPE")){
					valueFieldbuf.append(" A.C_ACCOUNT_TYPE = ? AND ");
			} else if(fieldedName.equals("PORT_ACC_CODE")){
				valueFieldbuf.append(" (TRIM(A.portCode) IS NULL OR A.portCode = ? ) AND ");
			} else if(fieldedName.equals("PORT_ACC_CODE1")){
				valueFieldbuf.append(" (R.C_PORT_CODE IS NULL OR R.C_PORT_CODE = ?) AND ");
			}
			
			
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(FundAccTableName.fundAcc);
	}
	
	private String commonSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("select DISTINCT A.C_IDEN,");
		buf.append(" A.C_OPEN_ACC_NAME,");
		buf.append(" A.C_CA_CODE,");
		buf.append(" A.C_DC_CODE,");
		buf.append(" A.C_OPEN_ADDR,");
		buf.append(" A.C_OPEN_ACC_NO,");
		buf.append(" A.C_ORG_CODE,");
		buf.append(" A.C_USAGE,");
		buf.append(" A.C_DESC,");
		buf.append(" A.C_HOLDER,");
		buf.append(" A.C_ASS_CODE,");
		buf.append(" A.C_PAY_CODE,");
		buf.append(" A.C_INTER_ORG_CODE,");
		buf.append(" A.C_ACCOUNT_TYPE,");
		buf.append(" A.C_PROVINCE,");
		buf.append(" A.C_CITY,");
		buf.append(" A.D_BEGIN,");
		buf.append(" A.D_END,");
		buf.append(" A.C_HAVEUSED,");
		buf.append(" A.C_OPEN_MODE,");
		buf.append(" A.C_BC_ORG_CODE,");
		buf.append(" A.C_RUNNING_ACC,A.C_FLOW_ACC_NO,");
		buf.append(" A.C_BC_LINK_NO,");
		buf.append(" A.C_CNX,");
		buf.append(" A.C_SWIFT_CODE,");
		buf.append(" A.C_BANK_ADDR,");
		buf.append(" A.C_CREATE_BY,");
		buf.append(" A.C_CREATE_TIME,");
		buf.append(" A.C_CHECK_BY,");
		buf.append(" A.C_CHECK_TIME,");
		buf.append(" A.C_UPDATE_BY,");
		buf.append(" A.C_UPDATE_TIME,");
		buf.append(" cury.C_DC_NAME, ");
		buf.append(" rela.C_PORT_CODE ,A.n_check_state,A.C_DISCARD_STATUS ");
		buf.append(" from T_P_BI_FUND_ACC A ");
		buf.append(" full join T_P_AB_PORT_ACC_RELA rela on A.c_iden = rela.c_rela_code ");
		buf.append(" left 	join T_S_DC_CURY cury on A.C_DC_CODE = cury.C_DC_CODE ");
		return buf.toString();
	}
	
	private String orderSql(){
		return " ORDER BY A.N_CHECK_STATE,A.C_UPDATE_TIME ";
	}
	
	public String getAllDataSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(cacheSql());
//		buf.append(orderSql());
		return buf.toString();
	}
	
	private String cacheSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("select A.C_IDEN,");
		buf.append(" A.C_OPEN_ACC_NAME,");
		buf.append(" A.C_CA_CODE,");
		buf.append(" A.C_DC_CODE,");
		buf.append(" A.C_OPEN_ADDR,");
		buf.append(" A.C_OPEN_ACC_NO,");
		buf.append(" A.C_ORG_CODE,");
		buf.append(" A.C_USAGE,");
		buf.append(" A.C_DESC,");
		buf.append(" A.C_HOLDER,");
		buf.append(" A.C_ASS_CODE,");
		buf.append(" A.C_PAY_CODE,");
		buf.append(" A.C_INTER_ORG_CODE,");
		buf.append(" A.C_ACCOUNT_TYPE,");
		buf.append(" A.C_PROVINCE,");
		buf.append(" A.C_CITY,");
		buf.append(" A.D_BEGIN,");
		buf.append(" A.D_END,");
		buf.append(" A.C_HAVEUSED,");
		buf.append(" A.C_OPEN_MODE,");
		buf.append(" A.C_BC_ORG_CODE,");
		buf.append(" A.C_RUNNING_ACC,A.C_FLOW_ACC_NO,");
		buf.append(" A.C_BC_LINK_NO,");
		buf.append(" A.C_CNX,");
		buf.append(" A.C_SWIFT_CODE,");
		buf.append(" A.C_BANK_ADDR,");
		buf.append(" A.C_CREATE_BY,");
		buf.append(" A.C_CREATE_TIME,");
		buf.append(" A.C_CHECK_BY,");
		buf.append(" A.C_CHECK_TIME,");
		buf.append(" A.C_UPDATE_BY,");
		buf.append(" A.C_UPDATE_TIME,");
		buf.append(" cury.C_DC_NAME, ");
		buf.append(" (SELECT TO_CLOB(F_CONCAT_ARRAY(CAST (collect( DISTINCT C_PORT_CODE ) AS VARTABLETYPE) ,'|')) FROM T_P_AB_PORT_ACC_RELA TP where TP.C_RELA_CODE = A.C_IDEN) as C_PORT_CODE, ");
		buf.append(" A.n_check_state,A.C_DISCARD_STATUS ");
		buf.append(" from T_P_BI_FUND_ACC A ");
//		buf.append(" left join t_cp_fundtype_rela ft on A.c_iden = ft.c_rela_code   ");
//		buf.append(" full join T_P_AB_PORT_ACC_RELA rela on A.c_iden = rela.c_rela_code ");
		buf.append(" left 	join T_S_DC_CURY cury on A.C_DC_CODE = cury.C_DC_CODE ");
		buf.append(" WHERE A.N_CHECK_STATE = 1  "); 
		return buf.toString();
	}
	
	public String getAllDataByPortSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(" WHERE rela.C_PORT_CODE = ? ");
		buf.append(orderSql());
		return buf.toString();
	}

	public String getDataByCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(" where A.C_OPEN_ACC_NO = ? ");
		buf.append(orderSql());
		return buf.toString();
	}
	
	public String getAllDataSqlByKeys() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(" where A.C_OPEN_ACC_NO = in (select * from table(?)) ");
		buf.append(orderSql());
		return buf.toString();
	}

	public String getDataListByTypes() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(" where A.C_OPEN_ACC_NO in (select * from table(?)) ");
		buf.append(orderSql());
		return buf.toString();
	}

	public String getDataListByOpenBank() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(" where A.C_ORG_CODE = ?  ");
		buf.append(orderSql());
		return buf.toString();
	}
	
	
	public String buildQueryAccByIdsSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select acc.C_DISCARD_STATUS, acc.C_IDEN,acc.C_OPEN_ACC_NAME,acc.C_CA_CODE,acc.C_DC_CODE, ");
		buf.append(" acc.C_OPEN_ADDR, acc.C_OPEN_ACC_NO,acc.C_ORG_CODE,acc.C_USAGE, ");
		buf.append(" acc.C_DESC,acc.C_HOLDER,acc.C_ASS_CODE,acc.C_PAY_CODE, ");
		buf.append(" acc.C_INTER_ORG_CODE,acc.C_ACCOUNT_TYPE,acc.C_PROVINCE,acc.C_CITY,acc.D_BEGIN, ");
		buf.append(" acc.D_END,acc.C_HAVEUSED,acc.C_OPEN_MODE, acc.C_BC_ORG_CODE, acc.C_RUNNING_ACC, ACC.C_FLOW_ACC_NO,");
		buf.append(" acc.C_BC_LINK_NO,acc.C_CNX,acc.C_SWIFT_CODE,acc.C_BANK_ADDR,acc.C_CREATE_BY, ");
		buf.append(" acc.C_CREATE_TIME,  acc.C_CHECK_BY, acc.C_CHECK_TIME, acc.C_UPDATE_BY, acc.C_UPDATE_TIME, cury.C_DC_NAME,  rela.C_PORT_CODE, acc.n_check_state ");
		buf.append(" from T_P_BI_FUND_ACC acc left join T_P_AB_PORT_ACC_RELA rela on acc.c_iden = rela.c_rela_code ");
		buf.append(" left join T_S_DC_CURY cury on acc.C_DC_CODE = cury.C_DC_CODE    ");
		buf.append(" WHERE acc.C_IDEN IN (SELECT * FROM TABLE(?))");
		return buf.toString();
	}
	
	public String buildQueryAccByAssCodeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT a.*,R.C_PORT_CODE as C_PORT_CODE ");
		buf.append(" from " + getT_P_BI_FUND_ACC() + " a ");
		buf.append(" left join T_P_AB_PORT_ACC_RELA R on R.C_RELA_CODE = A.C_IDEN ");
		buf.append(" WHERE TRIM(a.C_ASS_CODE) = ? ");
		return buf.toString();
	}
	
	public String buildUpdateAccbyAccNoSql() {
		StringBuffer buf = new StringBuffer();
	////STORY #31937 嘉实基金-待发送界面同时发送32笔指令的时候系统报错
		buf.append(" UPDATE T_P_BI_FUND_ACC SET C_HAVEUSED = '1'  WHERE C_OPEN_ACC_NO in (select * from table(?)) and (C_HAVEUSED = '0' OR TRIM(C_HAVEUSED) IS NULL) ");
		return buf.toString();
	}

	public String getDataListByAccTypeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT level, c.*                              ");
	    buf.append(" from (select 	nvl(c_dac_name_p,'[root]')  as C_ACCOUNT_TYPE,  ");
	    buf.append("             	'' as c_open_acc_name,     ");
	    buf.append("                c_dac_name as c_open_acc_no,    ");
	    buf.append("                ' ' as C_CHECK_TIME,            ");
	    buf.append("                1 as N_CHECK_STATE,             ");
	    buf.append("                '' as c_iden,                   ");
	    buf.append("                '' as c_port_code,              ");
	    buf.append("                '' as c_ca_code,                ");
	    buf.append("                '' as c_dc_code,                ");
	    buf.append("                '' as c_open_addr,              ");
	    buf.append("                '' as c_sys_code,               ");
	    buf.append("                '' as c_usage,                  ");
	    buf.append("                '' as c_desc,                   ");
	    buf.append("                date'2014-12-09' as d_begin,    ");
	    buf.append("                date'9998-12-31' as d_end,      ");
	    buf.append("                '' as c_update_by,              ");
	    buf.append("                '' as c_update_time,            ");
	    buf.append("                '' as c_check_by,               ");
	    buf.append("                '' as c_org_code,               ");
	    buf.append("                '' as c_pay_code,               ");
	    buf.append("                '' as c_inter_org_code,         ");
	    buf.append("                '' as c_holder,                 ");
	    buf.append("                '' as c_ass_code,               ");
	    buf.append("                '' as c_haveused,               ");
	    buf.append("                '' as c_province,               ");
	    buf.append("                '' as c_city,                   ");
        buf.append("                '' as c_port_name,              ");
        buf.append("                '' as C_DISCARD_STATUS               ");
	    buf.append("           from (select distinct a.c_dac_code,a.c_dac_code_p,a.c_dac_name,a.n_order,b.c_dac_name as c_dac_name_p from( ");
	    buf.append("           select * from t_s_dac_type           ");
	    buf.append("           start with C_dac_code IN (SELECT * FROM TABLE(?)) ");
	    buf.append("           connect by nocycle prior C_dac_code_p =  C_dac_code  "); 
	    buf.append("           order by level asc) a left join t_s_dac_type b on a.c_dac_code_p=b.c_dac_code ) c               ");
	    buf.append("         union all                              ");
	    buf.append("         SELECT                                 ");
	    buf.append("                b.c_dac_name AS C_ACCOUNT_TYPE, "); 
	    buf.append("                a.c_open_acc_name,              ");
	    buf.append("                trim(a.c_open_acc_no) as c_open_acc_no,  ");
	    buf.append("                ' ' as C_CHECK_TIME,            ");
	    buf.append("                1 as N_CHECK_STATE,             ");
	    buf.append("                a.c_iden,                       ");
	    buf.append("                nvl(a.c_port_code,c.c_port_code) as c_port_code,");
	    buf.append("                a.c_ca_code,                    ");
	    buf.append("                a.c_dc_code,                    ");
	    buf.append("                a.c_open_addr,                  ");
	    buf.append("                a.c_sys_code,                   ");
	    buf.append("                a.c_usage,                      ");
	    buf.append("                a.c_desc,                       ");
	    buf.append("                a.d_begin,                      ");
	    buf.append("                a.d_end,                        ");
	    buf.append("                a.c_update_by,                  ");
	    buf.append("                a.c_update_time,                ");
	    buf.append("                a.c_check_by,                   ");
	    buf.append("                a.c_org_code,                   ");
	    buf.append("                a.c_pay_code as c_pay_code,     ");
	    buf.append("                a.c_inter_org_code,             ");
	    buf.append("                a.c_holder,                     ");
	    buf.append("                a.c_ass_code,                   ");
	    buf.append("                a.c_haveused,                   ");
	    buf.append("                a.c_province,                   ");
	    buf.append("                a.c_city,                       ");
        buf.append("                d.c_port_name as c_port_name,");
        buf.append("                a.C_DISCARD_STATUS as C_DISCARD_STATUS");
	    buf.append("           FROM T_P_BI_FUND_ACC a      LEFT join t_cp_fundtype_rela ft ON a.c_iden = ft.c_rela_code            ");
	    buf.append("          left join t_s_dac_type b on ft.C_ACCOUNT_TYPE= b.c_dac_code ");
	    buf.append(" left join t_p_ab_port_acc_rela c on a.c_iden = c.C_RELA_CODE  ");
	    buf.append("           left join  T_P_AB_PORT d on c.c_port_code = d.c_port_code ");
	    buf.append("          WHERE ft.C_ACCOUNT_TYPE IN (SELECT * FROM TABLE(?)) ");
	    buf.append("            and a.n_check_state = 1         ");
	    buf.append("   GROUP BY  b.c_dac_name,a.c_open_acc_name,a.c_open_acc_no,' ',1,a.c_iden,nvl(a.c_port_code,c.c_port_code),a.c_ca_code,a.c_dc_code,a.c_open_addr,a.c_sys_code,a.c_usage,  ");
	    buf.append(" a.c_desc,a.d_begin,a.d_end,a.c_update_by,a.c_update_time,a.c_check_by,a.c_org_code,a.c_pay_code,a.c_inter_org_code,              ");
	    buf.append(" a.c_holder,a.c_ass_code,a.c_haveused,a.c_province,a.c_city,d.c_port_name,a.C_DISCARD_STATUS  ");
	    buf.append(" ) c  start with c.C_ACCOUNT_TYPE = '[root]'         ");
	    buf.append(" connect by nocycle prior  c.c_open_acc_no = c.C_ACCOUNT_TYPE ");   
	    buf.append(" order by level asc, c_open_acc_no              ");
		
		return buf.toString();
	}
	
	public String getDataListByAccTypeSql2() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT level, c.* ");
		buf.append("  from (select '[root]' as C_ACCOUNT_TYPE, ");
		buf.append("               c.c_dac_name as c_open_acc_name,");
		buf.append("               c_dac_name as c_open_acc_no, ");
		buf.append("               ' ' as C_CHECK_TIME, ");
		buf.append("               1 as N_CHECK_STATE, ");
		buf.append("               '' as c_iden,");        
		buf.append("               '' as c_port_code,");   
		buf.append("               '' as c_ca_code, ");    
		buf.append("               '' as c_dc_code, ");    
		buf.append("               '' as c_open_addr, ");  
		buf.append("               '' as c_sys_code,  ");  
		buf.append("               '' as c_usage, ");      
		buf.append("               '' as c_desc, ");
		buf.append("               date'2014-12-09' as d_begin, ");
		buf.append("               date'9998-12-31' as d_end, ");
		buf.append("               '' as c_update_by, ");  
		buf.append("               '' as c_update_time, ");
		buf.append("               '' as c_check_by, ");   
		buf.append("               '' as c_org_code, ");   
		buf.append("               '' as c_inter_org_code, ");   
		buf.append("               '' as c_holder, ");     
		buf.append("               '' as c_ass_code, ");   
		buf.append("               '' as c_haveused, ");  
		buf.append("               '' as C_DISCARD_STATUS ");     
		buf.append("          from t_s_dac_type c   ");
		buf.append("         where c.c_dac_code IN (SELECT * FROM TABLE(?)) ");
		buf.append("        union all ");
		buf.append("         	   SELECT  to_char(PKG_FUN_F_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST(COLLECT(NVL(c.c_dac_name,'')) AS VARTABLETYPE),'|')) AS  C_ACCOUNT_TYPE, ");
		buf.append("               c_open_acc_name,");
		buf.append("               trim(c_open_acc_no), ");
		buf.append("               ' ' as C_CHECK_TIME, ");
		buf.append("               1 as N_CHECK_STATE, ");
		buf.append("               acc.c_iden,");
		buf.append("               R.c_port_code AS C_PORT_CODE,");
		buf.append("               c_ca_code, ");
		buf.append("               c_dc_code, ");
		buf.append("               c_open_addr, ");
		buf.append("               c_sys_code,  ");
		buf.append("               c_usage, ");
		buf.append("               acc.c_desc, ");
		buf.append("               d_begin, ");
		buf.append("               d_end, ");
		buf.append("               acc.c_update_by, ");
		buf.append("               acc.c_update_time, ");
		buf.append("               c_check_by, ");
		buf.append("               c_org_code, ");
		// STORY #34439_招商基金_划款指令模板设计
		// T_P_BI_FUND_ACC增加中间行字段
		buf.append("               c_inter_org_code, ");
		buf.append("               c_holder, ");
		buf.append("               c_ass_code, ");
		buf.append("               c_haveused, ");
		buf.append("               C_DISCARD_STATUS ");
		buf.append("          FROM T_P_BI_FUND_ACC acc LEFT JOIN T_CP_FUNDTYPE_RELA FT ON ACC.C_IDEN = FT.C_RELA_CODE  left join T_S_dac_type C ON ft.C_ACCOUNT_TYPE = C.C_dac_CODE");
		buf.append("  LEFT JOIN t_p_ab_port_acc_rela r ON acc.c_iden = r.c_rela_code   ");
		buf.append("         WHERE FT.C_ACCOUNT_TYPE IN (SELECT * FROM TABLE(?))  and n_check_state = 1 ");
		buf.append("       GROUP BY  c_open_acc_name,trim(c_open_acc_no),' ',1,acc.c_iden,R.c_port_code,c_ca_code, c_dc_code,c_open_addr,c_sys_code,c_usage,acc.c_desc,      ");
		buf.append("  d_begin,d_end,acc.c_update_by, ACC.c_update_time,c_check_by,c_org_code,c_inter_org_code,c_holder,c_ass_code,c_haveused,C_DISCARD_STATUS  ");
		buf.append(" ) c start with c.C_ACCOUNT_TYPE = '[root]' ");
		buf.append("connect by nocycle prior  c.c_open_acc_no = c.C_ACCOUNT_TYPE ");  
		buf.append("order by level asc, c_open_acc_no ");  
		return buf.toString();
	}
	
	
	
	public String getFundAcc(){
		StringBuffer buf = new StringBuffer();
		buf.append(" select * from T_P_AB_PORT_ACC_RELA where C_RELA_CODE in (select * from table(?)) ");
		return buf.toString();
	}
	
	public String getFundAccPort(){
		StringBuffer buf = new StringBuffer();
		buf.append(" select * from T_P_AB_PORT_ACC_RELA where C_PORT_CODE =? ");
		return buf.toString();
	}

	public String getUniqueAccountTypeByPortsSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(" where A.N_CHECK_STATE=1 AND EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  a.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE =? )  ");
		buf.append(" AND (RELA.C_PORT_CODE IN (SELECT * FROM TABLE(?)) OR trim(RELA.C_PORT_CODE) is null) ");
		buf.append(orderSql());
		return buf.toString();
	}
	
	public String getAccNameAndCaCode() {
		StringBuffer buf = new StringBuffer();
		buf.append("select * ");
		buf.append(" from T_P_BI_FUND_ACC ");
		buf.append(" where ");
		buf.append(" n_check_state = 1");
		return buf.toString();
	}

    /**
	  * STORY #91838 【汇添富基金】账户插入restful接口
	  * @author zmk
	  * @date 2020-09-24
    * @return
    */
	public String getCashAccByRunningAccs() {
		StringBuffer buf = new StringBuffer();
		buf.append("select * ");
		buf.append(" from T_P_BI_FUND_ACC ");
		buf.append(" where ");
		buf.append(" C_OPEN_ACC_NO = ?  AND C_OPEN_ADDR = ? ");
		buf.append(" AND C_OPEN_ACC_NAME = ?  AND C_DC_CODE = ? ");
		return buf.toString();
	}
	
}
