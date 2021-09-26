CREATE OR REPLACE PROCEDURE Pro_DAE_Cvt(c_item   in varchar2,
                                        c_item_v in varchar2,
                                        b_cvt_km in boolean,
                                        b_Cvt    in out nocopy varchar2,
                                        dae_Row  in out nocopy DAE_ROW_TYPE) is
  /*按科目代码转换*/
  /*c_item 核算元素，多个中间用分号分隔*/
  /*c_item_v 核算元素的值域 与 c_item 相对应
  DAE{BBH AUD|01;ACC_EX_RES|02;<MKT>|03}DICT{dictName}

  DAE{BBH AUD|dictName1;ACC_EX_RES|dictName2;<MKT>|dictName3}FIX{DAE|daeV}
  --OR--
  DAE{BBH AUD|dictName1;ACC_EX_RES|dictName2;<MKT>|dictName3}FIX{TXT|txtV}
  */
  /*b_cvt_km: 是否需要转换科目代码，值为真表示需要转换*/
  /*核算元素*/
  v_item     varchar2(100) := c_item; 
  /*核算元素的值*/
  v_item_v   varchar2(200) := c_item_v; 
  /*核算元素值*/
  v_dae_v    varchar2(200) := ''; 
  /*国定值用于转换科目代码及名称*/
  v_fix_v    varchar2(20) := ''; 
  v_Dae_Dict VarTableType := VarTableType();
begin
  if (v_item_v is not null) then
    /*当数据包含有固定值时*/
    if (instr(v_item_v, 'FIX{') > 0) then
      v_fix_v  := substr(v_item_v, instr(v_item_v, 'FIX{') + 4);
       /*截取国定项的值*/
      v_item_v := substr(v_item_v, 1, instr(v_item_v, 'FIX{') - 1);
    end if;

    /*当数据包含有核算项目时*/
    if (instr(v_item_v, 'DAE{') > 0) then
      v_dae_v := substr(v_item_v, instr(v_item_v, 'DAE{') + 4);
    end if;

    if (instr(v_fix_v, '}') > 0) then
      v_fix_v := substr(v_fix_v, 1, instr(v_fix_v, '}') - 1);
    end if;
    if (instr(v_dae_v, '}') > 0) then
      v_dae_v := substr(v_dae_v, 1, instr(v_dae_v, '}') - 1);
    end if;

    if (v_dae_v is not null) then
      /*调用过程处理*/
      pro_DAE_DAE_CVT(v_item, v_dae_v, b_cvt_km, dae_Row, v_Dae_Dict);
    end if;

    /*转换科目代码*/
    if (v_fix_v is not null and b_cvt_km) then
      if (instr(v_fix_v, 'TXT|') > 0) then
        v_fix_v           := substr(v_fix_v, 5);
        dae_Row.C_KM_CODE := dae_Row.C_KM_CODE_P || v_fix_v;
        b_Cvt             := v_fix_v;
      elsif (instr(v_fix_v, 'DAE|') > 0) then
        v_fix_v           := substr(v_fix_v, 5);
        dae_Row.C_KM_CODE := dae_Row.C_KM_CODE_P || '<' || v_fix_v || '>';
        b_Cvt             := '<' || v_fix_v || '>';
        /*if (v_Dae_Dict.count > 0) then
          For i in 1 .. v_Dae_Dict.count loop
            if (substr(v_Dae_Dict(i), 1, length(v_fix_v)) = v_fix_v) then
              dae_Row.C_KM_CODE := dae_Row.C_KM_CODE_P ||
                                   substr(v_Dae_Dict(i),
                                          length(v_fix_v) + 2);
            end if;
          end loop;
        else
        dae_Row.C_KM_CODE := dae_Row.C_KM_CODE_P || '<' || v_fix_v || '>';
        end if;*/
      end if;
    end if;

  end if;
end Pro_DAE_Cvt;
