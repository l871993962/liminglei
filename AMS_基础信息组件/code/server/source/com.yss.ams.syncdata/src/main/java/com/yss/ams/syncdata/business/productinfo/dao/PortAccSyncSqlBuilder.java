package com.yss.ams.syncdata.business.productinfo.dao;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;

/**
 * STORY #58468 生命周期系统中的账户信息数据同步至基础组件的银行账户信息
 * @author lenovo
 *
 */
public class PortAccSyncSqlBuilder implements SQLBuilder {

	private String fundTableName = "";
	private String relaTableName = "";
	private String openAddr = "C_OPEN_ADDR";
	private String cpFundAcc = "T_C_CP_FUND_ACC";
	private String biFundAcc = "T_P_BI_FUND_ACC";
	
	/**
	 * 设置要维护的表名
	 * @param fundTableName
	 * @param relaTableName
	 */
	public void setTableName(String fundTableName, String relaTableName) {
		this.fundTableName = fundTableName;
		this.relaTableName = relaTableName;
	}

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
		if (cpFundAcc.equals(fundTableName)) {
			return dbNameResolver.getTableName(PortAccSyncTableName.cpFundAcc);
		}
		else {
			return dbNameResolver.getTableName(PortAccSyncTableName.biFundAcc);
		}
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return dbNameResolver.getColumnName(PortAccSyncColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		if (cpFundAcc.equals(fundTableName)) {
			return dbNameResolver.getTableName(PortAccSyncTableName.cpRecycle);
		}
		else {
			return dbNameResolver.getTableName(PortAccSyncTableName.biRecycle);
		}
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		if (cpFundAcc.equals(fundTableName)) {
			return dbNameResolver.getTableName(PortAccSyncTableName.cpFundAcc);
		}
		else {
			return dbNameResolver.getTableName(PortAccSyncTableName.biFundAcc);
		}
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
	
	/**
	 * 插入组合银行账户关联数据SQL
	 * @return
	 */
	public String getInsertPortFundRelaSql() {
		StringBuffer sql = new StringBuffer();
		if (cpFundAcc.equals(fundTableName)) {
			String userCode = ContextFactory.getContext().getUserCode();
			userCode = StringUtil.IsNullOrEmpty(userCode) ? " " : userCode;
			sql.append(" insert into T_P_AB_PORT_RELA (C_IDEN, C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE, C_CA_CODE, C_UPDATE_BY, C_UPDATE_TIME) ");
			sql.append(" SELECT SEQU_P_AB_PORT_RELA.NEXTVAL AS C_IDEN, ?, 'RELA_FUND_ACC', T.C_IDEN AS C_RELA_CODE, nvl(T.C_ACCOUNT_TYPE, ' '), T.C_CA_CODE, ");
			sql.append(" '").append(userCode).append("', to_char(sysdate,'yyyymmdd HH24:mi:ss') ");
			sql.append(" FROM T_C_CP_FUND_ACC T WHERE T.C_IDEN = ? ");
			sql.append(" AND NOT EXISTS (SELECT 1 FROM T_P_AB_PORT_RELA R WHERE R.C_PORT_CODE = ? ");
			sql.append(" AND R.C_RELA_CODE = T.C_IDEN AND R.C_RELA_TYPE = 'RELA_FUND_ACC' AND R.C_DV_TYPE_CODE = T.C_ACCOUNT_TYPE) ");
		}
		else {
			sql.append(" insert into T_P_AB_PORT_ACC_RELA (C_IDEN, C_PORT_CODE, C_RELA_CODE, C_ACCOUNT_TYPE) ");
			sql.append(" SELECT SEQU_P_AB_PORT_ACC_RELA.NEXTVAL AS C_IDEN, ?, T.C_IDEN AS C_RELA_CODE, T.C_ACCOUNT_TYPE ");
			sql.append(" FROM T_P_BI_FUND_ACC T WHERE T.C_IDEN = ? ");
			sql.append(" AND NOT EXISTS (SELECT 1 FROM T_P_AB_PORT_ACC_RELA R WHERE R.C_PORT_CODE = ? AND R.C_RELA_CODE = T.C_IDEN) ");
		}
		return sql.toString();
	}
	
	/**
	 * 更新组合银行账户关联数据SQL
	 * @param dvTypeCode
	 * @return
	 */
	public String getUpdatePortFundRelaSql(String dvTypeCode) {
		StringBuffer sql = new StringBuffer();
		if (cpFundAcc.equals(fundTableName)) {
			String userCode = ContextFactory.getContext().getUserCode();
			userCode = StringUtil.IsNullOrEmpty(userCode) ? " " : userCode;
			dvTypeCode = "".equals(dvTypeCode) ? " " : dvTypeCode;
			sql.append(" update T_P_AB_PORT_RELA a ");
			sql.append(" set (a.c_port_code, a.c_ca_code, a.c_dv_type_code, a.c_update_by, a.c_update_time) = ");
			sql.append(" (select ?, b.c_ca_code, nvl(b.c_account_type, ' '), '").append(userCode).append("', to_char(sysdate,'yyyymmdd HH24:mi:ss') from T_C_CP_FUND_ACC b where b.c_iden = a.c_rela_code) ");
			sql.append(" where a.c_rela_code = ? and a.c_port_code = ? and a.c_rela_type = 'RELA_FUND_ACC' and a.c_dv_type_code = '").append(dvTypeCode).append("' ");
		}
		else {
			sql.append(" update T_P_AB_PORT_ACC_RELA a ");
			sql.append(" set (a.c_port_code, a.c_account_type) = (select ?, b.c_account_type from T_P_BI_FUND_ACC b where b.c_iden = a.c_rela_code) ");
			sql.append(" where a.c_rela_code = ? and a.c_port_code = ? ");
		}
		return sql.toString();
	}
	
	/**
	 * 更新老账户信息表关联字段SQL
	 * @return
	 */
	public String updateFundAccRela() {
		StringBuffer sql = new StringBuffer();
		if (cpFundAcc.equals(fundTableName)) {
			sql.append(" update T_C_CP_FUND_ACC a set a.c_rela_code = ");
			sql.append(" (select b.c_iden from T_P_BI_FUND_ACC b ");
			sql.append(" where b.c_dc_code = a.c_dc_code and b.c_open_acc_name = a.c_open_acc_name ");
			sql.append(" and b.c_open_acc_no = a.c_open_acc_no and b.c_open_addr = a.c_open_addr) ");
			sql.append(" where a.c_iden = ? ");
		}
		return sql.toString();
	}

	/**
	 * 删除组合银行账户关联数据SQL
	 * @return
	 */
	public String getDeletePortFundRelaSql() {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from ").append(relaTableName).append(" where c_rela_code = ? and c_port_code = ? ");
		return sql.toString();
	}
	
	/**
	 * 查询账户主体卡信息数据SQL
	 * @param paraMap
	 * @return
	 */
	public String getQueryFundAccSql(HashMap<String, Object> paraMap) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.c_iden, ");
		sql.append(" r.c_port_code, ");
		sql.append(" a.c_ca_code, ");
		sql.append(" a.c_dc_code, ");
		sql.append(" a.c_open_addr, ");
		sql.append(" a.c_open_acc_no, ");
		sql.append(" a.c_sys_code, ");
		sql.append(" a.c_usage, ");
		sql.append(" a.c_desc, ");
		sql.append(" a.n_check_state, ");
		sql.append(" a.c_update_by, ");
		sql.append(" a.c_update_time, ");
		sql.append(" a.c_check_by, ");
		sql.append(" a.c_check_time, ");
		sql.append(" a.c_open_acc_name, ");
		sql.append(" a.d_begin, ");
		sql.append(" a.d_end, ");
		sql.append(" a.c_org_code, ");
		sql.append(" a.c_holder, ");
		sql.append(" a.c_ass_code, ");
		if (biFundAcc.equals(fundTableName)) {
			sql.append(" a.c_dv_data_src, ");
		}
		sql.append(" a.c_account_type, ");
		sql.append(" a.c_haveused, ");
		sql.append(" a.c_inter_org_code, ");
		sql.append(" a.c_bank_code, ");
		sql.append(" a.c_pay_code FROM ").append(fundTableName).append(" a INNER JOIN ").append(relaTableName).append(" r ");
		sql.append(" ON a.C_IDEN = r.C_RELA_CODE WHERE a.C_DC_CODE = ? and a.C_OPEN_ACC_NAME = ? and a.C_OPEN_ACC_NO = ? ");
		if (paraMap.containsKey(openAddr)) {
			sql.append(" and a.C_OPEN_ADDR = ? ");
		}
		return sql.toString();
	}
}
