package com.yss.ams.base.information.modules.sys.dccury.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;


/**
 * 国际货币  操作数据库  SQL构造器
 * @author 马向峰  拆分  2017.0527
 *
 */
public class DcCurySqlBuilder implements SQLBuilder {

	/**
	 * 查询国际货币的数量
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from T_S_DC_CURY a  ");
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
			buf.append(" select a.* from T_S_DC_CURY a  ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			buf.append("  order by a.n_order  ");
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
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DC_CODE")) {
				valueFieldbuf.append(" a.C_DC_CODE like ?  AND ");
			} else if (fieldedName.equals(("C_DC_NAME"))) {
				valueFieldbuf.append("  a.C_DC_NAME like ? AND ");
			} else if (fieldedName.equals("C_DC_SIGN")) {
				valueFieldbuf.append(" a.C_DC_SIGN like ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}else if(fieldedName.equals("ARRAY_C_DC_CODE")){ //添加两种币种以上的查询2015年8月14日 by-xhb
				valueFieldbuf.append("a.C_DC_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/* START 数据服务 */
	/**
	 * 获取得到所有国际货币的SQL
	 * @return	查询国际货币SQL
	 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		/*生成SQL语句*/
		getCommonQuerySqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 生成带参数的SQL语句
	 * @return	包含币种代码参数的SQL
	 */
	public String getDataByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("select * from (");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) where C_DC_CODE = ? ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 生成根据币种代码（集合）查询币种信息的SQL
	 * @return	查询币种信息的SQL
	 */
	public String getDataByTypes() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("select * from (");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) where C_DC_CODE IN (SELECT * FROM TABLE(?)) ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 生成SQL语句
	 * @param buf	封装查询SQL
	 */
	private void getCommonQuerySqlBuf(StringBuffer buf) {
		buf.append(" select a.* ");
		buf.append(" from T_S_DC_CURY a ");
		/*- Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示 */
		buf.append(" where a.C_DV_STATE='ENAB' ");
		buf.append(" order by a.n_order ");
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
		return dbnameresolver.getColumnName(DcCuryColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		//return dbnameresolver.getLogTableName(DcCuryTableName.userInfo);
		return dbnameresolver.getTableName(DcCuryTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(DcCuryTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DcCuryTableName.userInfo);
	}

	/**
	 * 根据时间戳 获取货币列表
	 * @return
	 */
	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

}
