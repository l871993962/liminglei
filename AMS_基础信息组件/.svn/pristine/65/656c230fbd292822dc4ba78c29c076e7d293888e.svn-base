package com.yss.ams.base.information.modules.bi.hday.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 节假日群设置sql处理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class HdaySqlBuilder implements SQLBuilder {
	
	/**
	 * 根据前台传递的参数拼接查询节假日群设置信息总数量sql
	 * @param paraNameList
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf
					.append(" select count(*) as CNT from T_P_BI_HDAY_SUB a where a.c_date_type = 'H' ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" and ").append(valueFieldbuf);
			}
			buf.append(" Order by a.N_CHECK_STATE,a.D_HDAY,a.C_IDEN ");
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
	 * 根据前台传递的参数拼接查询节假日群设置所有信息sql
	 * @param paraNameList
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf
					.append(" select a.C_IDEN,a.c_hday_code,a.d_hday,decode(a.c_date_type,'D','工作日','H','节假日') as c_date_type,a.c_desc,a.n_check_state,  ");
			buf
					.append("  a.c_update_by,a.c_update_time,decode(to_char(a.d_hday,'D'),1 ,'星期日',2,'星期一',3,'星期二',4,'星期三',5,'星期四', ");
			buf
					.append("  6,'星期五',7,'星期六') as D_WEAK,a.c_check_by,a.c_check_time,a.n_year from T_P_BI_HDAY_SUB a where (a.c_date_type = 'H' or a.c_date_type = '节假日') ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" and ").append(valueFieldbuf);
			}
			buf.append(" Order by a.N_CHECK_STATE,a.D_HDAY,a.C_IDEN ");
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
	 * 根据前台传递参数列表判断哪些参数需要作为查询的条件
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_HDAY_CODE")) {
				valueFieldbuf.append(" a.C_HDAY_CODE = ?  AND ");
			} else if (fieldedName.equals(("N_YEAR"))) {
				valueFieldbuf.append("  a.N_YEAR = ?  AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("C_HDAY_START")) {
			    valueFieldbuf.append("a.D_HDAY >= to_date(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("C_HDAY_END")) {
			    valueFieldbuf.append("a.D_HDAY <= to_date(?,'yyyy-MM-dd') AND ");
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
		return dbnameresolver.getColumnName(HdayGenColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getLogTableName(HdayTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(HdayTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(HdayTableName.userInfo);
	}
	
	/**
	 * 获取查询所有非重复年份的sql
	 * @return
	 */
	public String getAllYear() {
		String strSql = "select distinct a.N_YEAR  from t_p_bi_hday_sub a where a.C_HDAY_CODE = ? order by a.N_YEAR desc";
		return strSql;
	}

	/**
	 * 获取查询所有日期的sql
	 * @param paraNameList
	 * @return
	 */
	public String getAllHoiday(List<String> paraNameList) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select a.D_HDAY  from t_p_bi_hday_sub a where c_date_type = 'H' ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" and ").append(valueFieldbuf);
			}
			buf.append(" ORDER BY D_HDAY");
			retSql = buf.toString();
		} catch (Exception ex) {
			ex.getStackTrace();
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}
	
	/**
	 * 根据前台传递的参数拼接查询节假日群设置所有信息数量的sql
	 * @param paraNameList
	 */
	public String getHoidayCount(List<String> paraNameList) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select count(*) as CNT  from t_p_bi_hday_sub a where c_date_type = 'H' ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" and ").append(valueFieldbuf);
			}
			retSql = buf.toString();
		} catch (Exception ex) {
			ex.getStackTrace();
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	/**
	 * 根据主键获取节假日信息
	 * @return
	 */
	public String getHdayByPrimaryKey() {
		return "SELECT * FROM T_P_BI_HDAY_SUB A WHERE A.C_HDAY_CODE = ? " +
				"AND TO_CHAR(A.D_HDAY,'yyyy-MM-dd') IN (SELECT * FROM TABLE(?))";
	}
	
	/**
	 * 
	 * @Title getHoliDayByMouths 
	 * @Description 根据年月获取节假日
	 * @author lixiang@ysstech.com
	 * @date 2017年7月17日下午2:16:52
	 * @param date yyyy-MM
	 * @return
	 * @return String
	 */
	public String getHoliDayByMouths(String date) {
		StringBuffer sql = new StringBuffer();
	    sql.append(" SELECT D_HDAY FROM T_P_BI_HDAY_SUB WHERE TO_CHAR(D_HDAY,'YYYY-MM') = '");
	    sql.append(date);
	    sql.append("' AND N_CHECK_STATE = '1' AND C_HDAY_CODE = ? ");
	    sql.append("  GROUP BY D_HDAY ORDER BY D_HDAY ");
	    return sql.toString();
	}

}
