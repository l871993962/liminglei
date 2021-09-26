create or replace view v_d_ao_stock as
select stk."TD_D_AI_STOCK",stk."C_YEAR_MONTH",stk."C_PORT_CODE",stk."D_STOCK",stk."C_DTA_CODE",stk."C_DV_ISSUE_MODE",stk."C_DV_VAR_DUR",stk."C_DV_INVEST_CLS",stk."C_PORT_CLS_CODE",stk."C_DAI_CODE",stk."C_CA_CODE",stk."C_SEC_CODE",stk."C_FEE_CODE",stk."C_NET_CODE",stk."C_MKT_CODE",stk."C_SEC_VAR_CODE",stk."C_DV_ACC_TYPE",stk."C_DV_FEE_TYPE",stk."C_DS_CODE",stk."C_DC_CODE",stk."C_KM_CODE",stk."N_AMOUNT",stk."N_ORIG_MONEY",stk."N_PORT_MONEY",stk."C_DESC",stk."C_KM_NAME",stk."C_DV_KM_CLS",stk."C_TD_CHAN_CODE",
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
       cury.c_dc_name,
       ast.n_port_mv          as n_aststat_zcjz
  from T_D_AI_STOCK stk
  left join T_P_AB_PORT port
    on stk.c_port_code = port.c_port_code
  left join T_S_DAT_ASS_TYPE ass_type
    on port.c_dat_code = ass_type.c_dat_code
  left join T_F_SPERIOD sp
    on stk.d_stock between sp.d_start and sp.d_end
   and sp.c_port_code = stk.c_port_code
  left join T_S_DTA_TD_ATTR attr
    on stk.c_dta_code = attr.c_dta_code
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'ISSUE_MODE') issue
    on issue.c_dv_code = stk.c_dv_issue_mode
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'VAR_DUR') dur
    on dur.c_dv_code = stk.c_dv_var_dur
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'IVT_CLS') cls
    on stk.c_dv_invest_cls = cls.c_dv_code
  left join T_S_DAI_ITEM tiem
    on stk.c_dai_code = tiem.c_dai_code
  left join T_P_AB_CASH_ACC acc
    on stk.c_ca_code = acc.c_ca_code
  left join T_P_SV_SEC_BASE sec
    on stk.c_sec_code = sec.c_sec_code
  left join T_P_BI_SEC_VAR svar
    on stk.c_sec_var_code = svar.c_sec_var_code
  left join T_P_BI_FEE fee
    on stk.c_fee_code = fee.c_fee_code
  left join T_P_BI_SALES_NET net
    on stk.c_net_code = net.c_net_code
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
    on stk.c_mkt_code = mkt.c_mkt_code
  left join T_S_DC_CURY cury
    on stk.c_dc_code = cury.c_dc_code
  left join (select a.c_port_code,
                    a.d_aststat,
                    a.n_port_cost,
                    a.n_port_mv,
                    a.n_port_iv
               from T_R_FR_ASTSTAT a
              where a.c_key_code = 'ZCJZ') ast
    on stk.c_port_code = ast.c_port_code
   and stk.d_stock = ast.d_aststat;
