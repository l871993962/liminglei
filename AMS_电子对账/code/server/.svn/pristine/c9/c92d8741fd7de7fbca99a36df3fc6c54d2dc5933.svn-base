package com.yss.uco.elecreco.support.dzdz.bus.km;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.uco.elecreco.support.dzdz.bus.km.pojo.ErKmColumnName;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;

public class KmSqlBuilder implements IRecordSqlBuilder{
	public String getRecordSql() {
		StringBuffer buf = new StringBuffer();
		////广发证券 科目名称太长 客户系统存不进去
		buf.append(" SELECT C_KM_CODE,substr(C_KM_NAME,0,25) AS C_KM_NAME,C_KM_LEVEL,C_KM_CODE_P,N_DETAIL, C_DV_KM_CLS,C_DV_JD_WAY");
		///by weijj 20151027 bug121159 
		//		buf.append(getC_DV_KM_CLS()).append(",").append(getC_DV_JD_WAY());
		buf.append(",D_DATE, N_DETAIL");
		// //By Jinghehe 2015-8-15 工行对账模式修改
		//wlx 20161116 BUG145118工商银行电子对账问题 不对工行对账模式特殊处理
//		buf.append(" FROM (select C_IDEN,C_SN,C_DV_ER_WAY,C_ASS_CODE,C_FILE_TYPE,C_RPT_TYPE,");
//		buf.append(" D_START_DATE,D_END_DATE,C_KM_CODE,C_KM_NAME,C_KM_LEVEL,C_KM_CODE_P,");
//		buf.append(" C_DV_JD_WAY,C_DV_KM_CLS,D_DATE,case when 'DZMS_GHMS' = ? then 1 else N_DETAIL end as N_DETAIL ");
//		buf.append(" from T_D_ER_KM where c_km_code in (select a.c_km_code_p FROM T_D_ER_KM A ");
//		buf.append(" WHERE A.C_ASS_CODE = ? AND A.C_SN = ?  and a.n_detail = 1) ");
//		buf.append(" and C_ASS_CODE = ? AND C_SN = ?  ");
//		buf.append(" union all ");
//		buf.append(" select C_IDEN,C_SN,C_DV_ER_WAY,C_ASS_CODE,C_FILE_TYPE,C_RPT_TYPE, ");
//		buf.append(" D_START_DATE,D_END_DATE,C_KM_CODE,C_KM_NAME,C_KM_LEVEL,C_KM_CODE_P, ");
//		buf.append(" C_DV_JD_WAY,C_DV_KM_CLS,D_DATE,case when 'DZMS_GHMS' = ? then 0 else N_DETAIL end as N_DETAIL ");
//		buf.append(" from T_D_ER_KM where c_km_code not in (select a.c_km_code_p FROM T_D_ER_KM A ");
//		buf.append(" WHERE A.C_ASS_CODE = ? AND A.C_SN = ?  and a.n_detail = 1) ");
//		buf.append(" and C_ASS_CODE = ? AND C_SN = ?) A where  A.C_ASS_CODE = ? AND A.C_SN = ? order by C_KM_CODE  ");
		
		buf.append(" FROM T_D_ER_KM A WHERE  A.C_ASS_CODE = ? AND A.C_SN = ? ORDER BY C_KM_CODE  ");
		return buf.toString();
	}
//	private String getC_DV_KM_CLS(){
//		StringBuffer buf = new StringBuffer("CASE");
//		buf.append(" WHEN C_DV_KM_CLS = 'KC_ZC' THEN '1'");
//		buf.append(" WHEN C_DV_KM_CLS = 'KC_FZ' THEN '2'");
//		buf.append(" WHEN C_DV_KM_CLS = 'KC_GT' THEN '3'");
//		buf.append(" WHEN C_DV_KM_CLS = 'KC_QY' THEN '4'");
//		buf.append(" WHEN C_DV_KM_CLS = 'KC_SY' THEN '5'");
//		buf.append(" ELSE '6'");
//		buf.append(" END AS C_DV_KM_CLS ");
//		return buf.toString();
//	}

//	private String getC_DV_JD_WAY(){
//		StringBuffer buf = new StringBuffer("CASE");
//		buf.append(" WHEN C_DV_JD_WAY = 'JD_J' THEN '1'");
//		buf.append(" WHEN C_DV_JD_WAY = 'JD_D' THEN '-1'");
//		buf.append(" ELSE '0'");
//		buf.append(" END AS C_DV_JD_WAY ");
//		return buf.toString();
//	}
	
	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(ErKmColumnName.valueOf(name));
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
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
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
