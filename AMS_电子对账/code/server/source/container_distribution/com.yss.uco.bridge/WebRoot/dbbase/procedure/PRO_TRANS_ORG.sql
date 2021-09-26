CREATE OR REPLACE PROCEDURE PRO_TRANS_ORG(oResult OUT VARCHAR2,oErrText OUT VARCHAR2 ) IS
v_ErrorCode NUMBER;
v_ErrorText VARCHAR2(2000);
BEGIN


insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('BNP', 'BNP Paribas Securities (Asia) Ltd', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('CICC', 'CICC', 'ZQGS_152');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('CITI', '花旗', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('DB', 'Deutsche Bank', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('DB', 'Deutsche Bank', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('GS', 'GS', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('JP', 'JPMORGAN', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('MS', '大摩', 'RG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('Merrill Lynch', '美林', 'RG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('Morganstanley', '大摩', 'RG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('Nomura', '野村证券', 'RG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('SH001', '申银万国证券', 'ZQGS_113');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('SSGM LLC', '道富', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('SSGMI', '道富', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('UBS', 'UBS', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('xnqs', 'xnqs', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('安信证券', '安信证券', 'ZQGS_3');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('东兴证券', '东兴证券', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('海通证券', '海通证券', '中信证券');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('红塔证券', '红塔证券', 'ZQGS_64');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('华创证券', '华创证券', 'ORG_QT');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('华西证券', '华西证券', 'ZQGS_72');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('民生证券', '民生证券', 'ZQGS_92');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('申银万国', '申银万国', 'ZQGS_200');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('湘财证券', '湘财证券', 'ZQGS_127');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('兴业证券', '兴业证券', 'ZQGS_131');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('中投证券', '中投证券', 'ZQGS_156');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('中信证券', '中信证券', 'ZQGS_164');

insert into t_Trans_Org (FBROKERCODE, FBROKERNAME, C_ORG_CODE)
values ('中邮证券', '中邮证券', 'ORG_QT');

COMMIT;
oResult := 'SUCCESS';
EXCEPTION
   WHEN OTHERS THEN
oResult := 'ERROR';
v_ErrorCode := SQLCODE;
v_ErrorText := SUBSTR(SQLERRM, 1, 2000);
oErrText := v_ErrorText;
ROLLBACK; /*全部事务回滚*/
WriteLog('PRO_TRANS_ORG',v_ErrorCode,v_ErrorText); /*写入错误信息到日志*/
END PRO_TRANS_ORG;
