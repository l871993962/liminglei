package com.yss.ams.product.information.modules.aa.portcustom.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * <用户自定义组合>SQL语句构造类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortCustomSqlBuilder implements SQLBuilder {

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
		return dbNameResolver
				.getLogSequenceName(PortCustomTableName.portCustom);
	}

	public String getColumnNameByProperty(DBNameResolver dbNameRsv,
			String propName) {
		return dbNameRsv.getColumnName(PortCustomColumnName.valueOf(propName));
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(PortCustomTableName.recycle);
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

	/**
	 * 
	 * @param bool
	 *            是否根据资产类型过滤
	 * @return
	 */
	public String getPortCodeByMenuidSql(boolean bool) {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT DISTINCT x.* FROM (");
		getCommonQuerySqlBuf(buf);
		buf.append(" WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
		buf.append(" AND C_USER_CODE = ? ");
		buf.append(" AND C_POST_CODE = ? ");
		buf.append(" AND C_FUN_CODE = ? ");
		if (bool) {
			buf.append(" AND C_TR_CODE_R = ? ");
		}
		buf.append(" UNION ALL ");
		getCommonQuerySqlBuf(buf);
		buf.append(" WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
		buf.append(" AND C_USER_CODE = ? ");
		buf.append(" AND C_POST_CODE = ? ");
		buf.append(" AND C_FUN_CODE = ' '");
		if (bool) {
			buf.append(" AND C_TR_CODE_R = ? ");
		}
		buf.append(" ) x ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	// public String getPortCodeByMenuidSql(){
	// String sql = "";
	// StringBuffer buf = new StringBuffer();
	// getCommonQuerySqlBuf(buf);
	//		
	// buf.append(" WHERE C_PORT_CODE = ? ");
	// buf.append(" AND C_USER_CODE = ? ");
	// buf.append(" AND C_POST_CODE = ? ");
	// buf.append(" AND C_FUN_CODE = ? ");
	//		
	// sql = buf.toString();
	// StringUtil.clearStringBuffer(buf);
	// return sql;
	// }
	//	
	// public String getPortCodeSql(){
	// String sql = "";
	// StringBuffer buf = new StringBuffer();
	// getCommonQuerySqlBuf(buf);
	//		
	// buf.append(" WHERE C_PORT_CODE = ? ");
	// buf.append(" AND C_USER_CODE = ? ");
	// buf.append(" AND C_POST_CODE = ? ");
	// buf.append(" AND C_FUN_CODE = ' ' "); //解决Bug8163 by xzl
	//		
	// sql = buf.toString();
	// StringUtil.clearStringBuffer(buf);
	// return sql;
	// }

	public String getPortCodesSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);

		buf.append(" WHERE C_PORT_CODE = ? ");
		buf.append(" AND C_USER_CODE = ? ");
		buf.append(" AND C_POST_CODE = ? ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getAssSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append("select C_TR_CODE,");
		buf.append(" C_TR_NAME ");
		buf.append(" from T_P_AB_ASS_TR ");
		buf.append(" where N_CHECK_STATE = 1 ");
		buf.append(" and C_TR_CODE_P = '[root]' ");
		buf.append(" and (c_user is null or c_user = ?) ORDER BY N_ORDER ");//BUG #162973 by liulei
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getAssOnlyCodeSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("select C_TR_CODE,");
		buf.append(" C_TR_NAME ");
		buf.append(" from T_P_AB_ASS_TR ");
		buf.append(" where N_CHECK_STATE = 1 ");
		buf.append(" and C_TR_CODE_P = '[root]' ORDER BY N_ORDER ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getShowTypeSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append("SELECT C_TR_CODE_R FROM T_P_AB_PORT_CUSTOM ");
		buf.append(" WHERE ");
		buf.append(" C_USER_CODE = ? ");
		buf.append(" AND C_FUN_CODE = ? ");
		buf.append("  ORDER BY TO_NUMBER(C_IDEN) ");
		/*
		 * Author : ChenLong
		 * Date   : 2015-01-22
		 * Status : Add
		 * Comment: 单岗位改成多岗位时  资产池不再需要依据岗位 故这里注销岗位
		 * */
		
//		buf.append(" ANDC_POST_CODE = ?");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;

	}

	private void getCommonQuerySqlBuf(StringBuffer buf) {
		buf.append(" SELECT *");
		buf.append(" FROM T_P_AB_PORT_CUSTOM ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver
				.getLogSequenceName(PortCustomTableName.portCustom);
	}

}
