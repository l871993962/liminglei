package com.yss.ams.product.information.modules.ab.portrela.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class PortRelaTradeAccDataSqlBuilder implements SQLBuilder{

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
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameRsv, String propName) {
		return dbNameRsv.getColumnName(PortRelaCashColumnName.valueOf(propName));
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" select * from ( ");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) where c_port_code = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getPojoByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" select * from ( ");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) where C_SH_ACC_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" select * from ( ");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) where c_port_code ");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" select b.c_port_code, a.* ");
		buf.append(" from t_p_ab_port_rela b  ");
		buf.append(" join (select * ");
		buf.append(" from T_P_AB_SH_ACC where N_CHECK_STATE = 1) a ");
		buf.append(" on a.C_SH_ACC_CODE = b.c_rela_Code ");
		buf.append(" where b.c_rela_type = 'RELA_SH_ACC' ");
		buf.append(" and b.n_check_state = 1 ");
		
		
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
