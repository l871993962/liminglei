package com.yss.uco.elecreco.er.reverse.portrela.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class ElecPortRelaSqlBuilder implements SQLBuilder {



	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_RELA_TYPE")) {
				valueFieldbuf.append(" a.C_RELA_TYPE = ?  AND ");
			} else if (fieldedName.equalsIgnoreCase("C_RELA_CODE")) {
				valueFieldbuf.append(" a.C_RELA_CODE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf
						.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("ARRAY_C_DV_TYPE_CODE")) {
				valueFieldbuf.append("a.C_DV_TYPE_CODE  IN (SELECT * FROM TABLE(?))  AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_START")) {
				valueFieldbuf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_END")) {
				valueFieldbuf.append(" a.C_DEL_TIME <= ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	public String getOrganByPortSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		
		this.setWhereSql(valueFieldbuf, paraNameList);
		//BUG #168249 【21.5.0】电子对账参数设置选择数据报错 
		buf.append(" SELECT B.* ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT T.* FROM T_P_BI_ORG T WHERE T.N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_ORG_CODE = A.C_RELA_CODE ");
		
		buf.append("   JOIN (SELECT T.* FROM T_P_AB_PORT T WHERE T.N_CHECK_STATE = 1) P ");
		buf.append("     ON P.C_PORT_CODE = A.C_PORT_CODE ");
		
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}
	
	public String getQueryOrganSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		
		this.setWhereSql(valueFieldbuf, paraNameList);
		//BUG #168249 【21.5.0】电子对账参数设置选择数据报错 
		buf.append(" SELECT P.* ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT T.* FROM T_P_BI_ORG T WHERE T.N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_ORG_CODE = A.C_RELA_CODE ");
		
		buf.append("   JOIN (SELECT T.* FROM T_P_AB_PORT T WHERE T.N_CHECK_STATE = 1) P ");
		buf.append("     ON P.C_PORT_CODE = A.C_PORT_CODE ");
		
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
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



	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		//return dbnameresolver.getColumnName(PortColumnName.valueOf(s));
		return null;
	}



	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
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
}
