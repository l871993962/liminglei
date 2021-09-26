package com.yss.ams.base.information.modules.bi.curypair.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 货币对 操作数据库生成SQL工具类
 * @author zhao
 *
 */
public class CuryPairSqlBuilder implements SQLBuilder {


	/**
	 * 查询货币对的数量
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select count(*) as CNT from T_P_BI_CURY_PAIR a  ");
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

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select a.* from T_P_BI_CURY_PAIR a  ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);

			}

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
	 * SQL查询条件封装
	 * @param valueFieldbuf 查询条件
	 * @param paraNameList 查询字段
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_DC_CODE_MARK")) {
				valueFieldbuf
						.append(" a.C_DC_CODE_MARK IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals(("ARRAY_C_DC_CODE_PRICE"))) {
				valueFieldbuf
						.append("  a.C_DC_CODE_PRICE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}else if (fieldedName.equals("C_DC_CODE_MARK")) {
				valueFieldbuf.append("a.C_DC_CODE_MARK = ? AND ");
			}else if (fieldedName.equals("C_DC_CODE_PRICE")) {
				valueFieldbuf.append("a.C_DC_CODE_PRICE = ?  AND ");
			}
			
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/*START 数据服务方法*/
	/**
	 * 获取查询所有货币对SQL语句
	 * @return String(SQL)
	 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		//生成SQL并保存到StringBuffer
		getCommonQuerySqlBuf(buf);
		/*将StringBuffer内的SQL转化为字符串，并清空StringBuffer*/
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 获取根据 货币对代码 查询货币对的SQL语句
	 * @return String(SQL)
	 */
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		//生成SQL并保存到StringBuffer
		getCommonQuerySqlBuf(buf);
		//添加 货币对代码 查询条件
		buf.append(" AND C_CURY_PAIR_CODE = ? ");
		/*将StringBuffer内的SQL转化为字符串，并清空StringBuffer*/
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 生成根据货对比代码查找货币对信息的SQL
	 * @return 货对比代码查找货币对信息的SQL
	 */
	public String getAllDataSqlByKeys(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" AND C_CURY_PAIR_CODE IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 生成根据货对比代码查找货币对信息的SQL
	 * @return 货对比代码查找货币对信息的SQL
	 */
	public String getDataByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" AND C_CURY_PAIR_CODE IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 生成查找所有审核通过的货币对SQL
	 * @param buf
	 */
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" select a.* ");
		buf.append(" from T_P_BI_CURY_PAIR a ");
		buf.append(" where a.N_CHECK_STATE = 1 ");
	}
	
	/*END 数据服务方法*/

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
		return dbnameresolver.getColumnName(CuryPairColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getLogTableName(CuryPairTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(CuryPairTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(CuryPairTableName.userInfo);
	}

}
