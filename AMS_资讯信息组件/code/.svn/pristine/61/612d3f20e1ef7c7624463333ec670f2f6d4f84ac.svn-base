package com.yss.ams.sec.information.modules.mp.secTransfer.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * @ClassName SectransferSqlBuilder
 * @Description 证券代码转换sql
 * @author guohui
 * @CreateDate 2016年10月24日下午2:11:59
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class SectransferSqlBuilder implements SQLBuilder {
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		//boolean idQdType = true;
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		//if(idQdType){
			//by guohui 20170207 STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
			//交易渠道类型时
			buf.append(" select count(*) as CNT from T_D_MP_SEC_TRANSFER a left join T_P_AB_TD_CHAN b on a.C_SEC_CODE = b.c_td_chan_code ");
		//}else{
			//交易证券类型时
		//	buf.append(" select count(*) as CNT from T_D_MP_SEC_TRANSFER a left join T_P_SV_SEC_BASE b on a.C_SEC_CODE = b.C_SEC_CODE ");
		//}
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.N_CHECK_STATE asc ,a.C_UPDATE_TIME DESC ,a.C_CHECK_TIME DESC ");
		retSql = buf.toString();

		return retSql;
	}

	/**
	 * 查询条件
	 * @param paraNameList
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		boolean idQdType = true;
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		idQdType = paraNameList.contains("c_SEAT_TYPE");

		this.setWhereSql(valueFieldbuf, paraNameList);
		if(idQdType){
			//by guohui 20170207 STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
			//交易渠道类型时
			buf.append(" select distinct a.C_IDEN,a.C_SEC_CODE,a.C_TRANSFER_CODE,a.C_PUB_CODE,a.C_DATA_IDF, ");
			buf.append(" a.C_DESC,a.N_CHECK_STATE,a.C_UPDATE_BY,a.C_UPDATE_TIME,a.C_CHECK_BY,a.C_CHECK_TIME, ");
			buf.append(" a.c_type ");
			buf.append(" from T_D_MP_SEC_TRANSFER a left join T_P_AB_TD_CHAN b on a.C_SEC_CODE = b.c_td_chan_code ");
		}else{
			//交易证券类型时
			buf.append(" select distinct a.C_IDEN,a.C_SEC_CODE,a.C_TRANSFER_CODE,a.C_PUB_CODE,a.C_DATA_IDF, ");
			buf.append(" a.C_DESC,a.N_CHECK_STATE,a.C_UPDATE_BY,a.C_UPDATE_TIME,a.C_CHECK_BY,a.C_CHECK_TIME, ");
			buf.append(" b.C_SEC_ISIN_CODE,b.C_SEC_MKT_CODE,b.C_SEC_NAME,b.C_SEC_VAR_CODE,a.c_type ");
			buf.append(" from T_D_MP_SEC_TRANSFER a left join T_P_SV_SEC_BASE b on a.C_SEC_CODE = b.C_SEC_CODE ");
		}
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.N_CHECK_STATE asc ,a.C_UPDATE_TIME DESC ,a.C_CHECK_TIME DESC ");
		retSql = buf.toString();

		return retSql;
	}

	
	/**
	 * 整合前台条件
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_MKT_CODE")) {
				valueFieldbuf.append(" b.C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND "); //交易市场 in
			}else if(fieldedName.equals("C_TRANSFER_CODE")) {
				valueFieldbuf.append(" a.C_TRANSFER_CODE = ?  AND "); //转换规则
			}else if(fieldedName.equals("C_SEC_VAR_CODE")){
				valueFieldbuf.append(" b.C_SEC_VAR_CODE like ?  AND "); //证券品种
			}else if(fieldedName.equals("C_SEC_CODE")){
				valueFieldbuf.append(" a.C_SEC_CODE = ?  AND "); //证券品种
			}else if(fieldedName.equals("c_SEC_TYPE") || fieldedName.equals("c_SEAT_TYPE")){
				//by guohui 20170207 STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
				valueFieldbuf.append(" a.C_TYPE = ?  AND ");     //类别
			}else if(fieldedName.equals("c_DV_CHAN_TYPE")){
				valueFieldbuf.append(" b.c_DV_CHAN_TYPE like ?  AND ");     //渠道类别
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/**
	 * 根据证券内码集合删除证券转换信息
	 * STORY32744【南方基金】【v2.5需求】南方基金：社保资产证券代码新规则
	 * @Title buildDeleteBySecodes 
	 * @Description 该方法用于测试方法注释格式
	 * @author liuyanni@ysstech.com
	 * @date 2016年10月29日下午6:17:34
	 * @return
	 * @return String
	 */
	public String buildDeleteBySecodes(){
		String retSql = "";
		//BUG #152332 【南方基金】【紧急】债券基本信息按照社保理事会规则转换披露代码时，只覆盖自动生成的记录。modify by zhangquan  2017-02-17
		retSql = "delete from T_D_MP_SEC_TRANSFER a where a.C_SEC_CODE in (SELECT * FROM TABLE(?)) and a.C_DATA_IDF = 'Z'";
		return retSql;
	}
	
	/**
	 * 查询转换规则是否开启
	 * STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
	 * @Title getTradeRule 
	 * @author guohui@ysstech.com
	 * @date 2017年02月07日下午1:17:34
	 * @return
	 * @return String
	 */
	public String getTradeRule(){
		String retSql = "";
		retSql = "select * from T_D_MP_SEC_TRANSFERPARA where c_logical_judgment = 'Enabled' and c_item_code = ? and c_item_value like ? ";
		return retSql;
	}

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		return "select * from T_D_MP_SEC_TRANSFER";
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(SecTransferColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(SecTransferTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(SecTransferTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(SecTransferTableName.userInfo);
	}
	
	/**
	 * 
	 * @Title getQueryParamSql 
	 * @Description 组合自定义参数获取SQL, 参数需赋值组合代码和查询日期及参数代码
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月25日下午4:01:01
	 * @return
	 */
	public String getQueryParamSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select nvl(r.c_dv_params_value, l.c_dv_plat_value) as zqzhcode")
		.append("   from (select t.c_dv_plat_value ")
		.append("           from t_s_dsp_para t ")
		.append("          where t.c_dsp_code = ? ")
		.append("        ) l ")
		.append("   left join (select t.c_port_code, t.c_dv_params_value ")
		.append("                from t_p_ao_params t ")
		.append("               where t.c_dsp_code = ? ")
		.append("                 and t.n_check_state = 1 ")
		.append("                 and t.c_port_code = ? ")
		.append("                 and ? between t.d_begin and t.d_end ")
		.append("             ) r ")
		.append("     on 1 = 1 ");
		return sb.toString();
	}
}
