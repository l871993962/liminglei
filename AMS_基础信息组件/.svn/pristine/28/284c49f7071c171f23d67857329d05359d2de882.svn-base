package com.yss.ams.base.information.modules.sys.dztype.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 对账类型字典  SQl工具类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class DzTypeSqlBuilder implements SQLBuilder {
	/* SqlBuilder接口定义方法 数据源服务不用实现 */
	public String buildDeleteSql(DBNameResolver arg0) {
		return null;
	}

	public String buildInsertSql(DBNameResolver arg0) {
		return null;
	}

	public String buildSelectSql(DBNameResolver arg0) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver arg0) {
		return null;
	}

	

	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		return null;
	}

	public String getQueryConditionSql(List<String> arg0) throws Exception {
		return null;
	}

	public String getRecycleTableName(DBNameResolver arg0) {
		return null;
	}

	public String getTableName(DBNameResolver arg0) {
		return null;
	}
	
	/* START 数据源服务使用方法 */
	/**
	 * 获取对账类型字典列名
	 */
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(DzTypeCloumnName.valueOf(name));
	}
	
	/**
	 * 拼接sql，查询对账类型字典所有数据
	 * @param buf
	 */
	protected void setCommonQuyDataSql(StringBuffer buf) {
		buf.append(" SELECT ");
		buf.append("   A.C_IDEN, ");
		buf.append("   A.C_DZ_CODE, ");
		buf.append("   A.C_DZ_NAME, ");
		buf.append("   A.C_DZ_CODE_P ");
		buf.append(" FROM T_S_DZ_TYPE A ");
	}
	
	/**
	 * 拼接sql，按照对账类型字典代码升序的方式查询
	 */
	protected void setCommonQuyOrderSql(StringBuffer buf) {
		buf.append(" ORDER BY A.C_IDEN ");
	}

	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		setCommonQuyDataSql(buf);
		setCommonQuyOrderSql(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 拼接sql，根据对账类型代码集查询数据
	 * @return
	 */
	public String getDataByKeysSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		setCommonQuyDataSql(buf);
		buf.append(" WHERE A.C_DZ_CODE IN ");
		buf.append(" 	(SELECT * FROM TABLE(?) ) ");
		setCommonQuyOrderSql(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 根据对账类型字典代码查询所有数据
	 * @return
	 */
	public String getDataByCodeSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		setCommonQuyDataSql(buf);
		buf.append(" WHERE A.C_DZ_CODE = ? ");
		setCommonQuyOrderSql(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* END 数据源服务使用方法 */
}
