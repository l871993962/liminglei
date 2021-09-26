package com.yss.ams.base.information.modules.sys.dai.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class AccProSqlBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(DaiColumnName.valueOf(name));
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(DaiTableName.userInfo);
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM数据,并对结果按 C_DV_KM_CLS,N_ORDER进行排序
	 * @param dbNameResolver
	 * @return
	 */
	public String getAllDataSql(DBNameResolver dbNameResolver) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		
		getAllDataSql(dbNameResolver,buf);
		getOrderByBuffer(buf);
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	
	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param dbNameResolver
	 * @return
	 */
	public String getKmClsDataSql(DBNameResolver dbNameResolver) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		
		getAllDataSql(dbNameResolver,buf);
		buf.append(" WHERE ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DV_KM_CLS"));
		buf.append(" IN(SELECT * FROM TABLE(?)) ");
		getOrderByBuffer(buf);
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	
	/**
	 * 查询指定 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param dbNameResolver
	 * @return
	 */
	public String getDataByCodeSql(DBNameResolver dbNameResolver) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append(" '' AS C_IDEN, ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DAI_CODE"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DAI_NAME"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DV_KM_CLS"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "n_FUND_WAY"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "n_ORDER"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DV_BOOL_TYPE_AM"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_STOCK_CLS"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DAI_TYPE"));
		buf.append(" FROM ");
		buf.append(getTableName(dbNameResolver));
		buf.append(" WHERE ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DAI_CODE"));
		buf.append(" = ? ");
		
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	
	/**
	 * 查询指定科目类别C_DV_KM_CLS的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param dbNameResolver
	 * @return
	 */
	public String getAllDataSqlByTypes(DBNameResolver dbNameResolver) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		
		getAllDataSql(dbNameResolver,buf);
		buf.append(" WHERE ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DV_KM_CLS"));
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
		
		getOrderByBuffer(buf);
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	
	/**
	 * 查询 符合条件Conds的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param dbNameResolver
	 * @param Conds 查询条件
	 * @return
	 */
	public String getAllDataSqlBySqlConds(DBNameResolver dbNameResolver, String Conds) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		
		getAllDataSql(dbNameResolver,buf);
		buf.append(" WHERE ");
		buf.append(Conds);
		
		getOrderByBuffer(buf);
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	
	/**
	 * 查询某些 核算项目代码C_DAI_CODE的所有核算项目字典表T_S_DAI_ITEM的数据
	 * @param dbNameResolver
	 * @return
	 */
	public String getListByKeysSql(DBNameResolver dbNameResolver) {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getAllDataSql(dbNameResolver,buf);
		buf.append(" WHERE C_DAI_CODE IN (SELECT * FROM TABLE(?)) ");

		getOrderByBuffer(buf);
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}
	
	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM数据
	 * @param dbNameResolver
	 * @param buf
	 */
	private void getAllDataSql(DBNameResolver dbNameResolver,StringBuffer buf) {
		buf.append(" SELECT ");
		buf.append(" '' AS C_IDEN, ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DAI_CODE"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DAI_NAME"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DV_KM_CLS"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "n_FUND_WAY"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "n_ORDER"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DV_BOOL_TYPE_AM"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_STOCK_CLS"));
		buf.append(", ");
		buf.append(getColumnNameByProperty(dbNameResolver, "c_DAI_TYPE"));
		buf.append(" FROM ");
		buf.append(getTableName(dbNameResolver));		
		
	}
	
	/**
	 * 对查询结果按 C_DV_KM_CLS,N_ORDER进行排序
	 * @param buf
	 */
	private void getOrderByBuffer(StringBuffer buf){
		buf.append(" ORDER BY C_DV_KM_CLS,N_ORDER ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取所有的核算项目字典表T_S_DAI_ITEM数据
	 * @param dbNameResolver
	 * @return
	 */
	public String getDataListByTimestamp(DBNameResolver dbNameResolver) {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getAllDataSql(dbNameResolver,buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
}
