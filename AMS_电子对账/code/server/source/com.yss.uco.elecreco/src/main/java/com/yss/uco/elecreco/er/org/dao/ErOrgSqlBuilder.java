package com.yss.uco.elecreco.er.org.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class ErOrgSqlBuilder implements SQLBuilder {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取托管人机构
	 * @return
	 */
	public String getTrusteeOrgs()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.* FROM ( ");
		sb.append(" SELECT A.* ");
		sb.append("   FROM T_P_BI_ORG A ");
		sb.append("  START WITH A.C_ORG_CODE IN ");
		sb.append("             (SELECT C_ORG_CODE ");
		sb.append("                FROM (select a.* ");
		sb.append("                        from T_P_BI_ORG a ");
		sb.append("                       where ( ");
		sb.append("                             a.C_DV_TRUSTEE = 'TRUSTEE' OR ");
		sb.append("                             a.C_DV_TRUSTEE_SEC = 'TRUSTEE_SEC' OR ");
		sb.append("                             a.C_DV_TRUSTEE_MA = 'TRUSTEE_MA' ) ");
		sb.append("                       order by a.N_CHECK_STATE)) ");
		sb.append(" CONNECT BY PRIOR A.C_ORG_CODE = A.C_ORG_CODE_P ");
		sb.append(" ) A ");
		sb.append(" WHERE A.N_CHECK_STATE = 1 ");
		return sb.toString();
	}
	
	/**
	 * 获取管理人机构
	 * @return
	 */
	public String getManagerOrgs()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.* FROM ( ");
		sb.append(" SELECT A.* ");
		sb.append("   FROM T_P_BI_ORG A ");
		sb.append("  START WITH A.C_ORG_CODE IN ");
		sb.append("             (SELECT C_ORG_CODE ");
		sb.append("                FROM (select a.* ");
		sb.append("                        from T_P_BI_ORG a ");
		sb.append("                       where a.C_DV_MANAGER = 'MANAGER' ");
		sb.append("                       order by a.N_CHECK_STATE)) ");
		sb.append(" CONNECT BY PRIOR A.C_ORG_CODE = A.C_ORG_CODE_P ");
		sb.append(" ) A ");
		sb.append(" WHERE A.N_CHECK_STATE = 1 ");
		return sb.toString();
	}

}
