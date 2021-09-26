package com.yss.ams.visaval.dao;



import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;


public class AdvAlgoSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(AdvAlgoColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(AdvAlgoTableName.recycle);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		// TODO Auto-generated method stub
		
		String queryCountSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();		
		try{
			buf.append("SELECT COUNT(*) AS CNT from T_V_AA_ADV_ALGO a ");
			buildWhereSql(fieldValueBuf, paraNameList);
			if(fieldValueBuf.length() > 0){
				buf.append(" WHERE ").append(fieldValueBuf);
			}
			buf.append("  ORDER BY A.N_CHECK_STATE ASC ,A.C_UPDATE_TIME DESC ");
			queryCountSql = buf.toString();
		}catch(Exception ex){
			throw ex;
		}finally {
			StringUtil.clearStringBuffer(buf);
		}
		return queryCountSql;
	}
	private void buildWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DV_ALGO_TYPE")){
				valueFieldbuf.append(" a.C_DV_ALGO_TYPE =? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		// TODO Auto-generated method stub
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try{
			sqlBuffer.append( "SELECT A.* FROM T_V_AA_ADV_ALGO A " );
			
			buildWhereSql(valueFieldbuf,paraNameList);
			if (valueFieldbuf.length() > 0){
				sqlBuffer.append(" WHERE ").append(valueFieldbuf);
			}
			sqlBuffer.append("  ORDER BY A.N_CHECK_STATE ASC ,A.C_UPDATE_TIME DESC ");
			//sqlBuffer.append("  order by position  ");
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
		return dbnameresolver.getTableName(AdvAlgoTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(AdvAlgoTableName.userInfo);
	}
	
/*START 数据服务*/
	
	public String getAllDataSql(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		/**
		 * buf.append(" select a.*,zh.c_formula "); // STORY #31713 【产品优化】算法公式配置优化  添加算法中文代码  20170906 马向峰
		buf.append(" from T_V_AA_ADV_ALGO a  left join T_V_AA_ADV_ALGO_ZH zh on a.c_algo_code=zh.c_algo_code and");
		buf.append(" a.N_CHECK_STATE = 1 ");
		 */
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
//		buf.append("AND C_DV_ALGO_TYPE = ? ");edit by weijj  bugBUG9029 
		buf.append("AND a.C_ALGO_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		buf.append(" AND ");
		buf.append(" a.C_DV_ALGO_TYPE ");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getCommonQuerySqlBuf(StringBuffer buf){
//		buf.append(" select a.*"); 
//		buf.append(" from T_V_AA_ADV_ALGO a ");
//		buf.append(" where a.N_CHECK_STATE = 1 ");
		//STORY #31713 【产品优化】算法公式配置优化  modify by maxiangfeng 20170919
		//如果是新算法 则从关联表内取出算法的描述
		buf.append(" select a.*,d.c_desc as new_desc,zh.C_FORMULA as new_c_formula"); 
		buf.append(" from T_V_AA_ADV_ALGO a left join T_V_AA_ADV_ALGO_DESC d on a.c_algo_code=d.c_algo_code ");
		buf.append(" left join T_V_AA_ADV_ALGO_ZH zh on a.c_algo_code=zh.c_algo_code ");
		buf.append(" where a.N_CHECK_STATE = 1 ");
	}

	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		
		sql = " TO_DATE(a.C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		if (sql.trim().length() > 0) {
			buf.append(" AND (");
			buf.append(sql);
			buf.append(")");
		}

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/*END 数据服务*/
	
	/**
     * @Title getAllAchivmentAgio 
     * @Description 查询所有的算法公式
     * @author lff
     * @date 2019年3月3日上午11:15:28
     * @return String
     */
	public String getAllAchivmentAlgo() {
		StringBuffer buffer = new StringBuffer();
        buffer.append(" SELECT P.* FROM T_V_AA_ADV_ALGO ")
              .append(" P WHERE P.N_CHECK_STATE = 1 ");
        return buffer.toString();
	}
	
	/**
     * @Title getAllAchivmentAgio 
     * @Description 查询所有的算法公式
     * @author lff
     * @date 2019年3月3日上午11:15:28
     * @return String
     */
	public String getAchivmentAlgoByCode() {
		StringBuffer buffer = new StringBuffer();
        buffer.append(" SELECT P.* FROM T_V_AA_ADV_ALGO ")
              .append(" P WHERE P.C_ALGO_CODE = ? ");
        return buffer.toString();
	}
	
	/**
     * @Title getParamListByCode 
     * @Description 公式的参数项
     * @author lff
     * @date 2019年3月2日上午11:56:31
     * @return String
     */
    public String getParamListByCode() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(" SELECT * FROM T_S_PARA ")
              .append(" T WHERE C_PARA_CODE  IN (SELECT * FROM TABLE(?) ) ");
        return buffer.toString();
    }
	
}
