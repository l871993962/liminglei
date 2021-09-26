package com.yss.ams.base.information.modules.sys.dtatdattr.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 交易属性字典T_S_DTA_TD_ATTR SQLBuilder
 *
 */

public class DtatdAttrSqlBuilder implements SQLBuilder {

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

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(DtatdAttrColumnName.valueOf(s));
	}

	/**
	 * 根据条件获取符合条件的交易属性字典T_S_DTA_TD_ATTR的所有数据总数
	 * @param paraNameList查询条件参数
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from V_S_DTA_TD_ATTR a  ");
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
	 * 根据条件获取符合条件的交易属性字典T_S_DTA_TD_ATTR的所有数据信息
	 * @param paraNameList查询条件参数
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select a.* from V_S_DTA_TD_ATTR a  ");
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
	 * 拼接查询条件
	 * @param valueFieldbuf
	 * @param paraNameList 字段名组成的集合
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DTA_CODE")) {
				valueFieldbuf.append(" a.C_DTA_CODE like ?  AND ");
			} else if (fieldedName.equals(("C_DTA_NAME"))) {
				valueFieldbuf.append("  a.C_DTA_NAME like ? AND ");
			} else if (fieldedName.equals("C_BUSI_TYPE")) {
				valueFieldbuf.append(" a.C_BUSI_TYPE like ? AND ");
			} else if (fieldedName.equals("N_ORDER")) {
				valueFieldbuf.append(" a.N_ORDER like ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(DtatdAttrTableName.userInfo);
		//return dbnameresolver.getTableName(DtatdAttrTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(DtatdAttrTableName.userInfo);
	}


	/* START 数据源服务方法 */
	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR数据
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
	 * 根据交易属性代码C_DTA_CODE值获取交易属性字典T_S_DTA_TD_ATTR数据
	 */
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where C_DTA_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 根据业务类型C_BUSI_TYPE值获取所有交易属性字典T_S_DTA_TD_ATTR数据
	 */
	public String getDataListByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_BUSI_TYPE ");
		buf.append(" IN (SELECT * FROM TABLE(?) ) ");
		
		getOrderBySql(buf);
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getOrderBySql(StringBuffer buf){
		buf.append(" ORDER BY a.N_ORDER ");
	}
	
	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR数据
	 */
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" select a.* ");
		buf.append(" from V_S_DTA_TD_ATTR a ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DtatdAttrTableName.userInfo);
	}

	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR的 交易属性代码C_DTA_CODE,交易属性名称C_DTA_NAME列值
	 */
	public String getAllShortDataSql() {
		return "SELECT C_DTA_CODE,C_DTA_NAME FROM V_S_DTA_TD_ATTR";
	}
	
	/**
	 * 通过指定codes获取数据   STORY39265商品期权业务
	 * @param codes
	 * @return 数据集合
	 * @throws ServiceException
	 * @author xuyuanhao
	 * @date 2017-3-30
	 * @state add
	 */
	public String getDataListByCodes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_DTA_CODE ");
		buf.append(" IN (SELECT * FROM TABLE(?) ) ");
		
		getOrderBySql(buf);
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * STORY #96237 凭证处理过程中交易属性需要变更从缓存中处理
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
	
	/* END 数据源服务方法 */

}
