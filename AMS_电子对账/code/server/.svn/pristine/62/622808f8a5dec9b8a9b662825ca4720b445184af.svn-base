CREATE OR REPLACE PROCEDURE PRO_TRANS_FEE(oResult OUT VARCHAR2,oErrText OUT VARCHAR2 ) IS
v_ErrorCode NUMBER;
v_ErrorText VARCHAR2(2000);

BEGIN


insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('001', '交易所费用(Trade Fee)', 'JYF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('002', '佣金(Commision)', 'YJ');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('004', '证管费(Levy/Expense)', 'ZGF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('009', '深圳经手费', 'JSF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('010', 'A股佣金(Commision)', 'YJ');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('011', '应付赎回费', 'SHF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('012', '赎回费收入', 'SHFGZC');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('IV001', '管理费', 'YYL_GLF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('IV002', '托管费', 'YYL_TGF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('IV003', '审计费', 'YTL_SJF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('IV004', '信息披露费', 'YTL_XXPLF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('IV005', '投资顾问费', 'YYL_TZGWF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('IV008', '税务顾问费', 'YTL_SWGWF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SHAGGHF', '上海过户费A股', 'GHF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SHAGJSF', '上海经手费A股', 'JSF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SHAGYHS', '印花税(Stamp Duty)', 'YHS');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SHAGYJ', '上海A股佣金', 'YJ');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SHAGZGF', '上海征管费A股', 'ZGF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SHQZGHF', '上海过户费', 'GHF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SHQZJSF', '上海经手费', 'JSF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SHQZYHS', '印花税(Stamp Duty)', 'YHS');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SHQZZGF', '上海征管费', 'ZGF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SZAGGHF', '深圳过户费A股', 'GHF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SZAGJSF', '深圳征管费A股', 'ZGF');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SZAGYHS', '深圳印花税(Stamp Duty)', 'YHS');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SZAGYJ', '深圳A股佣金', 'YJ');

insert into t_trans_fee (FFEECODE, FFEENAME, C_FEE_CODE)
values ('SZAGZGF', '深圳经手费A股', 'JSF');

COMMIT;
oResult := 'SUCCESS';
EXCEPTION
   WHEN OTHERS THEN
oResult := 'ERROR';
v_ErrorCode := SQLCODE;
v_ErrorText := SUBSTR(SQLERRM, 1, 2000);
oErrText := v_ErrorText;
ROLLBACK; /*全部事务回滚*/
WriteLog('PRO_TRANS_FEE',v_ErrorCode,v_ErrorText); /*写入错误信息到日志*/
END PRO_TRANS_FEE;
