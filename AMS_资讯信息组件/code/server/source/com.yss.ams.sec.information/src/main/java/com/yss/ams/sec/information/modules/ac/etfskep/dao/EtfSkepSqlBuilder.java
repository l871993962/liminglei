package com.yss.ams.sec.information.modules.ac.etfskep.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
/**
 * ETF股票篮子 SQL构造类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class EtfSkepSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(EtfSkepColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(EtfSkepTableName.recycle);
	}

	/**
	 * 返回 根据前台查询条件获取ETF股票篮信息数据总数量 的SQL
	 * @param paraNameList
	 * @return queryCountSql
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		// TODO Auto-generated method stub
		
		String queryCountSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();		
		try{
			buf.append("SELECT COUNT(*) AS CNT from T_D_MP_ETF_SKEP_DETAIL a ");
			buildWhereSql(fieldValueBuf, paraNameList);
			if(fieldValueBuf.length() > 0){
				buf.append(" WHERE ").append(fieldValueBuf);
			}
			queryCountSql = buf.toString();
		}catch(Exception ex){
			throw ex;
		}finally {
			StringUtil.clearStringBuffer(buf);
		}
		return queryCountSql;
	}
	
	/**
	 * 根据前台传送过来参数判断添加筛选条件 的SQL
	 * @param buf
	 * @param paraNameList
	 */
	private void buildWhereSql(StringBuffer buf, List<String> paraNameList){
		//buf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList){
			if(fieldedName.equals("ARRAY_C_TRADE_CODE")){			
				buf.append(" a.C_TRADE_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
			else if(fieldedName.equals(("D_START_DATE"))){
				buf.append(" d_TRADE >= to_date(?,'yyyy-MM-dd') AND ");
			}
			else if(fieldedName.equals(("D_END_DATE"))){
				buf.append(" d_TRADE <= to_date(?,'yyyy-MM-dd') AND ");
			}
			else if(fieldedName.equals(("C_SEC_CODE"))){
				buf.append(" C_SEC_CODE = ? AND ");
			}
			else if (fieldedName.equals("C_TRADE_CODE")) {
				buf.append(" C_TRADE_CODE = ? AND");
			}
		}
		if (buf.length() > 0) {
			StringUtil.delLastSplitMark(buf, " AND ");
		}
	}
	
	/**
	 * 返回 根据前台查询条件获取ETF股票篮信息所有数据 的SQL
	 * @param paraNameList
	 * @return querySql
	 * @throws Exception
	 */
	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		// TODO Auto-generated method stub
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try{
			sqlBuffer.append("Select * from T_D_MP_ETF_SKEP_DETAIL a  ");
			buildWhereSql(valueFieldbuf,paraNameList);
			if (valueFieldbuf.length() > 0){
				sqlBuffer.append(" WHERE ").append(valueFieldbuf);
			}			
			sqlBuffer.append(" Order by D_TRADE,C_TRADE_CODE,C_SEC_CODE ");
			querySql = sqlBuffer.toString();
		}
		catch (Exception ex){
			throw ex;
		}
		finally{
			StringUtil.clearStringBuffer(sqlBuffer);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return querySql;
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(EtfSkepTableName.eConfirm);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(EtfSkepTableName.eConfirm);
	}
	
		
}
