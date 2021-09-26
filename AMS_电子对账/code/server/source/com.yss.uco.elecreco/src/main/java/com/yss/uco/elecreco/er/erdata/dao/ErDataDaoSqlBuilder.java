package com.yss.uco.elecreco.er.erdata.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class ErDataDaoSqlBuilder  implements SQLBuilder{

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

	public String getColumnNameByProperty(DBNameResolver arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionSql(List<String> arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRecycleTableName(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * BUG297716月报数据同一月内换一个日期生成  明细数据删除了  但生成报文记录没有删除
	 * @return
	 */
	public String checkDuplicate(){
		StringBuffer buf = new StringBuffer();
		//STORY73476【鹏华基金】并行组合电子对账需求
		buf.append(" SELECT A.C_IDEN, A.C_SN FROM T_D_ER_INFO A");
//		buf.append(" JOIN (SELECT C_ASS_CODE FROM T_P_AB_PORT WHERE C_PORT_CODE = ?) B");
//		buf.append(" ON A.C_ASS_CODE = B.C_ASS_CODE ");
		buf.append(" where A.C_PORT_CODE = ? ");
		buf.append(" and A.C_FILE_TYPE = ? AND A.D_DATE >= ? AND A.D_DATE <= ? AND C_STATE = ? AND A.C_RPT_TYPE = ? ");
		return buf.toString();
	}

	/**
	 * 拼接校验电子对账信息表对账数据发送状态sql
	 * @return
	 */
	public String checkStatusSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.C_IDEN, A.C_SN FROM T_D_ER_INFO A");
		//STORY73476【鹏华基金】并行组合电子对账需求
		//buf.append(" JOIN (SELECT C_ASS_CODE FROM T_P_AB_PORT WHERE C_PORT_CODE = ?) B");
		//buf.append(" ON A.C_ASS_CODE = B.C_ASS_CODE ");
		//根据“处理状态”来控制是否需要再次生成
		buf.append(" WHERE A.C_PORT_CODE = ? and A.C_FILE_TYPE = ? AND A.D_DATE = ? AND C_STATE = ? AND A.C_RPT_TYPE = ? ");
		return buf.toString();
	}
	
//	/**
//	 * 根据“处理状态”来删除上次生成的数据
//	 * @return
//	 */
//	public String deleteStatusSql()
//	{
//		StringBuffer buf = new StringBuffer();
//		buf.append(" delete FROM T_D_ER_INFO A");
//		buf.append(" where A.C_ASS_CODE in (SELECT C_ASS_CODE FROM T_P_AB_PORT WHERE C_PORT_CODE = ?) ");
//		buf.append(" and A.C_FILE_TYPE = ? AND A.D_DATE = ? AND C_STATE = ? AND A.C_RPT_TYPE = ? ");
//		return buf.toString();
//	}
	
	/**
	 * 拼接校验电子对账信息表对账数据发送状态sql
	 * @return
	 */
	public String deleteSql(String tableName, String erWay){
		StringBuffer buf = new StringBuffer();
		buf.append(" DELETE FROM ").append(tableName).append(" A");
		//BUG278098非功能测试 产生电子对账 发现SQLID 6x9j9a1tkkau9 执行时间比较长
		buf.append(" WHERE A.C_FILE_TYPE = ? AND A.C_ASS_CODE IN (SELECT C_ASS_CODE FROM T_P_AB_PORT WHERE C_PORT_CODE = ?) AND A.C_SN = ? ");
		if(!StringUtil.IsNullOrEmptyT(erWay)){
			buf.append(" AND A.C_DV_ER_WAY = 'FORWARD' ");
		}
		return buf.toString();
	}
	/**
	 * 拼接校验电子对账信息表对账数据发送状态sql
	 * @return
	 */
	public String updateUpdateTimeSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" update T_D_ER_INFO A set A.C_UPDATE_TIME = ? ");
		//根据“处理状态”来控制是否需要再次生成
		buf.append(" where A.C_File_type = ? and A.C_SN = ? ");
		return buf.toString();
		
	}
	
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
