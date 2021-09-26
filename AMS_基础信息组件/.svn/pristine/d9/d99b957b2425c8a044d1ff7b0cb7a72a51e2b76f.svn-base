package com.yss.ams.base.information.modules.sys.dsppara.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 综合参数字典T_S_DSP_PARA SQLBuilder
 *
 */
public class DspParaSqlBuilder implements SQLBuilder{

	public String buildDeleteSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver, String column) {
		return dbNameResolver.getColumnName(DspParaColumnName.valueOf(column));
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DspParaTableName.userInfo);
	}

	/**
	 * 获取综合参数字典表T_S_DSP_PARA数据总数
	 */
	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" select count(*) ");
		sqlBuff.append(" from ");
		sqlBuff.append(DspParaTableName.userInfo);
		return sqlBuff.toString();
	}

	/**
	 * 获取综合参数字典表T_S_DSP_PARA所有数据
	 */
	public String getQueryConditionSql(List<String> arg0) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" select t.* ");
		sqlBuff.append(" from ");
		sqlBuff.append(DspParaTableName.userInfo);
		sqlBuff.append(" t ");
		sqlBuff.append(" order by t.c_dsp_code desc");
		return sqlBuff.toString();
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogTableName(DspParaTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(DspParaTableName.userInfo);
	}
	
}
