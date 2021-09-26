package com.yss.ams.base.information.modules.sys.dvaitem.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 核算业务项字典T_S_DVA_ITEM SQLBuilder
 *
 */
public class DavItemSqlBuilder implements SQLBuilder {

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

	/**
	 * 根据条件参数获取核算业务项字典T_S_DVA_ITEM的数据总数
	 * @param paraNameList条件参数
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from V_S_DVA_ITEM a  ");
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
	 * 根据条件参数获取核算业务项字典T_S_DVA_ITEM的数据信息
	 * @param paraNameList条件参数
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select a.* from V_S_DVA_ITEM a  ");
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
	 * 拼接sql where条件
	 * @param valueFieldbuf拼接后sql where条件
	 * @param paraNameList 需要拼接的条件集合
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DVA_ITEM_CODE")) {
				valueFieldbuf.append(" a.C_DVA_ITEM_CODE like ?  AND ");
			} else if (fieldedName.equals(("C_DVA_ITEM_NAME"))) {
				valueFieldbuf.append("  a.C_DVA_ITEM_NAME like ? AND ");
			} else if (fieldedName.equals("C_DVA_ITEM_CODE_P")) {
				valueFieldbuf.append(" a.C_DVA_ITEM_CODE_P like ? AND ");
			} else if (fieldedName.equals("N_ORDER")) {
				valueFieldbuf.append(" a.N_ORDER like ? AND ");
			} else if (fieldedName.equals("N_DETAIL")) {
				valueFieldbuf.append(" a.N_DETAIL like ? AND ");
			} 
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}


	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return null;//dbnameresolver.getTableName(DvaItemTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return null;// dbnameresolver.getTableName(DvaItemTableName.userInfo);
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver, String s) {
		return dbnameresolver.getColumnName(DavItemColumnName.valueOf(s));
	}
	
	/* START 数据源服务方法 */
	
	/**
	 * 获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
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
	 * 根据核算项目代码C_DVA_ITEM_CODE获取核算业务项字典T_S_DVA_ITEM的一条数据
	 * @return
	 */
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" select * from ( ");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) where C_DVA_ITEM_CODE = ? ORDER BY N_ORDER");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 核算项目父级代码C_DVA_ITEM_CODE_P获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	public String getDataByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" select * from ( ");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) where C_DVA_ITEM_CODE_P in ( SELECT * FROM TABLE(?) ) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 获取核算业务项字典T_S_DVA_ITEM的所有数据
	 * @return
	 */
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" select a.C_DVA_ITEM_CODE, ");
		buf.append(" a.C_DVA_ITEM_NAME,a.C_DVA_ITEM_CODE_P, ");
		buf.append(" a.N_ORDER,a.N_DETAIL ");
		buf.append(" from V_S_DVA_ITEM  a ");
		buf.append(" CONNECT by PRIOR c_DVA_ITEM_CODE = C_DVA_ITEM_CODE_P ");
		buf.append(" start with C_DVA_ITEM_CODE_P = '[root]' ");
		buf.append(" order SIBLINGS by a.N_ORDER  ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *  获取核算业务项字典T_S_DVA_ITEM的所有数据树形结构
	 * @return
	 */
	public String getDvaItemTreeViewSql(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT ");
		buf.append("  a.C_DVA_ITEM_CODE,a.C_DVA_ITEM_NAME, ");
		buf.append("  a.C_DVA_ITEM_CODE_P,a.N_ORDER, ");
		buf.append("  a.N_DETAIL ");
		buf.append(" FROM V_S_DVA_ITEM a ");
		buf.append(" WHERE  ");
		buf.append("  a.n_detail = 0  ");
		buf.append(" CONNECT BY  ");
		buf.append(" PRIOR c_DVA_ITEM_CODE = C_DVA_ITEM_CODE_P ");
		buf.append(" START WITH C_DVA_ITEM_CODE_P = '[root]'  ");
		buf.append(" ORDER SIBLINGS BY a.N_ORDER ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/* END 数据源服务方法 */

	/**
	 *  获取核算业务项字典T_S_DVA_ITEM的所有数据树形结构
	 * @return
	 */
	public String getTreeViewQuerySql(List<String> paraNameList)
	throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.*, A.C_DVA_ITEM_CODE AS NODECODE, A.C_DVA_ITEM_CODE_P AS FPARAENTCODE, 1 AS N_LEVEL ");
		buf.append("   FROM V_S_DVA_ITEM A WHERE A.N_DETAIL >= 0 START WITH A.C_DVA_ITEM_CODE_P = '[root]' ");
		buf.append("  CONNECT BY PRIOR A.C_DVA_ITEM_CODE = A.C_DVA_ITEM_CODE_P  ");
		return buf.toString();
	}

	/**
	 * 获取核算业务项字典T_S_DVA_ITEM的所有数据
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
