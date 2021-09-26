package com.yss.ams.sec.information.modules.sv.base.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 证券基本信息SQL构造器
 * @author 马向峰
 *
 */
public class SecBaseSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		StringBuffer buf = new StringBuffer("SELECT * FROM T_P_SV_SEC_BASE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
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
		return dbnameresolver.getColumnName(SecBaseColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(SecBaseTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(SecBaseTableName.userInfo);
	}

	/**
	 * 获取通过证券内码获取对应的每手数量SQL
	 * @return
	 */
	public String getHDAmountBySecCodeSql() {
		return "SELECT N_AMOUNT_HD FROM T_P_SV_SEC_BASE A WHERE A.C_SEC_CODE = ?";
	}
	
		public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(SecBaseTableName.userInfo);
	}
	
	/**
	 * 
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.startsWith("ARRAY_C_SEC_CODE")) {
				valueFieldbuf.append(" a.C_SEC_CODE IN (SELECT * FROM TABLE(?))  AND ");
			} 
			else if(fieldedName.startsWith("C_SEC_CODE")){
				valueFieldbuf.append(" a.C_SEC_CODE = ? AND ");
			}else if(fieldedName.startsWith("ARRAY_C_IDEN")){
				valueFieldbuf.append(" a.C_IDEN IN (SELECT * FROM TABLE(?))  AND ");
			}else if(fieldedName.equals("C_IDEN")){
				valueFieldbuf.append(" a.C_IDEN = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/* start清算业务的服务 */
	// edit by lyj 2016-1-29 STORY #28785 华泰证券-在导入银行间API交易数据后的清算优化需求变更
	public String buildQuerySql() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append(" WHERE a.C_SEC_MKT_CODE = ? ");
		buf.append(" AND a.C_MKT_CODE = ? "); 
//		buf.append(" AND to_date(?,'yyyy/mm/dd') BETWEEN D_TO_LIST AND D_OFF_LIST ");
		buf.append(" AND ? BETWEEN D_TO_LIST AND D_OFF_LIST ");
		return buf.toString();
	}
	
	/**
	 * BUG #232668 富国基金-【运维】存在私有债品时，外汇交易中心债券代码清算错误
	 * @return
	 */
	public String buildQuerySqlByPortCode() {
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append("  WHERE a.C_SEC_MKT_CODE = ? AND a.C_MKT_CODE = ? ");
		buf.append("  AND ? BETWEEN D_TO_LIST AND D_OFF_LIST AND trim(C_port_code) is null ");
		buf.append("  union all ");
		buf.append("  SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append("  WHERE a.C_SEC_MKT_CODE = ? AND a.C_MKT_CODE = ? ");
		buf.append("  AND ? BETWEEN D_TO_LIST AND D_OFF_LIST AND instr(C_port_code,?) >0 ");;
		return buf.toString();
	}
	
	/* start清算业务的服务 
          根据证券品种、交易市场、回购期限获取品种信息
    by guohui 2016-12-06 STORY36452【南方基金】银行间API清算进来的回购流水，品种信息要根据计息天数来判断品种信息，不能按照上市代码来判断
    */
	public String buildQueryByVarDurSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append(" WHERE a.C_SEC_VAR_CODE = ? and a.C_MKT_CODE = ? ");
		buf.append(" AND a.C_DV_VAR_DUR = ? "); 
		buf.append(" AND ? BETWEEN D_TO_LIST AND D_OFF_LIST ORDER BY D_TO_LIST DESC");
		return buf.toString();
	}
	
	public String buildQueryRateSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append(" WHERE N_CHECK_STATE = 1 ");
		buf.append(" AND a.C_SEC_CODE = ? ");
		return buf.toString();
	}
	
	public String buildAutiSecSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" update  T_P_SV_SEC_BASE set N_CHECK_STATE = 1,c_check_by = 'sysdbo' ");
		buf.append(" WHERE N_CHECK_STATE = 0 ");
		buf.append(" AND C_SEC_MKT_CODE = ? ");
		return buf.toString();
	}
	
	/**
	 * STORY #38149 需求上海-[光大证券]金融资产管理平台V4.5[高]2017011901(银行间质押式回购基本信息改造)
	 * @return
	 */
	public String buildQuerySecByLimitDays(){
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append(" WHERE a.C_MKT_CODE = ? ");
		buf.append(" AND a.C_DV_VAR_DUR = ? "); 
		buf.append(" AND to_date(?,'yyyy/mm/dd') BETWEEN D_TO_LIST AND D_OFF_LIST ");
		return buf.toString();
	}
	
	public String buildQuerySecByLimitDaysAndVar(){
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append(" WHERE a.C_MKT_CODE = ? ");
		buf.append(" AND a.C_DV_VAR_DUR = ? "); 
		buf.append(" AND to_date(?,'yyyy/mm/dd') BETWEEN D_TO_LIST AND D_OFF_LIST ");
		buf.append(" AND a.C_SEC_VAR_CODE = ? ");
		return buf.toString();
	}
	
	/**
	 * STORY #38149 需求上海-[光大证券]金融资产管理平台V4.5[高]2017011901(银行间质押式回购基本信息改造)
	 * @return
	 */
	public String buildQuerySecSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append(" WHERE N_CHECK_STATE = 1 ");
		//edit by wangtangyao   根据调用方法 此处应以证券代码作为条件
		buf.append(" AND a.C_SEC_CODE = ? ");
		return buf.toString();
	}
	
	//协议存款业务获取证券基本信息
    //STORY #41786 安信-系统内部接口，协议存款数据
	public String buildQuerySecSqlARG() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT * FROM T_P_SV_SEC_BASE ");
		buf.append(" WHERE C_MKT_CODE = 'COTC' and C_SEC_VAR_CODE like 'CK%' ");
//		buf.append(" and D_TO_LIST <= ? and D_OFF_LIST >=? and C_DV_VAR_DUR = ? and C_ORG_CODE = ?");
//		BUG #194439 【安信基金】资金存放业务流水倒置算法默认值修改及成交编号取值逻辑修改 edit by guouguangyi 20180310
		buf.append(" AND C_SEC_MKT_CODE = ? ");
		return buf.toString();
	}
	
	public String buildQuerySecMktCodeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append(" WHERE a.C_SEC_MKT_CODE = ? ");
		buf.append(" and a.C_SEC_VAR_CODE like '%HG' ");
		buf.append(" and a.C_MKT_CODE = 'XCFE' ");
		return buf.toString();
	}
	
	public String buildQueryVarDurSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT a.* FROM T_P_SV_SEC_BASE a ");
		buf.append(" WHERE a.C_DV_VAR_DUR = ? ");
		buf.append(" and a.C_SEC_VAR_CODE like '%HG' ");
		buf.append(" and a.C_MKT_CODE = 'XCFE' ");
		return buf.toString();
	}
	/* end清算业务的服务 */
	
	/**
	 * STORY #85909 银行间质押式回购基本信息标准化 
	 * @return 
	 */
	String hasSameHgSecSql(){
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT a.c_sec_code as c_sec_code FROM T_P_SV_SEC_BASE a ");
		buf.append(" WHERE a.C_SEC_VAR_CODE = ? ");
		buf.append(" and a.C_SEC_MKT_CODE = ? ");
		buf.append(" and a.C_DV_VAR_DUR = ? ");
		buf.append(" and a.C_MKT_CODE = 'XCFE' ");
		buf.append(" and a.C_IDEN != ? ");
		return buf.toString();
	}
}
