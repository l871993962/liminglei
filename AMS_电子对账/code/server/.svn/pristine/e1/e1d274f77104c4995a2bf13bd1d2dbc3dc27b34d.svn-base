package com.yss.uco.elecreco.bi.elecrela.dao;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class ElecRelaSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(ElecRelaColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getLogTableName(ElecRelaTableName.elecRela);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ElecRelaTableName.elecRela);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		List<String> list = new ArrayList<String>(paraNameList);
		if(paraNameList.contains("C_REPORT_CODE"))
		{
			buf.append(" select COUNT(*) AS CNT from ( ");
			//财务报表不为空
			this.setWhereSql1(valueFieldbuf, paraNameList);
			buf.append(" select A.*,B.C_REPORT_NAME from T_Z_BI_RELA A left join T_F_RC_REP_TPL B on A.C_REPORT_CODE = B.C_REPORT_CODE ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			//财务报表为空
			valueFieldbuf.setLength(0);
			this.setWhereSql2(valueFieldbuf, list);
			buf.append(" union all select A.*,B.C_REPORT_NAME from T_Z_BI_RELA A left join T_F_RC_REP_TPL B on A.C_REPORT_CODE = B.C_REPORT_CODE ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			buf.append(" ) A ");
			
			//设置参数
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			paraNameList.addAll(list);
			return buf.toString();
		}else//没有财务报表参数
		{
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select COUNT(*) AS CNT from T_Z_BI_RELA A left join T_F_RC_REP_TPL B on A.C_REPORT_CODE = B.C_REPORT_CODE ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}

			return buf.toString();
		}
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		List<String> list = new ArrayList<String>(paraNameList);
		if(paraNameList.contains("C_REPORT_CODE"))
		{
			buf.append(" select A.* from ( ");
			//财务报表不为空
			this.setWhereSql1(valueFieldbuf, paraNameList);
			buf.append(" select A.*,B.C_REPORT_NAME from T_Z_BI_RELA A left join T_F_RC_REP_TPL B on A.C_REPORT_CODE = B.C_REPORT_CODE ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			//财务报表为空
			valueFieldbuf.setLength(0);
			this.setWhereSql2(valueFieldbuf, list);
			buf.append(" union all select A.*,B.C_REPORT_NAME from T_Z_BI_RELA A left join T_F_RC_REP_TPL B on A.C_REPORT_CODE = B.C_REPORT_CODE ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			buf.append(" ) A ");
			buf.append(" ORDER BY A.N_CHECK_STATE,A.C_ZB_CODE,A.C_UPDATE_TIME DESC  ");
			
			//设置参数
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			paraNameList.addAll(list);
			return buf.toString();
		}else//没有财务报表参数
		{
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select A.*,B.C_REPORT_NAME from T_Z_BI_RELA A  left join T_F_RC_REP_TPL B on A.C_REPORT_CODE = B.C_REPORT_CODE ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			buf.append(" ORDER BY A.N_CHECK_STATE,A.C_ZB_CODE,A.C_UPDATE_TIME DESC  ");

			return buf.toString();
		}
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		setCommonWhereSql(valueFieldbuf, paraNameList);

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	

	private void setWhereSql1(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		setCommonWhereSql(valueFieldbuf, paraNameList);
		if (paraNameList.contains("C_REPORT_CODE")) {
			valueFieldbuf.append("  A.C_REPORT_CODE = ? AND ");
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	private void setWhereSql2(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		List<String> list = new ArrayList<String>(paraNameList);
		setCommonWhereSql(valueFieldbuf, paraNameList);
		if (paraNameList.contains("C_REPORT_CODE")) {
			valueFieldbuf.append("  trim(A.c_report_code) is null AND ");
			valueFieldbuf.append("  not exists (SELECT 1 FROM T_Z_BI_RELA B where "+ SqlUtil.getCheckStateClause(list, "B") +" B.c_report_code = ? and B.c_zb_code = A.c_zb_code) AND ");
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	public String getDataListSql(){
		StringBuffer buf = new StringBuffer();
		
		buf.append(" select A.* from T_Z_BI_RELA A ");
		return buf.toString();
	}
	
	public String getDataListByNameSql(){
		StringBuffer buf = new StringBuffer();
		
		buf.append(" select A.* from T_Z_BI_RELA A WHERE C_ZB_NAME LIKE ? ");
		return buf.toString();
	}
	
	private void setCommonWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_DZ_CODE")) {
				valueFieldbuf.append(" A.C_DZ_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_ZB_CODE")) {
				valueFieldbuf.append(" A.C_ZB_CODE LIKE ? AND ");
			}else if(fieldedName.equalsIgnoreCase("C_ZB_NAME")){
				valueFieldbuf.append(" A.C_ZB_NAME LIKE ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_START")) {
				valueFieldbuf.append(" A.C_DEL_TIME >= ? AND ");
			}else if(fieldedName.equalsIgnoreCase("C_DEL_TIME_END")){
				valueFieldbuf.append(" A.C_DEL_TIME <= ? AND ");
			}
		}
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(ElecRelaTableName.elecRela);
	}

	public String getZbCodeByKeyCodeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.* from T_Z_BI_RELA A ");
		buf.append(" where A.C_DV_ZB_CODE in (SELECT * FROM TABLE(?)) ");
		buf.append(" and A.C_DZ_CODE in ('1011','1013') ");
		return buf.toString();
	}
	
	public String getRealIndexCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT DISTINCT (C_ZB_CODE) FROM T_Z_BI_RELA ");
		buf.append(" WHERE C_DZ_CODE = ? AND C_DV_ZB_CODE = ? AND N_CHECK_STATE >= 0");
		return buf.toString();
	}

}
