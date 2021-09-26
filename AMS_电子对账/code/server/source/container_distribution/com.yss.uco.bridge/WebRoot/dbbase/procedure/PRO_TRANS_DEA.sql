CREATE OR REPLACE PROCEDURE PRO_TRANS_DEA(oResult OUT VARCHAR2,oErrText OUT VARCHAR2 ) IS
v_ErrorCode NUMBER;
v_ErrorText VARCHAR2(2000);

BEGIN


insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('001', '存款拆借-存入', 'CKCJCR', 'CKTZ_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('002', '存款拆借-到期', 'CKCJDQ', 'CKTZ_DQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('003', '存款计息', 'FYHJX', 'RCJT_ZHLX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('004', '股票分红到帐', 'FHDZYW', 'DJPX_FHPX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('005', '基金分红到帐', 'JJFHDZYW', 'DJPX_FHPX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('010', '买入股票', 'MRGP', 'GPJY_BUY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('011', '卖出股票', 'MCGP', 'GPJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('012', '卖出股票公允价值调整', 'MCGPGZZZ', 'GPJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('020', '计提两费', 'FJTLF', 'RCJT_YYSZ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('021', '清算结转-流入', 'QSJZ', 'JYJS_ZQJS');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('022', '清算结转-流出', 'QSJZ', 'JYJS_ZQJS');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('023', '分红', 'QYPX', 'DJPX_FHPX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('024', '送股', 'QYSG', 'ZQSP_SG');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('025', '配股', 'QYPG', 'ZQSP_PG');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('026', '汇兑损益-存款(10月停止使用)', '', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('027', '结转支出', 'JZZC', 'ZJHH_HB');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('028', '结转收益', 'JZSY', 'SYJZ_JZQY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('029', '结转公允价值', 'JZGY', 'SYJZ_JZQY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('030', '外币之间兑换业务', 'WHBS', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('031', '汇兑损益-应收存款利息(10月停止使用)', '', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('032', '汇兑损益－应付清算款(10月停止使用)', '', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('033', '预提费用', 'YTFY', 'RCJT_YTDT');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('034', '存款利息到账', 'CKLX', 'RCJT_ZHLX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('034_cituo', '存款利息到账-借存款-贷收入', 'CKLX', 'RCJT_ZHLX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('035', '存款利息到账补差', 'CKLXBC', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('036', '买入基金', 'MRJJ', 'JJJY_BUY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('037', '结转汇兑差额', 'JZGY', 'SYJZ_JZQY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('038', '收益结转（申购）', 'JZSY', 'SYJZ_JZQY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('039', '收益结转（赎回）', 'JZSY', 'SYJZ_JZQY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('040', '与组合货币互换及划汇', 'HHYW', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('050', '汇兑损益-货币兑换及划汇(停止使用)', 'HHYW', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('051', '货币兑换及划汇费用', 'HHFY', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('052', '汇兑损益-货币兑换及划汇费用(停止使用)', 'HHFYSY', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('053', '汇兑损益-清算结转-流出(停止使用)', 'QSJZHDZJ', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('054', '换汇业务-结转汇兑损益(停止使用)', 'WHJZHDSY', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('055', '存款利息到账调整汇兑损益(停止使用)', 'CKLXHDSY', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('060', '卖出货币基金', 'MCHBJJ', 'JJJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('061', '卖出基金', 'MCJJ', 'JJJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('062', '卖出基金公允价值调整', 'MCJJGZZZ', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('063', '基金分红(除权日)', 'JJFHYW', 'DJPX_FHPX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('064', '基金分红到帐', 'JJFHDZYW', 'DJPX_FHPX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('065', '基金送股', 'JJSGYW', 'ZQSP_SG');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('077', '买入权证', 'MRQZ', 'QZJY_BUY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('088', '证券清算款补差额(美元)', 'QSKCE', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('088_hkd', '证券清算款补差额(港币)', 'QSKCE', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('091', '股票估值增值（暂停使用）', '', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('092', '股票估值增值(非货币型资产估值增值)', 'GPGZZZ', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('093', '基金估值增值(非货币型资产估值增值)', 'JJGZZZ', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('094', '权证估值增值', 'QZGZZZ', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('095', '认购权证行权', 'PGYW', 'QZXQ_QZXQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('095—02', '认沽权证行权', 'PGYW', 'QZXQ_QZXQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('099', '汇兑损益-财务取数', 'HDSY', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('100', '卖出债券', 'MCZQ', 'ZQJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('1001', '年终结转-已实现', 'JZSY', 'SYJZ_JZQY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('1002', '年终结转-未实现', 'JZSY', 'SYJZ_JZQY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('105', '汇兑损益－清算结转-流入(10月停止使用)', '', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('106', '汇兑损益－清算结转-流出(10月停止使用)', '', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('110', '买入债券', 'MRZQ', 'ZQJY_BUY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('111', '卖出权证', 'MCQZ', 'QZJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('112', '卖出权证公允价值调整', 'QZGYJZTZ', 'QZJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('120', '远期外汇交易估值', 'HLYQ', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('132', '汇兑损益－应收清算款(10月停止使用)', '', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('133', '待摊费用', 'DTFY', 'RCJT_YTDT');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('135', '支付费用管理费托管费', 'ZFLF', 'ZFJZ_YYFY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('136', '支付银行费用', 'ZFQTFY', 'ZJHH_HB');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('210', '基金成立', 'TAJJCL', 'TAXS_JJCL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('212', 'TA申购', 'TASG', 'TAXS_SG');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('213', 'TA赎回', 'TASH', 'TAXS_SH');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('216', 'TA申购款结转', 'SGSHJZ', 'JYJS_XSJS');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('217', 'TA赎回款结转', 'SGSHJZ', 'JYJS_XSJS');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('BFJTZ_in', '备付金流入-手动', 'BFJTZIN', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('BFJTZ_out', '备付金流出-手动', 'BFJTZOUT', '');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('DQCK', '定期存款存入', 'DQCK', 'CKTZ_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('DQCKDQ', '定期存款到期', 'DQCKDQ', 'CKTZ_DQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('DRGYJZ', '卖出存托公允价值调整', 'DRGYJZTZ', 'GPJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('DRGZZZ', '存托估值增值', 'GZZZ', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('DRMC', '卖出存托', 'MCDR', 'GPJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('DRMR', '买入存托', 'MRDR', 'GPJY_BUY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('EQ001', '新股中签', 'XGZQ', 'XGSG_QR');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FI-DFGYJZTZ', '债券兑付公允价值调整', 'ZQDFGZZZ', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FI001', '买入债券', 'MRZQ', 'ZQJY_BUY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FI002', '卖出债券', 'MCZQ', 'ZQJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FI003', '债券估值增值', 'ZQGZZZ', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FI004', '债券记息', 'ZQJX', 'RCJT_ZQLX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FI005', '卖出债券公允价值调整', 'MCZQGZZZ', 'ZQJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FI006', '债券到期兑付', 'ZQDF', 'DJPX_XJDJ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FI007', '兑付债券公允价值调整', 'ZQDFGZZZ', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU001', '存入保证金', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU001_01', '应付变动保证金', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU001_02', '结转应付变动保证金', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU002', '期货开仓', 'FUBS', 'QHJY_KC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU003', '期货估值增值', 'FUGZZZ', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU004', '结算当日估值增值', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU005', '缴纳初始保证金', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU006', '期货平仓', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU007', '结转当日变动保证金', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU009', '期货平仓结转公允价值变动损益', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU010', '解冻初始保证金', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU011', '初始保证金调整', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FU012', '期货调整估值增值', 'FUBS', 'QHJY_PC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('FW0001', '汇率远期到期', 'DQYW', 'YQJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN-FI004', '新债申购（网下）', 'XZSG', 'XZSG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN-FI005', '新债中签（网下）', 'XZZQ', 'XZSG_QR');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN-FI006', '新债中签返款（网下）', 'XZFK', 'XGSG_QR');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_010', '买入股票(A股)', 'MRGP', 'GPJY_BUY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_011', '卖出股票(A股)', 'MCGP', 'GPJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_012', '卖出股票(A股)公允价值调整(国内)', 'GNMCGPGYJZTZ', 'GPJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_EQ042', '配售增发中签(国内)', 'GNPSZFZQ', 'ZQSP_PG');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_FI001', '买入债券(国内)', 'MRZQ', 'ZQJY_BUY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_FI002', '卖出债券（国内）', 'MCZQ', 'ZQJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_FI_ZQPX', '债券派息(国内)', 'ZQPX', 'DJPX_XJDJ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_FI_ZQPX_02', '债券派息到账(国内)', 'ZQPX', 'DJPX_XJDJ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_PGJK', '配股缴款', 'PGJK', 'ZQSP_PG');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_PGJKCQ', '配股缴款除权', 'PGJKCQ', 'ZQSP_PG');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_QSJZ_C', '清算结转-流出(国内网下业务)', 'QSJZ', 'JYJS_ZQJS');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_QSJZ_R', '清算结转-流入(国内网下业务)', 'QSJZ', 'JYJS_ZQJS');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_RE_YHJ_HGDQ_MCHG', '银行间-回购到期-卖出回购', 'HGDQ', 'HGJY_ZHG_DQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_RE_YHJ_HGDQ_MRHG', '银行间-回购到期-买入回购', 'HGDQ', 'HGJY_NHG_DQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_RE_YHJ_MCHG', '银行间-卖出回购', 'MCHG', 'HGJY_ZHG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_RE_YHJ_MRHG', '银行间-买入回购', 'MRHG', 'HGJY_NHG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_YHJZQMC', '银行间债券卖出', 'MCYHJZQ', 'ZQJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_YHJZQMCGZ', '银行间债券卖出冲减估值增值', 'MCZGZZZ', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('GN_YHJZQMR', '银行间债券买入', 'MRYHJZQ', 'ZQJY_BUY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('IM01', '货币基金红利收入', 'HBJJHLSR', 'DJPX_FHPX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('IM02', '货币基金红利到账', 'HBJJHLDZ', 'DJPX_FHPX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('IM03', '货币基金估值增值', 'HBJJ', 'ZCGZ_ZQKC');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('JJFHCQ', '基金分红(除权日)', 'JJFH', 'TAXS_FH');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('JJFHCQ2', '基金分红(除权日)', 'JJFH', 'TAXS_FH');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('JJFHDZ', '基金分红到帐', 'JJFH', 'TAXS_FH');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('JJFHZTZ', '基金分红转投资', 'FHZT', 'TAXS_FHTZ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('OP001', '配股权证行权', 'PGYW', 'QZXQ_QZXQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('OP002', '配股权证调整公允价值调整', 'QZGYJZTZ', 'QZJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('OP0095', '认购权证调整公允价值调整', 'QZGYJZTZ', 'QZJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('OP0095-2', '认沽权证调整公允价值调整', 'QZGYJZTZ', 'QZJY_SELL');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('QY01', '红利到账', 'HLDZ', 'DJPX_FHPX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE021', '清算结转(回购到期)-流入', 'QSJZ', 'JYJS_ZQJS');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE022', '清算结转(回购到期)-流出', 'QSJZ', 'JYJS_ZQJS');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE039', '正回购首期', 'ZHGSQ', 'HGJY_ZHG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE040', '正回购计息', 'ZHGJX', 'RCJT_HGSY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE041', '正回购到期', 'ZHGDQ', 'HGJY_ZHG_DQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE042', '逆回购首期', 'NHGSQ', 'HGJY_NHG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE042_2', '逆回购首期_银行间', 'NHGSQ', 'HGJY_NHG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE043', '逆回购计息', 'NHGJX', 'RCJT_HGSY');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE044', '逆回购到期', 'NHGDQ', 'HGJY_NHG_DQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE044_2', '逆回购到期_银行间', 'NHGDQ', 'HGJY_NHG_DQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE045_2', '逆回购汇划费_银行间', 'NHGSQ', 'HGJY_NHG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE045_4', '银行间交易手续费', 'NHGSQ', 'HGJY_NHG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE097', '正回购首期-计提回购风险金', 'NHGSQ', 'HGJY_NHG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('RE098', '逆回购首期-计提回购风险金', 'NHGSQ', 'HGJY_NHG_SQ');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('REDivdend', '股票分红到账（币种不一致）', 'GPFHDZ', 'DJPX_FHPX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('SGJX001', 'TA申购款计息', 'CKJX', 'RCJT_ZHLX');

insert into t_Trans_Dva (FVCHTPLCODE, FVCHTPLNAME, FVCHTWAY, C_DT_CODE)
values ('SYJZ038', '收益结转-赎回', 'JZSY', 'SYJZ_JZQY');


COMMIT;
oResult := 'SUCCESS';
EXCEPTION
   WHEN OTHERS THEN
oResult := 'ERROR';
v_ErrorCode := SQLCODE;
v_ErrorText := SUBSTR(SQLERRM, 1, 2000);
oErrText := v_ErrorText;
ROLLBACK; /*全部事务回滚*/
WriteLog('PRO_TRANS_DEA',v_ErrorCode,v_ErrorText); /*写入错误信息到日志*/
END PRO_TRANS_DEA;
