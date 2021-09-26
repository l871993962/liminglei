package com.yss.uco.elecreco.er.reverse.compare.ye.dao;

import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareSqlBuilder;

public class YeDataCompareSqlBuilder extends DataCompareSqlBuilder {
	
	private final static String INNER_TABLE = "T_D_ER_YE";
	private final static String OUT_TABLE = "T_D_OD_YE_OUT";

	@Override
	public String getInnerDataSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.* from ").append(INNER_TABLE).append(" A ");
		sb.append(" where A.C_SN = ? ");
		return sb.toString();
	}

	@Override
	public String getOutDataSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.* from ").append(OUT_TABLE).append(" A ");
		sb.append(" where A.C_ASS_CODE = ? ");
		sb.append(" and A.C_TGH_CODE = ? ");
		sb.append(" and A.D_GZ_DATE = to_date(?,'yyyy-MM-dd') ");
		return sb.toString();
	}

	@Override
	public String getDeleteInnerDataSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from ").append(INNER_TABLE);
		sb.append(" where C_ASS_CODE in (SELECT C_ASS_CODE FROM T_P_AB_PORT where C_PORT_CODE = ? ) ");
		sb.append(" and C_FILE_TYPE = ? ");
		sb.append(" and C_RPT_TYPE = ? ");
		sb.append(" and C_DV_ER_WAY = ? ");
		sb.append(" and D_START_DATE = to_date(?,'yyyy-MM-dd') ");
		return sb.toString();
	}

	@Override
	public String getUpdateReportWaySql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" update T_D_ER_YE A ");
		sb.append(" set A.C_DV_ER_WAY = ? ");
		sb.append(" where A.C_SN = ? ");
		sb.append(" and A.C_ASS_CODE = ( SELECT C_ASS_CODE FROM T_P_AB_PORT where C_PORT_CODE = ? ) ");
		sb.append(" and A.D_START_DATE <= to_date( ? , 'yyyy-MM-dd') ");
		sb.append(" and A.D_END_DATE >= to_date( ? , 'yyyy-MM-dd') ");
		sb.append(" and A.C_FILE_TYPE = ? ");
		sb.append(" and A.C_RPT_TYPE = ? ");
		return sb.toString();
	}

}
