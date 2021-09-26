create or replace procedure Prc_Dz_TDzAccount(i_port       in varchar2,
                                              i_date       in varchar2,
                                              i_user       in varchar2,
                                              o_res        out varchar2,
                                              o_res_detail out varchar2) as
  /**
  *版本：v1.0.0.7
  *用途：将估值表系统中科目表（T_F_SC_KM）数据，根据条件，填充到电子对账科目中间表(TDzAccount)中
  *参数：输入参数组合代码i_port、统计日期i_date,用户代码i_user；输出参数：处理结果：o_res（成功 or 失败），o_res_detail 处理结果明细
  *修改记录：
  *   chenyoulong 20121226 创建
  **/

  -- 组合代码
  v_port varchar2(20) := i_port;
  -- 开始日期
  v_date varchar2(20) := i_date;
  -- 报文序号
  v_fsnnum nvarchar2(30);
  -- 获取生成的数据的行数
  v_rowcount number(10) := 0;
  -- 文件类型
  v_filetype varchar2(4) := '1031';
  -- 报表类型
  v_rpttype varchar2(2) := ' ';
  -- 基金代码
  v_fcode varchar(20) := i_port;
begin
  -- 1. 在产生电子对账科目数据前，先根据组合、日期删除掉电子对账估值表中重复数据，避免出现重复数据
 /* delete from TDzAccount a
   where a.ffundcode = v_port
     and a.fbdate = to_date(v_date, 'yyyy-MM-dd');*/
  -- 2. 获取fsn代码
  v_fsnnum := CREATEFSN(v_date);
  -- 获取基金代码
  v_fcode := GETFUNDCODE(v_port);

  -- 3. 产生电子对账科目数据
  insert into TDZACCOUNT
    (FFILETYPE,
     FFUNDCODE,
     FRPTTYPE,
     FBDATE,
     FEDATE,
     FACCTCODE,
     FACCTNAME,
     FACCTLEVEL,
     FACCTPARENT,
     FACCTDETAIL,
     FACCTCLASS,
     FBALDC,
     FSN)
    (select '1031' as FFILETYPE,
            v_fcode as FFUNDCODE,
            ' ' as FRPTTYPE,
            to_date(v_date, 'yyyy-MM-dd') as fbdate,
            to_date(v_date, 'yyyy-MM-dd') as fedate,
            C_KM_CODE as FACCTCODE,
            C_KM_NAME as FACCTNAME,
            to_number(GETKM_LEVEL(C_KM_CODE)) as FACCTLEVEL,
            C_KM_CODE_P as FACCTPARENT,
            N_DETAIL as FACCTDETAIL,
            case
              when C_DV_KM_CLS = 'KC_ZC' then
               '1'
              when C_DV_KM_CLS = 'KC_FZ' then
               '2'
              when C_DV_KM_CLS = 'KC_GT' then
               '3'
              when C_DV_KM_CLS = 'KC_QY' then
               '4'
              when C_DV_KM_CLS = 'KC_SY' then
               '5'
              else
               '6'
            end as FACCTCLASS,
            case
              when C_DV_JD_WAY = 'JD_J' then
               '1'
              when C_DV_JD_WAY = 'JD_D' then
               '-1'
              else
               '0'
            end as FBALDC,
            v_fsnnum as fsn
       from (select a.*
               from T_F_SC_KM a
               join (select C_PLAN_CODE
                      from T_E_EXEC_PLAN_RELA
                     where C_PLAN_Type = 'AO_LEVEL'
                       and n_check_state = 1
                       and C_PORT_CODE = v_port
                       and d_begin <= to_date(v_date, 'yyyy-MM-dd')
                       and d_end >= to_date(v_date, 'yyyy-MM-dd')) b on a.c_plan_code =
                                                                        b.c_plan_code
              where a.n_check_state = 1));
  v_rowcount := sql%rowcount;

  -- 如果已经产生了电子对账数据，则需要向电子对账信息表中添加一条待发送电子对账信息
  if v_rowcount > 0 then
     pro_dz_tdzbbinfo(v_port,to_date(v_date,'yyyy_MM-dd'),v_fsnnum,i_user,0,v_filetype,v_rpttype);
  end if;
  COMMIT;
  o_res        := 'Success';
  o_res_detail := '产生电子对账科目数据成功';
  -- 如果发生异常则回滚
EXCEPTION
  WHEN OTHERS THEN
    o_res        := 'Fail';
    o_res_detail := sqlerrm;
    ROLLBACK;
end;
