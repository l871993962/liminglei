package com.yss.ams.sec.information.modules.sv.indexinfo.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;


/**
 * 
 * @author chenbo
 *2017-06-22
 *#42948 资讯信息管理组件化拆分
 */
public class IndexInfoSqlBuilder implements SQLBuilder{

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
		return dbNameResolver.getTableName(IndexInfoTableName.indexinfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(IndexInfoColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(IndexInfoTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(IndexInfoTableName.indexinfo);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();
		try{
		buf.append(" select a.* from T_P_SV_INDEX a ");
		setWhereSql(fieldValueBuf, paraNameList);
		if(fieldValueBuf.length()>1){
			buf.append(" where ").append(fieldValueBuf);;
		}
		buf.append(" order by a.N_CHECK_STATE asc ");
		retSql = buf.toString();
		}catch(Exception ex){
			throw ex;
		}finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();
		
		try{
			buf.append(" select COUNT(*) AS CNT from T_P_SV_INDEX a ");
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
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_INDEX_CODE")) {
				valueFieldbuf.append(" a.C_INDEX_CODE = ? AND ");
			}else if (fieldedName.equals("ARRAY_INDEX_CODE")) {
				valueFieldbuf.append(" a.C_INDEX_CODE IN (select * from table(?)) AND ");
			} else if (fieldedName.equals("D_BASE")) {
				valueFieldbuf.append(" a.D_BASE >= ? AND ");
			} else if (fieldedName.equals("D_END")) {
					valueFieldbuf.append(" a.D_END <= ? AND ");
			/*- Fixed by huangsq Start BUG #133162 回收站不能按日期查询 */
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}
			/*- Fixed by huangsq End BUG #133162 回收站不能按日期查询 */
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/** 数据服务开始 **/
	
	/**
	 * 查询所有数据sql
	 * @return sql
	 */
	public String getDataList(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySql(buf);
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 数据服务公用sql
	 * @param buf
	 */
	private void getCommonQuerySql(StringBuffer buf) {
		buf.append(" SELECT INDEXINFO.* FROM T_P_SV_INDEX INDEXINFO WHERE INDEXINFO.N_CHECK_STATE = 1 ");
	}

	public String getPortRelaIndex(List<String> paraNameList) {
		StringBuffer buf  = new StringBuffer();
		buf.append(" SELECT A.* ");
		buf.append("   FROM T_P_SV_INDEX A ");
		buf.append("  WHERE NOT EXISTS (SELECT * ");
		buf.append("           FROM T_P_AB_PORT_RELA B ");
		buf.append("          WHERE A.C_INDEX_CODE = B.C_RELA_CODE ");
		buf.append("            AND B.C_PORT_CODE = ? ");
		buf.append("            AND B.N_CHECK_STATE >= 0) ");
		buf.append("     AND A.N_CHECK_STATE = 1   ");

		return buf.toString();
	}
	
	/** 数据服务结束**/

}
