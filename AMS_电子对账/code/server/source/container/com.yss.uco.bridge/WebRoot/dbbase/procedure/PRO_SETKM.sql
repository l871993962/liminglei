create or replace procedure Pro_setKM(c_KM_P  in varchar2,
                                      dae_row in out nocopy DAE_ROW_TYPE) as
begin
  for my_row in (select * from t_Trans_Km where FAcctCode = c_KM_P) loop
    dae_row.C_KM_Code       := my_row.C_KM_Code;
    dae_row.C_KM_CODE_P     := my_row.c_KM_CODE;
    dae_row.C_DTA_CODE      := my_row.C_DTA_CODE;
    dae_row.C_DV_ISSUE_MODE := my_row.C_DV_ISSUE_MODE;
    dae_row.C_DV_VAR_DUR    := my_row.C_DV_VAR_DUR;
    dae_row.C_DV_INVEST_CLS := my_row.C_DV_INVEST_CLS;
    dae_row.C_PORT_CLS_CODE := my_row.C_PORT_CLS_CODE;
    dae_row.C_DAI_CODE      := my_row.C_DAI_CODE;
    dae_row.C_CA_CODE       := my_row.C_CA_CODE;
    dae_row.C_SEC_CODE      := my_row.C_SEC_CODE;
    dae_row.C_FEE_CODE      := my_row.C_FEE_CODE;
    dae_row.C_NET_CODE      := my_row.C_NET_CODE;
    dae_row.C_MKT_CODE      := my_row.C_MKT_CODE;
    dae_row.C_SEC_VAR_CODE  := my_row.C_SEC_VAR_CODE;
    dae_row.C_DV_ACC_TYPE   := my_row.C_DV_ACC_TYPE;
    dae_row.C_DC_CODE       := my_row.C_DC_CODE;
    dae_row.C_DV_FEE_TYPE   := my_row.C_DV_FEE_TYPE;
    dae_row.C_DS_CODE       := my_row.C_DS_CODE;
    dae_row.C_TD_CHAN_CODE  := my_row.C_TD_CHAN_CODE;
  end loop;
end;
