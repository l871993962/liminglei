package com.yss.ams.product.information.modules.ab.port.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class PortFundRelaSqlBuilder implements SQLBuilder{

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(PortColumnName.valueOf(s));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return "T_P_AB_PORT_ACC_RELA";
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer whereSqlBuf = new StringBuffer();
		this.setWhereSQL(whereSqlBuf, paraNameList);
		buf.append(" SELECT PORT.C_IDEN,NVL(RELA.C_PORT_CODE,PORT.C_PORT_CODE) AS C_PORT_CODE, ");
		buf.append("        PORT.C_PORT_NAME,PORT.C_ASS_CODE,PORT.C_PORT_NAME_ST,PORT.C_PORT_NAME_EN, ");
		buf.append("        PORT.D_BUILD,D_CLOSE,D_CLEAR,C_DAT_CODE,C_DAT_CLS,C_DV_PROD_STATE, ");
		buf.append("        C_DC_CODE,C_DV_PORT_CODE,C_PORT_CODE_P,C_HDAY_CODE,C_DESC,PORT.N_CHECK_STATE, ");
		buf.append("        PORT.C_UPDATE_BY,PORT.C_UPDATE_TIME,PORT.C_CHECK_BY,PORT.C_CHECK_TIME ");
		buf.append("        FROM T_P_AB_PORT_ACC_RELA RELA ");
		buf.append("        LEFT JOIN T_P_AB_PORT PORT ON RELA.C_PORT_CODE = PORT.C_PORT_CODE ");
		if(whereSqlBuf.length() > 0){
			buf.append(" WHERE ").append(whereSqlBuf);
		}
		return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer whereSqlBuf = new StringBuffer();
		this.setWhereSQL(whereSqlBuf, paraNameList);
		buf.append(" SELECT COUNT(*) ");
		buf.append("        FROM T_P_AB_PORT_ACC_RELA RELA ");
		buf.append("        LEFT JOIN T_P_AB_PORT PORT ON RELA.C_PORT_CODE = PORT.C_PORT_CODE ");
		if(whereSqlBuf.length() > 0){
			buf.append(" WHERE ").append(whereSqlBuf);
		}
		return buf.toString();
	}
	
	public void setWhereSQL(StringBuffer whereSqlBuf,
			List<String> paraNameList) {
		if(paraNameList.contains("C_RELA_CODE")){
			whereSqlBuf.append(" RELA.C_RELA_CODE = ?  AND ");
		}
		
		if (whereSqlBuf.length() > 0) {
			StringUtil.delLastSplitMark(whereSqlBuf, " AND ");
		}
	}
	
	public String deletePortFundRela(){
		StringBuffer buf = new StringBuffer();
		buf.append(" DELETE FROM T_P_AB_PORT_ACC_RELA ");
		buf.append(" WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND C_RELA_CODE = ?");
		return buf.toString();
	}
}
