create or replace procedure Prc_Dz_TDZBALANCE(i_port in varchar2,
                                              i_date in varchar2,
                                              i_user in varchar2,
                                              o_res    out varchar2,
                                              o_res_detail out varchar2) as
  /**
   *版本：v1.0.0.7
   *用途：将估值系统中余额数据，根据条件，填充到电子对账余额中间表(tdzbalance)中
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
  v_km_spec number(2);
  -- 报文序号
  v_fsnnum nvarchar2(30);
  -- 年月，初始化为统计的年月
  v_year_month varchar2(20) := to_char(to_date(v_date, 'yyyy-MM-dd'),
                                       'yyyyMM');
  -- 会计期间,初始化默认为统计日期的月份
  v_period number(2) := to_char(to_date(v_date, 'yyyy-MM-dd'), 'MM');
  -- 是否年初数
  v_is_year_num number(1) := 0; -- 0表示不是年初数，1表示是年初数
  -- 定义年份,默认取库存日期所属年份
  v_year varchar2(20) := to_char(to_date(v_date, 'yyyy-MM-dd'), 'yyyy');
  -- 开始日期，默认为当前日期
  v_start_date date := to_date(v_date, 'yyyy-MM-dd');
  -- 结束日期，默认为当前日期
  v_end_date date := to_date(v_date, 'yyyy-MM-dd');
   -- 获取生成的数据的行数
  v_rowcount number(10) := 0;
  -- 文件类型
  v_filetype varchar2(4) := '1001';
  -- 报表类型
  v_rpttype varchar2(2) := '01';
    -- 基金代码
  v_fcode varchar(20) := i_port;
begin
  -- 1、判断当前统计日期是否存在年初数
  -- 1.1 如果当前库存日期为1月1日
  if to_char(to_date(v_date, 'yyyy-MM-dd'), 'MMdd') = '0101' then
    declare
      Cursor stock_cur is
        select D_Stock
          from t_d_Ai_Stock
         where C_Port_Code = v_port
           and C_Year_Month = v_year || '00'
           and D_Stock = to_date(v_date, 'yyyy-MM-dd')
           and rownum = 1;
      -- 开始使用游标取数据
    begin
      -- 打开游标
      open stock_cur;
      Loop
        fetch stock_cur
          into v_start_date;
        exit when stock_cur%notfound;

        -- 如果不为空，则返回所取的数值，为空，则返回默认值
        if v_start_date is not null then
          v_year      := to_char(v_start_date, 'yyyy');
          v_period    := to_char(v_start_date, 'MM');
          v_is_year_num := 1;
        end if;
      End loop;
      -- 关闭游标
      close stock_cur;
    end;
  end if;
  -- 1.2 如果年初数为0，即为非年初数是
  if v_is_year_num = 0 then
    declare
      Cursor stock_cur is
        select D_Stock
          from t_d_Ai_Stock
         where C_Port_Code = v_port
           and D_Stock =
               (select max(D_Stock) as D_Stock
                  from t_d_Ai_Stock
                 where C_Port_Code = v_port
                   and D_Stock <= to_date(v_date, 'yyyy-MM-dd'))
           and rownum = 1;
      -- 开始使用游标取数据
    begin
      -- 打开游标
      open stock_cur;
      Loop
        fetch stock_cur
          into v_start_date;
        exit when stock_cur%notfound;

        -- 如果不为空，则返回所取的数值，为空，则返回默认值
        if v_start_date is not null then
          v_year      := to_char(v_start_date, 'yyyy');
          v_period    := to_char(v_start_date, 'MM');
          v_is_year_num := 1;
        end if;
      End loop;
      -- 关闭游标
      close stock_cur;
    end;
  end if;

  -- 判断当前取出的开始日期是否为1月1日
  if to_char(v_start_date, 'MMdd') = '0101' then
    v_year_month := v_year || '00';
    v_end_date   := v_start_date;
  else
    v_year_month := to_char(v_start_date, 'yyyyMM');
    v_end_date   := v_start_date - 1;
  end if;

  -- 2.1 调用获取核算执行方案代码的存储过程
  pro_getplan(v_date, v_port, v_plan_code, v_km_spec);

  if v_km_spec is not null then

    -- 2. 获取fsn代码
    v_fsnnum := CREATEFSN(v_date);
      -- 获取基金代码
    v_fcode := GETFUNDCODE(v_port);

    -- begin
    -- 1. 在产生电子对账余额表数据前，先根据组合、日期删除掉电子对账余额表中重复数据，避免出现重复数据
  /*  delete from TDZBALANCE a
     where a.ffundcode = v_port
       and a.fbdate = to_date(v_date, 'yyyy-MM-dd');*/

    -- 2. 产生电子对账余额表的数据，由于库存表中，只存放明细的科目，因此上级科目余额，需要逐级累加计算
    insert into R_F_BM_BAL
      (C_Port_Code,
       C_KM_Code,
       C_KM_Name,
       C_KM_Code_P,
       C_KM_CODE_T,
       C_KM_CODE_P_T,
       N_DETAIL,
       N_Way,
       C_Year,
       D_Bal,
       N_Period,
       N_A_INI_Y,
       N_A_INI,
       N_A_DEB,
       N_A_CRE,
       N_A_DEB_Y,
       N_A_CRE_Y,
       N_A_BAL,
       N_M_INI_Y,
       N_M_INI,
       N_M_DEB,
       N_M_CRE,
       N_M_DEB_Y,
       N_M_CRE_Y,
       N_M_BAL,
       N_PM_INI_Y,
       N_PM_INI,
       N_PM_DEB,
       N_PM_CRE,
       N_PM_DEB_Y,
       N_PM_CRE_Y,
       N_PM_BAL,
       C_DC_Code,
       C_DV_KM_Cls)
      (select bal.C_Port_Code,
              bal.C_KM_Code,
              max(bal.C_KM_Name),
              max(bal.C_KM_Code_P),
              max(bal.C_KM_CODE_T),
              max(bal.C_KM_CODE_P_T),
              bal.N_DETAIL,
              bal.N_Way,
              to_char(to_date(v_date, 'yyyy-MM-dd'), 'yyyy') as C_Year,
              to_date(v_date, 'yyyy-MM-dd') as D_Bal,
              v_period as N_Period,
              sum(bal.N_A_INI_Y) as N_A_INI_Y,
              sum(bal.N_A_INI) as N_A_INI,
              sum(bal.N_A_DEB) as N_A_DEB,
              sum(bal.N_A_CRE) as N_A_CRE,
              sum(bal.N_A_DEB_Y) as N_A_DEB_Y,
              sum(bal.N_A_CRE_Y) as N_A_CRE_Y,
              sum(bal.N_A_BAL) as N_A_BAL,
              sum(bal.N_M_INI_Y) as N_M_INI_Y,
              sum(bal.N_M_INI) as N_M_INI,
              sum(bal.N_M_DEB) as N_M_DEB,
              sum(bal.N_M_CRE) as N_M_CRE,
              sum(bal.N_M_DEB_Y) as N_M_DEB_Y,
              sum(bal.N_M_CRE_Y) as N_M_CRE_Y,
              sum(bal.N_M_BAL) as N_M_BAL,
              sum(bal.N_PM_INI_Y) as N_PM_INI_Y,
              sum(bal.N_PM_INI) as N_PM_INI,
              sum(bal.N_PM_DEB) as N_PM_DEB,
              sum(bal.N_PM_CRE) as N_PM_CRE,
              sum(bal.N_PM_DEB_Y) as N_PM_DEB_Y,
              sum(bal.N_PM_CRE_Y) as N_PM_CRE_Y,
              sum(bal.N_PM_BAL) as N_PM_BAL,
              bal.C_DC_CODE,
              bal.C_DV_KM_Cls
         from (
               --- 取出年初数
               select a.C_Port_Code,
                       a.C_Year_Month,
                       a.D_Stock as D_BAL,
                       a.C_KM_Code,
                       a.C_KM_Name,
                       a.C_DC_CODE,
                       a.C_DV_KM_Cls,
                       c.N_WAY as N_WAY,
                       substr(a.C_KM_CODE, 1, Instr(a.C_KM_CODE, '.', -1) - 1) as C_KM_CODE_P,
                       NVL(b.C_KM_CODE, a.C_KM_CODE) as C_KM_CODE_T,
                       NVL(b.C_KM_CODE_P,
                           Substr(a.C_KM_CODE,
                                  1,
                                  Instr(a.C_KM_CODE, '.', -1) - 1)) as C_KM_CODE_P_T,
                       '年初' as C_Data_Type,
                       1 as N_DETAIL,
                       N_Amount as N_A_INI_Y,
                       0 as N_A_INI,
                       0 as N_A_DEB,
                       0 as N_A_CRE,
                       0 as N_A_DEB_Y,
                       0 as N_A_CRE_Y,
                       0 as N_A_BAL,
                       N_Orig_Money as N_M_INI_Y,
                       0 as N_M_INI,
                       0 as N_M_DEB,
                       0 as N_M_CRE,
                       0 as N_M_DEB_Y,
                       0 as N_M_CRE_Y,
                       0 as N_M_BAL,
                       N_Port_Money as N_PM_INI_Y,
                       0 as N_PM_INI,
                       0 as N_PM_DEB,
                       0 as N_PM_CRE,
                       0 as N_PM_DEB_Y,
                       0 as N_PM_CRE_Y,
                       0 as N_PM_BAL
                 from T_D_Ai_Stock a
                 left join (select C_KM_CODE,
                                   C_KM_CODE_P,
                                   C_DV_KM_Cls,
                                   C_DC_CODE
                              from T_F_SC_KM
                             where N_Detail = 1
                               and C_PLAN_CODE = v_plan_code) b on a.C_KM_CODE_T =
                                                                 b.C_KM_CODE
                                                             and a.C_DV_KM_Cls =
                                                                 b.C_DV_KM_Cls
                                                             and a.C_DC_CODE =
                                                                 b.C_DC_CODE
                 join (select C_DAI_CODE, N_FUND_WAY as N_WAY, C_DV_KM_Cls
                         From T_S_Dai_Item) c on a.C_Dai_Code = c.c_Dai_Code
                                             and a.C_DV_KM_Cls =
                                                 c.C_DV_KM_Cls
                where a.C_Port_Code = v_port
                  and Length(LTrim(a.C_KM_CODE)) > 0
                  and C_Year_Month = v_year || '00'
                  and D_Stock = to_date(v_year || '01-01', 'yyyy-MM-dd')
               --- 取出期初数
               union all
               select a.C_Port_Code,
                      a.C_Year_Month,
                      a.D_Stock as D_BAL,
                      a.C_KM_Code,
                      a.C_KM_Name,
                      a.C_DC_CODE,
                      a.C_DV_KM_Cls,
                      c.N_WAY as N_WAY,
                      substr(a.C_KM_CODE, 1, Instr(a.C_KM_CODE, '.', -1) - 1) as C_KM_CODE_P,
                      NVL(b.C_KM_CODE, a.C_KM_CODE) as C_KM_CODE_T,
                      NVL(b.C_KM_CODE_P,
                          Substr(a.C_KM_CODE,
                                 1,
                                 Instr(a.C_KM_CODE, '.', -1) - 1)) as C_KM_CODE_P_T,
                      '上期余额' as C_Data_Type,
                      1 as N_DETAIL,
                      0 as N_A_INI_Y,
                      N_Amount as N_A_INI,
                      0 as N_A_DEB,
                      0 as N_A_CRE,
                      0 as N_A_DEB_Y,
                      0 as N_A_CRE_Y,
                      N_Amount as N_A_BAL,
                      0 as N_M_INI_Y,
                      N_Orig_Money as N_M_INI,
                      0 as N_M_DEB,
                      0 as N_M_CRE,
                      0 as N_M_DEB_Y,
                      0 as N_M_CRE_Y,
                      N_Orig_Money as N_M_BAL,
                      0 as N_PM_INI_Y,
                      N_Port_Money as N_PM_INI,
                      0 as N_PM_DEB,
                      0 as N_PM_CRE,
                      0 as N_PM_DEB_Y,
                      0 as N_PM_CRE_Y,
                      N_Port_Money as N_PM_BAL
                 from T_D_Ai_Stock a
                 left join (select C_KM_CODE,
                                   C_KM_CODE_P,
                                   C_DV_KM_Cls,
                                   C_DC_CODE
                              from T_F_SC_KM
                             where N_Detail = 1
                               and C_PLAN_CODE = v_plan_code) b on a.C_KM_CODE_T =
                                                                 b.C_KM_CODE
                                                             and a.C_DV_KM_Cls =
                                                                 b.C_DV_KM_Cls
                                                             and a.C_DC_CODE =
                                                                 b.C_DC_CODE
                 join (select C_DAI_CODE, N_FUND_WAY as N_WAY, C_DV_KM_Cls
                         From T_S_Dai_Item) c on a.C_Dai_Code = c.c_Dai_Code
                                             and a.C_DV_KM_Cls =
                                                 c.C_DV_KM_Cls
                where a.C_Port_Code = v_port
                  and Length(LTrim(a.C_KM_CODE)) > 0
                  and C_Year_Month = v_year_month
                  and D_Stock = v_end_date
               --- 取出年累计发生额
               union all
               select a.C_Port_Code,
                      a.C_Year_Month,
                      a.D_Chk_Acc as D_BAL,
                      a.C_KM_Code,
                      a.C_KM_Name,
                      a.C_DC_CODE,
                      a.C_DV_KM_Cls,
                      c.N_WAY as N_WAY,
                      substr(a.C_KM_CODE, 1, instr(a.C_KM_CODE, '.', -1) - 1) as C_KM_CODE_P,
                      NVL(b.C_KM_CODE, a.C_KM_CODE) as C_KM_CODE_T,
                      NVL(b.C_KM_CODE_P,
                          substr(a.C_KM_CODE,
                                 1,
                                 instr(a.C_KM_CODE, '.', -1) - 1)) as C_KM_CODE_P_T,
                      '年累计发生' as C_Data_Type,
                      1 as N_DETAIL,
                      0 as N_A_INI_Y,
                      0 as N_A_INI,
                      0 as N_A_DEB,
                      0 as N_A_CRE,
                      (case
                        when a.N_Way = 1 then
                         a.N_Amount
                        else
                         0
                      end) as N_A_DEB_Y,
                      (case
                        when a.N_Way = -1 then
                         a.N_Amount
                        else
                         0
                      end) as N_A_CRE_Y,
                      0 as N_A_BAL,
                      0 as N_M_INI_Y,
                      0 as N_M_INI,
                      0 as N_M_DEB,
                      0 as N_M_CRE,
                      (case
                        when a.N_Way = 1 then
                         a.N_Orig_Money
                        else
                         0
                      end) as N_M_DEB_Y,
                      (case
                        when a.N_Way = -1 then
                         a.N_Orig_Money
                        else
                         0
                      end) as N_M_CRE_Y,
                      0 as N_M_BAL,
                      0 as N_PM_INI_Y,
                      0 as N_PM_INI,
                      0 as N_PM_DEB,
                      0 as N_PM_CRE,
                      (case
                        when a.N_Way = 1 then
                         a.N_Port_Money
                        else
                         0
                      end) as N_PM_DEB_Y,
                      (case
                        when a.N_Way = -1 then
                         a.N_Port_Money
                        else
                         0
                      end) as N_PM_CRE_Y,
                      0 as N_PM_BAL
                 from T_D_AI_ACT_VAL a
                 left join (select C_KM_CODE,
                                   C_KM_CODE_P,
                                   C_DV_KM_Cls,
                                   C_KM_NAME,
                                   C_DC_CODE
                              from T_F_SC_KM
                             where N_Detail = 1
                               and C_Plan_Code = v_plan_code) b on a.C_Port_Code = v_port
                                                             and a.C_KM_CODE_T =
                                                                 b.C_KM_CODE
                                                             and a.C_DV_KM_Cls =
                                                                 b.C_DV_KM_Cls
                                                             and a.C_DC_CODE =
                                                                 b.C_DC_CODE
                 join (select C_DAI_CODE, C_DV_KM_Cls, N_FUND_WAY as N_WAY
                         from T_S_Dai_Item) c on a.C_Dai_Code = c.c_Dai_Code
                                             and a.C_DV_KM_Cls =
                                                 c.C_DV_KM_Cls
                where a.C_Port_Code = v_port
                  and Length(LTrim(a.C_KM_CODE)) > 0
                  and (a.D_Chk_Acc between
                      to_date(v_year || '01-01', 'yyyy-MM-dd') and
                      to_date(v_date, 'yyyy-MM-dd'))
               --- 取出本期发生额和余额
               union all
               select a.C_Port_Code,
                      a.C_Year_Month,
                      a.D_Chk_Acc as D_BAL,
                      a.C_KM_Code,
                      a.C_KM_Name,
                      a.C_DC_CODE,
                      a.C_DV_KM_Cls,
                      c.N_WAY as N_WAY,
                      substr(a.C_KM_CODE, 1, instr(a.C_KM_CODE, '.', -1) - 1) as C_KM_CODE_P,
                      NVL(b.C_KM_CODE, a.C_KM_CODE) as C_KM_CODE_T,
                      NVL(b.C_KM_CODE_P,
                          substr(a.C_KM_CODE,
                                 1,
                                 instr(a.C_KM_CODE, '.', -1) - 1)) as C_KM_CODE_P_T,
                      '本期发生' as C_Data_Type,
                      1 as N_DETAIL,
                      0 as N_A_INI_Y,
                      0 as N_A_INI,
                      (case
                        when a.N_Way = 1 then
                         a.N_Amount
                        else
                         0
                      end) as N_A_DEB,
                      (case
                        when a.N_Way = -1 then
                         a.N_Amount
                        else
                         0
                      end) as N_A_CRE,
                      0 as N_A_DEB_Y,
                      0 as N_A_CRE_Y,
                      (case
                        when c.N_WAY = 1 then
                         a.N_Way * a.N_Amount
                        when c.N_WAY = -1 then
                         a.N_Way * a.N_Amount
                        else
                         0
                      end) as N_A_BAL,
                      0 as N_M_INI_Y,
                      0 as N_M_INI,
                      (case
                        when a.N_Way = 1 then
                         a.N_Orig_Money
                        else
                         0
                      end) as N_M_DEB,
                      (case
                        when a.N_Way = -1 then
                         a.N_Orig_Money
                        else
                         0
                      end) as N_M_CRE,
                      0 as N_M_DEB_Y,
                      0 as N_M_CRE_Y,
                      (case
                        when c.N_WAY = 1 then
                         a.N_Way * a.N_Orig_Money
                        when c.N_WAY = -1 then
                         a.N_Way * a.N_Orig_Money
                        else
                         0
                      end) as N_M_BAL,
                      0 as N_PM_INI_Y,
                      0 as N_PM_INI,
                      (case
                        when a.N_Way = 1 then
                         a.N_Port_Money
                        else
                         0
                      end) as N_PM_DEB,
                      (case
                        when a.N_Way = -1 then
                         a.N_Port_Money
                        else
                         0
                      end) as N_PM_CRE,
                      0 as N_PM_DEB_Y,
                      0 as N_PM_CRE_Y,
                      (case
                        when c.N_WAY = 1 then
                         a.N_Way * a.N_Port_Money
                        when c.N_WAY = -1 then
                         a.N_Way * a.N_Port_Money
                        else
                         0
                      end) as N_PM_BAL
                 from T_D_AI_ACT_VAL a
                 left join (select C_KM_CODE,
                                   C_KM_CODE_P,
                                   C_DV_KM_Cls,
                                   C_KM_NAME,
                                   C_DC_CODE
                              from T_F_SC_KM
                             where N_Detail = 1
                               and C_Plan_Code = v_plan_code) b on a.C_Port_Code = v_port
                                                             and a.C_KM_CODE_T =
                                                                 b.C_KM_CODE
                                                             and a.C_DV_KM_Cls =
                                                                 b.C_DV_KM_Cls
                                                             and a.C_DC_CODE =
                                                                 b.C_DC_CODE
                 join (select C_DAI_CODE, C_DV_KM_Cls, N_FUND_WAY as N_WAY
                         from T_S_Dai_Item) c on a.C_Dai_Code = c.c_Dai_Code
                                             and a.C_DV_KM_Cls =
                                                 c.C_DV_KM_Cls
                where a.C_Port_Code = v_port
                  and Length(LTrim(a.C_KM_CODE)) > 0
                  and (a.D_Chk_Acc between v_start_date and
                      to_date(v_date, 'yyyy-MM-dd'))) bal
        group by bal.C_Port_Code,
                 bal.C_KM_Code,
                 bal.C_DV_KM_Cls,
                 bal.N_DETAIL,
                 bal.N_WAY,
                 bal.C_DC_Code) order by bal.C_KM_CODE;

    while v_km_spec > 0 Loop
      begin
        insert into R_F_BM_BAL
          (C_Port_Code,
           C_KM_Code,
           C_KM_Name,
           C_KM_Code_P,
           C_KM_CODE_T,
           C_KM_CODE_P_T,
           N_DETAIL,
           N_Way,
           C_Year,
           D_Bal,
           N_Period,
           N_A_INI_Y,
           N_A_INI,
           N_A_DEB,
           N_A_CRE,
           N_A_DEB_Y,
           N_A_CRE_Y,
           N_A_BAL,
           N_M_INI_Y,
           N_M_INI,
           N_M_DEB,
           N_M_CRE,
           N_M_DEB_Y,
           N_M_CRE_Y,
           N_M_BAL,
           N_PM_INI_Y,
           N_PM_INI,
           N_PM_DEB,
           N_PM_CRE,
           N_PM_DEB_Y,
           N_PM_CRE_Y,
           N_PM_BAL,
           C_DC_Code,
           C_DV_KM_Cls)
          select y.*
            from (select bal.C_Port_Code,
                         GETLEVEL_KM(bal.C_KM_Code, v_km_spec) as C_KM_CODE,
                         max(NVL(km.C_KM_Name, ' ')) as C_KM_NAME,
                         NVL(GETLEVEL_KM(max(bal.C_KM_Code_P), v_km_spec - 1),
                             '[root]') as C_KM_CODE_P,
                         GETLEVEL_KM(max(bal.C_KM_CODE_T), v_km_spec) as C_KM_CODE_T,
                         NVL(GETLEVEL_KM(max(bal.C_KM_CODE_T), v_km_spec - 1),
                             '[root]') as C_KM_CODE_P_T,
                         0 as N_DETAIL,
                         bal.N_Way,
                         bal.C_Year,
                         bal.D_Bal,
                         bal.N_Period,
                         sum(0) as N_A_INI_Y,
                         sum(0) as N_A_INI,
                         sum(0) as N_A_DEB,
                         sum(0) as N_A_CRE,
                         sum(0) as N_A_DEB_Y,
                         sum(0) as N_A_CRE_Y,
                         sum(0) as N_A_BAL,
                         sum(case
                               when km.C_DC_Code = '***' then
                                0
                               else
                                N_M_INI_Y
                             end) as N_M_INI_Y,
                         sum(case
                               when km.C_DC_Code = '***' then
                                0
                               else
                                N_M_INI
                             end) as N_M_INI,
                         sum(case
                               when km.C_DC_Code = '***' then
                                0
                               else
                                N_M_DEB
                             end) as N_M_DEB,
                         sum(case
                               when km.C_DC_Code = '***' then
                                0
                               else
                                N_M_CRE
                             end) as N_M_CRE,
                         sum(case
                               when km.C_DC_Code = '***' then
                                0
                               else
                                N_M_DEB_Y
                             end) as N_M_DEB_Y,
                         sum(case
                               when km.C_DC_Code = '***' then
                                0
                               else
                                N_M_CRE_Y
                             end) as N_M_CRE_Y,
                         sum(case
                               when km.C_DC_Code = '***' then
                                0
                               else
                                N_M_BAL
                             end) as N_M_BAL,
                         sum(N_PM_INI_Y) as N_PM_INI_Y,
                         sum(N_PM_INI) as N_PM_INI,
                         sum(N_PM_DEB) as N_PM_DEB,
                         sum(N_PM_CRE) as N_PM_CRE,
                         sum(N_PM_DEB_Y) as N_PM_DEB_Y,
                         sum(N_PM_CRE_Y) as N_PM_CRE_Y,
                         sum(N_PM_BAL) as N_PM_BAL,
                         NVL(km.C_DC_Code, '***'),
                         bal.C_DV_KM_Cls
                    from R_F_BM_BAL bal
                    left join (select C_KM_Code, C_KM_Name, C_DC_Code
                                from T_F_Sc_Km a
                               where c_Plan_Code = v_port
                                 and not exists
                               (select '1'
                                        from R_F_BM_BAL c
                                       where c.c_km_code_T = a.c_km_code)) km on GETLEVEL_KM(bal.C_KM_Code_P_T,
                                                                                             v_km_spec) =
                                                                                 km.C_KM_Code
                   where bal.C_Port_Code = v_port
                     and GETKM_LEVEL(bal.C_KM_Code_P) = v_km_spec
                   group by bal.C_Port_Code,
                            GETLEVEL_KM(bal.C_KM_Code, v_km_spec),
                            bal.N_Way,
                            bal.C_Year,
                            bal.C_DV_KM_Cls,
                            bal.D_Bal,
                            bal.N_Period,
                            km.C_DC_Code) y
           where not exists
           (select '1' from R_F_BM_BAL where C_KM_Code = y.C_KM_Code);

        v_km_spec := v_km_spec - 1;

      end;
    end loop;

    --- 将余额临时表中的数据加载到电子对账数据表
    insert into tdzbalance
      (ffiletype,
       ffundcode,
       frpttype,
       fbdate,
       fedate,
       facctcode,
       fcurcode,
       fstartbal,
       fdebit,
       fcredit,
       fendbal,
       fbstartbal,
       fbdebit,
       fbcredit,
       fbendbal,
       fastartbal,
       fadebit,
       facredit,
       faendbal,
       fisdetail,
       fsn,
       f_j_toltal_amount,
       f_d_toltal_amount)
      select '1001' as ffiletype,
             v_fcode as ffundcode,
             '01' as frpttype,
             to_date(v_date, 'yyyy-MM-dd') as fbdate,
             to_date(v_date, 'yyyy-MM-dd') as fedate,
             C_KM_CODE as facctcode,
             C_DC_CODE as fcurcode,
             N_M_INI as fstartbal, -- 原币期初余额
             N_M_DEB as fdebit, -- 本期借方发生额(原币)
             N_M_CRE as fcredit, -- 本期贷方发生额(原币)
             N_M_BAL as fendbal, --期末余额(原币)
             N_PM_INI as fbstartbal, -- 本币期初余额
             N_PM_DEB as fbdebit, -- 本期借方发生额(本币)
             N_PM_CRE as fbcredit, -- 本期贷方发生额(本币)
             N_PM_BAL as fbendbal, --期末余额(本币)
             N_A_INI as fastartbal, -- 期初余额(数量)
             N_A_DEB as fadebit, -- 本期借方发生额(数量)
             N_A_CRE as facredit, -- 本期贷方发生额(数量)
             N_A_BAL as faendbal, -- 期末余额(数量)
             N_DETAIL as fisdetail,
             v_fsnnum as fsn,
             N_A_DEB_Y as f_j_toltal_amount,
             N_A_CRE_Y as f_d_toltal_amount
        from R_F_BM_BAL;
     v_rowcount := sql%rowcount;

  -- 如果已经产生了电子对账数据，则需要向电子对账信息表中添加一条待发送电子对账信息
  if v_rowcount > 0 then
     pro_dz_tdzbbinfo(v_port,to_date(v_date,'yyyy_MM-dd'),v_fsnnum,i_user,0,v_filetype,v_rpttype);
  end if;
  end if;
  COMMIT;
  o_res := 'Success';
  o_res_detail := '产生电子对账余额表数据成功';
EXCEPTION
  WHEN OTHERS THEN
    o_res := 'Fail';
    o_res_detail := sqlerrm;
    ROLLBACK;
end;
