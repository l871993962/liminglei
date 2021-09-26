package com.yss.ams.product.information.modules.ab.assetsTree_a.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * <A区资产树型结构>SQL语句构造类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTree_ASqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		String retSql = "";
		try{
		retSql = "select count(*) as CNT from T_P_AB_ASS_TR  a ";
		}catch(Exception ex){
			throw ex;
		}finally{
		}
		return retSql;
	}

	public String getQueryConditionSql(List<String> arg0) throws Exception {
		String retSql = "";
		try{
		retSql = "select * from T_P_AB_ASS_TR a ";
		}catch(Exception ex){
			throw ex;
		}finally{
		}
		return retSql;
	}
	
	public String getTreeViewQuerySql(List<String> paraNameList) throws Exception{
		StringBuffer buf = new StringBuffer();
		buf.append(" select a.*, ");
		buf.append(" 		a.C_TR_CODE_P as fParaentCode, ");
		buf.append(" 		a.C_TR_CODE as nodeCode, ");
		buf.append(" 		TRRULES.C_IDEN_RELA AS FLGZ_C_IDEN_RELA, ");
		buf.append(" 		TRRULES.C_CPSJWD AS C_CPSJWD, ");
		buf.append(" 		TRRULES.C_CPSJWD_FLCJ AS C_CPSJWD_FLCJ, ");
		buf.append(" 		TRRULES.C_ZCSXWD AS C_ZCSXWD, ");
		buf.append(" 		TRRULES.C_ZCSXWD_FLCJ AS C_ZCSXWD_FLCJ, ");
		buf.append(" 		1 as N_LEVEL ");
//		buf.append(" from T_P_AB_ASS_TR a ");
		buf.append(" from T_P_AB_ASS_TR a ");
		buf.append(" left join T_P_AB_ASS_TR_RULE trrules ");
		buf.append(" on a.c_iden = trrules.c_iden_rela ");
		//edit by zhoushuhang 2018-03-06 STORY49928产品树形结构界面优化
		if(paraNameList.contains("C_TR_CODE_P")){
//			buf.append(" from T_P_AB_ASS_TR a where (trim(c_user) is null or c_user = ?) AND C_TR_CODE_P = '[root]' ORDER BY N_ORDER");
			buf.append(" where (trim(c_user) is null or c_user = ?) AND C_TR_CODE_P = '[root]' ORDER BY N_ORDER");
		}else{
//			buf.append(" from T_P_AB_ASS_TR a where trim(c_user) is null or c_user = ? ");
			buf.append(" where trim(c_user) is null or c_user = ? ");
		}
		
		return buf.toString();
	}
	
	public String getDelOftenUsePorSql(){
		String sql = " DELETE FROM T_P_AB_ASS_TR T WHERE T.C_TR_CODE = ? AND T.C_DV_TR = ? ";
		return sql;
	}
	
	public String getOftenUsePortSql(List<String> paraNameList) throws Exception{
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select a.* from T_P_AB_ASS_TR a ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DV_TR")) {
				valueFieldbuf.append(" a.C_DV_TR = ?  AND ");
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
		return dbnameresolver.getColumnName(AssetsTree_AColumnName.valueOf(s));
	}
	
	public String getTreeViewColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(AssetTreeATreeViewColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(AssetsTreeTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(AssetsTreeTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(AssetsTreeTableName.userInfo);
	}

	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Task	  : STORY49928产品树形结构界面优化
	 * Comment: 获取更新sql
	 * @return
	 */
	public String getUpdateOrderSql(){
		String sql = " UPDATE T_P_AB_ASS_TR SET N_ORDER = ? WHERE C_TR_CODE = ? ";
		return sql;
	}
}
