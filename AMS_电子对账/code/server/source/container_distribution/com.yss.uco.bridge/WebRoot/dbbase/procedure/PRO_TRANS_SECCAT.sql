CREATE OR REPLACE PROCEDURE PRO_TRANS_SECCAT(oResult OUT VARCHAR2,oErrText OUT VARCHAR2 ) IS
v_ErrorCode NUMBER;
v_ErrorText VARCHAR2(2000);
BEGIN


insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('204001', '204001回购', 'RE03001', '企业融资回购', 'HG_MDS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('204001', '204001回购', 'RE04001', '企业融券回购', 'HG_MDS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('204001', '204001回购', 'RE05001', '买断式融券回购', 'HG_MDS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('204001', '204001回购', 'RE06001', '买断式融资回购', 'HG_MDS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('204001', '204001回购', 'RE07001', '融资回购', 'HG_MDS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('204001', '204001回购', 'RE08001', '融券回购', 'HG_MDS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('DE', '货币市场', 'DE01', '活期存款', 'CK_HQ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('DE', '货币市场', 'DE02', '通知存款', 'CK_TZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('DE', '货币市场', 'DE03', '定期存款', 'CK_DQ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('DE', '货币市场', 'DE04', '协议存款', 'CK_XY');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('DE', '货币市场', 'DE05', '一年期内票据(note)', 'CK');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('DE', '货币市场', 'DE06', 'FXCD', 'CK');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('DE', '货币市场', 'DE99', '其它存款', 'CK');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('EQ', '股票', 'EQ01', '普通股', 'GP_GP');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('EQ', '股票', 'EQ02', '新股', 'GP_GP');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('EQ', '股票', 'EQ99', '其它股票', 'GP_GP');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('FI', '债券', 'FI01', '到期一次还本付息型', 'ZQ_JRZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('FI', '债券', 'FI02', '固定利率定期付息型', 'ZQ_JRZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('FI', '债券', 'FI03', '浮动利率定期付息型', 'ZQ_JRZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('FI', '债券', 'FI99', '其他债券', 'ZQ_JRZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('FP', '期权', 'FP01', '股票期权', 'QQ_GP');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('FP', '期权', 'FP02', '股指期权', 'QQ_GP');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('FU', '期货', 'FU01', '股指期货', 'QH_GZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('FW', '远期', 'FW01', '90天远期交易', 'YQ_LL');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('FW', '远期', 'FW03', '1年远期交易', 'YQ_LL');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('Mortgage', 'Mortgage', 'MBSs', 'Morgage backed securites', 'YQ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('OP', '权证', 'OP01', '普通权证', 'QZ_QZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('OP', '权证', 'OP02', '配股权证', 'QZ_QZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('OP', '权证', 'OP99', '其他权证', 'QZ_QZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('OP', '权证', 'asdf', 'asdf', 'QZ');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('RE', '回购', 'RE01', '普通回购', 'HG_MDS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('RE', '回购', 'RE02', '场外回购', 'HG_MDS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('RT', '信托', 'RT01', '测试子类型', 'LC_XT');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('TR', '基金', 'TR01', '封闭式基金', 'JJ_FBS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('TR', '基金', 'TR02', '普通开放式基金', 'JJ_KFS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('TR', '基金', 'TR0201', '开放式股票型基金', 'JJ_KFS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('TR', '基金', 'TR0202', '开放式固定收益型基金', 'JJ_KFS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('TR', '基金', 'TR0203', '开放式混合型基金', 'JJ_KFS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('TR', '基金', 'TR03', '货币式基金', 'JJ_KFS_HBX');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('TR', '基金', 'TR04', 'ETF基金', 'JJ_KFS_ETF');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('TR', '基金', 'TR05', '信托基金', 'JJ_KFS');

insert into t_Trans_Seccat (FCATCODE, FCATNAME, FSUBCATCODE, FSUBCATNAME, C_CAT_CODE)
values ('TR', '基金', 'TR99', '其它基金', 'JJ');

COMMIT;
oResult := 'SUCCESS';
EXCEPTION
   WHEN OTHERS THEN
oResult := 'ERROR';
v_ErrorCode := SQLCODE;
v_ErrorText := SUBSTR(SQLERRM, 1, 2000);
oErrText := v_ErrorText;
ROLLBACK; /*全部事务回滚*/
WriteLog('PRO_TRANS_SECCAT',v_ErrorCode,v_ErrorText); /*写入错误信息到日志*/
END PRO_TRANS_SECCAT;
