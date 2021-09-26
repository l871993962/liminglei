create or replace view v_d_ai_act_val as
select val."C_IDEN",val."C_YEAR_MONTH",val."N_VCH_NUM",val."N_FL_NUM",val."C_PORT_CODE",val."D_CHK_ACC",val."C_DTA_CODE",val."C_DV_ISSUE_MODE",val."C_DV_VAR_DUR",val."C_DV_INVEST_CLS",val."C_PORT_CLS_CODE",val."C_DAI_CODE",val."C_CA_CODE",val."C_SEC_CODE",val."C_FEE_CODE",val."C_NET_CODE",val."C_DVA_ITEM_CODE",val."C_DT_CODE",val."C_MKT_CODE",val."C_SEC_VAR_CODE",val."C_DV_ACC_TYPE",val."C_DV_FEE_TYPE",val."C_DS_CODE",val."C_TD_CHAN_CODE",val."N_WAY",val."N_AMOUNT",val."N_ORIG_MONEY",val."C_DC_CODE",val."C_RATE_EXPR",val."N_PORT_MONEY",val."C_RELA_CODE",val."C_RELA_TYPE",val."C_DATA_IDF",val."C_KM_CODE",val."C_DESC",val."C_CREATE_BY",val."C_CREATE_TIME",val."C_CHECK_BY",val."C_CHECK_TIME",val."C_UPDATE_BY",val."C_UPDATE_TIME",val."C_KM_NAME",val."N_CHECK_STATE",val."N_ATTACH",val."C_DV_KM_CLS",val."C_KM_CODE_T",
       port.c_port_name,
       port.c_dat_code,
       ass_type.c_dat_name,
       ass_type.c_dat_code_p,
       sp.c_year              as c_sp_year,
       sp.d_start             as d_sp_start,
       sp.d_end               as d_sp_end,
       sp.n_period            as n_sp_period,
       sp.n_sp_curr,
       attr.c_dta_name,
       attr.c_busi_type       as c_attr_busi_type,
       issue.c_dv_name        as c_dv_issue_name,
       dur.c_dv_name          as c_dv_dur_name,
       cls.c_dv_name          as c_cls_name,
       tiem.c_dai_name,
       tiem.n_fund_way,
       tiem.c_dv_bool_type_am,
       tiem.c_stock_cls,
       tiem.c_dai_type,
       acc.c_ca_name          as c_acc_name,
       sec.c_sec_isin_code,
       sec.c_sec_mkt_code,
       svar.c_sec_var_name,
       svar.c_da_code         as c_var_da_code,
       fee.c_fee_name,
       net.c_net_name,
       net.c_dv_net_type,
       mkt.c_mkt_name,
       mkt.n_mkt_attr,
       mkt.c_dv_mkt_type,
       mkt.c_dv_name          as c_cv_mkt_type_name,
       mkt.c_mkt_name_en,
       mkt.c_mkt_name_st,
       dva.c_dva_item_name,
       dva.c_dva_item_code_p,
       dva.n_detail
  from T_D_AI_ACT_VAL val
  left join T_P_AB_PORT port
    on val.c_port_code = port.c_port_code
  left join T_S_DAT_ASS_TYPE ass_type
    on port.c_dat_code = ass_type.c_dat_code
  left join T_F_SPERIOD sp
    on val.d_chk_acc between sp.d_start and sp.d_end
   and sp.c_port_code = val.c_port_code
  left join T_S_DTA_TD_ATTR attr
    on val.c_dta_code = attr.c_dta_code
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'ISSUE_MODE') issue
    on issue.c_dv_code = val.c_dv_issue_mode
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'VAR_DUR') dur
    on dur.c_dv_code = val.c_dv_var_dur
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'IVT_CLS') cls
    on val.c_dv_invest_cls = cls.c_dv_code
  left join T_S_DAI_ITEM tiem
    on val.c_dai_code = tiem.c_dai_code
  left join T_P_AB_CASH_ACC acc
    on val.c_ca_code = acc.c_ca_code
  left join T_P_SV_SEC_BASE sec
    on val.c_sec_code = sec.c_sec_code
  left join T_P_BI_SEC_VAR svar
    on val.c_sec_var_code = svar.c_sec_var_code
  left join T_P_BI_FEE fee
    on val.c_fee_code = fee.c_fee_code
  left join T_P_BI_SALES_NET net
    on val.c_net_code = net.c_net_code
  left join (select m.*,
                    v.c_dv_name
               from T_P_BI_MKT m
               left join T_S_DV_VOC v
                 on m.c_dv_mkt_type = v.c_dv_code) mkt
    on val.c_mkt_code = mkt.c_mkt_code
  left join T_S_DVA_ITEM dva
    on val.c_dva_item_code = dva.c_dva_item_code
 where val.n_check_state = 1;
