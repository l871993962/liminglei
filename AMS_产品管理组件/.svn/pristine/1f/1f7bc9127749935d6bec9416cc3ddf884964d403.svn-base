package com.yss.ams.product.information.modules.dataCopy.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;

/**
 * 
 * @author weijj 保存用户调整过的数据项数据
 * 
 */
public class DataCopyCustomBuilder implements SQLBuilder {

	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver dbNameResolver) {
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
		return null;
	}
	
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return "T_S_DATA_COPY_CUSTOM";
	}

	public String getInsertSql() {
		StringBuffer sb = new StringBuffer();
		sb
				.append("insert into T_S_DATA_COPY_CUSTOM (c_iden,c_user_code,c_data_code,c_fun_code) values (");
		sb.append("SEQU_S_DATA_COPY_CUSTOM.Nextval,'").append(
				ContextFactory.getContext().getUserCode()).append("',");
		sb.append("?,?)");
		return sb.toString();

	}

	public String getDelSql() {
		StringBuffer sb = new StringBuffer();
		sb.append("delete T_S_DATA_COPY_CUSTOM where c_fun_code = ? and c_user_code = '").append(
				ContextFactory.getContext().getUserCode()).append("'");
		return sb.toString();
	}
	
	public String getQuerySql(String fun_code){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  T_S_DATA_COPY_CUSTOM where c_fun_code = '");
		sb.append(fun_code).append("' and c_user_code = '").append(
				ContextFactory.getContext().getUserCode()).append("'");
		return sb.toString();
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @author liuxiang
	 * @date 2015年12月17日 STORY #26079 华泰证券：估值4.5_新组合自动添加到TA导出文件报表分组
	 * modified by liyanjun 2017-2-21 STORY35927新产品成立参数模板需求
	 */
	public String getCreateCopySql(){
		StringBuffer buf = new StringBuffer();
		//STORY #38001 【南方基金】新建组合：复制创建新组合时，增加“复制产品属性分类信息”功能
		buf.append(" SELECT C_DATA_NAME, DECODE(C_DATA_CODE, 'planRelaPort', C_DATA_PARA,'attributePdPort', C_DATA_PARA,C_DATA_CODE) ");
		buf.append(" AS C_DATA_CODE, C_DATA_CODE_P, C_DV_STATE, C_SERVICE_CODE, N_ORDER, C_DATA_PARA ");
		buf.append(" FROM T_S_DATA_COPY   ");
//		buf.append(" UNION SELECT c_rep_name AS C_DATA_NAME, c_rep_code AS C_DATA_CODE, 'Expconfig' AS C_DATA_CODE_P, ");
//		buf.append(" '1' AS C_DV_STATE,'IExpSettCopyService' AS C_SERVICE_CODE, to_number(c_iden) AS N_ORDER, ");
//		buf.append(" c_rep_code AS C_DATA_PARA FROM t_r_trep_def_basic JOIN (SELECT DISTINCT C_report_code ");
//		buf.append(" FROM t_r_trep_def_if_port) ON C_report_code = c_rep_code ");
//		buf.append(" WHERE c_rep_status='TEMP_USABLE' AND TRIM(C_EXP_MODE) IS NOT NULL ");
//		buf.append(" AND EXISTS(SELECT 1 FROM T_S_DATA_COPY WHERE c_data_code ='Expconfig') ");
		//edit by huangjin 2016-9-22 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
		//关联用户权限
//		buf.append(" union all ");
//		buf.append(" select distinct b.c_user_name,a.c_user_code,'RightManage','1','IRightManageCopyService',to_number(b.c_iden) as N_ORDER,' ' ");
//		buf.append(" from T_S_USER_POST_DATA a join t_s_user b on a.c_user_code = b.c_user_code where a.n_check_state = 1 and c_data_code = ? ");
//		buf.append(" union all ");
//		buf.append(" select b.c_post_name,a.c_user_code ||'_'|| a.c_post_code,a.c_user_code,'1','IRightManageCopyService',to_number(a.c_iden) as N_ORDER,' ' ");
//		buf.append(" from T_S_USER_POST_DATA a join t_s_post b on a.c_post_code = b.c_post_code where a.n_check_state = 1 and c_data_code = ? ");
		//关联运营费用设置
//		buf.append(" union all ");
//		buf.append(" select distinct b.c_fee_name,a.c_fee_code,'investFee','1','IInveFeeCopyService',to_number(b.c_iden) as N_ORDER,' ' ");
//		buf.append(" from T_P_AB_INVE_FEE a join t_p_bi_ie b on a.c_fee_code = b.c_fee_code where a.n_check_state = 1 and c_port_code = ? "); 
//		buf.append(" union all  ");
//		buf.append(" select distinct b.c_algo_name,a.c_fee_code||'@'||a.c_algo_code,a.c_fee_code,'1','IInveFeeCopyService',to_number(b.c_iden) as N_ORDER,' ' ");
//		buf.append(" from T_P_AB_INVE_FEE a join T_V_AA_ADV_ALGO b on a.c_algo_code = b.c_algo_code where a.n_check_state = 1 and c_port_code = ? "); 
		//关联产品指标配置
//		buf.append(" union all  ");
//		buf.append(" select distinct a.c_index_name,'indexmanage@'||a.c_index_code,'indexmanage','1','IIndexManageCopyService',to_number(a.c_iden) as N_ORDER,' ' ");
//		buf.append(" from T_P_AO_INDEX a  where a.n_check_state = 1 and c_port_code = ? ");
//		buf.append(" ORDER BY N_ORDER ");
		return buf.toString();
	}

	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * @param paraNameList
	 * @return
	 */
	public String getCopyCheckDataCountSql(List<String> paraNameList) throws Exception {
		String queryCountSql = "";
		StringBuffer buf = new StringBuffer();
		try {
			buf.append("select COUNT(*) AS CNT from (")
					.append(this.getCopyCheckDataSql(paraNameList))
					.append(")");
			queryCountSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return queryCountSql;
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 查询所选组合的各项目的复制情况
	 * @param paraNameList
	 * @return
	 */
	public String getCopyCheckDataSql(List<String> paraNameList) throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try{
			sqlBuffer.append(" SELECT C_IDEN,C_PORT_CODE,C_SOURCE_CODE,C_DATA_CODE,C_DATA_NAME,C_COPY_STATE FROM T_S_DATA_COPY_CHECK A WHERE ");
			buildCheckWhereSql(valueFieldbuf,paraNameList);
			if (valueFieldbuf.length() > 0){
				sqlBuffer.append(" ").append(valueFieldbuf);
			}
			sqlBuffer.append(" ORDER BY C_PORT_CODE,C_SOURCE_CODE ");
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
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * @param buf
	 * @param paraNameList
	 */
	private void buildCheckWhereSql(StringBuffer buf, List<String> paraNameList) {
//		buf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				buf.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}
		if (buf.length() > 0) {	
			StringUtil.delLastSplitMark(buf, " AND ");
		}
	}

	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 获取满足如下条件的sql:
	 * 组合 A已净值确认即THREETYPE (影响估值表、核算、清算的参数不能再次复制)
	 *       已存在凭证即TWOTYPE (影响核算、清算的参数不能再次复制)
	 *       已存在流水即ONETYPE (影响清算的项目不能再次复制)
	 * add by zhaijiajia 20190223
	 * @throws Exception 
	 */
	/*public String getPortCopyTypeSql() throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		try{
			sqlBuffer.append("	SELECT C_PORT_CODE, 	");
			sqlBuffer.append("	       CASE  	");
			sqlBuffer.append("	         WHEN SUM(TYPE3) = 1 THEN  	");
			sqlBuffer.append("	          'THREETYPE'  	");
			sqlBuffer.append("	         WHEN SUM(TYPE2) = 1 THEN  	");
			sqlBuffer.append("	          'TWOTYPE'  	");
			sqlBuffer.append("	         WHEN SUM(TYPE1) = 1 THEN  	");
			sqlBuffer.append("	          'ONETYPE'  	");
			sqlBuffer.append("	       END AS TYPE  	");
			sqlBuffer.append("	  FROM (SELECT DISTINCT C_PORT_CODE, 1 AS TYPE1, 0 AS TYPE2, 0 AS TYPE3  	");
			sqlBuffer.append("	          FROM V_D_AC_TRADE_ALL  	");
			sqlBuffer.append("	         WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?))  	");
			sqlBuffer.append("	           AND D_TRADE >= ?  	");
			sqlBuffer.append("	           AND N_CHECK_STATE = 1  	");
			sqlBuffer.append("	        UNION ALL  	");
			sqlBuffer.append("	        SELECT DISTINCT C_PORT_CODE, 0 AS TYPE1, 1 AS TYPE2, 0 AS TYPE3  	");
			sqlBuffer.append("	          FROM T_D_AI_ACT_VAL  	");
			sqlBuffer.append("	         WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?))  	");
			sqlBuffer.append("	           AND D_CHK_ACC >= ?  	");
			sqlBuffer.append("	        UNION ALL  	");
			sqlBuffer.append("	        SELECT DISTINCT C_PORT_CODE, 0 AS TYPE1, 0 AS TYPE2, 1 AS TYPE3  	");
			sqlBuffer.append("	          FROM T_E_CONFIRM  	");
			sqlBuffer.append("	         WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?))  	");
			sqlBuffer.append("	           AND C_EXECUTE = 'LOCK'  	");
			sqlBuffer.append("	           AND D_BIZ_DATE >= ? 	");
			sqlBuffer.append("	           AND C_BIZ_CLS = 'eConfirm')  	");
			sqlBuffer.append("	 GROUP BY C_PORT_CODE	");
			querySql = sqlBuffer.toString();
		}
		catch (Exception ex){
			throw ex;
		}
		finally{
			StringUtil.clearStringBuffer(sqlBuffer);
		}
		return querySql;
	}*/

	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 前台A区界面选择M个组合，B区展示所有明细项目N个，则保存M*N条复制记录
	 * @throws Exception 
	 * @return
	 */
	public String getInsertCopyInfosSql() throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		try{
			sqlBuffer.append("	  INSERT INTO T_S_DATA_COPY_CHECK	");
			sqlBuffer.append("	    (C_IDEN,	");
			sqlBuffer.append("	     C_PORT_CODE,	");
			sqlBuffer.append("	     C_SOURCE_CODE,	");
			sqlBuffer.append("	     C_DATA_CODE,	");
			sqlBuffer.append("	     C_DATA_NAME,	");
			sqlBuffer.append("	     C_COPY_STATE)	");
			sqlBuffer.append("	  VALUES	");
			sqlBuffer.append("	    (SEQU_S_DATA_COPY_CHECK.NEXTVAL, ?, ?, ?, ?, ?)	");
			querySql = sqlBuffer.toString();
		}
		catch (Exception ex){
			throw ex;
		}
		finally{
			StringUtil.clearStringBuffer(sqlBuffer);
		}
		return querySql;
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 前台A区界面选择M个组合，B区展示所有明细项目N个，则保存M*N条复制记录
	 * @throws Exception 
	 * @return
	 */
	public String getUpdteCopyInfosSql(String columnname) throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		try{
			sqlBuffer.append("	UPDATE T_S_DATA_COPY_CHECK SET 	");
			sqlBuffer.append(columnname);
			sqlBuffer.append("	= ?	");
			sqlBuffer.append("	WHERE C_PORT_CODE = ? 	");
			sqlBuffer.append("	AND C_DATA_CODE = ? 	");
			if ("C_SOURCE_CODE".equalsIgnoreCase(columnname)) {
				sqlBuffer.append("	AND C_COPY_STATE = ? 	");
			} else {
				sqlBuffer.append("	AND C_SOURCE_CODE = ? 	");
			}
			querySql = sqlBuffer.toString();

		}
		catch (Exception ex){
			throw ex;
		}
		finally{
			StringUtil.clearStringBuffer(sqlBuffer);
		}
		return querySql;
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 前台A区界面选择M个组合，B区展示所有明细项目N个，则保存M*N条复制记录
	 * @throws Exception 
	 * @return
	 */
	public String getdeleteCopyInfosSql() throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		try{
			sqlBuffer.append("	DELETE FROM T_S_DATA_COPY_CHECK 	");
			sqlBuffer.append("	WHERE C_PORT_CODE = ? 	");
			sqlBuffer.append("	AND C_DATA_CODE = ? 	");
			sqlBuffer.append("	AND C_SOURCE_CODE = ? 	");
			sqlBuffer.append("	AND C_COPY_STATE = '0' 	");
			querySql = sqlBuffer.toString();
		}
		catch (Exception ex){
			throw ex;
		}
		finally{
			StringUtil.clearStringBuffer(sqlBuffer);
		}
		return querySql;
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * 前台A区界面选择M个组合，B区展示所有明细项目N个，则保存M*N条复制记录
	 * @throws Exception 
	 * @return
	 */
	public String getCopyInfosTypeSql() throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		try{
			sqlBuffer.append("	SELECT CASE WHEN A.C_COPY_STATE = B.C_COPY_STATE AND A.C_SOURCE_CODE = B.C_SOURCE_CODE THEN 'NULL'	");
			sqlBuffer.append("	WHEN A.C_COPY_STATE = B.C_COPY_STATE AND A.C_COPY_STATE = '1' THEN 'C_SOURCE_CODE' 	");
			sqlBuffer.append("	WHEN A.C_SOURCE_CODE = B.C_SOURCE_CODE AND A.C_COPY_STATE = '0' THEN 'C_COPY_STATE'	");
			sqlBuffer.append("	WHEN A.C_SOURCE_CODE = B.C_SOURCE_CODE AND A.C_COPY_STATE = '1' THEN 'NULL'	");
			sqlBuffer.append("	  ELSE 'INSERT' END AS TYPE	");
			sqlBuffer.append("	  FROM T_S_DATA_COPY_CHECK A	");
			sqlBuffer.append("	  JOIN (SELECT ? C_PORT_CODE,? C_DATA_CODE,? C_SOURCE_CODE,? C_COPY_STATE FROM DUAL) B	");
			sqlBuffer.append("	  ON A.C_PORT_CODE = B.C_PORT_CODE	");
			sqlBuffer.append("	  AND A.C_DATA_CODE = B.C_DATA_CODE	");
			sqlBuffer.append("	  AND A.C_COPY_STATE = B.C_COPY_STATE	");
			sqlBuffer.append(" UNION ALL");
			sqlBuffer.append("	SELECT CASE WHEN A.C_COPY_STATE = B.C_COPY_STATE AND A.C_SOURCE_CODE = B.C_SOURCE_CODE THEN 'NULL'	");
			sqlBuffer.append("	WHEN A.C_COPY_STATE = B.C_COPY_STATE AND A.C_COPY_STATE = '1' THEN 'C_SOURCE_CODE' 	");
			sqlBuffer.append("	WHEN A.C_SOURCE_CODE = B.C_SOURCE_CODE AND A.C_COPY_STATE = '0' THEN 'C_COPY_STATE'	");
			sqlBuffer.append("	WHEN A.C_SOURCE_CODE = B.C_SOURCE_CODE AND A.C_COPY_STATE = '1' THEN 'NULL'	");
			sqlBuffer.append("	  ELSE 'INSERT' END AS TYPE	");
			sqlBuffer.append("	  FROM T_S_DATA_COPY_CHECK A	");
			sqlBuffer.append("	  JOIN (SELECT ? C_PORT_CODE,? C_DATA_CODE,? C_SOURCE_CODE,? C_COPY_STATE FROM DUAL) B	");
			sqlBuffer.append("	  ON A.C_PORT_CODE = B.C_PORT_CODE	");
			sqlBuffer.append("	  AND A.C_DATA_CODE = B.C_DATA_CODE	");
			sqlBuffer.append("	  AND A.C_SOURCE_CODE = B.C_SOURCE_CODE	");

			querySql = sqlBuffer.toString();
		}
		catch (Exception ex){
			throw ex;
		}
		finally{
			StringUtil.clearStringBuffer(sqlBuffer);
		}
		return querySql;
	}
}
