create or replace procedure Pro_GetPlan(i_date  in varchar2,
                                   i_port     in varchar2,
                                   o_plancode out varchar2,
                                   o_kmlevel  out number) as
   /**
   *版本：v1.0.0.7
   *用途：此存储过程是根据日期，组合，从方案关联表（T_E_EXEC_PLAN_RELA）中取出方案代码，
   * 同时，关联T_F_SC_KM_RELA中取出该方案对应的科目级别
   *修改记录：
   *   chenyoulong 20121226 创建
   **/

  -- 组合代码
  v_port varchar2(20) := i_port;
  -- 开始日期
  v_date varchar2(20) := i_date;
begin

  declare

    v_p_code varchar2(30);

    v_k_spec number(2);

    Cursor plan_cur is
      select a.C_PLAN_CODE, nvl(length(b.c_km_spec), 0) as c_km_spec
        from T_E_EXEC_PLAN_RELA a
        left join (select c_km_spec, c_plan_code
                     from T_F_SC_KM_RELA
                    where N_CHECK_STATE = 1) b on a.C_PLAN_CODE =
                                                  b.c_plan_code
       where a.C_PLAN_Type = 'AO_LEVEL'
         and a.n_check_state = 1
         and a.C_PORT_CODE = v_port
         and a.d_begin <= to_date(v_date, 'yyyy-MM-dd')
         and a.d_end >= to_date(v_date, 'yyyy-MM-dd');

    /**
    * 开始使用游标取数据
    **/
  begin
    -- 打开游标
    open plan_cur;
    Loop
      fetch plan_cur
        into v_p_code, v_k_spec;
      exit when plan_cur%notfound;

      -- 如果不为空，则返回所取的数值，为空，则返回默认值
      if v_p_code is not null then
        o_plancode := v_p_code;
        o_kmlevel := v_k_spec;
      else
        o_plancode := '';
        o_kmlevel  := 0;
      end if;
    End loop;
    -- 关闭游标
    close plan_cur;
  end;
end;
