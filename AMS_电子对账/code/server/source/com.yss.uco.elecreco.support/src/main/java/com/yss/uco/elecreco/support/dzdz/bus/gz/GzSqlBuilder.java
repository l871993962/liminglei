package com.yss.uco.elecreco.support.dzdz.bus.gz;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.uco.elecreco.support.dzdz.bus.gz.pojo.ErGzbColumnName;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;

public class GzSqlBuilder implements IRecordSqlBuilder{
	public String getRecordSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT CASE WHEN LENGTH(A.C_KM_CODE) > 100 THEN  SUBSTR(A.C_KM_CODE, 1, 100)  ELSE A.C_KM_CODE END AS C_KM_CODE,");
		buf.append("A.C_KM_NAME,N_VA_PRICE,N_QUOT_LOGO,N_AMOUNT,N_PORT_COST,N_PORT_MV,N_PORT_IV");
		buf.append(",N_CB_JZ_BL, N_SZ_JZ_BL,N_DETAIL,C_PORT_CLS_CODE,C_SN ");
//		//wlx 20161116 BUG145118工商银行电子对账问题 不对工行对账模式特殊处理
//		////By Jinghehe 2015-8-15 工行对账模式修改
//		buf.append(" FROM ( SELECT C_IDEN,C_FILE_TYPE,C_ASS_CODE,C_RPT_TYPE,D_START_DATE,D_END_DATE, ");
//		buf.append(" C_KM_CODE,C_KM_NAME,N_VA_PRICE,N_QUOT_LOGO,N_AMOUNT,N_PORT_COST,N_PORT_MV, ");
//		buf.append(" N_PORT_IV,N_CB_JZ_BL,N_SZ_JZ_BL,case when 'DZMS_GHMS' = ? then 1 else N_DETAIL ");
//		buf.append(" end as N_DETAIL,C_SN,C_DV_ER_WAY FROM T_D_ER_GZ WHERE C_ASS_CODE = ? AND C_SN = ? ");
//		buf.append(" AND C_KM_CODE IN (select PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(a.c_km_code, PKG_FUN_GETKM_LEVEL.getkm_level(a.c_km_code) - 1) as c_km_code ");
//		buf.append(" from T_D_ER_GZ a where a.C_ASS_CODE = ? AND A.C_SN = ? and a.n_detail = 1) ");
//
//		buf.append(" UNION ALL ");
//
//		buf.append(" SELECT C_IDEN,C_FILE_TYPE,C_ASS_CODE,C_RPT_TYPE,D_START_DATE,D_END_DATE, ");
//		buf.append(" C_KM_CODE,C_KM_NAME,N_VA_PRICE,N_QUOT_LOGO,N_AMOUNT,N_PORT_COST,N_PORT_MV, ");
//		buf.append(" N_PORT_IV,N_CB_JZ_BL,N_SZ_JZ_BL,case when 'DZMS_GHMS' = ? then 0 else N_DETAIL ");
//		buf.append(" end as N_DETAIL,C_SN,C_DV_ER_WAY FROM T_D_ER_GZ WHERE C_ASS_CODE = ? AND C_SN = ? ");
//		buf.append(" AND C_KM_CODE NOT IN (select PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(a.c_km_code, PKG_FUN_GETKM_LEVEL.getkm_level(a.c_km_code) - 1) as c_km_code ");
//		buf.append(" from T_D_ER_GZ a where a.C_ASS_CODE = ? AND A.C_SN = ? and a.n_detail = 1)) A ");
		
		buf.append(" FROM T_D_ER_GZ A ");
		buf.append(" WHERE A.C_ASS_CODE = ? AND A.C_SN = ? ");
		///by weijj 20150928 bug119825 
//		buf.append(" AND (A.N_AMOUNT <> 0 or A.N_PORT_IV <> 0 or A.N_PORT_MV <> 0 or A.N_PORT_COST <> 0 ");
		///by weijj 20150928 bug119825 
//		buf.append(" or A.C_KM_CODE in (select c_zb_code from t_z_bi_rela where n_check_state = 1)) ");
		buf.append(" order by C_KM_CODE ");
		return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
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
		return dbNameResolver.getColumnName(ErGzbColumnName.valueOf(name));
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

	
}
