package com.yss.ifa.szt.tool.rptlog.dao;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class ErRptLogSqlBuilder implements SQLBuilder  {

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,String s) {
		return dbnameresolver.getColumnName(ErRptLogColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErRptLogTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErRptLogTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErRptLogTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_RPT_LOG A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select C_IDEN, C_SN, case when C_FILE_TYPE = '1022' then '对账结果' else '发送结果' end as C_FILE_TYPE, C_ASS_CODE, C_RPT_TYPE, C_SRC_USERID, C_SRC_APPID, C_PKG_PSD, C_DEPT_CODE, C_CERT_ID, C_SECRETKEY, C_DV_SECRETTYPE, C_DV_CHARSET, C_TARGET_USERID, C_TARGET_APPID, C_DV_LOG_TYPE, D_HANDLE_TIME, C_ERR_INFO, C_PKG_ID from T_D_ER_RPT_LOG A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.D_HANDLE_TIME DESC  ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_SN")) {
				valueFieldbuf.append(" A.C_SN = ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_ASS_CODE")) {
				valueFieldbuf.append(" A.C_ASS_CODE = ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("ARRAY_C_FILE_TYPE")) {
				valueFieldbuf.append(" A.C_FILE_TYPE in (select * from table(?)) AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_RPT_TYPE")) {
				valueFieldbuf.append(" A.C_RPT_TYPE = ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_BEGIN_DATE")) {
				valueFieldbuf.append(" A.C_BEGIN_DATE = ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_DV_LOG_TYPE")) {
				valueFieldbuf.append(" A.C_DV_LOG_TYPE = ? AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getDeleteRptLogSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from T_D_ER_RPT_LOG A ");
		sb.append(" where A.D_HANDLE_TIME < to_date(?,'yyyyMMdd') ");
		return sb.toString();
	}

	public String getInsertSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into T_D_ER_RPT_LOG (C_IDEN, C_ASS_CODE,C_CERT_ID,C_DEPT_CODE,C_DV_CHARSET,C_DV_LOG_TYPE,C_DV_SECRETTYPE,C_ERR_INFO,C_FILE_TYPE,C_PKG_PSD,C_RPT_TYPE,C_SN,C_SRC_APPID,C_SRC_USERID,C_TARGET_APPID,C_TARGET_USERID,D_HANDLE_TIME,C_DECRYPT_LOG,C_ENCRYPT_LOG,C_PKG_ID) ");
		sb.append(" values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		return sb.toString();
	}
	
	public String getInsertSqlNoClob() {
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into T_D_ER_RPT_LOG (C_IDEN, C_ASS_CODE,C_CERT_ID,C_DEPT_CODE,C_DV_CHARSET,C_DV_LOG_TYPE,C_DV_SECRETTYPE,C_ERR_INFO,C_FILE_TYPE,C_PKG_PSD,C_RPT_TYPE,C_SN,C_SRC_APPID,C_SRC_USERID,C_TARGET_APPID,C_TARGET_USERID,D_HANDLE_TIME,C_DECRYPT_LOG,C_ENCRYPT_LOG,C_PKG_ID) ");
		sb.append(" values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,empty_clob(),empty_clob(),?) ");
		return sb.toString();
	}

	public String queryLogByIdSql()
	{
		return "select C_DECRYPT_LOG from T_D_ER_RPT_LOG where C_IDEN = ? ";
	}

	public String getUpdateSNSql() {
		return "update T_D_ER_RPT_LOG set C_SN = ? where C_SN = ? and C_FILE_TYPE = '1022' ";
	}

}