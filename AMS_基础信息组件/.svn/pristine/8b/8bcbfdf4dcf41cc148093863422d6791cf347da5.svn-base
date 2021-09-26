create or replace procedure Prc_Dz_TDzJJGZB(i_port in varchar2,
                                            i_date in varchar2,
                                            i_user in varchar2,
                                            o_res    out varchar2,
                                            o_res_detail out varchar2) as
   /**
   *版本：v1.0.0.7
   *用途：将估值系统中估值表（T_R_FR_ASTSTAT）数据，根据条件，填充到电子对账估值中间表(TDZJJGZB)中
   *参数：输入参数组合代码i_port、统计日期i_date,用户代码 i_user；输出参数：处理结果：o_res（成功 or 失败），o_res_detail 处理结果明细
   *修改记录：
   *   chenyoulong 20121226 创建
   **/

  -- 组合代码
  v_port varchar2(20) := i_port;
  -- 开始日期
  v_date varchar2(20) := i_date;
  -- 声明方案代码变量
  v_plan_code varchar2(20);
  -- 声明方案级别变量
  v_km_spec int;
  -- 报文序号
  v_fsnnum nvarchar2(30);
  -- 资产净值，本位币成本
  v_port_cost number(18, 4) := 0;
  -- 资产净值，本位币市值
  v_port_mv number(18, 4) := 0;
  -- 资产净值，本位币估值增值
  v_port_iv number(18, 4) := 0;
   -- 获取生成的数据的行数
  v_rowcount number(10) := 0;
  -- 文件类型
  v_filetype varchar2(4) := '1011';
  -- 报表类型
  v_rpttype varchar2(2) := '01';
    -- 基金代码
  v_fcode varchar(20) := i_port;
begin
  -- 1 调用获取核算执行方案代码的存储过程
  pro_getplan(v_date, v_port, v_plan_code, v_km_spec);

  if v_km_spec is not null then

    -- 获取当天的资产净值，用于计算成本占净值比例，市值占净值比例
    declare
      Cursor bl_cur is
        select A.N_PORT_COST, A.N_PORT_MV, A.N_PORT_IV
          from T_R_FR_ASTSTAT a
         where a.c_port_code = v_port
           and a.C_NAV_TYPE = 'TOTAL'
           and D_ASTSTAT = to_date(v_date, 'yyyy-MM-dd')
           and C_KEY_CODE = 'ZCJZ'
           and rownum = 1
         order by a.C_KM_CODE;
      -- 打开游标
    begin
      open bl_cur;
      loop
        fetch bl_cur
          into v_port_cost, v_port_mv, v_port_iv;
        exit when bl_cur%notfound;
      end loop;
      close bl_cur;
    end;

    -- 2. 获取fsn代码
    v_fsnnum := CREATEFSN(v_date);
      -- 获取基金代码
    v_fcode := GETFUNDCODE(v_port);

    -- 2. 在产生电子对账估值表数据前，先根据组合、日期删除掉电子对账估值表中重复数据，避免出现重复数据
   /* delete from TDZJJGZB a
     where a.ffundcode = v_port
       and a.fbdate = to_date(v_date, 'yyyy-MM-dd');*/

    -- 3. 产生估值产生科目的数据，即排除掉 类型C_NAV_TYPE 不等于“TOTAL”，“TOTAL_ALL”的数据
    while v_km_spec > 0 Loop
      begin
        insert into tdzjjgzb
          (ffiletype,
           ffundcode,
           frpttype,
           fbdate,
           fedate,
           fkmbm,
           fkmmc,
           fhqjg,
           fhqbz,
           fzqsl,
           fzqcb,
           fzqsz,
           fgz_zz,
           fcb_jz_bl,
           fsz_jz_bl,
           fisdetail,
           fsn)
          select '1011' as ffiletype,
                 v_fcode as ffundcode,
                 '01' as frpttype,
                 to_date(v_date, 'yyyy-MM-dd') as fbdate,
                 to_date(v_date, 'yyyy-MM-dd') as fedate,
                 C_KM_CODE as fkmbm,
                 C_KM_NAME as fkmmc,
                 N_VA_PRICE as fhqjg,
                 1 as fhqbz,
                 n_amount as fzqsl,
                 N_PORT_COST as FZqcb,
                 N_PORT_MV as FZqsz,
                 N_PORT_IV as FGz_zz,
                 case
                   when n_port_cost <> 0 then
                    N_PORT_COST / v_port_cost
                   else
                    0
                 end as fcb_jz_bl,
                 case
                   when n_port_mv <> 0 then
                    N_PORT_MV / v_port_mv
                   else
                    0
                 end as fsz_jz_bl,
                 N_DETAIL as fisdetail,
                 v_fsnnum as fsn
            from (select C_KM_CODE,
                         C_KM_NAME,
                         C_KM_CODE_P,
                         C_DC_CODE,
                         N_DETAIL,
                         Max(N_VA_RATE) as N_VA_RATE,
                         sum(N_AMOUNT) AS N_AMOUNT,
                         sum(N_ORIG_COST) as N_ORIG_COST,
                         sum(N_PORT_COST) as N_PORT_COST,
                         Max(N_VA_PRICE) as N_VA_PRICE,
                         sum(N_ORIG_MV) as N_ORIG_MV,
                         sum(N_PORT_MV) as N_PORT_MV,
                         sum(N_ORIG_IV) as N_ORIG_IV,
                         sum(N_PORT_IV) as N_PORT_IV
                    from (select c.C_KM_CODE,
                                 max(case
                                       when b.N_Detail = 1 then
                                        c.C_KM_NAME
                                       else
                                        b.C_KM_NAME
                                     end) as C_KM_NAME,
                                 max(c.C_KM_CODE_P) as C_KM_CODE_P,
                                 b.C_DC_CODE,
                                 b.N_DETAIL,
                                 decode(b.C_DC_CODE,
                                        '***',
                                        0,
                                        max(c.N_VA_RATE)) as N_VA_RATE,
                                 decode(b.N_DETAIL, 1, max(c.N_AMOUNT), 0) as N_AMOUNT,
                                 decode(b.C_DC_CODE,
                                        '***',
                                        0,
                                        sum(c.N_ORIG_COST)) as N_ORIG_COST,
                                 sum(c.N_PORT_COST) as N_PORT_COST,
                                 Max(c.N_VA_PRICE) as N_VA_PRICE,
                                 decode(b.C_DC_CODE,
                                        '***',
                                        0,
                                        sum(c.N_ORIG_MV)) as N_ORIG_MV,
                                 sum(c.N_PORT_MV) as N_PORT_MV,
                                 decode(b.C_DC_CODE,
                                        '***',
                                        0,
                                        sum(c.N_ORIG_IV)) as N_ORIG_IV,
                                 sum(c.N_PORT_IV) as N_PORT_IV
                            from (select C_KM_CODE,
                                         C_KM_NAME,
                                         C_KM_CODE_P,
                                         C_DC_CODE,
                                         N_DETAIL
                                    from T_F_SC_KM a
                                   where GETKM_LEVEL(C_KM_CODE) = v_km_spec
                                     and a.C_PLAN_CODE = v_plan_code) b
                            join (select N_VA_RATE,
                                        N_AMOUNT,
                                        N_ORIG_COST,
                                        N_PORT_COST,
                                        N_VA_PRICE,
                                        N_ORIG_MV,
                                        N_PORT_MV,
                                        N_ORIG_IV,
                                        N_PORT_IV,
                                        GETLEVEL_KM(C_KM_CODE, v_km_spec) as C_KM_CODE,
                                        C_KM_NAME,
                                        GETLEVEL_KM(C_KM_CODE_T, v_km_spec) as C_KM_CODE_T,
                                        GETLEVEL_KM(C_KM_CODE, v_km_spec - 1) as C_KM_CODE_P
                                   from T_R_FR_ASTSTAT
                                  where C_PORT_CODE = v_port
                                    and d_aststat =
                                        to_date(v_date, 'yyyy-MM-dd')
                                    and C_NAV_TYPE not in
                                        ('TOTAL', 'TOTAL_ALL')
                                    AND C_KM_CODE IS NOT NULL
                                    AND C_KM_CODE <> ' ') c on b.C_KM_CODE =
                                                               c.C_KM_CODE_T
                           group by c.C_KM_CODE,
                                    b.C_KM_CODE_P,
                                    b.C_DC_CODE,
                                    b.N_DETAIL)
                   group by C_KM_CODE,
                            C_KM_NAME,
                            C_KM_CODE_P,
                            C_DC_CODE,
                            N_DETAIL
                   order by C_KM_CODE);
        v_km_spec := v_km_spec - 1;
        v_rowcount := v_rowcount + sql%rowcount;
      end;
    end loop;

    -- 3. 产生估值为产生科目的数据，即取出 类型C_NAV_TYPE 等于“TOTAL”，“TOTAL_ALL”的数据
    insert into tdzjjgzb
      (ffiletype,
       ffundcode,
       frpttype,
       fbdate,
       fedate,
       fkmbm,
       fkmmc,
       fhqjg,
       fhqbz,
       fzqsl,
       fzqcb,
       fzqsz,
       fgz_zz,
       fcb_jz_bl,
       fsz_jz_bl,
       fisdetail,
       fsn)
      select '1011' as ffiletype,
             v_fcode as ffundcode,
             '01' as frpttype,
             to_date(v_date, 'yyyy-MM-dd') as fbdate,
             to_date(v_date, 'yyyy-MM-dd') as fedate,
             C_ZB_CODE as fkmbm,
             case
               when C_NAV_TYPE = 'TOTAL' then
                'HJ'
               else
                C_KM_NAME
             end as fkmmc,
             N_VA_PRICE as fhqjg,
             1 as fhqbz,
             n_amount as fzqsl,
             N_PORT_COST as FZqcb,
             case
               when C_NAV_TYPE = 'TOTAL' then
                N_PORT_MV
               else
                N_VA_RATE
             end as FZqsz,
             N_PORT_IV as FGz_zz,
             0 as fcb_jz_bl,
             0 as fsz_jz_bl,
             1 as fisdetail,
             v_fsnnum as fsn
        from (select a.*
                from (select a.*, b.C_ZB_CODE
                        from T_R_FR_ASTSTAT a
                        join (select C_ZB_CODE,
                                    C_DZ_CODE,
                                    C_PORT_CODE_CLS,
                                    C_DV_ZB_CODE
                               from T_Z_BI_RELA
                              WHERE N_CHECK_STATE = 1
                                and C_PORT_CODE_CLS is null) B ON B.C_DV_ZB_CODE =
                                                                  A.c_Key_Code
                       where a.c_port_code = v_port
                         and a.C_NAV_TYPE in ('TOTAL', 'TOTAL_ALL')
                         and a.D_ASTSTAT = to_date(v_date, 'yyyy-MM-dd')
                      union all
                      select a.*, b.C_ZB_CODE
                        from T_R_FR_ASTSTAT a
                        join (select C_ZB_CODE,
                                     C_DZ_CODE,
                                     C_PORT_CODE_CLS,
                                     C_DV_ZB_CODE
                                from T_Z_BI_RELA
                               WHERE N_CHECK_STATE = 1
                                 and C_PORT_CODE_CLS is not null) B ON B.C_DV_ZB_CODE =
                                                                       A.c_Key_Code
                                                                   and SUBSTR(A.C_KM_CODE,
                                                                              INSTR(A.C_KM_CODE,
                                                                                    '_',
                                                                                    1,
                                                                                    1) + 1) =
                                                                       b.C_PORT_CODE_CLS
                       where a.c_port_code = v_port
                         and a.C_NAV_TYPE in ('TOTAL', 'TOTAL_ALL')
                         and a.D_ASTSTAT = to_date(v_date, 'yyyy-MM-dd')) a
               order by a.c_km_code);
     v_rowcount := v_rowcount + sql%rowcount;

  -- 如果已经产生了电子对账数据，则需要向电子对账信息表中添加一条待发送电子对账信息
  if v_rowcount > 0 then
     pro_dz_tdzbbinfo(v_port,to_date(v_date,'yyyy_MM-dd'),v_fsnnum,i_user,0,v_filetype,v_rpttype);
  end if;
  end if;
  COMMIT;
  o_res := 'Success';
  o_res_detail := '产生电子对账估值表数据成功';
  -- 如果发生异常则回滚
EXCEPTION
  WHEN OTHERS THEN
     o_res := 'Fail';
     o_res_detail:= sqlerrm;
    ROLLBACK;
end;
