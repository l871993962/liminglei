package com.yss.ams.product.information.modules.ab.portPdAttribute.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * <产品属性分类>SQL语句构造类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortPdAttributeSqlBuilder implements SQLBuilder {

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
		return dbNameResolver.getTableName(PortPdAttributeTableName.userInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return dbNameResolver.getColumnName(PortPdAttributeColumnName
				.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver
				.getLogTableName(PortPdAttributeTableName.userInfo);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver
				.getLogSequenceName(PortPdAttributeTableName.userInfo);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select a.*  from T_P_AB_PORT_PD  a ");
			buf.append("  left join  (select C_PORT_CODE,C_DAT_CODE from T_P_AB_PORT ");
			buf.append(" where N_CHECK_STATE = 1 )b on b.C_PORT_CODE = a.C_PORT_CODE ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
				buf.append(" ORDER BY a.N_CHECK_STATE, a.C_IDEN ");
			}
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select count(*) as CNT from T_P_AB_PORT_PD  a ");
			buf.append("  left join  (select C_PORT_CODE,C_DAT_CODE from T_P_AB_PORT ");
			buf.append(" where N_CHECK_STATE = 1 )b on b.C_PORT_CODE = a.C_PORT_CODE ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldName : paraNameList) {
			if (fieldName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf
						.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldName.equals("C_INVEST_CODE")) {
				valueFieldbuf.append(" a.C_INVEST_CODE = ? AND ");
			} else if (fieldName.equals("C_ASSETS_CODE")) {
				valueFieldbuf.append(" a.C_INVEST_CODE = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}

	}

	public String getVocabularySql() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(" SELECT DISTINCT C_DV_CODE, C_DV_NAME FROM v_s_dv_voc ");

		return buffer.toString();
	}

	public String getAssetSql() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(" SELECT DISTINCT C_DAT_CODE, C_DAT_NAME FROM T_S_DAT_ASS_TYPE ");
//		buffer.append(" WHERE C_DAT_TYPE = 'ASS' ");

		return buffer.toString();
	}

	public String getAssetCodeSql() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(" SELECT DISTINCT C_PORT_CODE, C_DAT_CODE FROM T_P_AB_PORT ");
		buffer.append(" WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");

		return buffer.toString();
	}

	public String getPortNameDictSql() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(" SELECT DISTINCT C_PORT_CODE, C_PORT_NAME FROM T_P_AB_PORT ");
		buffer.append(" WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");

		return buffer.toString();
	}
	
	public String queryPortCodesByType() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT  C_PORT_CODE FROM T_P_AB_PORT_PD ");
		buffer.append(" WHERE C_PORT_TYPE = ? ");
		return buffer.toString();
	}
	
	public String getShortNumPort(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT  C_PORT_CODE,C_DAT_CODE,C_CHECK_BY,C_CHECK_TIME,C_UPDATE_TIME,C_UPDATE_BY, ");
		buffer.append(" N_CHECK_STATE,C_SHORT_NUM FROM T_P_AB_PORT_PD ");
		buffer.append(" WHERE C_SHORT_NUM is not null and N_CHECK_STATE>0 ");
		return buffer.toString();
	}
}
