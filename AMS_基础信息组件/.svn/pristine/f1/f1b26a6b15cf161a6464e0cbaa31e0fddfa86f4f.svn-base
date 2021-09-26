package com.yss.ams.base.information.modules.sys.convertdict.zdorg.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class ZdOrgSqlBuilder implements SQLBuilder {

	@Override
	public String buildDeleteSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildInsertSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbnameresolver, String s) {
		return dbnameresolver.getColumnName(ZdOrgColumnName
				.valueOf(s));
	}

	@Override
	public String getLogSequenceName(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return arg0.getLogSequenceName(ZdOrgTableName.corporg);
	}

	@Override
	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> arg0) throws Exception {
	    StringBuffer buf = new StringBuffer();
	    buf.append("select a.* from T_V_D_GROUP a  ");
	    //STORY #76421 嘉实基金-紧急-运营平台推送流水、参数等、行情
	    buf.append("  where a.N_CHECK_STATE >= 0");	    
	    for (String fieldName : arg0) {
			if ("ARRAY_C_IDEN".equals(fieldName)) {
				buf.append(" AND A.C_IDEN IN (SELECT * FROM TABLE(?))  ");
			}else if ("C_GROUP_CODE".equals(fieldName)) {
				buf.append(" AND A.C_GROUP_CODE = ? ");
			}
		}
	    buf.append("  start with a.C_GROUP_CODE_P ='[root]' ");
	    buf.append(" connect by prior a.C_GROUP_CODE = a.C_GROUP_CODE_P order by a.N_CHECK_STATE ASC  ");

	    return buf.toString();
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ZdOrgTableName.recycle);
	}

	@Override
	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ZdOrgTableName.corporg);
	}
	
	/**
	 * 组织架构查询数据sql拼接（树形结构）
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getTreeViewQuerySql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT A.*, A.C_GROUP_CODE AS NODECODE, A.C_GROUP_CODE_P AS FPARAENTCODE, 1 AS N_LEVEL ");
		buf.append("   FROM T_V_D_GROUP A WHERE A.N_CHECK_STATE >= 0 START WITH A.C_GROUP_CODE_P = '[root]' ");
		buf.append(" CONNECT BY PRIOR A.C_GROUP_CODE = A.C_GROUP_CODE_P ORDER BY A.N_CHECK_STATE ASC ");
		return buf.toString();
	}
	
	/**
	 * 是否有根节点SQL
	 * @return
	 */
	public String getCheckHasRootNode(){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" SELECT COUNT(*) AS CNT ");
		sqlBuff.append("     FROM T_V_D_GROUP  ");
		sqlBuff.append("  WHERE C_GROUP_CODE_P = '[root]'  ");
		return sqlBuff.toString();
	}

}
