create or replace procedure PRO_setDAE(daeName    in varchar2,
                                       daeValue   in varchar2,
                                       n_FixValue in boolean,
                                       dae_Row    in out nocopy DAE_ROW_TYPE) as
  /*设置核算元素的值*/
  /*daeName:核算元素代码值*/
  /*daeValue:核算元素的值*/
  v_Name  varchar2(100) := daeName;
  v_Value varchar2(100) := daeValue;
  V_Curr  varchar2(50) := '';
begin
  if (v_Value = ' ' or v_Value = '') then
    v_Value := null;
  end if;
  if (instr(v_Name, '<') > 0) then
    v_Name := substr(v_Name, instr(v_Name, '<') + 1);
  end if;
  if (instr(v_Name, '>') > 0) then
    v_Name := substr(v_Name, 1, instr(v_Name, '>') - 1);
  end if;
  if (v_Name = 'ISSUE_MODE' /*'发行方式'*/
     ) then
    dae_Row.C_DV_ISSUE_MODE := NVL(v_Value, '<ISSUE_MODE>');
  elsif (v_Name = 'VAR_DUR' /*'期限'*/
        ) then
    dae_Row.C_DV_VAR_DUR := NVL(v_Value, '<VAR_DUR>');
  elsif (v_Name = 'PORT_CLS' /*'分级组合'*/
        ) then
    dae_Row.C_PORT_CLS_CODE := NVL(v_Value, '<PORT_CLS>');
  elsif (v_Name = 'CA' /*'现金账户'*/
        ) then
    dae_Row.C_CA_CODE := NVL(v_Value, '<CA>');
  elsif (v_Name = 'SEC' /*'证券内码'*/
        ) then
    dae_Row.C_SEC_CODE := NVL(v_Value, '<SEC>');
  elsif (v_Name = 'FEE' /*'费用代码'*/
        ) then
    dae_Row.C_FEE_CODE := NVL(v_Value, '<FEE>');
  elsif (v_Name = 'NET' /*'网点代码'*/
        ) then
    dae_Row.C_NET_CODE := NVL(v_Value, '<NET>');
    /*elsif (v_Name = '交易类型') then
    dae_Row.C_DV_ISSUE_MODE := NVL(v_Value, '<DT>');*/
  elsif (v_Name = 'MKT' /*'交易市场'*/
        ) then
    dae_Row.C_MKT_CODE := NVL(v_Value, '<MKT>');
  elsif (v_Name = 'SEC_VAR' /*'证券品种'*/
        ) then
    dae_Row.C_SEC_VAR_CODE := NVL(v_Value, '<SEC_VAR>');
  elsif (v_Name = 'ACC_TYPE' /*'账户类型'*/
        ) then
    dae_Row.C_DV_ACC_TYPE := NVL(v_Value, '<ACC_TYPE>');
  elsif (v_Name = 'FEE_TYPE' /*'费用品种'*/
        ) then
    dae_Row.C_DV_FEE_TYPE := NVL(v_Value, '<FEE_TYPE>');
  elsif (v_Name = 'DS' /*'销售类型'*/
        ) then
    dae_Row.C_DS_CODE := NVL(v_Value, '<DS>');
  elsif (v_Name = 'TD_CHAN' /*'交易渠道'*/
        ) then
    dae_Row.C_TD_CHAN_CODE := NVL(v_Value, '<TD_CHAN>');
  elsif (v_Name = 'DC' /*'交易币种'*/
        ) then
    dae_Row.C_DC_CODE := NVL(v_Value, '<DC>');
  elsif (v_Name = 'DTA' /*'交易属性'*/
        ) then
    dae_Row.C_DTA_CODE := NVL(v_Value, '<DTA>');
  elsif (v_Name = 'INVEST_CLS' /*'投资分类'*/
        ) then
    dae_Row.C_DV_INVEST_CLS := NVL(v_Value, '<INVEST_CLS>');
  end if;
  if (v_Value is null) then
    v_Curr := '<' || v_Name || '>';
  else
    v_Curr := v_Value;
  end if;
  if (n_FixValue = false) then
    dae_Row.C_DVA_CURR   := v_Name;
    dae_Row.C_DVA_CURR_V := V_Curr;
  end if;
end;
