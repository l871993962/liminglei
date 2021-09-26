create or replace view v_d_ac_trade as
select ivt."C_IDEN",ivt."C_TD_TYPE",ivt."C_DV_TYPE_SUB",ivt."C_PORT_CODE",ivt."C_SEC_CODE",ivt."C_SEC_CODE_TAG",ivt."C_DT_CODE",ivt."C_SEC_VAR_CODE",ivt."C_DC_CODE",ivt."C_DV_PLAT",ivt."C_MKT_CODE",ivt."C_DTA_CODE",ivt."C_DV_ISSUE_MODE",ivt."C_DV_VAR_DUR",ivt."C_TD_CHAN_CODE",ivt."C_DV_INVEST_CLS",ivt."C_SH_ACC_CODE",ivt."C_AUX_SIGN",ivt."D_TRADE",ivt."N_TD_AMOUNT",ivt."N_TD_PRICE",ivt."N_INCOME",ivt."N_TD_MONEY",ivt."D_SETT_FIRST",ivt."C_CA_CODE_SETT_FIRST",ivt."N_SETT_MONEY_FIRST",ivt."N_SETT_MONEY_DUE",ivt."D_SETT_DUE",ivt."C_CA_CODE_SETT_DUE",ivt."D_SETT_FACT",ivt."C_TD_NO",ivt."C_CA_CODE_BAIL",ivt."C_CA_CODE_OT",ivt."C_DV_STATE",ivt."C_DATA_IDF",ivt."C_DESC",ivt."N_CHECK_STATE",ivt."C_UPDATE_BY",ivt."C_UPDATE_TIME",ivt."C_CHECK_BY",ivt."C_CHECK_TIME",ivt."C_CFG_CODE",ivt."D_DATE",
       port.c_port_name,
       port.c_dat_code,
       ass_type.c_dat_name,
       ass_type.c_dat_code_p,
       sp.c_year              as c_sp_year,
       sp.d_start             as d_sp_start,
       sp.d_end               as d_sp_end,
       sp.n_period            as n_sp_period,
       sp.n_sp_curr,
       sec1.c_sec_isin_code,
       sec1.c_sec_mkt_code,
       sec1.c_sec_name,
       svar.c_sec_var_name,
       svar.c_da_code         as c_var_da_code,
       sec2.c_sec_name        as c_sec_name_t,
       sec2.C_SEC_MKT_CODE    as C_SEC_MKT_CODE_T,
       tdmd.c_dt_name,
       tdmd.c_busi_type,
       tdmd.n_fund_way,
       tdmd.n_capi_way,
       cury.c_dc_name,
       attr.c_dta_name,
       attr.c_busi_type       as c_attr_busi_type,
       issue.c_dv_name        as c_dv_issue_name,
       dur.c_dv_name          as c_dv_dur_name,
       chan.c_td_chan_name,
       chan.c_dv_chan_type,
       chan.c_org_code,
       chan_type.c_dv_name    as c_chan_type_name,
       org.c_org_name,
       org.c_dv_org_type,
       org.c_org_name_st,
       org.c_org_name_cn,
       orgtype.c_dv_name      as c_org_type_name,
       cls.c_dv_name          as c_cls_name,
       acc_1st.c_ca_name      as c_acc_1st_name,
       acc_1st.c_dv_acc_type  as c_acc_1st_type,
       acc_due.c_ca_name      as c_acc_due_name,
       acc_due.c_dv_acc_type  as c_acc_due_type,
       acc_bail.c_ca_name     as c_acc_bail_name,
       acc_bail.c_dv_acc_type as c_acc_bail_type,
       acc_ot.c_ca_name       as c_acc_ot_name,
       acc_ot.c_dv_acc_type   as c_acc_ot_type,
       mkt.c_mkt_name,
       mkt.n_mkt_attr,
       mkt.c_dv_mkt_type,
       mkt.c_dv_name          as c_cv_mkt_type_name,
       mkt.c_hday_code,
       mkt.c_area_code,
       mkt.n_sett_days,
       mkt.c_swift_code,
       mkt.c_mkt_name_en,
       mkt.c_mkt_name_st,
       mkt.c_area_code_en,
       mkt.c_area_name,
       mkt.c_area_seq,
       mkt.c_area_name_en,
       yj.n_fee_money         as n_fee_yj,
       jyj.n_fee_money        as n_fee_jyj,
       jysfy.n_fee_jys,
       ast.n_port_mv          as n_aststat_zcjz,
       vpr.n_rate,--新添加
       case when vpr.n_rate is not null then round(ivt.n_td_money * vpr.n_rate,2) else null end as n_td_money_port,
       case when vpr.n_rate is not null then round(ivt.n_sett_money_due * vpr.n_rate,2) else null end as n_sett_money_due_port,
       case when vpr.n_rate is not null then round(yj.n_fee_money * vpr.n_rate,2) else null end as n_fee_yj_port,
       case when vpr.n_rate is not null then round(jysfy.n_fee_jys * vpr.n_rate,2) else null end as n_fee_jys_port
  from T_D_AC_TRADE_IVT ivt
  left join T_P_AB_PORT port
    on ivt.c_port_code = port.c_port_code
  left join T_S_DAT_ASS_TYPE ass_type
    on port.c_dat_code = ass_type.c_dat_code
  left join T_F_SPERIOD sp
    on ivt.d_trade between sp.d_start and sp.d_end
   and sp.c_port_code = port.c_port_code
  left join T_P_SV_SEC_BASE sec1
    on ivt.c_sec_code = sec1.c_sec_code
  left join T_P_BI_SEC_VAR svar
    on sec1.c_sec_var_code = svar.c_sec_var_code
  left join t_p_Sv_Sec_Base sec2
    on ivt.c_sec_code_tag = sec2.c_sec_code
  left join T_S_DT_TD_MODE tdmd
    on ivt.c_dt_code = tdmd.c_dt_code
  left join T_S_DC_CURY cury
    on ivt.c_dc_code = cury.c_dc_code
  left join T_S_DTA_TD_ATTR attr
    on ivt.c_dta_code = attr.c_dta_code
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'ISSUE_MODE') issue
    on issue.c_dv_code = ivt.c_dv_issue_mode
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'YQJY_TYPE') dur
    on dur.c_dv_code = ivt.c_dv_var_dur
  left join T_P_AB_TD_CHAN chan
    on chan.c_td_chan_code = ivt.c_td_chan_code
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'CHAN_TYPE') chan_type
    on chan.c_dv_chan_type = chan_type.c_dv_code
  left join T_P_BI_ORG org
    on chan.c_org_code = org.c_org_code
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'ORG_TYPE') orgtype
    on org.c_dv_org_type = orgtype.c_dv_code
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'IVT_CLS') cls
    on ivt.c_dv_invest_cls = cls.c_dv_code
  left join T_P_AB_CASH_ACC acc_1st
    on ivt.c_ca_code_sett_first = acc_1st.c_ca_code
  left join T_P_AB_CASH_ACC acc_due
    on ivt.c_ca_code_sett_due = acc_due.c_ca_code
  left join T_P_AB_CASH_ACC acc_bail
    on ivt.c_ca_code_bail = acc_bail.c_ca_code
  left join T_P_AB_CASH_ACC acc_ot
    on ivt.c_ca_code_ot = acc_ot.c_ca_code
  left join (select m.*,
                    v.c_dv_name,
                    r.c_area_code_en,
                    r.c_area_name,
                    r.c_area_name_en,
                    r.c_area_code_p,
                    r.c_area_seq
               from T_P_BI_MKT m
               left join T_S_DV_VOC v
                 on m.c_dv_mkt_type = v.c_dv_code
               left join T_P_BI_AREA r
                 on m.c_area_code = r.c_area_code) mkt
    on ivt.c_mkt_code = mkt.c_mkt_code
  left join (select *
               from T_D_AC_TRADE_IVT_FEE f
              where f.c_fee_code = 'YFL_YJ') yj
    on ivt.c_iden = yj.c_iden_rela
  left join (select *
               from T_D_AC_TRADE_IVT_FEE f
              where f.c_fee_code = 'YJL_JYJ') jyj
    on ivt.c_iden = jyj.c_iden_rela
  left join (select f.c_iden_rela, sum(f.n_fee_money) as n_fee_jys
               from T_D_AC_TRADE_IVT_FEE f
               join T_P_BI_FEE tf
                 on f.c_fee_code = tf.c_fee_code
              where tf.c_dv_fee_type = 'FEE_ZQL'
              group by tf.c_dv_fee_type, f.c_iden_rela) jysfy
    on ivt.c_iden = jysfy.c_iden_rela
  left join (select * from T_R_FR_ASTSTAT a where a.c_key_code = 'ZCJZ') ast
    on ivt.c_port_code = ast.c_port_code
   and ivt.d_trade = ast.d_aststat
  left join (select case
                     when c_rate_expr is not null
                       then eval_func(c_rate_expr)
                     else null end as n_rate,
                      c_val_pr_code,
                      c_port_code,
                      d_val_acct
              from T_D_AI_VAL_PR pr where pr.c_pr_type = 'R') vpr
    on ivt.c_dc_code = vpr.c_val_pr_code
    and vpr.d_val_acct = ivt.d_trade
    and ivt.c_port_code = vpr.c_port_code
  where ivt.n_check_state = 1;
