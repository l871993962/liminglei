create or replace procedure pro_DAE_DICT(dvaName    in varchar2,
                                         dvaValue   in varchar2,
                                         n_FixValue in boolean,
                                         dae_Row    in out nocopy DAE_ROW_TYPE) as
  /*dvaName 核算元素的名称,中文表示*/
  /*dvaValue 核算元素的值*/
  /*dae_Row 保存核算元素的类型*/
  v_Value       varchar2(100) := '';
  v_Name        varchar2(100) := '';
  v_Name_Array  VarTableType := VarTableType();
  v_Value_Array VarTableType := VarTableType();
begin
  if (dvaName is not null and dvaValue is not null) then
    Pro_Dae_Regex(dvaName, '|', v_Name_Array);
    Pro_Dae_Regex(dvaValue, ';', v_Value_Array);
    FOR i in 1 .. v_Name_Array.count loop
      v_Name := v_Name_Array(i);
      if (v_Value_Array.count >= i) then
        v_Value := v_Value_Array(i);
        for my_cur in (select FIndCode
                         from t_Trans_Vch_Dict
                        where FDICTCODE = v_Value_Array(i)
                          and (FCnvConent = dae_Row.C_KM_Code or
                              FCnvConent =
                              subStr(dae_Row.C_KM_Code,
                                      length(dae_Row.C_KM_CODE_P) + 1))
                          and (FPortCode = dae_Row.C_PORT_CODE or
                              FPortCode = ' ')
                        order by FPortCode DESC) loop
          v_Value := my_cur.findcode;
          PRO_setDAE(v_Name, v_Value, n_FixValue, dae_Row);
          return;
        end loop;
      else
        PRO_setDAE(v_Name, '', n_FixValue, dae_Row);
      end if;
    end loop;
  elsif (dvaName is not null) then
    Pro_Dae_Regex(dvaName, '|', v_Name_Array);
    FOR i IN 1 .. v_Name_Array.count LOOP
      v_Name := v_Name_Array(i);
      PRO_setDAE(v_Name, '', n_FixValue, dae_Row);
    end loop;
  else
    return;
  end if;
end;
