package com.yss.ams.base.information.modules.bi.accountTreeA.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class AccountTreeASqlBuilder implements SQLBuilder {

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
		return dbNameResolver.getTableName(AccountTreeATableName.accountTreeA);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(AccountTreeAColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(AccountTreeATableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_P_BI_ACC_TREE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) from T_P_BI_ACC_TREE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_NODE_CODE")) {
				valueFieldbuf.append(" A.C_NODE_CODE =? AND ");
			}else if(fieldedName.equals("ARRAY_C_NODE_CODE")){
				valueFieldbuf.append(" A.C_NODE_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if(fieldedName.equals("C_POST_CODE")){
				valueFieldbuf.append(" A.C_POST_CODE LIKE '%'||?||'%' AND ");
			}else if(fieldedName.equals("C_NODE_CODE_P")){
				valueFieldbuf.append(" a.C_NODE_CODE_P in ( ");
				valueFieldbuf.append(" select c_node_code from T_P_BI_ACC_TREE t ");
				valueFieldbuf.append(" start with t.C_NODE_CODE = ? ");
				valueFieldbuf.append(" connect by prior t.c_node_code=t.c_node_code_P  ");
				valueFieldbuf.append(" )  AND ");
			}else if (fieldedName.equals("C_NODE_CODE_P_ROOT")) {
				valueFieldbuf.append(" A.C_NODE_CODE_P = ?  AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getAllDataSql() {
		// TODO Auto-generated method stub
		return null;
	}

}
