package com.yss.uco.elecreco.er.erkmb.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class ErKmbSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(ErKmbColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErKmbTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErKmbTableName.kmInfo);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from tdzaccount A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
//		buf.append(" select A.* from(select distinct A.*,case when FACCTCLASS = '1' then '资产类' " );
//		buf.append(" when FACCTCLASS = '2' then '负债类' when FACCTCLASS = '3' then '共同类'");
//		buf.append(" when FACCTCLASS = '4' then '权益类' when FACCTCLASS = '5' then '损益类'");
//		buf.append(" else '表外类' end as FACCTCLASSNAME,case when FBALDC = '1' then '借' ");
//		buf.append(" when FBALDC = '-1' then '贷' else '平' end as FBALDCNAME ");
//		buf.append(" from tdzaccount A ");
		buf.append(" SELECT C_DV_ER_WAY,C_KM_LEVEL,C_FILE_TYPE,C_KM_CODE,C_SN,D_START_DATE,N_DETAIL,  ");
		buf.append(" C_ASS_CODE,D_DATE,D_END_DATE,C_IDEN,C_DV_JD_WAY,C_RPT_TYPE,C_KM_NAME,C_DV_KM_CLS,C_KM_CODE_P FROM T_D_ER_KM A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
//		buf.append(" ) a start with a.facctparent = '[root]' connect by prior  a.facctcode = a.facctparent order siblings by a.facctcode");
		buf.append(" ORDER BY A.C_KM_CODE ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_SN")) {
				valueFieldbuf
						.append(" A.C_SN = ? AND ");
			}else if(fieldedName.equalsIgnoreCase("C_ASS_CODE")){
				valueFieldbuf.append(" A.C_ASS_CODE = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/**
	 * 拼接电子对账科目表删除sql
	 * @return
	 */
	public String getDeleteSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" delete from T_D_ER_KM a ");
		buf.append("  where a.C_SN = ? and a.C_ASS_CODE = ? and a.C_FILE_TYPE = ? and a.c_Rpt_Type = ? ");
		return buf.toString();
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
