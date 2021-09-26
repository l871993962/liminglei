package com.yss.ams.base.information.modules.sys.ieItem.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 收支项目字典表T_S_IE_ITEM SQLBuilder
 *
 */
public class IeItemSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(IeItemColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(IeItemTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(IeItemTableName.userInfo);
	}

/*START 数据服务方法*/
	
	/**
	 * 获取收支项目字典表T_S_IE_ITEM 所有数据
	 * @return
	 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		buf.append(" ORDER BY A.N_ORDER ASC ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 根据收支项目代码C_IE_CODE 获取对应一条收支项目字典表T_S_IE_ITEM 数据
	 * @return
	 */
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" WHERE C_IE_CODE = ? ");
		buf.append(" ORDER BY A.N_ORDER ASC ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 根据状态N_STATE 获取对应所有收支项目字典表T_S_IE_ITEM 数据
	 * @return
	 */
	public String getDataListByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where A.N_STATE ");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
		
		buf.append(" ORDER BY A.N_ORDER ASC ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 根据收支项目代码C_IE_CODE 获取对应所有收支项目字典表T_S_IE_ITEM 数据
	 * @return
	 */
	public String getDataByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" WHERE C_IE_CODE IN (SELECT * FROM TABLE(?)) ");
		buf.append(" ORDER BY A.N_ORDER ASC ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 获取所有收支项目字典表T_S_IE_ITEM 数据
	 * @return
	 */
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" SELECT A.* FROM T_S_IE_ITEM A  ");
	}
	
	/*END 数据服务方法*/
	
	/**
	 * 根据条件 获取 符合条件的收支项目字典表T_S_IE_ITEM数据总数
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from T_S_IE_ITEM a  ");
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
	 * 根据条件 获取 符合条件的收支项目字典表T_S_IE_ITEM数据
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select a.* from T_S_IE_ITEM a  ");
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
	 * 根据条件 拼接where语句条件
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_IE_CODE")) {
				valueFieldbuf.append(" a.C_IE_CODE like ?  AND ");
			} else if (fieldedName.equals(("C_IE_NAME"))) {
				valueFieldbuf.append("  a.C_IE_NAME like ? AND ");
			} else if (fieldedName.equals("C_DAI_CODE_REC")) {
				valueFieldbuf.append(" a.C_DAI_CODE_REC like ? AND ");
			} else if (fieldedName.equals("C_DAI_CODE_COP")) {
				valueFieldbuf.append(" a.C_DAI_CODE_COP like ? AND ");
			}else if (fieldedName.equals("C_DAI_CODE_INC")) {
				valueFieldbuf.append(" a.C_DAI_CODE_INC like ? AND ");
			} else if (fieldedName.equals("C_DAI_CODE_FEE")) {
				valueFieldbuf.append(" a.C_DAI_CODE_FEE like ? AND ");
			}else if (fieldedName.equals("N_STATE")) {
				valueFieldbuf.append(" a.N_STATE like ? AND ");
			} else if (fieldedName.equals("N_ORDER")) {
				valueFieldbuf.append(" a.N_ORDER like ? AND ");
			} else if (fieldedName.equals("C_IE_TYPE")) {
				valueFieldbuf.append(" a.C_IE_TYPE = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(IeItemTableName.userInfo);
	}

	/**
	 * 根据时间戳获取所有收支项目字典表T_S_IE_ITEM数据
	 * @return
	 */
	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		buf.append(" ORDER BY A.N_ORDER ASC ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 根据收支项目类型获取收支项目sql
	 * @return
	 * @author liuxiang 2015-8-14 STORY #24240 太平资产收支结转功能优化
	 */
	public String getDataByIeTypes() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where A.C_IE_TYPE ");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
		buf.append(" ORDER BY A.N_ORDER ASC ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
}
