package com.yss.ams.base.information.modules.sys.feeRelation.dao;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * @classDesc 费用关联sql处理
 * @version 1.0 2012-9-22
 * @author yh
 */
/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
public class FeeRelationSqlBuilder implements SQLBuilder {
	
	/**
	 * 获取费用关联表列名
	 * @param dbNameResolver name
	 */
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver
				.getColumnName(FeeRelationColumnName.valueOf(name));
	}
	
	/**
	 * 获取费用关联表名
	 * @param dbNameResolver
	 */
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(FeeRelationTableName.userInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildDeleteSql(com.yss.framework.
	 * db.sql.DBNameResolver)
	 */
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildInsertSql(com.yss.framework.
	 * db.sql.DBNameResolver)
	 */
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildSelectSql(com.yss.framework.
	 * db.sql.DBNameResolver)
	 */
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildUpdateSql(com.yss.framework.
	 * db.sql.DBNameResolver)
	 */
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据前台传递过来的参数条件拼接查询费用关联列表所有数据数目的sql
	 * @param paraNameList
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from T_S_DF_FEE_RELA a  ");
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
	 * 根据前台传递过来的参数条件拼接查询费用关联列表所有数据的sql
	 * @param paraNameList
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			sqlBuffer.append("SELECT * FROM T_S_DF_FEE_RELA A  ");
			setWhereSql(valueFieldbuf, paraNameList);
			if (valueFieldbuf.length() > 0) {
				sqlBuffer.append(" WHERE ");
				sqlBuffer.append(valueFieldbuf);
			}
			sqlBuffer.append(" ORDER BY A.N_ORDER,A.C_FEE_CODE ");
			querySql = sqlBuffer.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(sqlBuffer);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return querySql;
	}
	
	/**
	 * 根据前台传递参数判断哪些参数需要作为查询条件
	 * @param buf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer buf, List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
		    if (fieldedName.equals("C_FEE_CODE")) {
				buf.append(" a.C_FEE_CODE like ?  AND ");
			} else if (fieldedName.equals(("C_TD_TYPE"))) {
				buf.append("  a.C_TD_TYPE like ? AND ");
			} else if (fieldedName.equals("N_ORDER")) {
				buf.append(" a.N_ORDER like ? AND ");
			} else if (fieldedName.equals("C_IE_CODE")) {
				buf.append(" a.C_IE_CODE like ? AND ");
			} else if (fieldedName.equals("C_TD_TYPE")) {
				buf.append(" C_TD_TYPE = ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				buf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				buf.append(" a.C_DEL_TIME <= ? AND ");
			}
		}
		if (buf.length() > 0) {
			StringUtil.delLastSplitMark(buf, " AND ");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#getRecycleTableName(com.yss.framework
	 * .db.sql.DBNameResolver)
	 */
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogTableName(FeeRelationTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(FeeRelationTableName.userInfo);
	}
	
	/**
	 * 查询资产交易关联费用的sql
	 * chenchangyou
	 * 20141128
	 * @return
	 */
	public String queryRealtionSql(HashMap<String, Object> paraMap){
		String queryDic="";
		if(paraMap!=null&&!paraMap.isEmpty()){
			for(int i=0;i<paraMap.size();i++){
				String para=(String)paraMap.get(i+"");
				if(i==0){
					queryDic+="'"+para+"'";
				}else{
					queryDic+=",'"+para+"'";
				}			
			}
		}
		StringBuffer buf=new StringBuffer();
		buf.append("select rownum as c_iden,c_fee_code,c_ie_code,'ZCJY' as c_td_type, ");
		buf.append(" rownum as N_ORDER from ( ");
		buf.append(" select distinct a.c_fee_code,b.c_para_value as c_ie_code ");
		buf.append(" from T_P_CO_NS_TD_FEE a left join T_P_CO_NS_TD_PARA b");
		buf.append(" on a.c_iden=b.c_iden_rela where c_para_code='C_IE_CODE' and  a.c_iden in( ");
		buf.append(" select c_iden_rela from T_P_CO_NS_TD_PARA ");
		buf.append(" where c_para_value in( ");
		buf.append(queryDic);
		buf.append(" )))");
		return buf.toString();
	}
}
