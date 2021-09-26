package com.yss.ams.product.information.modules.dataCopy.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class DataCopyBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return dbNameResolver.getColumnName(DataCopyColumnName.valueOf(name));
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
//		StringBuffer sb = new StringBuffer(commonSql());
//		StringBuffer whereSql = new StringBuffer();
//		whereSql = getWhereSql(whereSql, paraNameList);
//		if (whereSql.length() > 0) {
//			sb.append(" where ");
//			sb.append(whereSql);
//		}
		return getFunTreeSql().toString();
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getTableName(DataCopyTableName.recycle);
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getTableName(DataCopyTableName.copydata);
	}

	private StringBuffer getWhereSql(StringBuffer whereSqlBuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("APPAY_C_DATA_CODE")) {
				whereSqlBuf
						.append(" a.c_data_code in (select * from table(?)) AND ");
			}
		}
		if (whereSqlBuf.length() > 0) {
			StringUtil.delLastSplitMark(whereSqlBuf, " AND ");
		}

		return whereSqlBuf;
	}

	public String commonSql() {
		return "select a.c_iden,a.c_data_name,a.c_data_para AS c_data_code,a.c_data_code AS c_data_code_p,a.c_service_code,a.c_dv_state,a.n_order from T_S_DATA_COPY a where a.c_dv_state = '1' ";
	}
	
	public String funSql(){
		return "";//"select b.c_iden, b.c_fun_name as c_data_name,b.c_fun_code as c_data_code , b.c_fun_code_p as c_data_code_p,'' as c_service_code,'1' as c_dv_state,b.n_order from v_s_fun b";
	}

	/**
	 * add by huangjin 2016-9-23 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
	 * 产品参数复制界面增加'权限管理'复选框
	 * @return
	 */
	public String userSql(){
		return "";//"select distinct b.c_iden,b.c_user_name ,a.c_user_code,'RightManage','IRightManageCopyService','1',to_number(b.c_iden) from t_s_user_rela a join t_s_user b on a.c_user_code = b.c_user_code where a.n_check_state = 1 and c_port_code = ?";
	}
	
	/**
	 * add by huangjin 2016-9-23 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
	 * @return
	 */
	public String postSql(){
		return "";//"select b.c_iden,b.c_post_name,a.c_user_code || '_' || a.c_post_code,a.c_user_code,'IRightManageCopyService','1',to_number(a.c_iden) from t_s_user_rela a join t_s_post b on a.c_post_code = b.c_post_code where a.n_check_state = 1 and c_port_code = ?";
	}
	
	/**
	 * add by huangjin 2016-9-23 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
	 * 产品参数复制界面增加'接口配置'复选框
	 * @return
	 */
	public String expSql(){
		return "";//"select c_iden,c_rep_name,c_rep_code,'Expconfig','IExpSettCopyService','1',to_number(c_iden) AS N_ORDER FROM t_r_trep_def_basic JOIN (SELECT DISTINCT C_report_code FROM t_r_trep_def_if_port) ON C_report_code = c_rep_code WHERE c_rep_status = 'TEMP_USABLE' AND TRIM(C_EXP_MODE) IS NOT NULL AND EXISTS (SELECT 1 FROM T_S_DATA_COPY WHERE c_data_code = 'Expconfig')";
	}
	//select * from (select a.c_iden,a.c_data_name,a.c_data_code,a.c_data_code_p,a.c_service_code,a.c_dv_state from T_S_DATA_COPY a where a.c_dv_state = '1'   union all select b.c_iden, b.c_fun_name as c_data_name,b.c_fun_code as c_data_code , b.c_fun_code_p as c_data_code_p,'' as c_service_code,'1' as c_dv_state from t_s_fun b ) c  start with c.c_data_Code_p in  (select c_data_code_p from T_S_DATA_COPY) Connect by prior  c.c_data_code_p = c.c_data_code
	
	public String getFunTreeSql(){
		StringBuffer sb = new StringBuffer();
//		sb.append("select distinct(C_DATA_CODE||C_DATA_CODE_P),C_DATA_CODE ,C_DATA_CODE_P,C_DATA_NAME,C_SERVICE_CODE,C_DV_STATE,N_ORDER from ( ");
//		sb.append("select * from (");
		sb.append(commonSql());
//		sb.append(" union all ");
		//STORY #85993 产品参数复制从FAST迁移到产品管理组件   baoqiaolin 将FAST相关表改为调用服务查询，移到dao层实现
//		sb.append(userSql());
//		sb.append(" union all ");
//		sb.append(postSql());
//		sb.append(" union all ");
//		sb.append(expSql());
//		sb.append(" union all ");
//		sb.append(funSql());
//		sb.append(" ) c  ");
//		sb.append(" start with c.c_data_Code_p in (select c_data_code from T_S_DATA_COPY where c_dv_state = '1'");
//		sb.append(" union all ");
//		sb.append(" select c_user_code from t_s_user_rela where c_port_code = ? ");
//		sb.append(" ) Connect by prior  c.c_data_code_p = c.c_data_code ");
//		sb.append(" order by c.N_Order,Level desc");
//		sb.append(" )");
		return sb.toString();
	}
	public String orderSql() {
		return " order by a.N_ORDER_BY";
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @return
	 * @throws Exception
	 */
	public String getInsertCopyRSql() throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("INSERT INTO R_S_DATA_COPY_R ");
		sqlBuffer.append(" (C_IDEN, C_DATA_NAME,C_DATA_CODE,C_DATA_CODE_P,C_DV_STATE,C_SERVICE_CODE,N_ORDER,C_DATA_PARA,ID_DATA_CODE)	");
		sqlBuffer.append(" VALUES ");
		sqlBuffer.append(" (SEQU_S_DATA_COPY.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)");
		querySql = sqlBuffer.toString();
		StringUtil.clearStringBuffer(sqlBuffer);		 
		return querySql;
	}
	
	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @return
	 * @throws Exception
	 */
	public String getSelectCopyRSql() throws Exception {
		StringBuffer sb = new StringBuffer(); 		
		sb.append("select distinct(C_DATA_CODE||C_DATA_CODE_P),C_DATA_CODE ,C_DATA_CODE_P,C_DATA_NAME,C_SERVICE_CODE,C_DV_STATE,N_ORDER from ( ");
		sb.append("select * from (");
		sb.append("select a.c_iden,a.c_data_name,a.c_data_code,a.c_data_code_p,a.c_service_code,a.c_dv_state,a.n_order from R_S_DATA_COPY_R a where a.c_dv_state = '1' and ID_DATA_CODE like 'tmp_%'");
		sb.append(" ) c  ");
		sb.append(" start with c.c_data_Code_p in (SELECT * FROM TABLE(?)) Connect by prior  c.c_data_code_p = c.c_data_code ");
		sb.append(" order by c.N_Order,Level desc");
		sb.append(" )");
		String querySql = sb.toString();
		StringUtil.clearStringBuffer(sb);		 
		return querySql;
	}
}
