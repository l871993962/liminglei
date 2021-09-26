CREATE OR REPLACE PROCEDURE PRO_TRANS_DICT_NAME(oResult OUT VARCHAR2,oErrText OUT VARCHAR2 ) IS
v_ErrorCode NUMBER;
v_ErrorText VARCHAR2(2000);
BEGIN

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00088', '损益平准金转换');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT-TR-CB', '基金-成本');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00013', '主次托管行帐号');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00007', '两费代码转换_应付');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00001', '股票');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('FU_ACCOUNT', '保证金账户');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT-TR-GZZZ', '基金-估值增值');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00014', '银行应收');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00032', '场内基金与货币基金');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT000052', '币种2');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCTGN007', '正回购金融资产');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00020', '应付利息账号');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00011-2', '银行帐号');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT_JJ_EXC_CURY', '基金交易所币种');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00011', '银行帐号');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT_ZQ_EXC_CURY', '债券交易所币种');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00027', '待摊代码转换_应收');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT0100', '自定义品种');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCTGN002', '证券投资市场转换');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCTGN003', '债券子类型');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00030', '证券类型转科目');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00017', '预提代码转换_应付');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00029', '待摊代码转换_损益');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('GN-DCT00006', '清算款交易所币种(3级)');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00120', '国内国外');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00028', '交易所科目');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00015', '银行应付');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT99931', '损益平准金转换');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00002', '结算延迟天数');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('YSCKLX', '应收存款利息科目');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCTGN005', '券商代码转换（应付佣金）');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00008', '费用名称转换');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00012', '投资收益交易所币种');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DTC00021', '存款币种');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCTGN008', '回购交易所');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCTGN001', '国内清算款');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT000030', '币种');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00006', '清算款交易所币种');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00005', '币种');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('CKLXSR', '存款利息收入科目');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00019', '预提代码转换_损益');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT-QS-001', '券商');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00000', '实收基金科目');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00031', '存款类型转换');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('DCT00009', '两费代码转换_损益');

insert into t_trans_vch_dict_name (FDICTCODE, FDICTNAME)
values ('FI_EXCG_CATE', '债券交易所品种');


COMMIT;
oResult := 'SUCCESS';
EXCEPTION
   WHEN OTHERS THEN
oResult := 'ERROR';
v_ErrorCode := SQLCODE;
v_ErrorText := SUBSTR(SQLERRM, 1, 2000);
oErrText := v_ErrorText;
ROLLBACK; /*全部事务回滚*/
WriteLog('PRO_TRANS_DICT_NAME',v_ErrorCode,v_ErrorText); /*写入错误信息到日志*/
END PRO_TRANS_DICT_NAME;
