package com.yss.ams.base.information.modules.bi.salesnet.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 销售网点设置sql处理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class SalesNetSqlBuilder implements SQLBuilder {

	/**
	 * 返回根据前台查询条件获取销售网点设置总记录数的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select count(*) as CNT from T_P_BI_SALES_NET a");
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

	/**
	 * 返回根据前台查询条件获取销售网点设置所有记录的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select a.* from T_P_BI_SALES_NET a");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
//			if(paraNameList.size() > 0){
//				if(paraNameList.toString().contains("pdNet")){
//					buf.append(" and (a.c_net_name  like '%分行' or a.c_net_name like '%村镇%') ");
//					paraNameList.remove("pdNet");
//				}
//			}
			buf.append(" order by a.N_CHECK_STATE asc, a.rowId asc ");
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
	 * 根据前台查询条件判断哪些参数需要作为查询条件
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_DV_NET_TYPE")) {
				valueFieldbuf
						.append(" a.C_DV_NET_TYPE IN (SELECT * FROM TABLE(?)) AND ");
			}else if(fieldedName.equals("C_VENDOR_CODE")){
				valueFieldbuf.append("  a.C_VENDOR_CODE like ? AND ");
			}else if (fieldedName.equals(("C_DV_NET_TYPE"))) {
				valueFieldbuf.append("  a.C_DV_NET_TYPE = ? AND ");
			} else if (fieldedName.equals(("C_NET_CODE"))) {
				valueFieldbuf.append("  a.C_NET_CODE like ? AND ");
			} else if (fieldedName.equals("C_NET_NAME")) {
				valueFieldbuf.append(" a.C_NET_NAME like ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("N_STATE")) {
				valueFieldbuf.append(" A.N_CHECK_STATE = ? AND ");
			} else if (fieldedName.equals("C_NET_SOURCE")) {
				valueFieldbuf.append(" a.C_NET_SOURCE like ? AND ");
			}else if(fieldedName.equals("ARRAY_C_IDEN")){
				valueFieldbuf
				.append(" a.C_iden IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/* START 数据服务 */

	/**
	 * 返回查询销售网点设置所有记录的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 返回根据销售网点代码获取销售网点设置所有记录的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getDataByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);

		buf.append(" and C_NET_CODE = ? ");
//		buf.append(" and C_DV_NET_TYPE = ? ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 返回根据多个销售网点代码获取销售网点设置所有记录的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getAllDataSqlByKeys() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" and C_NET_CODE IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	private void getCommonQuerySqlBuf(StringBuffer buf) {
		buf.append(" select a.* ");
		buf.append(" from T_P_BI_SALES_NET a ");
		buf.append(" where a.N_CHECK_STATE = 1 ");
	}

	/* END 数据服务 */
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
		return dbnameresolver.getColumnName(SalesNetColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getLogTableName(SalesNetTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(SalesNetTableName.userInfo);
	}
	
	/**
	 * 返回查询销售网点设置所有记录的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getPdNetSql() {
		StringBuffer buf = new StringBuffer();
//		buf.append("select t.* from t_p_bi_sales_net t where t.c_net_name  like '%分行' or t.c_net_name like '%村镇%' ");
		buf.append("select t.* from t_p_bi_sales_net t where t.n_check_state = 1 ");
//		buf.append(" and t.n_check_state = 1");
		return buf.toString();
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getSequenceName(SalesNetTableName.userInfo);
	}

	/**
	 * 返回查询销售网点代码和销售网点名称所有记录的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getAllShortDataListSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT A.C_NET_CODE, A.C_NET_NAME FROM T_P_BI_SALES_NET A WHERE A.N_CHECK_STATE=1");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

}
