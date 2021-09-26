package com.yss.ams.base.information.modules.bi.account.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * STORY #76292 【4.5同步】同步“机构名称”、“账户明细类型”、“关联组合”至“现金账户”
 * @author lenovo
 *
 */
public class PortRelaSqlBuilder implements SQLBuilder {

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
	
	/**
	 * 插入组合现金账户关联数据SQL
	 * @return
	 */
	public String getInsertPortRelaSql() {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into T_P_AB_PORT_RELA (C_IDEN, C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE, C_UPDATE_BY, C_UPDATE_TIME) ");
		sql.append(" SELECT SEQU_P_AB_PORT_RELA.NEXTVAL AS C_IDEN, ?, 'RELA_CASH_ACC', ?, ' ', ?, to_char(sysdate,'yyyymmdd HH24:mi:ss') ");
		sql.append(" FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM T_P_AB_PORT_RELA R WHERE R.C_PORT_CODE = ? AND R.C_RELA_TYPE = 'RELA_CASH_ACC') ");
		return sql.toString();
	}
	
	/**
	 * 更新组合现金账户关联数据SQL
	 * @return
	 */
	public String getUpdatePortRelaSql() {
		StringBuffer sql = new StringBuffer();
		sql.append(" update T_P_AB_PORT_RELA a ");
		sql.append(" set a.c_rela_code = ?, a.c_update_by = ?, a.n_check_state = 0, a.c_update_time = to_char(sysdate,'yyyymmdd HH24:mi:ss') ");
		sql.append(" where a.c_rela_type = 'RELA_CASH_ACC' and a.c_port_code in (select * from table(?)) ");
		return sql.toString();
	}
	
	/**
	 * 删除组合现金账户关联数据SQL
	 * @return
	 */
	public String getDeletePortRelaSql() {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from T_P_AB_PORT_RELA where c_rela_code = ? and c_rela_type = 'RELA_CASH_ACC' and c_port_code in (select * from table(?)) ");
		return sql.toString();
	}
	
	/**
	 * 查询现金账户对应的组合代码
	 * @return
	 */
	public String getPortCodeSql() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.c_port_code ");
		sql.append(" FROM T_P_AB_PORT_RELA a ");
		sql.append(" WHERE a.c_rela_code = ? AND a.c_rela_type = 'RELA_CASH_ACC' ");
		return sql.toString();
	}
	
	/**
	 * 获取关联现金账户的组合数量
	 * @return
	 */
	public String getRelaPortCountSql() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) as port_count from T_P_AB_PORT_RELA where c_port_code = ? and c_rela_type = 'RELA_CASH_ACC' ");
		return sql.toString();
	}
}
