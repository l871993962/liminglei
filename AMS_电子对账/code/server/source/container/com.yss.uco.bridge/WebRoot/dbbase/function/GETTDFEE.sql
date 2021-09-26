create or replace function getTdFee return t_fee_type
      pipelined as
      fee fee_row_type;
    begin
      for myrow in ( SELECT C_FEE_CODE, C_SEC_VAR_CODE , C_MKT_CODE,C_DV_RATE_TYPE_TD, C_DV_CALC_MARK , D_BEGIN , D_END, N_RATE_VALUE ,N_MAX_VALUE ,N_MIN_VALUE ,
 C_DESC, N_CHECK_STATE , C_UPDATE_BY, C_UPDATE_TIME , C_CHECK_BY,C_CHECK_TIME,C_DT_CODE , C_SEC_CODE , C_DV_PLAT FROM T_P_CO_TD_FEE) loop
        fee := fee_row_type( myrow.C_FEE_CODE, myrow.C_SEC_VAR_CODE , myrow.C_MKT_CODE,myrow.C_DV_RATE_TYPE_TD, myrow.C_DV_CALC_MARK , myrow.D_BEGIN , myrow.D_END, myrow.N_RATE_VALUE ,myrow.N_MAX_VALUE ,myrow.N_MIN_VALUE ,
 myrow.C_DESC, myrow.N_CHECK_STATE ,myrow.C_UPDATE_BY, myrow.C_UPDATE_TIME , myrow.C_CHECK_BY,myrow.C_CHECK_TIME,myrow.C_DT_CODE , myrow.C_SEC_CODE , myrow.C_DV_PLAT);
        pipe row(fee);
      end loop;
      return;
    end;
