create or replace view v_p_ab_cash_acc as
select acc."C_CA_CODE",acc."C_CA_NAME",acc."C_DV_ACC_TYPE",acc."C_PORT_CODE",acc."C_DC_CODE",acc."D_OPEN",acc."D_CLOSE",acc."C_ORG_CODE",acc."C_OPEN_ACC_NO",acc."C_OPEN_ADDR",acc."C_DESC",acc."N_CHECK_STATE",acc."C_UPDATE_BY",acc."C_UPDATE_TIME",acc."C_CHECK_BY",acc."C_CHECK_TIME",acc."C_IDEN", voc.c_dv_name as c_dv_acc_type_name, cury.c_dc_name
  from T_P_AB_CASH_ACC acc
  left join (select * from T_S_DV_VOC v where v.c_dv_type = 'ACC_TYPE') voc
    on acc.c_dv_acc_type = voc.c_dv_code
  left join (select * from T_S_DC_CURY) cury
    on acc.c_dc_code = cury.c_dc_code;
