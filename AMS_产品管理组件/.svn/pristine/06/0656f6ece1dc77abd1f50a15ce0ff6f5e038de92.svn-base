package com.yss.ams.product.information.modules.ab.portrela.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class PortRelaTradeAccNoDataSqlBuilder implements SQLBuilder{

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
		return dbNameRsv.getColumnName(PortRelaColumnName.valueOf(propName));
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
	
	/**
	 * add by liyanjun 2016-4-15 BUG #129496 上海黄金交易所贵金属交易问题汇总
	 * 解决“组合关联客户编号”set界面控件不显示值
	 * @return
	 */
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
		buf.append(" select b.c_port_code,b.C_DV_TYPE_CODE, a.*, ");
		/**Start 20150516 added by liubo.BUG #112509 现货交易业务新建数据的时候报错
		 * SQL中缺少C_CA_CODE字段，导致给POJO赋值时出错*/
		buf.append(" b.C_CA_CODE, ");
		/**End 20150516 added by liubo.BUG #112509 现货交易业务新建数据的时候报错*/
		buf.append(" b.C_RELA_CODE,b.C_RELA_TYPE ");
		buf.append(" from t_p_ab_port_rela b  ");
		buf.append(" join (select * ");
		buf.append(" from T_P_AB_SH_ACC where N_CHECK_STATE = 1) a ");
		buf.append(" on a.C_SH_ACC_CODE = b.c_rela_Code ");
//		buf.append(" where b.c_rela_type = 'RELA_CL_NUM' ");
//		buf.append(" and b.n_check_state = 1 ");
		buf.append(" where b.n_check_state = 1 ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
