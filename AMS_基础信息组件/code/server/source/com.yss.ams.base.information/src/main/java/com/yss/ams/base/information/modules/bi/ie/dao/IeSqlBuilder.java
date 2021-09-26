package com.yss.ams.base.information.modules.bi.ie.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * @classDesc 收支代码
 * @version 1.0 2012-11-29
 * @author yh
 */

/**
 * 收支代码设置sql处理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class IeSqlBuilder implements SQLBuilder {

	/**
	 * 根据前台查询条件获取收支项目设置总记录数
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();
		
		try{
			buf.append(" select COUNT(*) AS CNT from T_P_BI_IE a ");
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
	 * 根据前台查询条件获取收支项目设置所有记录
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();
		try{
		buf.append(" select a.* from T_P_BI_IE a ");
		setWhereSql(fieldValueBuf, paraNameList);
		if(fieldValueBuf.length()>1){
			buf.append(" where ").append(fieldValueBuf);;
		}
		buf.append(" order by a.N_CHECK_STATE asc ,C_FEE_CODE ,a.C_IDEN ASC");
		retSql = buf.toString();
		}catch(Exception ex){
			throw ex;
		}finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}
	
	/**
	 * 根据前台传送过来参数判断添加筛选条件，获取收支设置数据
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_FEE_CODE")) {
				valueFieldbuf.append("a.C_FEE_CODE LIKE ? AND ");
			} else if (fieldedName.equals("C_FEE_NAME")) {
				valueFieldbuf.append("a.C_FEE_NAME LIKE ? AND ");
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
		return dbnameresolver.getColumnName(IeColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(IeTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(IeTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(IeTableName.userInfo);
	}
	
	/*START 数据服务方法*/
	/**
	 * 获取所有已审核的收支项目设置
	 * @return
	 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		//buf.append(" ORDER BY A.N_ORDER ASC ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" AND C_FEE_CODE = ? ");
		getOrderBySqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" AND C_SRC_MARK IN (SELECT * FROM TABLE(?)) ");
		getOrderBySqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByFeeCodes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" AND C_FEE_CODE IN (SELECT * FROM TABLE(?)) ");
		getOrderBySqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getOrderBySqlBuf(StringBuffer buf){
		buf.append(" ORDER BY N_CHECK_STATE asc ,C_FEE_CODE ,C_IDEN ASC ");
	}
	
	private void getCommonQuerySqlBuf(StringBuffer buf){
		//buf.append(" SELECT A.* FROM T_S_IE_ITEM A  ");
		buf.append(" select a.* from T_P_BI_IE a");// Add by ChenLong 20120521 收支代码数据表来源不正确
		buf.append(" WHERE A.N_CHECK_STATE = 1 ");
	}

	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByType() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" select a.*, b.c_Fee_Name from T_P_BI_IE_RELA a ");
		buf.append(" left join T_P_BI_IE b on a.c_Fee_Code = b.c_Fee_Code ");
		buf.append(" where a.N_CHECK_STATE = 1 AND a.C_IE_CODE IN (SELECT * FROM TABLE(?))");
		buf.append(" order by a.N_CHECK_STATE asc, a.C_FEE_CODE, a.C_IDEN ASC ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	/*END 数据服务方法*/

}
