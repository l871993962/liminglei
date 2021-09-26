CREATE OR REPLACE PROCEDURE PRO_TRANS_SEC_GP(oResult  out varchar2,
                                             oErrText out varchar2) IS
  v_ErrorCode NUMBER; /*错误代码*/
  v_ErrorText VARCHAR2(2000); /*错误信息*/
  /*股票基本信息的导入*/
BEGIN
  delete from T_P_SV_SEC_BASE a
   where Exists
   (select '1'
            from TB_006_Para_Security sec where sec.FCatCode = 'EQ' and sec.FCheckState <= 1 and a.c_sec_code = sec.fsecuritycode);
  insert into T_P_SV_SEC_BASE
    (C_IDEN,
     C_SEC_CODE,
     C_SEC_NAME,
     C_SEC_MKT_CODE,
     C_SEC_ISIN_CODE,
     C_MKT_CODE,
     C_SEC_VAR_CODE,
     C_DC_CODE,
     N_PRICE_FCR,
     C_SEC_CODE_TRG,
     N_AMOUNT_HD,
     N_FV_ISSUE,
     D_TO_LIST,
     D_OFF_LIST,
     C_DV_VAR_DUR,
     C_DV_QUT_MOD,
     N_RATE,
     N_FV_IR,
     N_PRICE_ISSUE,
     C_DV_AI_MOD,
     C_DV_AI_EXPR,
     C_DV_PI_MOD,
     D_AI_BEGIN,
     D_AI_END,
     C_DESC,
     N_CHECK_STATE,
     C_UPDATE_BY,
     C_UPDATE_TIME,
     C_CHECK_BY,
     C_CHECK_TIME)
    select SEQ_P_SV_SEC_BASE.NEXTVAL as C_IDEN,
           sec.FSecurityCode as C_SEC_CODE,/*证券代码*/
           sec.FSecurityName as C_SEC_NAME,/*证券名称*/
           sec.FMarketCode as C_SEC_MKT_CODE,/*上市代码*/
           sec.FISINCode as C_SEC_ISIN_CODE,/*ISIN代码*/
           nvl(mkt.c_mkt_code,sec.FExchangeCode) as C_MKT_CODE,/*交易市场*/
           nvl(cat.c_cat_code,'GP_GP') as C_SEC_VAR_CODE,/*证券品种*/
           sec.FTradeCury as C_DC_CODE,/*交易币种*/
           sec.FFactor as N_PRICE_FCR,/*报价因子*/
           '' as C_SEC_CODE_TRG,/*标的证券*/
           sec.FHandAmount as N_AMOUNT_HD,/*每手数量*/
           sec.FFaceAmount as N_FV_ISSUE,/*发行面值*/
           case
             when sec.FStartDate is null then
             to_date('1900-1-1')
             else
             sec.FStartDate
           end as D_TO_LIST,/*上市日期*/
           to_Date('9998-12-31', 'yyyy-MM-dd') as D_OFF_LIST,/*退市日期*/
           ' ' as C_DV_VAR_DUR, /*付息频率*/
           ' ' C_DV_QUT_MOD, /*报价方式*/
           0 as N_RATE,
           0 as N_FV_IR,
           0 as N_PRICE_ISSUE,
           ' ' as C_DV_AI_MOD, /*计息方式*/
           ' ' as C_DV_AI_EXPR, /*计息公式*/
           ' ' as C_DV_PI_MOD, /*付息公式*/
           to_Date('1900-01-01', 'yyyy-MM-dd') as D_AI_BEGIN,/*计息起始日*/
           to_Date('1900-01-01', 'yyyy-MM-dd') as D_AI_END,/*计息结束日*/
           sec.FDesc as C_DESC,/*描述*/
           sec.FCheckState as N_CHECK_STATE,
           sec.FCreator as C_UPDATE_BY,
           sec.FCreateTime as C_UPDATE_TIME,
           sec.FCheckUser as C_CHECK_BY,
           sec.FCheckTime as C_CHECK_TIME
      from TB_006_Para_Security sec
      left join t_Trans_Seccat cat
        on sec.Fsubcatcode = cat.Fsubcatcode
      left join t_trans_mkt mkt
        on mkt.fexchangecode=sec.fexchangecode
     where sec.FCheckState <= 1
       and sec.FCatCode = 'EQ';
  commit;
  oResult := 'SUCCESS';
EXCEPTION
  when OTHERS then
    v_ErrorCode := SQLCODE;
    v_ErrorText := SUBSTR(SQLERRM, 1, 2000);
    oErrText    := v_ErrorText;
    oResult     := 'ERROR';
    rollback;
    writeLog('PRO_TRANS_SEC_GP', v_ErrorCode, v_ErrorText);
END PRO_TRANS_SEC_GP;
