package com.yss.ams.base.information.modules.sys.voc.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * //STORY #83002 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志  edit by sunyanlin 20191202
 * @author lenovo
 *
 */
public class UcoVocSqlbuilder  implements SQLBuilder {
	
	
	
	/**
	 * 全量删除估值词汇表数据
	 * @return
	 */
	public String getDeleteAllUcoVocSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" DELETE FROM T_S_DV_VOC_UCO ");
		return buf.toString();
	}
	
	/**
	 * 插入数据
	 * @return
	 */
	public String getInsertUcoVocSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO T_S_DV_VOC_UCO (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_AUTH_ORG_CODE) ");
		buf.append(" VALUES (?, ?, ?, ?, ?, ?) ");
		return buf.toString();
	}
	
	/**
	 * 定向删除
	 * @return
	 */
	public String getDeleteUcoVocSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" DELETE FROM T_S_DV_VOC_UCO WHERE C_DV_CODE = ? ");
		return buf.toString();
	}
	
	/**
	 * 定向更新
	 * @return
	 */
	public String getUpdateUcoVocSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" UPDATE T_S_DV_VOC_UCO SET C_DV_NAME = ?, C_DV_TYPE = ?, C_DESC = ?, N_ORDER = ?, C_AUTH_ORG_CODE = ? ");
		buf.append(" WHERE C_DV_CODE = ? ");
		return buf.toString();
	}

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(UcoVocTableName.userInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(UcoVocColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
