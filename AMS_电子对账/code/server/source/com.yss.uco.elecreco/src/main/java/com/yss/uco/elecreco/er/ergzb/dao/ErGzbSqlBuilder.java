package com.yss.uco.elecreco.er.ergzb.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class ErGzbSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(ErGzbColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErGzbTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErGzbTableName.gzInfo);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_GZ A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
//		buf.append(" select a.* from (select distinct a.* from (select a.*,b.C_KM_CODE_P,' ' as navtype from T_D_ER_GZ a ");
//		buf.append(" join(select C_ASS_CODE,D_START_DATE,D_END_DATE,C_KM_CODE,C_KM_CODE_P,C_KM_NAME FROM T_D_ER_KM)b ");
//		buf.append("  on b.C_ASS_CODE = a.C_ASS_CODE and a.D_START_DATE = b.D_START_DATE and a.C_KM_CODE = b.C_KM_CODE");
//		if (valueFieldbuf.length() > 0) {
//			buf.append("  WHERE ").append(valueFieldbuf);
//		}
//		buf.append(" )a  start with a.C_KM_CODE_P = '[root]' connect by prior a.C_KM_CODE = a.C_KM_CODE_P order siblings by a.C_KM_CODE)a ");
//		buf.append(" union all  ");
//		buf.append(" select a.* from(select a.C_FILE_TYPE,a.C_ASS_CODE,a.C_RPT_TYPE,a.D_START_DATE,a.D_END_DATE, ");
//		buf.append(" b.C_ZB_NAME as C_KM_CODE,case when a.C_KM_NAME = 'HJ' then '' else a.C_KM_NAME end as C_KM_NAME, ");
//		buf.append("  a.N_VA_PRICE,a.N_QUOT_LOGO,a.N_AMOUNT, a.N_PORT_COST,a.N_PORT_MV, a.N_PORT_IV, a.C_CB_JZ_BL,a.C_SZ_JZ_BL,a.N_DETAIL,a.c_sn, ");
//		buf.append("  '[root]' as C_KM_CODE_P,case when a.C_KM_NAME = 'HJ' then 'TOTAL' else 'TOTAL_ALL' end as navtype ");
//		buf.append(" from T_D_ER_GZ a ");
//		buf.append("  join (select C_ZB_CODE, C_ZB_NAME from t_z_bi_rela) b on b.C_ZB_CODE = a.C_KM_CODE ) a ");
		
		buf.append("SELECT A.*, PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE,PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_CODE) - 1)AS C_KM_CODE_P FROM T_D_ER_GZ A ");
		
		if (valueFieldbuf.length() > 0) {
			buf.append("  WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY DECODE(A.C_NAV_TYPE,'TOTAL',2,'TOTAL_ALL',3,1)asc ,C_KM_CODE asc ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_SN")) {
				valueFieldbuf.append(" A.C_SN = ? AND ");
			}else if(fieldedName.equalsIgnoreCase("C_ASS_CODE")){
				valueFieldbuf.append(" A.C_ASS_CODE = ? AND ");
			}else if("C_RPT_TYPE".equalsIgnoreCase(fieldedName)){ // 补参数字段 byleeyu20160623
				valueFieldbuf.append(" A.C_RPT_TYPE = ? AND ");
			}else if("ARRAY_C_KM_CODE".equalsIgnoreCase(fieldedName))
			{
				valueFieldbuf.append(" A.C_KM_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/**
	 * 拼接电子对账估值表删除sql
	 * @return
	 */
	public String getDeleteSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" delete from T_D_ER_GZ a ");
		buf.append(" where a.C_SN = ? and a.C_ASS_CODE = ? and a.C_FILE_TYPE = ? and a.c_Rpt_Type = ? ");
		return buf.toString();
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
