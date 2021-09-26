CREATE OR REPLACE PROCEDURE pro_DAE_DICT_CVT(dictName in varchar2,
                                             dvaValue in out nocopy varchar2,
                                             dae_Row  in out nocopy DAE_ROW_TYPE) as
  /*dictName 科目字典代码*/
  /*dvaValue 核算元素的值*/
  /*dae_Row 保存核算元素的类型*/
  --v_Value varchar2(100) := '';
  --v_Dict  varchar2(100) := dictName;
  v_Flag number(3) := 0; /*标记*/
begin
  if (dictName is not null) then
    /*由组合、全代码判断*/
    select count(*)
      into v_Flag
      from T_F_SC_DICT
     where C_DICT_CODE = dictName
       and C_ACCT_CODE = dae_Row.C_KM_CODE
       and C_PORT_CODE = dae_Row.C_PORT_CODE;
    if (v_Flag = 1) then
      select C_CVT_CODE
        into dvaValue
        from T_F_SC_DICT
       where C_DICT_CODE = dictName
         and C_ACCT_CODE = dae_Row.C_KM_CODE
         and C_PORT_CODE = dae_Row.C_PORT_CODE;
      return;
    end if;
    v_Flag := 0;
    /*由公共组合、全代码判断*/
    select count(*)
      into v_Flag
      from T_F_SC_DICT
     where C_DICT_CODE = dictName
       and C_ACCT_CODE = dae_Row.C_KM_CODE
       and C_PORT_CODE = ' ';
    if (v_Flag = 1) then
      select C_CVT_CODE
        into dvaValue
        from T_F_SC_DICT
       where C_DICT_CODE = dictName
         and C_ACCT_CODE = dae_Row.C_KM_CODE
         and C_PORT_CODE = ' ';
      return;
    end if;
    v_Flag := 0;
    /*由组合、截位代码判断*/
    select count(*)
      into v_Flag
      from T_F_SC_DICT
     where C_DICT_CODE = dictName
       and C_PORT_CODE = dae_Row.C_PORT_CODE
       and substr(dae_Row.C_KM_CODE, N_Start, N_LEN) = C_ACCT_CODE;
    if (v_Flag = 1) then
      select C_CVT_CODE
        into dvaValue
        from T_F_SC_DICT
       where C_DICT_CODE = dictName
         and C_PORT_CODE = dae_Row.C_PORT_CODE
         and substr(dae_Row.C_KM_CODE, N_Start, N_LEN) = C_ACCT_CODE;
      return;
    end if;
    v_Flag := 0;
    /*由公共组合、截位代码判断*/
    select count(*)
      into v_Flag
      from T_F_SC_DICT
     where C_DICT_CODE = dictName
       and C_PORT_CODE = ' '
       and substr(dae_Row.C_KM_CODE, N_Start, N_LEN) = C_ACCT_CODE;
    if (v_Flag = 1) then
      select C_CVT_CODE
        into dvaValue
        from T_F_SC_DICT
       where C_DICT_CODE = dictName
         and C_PORT_CODE = ' '
         and substr(dae_Row.C_KM_CODE, N_Start, N_LEN) = C_ACCT_CODE;
      return;
    end if;
  end if;
end pro_DAE_DICT_CVT;
