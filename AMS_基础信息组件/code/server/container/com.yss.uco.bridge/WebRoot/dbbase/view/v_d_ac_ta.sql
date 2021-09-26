create or replace view v_d_ac_ta as
select ta."C_IDEN",ta."C_PORT_CODE",ta."C_PORT_CLS_CODE",ta."C_SELL_NET_CODE",ta."C_DS_CODE",ta."C_DC_CODE",ta."D_SELL",ta."D_CONFIRM",ta."N_SELL_AMOUNT",ta."N_SELL_MONEY",ta."N_SETT_MONEY",ta."D_SETT",ta."C_CA_CODE_SETT",ta."C_SEC_CODE_OT",ta."C_DESC",ta."C_UPDATE_BY",ta."C_UPDATE_TIME",ta."C_CHECK_BY",ta."C_CHECK_TIME",ta."N_CHECK_STATE",ta."N_INCOME",ta."C_TD_NO",ta."C_CFG_CODE",ta."D_DATE",ta."C_DATA_IDF",
       port.c_port_name,
       port.c_dat_code,
       ass_type.c_dat_name,
       ass_type.c_dat_code_p,
       sp.c_year             as c_sp_year,
       sp.d_start            as d_sp_start,
       sp.d_end              as d_sp_end,
       sp.n_period           as n_sp_period,
       sp.n_sp_curr,
       net.c_net_name,
       net.c_dv_net_type,
       cury.c_dc_name,
       acc.c_ca_name         as c_acc_name,
       acc.c_dv_acc_type,
       fee.c_fee_money,
       td.c_dt_name,--新增
       td.c_busi_type,
       td.n_fund_way,
       td.n_capi_way
  from T_D_AC_SELL_TA ta
  left join T_P_AB_PORT port
    on ta.c_port_code = port.c_port_code
  left join T_S_DAT_ASS_TYPE ass_type
    on port.c_dat_code = ass_type.c_dat_code
  left join T_F_SPERIOD sp
    on ta.d_sell between sp.d_start and sp.d_end
   and sp.c_port_code = ta.c_port_code
  left join T_P_BI_SALES_NET net
    on ta.c_sell_net_code = net.c_net_code
  left join T_S_DC_CURY cury
    on ta.c_dc_code = cury.c_dc_code
  left join T_P_AB_CASH_ACC acc
    on ta.c_ca_code_sett = acc.c_ca_code
  left join T_S_DT_TD_MODE td
    on ta.c_ds_code = td.c_dt_code
  left join (select f.c_iden_rela, sum(f.n_fee_money) as c_fee_money
               from T_D_AC_SELL_TA_FEE f
              group by f.c_iden_rela) fee
    on ta.c_iden = fee.c_iden_rela
  where ta.n_check_state = 1;
