package com.yss.ams.base.information.modules.sys.accele.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class AccEleSqlBuilder implements SQLBuilder {

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
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(AccEleColumnName.valueOf(s));
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from T_S_DAE_ELEM a  ");
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
			buf.append(" select a.* from T_S_DAE_ELEM a  ");
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

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DAE_CODE")) {
				valueFieldbuf.append(" a.C_DAE_CODE like ?  AND ");
			} else if (fieldedName.equals(("C_DAE_NAME"))) {
				valueFieldbuf.append("  a.C_DAE_NAME like ? AND ");
			} else if (fieldedName.equals("C_DS_TYPE")) {
				valueFieldbuf.append(" a.C_DS_TYPE like ? AND ");
			} else if (fieldedName.equals("C_DAI_FIELD")) {
				valueFieldbuf.append(" a.C_DAI_FIELD like ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(AccEleTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(AccEleTableName.userInfo);
	}

	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getAllDataSqlByKeys() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		buf.append(" WHERE C_DAE_CODE IN (SELECT * FROM TABLE(?))");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_DAE_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_DAE_CODE IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" select a.* ");
		buf.append(" from T_S_DAE_ELEM a ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(AccEleTableName.userInfo);
	}

}
