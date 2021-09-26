package com.yss.ams.base.information.modules.bi.hdaygroup.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 节假日群类型sql处理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class HdayGroupSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		return null;
	}
	
	/**
	 * 获取节假日群类型信息
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionSql() throws Exception {
		String retSql = " select a.*,1 as n_Level,'nodecode' as nodeCode,'parentcode' as fParaentCode from t_p_bi_hday a where a.N_CHECK_STATE >= 0 order by a.N_CHECK_STATE desc ,a.C_IDEN ";
		return retSql;
	}

	public String getQueryConditionSql(List<String> arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	
	/**
	 * 获取节假日群类型列名
	 * @param dbnameresolver s
	 */
	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(HdayGroupColumnName.valueOf(s));
	}

	public String getColumnNameByPropertyTreeView(DBNameResolver dbNameResolver,
			String s){
		return dbNameResolver.getColumnName(HdayGroupTreeViewColumnName.valueOf(s));
	}
	
	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(HdayGroupTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(HdayGroupTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(HdayGroupTableName.userInfo);
	}
	
/*START 数据服务方法*/
	
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
		getCommonQuerySqlBuf(buf);
		
		buf.append(" AND C_HDAY_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 拼接根据多个节假日群代码获取节假日群信息sql
	 * @return
	 */
	public String getDataByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" AND C_HDAY_CODE IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 拼接获取节假日群信息sql
	 * @param buf
	 */
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" select a.*, ");
		buf.append(" c_hday_code as c_hday_code_p  ");
		buf.append(" from t_p_bi_hday a  ");
		buf.append(" where a.N_CHECK_STATE = 1 ");
		buf.append(" order by a.N_CHECK_STATE desc, a.C_IDEN ");
	}
	
	private void getCommonQuerySqlBuf1(StringBuffer buf){
		buf.append(" select a.*, ");
		buf.append(" c_hday_code as c_hday_code_p  ");
		buf.append(" from t_p_bi_hday a  ");
		buf.append(" where a.N_CHECK_STATE = 1 and TO_DATE(a.C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
		buf.append(" order by a.N_CHECK_STATE desc, a.C_IDEN ");
	}

	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf1(buf);
		
		sql = " ";
		if (sql.trim().length() > 0) {
			buf.append(" AND (");
			buf.append(sql);
			buf.append(")");
		}

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/*END 数据服务方法*/

	/**
	 * STORY #73552 节假日信息设置restful接口 
	 * add by zuomingke
	 * date 20190713
	 * @return String
	 */
	
	public String getAllHdayGroupSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append(" SELECT * FROM T_P_BI_HDAY A WHERE A.N_CHECK_STATE >= 0 ");		

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
}
