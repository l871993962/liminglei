create or replace type fee_row_type as object
(
  C_FEE_CODE        VARCHAR2(20) ,
  C_SEC_VAR_CODE    VARCHAR2(20) ,
  C_MKT_CODE        VARCHAR2(20),
  C_DV_RATE_TYPE_TD VARCHAR2(20),
  C_DV_CALC_MARK    VARCHAR2(20),
  D_BEGIN           DATE ,
  D_END             DATE ,
  N_RATE_VALUE      NUMBER(30,15) ,
  N_MAX_VALUE       NUMBER(18,4),
  N_MIN_VALUE       NUMBER(18,4) ,
  C_DESC            VARCHAR2(200),
  N_CHECK_STATE     NUMBER(3) ,
  C_UPDATE_BY       VARCHAR2(20) ,
  C_UPDATE_TIME     VARCHAR2(20),
  C_CHECK_BY        VARCHAR2(20),
  C_CHECK_TIME      VARCHAR2(20),
  C_DT_CODE         VARCHAR2(20) ,
  C_SEC_CODE        VARCHAR2(50) ,
  C_DV_PLAT         VARCHAR2(20)
)
