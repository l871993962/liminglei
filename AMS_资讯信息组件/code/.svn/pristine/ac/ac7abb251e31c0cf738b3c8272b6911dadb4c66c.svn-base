package com.yss.ams.sec.information.modules.plateset.plate.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 板块  生成SQL类
 * @author 马向峰 拆分
 *@Date 20170531
 */
public class PlateSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> arg0) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		setWhereSql(valueFieldbuf, arg0);
		buf.append("select count(*) as CNT from t_p_bi_plate a ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" where ").append(valueFieldbuf);
			buf.append(" and ");
		}else {
			buf.append(" where ");
		}
		buf.append(" a.N_CHECK_STATE >= 0 start with a.c_plate_code_p ='[root]'");
		buf.append(" connect by prior a.c_plate_code = a.c_plate_code_p order by a.N_CHECK_STATE asc ");
		
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> arg0) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		setWhereSql(valueFieldbuf, arg0);
		buf.append("select a.* from t_p_bi_plate a ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" where ").append(valueFieldbuf);
		}
		buf.append(" start with a.c_plate_code_p ='[root]'");
		buf.append(" connect by prior a.c_plate_code = a.c_plate_code_p order by a.N_CHECK_STATE asc ");
		
		return buf.toString();
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			} else if(fieldedName.equals("ARRAY_C_PLATE_CODE")){
				valueFieldbuf.append("a.C_PLATE_CODE IN (SELECT * FROM TABLE(?)) AND ");
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
	
	public String getTreeViewQuerySql(List<String> paraNameList) throws Exception{
		StringBuffer buf = new StringBuffer();
		
		buf.append("select a.* ,a.C_PLATE_CODE as nodeCode,a.C_PLATE_CODE_P as fParaentCode,1 as N_LEVEL from t_p_bi_plate a ");
//		buf.append( commonSql.whereClause(getQuyConJObj())
		buf.append( " where a.N_CHECK_STATE >= 0 start with a.c_plate_code_p ='[root]'");
		buf.append( " connect by prior a.c_plate_code = a.c_plate_code_p order by a.N_CHECK_STATE asc ");
		return buf.toString();
	}
	
	/**
	 * 板块分类SQL
	 * @return
	 */
	public String getPlateCategotySQL(boolean flag){
		StringBuffer sqlBuff = new StringBuffer();
		//V4.5赢时胜2014年2月20日02_B.xls Modify by :zhengguiyu 20140220
		sqlBuff.append(" select a.* from t_p_bi_plate a  where a.N_CHECK_STATE > 0 ");
		sqlBuff.append(" start with a.c_plate_code_p ='[root]'");
//		zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错   (关联结构设置界面行业类别下拉框选项) 加载版块信息设置中版块代码为SAC的数据
		if(flag == false)
		{
			sqlBuff.append("and a.c_plate_code = 'SAC'");
		}
		sqlBuff.append(" connect by prior  a.c_plate_code = a.c_plate_code_p order by a.N_CHECK_STATE asc ");
		return sqlBuff.toString();		   
	}
	
	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(PlateColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(PlateTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(PlateTableName.userInfo);
	}
	
	public String getColumnNameByPropertyByPlate_A(DBNameResolver dbnameresolver,String s){
		return dbnameresolver.getColumnName(PlateATreeViewColumnName.valueOf(s));
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(PlateTableName.userInfo);
	}
	
	/**
	 * 查询板块信息是否有子节点
	 */
	public String getSubDataSql(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT COUNT(C_IDEN) ");
		buf.append(" FROM T_P_BI_PLATE_SUB ");
		buf.append(" WHERE C_PLATE_CODE IN (");
		buf.append(" SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		
		return sql;
		
	}

	/**开始数据服务**/
	
	public String getsubConvertSql(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		//// 版本信息只有 T_P_BI_PLATE 数据进行提供代码转名称，T_P_BI_PLATE_SUB 没有特殊数据 
		//// By Jinghehe 2017-8-11 
//		buf.append(" SELECT DISTINCT(SUB.C_PLATE_CODE), PLATE.C_PLATE_NAME ");
//		buf.append(" FROM T_P_BI_PLATE_SUB SUB JOIN T_P_BI_PLATE PLATE ");
//		buf.append(" ON SUB.C_PLATE_CODE = PLATE.C_PLATE_CODE ");
		buf.append(" SELECT C_PLATE_CODE, C_PLATE_NAME ");
		buf.append(" FROM T_P_BI_PLATE PLATE where N_CHECK_STATE > 0");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getPojoByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		//// 版本信息只有 T_P_BI_PLATE 数据进行提供代码转名称，T_P_BI_PLATE_SUB 没有特殊数据 
		//// By Jinghehe 2017-8-11 
//		buf.append(" SELECT DISTINCT(SUB.C_PLATE_CODE), PLATE.C_PLATE_NAME ");
//		buf.append(" FROM T_P_BI_PLATE_SUB SUB JOIN T_P_BI_PLATE PLATE ");
//		buf.append(" ON SUB.C_PLATE_CODE = PLATE.C_PLATE_CODE ");
//		buf.append(" WHERE PLATE.C_PLATE_CODE = ? ");
		
		buf.append(" SELECT  PLATE.* ");
		buf.append(" FROM T_P_BI_PLATE PLATE ");
		buf.append(" WHERE PLATE.C_PLATE_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getKeyConvertListMap(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		//// 版本信息只有 T_P_BI_PLATE 数据进行提供代码转名称，T_P_BI_PLATE_SUB 没有特殊数据 
		//// By Jinghehe 2017-8-11 
//		buf.append(" SELECT DISTINCT(SUB.C_PLATE_CODE), PLATE.C_PLATE_NAME ");
//		buf.append(" FROM T_P_BI_PLATE_SUB SUB JOIN T_P_BI_PLATE PLATE ");
//		buf.append(" ON SUB.C_PLATE_CODE = PLATE.C_PLATE_CODE ");
//		buf.append(" WHERE SUB.C_PLATE_CODE IN ( SELECT * FROM TABLE(?) ) ");
		
	//	buf.append(" SELECT C_PLATE_CODE, C_PLATE_NAME ");
		//buf.append(" FROM T_P_BI_PLATE PLATE where N_CHECK_STATE > 0 and C_PLATE_CODE IN ( SELECT * FROM TABLE(?) ) ");
	
		buf.append(" SELECT DISTINCT(SUB.C_PLATE_FLBZ||'_'||SUB.C_PLATE_CODE) C_PLATE_CODE, PLATE.C_PLATE_NAME ");
		buf.append(" FROM T_P_BI_PLATE_SUB SUB JOIN T_P_BI_PLATE PLATE ");
		buf.append(" ON SUB.C_PLATE_CODE = PLATE.C_PLATE_CODE and SUB.c_plate_flbz=PLATE.c_plate_flbz");
		buf.append(" WHERE (SUB.C_PLATE_FLBZ||'_'||SUB.C_PLATE_CODE) IN ( SELECT * FROM TABLE(?) ) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getDataList() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" select a.* from t_p_bi_plate a where a.N_CHECK_STATE = 1 ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**数据服务结束**/
	
}
