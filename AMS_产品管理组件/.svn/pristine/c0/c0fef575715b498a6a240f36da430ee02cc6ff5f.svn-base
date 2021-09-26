package com.yss.ams.product.information.modules.cp.pubacc.dao;



import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 公用账户信息设置sql拼接处理类
 * 
 * @author chenyoulong
 * @version v1.0.0.4
 */
public class PubAccSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(PubAccColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(PubAccTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(PubAccTableName.pubAcc);
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_C_CP_PUB_ACC A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_C_CP_PUB_ACC A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.N_CHECK_STATE,A.C_UPDATE_TIME  ");

		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_OPEN_ACC_NAME")) {
				valueFieldbuf.append(" A.C_OPEN_ACC_NAME LIKE ?  AND ");
			} else if (fieldedName.equals("C_DC_CODE")) {
				valueFieldbuf.append(" A.C_DC_CODE = ? AND ");
			} else if (fieldedName.equals("N_STATE")) {
				valueFieldbuf.append(" A.N_CHECK_STATE = ? AND ");
			} else if (fieldedName.equals("C_OPEN_NAME")) {
				valueFieldbuf.append(" A.C_OPEN_ACC_NAME = ? AND ");
			} else if (fieldedName.equals("C_DV_OPPO_RELA")) {
				valueFieldbuf.append(" A.C_DV_OPPO_RELA = ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append(" a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("C_ORG_CODE")) {
				valueFieldbuf.append(" a.C_IDEN in (select c_rela_code from T_P_BI_ORG_RELA where c_org_code = ? AND C_RELA_TYPE = 'pubAccInfo') AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(PubAccTableName.pubAcc);
	}

	private String commonSql() {
		return " select A.* from T_C_CP_PUB_ACC A ";
	}
	private String orderSql(){
		return " ORDER BY A.N_CHECK_STATE,A.C_UPDATE_TIME ";
	}
	public String getAllDataSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(orderSql());
		return buf.toString();
	}

	public String getDataByCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(" where A.C_OPEN_ACC_NO = ? ");
		buf.append(orderSql());
		return buf.toString();
	}
	
	public String getAllDataSqlByKeys() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(" where A.C_OPEN_ACC_NO = in (select * from table(?)) ");
		buf.append(orderSql());
		return buf.toString();
	}

	public String getDataListByTypes() {
		StringBuffer buf = new StringBuffer();
		buf.append(commonSql());
		buf.append(" where A.C_OPEN_ACC_NO in (select * from table(?)) ");
		buf.append(orderSql());
		return buf.toString();
	}

}
