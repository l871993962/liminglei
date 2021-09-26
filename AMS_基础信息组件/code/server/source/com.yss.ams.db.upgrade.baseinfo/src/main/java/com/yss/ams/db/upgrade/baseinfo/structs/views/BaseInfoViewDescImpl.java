package com.yss.ams.db.upgrade.baseinfo.structs.views;

import com.yss.ams.db.upgrade.baseinfo.structs.Baseinfo;
import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.ViewBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;

/**
 * 
 * @ClassName BaseInfoViewDescImpl
 * @Description 
 * @author leijianhua@ysstech.com
 * @CreateDate 2017年9月22日下午1:29:25
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class BaseInfoViewDescImpl extends BaseStructDesc{
	private ViewBuilder viewBuilder = null;
	@Override
	public void execute() throws Exception {
		viewBuilder = getViewBuilder();
		buildV_LIC_S_DVA_ITEM();
		buildV_LIC_S_DT_TD_MODE();
		buildV_LIC_S_DTA_TD_ATTR();
		buildV_LIC_S_DSP_PARA();
		buildV_LIC_S_DA_SEC_VAR();
		buildV_LIC_S_DAT_ASS_TYPE();
		
		buildV_S_DVA_ITEM();
		buildV_S_DT_TD_MODE();
		buildV_S_DTA_TD_ATTR();
		buildV_S_DSP_PARA();
		buildV_S_DA_SEC_VAR();
		buildV_S_DAT_ASS_TYPE();

	}
	
	/**
	 * STORY #69095许可证配置和功能优化--查询资源时取并集（同时存在已过期和未过期的取未过期的）
	 * 
	 * */
	private void buildV_LIC_S_DAT_ASS_TYPE() throws Exception{
		viewBuilder.createOrReplaceView("V_LIC_S_DAT_ASS_TYPE", "资产类型字典表lic视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_LIC_S_DAT_ASS_TYPE AS")
	    .appendLine("select b.D_BEGIN_DATE,b.D_END_DATE,b.C_DAT_CODE from (")
	    .appendLine("select ROW_NUMBER() over(partition by t.C_DAT_CODE order by N_EXPIRE_DAYS desc) rn,")
	    .appendLine("       t. *")
	    .appendLine("  from (select to_date(to_char(t3.d_end_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') -")
	    .appendLine("               to_date(to_char(sysdate, 'yyyy-MM-dd'), 'yyyy-MM-dd') as N_EXPIRE_DAYS,")
	    .appendLine("               t3.*")
	    .appendLine("          from (select t1.d_begin_date,t1.d_end_date,t2.c_lic_res_code as C_DAT_CODE")
	    .appendLine("from T_S_LIC_RES_GROUP  t1, T_S_LIC_RES_DETAIL t2")
	    .appendLine("where t1.c_lic_res_group_code = t2.c_lic_res_group_code")
	    .appendLine("and t2.c_lic_res_type='ASS_TYPE') t3) t) b")
	    .appendLine("where b.RN = 1")
	    .end_SQL()
		.build(UpdateType.REQUEST, "69095", "查询资源时取并集同时存在已过期和未过期的取未过期的", "leijianhua@ysstech", "2017-09-22");
	}
	
	/**
	 * STORY #69095许可证配置和功能优化--查询资源时取并集（同时存在已过期和未过期的取未过期的）
	 * 
	 * */
	private void buildV_LIC_S_DA_SEC_VAR() throws Exception{
		viewBuilder.createOrReplaceView("V_LIC_S_DA_SEC_VAR", "证券品种字典lic视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_LIC_S_DA_SEC_VAR AS")
	    .appendLine("select b.D_BEGIN_DATE,b.D_END_DATE,b.C_SEC_VAR_CODE from (")
	    .appendLine("select ROW_NUMBER() over(partition by t.C_SEC_VAR_CODE order by N_EXPIRE_DAYS desc) rn,")
	    .appendLine("       t. *")
	    .appendLine("  from (select to_date(to_char(t3.d_end_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') -")
	    .appendLine("               to_date(to_char(sysdate, 'yyyy-MM-dd'), 'yyyy-MM-dd') as N_EXPIRE_DAYS,")
	    .appendLine("               t3.*")
	    .appendLine("          from (select t1.d_begin_date,t1.d_end_date,t2.c_lic_res_code as C_SEC_VAR_CODE")
	    .appendLine("from T_S_LIC_RES_GROUP  t1, T_S_LIC_RES_DETAIL t2")
	    .appendLine("where t1.c_lic_res_group_code = t2.c_lic_res_group_code")
	    .appendLine("and t2.c_lic_res_type='SEC_VAR') t3) t) b")
	    .appendLine("where b.RN = 1")
	    .end_SQL()
		.build(UpdateType.REQUEST, "69095", "查询资源时取并集同时存在已过期和未过期的取未过期的", "leijianhua@ysstech", "2017-09-22");
	}
	
	/**
	 * STORY #69095许可证配置和功能优化--查询资源时取并集（同时存在已过期和未过期的取未过期的）
	 * 
	 * */
	private void buildV_LIC_S_DSP_PARA() throws Exception{
		viewBuilder.createOrReplaceView("V_LIC_S_DSP_PARA", "综合参数字典lic视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_LIC_S_DSP_PARA AS")
	    .appendLine("select b.D_BEGIN_DATE,b.D_END_DATE,b.C_DSP_CODE from (")
	    .appendLine("select ROW_NUMBER() over(partition by t.C_DSP_CODE order by N_EXPIRE_DAYS desc) rn,")
	    .appendLine("       t. *")
	    .appendLine("  from (select to_date(to_char(t3.d_end_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') -")
	    .appendLine("               to_date(to_char(sysdate, 'yyyy-MM-dd'), 'yyyy-MM-dd') as N_EXPIRE_DAYS,")
	    .appendLine("               t3.*")
	    .appendLine("          from (select t1.d_begin_date,t1.d_end_date,t2.c_lic_res_code as C_DSP_CODE")
	    .appendLine("from T_S_LIC_RES_GROUP  t1, T_S_LIC_RES_DETAIL t2")
	    .appendLine("where t1.c_lic_res_group_code = t2.c_lic_res_group_code")
	    .appendLine("and t2.c_lic_res_type='DSP_PARA') t3) t) b")
	    .appendLine("where b.RN = 1")
	    .end_SQL()
		.build(UpdateType.REQUEST, "69095", "查询资源时取并集同时存在已过期和未过期的取未过期的", "leijianhua@ysstech", "2017-09-22");
	}

	/**
	 * STORY #69095许可证配置和功能优化--查询资源时取并集（同时存在已过期和未过期的取未过期的）
	 * 
	 * */
	private void buildV_LIC_S_DTA_TD_ATTR() throws Exception{
		viewBuilder.createOrReplaceView("V_LIC_S_DTA_TD_ATTR", "交易属性字典lic视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_LIC_S_DTA_TD_ATTR AS")
	    .appendLine("select b.D_BEGIN_DATE,b.D_END_DATE,b.C_DTA_CODE from (")
	    .appendLine("select ROW_NUMBER() over(partition by t.C_DTA_CODE order by N_EXPIRE_DAYS desc) rn,")
	    .appendLine("       t. *")
	    .appendLine("  from (select to_date(to_char(t3.d_end_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') -")
	    .appendLine("               to_date(to_char(sysdate, 'yyyy-MM-dd'), 'yyyy-MM-dd') as N_EXPIRE_DAYS,")
	    .appendLine("               t3.*")
	    .appendLine("          from (select t1.d_begin_date,t1.d_end_date,t2.c_lic_res_code as C_DTA_CODE")
	    .appendLine("from T_S_LIC_RES_GROUP  t1, T_S_LIC_RES_DETAIL t2")
	    .appendLine("where t1.c_lic_res_group_code = t2.c_lic_res_group_code")
	    .appendLine("and t2.c_lic_res_type='TD_ATTR') t3) t) b")
	    .appendLine("where b.RN = 1")
	    .end_SQL()
		.build(UpdateType.REQUEST, "69095", "查询资源时取并集同时存在已过期和未过期的取未过期的", "leijianhua@ysstech", "2017-09-22");
	}

	/**
	 * STORY #69095许可证配置和功能优化--查询资源时取并集（同时存在已过期和未过期的取未过期的）
	 * 
	 * */
	private void buildV_LIC_S_DT_TD_MODE() throws Exception{
		viewBuilder.createOrReplaceView("V_LIC_S_DT_TD_MODE", "交易方式字典lic视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_LIC_S_DT_TD_MODE AS")
	    .appendLine("select b.D_BEGIN_DATE,b.D_END_DATE,b.C_DT_CODE from (")
	    .appendLine("select ROW_NUMBER() over(partition by t.C_DT_CODE order by N_EXPIRE_DAYS desc) rn,")
	    .appendLine("       t. *")
	    .appendLine("  from (select to_date(to_char(t3.d_end_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') -")
	    .appendLine("               to_date(to_char(sysdate, 'yyyy-MM-dd'), 'yyyy-MM-dd') as N_EXPIRE_DAYS,")
	    .appendLine("               t3.*")
	    .appendLine("          from (select t1.d_begin_date,t1.d_end_date,t2.c_lic_res_code as C_DT_CODE")
	    .appendLine("from T_S_LIC_RES_GROUP  t1, T_S_LIC_RES_DETAIL t2")
	    .appendLine("where t1.c_lic_res_group_code = t2.c_lic_res_group_code")
	    .appendLine("and t2.c_lic_res_type='TD_MODE') t3) t) b")
	    .appendLine("where b.RN = 1")
	    .end_SQL()
		.build(UpdateType.REQUEST, "69095", "查询资源时取并集同时存在已过期和未过期的取未过期的", "leijianhua@ysstech", "2017-09-22");
	}

	/**
	 * STORY #69095许可证配置和功能优化--查询资源时取并集（同时存在已过期和未过期的取未过期的）
	 * 
	 * */
	private void buildV_LIC_S_DVA_ITEM() throws Exception{
		viewBuilder.createOrReplaceView("V_LIC_S_DVA_ITEM", "核算业务项字典lic视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_LIC_S_DVA_ITEM AS")
	    .appendLine("select b.D_BEGIN_DATE,b.D_END_DATE,b.C_DVA_ITEM_CODE from (")
	    .appendLine("select ROW_NUMBER() over(partition by t.C_DVA_ITEM_CODE order by N_EXPIRE_DAYS desc) rn,")
	    .appendLine("       t. *")
	    .appendLine("  from (select to_date(to_char(t3.d_end_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') -")
	    .appendLine("               to_date(to_char(sysdate, 'yyyy-MM-dd'), 'yyyy-MM-dd') as N_EXPIRE_DAYS,")
	    .appendLine("               t3.*")
	    .appendLine("          from (select t1.d_begin_date,t1.d_end_date,t2.c_lic_res_code as C_DVA_ITEM_CODE")
	    .appendLine("from T_S_LIC_RES_GROUP  t1, T_S_LIC_RES_DETAIL t2")
	    .appendLine("where t1.c_lic_res_group_code = t2.c_lic_res_group_code")
	    .appendLine("and t2.c_lic_res_type='DVA_ITEM') t3) t) b")
	    .appendLine("where b.RN = 1")
	    .end_SQL()
		.build(UpdateType.REQUEST, "69095", "查询资源时取并集同时存在已过期和未过期的取未过期的", "leijianhua@ysstech", "2017-09-22");
	}
	
	
	private void buildV_S_DVA_ITEM() throws Exception{
		viewBuilder.createOrReplaceView("V_S_DVA_ITEM", "核算业务项字典视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_S_DVA_ITEM AS")
	    .appendLine("select t.\"C_DVA_ITEM_CODE\",t.\"C_DVA_ITEM_NAME\",t.\"C_DVA_ITEM_CODE_P\",t.\"N_ORDER\",t.\"N_DETAIL\",t.\"C_PARA\" from T_S_DVA_ITEM t where t.c_dva_item_code not in (select C_LIC_RES_CODE from T_S_LIC_RES_DETAIL where C_LIC_RES_TYPE='DVA_ITEM')")
	    .appendLine("union")
	    .appendLine("select distinct t1.\"C_DVA_ITEM_CODE\",t1.\"C_DVA_ITEM_NAME\",t1.\"C_DVA_ITEM_CODE_P\",t1.\"N_ORDER\",t1.\"N_DETAIL\",t1.\"C_PARA\"")
	    .appendLine("from T_S_LIC_BASEINFO t6,v_lic_S_DVA_ITEM t2,T_S_DVA_ITEM t1")
	    .appendLine("where t1.c_dva_item_code = t2.C_DVA_ITEM_CODE")
	    .appendLine("and (t6.C_LIC_TYPE = '99'  or (t2.D_BEGIN_DATE <= sysdate and t2.D_END_DATE >= sysdate ))")
	    .end_SQL()
	    .addDenpendcies("V_LIC_S_DVA_ITEM")
	    .build(UpdateType.REQUEST, "00001", "转移至baseInfo中", "leijianhua@ysstech", "2017-09-22");
	}

	private void buildV_S_DT_TD_MODE() throws Exception{
		viewBuilder.createOrReplaceView("V_S_DT_TD_MODE", "交易方式字典视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_S_DT_TD_MODE AS")
	    .appendLine("select t.\"C_DT_CODE\",t.\"C_DT_NAME\",t.\"C_BUSI_TYPE\",t.\"N_FUND_WAY\",t.\"N_CAPI_WAY\",t.\"N_ORDER\" from T_s_dt_td_mode t where t.c_dt_code not in (select C_LIC_RES_CODE from T_S_LIC_RES_DETAIL where C_LIC_RES_TYPE='TD_MODE')")
	    .appendLine("union")
	    .appendLine("select distinct t1.\"C_DT_CODE\",t1.\"C_DT_NAME\",t1.\"C_BUSI_TYPE\",t1.\"N_FUND_WAY\",t1.\"N_CAPI_WAY\",t1.\"N_ORDER\"")
	    .appendLine("from T_S_LIC_BASEINFO t6,v_lic_s_dt_td_mode t2,T_s_dt_td_mode t1")
	    .appendLine("where t1.C_DT_CODE = t2.C_DT_CODE")
	    .appendLine("and (t6.C_LIC_TYPE = '99'  or (t2.D_BEGIN_DATE <= sysdate and t2.D_END_DATE >= sysdate ))")
	    .end_SQL()
	    .addDenpendcies("v_lic_s_dt_td_mode")
	    .build(UpdateType.REQUEST, "00001", "转移至baseInfo中", "leijianhua@ysstech", "2017-09-22");
	}

	private void buildV_S_DTA_TD_ATTR() throws Exception{
		viewBuilder.createOrReplaceView("V_S_DTA_TD_ATTR", "交易属性字典视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_S_DTA_TD_ATTR AS")
	    .appendLine("select t.\"C_DTA_CODE\",t.\"C_DTA_NAME\",t.\"C_BUSI_TYPE\",t.\"N_ORDER\" from T_s_dta_td_attr t where t.c_dta_code not in (select C_LIC_RES_CODE from T_S_LIC_RES_DETAIL where C_LIC_RES_TYPE='TD_ATTR')")
	    .appendLine("union")
	    .appendLine("select distinct t1.\"C_DTA_CODE\",t1.\"C_DTA_NAME\",t1.\"C_BUSI_TYPE\",t1.\"N_ORDER\"")
	    .appendLine("from T_S_LIC_BASEINFO t6,v_lic_s_dta_td_attr t2,T_s_dta_td_attr t1")
	    .appendLine("where t1.C_DTA_CODE = t2.C_DTA_CODE")
	    .appendLine("and (t6.C_LIC_TYPE = '99' or (t2.D_BEGIN_DATE <= sysdate and t2.D_END_DATE >= sysdate))")
	    .end_SQL()
	    .addDenpendcies("v_lic_s_dta_td_attr")
	    .build(UpdateType.REQUEST, "00001", "转移至baseInfo中", "leijianhua@ysstech", "2017-09-22");
	}
	
	private void buildV_S_DSP_PARA() throws Exception{
		viewBuilder.createOrReplaceView("V_S_DSP_PARA", "综合参数字典视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_S_DSP_PARA AS")
	    .appendLine("select t.\"C_DSP_CODE\",t.\"C_DSP_NAME\",t.\"C_DSP_CLASS\",t.\"C_DSP_GROUP_CODE\",t.\"C_DSP_VALUE_TYPE\",t.\"C_DV_TYPE\",t.\"C_DSG_CODE\",t.\"C_DSG_NAME\",t.\"C_DV_PLAT_VALUE\",t.\"C_DESC\",t.\"C_DSP_GROUP_NAME\",t.\"C_DAT_CODE\",t.\"C_DS_TPYE\", t.\"N_ORDER\",t.\"C_REF_CODE\",t.\"C_REF_VALUE\",t.\"C_DSP_CLS_FIRST_NAME\"  from T_S_DSP_PARA t where t.c_dsp_code not in (select C_LIC_RES_CODE  from T_S_LIC_RES_DETAIL where C_LIC_RES_TYPE='DSP_PARA')")
	    .appendLine("union")
	    .appendLine("select distinct t1.\"C_DSP_CODE\",t1.\"C_DSP_NAME\",t1.\"C_DSP_CLASS\",t1.\"C_DSP_GROUP_CODE\",t1.\"C_DSP_VALUE_TYPE\",t1.\"C_DV_TYPE\",t1.\"C_DSG_CODE\",t1.\"C_DSG_NAME\",t1.\"C_DV_PLAT_VALUE\",t1.\"C_DESC\",t1.\"C_DSP_GROUP_NAME\",t1.\"C_DAT_CODE\",t1.\"C_DS_TPYE\", t1.\"N_ORDER\",t1.\"C_REF_CODE\",t1.\"C_REF_VALUE\",t1.\"C_DSP_CLS_FIRST_NAME\"")
	    .appendLine("from T_S_LIC_BASEINFO t6,v_lic_S_DSP_PARA t2,T_S_DSP_PARA t1")
	    .appendLine("where t1.c_Dsp_Code = t2.c_Dsp_Code")
	    .appendLine("and (t6.C_LIC_TYPE = '99'  or (t2.D_BEGIN_DATE <= sysdate and t2.D_END_DATE >= sysdate ))")
	    .end_SQL()
	    .addDenpendcies("v_lic_S_DSP_PARA")
	    .build(UpdateType.REQUEST, "00001", "转移至baseInfo中", "leijianhua@ysstech", "2017-09-22");
	}

	private void buildV_S_DA_SEC_VAR() throws Exception{
		viewBuilder.createOrReplaceView("V_S_DA_SEC_VAR", "证券品种字典视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_S_DA_SEC_VAR AS")
	    .appendLine("select t.\"C_IDEN\",t.\"C_SEC_VAR_CODE\",t.\"C_SEC_VAR_NAME\",t.\"C_DA_CODE\",t.\"C_DA_CODE_P\",t.\"N_ORDER\",t.\"C_DV_STATE\" from T_s_da_sec_var t where t.c_sec_var_code not in (select C_LIC_RES_CODE from T_S_LIC_RES_DETAIL where C_LIC_RES_TYPE='SEC_VAR')")
	    .appendLine("union")
	    .appendLine("select distinct t1.\"C_IDEN\",t1.\"C_SEC_VAR_CODE\",t1.\"C_SEC_VAR_NAME\",t1.\"C_DA_CODE\",t1.\"C_DA_CODE_P\",t1.\"N_ORDER\",t1.\"C_DV_STATE\"")
	    .appendLine("from T_S_LIC_BASEINFO t6,V_LIC_s_da_sec_var t2,T_s_da_sec_var t1")
	    .appendLine("where t1.C_SEC_VAR_CODE = t2.C_SEC_VAR_CODE")
	    .appendLine("and (t6.C_LIC_TYPE = '99' or (t2.D_BEGIN_DATE <= sysdate and t2.D_END_DATE >= sysdate))")
	    .end_SQL()
	    .addDenpendcies("V_LIC_s_da_sec_var")
	    .build(UpdateType.REQUEST, "00001", "转移至baseInfo中", "leijianhua@ysstech", "2017-09-22");
	}
	private void buildV_S_DAT_ASS_TYPE() throws Exception{
		viewBuilder.createOrReplaceView("V_S_DAT_ASS_TYPE", "资产类型字典表视图")
	    .begin_SQL()
	    .appendLine("CREATE OR REPLACE VIEW V_S_DAT_ASS_TYPE AS")
	    .appendLine("select t.\"C_DAT_CODE\",t.\"C_DAT_NAME\",t.\"N_ORDER\",t.\"C_DAT_CODE_P\",t.\"C_DAT_TYPE\" from T_S_DAT_ASS_TYPE t where t.c_dat_code not in (select C_LIC_RES_CODE from T_S_LIC_RES_DETAIL where C_LIC_RES_TYPE='ASS_TYPE')")
	    .appendLine("union")
	    .appendLine("select distinct t1.\"C_DAT_CODE\",t1.\"C_DAT_NAME\",t1.\"N_ORDER\",t1.\"C_DAT_CODE_P\",t1.\"C_DAT_TYPE\"")
	    .appendLine("from T_S_LIC_BASEINFO t6,V_LIC_S_DAT_ASS_TYPE t2,T_S_DAT_ASS_TYPE t1")
	    .appendLine("where t1.C_DAT_CODE = t2.C_DAT_CODE")
	    .appendLine("and (t6.C_LIC_TYPE = '99'  or (t2.D_BEGIN_DATE <= sysdate and t2.D_END_DATE >= sysdate))")
	    .end_SQL()
	    .addDenpendcies("V_LIC_S_DAT_ASS_TYPE")
	    .build(UpdateType.REQUEST, "00001", "转移至baseInfo中", "leijianhua@ysstech", "2017-09-22");
	}

}
