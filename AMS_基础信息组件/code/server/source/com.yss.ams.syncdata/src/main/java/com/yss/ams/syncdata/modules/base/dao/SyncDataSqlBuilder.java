package com.yss.ams.syncdata.modules.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 数据同步 SQL构造类
 * @author chenyoucai
 */
public class SyncDataSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(SyncDataColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(SyncDataTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(SyncDataTableName.tableName);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(SyncDataTableName.tableName);
	}
	
	/**
	 * 返回 根据前台查询条件获取ETF股票篮信息所有数据 的SQL
	 * @param paraNameList
	 * @return querySql
	 * @throws Exception
	 */
	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try{
			sqlBuffer.append("Select * from T_D_OD_SYNC_MQ a  ");
			buildWhereSql(valueFieldbuf,paraNameList);
			if (valueFieldbuf.length() > 0){
				sqlBuffer.append(" WHERE ").append(valueFieldbuf);
			}			
			sqlBuffer.append(" Order by C_DV_MODULE_CODE,C_RECEIVE_TIME ");
			querySql = sqlBuffer.toString();
		}
		catch (Exception ex){
			throw ex;
		}
		finally{
			StringUtil.clearStringBuffer(sqlBuffer);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return querySql;
	}
	
	/**
	 * 返回 根据前台查询条件获取ETF股票篮信息数据总数量 的SQL
	 * @param paraNameList
	 * @return queryCountSql
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		String queryCountSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer fieldValueBuf = new StringBuffer();		
		try{
			buf.append("SELECT COUNT(*) AS CNT from T_D_OD_SYNC_MQ a ");
			buildWhereSql(fieldValueBuf, paraNameList);
			if(fieldValueBuf.length() > 0){
				buf.append(" WHERE ").append(fieldValueBuf);
			}
			queryCountSql = buf.toString();
		}catch(Exception ex){
			throw ex;
		}finally {
			StringUtil.clearStringBuffer(buf);
		}
		return queryCountSql;
	}
	
	/**
	 * 根据前台传送过来参数判断添加筛选条件 的SQL
	 * @param buf
	 * @param paraNameList
	 */
	private void buildWhereSql(StringBuffer buf, List<String> paraNameList){
		for (String fieldedName : paraNameList){
			if(fieldedName.equalsIgnoreCase("ARRAY_C_DV_MODULE_CODE")){
				buf.append(" C_DV_MODULE_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if(fieldedName.equalsIgnoreCase(("D_START"))){
				buf.append(" to_date(C_RECEIVE_TIME,'yyyy-MM-dd HH24:MI:SS') >= to_date(?,'yyyy-MM-dd HH24:MI:SS') AND ");
			}else if(fieldedName.equalsIgnoreCase(("D_END"))){
				buf.append(" to_date(C_RECEIVE_TIME,'yyyy-MM-dd HH24:MI:SS') <= to_date(?,'yyyy-MM-dd HH24:MI:SS') AND ");
			}else if(fieldedName.equalsIgnoreCase("ARRAY_C_DV_STATE")){			
				buf.append(" C_DV_STATE IN (SELECT * FROM TABLE(?)) AND ");
			}else if(fieldedName.equalsIgnoreCase("C_SYSTEM_CODE")){			
				buf.append(" C_SYSTEM_CODE  = ? AND ");
			}
		}
		if (buf.length() > 0) {
			StringUtil.delLastSplitMark(buf, " AND ");
		}
	}
	
	
	/**
	 * 忽略消息:只有已接收状态下的消息才可忽略
	 * @return
	 */
	public String ignoreMessagesSql(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(" UPDATE T_D_OD_SYNC_MQ ");
		buffer.append(" SET C_DV_STATE = 'IGNORED',C_UPDATE_BY = ? , C_UPDATE_TIME = ? ");
		buffer.append(" WHERE C_IDEN IN (SELECT * FROM TABLE(?)) AND C_DV_STATE = 'RECEIVED'");
		return buffer.toString();
	}
	/**
	 * 数据同步成功，将消息状态改成已更新
	 * @return
	 */
	public String syncSuccessSql(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(" UPDATE T_D_OD_SYNC_MQ ");
		buffer.append(" SET C_DV_STATE = 'UPDATED' , C_UPDATE_BY = ? , C_UPDATE_TIME = ? ");
		buffer.append(" WHERE C_IDEN IN (SELECT * FROM TABLE(?)) AND C_DV_STATE = 'RECEIVED'");
		return buffer.toString();
	}
	
	/**
	 * 根据业务系统code获取该业务系统监听的功能模块
	 */
	public String getModuleCfgSql() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT C_MODULE_CODE FROM T_D_OD_SYNC_MQ_CFG ");
		buffer.append(" WHERE C_SYSTEM_CODE = ?");
		return buffer.toString();
	}
	
	/**
	 * 获取同步监听模块配置信息
	 */
//	public String queryAllFuncodeCfgSql() {
//		StringBuffer buffer = new StringBuffer();
//		buffer.append(" SELECT A.C_DV_CODE, A.C_DV_NAME , CFG.C_MODULE_CODE FROM T_S_DV_VOC A ");
//		buffer.append(" LEFT JOIN T_D_OD_SYNC_MQ_CFG CFG");
//		buffer.append(" ON C_SYSTEM_CODE = ? AND A.C_DV_CODE = CFG.C_MODULE_CODE ");
//		buffer.append(" WHERE C_DV_TYPE = 'SYNCFUNCODE'");
//		return buffer.toString();
//	}
	
	/**
	 * 删除已保存的数据同步模块设置
	 */
	public String deleteSyncModuleSql() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" DELETE FROM T_D_OD_SYNC_MQ_CFG");
		buffer.append(" WHERE C_SYSTEM_CODE = ? ");
		return buffer.toString();
	}
	
	/**
	 *保存数据同步模块设置
	 */
	public String saveSyncModuleSql() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" INSERT INTO T_D_OD_SYNC_MQ_CFG");
		buffer.append(" (C_IDEN,C_SYSTEM_CODE,C_MODULE_CODE,C_UPDATE_BY,C_UPDATE_TIME)");
		buffer.append(" VALUES (SEQU_D_OD_SYNC_MQ_CFG.NEXTVAL,?,?,?,?)");
		return buffer.toString();
	}
	
}
