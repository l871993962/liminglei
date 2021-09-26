package com.yss.ams.base.information.modules.bi.account.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 
 * @ClassName BaseOrganSqlBuilder
 * @Description 公共机构信息sql拼接类
 * @author liangyilin@ysstech.com
 * @date 2017年6月22日下午7:23:08
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class AreaCitySqlBuilder implements SQLBuilder {

	@Deprecated
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		
		return null;
	}

	@Deprecated
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		
		return null;
	}

	@Deprecated
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		
		return null;
	}

	@Deprecated
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		
		return dbNameResolver.getTableName(AreaCityTableName.tableInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		
		return dbNameResolver.getColumnName(AreaCityColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		
		return null;
	}


	/**
	 * 
	 * @Title getAllDataSqlByKeys 
	 * @Description 通过省份城市代码查询
	 * @author hehonghui@ysstech.com
	 * @date 2017年11月29日下午7:08:36
	 * @return
	 * @return String
	 */
	public String getAllDataSqlByKeys(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("select * from "+AreaCityTableName.tableInfo+" where C_CODE IN ( SELECT * FROM TABLE(?) ) ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 
	 * @Title getAllDataSql 
	 * @Description 获取全部的省份以及城市
	 * @author hehonghui@ysstech.com
	 * @date 2017年11月29日下午7:08:49
	 * @return
	 * @return String
	 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("select * from "+AreaCityTableName.tableInfo);
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 返回结果集
	 */
	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		
		StringBuffer buffer = new StringBuffer();
		
		StringBuffer valueFieldbuf = new StringBuffer();
		
		getCommonSql(buffer);
		
		setWhereSql(valueFieldbuf, paraNameList);
		
		if(valueFieldbuf.length() > 0){
			buffer.append(" WHERE ").append(valueFieldbuf);
		}
		
		retSql = buffer.toString();
		
		StringUtil.clearStringBuffer(buffer);
		StringUtil.clearStringBuffer(valueFieldbuf);
		
		return retSql;
	}

	/**
	 * 返回结果集的条数信息
	 */
	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		
		return null;
	}
	
	
	/**
	 * 
	 * @Title getCommonSql 
	 * @Description 返回区域信息表所有字段
	 * @author yinyuyi
	 * @date 2016年10月26日下午2:49:10
	 * @param buffer
	 * @return void
	 */
	public void getCommonSql(StringBuffer buffer){
		buffer.append("SELECT C_IDEN,C_CODE,C_NAME,C_LEVEL,C_PARENT_CODE,");
		buffer.append("N_NUMBER1, C_STR1 ");
		buffer.append("FROM ");
		buffer.append(AreaCityTableName.tableInfo);
	}
	
	
	/**
	 * 
	 * @Title setWhereSql 
	 * @Description 拼接限定条件sql
	 * @author yinyuyi
	 * @date 2016年10月26日下午2:49:00
	 * @param buffer
	 * @param paramNameList
	 * @return void
	 */
	private void setWhereSql(StringBuffer buffer,List<String> paramNameList){
		
		for (String fieldName : paramNameList) {
			if(fieldName.equals("C_LEVEL")){
				buffer.append(" C_LEVEL = ? AND ");
			}else if(fieldName.equals("C_PARENT_CODE")){
				buffer.append(" C_PARENT_CODE = ? AND ");
			}
		}
		
		if(buffer.length() > 0){
			StringUtil.delLastSplitMark(buffer, "AND ");
		}
		
	} 

}
