package com.yss.ams.base.information.modules.sys.secvar.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.platform.support.dataservice.columnname.SecVarColumnName;

/**
 * 证券品种字典T_S_DA_SEC_VAR  SQLBuilder
 *
 */
public class SecVarSqlBuilder implements SQLBuilder {

	/* START 数据服务 */

	/**
	 * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据
	 * @return
	 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		getCommonOrderSqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 一条证券品种字典V_S_DA_SEC_VAR数据
	 * @return
	 */
	public String getDataByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		buf.append(" where ");
		buf.append(" C_SEC_VAR_CODE = ? ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 根据 证券属性代码 C_DA_CODE数组 获取 符合条件的证券品种字典V_S_DA_SEC_VAR的数据
	 * @return
	 */
	public String getDataListByTypes(String[] Types) {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);

		sql = whereTypes(Types);
		if (sql.trim().length() > 0) {
			buf.append(" WHERE ");
			buf.append(sql);
		}

		getCommonOrderSqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 动态生成条件类型byleeyu20130518
	 * 
	 * @param types
	 *            品种类型关系为 或者
	 * @return
	 */
	private String whereTypes(String[] types) {
		StringBuffer buf = new StringBuffer();
		for (String type : types) {
			if (type != null && type.trim().length() > 0) {
				buf.append(" a.C_DA_CODE LIKE ? OR ");
			}
		}

		if (buf.length() > 3) {
			buf.setLength(buf.length() - 3);
		}
		return buf.toString();
	}

	/**
	 * 根据 证券品种代码C_SEC_VAR_CODE 获取 所有证券品种字典V_S_DA_SEC_VAR数据
	 * @return
	 */
	public String getDataByKeys() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		buf.append(" where ");
		buf.append(" C_SEC_VAR_CODE IN (SELECT * FROM TABLE(?)) ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据
	 * @return
	 */
	private void getCommonQuerySqlBuf(StringBuffer buf) {
		buf.append(" SELECT A.*,  ");
		buf.append("  1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_CHECK_TIME, ");
		buf.append("  '' AS C_CHECK_BY, ");
		buf.append("  '' AS C_UPDATE_BY, ");
		buf.append("  '' AS C_UPDATE_TIME, ");
		buf.append("  '' AS C_DESC ");
		buf.append(" FROM V_S_DA_SEC_VAR A  ");
	}

	private void getCommonOrderSqlBuf(StringBuffer buf) {
		// buf.append(" CONNECT BY PRIOR C_DA_CODE = C_DA_CODE_P ");
		// buf.append(" START WITH C_DA_CODE_P = '[root]' ");
		// buf.append(" ORDER SIBLINGS BY C_IDEN ");
	}

	/* END 数据服务 */

	/**
	 * 根据条件paraNameList 获取T_S_DA_SEC_ATTR和T_P_BI_SEC_VAR联合查询数据总数
	 * @param paraNameList 查询条件
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionExtendCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from T_S_DA_SEC_ATTR b, T_P_BI_SEC_VAR  a ");
			buf.append(" where b.c_da_code = a.c_da_code ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" AND ").append(valueFieldbuf);
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

	/**
	 * 根据条件paraNameList 获取所有符合条件T_S_DA_SEC_ATTR和T_P_BI_SEC_VAR联合查询数据
	 * @param paraNameList 查询条件
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionExtendSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select  a.c_da_code,b.c_da_code_p , b.c_da_name as c_da_name,");
			buf.append(" a.c_sec_var_code,a.c_sec_var_name,a.c_desc as c_desc  ,");
			buf.append(" C_UPDATE_BY,C_CHECK_TIME, N_CHECK_STATE,C_UPDATE_TIME,C_CHECK_BY");
			buf.append(" ,a.C_IDEN from T_S_DA_SEC_ATTR b, T_P_BI_SEC_VAR  a ");
			buf.append(" where b.c_da_code = a.c_da_code ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" AND ").append(valueFieldbuf);
			}
			buf.append(" order by a.N_CHECK_STATE asc, b.C_DA_CODE,b.C_DA_CODE_P,a.C_UPDATE_TIME desc , a.C_IDEN");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}
	/**
	 * 根据条件paraNameList 拼接where sql 语句
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_SEC_VAR_CODE")) {
				valueFieldbuf.append(" a.C_SEC_VAR_CODE like ? AND ");
			} else if (fieldedName.equals(("C_SEC_VAR_NAME"))) {
				valueFieldbuf.append("  a.C_SEC_VAR_NAME like ? AND ");
			} else if (fieldedName.equals("C_DA_CODE")) {
				valueFieldbuf.append(" a.C_DA_CODE  like ?  AND ");
			}else if(fieldedName.equals("ARRAY_C_SEC_VAR_CODE")){
				valueFieldbuf.append(" t.C_SEC_VAR_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
//			} else if (fieldedName.equals("C_DEL_TIME_START")) {
//				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
//			} else if (fieldedName.equals("C_DEL_TIME_END")) {
//				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
//			}
			else if(fieldedName.equals("C_DA_CODE_P")){
				valueFieldbuf.append("b.C_DA_CODE_P = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

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
		return dbnameresolver.getColumnName(SecVarColumnName.valueOf(s));
	}

	public String getColumnNameByPropertyExtend(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(SecVarExtendColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
//		return dbnameresolver.getTableName(SecVarTableName.recycle);
		return null;
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(SecVarTableName.userInfo);
	}

	/**
	 * 根据条件arg0 获取品种属性的父级代码 c_da_code_p为'[root]'的  证券品种字典视图V_S_DA_SEC_VAR的所有数据
	 */
	public String getQueryConditionSql(List<String> arg0) throws Exception {
		// TODO Auto-generated method stub
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, arg0);
//			buf
//					.append(" select  a.c_da_code,b.c_da_code_p , b.c_da_name as c_da_name,");
//			buf
//					.append(" a.c_sec_var_code,a.c_sec_var_name,a.c_desc as c_desc  ,");
//			buf
//					.append(" C_UPDATE_BY,C_CHECK_TIME, N_CHECK_STATE,C_UPDATE_TIME,C_CHECK_BY");
//			buf.append(" ,a.C_IDEN from T_S_DA_SEC_ATTR b, T_P_BI_SEC_VAR  a ");
//			buf.append(" where b.c_da_code = a.c_da_code ");
			
//			if (valueFieldbuf.length() > 0) {
//				buf.append(" AND ").append(valueFieldbuf);
//			}
//			buf
//					.append(" order by a.N_CHECK_STATE asc, b.C_DA_CODE,b.C_DA_CODE_P,a.C_UPDATE_TIME desc , a.C_IDEN");
            buf.append(" SELECT T.*,  ");
			buf.append("  1 AS N_CHECK_STATE, ");
			buf.append("  '' AS C_CHECK_TIME, ");
			buf.append("  '' AS C_CHECK_BY, ");
			buf.append("  '' AS C_UPDATE_BY, ");
			buf.append("  '' AS C_UPDATE_TIME, ");
			buf.append("  '' AS C_DESC ");
			buf.append(" FROM V_S_DA_SEC_VAR T  ");
			buf.append(" where t.c_da_code_p = '[root]' ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" AND ").append(valueFieldbuf);
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

	/**
	 * 根据条件arg0 获取 证券品种字典视图V_S_DA_SEC_VAR的所有数据
	 */
	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		// TODO Auto-generated method stub
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, arg0);
//			buf
//					.append(" select count(*) as CNT from T_S_DA_SEC_ATTR b, T_P_BI_SEC_VAR  a ");
//			buf.append(" where b.c_da_code = a.c_da_code ");
			
			buf.append("select count(*) from V_S_DA_SEC_VAR ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" AND ").append(valueFieldbuf);
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

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(SecVarTableName.userInfo);
	}
    
	/**
	 * 获取证券品种代码 c_sec_var_code 为'PJ','LC','ZQ','CK','HG','CJ'  的证券品种字典视图V_S_DA_SEC_VAR的数据
	 * 以及 业务类型c_busi_type 为('HG','CJ','CK')   的交易方式字典V_S_DT_TD_MODE的数据	
	 *   的集合
	 * 
	 * @return
	 */
    public String queryProductByCodesSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select t.c_iden,t.c_sec_var_code,t.c_sec_var_name,t.c_da_code,t.c_da_code_p from V_S_DA_SEC_VAR t              ");
		buf.append(" where t.c_sec_var_code in ('PJ','LC','ZQ','CK','HG','CJ')                                                      ");
		buf.append(" union all                                                                                                      ");
		buf.append(" select ' ' as c_iden, m.c_dt_code as c_sec_var_code,                                                           ");
		buf.append(" m.c_dt_name as c_sec_var_name,m.c_dt_code as c_da_code,substr(m.c_busi_type,0,2) as c_da_code_p from V_S_DT_TD_MODE m  ");
		buf.append(" where substr(m.c_busi_type,0,2) in ('HG','CJ','CK')                                                                 ");
		return buf.toString();
	}

    /**
     * 获取 证券品种字典V_S_DA_SEC_VAR所有的数据
     * @return
     */
	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		getCommonOrderSqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 根据T_P_SV_SEC_BASE的c_sec_code的值获取 证券品种字典V_S_DA_SEC_VAR所有的数据
	 * @param seccode T_P_SV_SEC_BASE的c_sec_code的值
	 * @return
	 * @throws Exception
	 */
	public String getVarcodeByCode(String seccode)throws Exception {
		StringBuffer buf = new StringBuffer();
		String sql = "";
		try{
			buf.append("select b.* from ");
			buf.append("(select c_sec_code,c_sec_var_code from T_P_SV_SEC_BASE where c_sec_code='");
			buf.append(seccode).append("') a left join V_S_DA_SEC_VAR b ");
			buf.append("on a.c_sec_var_code=b.c_sec_var_code ");
			sql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return sql;
	}
}
