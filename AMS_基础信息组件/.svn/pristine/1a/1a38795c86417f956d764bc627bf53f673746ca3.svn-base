package com.yss.ams.base.information.modules.bi.accountTreeB.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class AccountTreeBSqlBuilder implements SQLBuilder  {

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
		return dbNameResolver.getTableName(AccountTreeBTableName.accountTreeB);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(AccountTreeBColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(AccountTreeBTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return null;
	}
	
	private static String getAccTreeRela(){
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(" select a.c_iden, ");
		buf.append("\r\n").append("        a.c_node_code_p, ");
		buf.append("\r\n").append("        a.c_iden as c_node_code, ");
		buf.append("\r\n").append("        a.c_iden_rela, ");
		buf.append("\r\n").append("        a.n_check_state, ");
		buf.append("\r\n").append("        a.c_check_time, ");
		buf.append("\r\n").append("        a.c_check_by, ");
		buf.append("\r\n").append("        a.c_update_time, ");
		buf.append("\r\n").append("        a.c_update_by, ");
		buf.append("\r\n").append("        b.c_open_acc_no, ");
		buf.append("\r\n").append("        b.c_open_acc_name, ");
		buf.append("\r\n").append("        b.c_open_addr, ");
		buf.append("\r\n").append("        b.c_org_code, ");
		buf.append("\r\n").append("        b.c_dc_code, ");
		buf.append("\r\n").append("        b.c_account_type ");
		buf.append("\r\n").append(" from t_P_ab_acc_tree_rela a ");
		buf.append("\r\n").append(" left join t_p_bi_fund_acc b ");
		buf.append("\r\n").append(" on a.c_iden_rela = b.c_iden ");
		return buf.toString();
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from (" + getAccTreeRela() + ") A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) from T_P_AB_ACC_TREE_RELA A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_NODE_CODE_P")) {
				valueFieldbuf.append(" a.c_node_code_p in ( ");
				valueFieldbuf.append(" select c_node_code from T_P_BI_ACC_TREE t ");
				valueFieldbuf.append(" start with t.c_node_code IN (SELECT * FROM TABLE(?)) ");
				valueFieldbuf.append(" connect by prior t.c_node_code=t.c_node_code_P  ");
				valueFieldbuf.append(" )  AND ");
			}else if (fieldedName.equals("C_NODE_CODE_P")){
				valueFieldbuf.append(" a.c_node_code_p in ( ");
				valueFieldbuf.append(" select c_node_code from T_P_BI_ACC_TREE t ");
				valueFieldbuf.append(" start with t.c_node_code = ? ");
				valueFieldbuf.append(" connect by prior t.c_node_code=t.c_node_code_P  ");
				valueFieldbuf.append(" )  AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getDefaultTGHTreeViewData() {
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(" select a.c_iden,  ");
		buf.append("\r\n").append("        case when a.c_account_type='CPZH_TGH'  ");
		buf.append("\r\n").append("          and trim(a.c_org_code) IS NOT NULL  ");
		buf.append("\r\n").append("        then a.c_org_code else 'OTHERS' end as c_node_code_p,  ");
		buf.append("\r\n").append("        a.c_iden as c_node_code,  ");
		buf.append("\r\n").append("        a.c_iden as c_iden_rela,  ");
		buf.append("\r\n").append("        a.n_check_state,  ");
		buf.append("\r\n").append("        a.c_check_time,  ");
		buf.append("\r\n").append("        a.c_check_by,  ");
		buf.append("\r\n").append("        a.c_update_time,  ");
		buf.append("\r\n").append("        a.c_update_by,  ");
		buf.append("\r\n").append("        a.c_open_acc_no,  ");
		buf.append("\r\n").append("        a.c_open_acc_name,  ");
		buf.append("\r\n").append("        a.c_open_addr,  ");
		buf.append("\r\n").append("        a.c_org_code,  ");
		buf.append("\r\n").append("        a.c_dc_code,  ");
		buf.append("\r\n").append("        a.c_account_type  ");
		buf.append("\r\n").append(" from t_p_bi_fund_acc a  ");
		buf.append("\r\n").append(" where A.N_CHECK_STATE = 1 ");
		buf.append("\r\n").append(" UNION ALL ");
		buf.append("\r\n").append(" select DISTINCT  ");
		buf.append("\r\n").append("        a.c_org_code AS c_iden,  ");
		buf.append("\r\n").append("        '[root]' as c_node_code_p,  ");
		buf.append("\r\n").append("        a.c_org_code as c_node_code,  ");
		buf.append("\r\n").append("        '' as c_iden_rela,  ");
		buf.append("\r\n").append("        1 as n_check_state,  ");
		buf.append("\r\n").append("        ' ' as c_check_time,  ");
		buf.append("\r\n").append("        ' ' as c_check_by,  ");
		buf.append("\r\n").append("        ' ' as c_update_time,  ");
		buf.append("\r\n").append("        ' ' as c_update_by,  ");
		buf.append("\r\n").append("        ' ' as c_open_acc_no,  ");
		buf.append("\r\n").append("        b.c_org_name as c_open_acc_name,  ");
		buf.append("\r\n").append("        ' ' as c_open_addr,  ");
		buf.append("\r\n").append("        ' ' as c_org_code,  ");
		buf.append("\r\n").append("        ' ' as c_dc_code,  ");
		buf.append("\r\n").append("        ' ' as c_account_type  ");
		buf.append("\r\n").append(" from t_p_bi_fund_acc a  ");
		buf.append("\r\n").append(" left join t_p_bi_org b on a.c_org_code = b.c_org_code ");
		buf.append("\r\n").append(" left join t_cp_fundtype_real ft on a.c_iden = ft.c_rela_code ");
		buf.append("\r\n").append(" where ft.c_account_type='CPZH_TGH' and trim(a.c_org_code) IS NOT NULL ");
		buf.append("\r\n").append(" AND A.N_CHECK_STATE = 1 ");
		return buf.toString();
	}

	public String getDefaultGLRTreeViewData() {
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(" select a.c_iden,  ");
		buf.append("\r\n").append("        case when ");
		buf.append("\r\n").append("           trim(a.c_holder) IS NOT NULL  ");
		buf.append("\r\n").append("        then a.c_holder else 'OTHERS' end as c_node_code_p,  ");
		buf.append("\r\n").append("        a.c_iden as c_node_code,  ");
		buf.append("\r\n").append("        a.c_iden as c_iden_rela,  ");
		buf.append("\r\n").append("        a.n_check_state,  ");
		buf.append("\r\n").append("        a.c_check_time,  ");
		buf.append("\r\n").append("        a.c_check_by,  ");
		buf.append("\r\n").append("        a.c_update_time,  ");
		buf.append("\r\n").append("        a.c_update_by,  ");
		buf.append("\r\n").append("        a.c_open_acc_no,  ");
		buf.append("\r\n").append("        a.c_open_acc_name,  ");
		buf.append("\r\n").append("        a.c_open_addr,  ");
		buf.append("\r\n").append("        a.c_org_code,  ");
		buf.append("\r\n").append("        a.c_dc_code,  ");
		buf.append("\r\n").append("        a.c_account_type  ");
		buf.append("\r\n").append(" from t_p_bi_fund_acc a  ");
		buf.append("\r\n").append(" where A.N_CHECK_STATE = 1 ");
		buf.append("\r\n").append(" UNION ALL ");
		buf.append("\r\n").append(" select DISTINCT  ");
		buf.append("\r\n").append("        a.c_holder AS c_iden,  ");
		buf.append("\r\n").append("        '[root]' as c_node_code_p,  ");
		buf.append("\r\n").append("        a.c_holder as c_node_code,  ");
		buf.append("\r\n").append("        '' as c_iden_rela,  ");
		buf.append("\r\n").append("        1 as n_check_state,  ");
		buf.append("\r\n").append("        ' ' as c_check_time,  ");
		buf.append("\r\n").append("        ' ' as c_check_by,  ");
		buf.append("\r\n").append("        ' ' as c_update_time,  ");
		buf.append("\r\n").append("        ' ' as c_update_by,  ");
		buf.append("\r\n").append("        ' ' as c_open_acc_no,  ");
		buf.append("\r\n").append("        b.c_org_name as c_open_acc_name,  ");
		buf.append("\r\n").append("        ' ' as c_open_addr,  ");
		buf.append("\r\n").append("        ' ' as c_org_code,  ");
		buf.append("\r\n").append("        ' ' as c_dc_code,  ");
		buf.append("\r\n").append("        ' ' as c_account_type  ");
		buf.append("\r\n").append(" from t_p_bi_fund_acc a  ");
		buf.append("\r\n").append(" left join t_p_bi_org b on a.c_holder = b.c_org_code ");
		buf.append("\r\n").append(" where trim(a.c_holder) IS NOT NULL ");
		buf.append("\r\n").append(" AND A.N_CHECK_STATE = 1 ");
		return buf.toString();
	}

	public String getDefaultTXTreeViewData() {
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(" select a.c_iden,  ");
		buf.append("\r\n").append("        case when ");
		buf.append("\r\n").append("           trim(a.c_holer) IS NOT NULL  ");
		buf.append("\r\n").append("        then a.c_holer else 'OTHERS' end as c_node_code_p,  ");
		buf.append("\r\n").append("        a.c_iden as c_node_code,  ");
		buf.append("\r\n").append("        a.c_iden as c_iden_rela,  ");
		buf.append("\r\n").append("        a.n_check_state,  ");
		buf.append("\r\n").append("        a.c_check_time,  ");
		buf.append("\r\n").append("        a.c_check_by,  ");
		buf.append("\r\n").append("        a.c_update_time,  ");
		buf.append("\r\n").append("        a.c_update_by,  ");
		buf.append("\r\n").append("        a.c_open_acc_no,  ");
		buf.append("\r\n").append("        a.c_open_acc_name,  ");
		buf.append("\r\n").append("        a.c_open_addr,  ");
		buf.append("\r\n").append("        a.c_org_code,  ");
		buf.append("\r\n").append("        a.c_dc_code,  ");
		buf.append("\r\n").append("        a.c_account_type  ");
		buf.append("\r\n").append(" from t_p_bi_fund_acc a  ");
		buf.append("\r\n").append(" where A.N_CHECK_STATE = 1 ");
		buf.append("\r\n").append(" UNION ALL ");
		buf.append("\r\n").append(" select DISTINCT  ");
		buf.append("\r\n").append("        a.c_holer AS c_iden,  ");
		buf.append("\r\n").append("        '[root]' as c_node_code_p,  ");
		buf.append("\r\n").append("        a.c_holer as c_node_code,  ");
		buf.append("\r\n").append("        '' as c_iden_rela,  ");
		buf.append("\r\n").append("        1 as n_check_state,  ");
		buf.append("\r\n").append("        ' ' as c_check_time,  ");
		buf.append("\r\n").append("        ' ' as c_check_by,  ");
		buf.append("\r\n").append("        ' ' as c_update_time,  ");
		buf.append("\r\n").append("        ' ' as c_update_by,  ");
		buf.append("\r\n").append("        ' ' as c_open_acc_no,  ");
		buf.append("\r\n").append("        b.c_org_name as c_open_acc_name,  ");
		buf.append("\r\n").append("        ' ' as c_open_addr,  ");
		buf.append("\r\n").append("        ' ' as c_org_code,  ");
		buf.append("\r\n").append("        ' ' as c_dc_code,  ");
		buf.append("\r\n").append("        ' ' as c_account_type  ");
		buf.append("\r\n").append(" from t_p_bi_fund_acc a  ");
		buf.append("\r\n").append(" left join t_p_bi_org b on a.c_holer = b.c_org_code ");
		buf.append("\r\n").append(" where trim(a.c_holer) IS NOT NULL ");
		buf.append("\r\n").append(" AND A.N_CHECK_STATE = 1 ");
		return buf.toString();
	}

}
