package com.yss.ams.base.information.modules.sys.cashflow.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 现金流标记字典   SQl工具类
 * @author yuankai 基础信息拆分  2017.5.31
 *
 */
public class CashFlowSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(CashFlowColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(CashFlowTableName.recycle);
	}
	
	/**
	 * 获取现金流标记字典表名
	 */
	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(CashFlowTableName.userInfo);

	}
	
	/**
	 * 查询所有的不重复的现金流标记代码以及现金流名称
	 * @return
	 */
	public String getCashFlowNameByCodeSql() {
		
		return " SELECT  distinct(C_CASH_FLOW_CODE),C_CASH_FLOW_NAME  FROM t_s_Cash_Flow  ";
	}
	
	/**
	 * 根据对应的现金流代码查询出现金流名称
	 * @param pojo
	 * @return
	 */
	public String getCashFlowNameByCodeSql(String pojo) {
		
		return " SELECT  C_CASH_FLOW_CODE,C_CASH_FLOW_NAME  FROM t_s_Cash_Flow  where C_CASH_FLOW_CODE='"+pojo+"'";
	}
	
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(CashFlowTableName.userInfo);
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
	
	public String getAllDataListSql(DBNameResolver dbnamersv) {//luxiaoying：查询现金流标记SQL语句
		String retSql = "SELECT  C_CASH_FLOW_CODE,C_CASH_FLOW_NAME  FROM t_s_Cash_Flow ";
	
		return retSql;
	}

}
