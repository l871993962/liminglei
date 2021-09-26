CREATE OR REPLACE PROCEDURE PRO_TRANS_SC_DICT(oResult OUT VARCHAR2,oErrText OUT VARCHAR2 ) IS
v_ErrorCode NUMBER;
v_ErrorText VARCHAR2(2000);
/*将数据插入到科目字典表*/
BEGIN

delete from T_F_SC_DICT;
insert into T_F_SC_DICT
  (C_DICT_CODE, C_ACCT_CODE, C_CVT_CODE, C_PORT_CODE, N_START, N_LEN)
  select FDictCode||'_'||FDictName, FCNVCONENT, FINDCode, FPortCode, 0, 0
    from Tb_001_Vch_Dict;

    update T_F_SC_DICT a
   set C_PORT_CODE =
       (select FPortCOde
          from Tb_001_Para_Cashaccount b
         where b.FCashAccCode = a.c_Cvt_Code)
 where exists (select FPortCOde
          from Tb_001_Para_Cashaccount b
         where b.FCashAccCode = a.c_Cvt_Code);

COMMIT;
oResult := 'SUCCESS';
EXCEPTION
   WHEN OTHERS THEN
oResult := 'ERROR';
v_ErrorCode := SQLCODE;
v_ErrorText := SUBSTR(SQLERRM, 1, 2000);
oErrText := v_ErrorText;
ROLLBACK; /*全部事务回滚*/
WriteLog('PRO_TRANS_KM',v_ErrorCode,v_ErrorText); /*写入错误信息到日志*/
END PRO_TRANS_SC_DICT;
