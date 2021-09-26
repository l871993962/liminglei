//package com.yss.ams.product.information.modules.cp.fax.dao;

//import java.util.List;

//import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
//import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
//import com.yss.framework.api.mvc.dao.sql.SqlUtil;
//import com.yss.framework.api.util.StringUtil;

//public class ElecSealRelaSqlBuilder implements SQLBuilder {

//	@Override
//	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public String getTableName(DBNameResolver dbNameResolver) {
//		return dbNameResolver.getTableName(SealRelaInfoTableName.tableInfo);
//	}

//	@Override
//	public String getColumnNameByProperty(DBNameResolver dbNameResolver, String name) {
//		return dbNameResolver.getColumnName(SealRelaInfoColumnName.valueOf(name));
//	}

//	@Override
//	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
//		return null;
//	}
	
	/**
	 * 查询条件
	 * @Title setWhere 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年8月16日下午9:03:54
	 * @param buf
	 * @param paraNameList
	 * @return void
	 */
//	private void setWhere(StringBuffer buf, List<String> paraNameList){
//		if (paraNameList != null && paraNameList.size() > 0) {
//			buf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
//			for(String paraName : paraNameList){
//				if("C_RELA_CLASS".equalsIgnoreCase(paraName)){
//					buf.append(" a.C_RELA_CLASS = ? AND ");
//				}else if("C_FUN_CODE".equalsIgnoreCase(paraName)){
//					buf.append(" a.C_FUN_CODE = ? AND ");
//				}else if("ARRAY_C_FUN_CODE".equalsIgnoreCase(paraName)){
//					buf.append(" a.C_FUN_CODE IN (SELECT * FROM TABLE(?)) AND ");
//				}else if("C_SEAL_TYPE".equalsIgnoreCase(paraName)){
//					buf.append(" a.C_SEAL_CODE IN (SELECT C_SEAL_CODE FROM T_S_SEAL_FILE WHERE C_SEAL_TYPE = ? ) AND ");
//				}else if("C_SEAL_USER".equalsIgnoreCase(paraName)){
//					buf.append(" a.C_SEAL_CODE IN (SELECT C_SEAL_CODE FROM T_S_SEAL_FILE WHERE C_SEAL_USER = ? ) AND ");
//				}else if("C_SEAL_CODE".equalsIgnoreCase(paraName)){
//					buf.append(" a.C_SEAL_CODE like ? AND ");
//				}else if(paraName.startsWith("EXT_")){
//					buf.append(" a.C_IDEN IN (SELECT C_IDEN_RELA FROM T_S_ELECSEAL_EXT WHERE C_FIELD_CODE = ? AND (C_FIELD_VALUE IS NULL OR C_FIELD_VALUE IN (SELECT * FROM TABLE(?)))) ");
//				}
//			}
			
//			StringUtil.delLastSplitMark(buf, " AND ");
//		}
//	}

//	@Override
//	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
//		StringBuffer buf = new StringBuffer();
//		buf.append("select * from t_s_elecseal_base a ");
//		StringBuffer whereBuf = new StringBuffer();
//		setWhere(whereBuf, paraNameList);
//		if(whereBuf.length()>0){
//			buf.append(" where ");
//			buf.append(whereBuf);
//		}
		
//		buf.append(" ORDER BY a.C_UPDATE_TIME DESC ");
//		return buf.toString();
//	}

//	@Override
//	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
//		StringBuffer buf = new StringBuffer();
//		buf.append("select count(1) from t_s_elecseal_base a ");
//		StringBuffer whereBuf = new StringBuffer();
//		setWhere(whereBuf, paraNameList);
//		if(whereBuf.length()>0){
//			buf.append(" where ");
//			buf.append(whereBuf);
//		}
//		return buf.toString();
//	}
	
	
	/**
	 * 查询扩展表信息
	 * @Title getQueryExtSQL 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年8月18日下午5:18:33
	 * @return
	 * @return String
	 */
//	public String getQueryExtSQL(){
//		StringBuffer buf = new StringBuffer();
//		buf.append(" SELECT * FROM T_S_ELECSEAL_EXT A  ");
//		buf.append(" WHERE ");
//		buf.append(" A.C_IDEN_RELA IN (SELECT * FROM TABLE(?)) ");
//		return buf.toString();
//	}
	
	/**
	 * 获取插入扩展表的SQL
	 * @Title getInsetExtSQL 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年8月18日下午4:36:28
	 * @return
	 * @return String
	 */
//	public String getInsetExtSQL(){
//		StringBuffer buf = new StringBuffer();
//		buf.append(" INSERT INTO T_S_ELECSEAL_EXT A  ");
//		buf.append(" (C_IDEN_RELA, C_FIELD_CODE,C_FIELD_VALUE,N_TYPE,N_ORDER) ");
//		buf.append(" VALUES ");
//		buf.append(" (?,?,?,?,?) ");
//		return buf.toString();
//	}

	/**
	 * 获取删除扩展表的SQL
	 * @Title getDeleteExtSQL 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年8月18日下午4:37:36
	 * @return
	 * @return String
	 */
//	public String getDeleteExtSQL(){
//		StringBuffer buf = new StringBuffer();
//		buf.append(" DELETE FROM T_S_ELECSEAL_EXT A ");
//		buf.append(" WHERE A.C_IDEN_RELA IN (SELECT * FROM TABLE(?)) ");
//		return buf.toString();
//	}
	
	/**
	 * 根据id删除主表
	 * @Title getDeleteBaseSQL 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年8月18日下午5:35:32
	 * @return String
	 */
//	public String getDeleteBaseSQL(){
//		StringBuffer buf = new StringBuffer();
//		buf.append(" DELETE FROM T_S_ELECSEAL_BASE A ");
//		buf.append(" WHERE A.C_IDEN IN (SELECT * FROM TABLE(?)) ");
//		return buf.toString();
//	}
	
	/**
	 * 根据id跟主表数据
	 * @Title getUpdateBaseByIdSQL 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年8月18日下午5:50:28
	 * @return String
	 */
//	public String getUpdateBaseByIdSQL(){
//		StringBuffer buf = new StringBuffer();
//		buf.append(" UPDATE T_S_ELECSEAL_BASE A ");
//		buf.append(" SET ");
//		buf.append(" A.C_SEAL_CODE = ?, ");
//		buf.append(" A.C_FUN_CODE = ?, ");
//		buf.append(" A.C_DIS_PAGE = ?, ");
//		buf.append(" A.C_POSITION = ?, ");
//		buf.append(" A.N_MARGINX = ?, ");
//		buf.append(" A.N_MARGINY = ?, ");
//		buf.append(" A.N_WIDTH = ?, ");
//		buf.append(" A.N_HEIGHT = ?, ");
//		buf.append(" A.C_UPDATE_BY = ? , ");
//		buf.append(" A.C_UPDATE_TIME = ? , ");
//		buf.append(" A.C_CHECK_BY = ? , ");
//		buf.append(" A.C_CHECK_TIME = ? , ");
//		buf.append(" A.N_CHECK_STATE = ?  ");
//		buf.append(" WHERE A.C_IDEN = ? ");
//		return buf.toString();
//	}
	

//}
