package com.yss.ams.base.information.modules.bi.ieLink.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * @classDesc 收支连接
 * @version 1.0 2012-11-29
 * @author yh
 */

/**
 * @author yuankai 公共信息拆分  2017.5.31
 */
public class IeLinkSqlBuilder implements SQLBuilder {
	
	/**
	 * 根据收支链接前台查询条件获取收支链接设置总记录数
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();
		
		try{
			buf.append(" select COUNT(*) AS CNT from T_P_BI_IE_RELA a ");
			setWhereSql(fieldValueBuf, paraNameList);
			if(fieldValueBuf.length()>1){
				buf.append(" where ").append(fieldValueBuf);
			}
			retSql = buf.toString();
		}catch(Exception ex){
			throw ex;
		}finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	/**
	 * 根据收支链接前台查询条件获取收支链接设置总记录
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();
		try{
		buf.append(" select a.* ,b.c_Fee_Name from T_P_BI_IE_RELA a ");
		buf.append(" left join T_P_BI_FEE b on a.c_Fee_Code = b.c_Fee_Code");
		setWhereSql(fieldValueBuf, paraNameList);
		if(fieldValueBuf.length()>1){
			buf.append(" where ").append(fieldValueBuf);;
		}
		buf.append(" order by a.N_CHECK_STATE asc ,a.C_FEE_CODE ,a.C_IDEN ASC");
		retSql = buf.toString();
		}catch(Exception ex){
			throw ex;
		}finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}
	
	/**
	 * 根据前台传递参数判断哪些参数需要作为查询条件
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_FEE_CODE")) {
				valueFieldbuf.append(" a.C_FEE_CODE LIKE ? AND ");
			} else if (fieldedName.equals("ARRAY_C_IE_CODE")) {
				valueFieldbuf.append(" a.C_IE_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
					valueFieldbuf.append(" a.C_DEL_TIME <= ? AND ");
			} 
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/* START 数据服务 */
	
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		getCommonOrderSqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * add by liyanjun 2016-2-17 BUG #126592 科目体系界面的费用代码选项数据重复
	 */
	public String getAllFeeDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT DISTINCT B.C_IDEN,B.C_FEE_CODE,B.C_SRC_MARK,B.C_DESC ,B.N_CHECK_STATE,B.C_UPDATE_BY, ");
		buf.append(" B.C_UPDATE_TIME,B.C_CHECK_BY,B.C_CHECK_TIME, B.C_FEE_NAME,' ' C_FEE_CODE_P,' ' C_IE_NAME,' ' C_IE_CODE ");
		buf.append(" FROM T_P_BI_IE_RELA A, ");
		buf.append(" T_P_BI_IE B, T_S_IE_ITEM C ");
		buf.append(" WHERE A.N_CHECK_STATE = 1  ");
		buf.append(" AND A.C_FEE_CODE = B.C_FEE_CODE ");
		buf.append(" AND B.N_CHECK_STATE = 1 ");
		buf.append(" AND A.C_IE_CODE = C.C_IE_CODE ");
		buf.append(" ORDER BY B.C_FEE_CODE ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		buf.append(" AND A.C_FEE_CODE = ? ");
		getCommonOrderSqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByCodes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		buf.append(" and A.C_FEE_CODE ");
		buf.append(" in (SELECT * FROM TABLE(?)) ");
		getCommonOrderSqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByParentCodes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		buf.append(" and A.C_FEE_CODE_P ");
		buf.append(" in (SELECT * FROM TABLE(?)) ");
		getCommonOrderSqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		buf.append(" and A.C_IE_CODE ");
		buf.append(" in (SELECT * FROM TABLE(?)) ");
		getCommonOrderSqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" SELECT DISTINCT A.*, B.C_FEE_NAME, C.C_IE_NAME ");
		buf.append(" FROM T_P_BI_IE_RELA A, ");
		buf.append(" T_P_BI_IE B, T_S_IE_ITEM C ");
		buf.append(" WHERE A.N_CHECK_STATE = 1  ");
		buf.append(" AND A.C_FEE_CODE = B.C_FEE_CODE ");
		buf.append(" AND B.N_CHECK_STATE = 1 ");
		buf.append(" AND A.C_IE_CODE = C.C_IE_CODE ");
	}
	
	private void getCommonOrderSqlBuf(StringBuffer buf){
		buf.append(" ORDER BY A.C_IE_CODE, A.C_FEE_CODE ");
	}
	
	/* END 数据服务 */
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
		return dbnameresolver.getColumnName(IeLinkColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(IeLinkTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(IeLinkTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(IeLinkTableName.userInfo);
	}

}
