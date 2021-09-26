create or replace view v_r_fr_aststat as
select ast."ID_R_FR_ASTSTAT",ast."D_ASTSTAT",ast."C_PORT_CODE",ast."C_NAV_TYPE",ast."N_WAY",ast."N_VA_PRICE",ast."N_VA_RATE",ast."N_AMOUNT",ast."N_ORIG_COST",ast."N_ORIG_MV",ast."N_ORIG_IV",ast."N_PORT_COST",ast."N_PORT_MV",ast."N_PORT_IV",ast."C_KEY_CODE",ast."C_KEY_NAME",ast."C_KM_CODE",ast."C_KM_NAME",ast."C_DV_KM_CLS",ast."C_DC_CODE",ast."C_SEC_CODE",ast."C_SEC_VAR_CODE",ast."C_MKT_CODE",ast."C_DTA_CODE",ast."C_DV_ISSUE_MODE",ast."C_DV_VAR_DUR",ast."C_DV_INVEST_CLS",ast."C_DAI_CODE",ast."C_CA_CODE",ast."C_FEE_CODE",ast."C_NET_CODE",ast."C_DV_ACC_TYPE",ast."C_DV_FEE_TYPE",ast."C_DS_CODE",ast."C_TD_CHAN_CODE",ast."C_SUSPENSION",ast."C_RT_IF",ast."C_KM_CODE_T", cury.c_dc_name, dai.c_dai_name, chan.c_td_chan_name, fee.c_fee_name, net.c_net_name, case when instr(ast.c_km_code, '_') > 0 then substr(ast.c_km_code, 5) else ' ' end as c_port_cls_code
  from t_r_fr_aststat ast
  left join (select * from T_S_DC_CURY) cury
    on ast.c_dc_code = cury.c_dc_code
  left join (select * from t_s_dai_item) dai
    on ast.c_dai_code = dai.c_dai_code
  left join (select * from T_P_AB_TD_CHAN) chan
    on ast.c_td_chan_code = chan.c_td_chan_code
  left join (select * from T_P_BI_FEE) fee
    on ast.c_fee_code = fee.c_fee_code
  left join (select * from T_P_BI_SALES_NET) net
    on ast.c_net_code = net.c_net_code;
