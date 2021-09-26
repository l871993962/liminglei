create or replace procedure Pro_DAE(c_item     in varchar2,
                                    c_item_v   in varchar2,
                                    n_FixValue in boolean,
                                    dae_Row    in out nocopy DAE_ROW_TYPE) is
  /*c_item 核算元素，多个中间用分号分隔*/
  /*c_item_v 核算元素的值域 与 c_item 相对应*/
  /*n_Fix 是否为固定值项 0:不是，1:是*/
  v_item   varchar2(100) := c_item; /*核算元素*/
  v_item_v varchar2(200) := c_item_v; /*核算元素的值*/
  v_type   varchar2(4) := ''; /*数据类型*/
begin
  if (upper(substr(v_item_v, 1, 2)) = 'KM') then
    /*当数据为科目时*/
    v_type := 'KM';
  elsif (upper(substr(v_item_v, 1, 4)) = 'DICT') then
    /*当数据为凭证字典时*/
    v_type := 'DICT';
  else
    /*当数据为核算元素时*/
    v_type := 'DAE';
  end if;
  if (instr(v_item_v, '{') > -1) then
    v_item_v := substr(v_item_v, instr(v_item_v, '{') + 1);
  end if;
  if (instr(v_item_v, '}') > -1) then
    v_item_v := substr(v_item_v, 1, instr(v_item_v, '}') - 1);
  end if;

  if (v_type = 'KM') then
    pro_DAE_Km(v_item, v_item_v, n_FixValue, dae_Row);
  elsif (v_type = 'DICT') then
    pro_DAE_Dict(v_item, v_item_v, n_FixValue, dae_Row);
  else
    pro_DAE_Dae(v_item, v_item_v, n_FixValue, dae_Row);
  end if;
end Pro_DAE;
