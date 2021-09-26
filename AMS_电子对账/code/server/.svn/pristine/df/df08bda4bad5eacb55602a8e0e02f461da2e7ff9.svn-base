package com.yss.uco.elecreco.er.eryeb.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class ErYebSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(ErYebColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErYebTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErYebTableName.yeInfo);
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_YE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT A.*, PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE,PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_CODE) - 1)AS C_KM_CODE_P FROM T_D_ER_YE A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY C_KM_CODE ");
//		Debug.println("余额 表：", buf.toString());
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_SN")) {
				valueFieldbuf.append(" A.C_SN = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_ASS_CODE")) {
				valueFieldbuf.append(" A.C_ASS_CODE = ? AND ");
			}else if("C_RPT_TYPE".equalsIgnoreCase(fieldedName)){ //BUG #144037 电子对账余额表，查询所有对账数据报错 edit by xuhanbing 2016-11-1
				valueFieldbuf.append(" A.C_RPT_TYPE = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/**
	 * 拼接电子对账余额表删除sql
	 * 
	 * @return
	 */
	public String getDeleteSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" delete from T_D_ER_YE a ");
		buf.append(" where a.C_SN = ? and a.C_ASS_CODE = ? and a.C_FILE_TYPE = ? and a.c_Rpt_Type = ?  ");
		return buf.toString();
	}

	public String getKmSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" select distinct a.c_km_code, c_km_name from T_D_ER_YE a ");
		//STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。
		//buf.append(" LEFT JOIN (SELECT DISTINCT C_KM_CODE, C_KM_NAME FROM T_D_AI_STOCK b");
		//buf.append(" WHERE  D_STOCK = TO_DATE(?, 'yyyy-MM-dd')) B");
		//buf.append("  ON A.C_KM_CODE = B.C_KM_CODE WHERE A.C_SN = ? ");
		buf.append("  WHERE A.C_SN = ? ");
		return buf.toString();
	}
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}
}
