package com.yss.ams.sec.information.modules.sv.base.dao;

import java.util.List;

import com.yss.ams.base.information.support.bi.mkt.service.IMktDataService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;

/***
 * 证券基本信息 SQL工具类
 * @author 马向峰
 *
 */
public class SecBaseInfoSqlBuilder implements SQLBuilder {

	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(SecBaseTableName.userInfo);
	}

	public String getColumnNameByProperty(DBNameResolver dbNameRsv,
			String propName) {
		return dbNameRsv.getColumnName(SecBaseColumnName.valueOf(propName));
	}

	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(SecBaseTableName.recycle);
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* START 数据服务 */

	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		//getCommonOrderSqlBuf(buf);
		getCommonQuerySqlBufOfZcfz(buf);//新增资产负债的品种查询 马可
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 根据证券的 币种代码、交易市场代码、证券品种代码、资产负债的品种 组装查询条件
	 * @author 马向峰 拆分 20170627
	 * @param filter
	 * @return
	 */
	public String getAllDataSql(SecBase filter) {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		//getCommonOrderSqlBuf(buf);
		if(filter.getC_DC_CODE() != null && filter.getC_DC_CODE()
				.trim().length() > 0){
			buf.append(" and A.C_DC_CODE = '"+filter.getC_DC_CODE()+"'");
		}
		if(filter.getC_MKT_CODE() != null && filter
				.getC_MKT_CODE().trim().length() > 0){
			buf.append(" AND A.C_MKT_CODE = '"+filter.getC_MKT_CODE()+"'");
		}
		if(filter.getC_SEC_VAR_CODE() != null && filter
				.getC_SEC_VAR_CODE().trim()
				.length() > 0){
			buf.append(" AND A.C_SEC_VAR_CODE = '"+filter.getC_SEC_VAR_CODE()+"'");
		}
		
		getCommonQuerySqlBufOfZcfz(buf);//新增资产负债的品种查询 马可
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 根据 证券代码、上市日期、退市日期 组合查询条件
	 * @return
	 */
	public String getDataByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);

		buf.append(" and C_SEC_CODE = ? ");
		buf.append(" and D_TO_LIST = ? ");
		buf.append(" and D_OFF_LIST = ? ");
		getCommonOrderSqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataBySecCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);

		buf.append(" and C_SEC_CODE = ? ");
		getCommonOrderSqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getDataBySecList() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);

		buf.append(" and C_SEC_CODE not in (select c_port_code from R_D_CLR_PARAM ) ");
		getCommonOrderSqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	
	public String getDataListByTypes(String[] Types) {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		sql = whereTypes(Types);
		if (sql.trim().length() > 0) {
			buf.append(" AND (");
			buf.append(sql);
			buf.append(")");
		}

		getCommonOrderSqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getShortDataList(String[] Types,String like) {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		
		//用TYPES拼接OR条件
		sql = whereTypes(Types);
		if (sql.trim().length() > 0) {
			buf.append(" AND (");
			buf.append(sql);
			buf.append(")");
		}
		
		if(like != null && like.length() > 0){
			buf.append(" AND ");
			buf.append("C_SEC_CODE LIKE '%" + like +"%'");
		}

		getCommonOrderSqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	

	/**
	 * 动态生成条件类型byleeyu20130518
	 * 
	 * @param types
	 *            品种类型关系为 或者
	 * @return
	 */
	private String whereTypes(String[] types) {
		StringBuffer buf = new StringBuffer();
		for (String type : types) {
			if (type != null && type.trim().length() > 0) {
				buf.append(" a.C_SEC_VAR_CODE LIKE ? OR ");
			}
		}

		if (buf.length() > 3) {
			buf.setLength(buf.length() - 3);
		}
		return buf.toString();
	}

	private void getCommonQuerySqlBuf(StringBuffer buf) {
		//合并需求：STORY #38433 南方基金-OA债券分销：债券临时代码和正式代码设置转换关系 ，增加字段 c_fx_code
		buf.append(" select a.c_iden,a.c_sec_code,a.c_sec_name,a.c_sec_mkt_code,  a.c_sec_isin_code, a.c_fx_code, a.d_tra_begin,a.d_tra_end, a.c_mkt_code,a.c_sec_var_code,");
		buf.append(" a.c_dc_code,a.n_price_fcr,a.c_sec_code_trg,  a.n_amount_hd,a.n_fv_issue, a.d_to_list,");
		buf.append(" a.d_off_list,  a.c_dv_var_dur,a.c_dv_qut_mod,a.n_rate,a.n_fv_ir,  a.n_price_issue,a.c_dv_ai_mod,a.c_dv_pi_mod,");
		buf.append(" a.d_ai_begin, a.d_ai_end,a.c_desc,a.n_check_state,  a.c_update_by,a.c_update_time,a.c_check_by,  a.c_check_time,");
		buf.append(" a.c_dv_ai_expr,a.c_org_code,a.c_org_name,  a.c_open_acc_name,a.c_open_acc_no,a.c_sys_code,");
		buf.append(" a.c_credit_rating,  a.c_dv_assure,a.c_dv_issue,a.c_sec_name_cn,a.n_ratio, a.d_end,a.C_ETF_TYPE, b.c_da_code");
		buf.append(" ,a.c_DV_RIGHT,a.c_sett_org,a.C_RZRQ_MARK ");
		/* (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式 
		 *  新增利率类型 利差 利率代码 比例系数 检查间隔 利率上限 下限 间隔时间周期 天数*/
		buf.append(" , a.C_DV_RTA,a.N_SPREAD,a.c_expr_code,a.N_BLXS,a.c_jcjg,a.n_upper_limit,a.n_lower_limit,a.C_interval_time ,a.c_INTERVAL_Day ");
		/*liuxiang 2016-5-11 添加组合代码 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能*/
		//合并需求：STORY35879【招商基金】【QDII】远期外汇需支持交割期间部分多次平仓业务
		buf.append(" , a.C_PORT_CODE, a.D_SQAI_BEGIN , a.c_pcyk_cury ");
		//add by zhd 2016-09-08
		//STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
		//实际所属证券 
		buf.append(" ,a.C_SJSSZQ, a.C_JTJZ4STD_SETT, a.c_JTJZ4NOTSTD_SETT ");
		buf.append(" , c_tgr_code,c_tgr_name,n_xsfwfl,n_glf_rate,n_tgf_rate, n_today_interest ,N_FESX,N_FEXX ");
		buf.append(" ,a.C_BANK_CODE,a.C_BRANCH_BANK_CODE ");
		//huangkaixuan 20217-29 STORY109057博时基金-公募2.5已实现-存放业务品种信息节假日群的选择
		buf.append(" ,a.c_sec_var_cls,a.c_HDAY_CODE_CK ");
		// STORY37961财税140号文件，针对资管增值税系统改造需求 添加是否保本，金融商品字段
		buf.append(" , A.C_FINA_COMM, A.C_GUAR_TYPE ");
		//合并需求 STORY #47088 债券基本信息，增加发行机构类型字段，区分金融机构和非金融机构
		//STORY #55718 【南方基金】关于香港市场节假日与国内节假日出现非对称节假日情况的估值问题
		buf.append(",A.D_TO_LIST_ZQ,A.C_MAIN_PROP");
		// add By HeMing 集资方式
		buf.append(" ,A.c_jzfs_code");
		buf.append(" ,A.N_BASE_DATE ");
		buf.append(" ,A.N_PAY_DATE ");
		buf.append(" ,A.C_PAY_DATE_TYPE ");
		buf.append(" ,A.C_BASE_DATE_TYPE ");
		//STORY #62079 人保资产-支持人保资产新科目体系核算
		buf.append(" ,A.C_KZCD_GQ ,A.C_DJPX_OPEN ");
		buf.append(" ,A.N_JXTS,A.C_PAY_CALCULATE ");
		buf.append(" ,A.N_RATE_AT ");
		//modify by ZhangJM  STORY #76306 银华基金：投资分级基金A类产品，每日计提收益，卖出时进行冲减
		buf.append(" ,A.N_FV_DAY_IR ");
		//modify by zhoushuhang 20200917 BUG334415 STORY#86134   I9科目体系  证券送配业务问题,这里增加金融工具字段
		buf.append(" ,A.C_FINANCE_TOOL ");
		//add by zhoushuhang 20210115 STORY95896华夏基金QD-彭博证券信息相关接口，对已有券更新字段需求
		buf.append(" ,A.C_MANAGER_NAME, A.C_FUND_NAME ");
		//modify by zhoushuhang 20210308 BUG356619计提所得税（包含债券利息税、股息红利税、基金股利税），税费方案设置优先级匹配逻辑优化
		buf.append(" ,A.C_LC_SEC_TAG ");
		//STORY #76847 【华宝基金】511990 SH华宝添益红利投资特殊处理 
		buf.append(" ,A.C_INVEST_ACCOUNT ");
		//BUG #327840 债券品种信息导入字段错误问题
		buf.append(" ,NVL(TRIM(A.C_DV_PLAT),' ') as C_DV_PLAT");
		//BUG #340267 存放品种信息新增字段刷新缓存无效 增加字段查询
		buf.append(" ,A.C_ZCFS,A.C_ZCPL");
		getSecbaseCommonsql(buf);
//		buf.append(" from T_P_SV_SEC_BASE a");
//		buf.append(" left join (select c_da_code, c_sec_var_code");
//		buf.append(" from T_S_DA_SEC_VAR ) b");
//		buf.append(" on a.c_sec_var_code = b.c_sec_var_code");
//		// modified by HeLiang 2017-08-08 STORY #44600 财汇基金品种信息PAR_BASEFUND_INFO清算进系统时要展示未审核状态
//		// 比对更新字段时需要通过缓存来取待更新证券实体，所以不能只加载已审核证券，未审核证券也需要更新
//		//buf.append(" WHERE  a.n_check_state = 1");
//		buf.append(" WHERE b.c_da_code is not null");
//		//add by zhd 2016-09-19
//		//STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
//		//buf.append(" and a.c_sjsszq is null ");
	}
	
	private void getSecbaseCommonsql(StringBuffer buf){
		buf.append(" from T_P_SV_SEC_BASE a");
		buf.append(" left join (select c_da_code, c_sec_var_code");
		buf.append(" from T_S_DA_SEC_VAR ) b");
		buf.append(" on a.c_sec_var_code = b.c_sec_var_code");
		buf.append(" WHERE b.c_da_code is not null");
	}
	
	
	/**
	 * BUG #338952 【回归】华夏基金（300.7.20200831.0929）-——缓存问题
	 * @param buf
	 */
	public void getCommonQueryCountSqlBuf(StringBuffer buf) {
		
		buf.append(" select count(1) num ");
		getSecbaseCommonsql(buf);
		
	}
	
	
	/**
	 * 增加资产负债品种信息
	 * @param buf
	 */
	private void getCommonQuerySqlBufOfZcfz(StringBuffer buf){
		buf.append(" union all ");
		buf.append(" select a.c_iden,a.c_sec_code,a.c_sec_name,a.c_sec_mkt_code,");
		//合并需求：STORY #38433 南方基金-OA债券分销：债券临时代码和正式代码设置转换关系 ，增加字段 c_fx_code
		buf.append(" a.c_sec_isin_code, a.c_fx_code,");		
		//合并需求：STORY #39209 【南方基金】【紧急】债券基本信息增加两个字段，可以维护债券转股期
		buf.append(" decode(a.d_tra_begin,'',to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd'),a.d_tra_begin) as  d_tra_begin,");
		//合并需求：STORY #39209 【南方基金】【紧急】债券基本信息增加两个字段，可以维护债券转股期
		buf.append(" decode(a.d_tra_end,'',to_date('9998/12/31','yyyy-MM-dd'),a.d_tra_end) as d_tra_end,");
		buf.append("a.c_mkt_code,a.c_sec_var_code,");
		buf.append(" a.c_dc_code,a.n_price_fcr,a.c_sec_code_trg,");
		buf.append(" a.n_amount_hd,a.n_fv_issue,");
		buf.append(" decode(a.d_to_list,'',to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd'),a.d_to_list) as  d_to_list,");
		buf.append(" decode(a.d_off_list,'',to_date('9998/12/31','yyyy-MM-dd'),a.d_off_list) as d_off_list,");
		buf.append(" a.c_dv_var_dur,a.c_dv_qut_mod,a.n_rate,a.n_fv_ir,");
		buf.append(" a.n_price_issue,a.c_dv_ai_mod,a.c_dv_pi_mod,");
		buf.append(" decode(a.d_ai_begin,'',to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd'),a.d_ai_begin) as  d_ai_begin,");
		buf.append(" decode(a.d_ai_end,'',to_date('9998/12/31','yyyy-MM-dd'),a.d_ai_end) as d_ai_end,");
		buf.append(" a.c_desc,a.n_check_state,");
		buf.append(" a.c_update_by,a.c_update_time,a.c_check_by,");
		buf.append(" a.c_check_time,a.c_dv_ai_expr,a.c_org_code,a.c_org_name,");
		buf.append(" a.c_open_acc_name,a.c_open_acc_no,a.c_sys_code,a.c_credit_rating,");
		buf.append(" a.c_dv_assure,a.c_dv_issue,a.c_sec_name_cn,a.n_ratio,");
		buf.append(" decode(a.d_end,'',to_date('9998/12/31','yyyy-MM-dd'),a.d_end) as d_end,");
		buf.append(" ' ' AS C_ETF_TYPE,b.c_da_code");
		buf.append(" ,'NULLRIGHT' as c_DV_RIGHT,' ' as c_sett_org,' ' as C_RZRQ_MARK ");
		/* (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式 */
		buf.append(" , ' ' as C_DV_RTA,0.00 as N_SPREAD,' ' as c_expr_code,0.00 as N_BLXS,' ' as c_jcjg,0.00 as n_upper_limit,0.00 as n_lower_limit,' ' as C_interval_time ,' ' as c_INTERVAL_day");	
		/*liuxiang 2016-5-11 增加组合代码 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能*/
		buf.append(" , ' ' as C_PORT_CODE, ");
		buf.append(" decode(a.D_SQAI_BEGIN,'',to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd'),a.D_SQAI_BEGIN) as  D_SQAI_BEGIN ");
		/* 合并需求：STORY35879【招商基金】【QDII】远期外汇需支持交割期间部分多次平仓业务  远期品种信息 增加平仓盈亏币种*/
		buf.append(" ,' ' as c_pcyk_cury ");
		//add by zhd 2016-09-08
		//STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
		//实际所属证券 
		buf.append(" ,' ' AS C_SJSSZQ, ' ' as C_JTJZ4STD_SETT, ' ' as c_JTJZ4NOTSTD_SETT ");
		buf.append(" ,c_tgr_code,c_tgr_name,n_xsfwfl,0 as n_glf_rate,0 as n_tgf_rate ,n_today_interest ,N_FESX,N_FEXX ");
		buf.append(" ,' ' as C_BANK_CODE,' ' as C_BRANCH_BANK_CODE ");
		//huangkaixuan 20217-29 STORY109057博时基金-公募2.5已实现-存放业务品种信息节假日群的选择
		buf.append(" ,a.c_sec_var_cls,' ' as c_HDAY_CODE_CK ");
		// STORY37961财税140号文件，针对资管增值税系统改造需求 添加是否保本，金融商品字段
		buf.append(" , ' ' AS C_FINA_COMM, ' ' AS C_GUAR_TYPE ");
		//STORY #55718 【南方基金】关于香港市场节假日与国内节假日出现非对称节假日情况的估值问题
		buf.append(" ,to_date('9998/12/31', 'yyyy-MM-dd') AS D_TO_LIST_ZQ ");
		//合并需求 STORY #47088 债券基本信息，增加发行机构类型字段，区分金融机构和非金融机构
		buf.append(" ,' ' AS C_MAIN_PROP ");
		// add By HeMing 集资方式
		buf.append(" ,A.c_jzfs_code");
		//STORY #59311 【易方达基金】【紧急加急】按周付息定期存款业务需求   
	    buf.append(" ,0 AS N_BASE_DATE ");
	    buf.append(" ,0 AS N_PAY_DATE ");
	    buf.append(" ,' ' AS C_PAY_DATE_TYPE ");
	    buf.append(" ,' ' AS C_BASE_DATE_TYPE ");
	    //STORY #62079 人保资产-支持人保资产新科目体系核算
  		buf.append(" ,A.C_KZCD_GQ , '1' as C_DJPX_OPEN ");
  		buf.append(" ,0 AS N_JXTS,' ' AS C_PAY_CALCULATE ");
  		buf.append(" ,0 AS N_RATE_AT");
  		//modify by ZhangJM  STORY #76306 银华基金：投资分级基金A类产品，每日计提收益，卖出时进行冲减
  		buf.append(" ,0 AS N_FV_DAY_IR");
  		//modify by zhoushuhang 20200917 BUG334415 STORY#86134   I9科目体系  证券送配业务问题,这里增加金融工具字段
  		buf.append(" ,' ' AS C_FINANCE_TOOL ");
  		//add by zhoushuhang 20210115 STORY96254华夏基金QD-彭博证券基本信息接口，增加直存“基金管理人”和“基金系列名称”的逻辑
  		buf.append(" ,'' AS C_MANAGER_NAME, '' AS C_FUND_NAME ");
  		//modify by zhoushuhang 20210308 BUG356619计提所得税（包含债券利息税、股息红利税、基金股利税），税费方案设置优先级匹配逻辑优化
  		buf.append(" ,'' AS C_LC_SEC_TAG ");
  		//STORY #76847 【华宝基金】511990 SH华宝添益红利投资特殊处理 
  		buf.append(" ,'' AS C_INVEST_ACCOUNT ");
  		//BUG #327840 债券品种信息导入字段错误问题
  		buf.append(" ,' ' as C_DV_PLAT");
  		//BUG #340267 存放品种信息新增字段刷新缓存无效 增加字段查询
  		buf.append(" ,' ' AS C_ZCFS,' ' AS C_ZCPL");
		buf.append(" from T_P_NS_BASE a");
		buf.append(" left join (select c_da_code, c_sec_var_code from T_S_DA_SEC_VAR) b");
		buf.append(" on a.c_sec_var_code = b.c_sec_var_code");
		buf.append(" WHERE a.n_check_state = 1 and b.c_da_code is not null");
	}
	private void getCommonOrderSqlBuf(StringBuffer buf) {
		buf.append(" order by a.N_CHECK_STATE asc ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(SecBaseTableName.userInfo);
	}
	
	/**
	 * 根据证券品种类型和交易市场组合查询条件
	 * @param Types  证券品种类型
	 * @return
	 */
	public String getDataListByTypesAndMkt(String[] Types){
		// update by 马向峰
		IMktDataService mktDataService = YssServiceFactory.getInstance().createService(IMktDataService.class);
		
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		sql = whereTypes(Types);
		if (sql.trim().length() > 0) {
			buf.append(" AND (");
			buf.append(sql);
			buf.append(")");
		}
		buf.append(" AND a.C_MKT_CODE IN (select C_MKT_CODE from (");
		buf.append(mktDataService.getAllDataSql()).append(" ))");
		//buf.append(new MktSqlBuilder().getAllDataSql()).append(" ))");
		
		return buf.toString();
	}
	
	public String getDataListByDae(List<String> Params){
		String sql ="";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		sql = whereDaes(Params);		
		if (sql.trim().length() > 0) {
			buf.append(" AND (");
			buf.append(sql);
			buf.append(")");
		}
		
		return buf.toString();
	}
	
	private String whereDaes(List<String> list){
		StringBuffer buf = new StringBuffer();
		for(String s : list){
			if(s.equalsIgnoreCase("SEC_VAR")){
				buf.append(" a.C_SEC_VAR_CODE LIKE ? AND ");
			}else if(s.equalsIgnoreCase("DC")){
				buf.append(" a.C_DC_CODE = ? AND ");
			}else if(s.equalsIgnoreCase("MKT")){
				buf.append(" a.C_MKT_CODE = ? AND ");
			}
		}
		
		if (buf.length() > 4) {
			buf.setLength(buf.length() - 4);
		}
		return buf.toString();
	}

	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		
//		sql = " TO_DATE(a.C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		sql = " a.C_CHECK_TIME >= ? ";
		if (sql.trim().length() > 0) {
			buf.append(" AND (");
			buf.append(sql);
			buf.append(")");
		}
		getCommonOrderSqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByTimestampCount(){
		StringBuffer buf = new StringBuffer();
		buf.append(" select count(1) from ( ");
		getCommonQuerySqlBuf(buf);
		buf.append(" AND (");
//		buf.append(" TO_DATE(a.C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
		buf.append("  a.C_CHECK_TIME >= ? ");
		
		buf.append(")");
		buf.append(")");
		return buf.toString();
	}

	/**
	 * 查询指数数据转封装成证券基本信息 
	 * By Jinghehe 2014-8-4
	 * @return
	 */
	public String getAllIndexDataList() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select C_IDEN,C_SEC_CODE,C_INDEX_NAME as C_SEC_NAME, ");
		buf.append(" C_INDEX_CODE as C_SEC_MKT_CODE,C_SEC_ISIN_CODE, ");
		buf.append(" ' ' as C_MKT_CODE,'QQ_ZS' as C_SEC_VAR_CODE, ");
		buf.append(" C_DC_CODE,0 as N_PRICE_FCR, ");
		buf.append(" ' ' as C_SEC_CODE_TRG,0 as N_AMOUNT_HD, ");
		buf.append(" 0 as N_FV_ISSUE,D_BASE as D_TO_LIST, ");
		buf.append(" D_END as D_OFF_LIST,' ' as C_DV_VAR_DUR, ");
		buf.append(" ' ' as C_DV_QUT_MOD,0 as N_RATE, ");
		buf.append(" 0 as N_FV_IR,0 as N_PRICE_ISSUE, ");
		buf.append(" ' ' as C_DV_AI_MOD,' ' as C_DV_PI_MOD, ");
		buf.append(" D_BASE as D_AI_BEGIN,D_END as D_AI_END, ");
		buf.append(" C_DESC,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME, ");
		buf.append(" C_CHECK_BY,C_CHECK_TIME,' ' as C_DV_AI_EXPR, ");
		buf.append(" ' ' as C_ORG_CODE,' ' as C_ORG_NAME,' ' as C_OPEN_ACC_NAME, ");
		buf.append(" ' ' as C_OPEN_ACC_NO,' ' as C_SYS_CODE,' ' as C_CREDIT_RATING, ");
		buf.append(" ' ' as C_DV_ASSURE,' ' as C_DV_ISSUE,C_INDEX_NAME as C_SEC_NAME_CN, ");
		//BUG #149687 股指期权接口清算报错以及界面功能报错  edit by xuhanbing 加入缺少的字段
		buf.append(" 'NULLRIGHT' as C_DV_RIGHT, ' ' as C_ETF_TYPE, ' ' as C_RZRQ_MARK, ' ' as C_SETT_ORG, '' as c_SJSSZQ, ");
		/* (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式 */
		buf.append(" ' ' as C_DV_RTA,0.00 as N_SPREAD,' ' as c_expr_code,0.00 as N_BLXS,' ' as c_jcjg,0.00 as n_upper_limit,0.00 as n_lower_limit,' ' as C_interval_time ,' ' as c_INTERVAL_day, ");	
		/*liuxiang 2016-5-11 添加组合代码 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能*/
		buf.append(" ' ' as C_PORT_CODE , 0 as N_RATIO,D_END from T_P_SV_INDEX where N_CHECK_STATE = 1 ");
		return buf.toString();
	}
	
	/* END 数据服务 */
	
	public String getDataListCount(){
		StringBuffer buf = new StringBuffer();
		buf.append(" select count(1) from ( ");
		buf.append(getAllDataSql());
		buf.append(")");
		return buf.toString();
	}

	/**
	 * add by zhd 2016-09-20
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 得到所有实际所属证券为空的证券
	 * @param Types
	 * @return
	 */
	public String getDataListBySjsszq(String[] Types) {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append(" SELECT * FROM T_P_SV_SEC_BASE A ");
		buf.append(" WHERE ");
		sql = whereTypes(Types);
		if (sql.trim().length() > 0) {
			buf.append(" (");
			buf.append(sql);
			buf.append(") AND ");
		}
		
		buf.append(" A.C_SJSSZQ IS NULL ");
		buf.append(" AND A.N_CHECK_STATE = 1 ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByIndiv() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlByIndiv(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 查询资金存放业务交易证券 STORY #33007 【南方基金】【紧急】存放品种信息机构名称字段只显示商业银行的信息 
	 * By guohui
	 * 2016-08-25
	 * @return
	 */
	public void getCommonQuerySqlByIndiv(StringBuffer buf) {
		buf.append(" select * from (select ' ' as C_IDEN, ");
		buf.append(" '[root]' as c_ORG_CODE_P, ");
		buf.append(" 'ALL' as c_sec_code, ");
		buf.append(" 'ALL' as c_sec_name, ");
		buf.append(" ' ' as C_SEC_VAR_CODE, ");
		buf.append(" '' AS c_dc_code, ");
		buf.append(" 1 AS N_CHECK_STATE, ");
		buf.append(" '' AS C_CHECK_TIME, ");
		buf.append(" '' AS C_CHECK_BY, ");
		buf.append(" '' AS C_UPDATE_BY, ");
		buf.append(" '' AS C_UPDATE_TIME, ");
		buf.append(" 0 as N_PRICE_ISSUE, ");
		buf.append(" ' ' as C_DV_ISSUE, ");
		buf.append(" 0 as detail ");
		buf.append(" from dual ");
		buf.append(" union all ");
		buf.append(" select c_iden, ");
		buf.append(" 'ALL' as c_ORG_CODE_P, ");
		buf.append(" c_sec_code as c_sec_code, ");
		buf.append(" c_sec_name as c_sec_name, ");
		buf.append(" C_SEC_VAR_CODE, ");
		buf.append(" c_dc_code, ");
		buf.append(" n_check_state, ");
		buf.append(" C_CHECK_TIME, ");
		buf.append(" C_CHECK_BY, ");
		buf.append(" C_UPDATE_BY, ");
		buf.append(" C_UPDATE_TIME, ");
		buf.append(" N_PRICE_ISSUE, ");
		buf.append(" C_DV_ISSUE, ");
		buf.append(" case ");
		buf.append(" when trim(c_org_code) is null then ");
		buf.append(" 0 else 1 ");
		buf.append(" end as detail ");
		buf.append(" from T_P_SV_SEC_BASE ");
		buf.append(" where trim(c_org_code) is null ");
		buf.append(" and (c_sec_var_code like 'CK%' or c_sec_var_code like 'DK%') and n_check_state = 1 ");
		buf.append(" AND trim(C_SJSSZQ) IS NULL ");//add by zhd 2016-09-12 STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
		buf.append(" union all ");
		buf.append(" select * from ");
		buf.append(" (select * from ( ");
		buf.append(" select distinct * from ( ");
		buf.append(" select distinct b.c_iden, ");
		buf.append(" '[root]' as c_ORG_CODE_P, ");
		buf.append(" b.c_org_code as c_sec_code,  ");
		buf.append(" b.c_org_name as c_sec_name, ");
		buf.append(" ' ' as C_SEC_VAR_CODE, ");
		buf.append(" b.c_dc_code, ");
		buf.append(" b.n_check_state, ");
		buf.append(" b.C_CHECK_TIME, ");
		buf.append(" b.C_CHECK_BY, ");
		buf.append(" b.C_UPDATE_BY, ");
		buf.append(" b.C_UPDATE_TIME, ");
		buf.append(" 0 as N_PRICE_ISSUE, ");
		buf.append(" ' ' as C_DV_ISSUE, ");
		buf.append(" 0 as detail ");
		buf.append(" from T_P_BI_ORG b ");
		buf.append(" left join T_P_SV_SEC_BASE c ");
		buf.append(" on b.c_org_code = c.c_org_code ");
		buf.append(" where b.c_dv_org_type = 'ORG_SYYH' and b.n_check_state = 1 ");
		buf.append(" union all ");
		buf.append(" select c_iden, ");
		buf.append(" case ");
		buf.append(" when trim(c_org_code) is not null then ");
		buf.append(" c_org_code ");
		buf.append(" else ");
		buf.append(" 'ALL' ");
		buf.append(" end as c_ORG_CODE_P, ");
		buf.append(" c_sec_code, ");
		buf.append(" c_sec_name, ");
		buf.append(" C_SEC_VAR_CODE, ");
		buf.append(" c_dc_code, ");
		buf.append(" n_check_state, ");
		buf.append(" C_CHECK_TIME, ");
		buf.append(" C_CHECK_BY, ");
		buf.append(" C_UPDATE_BY, ");
		buf.append(" C_UPDATE_TIME, ");
		buf.append(" N_PRICE_ISSUE, ");
		buf.append(" C_DV_ISSUE, ");
		buf.append(" case ");
		buf.append(" when trim(c_org_code) is null then ");
		buf.append(" 0 else 1 ");
		buf.append(" end as detail ");
		buf.append(" from T_P_SV_SEC_BASE ");
		buf.append(" where (c_sec_var_code like 'CK%' or ");
		buf.append(" c_sec_var_code like 'DK%') and  n_check_state = 1 ");
		buf.append(" AND trim(C_SJSSZQ) IS NULL) ");//add by zhd 2016-09-12 STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
		buf.append(" start with detail = 1 connect by nocycle c_sec_code = prior c_ORG_CODE_P) ");
		buf.append(" start with c_ORG_CODE_P = '[root]' connect by nocycle prior c_sec_code = c_ORG_CODE_P)) ");
	}
}
