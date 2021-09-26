CREATE OR REPLACE PROCEDURE PRO_TRANS_MKT(oResult OUT VARCHAR2,oErrText OUT VARCHAR2 ) IS
v_ErrorCode NUMBER;
v_ErrorText VARCHAR2(2000);

BEGIN

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('00', '场外', 'CW');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('AU', 'australia exchange', 'AX');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('BH', 'BH', 'BH');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('BL', 'Belgium exchange', 'b');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('CA', 'Canada exchange', 'PT');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('CG', 'Shanghai Exchange', 'SS');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('CH', 'Switzerland exchange', 'S');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('CRSTGB22', '英国交易所', 'I');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('CS', 'Shenzhen Exchange', 'SZ');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('DTCYUS33', '美国交易所', 'A');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('FD', 'EN Paris', 'PA');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('FP', '法国交易所', 'F');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('FR', 'France exchange', 'F');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('GM', 'Jermany exchange', '');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('GR', '德国交易所', 'MU');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('ID', 'ID', '');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('IT', '意大利交易所', '');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('JP', 'Japanese exchange', 'Q');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('LN', 'London exchange', 'L');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('LU', 'Luxembourg exchange', 'LU');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('LX', 'LX', '');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('NL', 'Netherlands exchange', 'NZ');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('OTC', '银行间', 'CY');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('PT', 'Portugal exchange', 'PT');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('RO', 'Romania exchange', '');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('SH', 'SH', 'SS');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('SICVFRPP', '法国交易所', 'F');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('SP', 'Singapore Exchange', 'SIM');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('UA', 'Ukraine exchange', 'PFT');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('VX', '瑞士交易所', 'S');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('XHKCHKH1', '香港交易所', 'HK');

insert into t_Trans_MKT (FEXCHANGECODE, FEXCHANGENAME, C_MKT_CODE)
values ('sdfdd', 'asdfdd', '');

COMMIT;
oResult := 'SUCCESS';
EXCEPTION
   WHEN OTHERS THEN
oResult := 'ERROR';
v_ErrorCode := SQLCODE;
v_ErrorText := SUBSTR(SQLERRM, 1, 2000);
oErrText := v_ErrorText;
ROLLBACK; /*全部事务回滚*/
WriteLog('PRO_TRANS_MKT',v_ErrorCode,v_ErrorText); /*写入错误信息到日志*/
END PRO_TRANS_MKT;
