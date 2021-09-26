CREATE OR REPLACE PROCEDURE PRO_KMPLAN(i_Plan_Code in varchar2,
                                       v_DAE       in out nocopy DAE_ROW_TYPE)
  Authid Current_User as
  /*生成科目方案*/
  v_Plan_Code varchar2(20) := i_Plan_Code;
  v_Cnt       number(3) := 0; /*相同配置记录数*/
  v_Sql       varchar2(2000) := ''; /*生成科目的SQL语句*/
  v_Cvt       varchar2(50) := ''; /*转换的科目信息，包括转换为固定值与核算元素*/
  v_IsAux     number(3) := 0; /*核算字段值*/
begin
  /*根据配置信息生成科目体系方案*/
  v_Sql := 'Create or Replace View VLAccount as(';
  for my_set in (select max(FYear) as FYear,
                        FSetCode as N_Set_Code,
                        trim(to_char(FSetCode, '000')) as FSetCode,
                        FSetID
                   from LSetList
                  where FSetId in
                        (select C_Port_Code
                           from T_Trans_Plan
                          where C_Plan_Code = v_Plan_Code)
                  group by FSetCode, FSetId
                  order by FSetCode, FYear) loop
    v_Sql := v_Sql || 'select facctcode||''<SEC>'' as facctcode,' ||
             'facctname,' || 'facctlevel+1 as facctlevel,' ||
             'facctcode as facctparent,' || 'facctdetail,' || 'facctclass,' ||
             'facctattr,' || 'facctattrid,' || 'fcurcode,' || 'fbaldc,' ||
             'famount,' || 'fcarryacc,' || 'fename,' || 'fby,' ||
             'fauxiacc,' || 'fportclscode' || ' from A' || my_set.Fyear ||
             my_set.Fsetcode || 'LAccount ' || ' where FAuxiAcc != '' '' ' ||
             ' union ' || ' select facctcode,' || 'facctname,' ||
             'facctlevel,' || 'facctparent,' ||
             'case when FAuxiAcc != '' ''then 0 else facctdetail end as facctdetail,' ||
             'facctclass,' || 'facctattr,' || 'facctattrid,' || 'fcurcode,' ||
             'fbaldc,' || 'famount,' || 'fcarryacc,' || 'fename,' || 'fby,' ||
             ''' '' as fauxiacc,' || 'fportclscode' || ' from A' ||
             my_set.Fyear || my_set.Fsetcode || 'LAccount union ';
  end loop;
  v_Sql := subStr(v_Sql, 1, length(v_Sql) - 6);
  v_Sql := v_Sql || ')';
  execute immediate v_Sql;
  /*删除科目表数据*/
  delete from T_F_SC_KM where C_Plan_Code = v_Plan_Code;
  delete from T_Trans_PlanKm;
  /*根据设置的科目性质 生成核算项目与核算元素*/
  for my_row in (select a.FAcctCode,
                        a.FAcctName,
                        a.FAcctParent,
                        a.FAcctLevel,
                        a.FAuxiAcc,
                        a.FAcctDetail,
                        a.FAcctAttr,
                        a.FAcctAttrid,
                        a.FBy,
                        a.FACCTCLASS,
                        a.FPortClsCode,
                        a.FAmount,
                        a.C_DV_JD_WAY,
                        b.C_Dae_Code,
                        NVL(b.C_DC_CODE,a.FCurCode) as FCurCode,
                        b.C_Item,
                        b.C_ITEM_V
                   from (select a1.FAcctCode,
                                a1.FAcctName,
                                a1.FAcctParent,
                                a1.FAcctLevel,
                                case
                                  when length(ltrim(a1.FAuxiAcc)) = 0 then
                                   0
                                  else
                                   1
                                end as FAuxiAcc,
				a1.FCurCode,
                                a1.FAcctDetail,
                                a1.FAcctAttr,
                                a1.FAcctAttrid,
                                a1.FBy,
                                Decode(a1.FBalDC,
                                       1,
                                       'JD_J',
                                       -1,
                                       'JD_D',
                                       'JD_P') as C_DV_JD_WAY,
                                a1.FPortClsCode,
                                a1.FAmount,
                                Decode(a1.FAcctClass,
                                       '资产类',
                                       'KC_ZC',
                                       '负债类',
                                       'KC_FZ',
                                       '共同类',
                                       'KC_GT',
                                       '权益类',
                                       'KC_QY',
                                       '损益类',
                                       'KC_SY',
                                       'KC_BW') as FACCTCLASS
                           from VLAccount a1) a
                   join (select FAcctCode,
                               C_DC_CODE,
                               C_DAE_CODE,
                               C_ITEM,
                               C_ITEM_V
                          from t_Trans_KMCFG
                         where C_DAE_CODE != ' '
                           and FSetCode = v_Plan_Code) b
                     on a.FAcctCode = b.FAcctCode
                  order by a.FAcctCode) loop
    /*---初始化变量------*/
    v_Cnt   := 0;
    v_Cvt   := '';
    v_IsAux := 0;
    v_DAE   := DAE_ROW_TYPE('',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '',
                            '');
    /*----初始化变量--END-----*/

    /*判断父级科目代码，获取父级科目的核算元素*/
    if (length(ltrim(my_row.Facctparent)) > 0) then
      for my_parent in (select km.*
                          from T_F_SC_KM km, T_Trans_PlanKm b
                         where km.C_KM_Code = b.C_KM_Code
                           and km.C_Plan_Code = v_Plan_Code
                           and b.FAcctCode = my_row.Facctparent) loop
        v_DAE.C_KM_Code       := my_parent.C_KM_Code;
        v_DAE.C_KM_CODE_P     := my_parent.c_KM_CODE;
        v_DAE.C_DTA_CODE      := my_parent.C_DTA_CODE;
        v_DAE.C_DV_ISSUE_MODE := my_parent.C_DV_ISSUE_MODE;
        v_DAE.C_DV_VAR_DUR    := my_parent.C_DV_VAR_DUR;
        v_DAE.C_DV_INVEST_CLS := my_parent.C_DV_INVEST_CLS;
        v_DAE.C_PORT_CLS_CODE := my_parent.C_PORT_CLS_CODE;
        v_DAE.C_DAI_CODE      := my_parent.C_DAI_CODE;
        v_DAE.C_CA_CODE       := my_parent.C_CA_CODE;
        v_DAE.C_SEC_CODE      := my_parent.C_SEC_CODE;
        v_DAE.C_FEE_CODE      := my_parent.C_FEE_CODE;
        v_DAE.C_NET_CODE      := my_parent.C_NET_CODE;
        v_DAE.C_MKT_CODE      := my_parent.C_MKT_CODE;
        v_DAE.C_SEC_VAR_CODE  := my_parent.C_SEC_VAR_CODE;
        v_DAE.C_DV_ACC_TYPE   := my_parent.C_DV_ACC_TYPE;
        v_DAE.C_DC_CODE       := my_parent.C_DC_CODE;
        v_DAE.C_DV_FEE_TYPE   := my_parent.C_DV_FEE_TYPE;
        v_DAE.C_DS_CODE       := my_parent.C_DS_CODE;
        v_DAE.C_TD_CHAN_CODE  := my_parent.C_TD_CHAN_CODE;
      end loop;
    end if;

    v_DAE.C_KM_CODE  := my_row.FAcctCode; /*科目代码*/
    v_DAE.C_DAI_CODE := my_row.C_DAE_CODE; /*核算项目*/
    if (my_row.c_item is not null and my_row.c_item != ' ') then
      Pro_DAE_Cvt(my_row.c_item, my_row.c_item_v, true, v_Cvt, v_DAE);
    end if;

    /*设置其他参数*/
    pro_SetOther(my_row.fcurcode,
                 my_row.FAcctAttrid,
                 my_row.FBy,
                 my_row.FPortClsCode,
                 v_DAE);

    if (v_DAE.C_KM_CODE_P is not null and
       length(my_row.FAcctCode) > length(my_row.FAcctParent)) then
      if (length(trim(v_Cvt)) > 0) then
        v_DAE.C_KM_CODE := v_DAE.C_KM_CODE_P || '.' || v_Cvt;
      else
        v_DAE.C_KM_CODE := v_DAE.C_KM_CODE_P || '.' ||
                           substr(my_row.FAcctCode,
                                  length(my_row.FAcctParent) + 1);
      end if;
    end if;

    if (length(trim(v_Cvt)) > 0) then
      if (substr(v_Cvt, 1, 1) = '<' and substr(v_Cvt, length(v_Cvt)) = '>') then
        v_IsAux := 1;
      end if;
    end if;

    select count(*)
      into v_Cnt /*当值大于0表示有相同的科目配置，需要过滤*/
      from T_F_SC_KM
     where C_Plan_Code = v_Plan_Code
       and C_KM_CODE = v_DAE.C_KM_CODE
       and C_KM_CODE_P = v_DAE.C_KM_CODE_P
       and C_DC_CODE = v_DAE.C_DC_CODE
       and C_DAI_CODE = v_DAE.C_DAI_CODE
       and C_CAI_CURR = v_DAE.C_DVA_CURR
       and C_DAI_DETAI = v_DAE.C_DVA_CURR_V;

    if (v_Cnt = 0) then
      /*当值等于0，执行数据的插入到科目体系表功能*/
      insert into T_Trans_PlanKm
        (c_Km_Code, C_KM_Code_P, Facctcode, Fparentcode)
      values
        (v_DAE.C_KM_CODE,
         v_DAE.C_KM_CODE_P,
         ltrim(my_row.FAcctCode),
         ltrim(my_row.FAcctParent));
      /*插入数据到财务－－科目体系表*/
      INSERT INTO T_F_SC_KM
        (C_PLAN_CODE /*方案代码*/,
         C_KM_CODE /*科目代码*/,
         C_KM_NAME /*科目名称*/,
         C_KM_CODE_P /*父级科目代码*/,
         C_DV_KM_CLS /*科目类别*/,
         C_DTA_CODE /*交易属性*/,
         C_DV_ISSUE_MODE /*发行方式*/,
         C_DV_VAR_DUR /*期限*/,
         C_DV_INVEST_CLS /*投资分类*/,
         C_PORT_CLS_CODE /*分级组合*/,
         C_DAI_CODE /*核算项目*/,
         C_CA_CODE /*帐户代码*/,
         C_SEC_CODE /*证券内码*/,
         C_FEE_CODE /*费用代码*/,
         C_NET_CODE /*网点代码*/,
         C_MKT_CODE /*交易市场*/,
         C_SEC_VAR_CODE /*证券品种*/,
         C_DV_ACC_TYPE /*帐户品种*/,
         C_DC_CODE /*币种，复合币为****/,
         C_DV_FEE_TYPE /*费用类型*/,
         C_DS_CODE /*销售方式*/,
         C_TD_CHAN_CODE /*交易渠道*/,
         C_CAI_CURR /*核算结构*/,
         C_DAI_DETAI /*核算元素的值*/,
         N_DETAIL /*是否明细项（1－明细项；0－非明细项）*/,
         N_STATE /*使用状态（0-未使用；1-已使用）*/,
         C_DV_BOOL_TYPE_AM /*核算数量标识*/,
         C_DV_JD_WAY /*借贷方向*/,
         C_DV_BOOL_TYPE_AUX /*辅助核算标识*/,
         C_DESC /*描述*/,
         N_CHECK_STATE /*审核状态（1-审核；0-未审核；-1-删除）*/,
         C_UPDATE_BY /*更新人*/,
         C_UPDATE_TIME /*更新时间*/,
         C_CHECK_BY /*审核人*/,
         C_CHECK_TIME /*审核时间*/)
      values
        (v_Plan_Code, /*方案代码*/
         v_DAE.C_KM_CODE, /*科目代码*/
         my_row.FAcctName, /*科目名称*/
         NVL(v_DAE.C_KM_CODE_P, '[root]'), /*父级科目代码*/
         my_row.FACCTCLASS, /*科目类别*/
         NVL(v_DAE.C_DTA_CODE, ' '), /*交易属性*/
         NVL(v_DAE.C_DV_ISSUE_MODE, ' '), /*发行方式*/
         NVL(v_DAE.C_DV_VAR_DUR, ' '), /*期限*/
         NVL(v_DAE. C_DV_INVEST_CLS, ' '), /*投资分类*/
         NVL(v_DAE. C_PORT_CLS_CODE, ' '), /*分级组合*/
         NVL(v_DAE. C_DAI_CODE, ' '), /*核算项目*/
         NVL(v_DAE. C_CA_CODE, ' '), /*帐户代码*/
         NVL(v_DAE. C_SEC_CODE, ' '), /*证券内码*/
         NVL(v_DAE. C_FEE_CODE, ' '), /*费用代码*/
         NVL(v_DAE. C_NET_CODE, ' '), /*网点代码*/
         NVL(v_DAE. C_MKT_CODE, ' '), /*交易市场*/
         NVL(v_DAE. C_SEC_VAR_CODE, ' '), /*证券品种*/
         NVL(v_DAE. C_DV_ACC_TYPE, ' '), /*帐户品种*/
         NVL(v_DAE. C_DC_CODE, '***'), /*币种，复合币为****/
         NVL(v_DAE. C_DV_FEE_TYPE, ' '), /*费用类型*/
         NVL(v_DAE. C_DS_CODE, ' '), /*销售方式*/
         NVL(v_DAE. C_TD_CHAN_CODE, ' '), /*交易渠道*/
         NVL(v_DAE. C_DVA_CURR, ' '), /*核算结构*/
         NVL(v_DAE. C_DVA_CURR_V, ' '), /*核算元素的值*/
         my_row.FAcctDetail, /*是否明细项（1－明细项；0－非明细项）*/
         0, /*使用状态（0-未使用；1-已使用）*/
         to_Char(my_row.FAmount), /*核算数量标识*/
         my_row. C_DV_JD_WAY, /*借贷方向*/
         v_IsAux, /*辅助核算标识*/
         ' ', /*描述*/
         1, /*审核状态（1-审核；0-未审核；-1-删除）*/
         'dbETL', /*更新人*/
         to_char(sysdate, 'yyyyMMdd HH:mm:ss'), /*更新时间*/
         'dbETL', /*审核人*/
         to_char(sysdate, 'yyyyMMdd HH:mm:ss') /*审核时间*/);
      commit;
    end if;
  end loop;
end pro_KmPlan;
