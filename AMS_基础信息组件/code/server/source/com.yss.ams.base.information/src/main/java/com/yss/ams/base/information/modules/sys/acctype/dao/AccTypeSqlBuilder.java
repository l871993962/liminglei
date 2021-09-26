package com.yss.ams.base.information.modules.sys.acctype.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;


public class AccTypeSqlBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(AccTypeColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(AccTypeTableName.userInfo);
		//return dbnameresolver.getTableName(AccTypeTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(AccTypeTableName.userInfo);
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from V_S_DAT_ASS_TYPE a  ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select a.* from V_S_DAT_ASS_TYPE a  ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			buf.append("  order by a.n_order  ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DAT_CODE")) {
				valueFieldbuf.append(" a.C_DAT_CODE like ?  AND ");
			} else if (fieldedName.equals(("C_DV_NAME"))) {
				valueFieldbuf.append("  a.C_DAT_NAME like ? AND ");
			} else if (fieldedName.equals("N_ORDER")) {
				valueFieldbuf.append(" a.N_ORDER like ? AND ");
			} else if (fieldedName.equals("C_DAT_CODE_P")) {
				valueFieldbuf.append("a.C_DAT_CODE_P like ? AND ");
			} 
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的所有记录的sql查询语句
	 * return String类型的sql查询语句
	 */
	public String getAllDataSql(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 根据资产类型代码C_DAT_CODE获取所有符合条件的数据
	 * @return 获取符合条件的数据的SQL语句
	 */
	public String getAllDataSqlByKeys(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		buf.append(" where ");
		buf.append(" C_DAT_CODE in ");
		buf.append(" (SELECT * FROM TABLE(?))");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的所有记录的sql查询语句
	 * @param buf
	 */
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append("select a.* "); 
		buf.append("from V_S_DAT_ASS_TYPE a");
	}
	
	/**
	 * 获取资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的记录的sql语句
	 * @return 资产类型字典V_S_DAT_ASS_TYPE的指定的资产类型代码C_DAT_CODE的记录的sql语句
	 */
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_DAT_CODE = ? ");
//		buf.append(" (SELECT * FROM TABLE(?))");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
//	
	
	/**
	 * 根据资产类型代码C_DAT_CODE获取所有符合条件的数据
	 * @return sql语句
	 */
	public String getDataByCodes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_DAT_CODE in ( ");
		buf.append(" select * from TABLE(?))");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(AccTypeTableName.userInfo);
	}
	
	/**根据类型获取相应资产类型数据
	 * @return 获取类型相关的资产类型的sql语句
	 */
	public String getDataByTypes() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_DAT_TYPE in ( ");
		buf.append(" select * from TABLE(?)) ");
		buf.append(" order by a.n_order ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
}
