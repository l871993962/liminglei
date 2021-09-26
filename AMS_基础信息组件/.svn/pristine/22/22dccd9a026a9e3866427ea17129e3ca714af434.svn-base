package com.yss.ams.base.information.modules.sys.automaticSet.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/** 
 * 自动化业务设置sqlbuilder
 * @ClassName: AutomaticSetSqlBuilder
 * @date 2020年12月24日
 * @Stroy90952
 * @author yangze
 */
public class AutomaticSetSqlBuilder implements SQLBuilder {

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
		return dbNameResolver.getTableName(AutomaticSetRelaTableName.userInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(AutomaticSetColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(AutomaticSetRelaTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(AutomaticSetRelaTableName.userInfo);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		sqlBuff.append(" select A.* ");
		sqlBuff.append(" from ");
		sqlBuff.append(AutomaticSetRelaTableName.userInfo + " A ");
		if (valueFieldbuf.length() > 0) {
			sqlBuff.append(" where ").append(valueFieldbuf);
		}
		sqlBuff.append(" order by A.C_BUSINESS_CODE desc, A.C_PORT_CODE");
		return sqlBuff.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		sqlBuff.append(" select count(*) ");
		sqlBuff.append(" from ");
		sqlBuff.append(AutomaticSetRelaTableName.userInfo + " A ");
		if (valueFieldbuf.length() > 0) {
			sqlBuff.append(" where ").append(valueFieldbuf);
		}
		return sqlBuff.toString();
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf, List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_BUSINESS_TYPE_CODE")) {
				valueFieldbuf.append(" A.C_BUSINESS_TYPE_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_BUSINESS_CODE")) {
				valueFieldbuf.append(" A.C_BUSINESS_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * 根据组合代码查询业务类型code
	 * @return
	 */
	public String queryBusiCodeByPortCode() {
		return " SELECT A.C_BUSINESS_TYPE_CODE, A.C_BUSINESS_CODE FROM T_P_AUTOMATIC_SET A WHERE A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1 ";
	}
	
	/**
	 * STORY #100516 【华夏基金】系统支持设置组合的责任会计信息（STORY #100065拆出给估值）
	 * 根据组合查询业务类型和组合对应关系
	 * @return
	 */
	public String queryBusiCodeByPortCodes() {
		return " SELECT A.C_PORT_CODE, A.C_BUSINESS_CODE FROM T_P_AUTOMATIC_SET A WHERE A.C_BUSINESS_TYPE_CODE = ? AND A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND A.N_CHECK_STATE = 1 ";
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @return
	 */
	public String queryCheckStatusByPortCodeAndBusiCode() {
		return " SELECT COUNT(1) CNUM FROM T_P_AUTOMATIC_SET A WHERE A.C_PORT_CODE = ? AND A.C_BUSINESS_TYPE_CODE = ? AND A.C_BUSINESS_CODE = ? AND A.N_CHECK_STATE = 1 ";
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @return
	 */
	public String getPortListByBusiCodeSql() {
		return " SELECT A.C_PORT_CODE FROM T_P_AUTOMATIC_SET A WHERE A.C_BUSINESS_TYPE_CODE = ? AND A.C_BUSINESS_CODE = ? AND A.N_CHECK_STATE = 1 ";
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @return
	 */
	public String getDataTypeListSql() {
		return " SELECT DISTINCT A.C_BUSINESS_TYPE_CODE, A.C_BUSINESS_TYPE_NAME FROM T_P_AUTOMATIC_SET_TYPE A ";
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @return
	 */
	public String getDataListByTypeSql() {
		return " SELECT A.C_BUSINESS_CODE, A.C_BUSINESS_NAME FROM T_P_AUTOMATIC_SET_TYPE A WHERE A.C_BUSINESS_TYPE_CODE = ? ORDER BY A.N_ORDER ";
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @return
	 */
	public String getDeleteDataByTypeSql() {
		return " DELETE FROM T_P_AUTOMATIC_SET_TYPE A WHERE A.C_BUSINESS_TYPE_CODE = ? ";
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @return
	 */
	public String getInsertDataListSql() {
		return " INSERT INTO T_P_AUTOMATIC_SET_TYPE (N_ORDER,C_BUSINESS_TYPE_CODE,C_BUSINESS_TYPE_NAME,C_BUSINESS_CODE,C_BUSINESS_NAME,C_UPDATE_BY,C_UPDATE_TIME) VALUES (?,?,?,?,?,?,TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS')) ";
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @return
	 */
	public String getBusiInfoByCodeSql() {
		return " SELECT DISTINCT A.C_BUSINESS_CODE, B.C_BUSINESS_NAME FROM T_P_AUTOMATIC_SET A LEFT JOIN T_P_AUTOMATIC_SET_TYPE B ON B.C_BUSINESS_TYPE_CODE = A.C_BUSINESS_TYPE_CODE AND B.C_BUSINESS_CODE = A.C_BUSINESS_CODE WHERE A.C_BUSINESS_TYPE_CODE = ? AND A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1 ";
	}
	
	/**
	 * STORY #100516 【华夏基金】系统支持设置组合的责任会计信息（STORY #100065拆出给估值）
	 * @return
	 */
	public String getDeleteSetDataByTypeSql() {
		return " DELETE FROM T_P_AUTOMATIC_SET A WHERE A.C_BUSINESS_TYPE_CODE = ? AND A.N_CHECK_STATE = 0 AND TRIM(A.C_BUSINESS_CODE) IS NULL ";
	}
	
	/**
	 * STORY #100516 【华夏基金】系统支持设置组合的责任会计信息（STORY #100065拆出给估值）
	 * @return
	 */
	public String getInsertSetDataListSql() {
		return " INSERT INTO T_P_AUTOMATIC_SET (C_IDEN, C_BUSINESS_TYPE_CODE, C_PORT_CODE) SELECT ?, ?, ? FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM T_P_AUTOMATIC_SET WHERE C_BUSINESS_TYPE_CODE = ? AND C_PORT_CODE = ?) ";
	}
}
