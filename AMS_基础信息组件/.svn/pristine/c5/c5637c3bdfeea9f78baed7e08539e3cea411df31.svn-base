package com.yss.ams.base.information.modules.bi.accountType.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * @ClassName HkTypeUnifyPaySqlBuilder
 * @Description 款项类型设置
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
public class AccountTypeSqlBuilder implements SQLBuilder{

	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(AccountTypeTableName.elecRela);
	}

	public String getColumnNameByProperty(DBNameResolver dbNameRsv, String propName) {
		return dbNameRsv.getColumnName(AccountTypeColumnName.valueOf(propName));
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_S_DAC_TYPE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_S_DAC_TYPE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DAC_CODE")) {
				valueFieldbuf.append(" A.C_DAC_CODE =? AND ");
			}else if(fieldedName.equals("ARRAY_DAC_CODE")){
				valueFieldbuf.append(" A.C_DAC_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if(fieldedName.equals("C_DV_STATE")){
				valueFieldbuf.append(" A.C_DV_STATE =? AND ");
			}else if(fieldedName.equals("C_DAC_TYPE")){
				valueFieldbuf.append(" A.C_DAC_TYPE =? AND ");
			}else if(fieldedName.equals("C_ACC_TYPE")){
				valueFieldbuf.append(" A.C_ACC_TYPE =? AND ");
			}
		}
		valueFieldbuf.append(" 1=1  CONNECT BY PRIOR  A.C_DAC_CODE = A.C_DAC_CODE_P  START WITH A.C_DAC_CODE_P = '[root]'  ORDER SIBLINGS BY A.N_ORDER");
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/*START 数据服务方法*/
	
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlEnableBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append("select * from ( ");
		getCommonQuerySqlEnableBuf(buf);
		buf.append(" ) where C_DAC_CODE = ?  ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getAllDataSqlByKeys(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append("select * from ( ");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) where C_DAC_CODE IN ( SELECT * FROM TABLE(?) ) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append("select * from ( ");
		getCommonQuerySqlEnableBuf(buf);
		buf.append(" ) where C_DAC_TYPE in ( SELECT * FROM TABLE(?) )  ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByKeys(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append("select * from ( ");
		getCommonQuerySqlEnableBuf(buf);
		buf.append(" ) where C_DAC_CODE_P in ( SELECT * FROM TABLE(?) )   ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" SELECT A.* ");
		buf.append(" FROM T_S_DAC_TYPE A ");
		buf.append(" CONNECT BY PRIOR ");
		buf.append(" A.C_DAC_CODE = A.C_DAC_CODE_P ");
		buf.append(" START WITH A.C_DAC_CODE_P = '[root]' ");
		buf.append(" ORDER SIBLINGS BY A.N_ORDER ");
	}
	
	private void getCommonQuerySqlEnableBuf(StringBuffer buf){
		buf.append(" SELECT A.* ");
		buf.append(" FROM T_S_DAC_TYPE A ");
		buf.append(" WHERE A.C_DV_STATE='ENAB'  ");
		buf.append(" CONNECT BY PRIOR ");
		buf.append(" A.C_DAC_CODE = A.C_DAC_CODE_P ");
		buf.append(" START WITH A.C_DAC_CODE_P = '[root]' ");
		buf.append(" ORDER SIBLINGS BY A.N_ORDER ");
	}
	
	
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*END 数据服务方法*/
	public String getTree() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT LEVEL,CONNECT_BY_ISLEAF, B.* FROM ( ");
		buf.append(" SELECT * FROM T_S_DAC_TYPE WHERE C_DAC_CODE IN (SELECT * FROM TABLE(?)) AND C_ACC_TYPE = ? )  B ");
		buf.append(" START WITH C_DAC_CODE_P = '[root]'  CONNECT BY C_DAC_CODE_P = PRIOR C_DAC_CODE ORDER SIBLINGS BY N_ORDER ");
		return buf.toString();
	}
	
	public String getAccByCodeSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_S_DAC_TYPE where C_DAC_CODE not in (select C_DAC_CODE from T_PL_DAC_TYPE_SELFDEF) AND C_DAC_CODE in (SELECT * FROM table(?))  ");
		return buf.toString();
	}
	
	/**
	 * 根据父节点查询子账户
	 * @return
	 */
	public String queryAccoutTypeByParent() {
		return "SELECT A.* FROM T_S_DAC_TYPE A WHERE A.C_DAC_CODE_P = ?";
	}
}
