CREATE OR REPLACE PROCEDURE pro_DAE_DAE_CVT(dvaName    in varchar2,
                                            dvaValue   in varchar2,
                                            b_km_plan  in boolean,
                                            dae_Row    in out nocopy DAE_ROW_TYPE,
                                            v_Dae_Dict in out nocopy VarTableType) as
  /*dvaName 核算元素的代码*/
  /*dvaValue 核算元素的值*/
  /*dae_Row 保存核算元素的类型*/
  /*v_Dae_Dict  保存核算项目对应的字典值*/
  /*b_km_plan 用于生成科目方案，值为值表示用于生成方案，值为假，表示生成明细*/
  v_Value       varchar2(100) := '';
  v_Name        varchar2(100) := '';
  v_Name_Array  VarTableType := VarTableType(); /*核算元素*/
  v_Value_Array VarTableType := VarTableType(); /*核算值*/
  v_Value_Row   VarTableType := VarTableType(); /*单行核算元素的值*/
begin
  v_Dae_Dict := VarTableType();
  if (dvaName is not null and dvaValue is not null) then
    Pro_Dae_Regex(dvaName, '|', v_Name_Array);
    Pro_Dae_Regex(dvaValue, ';', v_Value_Array);
    For i in 1 .. v_Name_Array.count loop
      v_Name  := v_Name_Array(i);
      v_Value := '';
      if (v_Value_Array.count >= i) then
        v_Value := v_Value_Array(i);
        Pro_Dae_Regex(v_Value, '|', v_Value_Row);
        if (v_Value_Row.count = 2) then
          /*核算元素中有转换字典*/
          v_Value := v_Value_Row(1);
          PRO_setDAE_CVT(v_Name,
                         v_Value,
                         v_Value_Row(2),
                         b_km_plan,
                         dae_Row);
        elsif (v_Value_Row.count = 1) then
          /*核算元素中无转换字典*/
          v_Value := v_Value_Row(1);
          PRO_setDAE_CVT(v_Name, v_Value, '', b_km_plan, dae_Row);
        else
          v_Value := '';
          PRO_setDAE_CVT(v_Name, v_Value, '', b_km_plan, dae_Row);
        end if;
      else
        v_Value := '';
        PRO_setDAE_CVT(v_Name, v_Value, '', b_km_plan, dae_Row);
      end if;
      v_Dae_Dict.EXTEND;
      v_Dae_Dict(i) := v_Name || ';' || v_Value;
    end loop;
  elsif (dvaName is not null) then
    Pro_Dae_Regex(dvaName, '|', v_Name_Array);
    FOR i in 1 .. v_Name_Array.count loop
      v_Name  := v_Name_Array(i);
      v_Value := '';
      PRO_setDAE_CVT(v_Name, v_Value, '', b_km_plan, dae_Row);
      v_Dae_Dict.EXTEND;
      v_Dae_Dict(i) := v_Name || ';' || v_Value;
    end loop;
  else
    return;
  end if;
end pro_DAE_DAE_CVT;
