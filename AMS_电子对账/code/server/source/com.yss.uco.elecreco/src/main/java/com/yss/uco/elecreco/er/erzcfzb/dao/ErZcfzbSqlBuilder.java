package com.yss.uco.elecreco.er.erzcfzb.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class ErZcfzbSqlBuilder implements SQLBuilder {

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
		return dbNameResolver.getColumnName(ErZcfzbTableName.zcfzInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return dbNameResolver.getColumnName(ErZcfzbColumnName.valueOf(name));
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
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT A.C_IDEN,C_FILE_TYPE,A.C_ASS_CODE,A.C_RPT_TYPE,A.D_START_DATE,A.D_END_DATE, ");
		buf.append(" '' AS C_DEPT_CODE,'' AS C_CERT_ID, A.C_SN, A.C_INDEX_CODE, ");
		buf.append(" A.C_INDEX_NAME,A.N_BEGIN_VALUE,A.N_END_VALUE FROM T_D_ER_ZCFZ A ");
//		buf.append(" B.C_ZB_NAME AS C_INDEX_NAME,A.N_BEGIN_VALUE,A.N_END_VALUE FROM T_D_ER_ZCFZ A ");
//		buf.append(" LEFT JOIN (SELECT C_DZ_CODE, C_ZB_CODE, C_ZB_NAME, ROW_NUMBER() OVER(PARTITION BY C_DZ_CODE, C_ZB_CODE ORDER BY C_ZB_NAME DESC) RANK FROM T_Z_BI_RELA ) B ");
//		buf.append(" ON A.C_FILE_TYPE = B.C_DZ_CODE AND B.C_ZB_CODE = A.C_INDEX_CODE AND B.RANK=1 ");
//		buf.append(" LEFT JOIN ( ");
//		buf.append(" SELECT C_DEPT_CODE,C_CERT_ID,C_ASS_CODE FROM T_D_ER_RELA R1 ");
//		buf.append(" JOIN (SELECT R.C_RELA_CODE, P.C_ASS_CODE AS C_ASS ");		  
//		buf.append(" FROM T_P_AB_PORT_RELA R ");
//		buf.append(" JOIN T_P_AB_PORT P ON R.C_PORT_CODE = P.C_PORT_CODE ");
//		buf.append(" AND R.C_RELA_TYPE = 'RELA_ORG') P1 ");
//		buf.append(" ON P1.C_RELA_CODE = R1.C_TGH_CODE) C ON A.C_ASS_CODE = C.C_ASS_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.C_INDEX_CODE");
		return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_SN")) {
				valueFieldbuf.append(" A.C_SN = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_ASS_CODE")) {
				valueFieldbuf.append(" A.C_ASS_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_RPT_TYPE")) {
				valueFieldbuf.append(" A.C_RPT_TYPE = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getKmSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.C_IDEN,C_FILE_TYPE,A.C_ASS_CODE,A.C_RPT_TYPE,A.D_START_DATE,A.D_END_DATE, ");
		buf.append(" C.C_ORG_CODE AS C_DEPT_CODE,C.C_DV_ZB_CODE AS C_CERT_ID, A.C_SN, A.C_INDEX_CODE, ");
		buf.append(" A.C_INDEX_NAME,A.N_BEGIN_VALUE,A.N_END_VALUE FROM T_D_ER_ZCFZ A ");
//		buf.append(" B.C_ZB_NAME AS C_INDEX_NAME,A.N_BEGIN_VALUE,A.N_END_VALUE FROM T_D_ER_ZCFZ A ");
//		buf.append(" LEFT JOIN (SELECT C_DZ_CODE, C_ZB_CODE, C_ZB_NAME FROM T_Z_BI_RELA) B ");
//		buf.append(" ON A.C_FILE_TYPE = B.C_DZ_CODE AND B.C_ZB_CODE = A.C_INDEX_CODE ");
		buf.append(" LEFT JOIN (SELECT C_ZB_CODE, C_ZB_NAME, C_DV_ZB_CODE, C_ORG_CODE,C_DZ_CODE FROM T_Z_BI_RELA) C ");
		buf.append(" ON A.C_INDEX_CODE = C.C_ZB_CODE AND A.C_FILE_TYPE = C.C_DZ_CODE ");
		buf.append(" WHERE A.C_SN = ? AND A.C_RPT_TYPE = ?");
		return buf.toString();
	}
	
}
