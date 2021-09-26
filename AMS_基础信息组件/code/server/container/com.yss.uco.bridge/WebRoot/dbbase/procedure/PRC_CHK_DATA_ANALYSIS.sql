CREATE OR REPLACE PROCEDURE PRC_chk_data_analysis(C_START VARCHAR,C_END VARCHAR)
IS
   v_exe_sql     VARCHAR2(2000);
BEGIN

    v_exe_sql := 'DELETE FROM t_chk_data_analysis WHERE D_DATE BETWEEN TO_DATE(''' || C_START || ''',''yyyy-MM-dd'') AND to_date(''' || C_END || ''',''yyyy-MM-dd'')';
             DBMS_OUTPUT.put_line ( TO_DATE(C_START,'yyyy-MM-dd'));
    EXECUTE IMMEDIATE v_exe_sql;

    v_exe_sql := 'insert into t_chk_data_analysis (c_source, d_date, c_sh_acc_code, c_chan_code, c_sec_code, c_bs, n_count, n_sum_cjsl, n_sum_cjje)
    select ''SHGH'' C_SOURCE,BCRQ D_DATE,GDDM c_sh_acc_code,GSDM C_CHAN_CODE ,ZQDM C_SEC_CODE ,BS C_BS,COUNT(*) N_COUNT,SUM(CJSL) N_SUM_CJSL,SUM(CJJE) N_SUM_CJJE FROM t_d_od_gh
    where BCRQ BETWEEN TO_DATE(''' || C_START || ''',''yyyy-MM-dd'') AND to_date(''' || C_END || ''',''yyyy-MM-dd'') GROUP BY BCRQ,GDDM,GSDM,ZQDM,BS';
    EXECUTE IMMEDIATE v_exe_sql;

    v_exe_sql := 'insert into t_chk_data_analysis(c_source, d_date, c_sh_acc_code, c_chan_code, c_sec_code, c_bs, n_count, n_sum_cjsl, n_sum_cjje)
    select ''SZHB'',HBCJRQ,HBGDDM,NVL(SUBSTR(HBHTXH,0,6),''000000''),HBZQDM,HBYWLB,COUNT(*),SUM(HBCJSL),SUM(HBCJSL*HBCJJG) from t_d_od_sjshb
    WHERE HBCJRQ BETWEEN TO_DATE(''' || C_START || ''',''yyyy-MM-dd'') AND to_date(''' || C_END || ''',''yyyy-MM-dd'') GROUP BY HBCJRQ,HBGDDM,SUBSTR(HBHTXH,0,6),HBZQDM,HBYWLB';
    EXECUTE IMMEDIATE v_exe_sql;

    v_exe_sql := 'insert into t_chk_data_analysis(c_source, d_date, c_sh_acc_code, c_chan_code, c_sec_code, c_bs, n_count, n_sum_cjsl, n_sum_cjje)
    SELECT ''SZFX'',FXFSRQ,FXGDDM,FXXWDM,FXZQDM,FXYWLB,COUNT(*),SUM(FXQRGS),SUM(FXZJJE*FXQRGS) FROM t_d_od_sjsfx
    WHERE  FXFSRQ BETWEEN TO_DATE(''' || C_START || ''',''yyyy-MM-dd'') AND to_date(''' || C_END || ''',''yyyy-MM-dd'') GROUP BY FXFSRQ,FXGDDM,FXXWDM,FXZQDM,FXYWLB';
    EXECUTE IMMEDIATE v_exe_sql;

    v_exe_sql := 'insert into t_chk_data_analysis(c_source, d_date, c_sh_acc_code, c_chan_code, c_sec_code, c_bs, n_count, n_sum_cjsl, n_sum_cjje)
    select ''SZGF'',GFFSRQ,NVL(GFGDDM,''0000000000''),GFXWDM,GFZQDM,GFYWLB,COUNT(*),SUM(GFQRGS),SUM(GFZJJE) from t_d_od_SJSgf
    WHERE  GFFSRQ BETWEEN TO_DATE(''' || C_START || ''',''yyyy-MM-dd'') AND to_date(''' || C_END || ''',''yyyy-MM-dd'') GROUP BY GFFSRQ,NVL(GFGDDM,''0000000000''),GFXWDM,GFZQDM,GFYWLB';
    EXECUTE IMMEDIATE v_exe_sql;

    v_exe_sql := 'insert into t_chk_data_analysis(c_source, d_date, c_sh_acc_code, c_chan_code, c_sec_code, c_bs,c_ltlx,c_zqlb,c_bdlx,n_count, n_sum_cjsl, n_sum_cjje)
    SELECT ''ZQBD'',BDRQ,ZQZH,XWH,ZQDM,qylb,ltlx,zqlb,bdlx,count(*),sum(bdsl),0 FROM t_d_od_zqbd
    WHERE BDRQ BETWEEN TO_DATE(''' || C_START || ''',''yyyy-MM-dd'') AND to_date(''' || C_END || ''',''yyyy-MM-dd'') GROUP BY BDRQ,ZQZH,XWH,ZQDM,qylb,ltlx,zqlb,bdlx';
    EXECUTE IMMEDIATE v_exe_sql;

    COMMIT;



/*
select * from T_CHK_DATA_ANALYSIS WHERE  C_ZQLB IS NULL AND C_SOURCE = 'SHGH' AND C_SEC_CODE LIKE '51%'

--上海股票
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP' WHERE (C_SOURCE = 'SHGH' AND C_SEC_CODE LIKE '60%');

--上海股票- 新股申购/中签－首发  T+1 --只有中签
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP',C_BDLX = 'XGSG_SZPS_IPO' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '737%' OR C_SEC_CODE LIKE '787%'));

--上海股票- 新股申购/中签－首发配号  T+1
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP',C_BDLX = 'XGSG_SZPS_IPO_PH' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '747%' OR C_SEC_CODE LIKE '797%'));


--上海股票- 市值配售
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP',C_BDLX = 'SZPS' WHERE (C_SOURCE = 'SHGH' AND C_SEC_CODE LIKE '747%');


--上海股票- 新股申购/中签-新股东定价增发  T+3
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP',C_BDLX = 'XGZF_XGD' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '730%' OR C_SEC_CODE LIKE '780%'));

--上海股票- 新股扣款/返款
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP',C_BDLX = 'XGFK' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '740%' OR C_SEC_CODE LIKE '790%'));

--上海股票- 老股东定价增发--只有中签
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP',C_BDLX = 'XGZF_LGD' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '700%' or C_SEC_CODE LIKE '760%'));


--上海股票- 新股发行配号
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP',C_BDLX = 'XGPH' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '741%' or C_SEC_CODE LIKE '791%'));


--上海国债
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_GZXQ' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '0%' OR C_SEC_CODE LIKE '10%'));

--上海可转换
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_KZZ' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '110%' OR C_SEC_CODE LIKE '113%'));

--上海可转换申购
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_KZZ',C_BDLX = 'XZSG_KZZ' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '733%' OR C_SEC_CODE LIKE '783%'));


--上海债券- 可转债扣款/返款
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_KZZ',C_BDLX = 'XZFK' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '743%' OR C_SEC_CODE LIKE '793%'));

--上海公司债券751990-751999申购
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_GSZ',C_BDLX = 'XZSG' WHERE (C_SOURCE = 'SHGH' AND SUBSTR(C_SEC_CODE,0,5) IN ('75197','75198','75199'));

--上海公司债券122
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_GSZ',C_BDLX = 'BS' WHERE (C_SOURCE = 'SHGH' AND C_SEC_CODE LIKE '122%');

--上海企业债债券129
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_QYZ',C_BDLX = 'BS' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '129%' or C_SEC_CODE LIKE '120%'));

--上海可分离债债券126
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_KFLZ',C_BDLX = 'BS' WHERE (C_SOURCE = 'SHGH' AND C_SEC_CODE LIKE '126%');

--本所决定将原公司债券发行代码段751990-751999增加调整为751970-751999代码段；将原地方政府债券发行代码段751900-751979调整为751900-751969代码段。
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_DFZ',C_BDLX = 'XZSG' WHERE (C_SOURCE = 'SHGH' AND SUBSTR(C_SEC_CODE,0,5) IN ('75190','75191','75192','75193','75194','75195','75196'));

--上海封闭式基金
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'JJ_FBS' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '500%'));

--上海ETF基金
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'JJ_KFS_ETF' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '510%'));

--上海权证-认购
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'QZ_RG' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '5800%'));

--上海权证-认沽
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'QZ_RZ' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '5809%'));

--上海回购-质押式
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'HG_ZYS' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '201%' OR  C_SEC_CODE LIKE '204%'));

--上海回购-开放式回购
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'HG_KFS' WHERE (C_SOURCE = 'SHGH' AND (C_SEC_CODE LIKE '202%'));


--深圳股票
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP_AG' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '00%'));

--深圳股票中小企业版
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP_ZXQY' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '003%'));

--创业版
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP_CYB' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '30%'));


UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'GP_ZXQY' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '002%'));

--【10】是国债的证券代码区间；
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_GZXQ' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '10%') AND C_SEC_CODE NOT LIKE '109%');

--【109000，109999】是地方政府债券的证券代码区间）
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_DFZ' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '109%'));

--【111000，111999】是企业债券的证券代码区间；
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_QYZ' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '111%'));

--【112000，112999】是公司债的证券代码区间；
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_GSZ' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '112%'));

--【115000，115999】是分离交易型可转债的证券代码区间；
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_KFLZ' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '115%'));

--【119000，119999】是资产证券化产品的证券代码区间；
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_ZCZCZQ' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '119%'));

--【12】是可转债的证券代码区间；
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'ZQ_KZZ' WHERE (C_SOURCE = 'SZHB' AND (C_SEC_CODE LIKE '12%'));

select distinct c_bs from T_CHK_DATA_ANALYSIS

--【030000，032999】是认购权证代码区间；
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'QZ_RG' WHERE (C_SOURCE = 'SZHB' AND SUBSTR(C_SEC_CODE,0,3) IN ('030','031','032'));

--【038000，039999】是A 股认沽权证代码区间；
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'QZ_RZ' WHERE (C_SOURCE = 'SZHB' AND SUBSTR(C_SEC_CODE,0,3) IN ('038','039'));


--证券代码【037001，037999】为上市公司股权激励计划涉及的员工认股权的证券代码区间；
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'QZ_GQ' WHERE (C_SOURCE = 'SZHB' AND SUBSTR(C_SEC_CODE,0,3) IN ('037'));

--【033000，036999】为权证业务预留的代码区间；


--深圳回购
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'HG_ZYS' WHERE (C_SOURCE = 'SZHB' AND SUBSTR(C_SEC_CODE,0,2) IN ('13'));


--深圳封闭式基金
UPDATE T_CHK_DATA_ANALYSIS SET C_ZQLB = 'JJ_FBS' WHERE (C_SOURCE = 'SZHB' AND SUBSTR(C_SEC_CODE,0,2) IN ('18'));

select * from T_CHK_DATA_ANALYSIS WHERE  C_ZQLB IS NULL and C_SOURCE='SZHB' AND C_SEC_CODE LIKE '28%'

--深圳创新封闭式基金
update t_chk_data_analysis set c_zqlb = 'JJ_FBS_CX' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '1500%'
--深圳契约型开放式
update t_chk_data_analysis set c_zqlb = 'JJ_KFS_LOF' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '161%'
update t_chk_data_analysis set c_zqlb = 'JJ_KFS_LOF' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '160%'
update t_chk_data_analysis set c_zqlb = 'JJ_KFS_LOF' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '164%'
update t_chk_data_analysis set c_zqlb = 'JJ_KFS_LOF' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '165%'
--深圳ETF
update t_chk_data_analysis set c_zqlb = 'JJ_KFS_ETF' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '159%'
--新股申购
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGSG' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '00%' AND C_BS = '7B'

--新股申购－撤单
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGSG_CD' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '00%' AND C_BS = '7C'


--新股增发
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGZF' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '07%' AND C_BS = '7B'


update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'QZ_PG' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '08%' AND C_BS = '7B'

--配股、老股东配售、配债
update t_chk_data_analysis set c_zqlb = 'ZQ',C_BDLX = 'XZPS' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '08%' AND C_BS = '4B' and N_SUM_CJJE/N_SUM_CJSL  =100
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XG_SZPS' where C_SOURCE = 'SZHB' AND C_SEC_CODE LIKE '08%' AND C_BS = '4B' and N_SUM_CJJE/N_SUM_CJSL  <> 100

--深圳债券利息
update t_chk_data_analysis set c_zqlb = 'ZQ',C_BDLX = 'ZQ_LX' where C_SOURCE = 'SZGF' AND C_SEC_CODE LIKE '1%' AND C_BS = 'X1'

--股票派息
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'GPPX' where C_SOURCE = 'SZGF' AND C_SEC_CODE LIKE '00%' AND C_BS = '20'

--债券派息
update t_chk_data_analysis set c_zqlb = 'ZQ',C_BDLX = 'ZQPX' where C_SOURCE = 'SZGF' AND C_SEC_CODE LIKE '1%' AND C_BS = '20'

--
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'GP_XS' where C_SOURCE = 'SZGF' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'XS'

--新股申购
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGSG' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A0'
--新股配号
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGPH' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A2'

--新股中签
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGZQ' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A3'

--新股返款
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGFK' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A6'


--新股申购
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGSG' where C_SOURCE = 'SZGF' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A0'

--新股配号
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGPH' where C_SOURCE = 'SZGF' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A2'

--新股中签
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGZQ' where C_SOURCE = 'SZGF' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A3'

--新股返款
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGFK' where C_SOURCE = 'SZGF' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A6'



--市值配售可配股数
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGPS_KPGS' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A4'
--市值配售
update t_chk_data_analysis set c_zqlb = 'GP',C_BDLX = 'XGPSZQ' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '00%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A5'


--基金申购
update t_chk_data_analysis set c_zqlb = 'JJ',C_BDLX = 'JJSG' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '16%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A0'
--基金配号
update t_chk_data_analysis set c_zqlb = 'JJ',C_BDLX = 'JJPH' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '16%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A2'

--基金中签
update t_chk_data_analysis set c_zqlb = 'JJ',C_BDLX = 'JJZQ' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '16%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A3'

--基金返款
update t_chk_data_analysis set c_zqlb = 'JJ',C_BDLX = 'JJFK' where C_SOURCE = 'SZFX' AND (C_SEC_CODE LIKE '16%' OR C_SEC_CODE LIKE '30%') AND C_BS = 'A6'

*/


EXCEPTION
   WHEN OTHERS
   THEN
      BEGIN
         DBMS_OUTPUT.put_line (v_exe_sql);
         ROLLBACK;
      END;
END PRC_chk_data_analysis;
