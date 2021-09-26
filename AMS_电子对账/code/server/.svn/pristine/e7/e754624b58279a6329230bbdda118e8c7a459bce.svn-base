package com.yss.uco.elecreco.er.erdblgz.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class ErDblgzbSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(ErDblgzbColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErDblgzbTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErDblgzbTableName.gzInfo);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_DBLGZ A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		
		buf.append("SELECT A.* FROM T_D_ER_DBLGZ A ");
		
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
		buf.append(" delete from T_D_ER_DBLGZ a ");
		buf.append(" where a.C_SN = ? and a.C_ASS_CODE = ? and a.C_FILE_TYPE = ? and a.c_Rpt_Type = ? ");
		return buf.toString();
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErDblgzbTableName.gzInfo);
	}

	public String getInsertSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" insert into T_D_ER_DBLGZ(C_IDEN,C_FILE_TYPE,C_ASS_CODE,C_RPT_TYPE,D_START_DATE,D_END_DATE,C_KM_CODE,C_KM_CODE_P,C_KM_NAME,N_VA_PRICE,N_QUOT_LOGO,N_AMOUNT,N_ORIG_COST,N_PORT_COST,N_ORIG_MV,N_PORT_MV,N_ORIG_IV,N_PORT_IV,N_CB_JZ_BL,N_SZ_JZ_BL,N_DETAIL,C_SN,C_DV_ER_WAY,C_NAV_TYPE,C_PORT_CLS_CODE,C_ZB_NAME,N_WAY) ");
		buf.append(" values(SEQU_D_ER_DBLGZ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		return buf.toString();
	}

}
