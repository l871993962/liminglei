package com.yss.uco.elecreco.er.reverse.compare.gz.dao;

import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareSqlBuilder;

public class GzDataCompareSqlBuilder extends DataCompareSqlBuilder {

	@Override
	public String getInnerDataSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT A.* FROM T_D_ER_GZ A ");
		sb.append(" where A.C_SN = ? ");
		return sb.toString();
	}

	@Override
	public String getOutDataSql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteInnerDataSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" DELETE FROM T_D_ER_GZ A ");
		sb.append(" where A.C_ASS_CODE = (select C_ASS_CODE from T_P_AB_PORT where C_PORT_CODE = ?) ");
		sb.append(" and A.D_START_DATE <= to_date(?, 'yyyy-MM-dd') ");
		sb.append(" and A.D_END_DATE >= to_date(?, 'yyyy-MM-dd') ");
		sb.append(" and A.C_DV_ER_WAY = ? ");
		sb.append(" and A.C_FILE_TYPE = ? ");
		sb.append(" and A.C_RPT_TYPE = ? ");
		return sb.toString();
	}

	@Override
	public String getUpdateReportWaySql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" update T_D_ER_GZ A ");
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
