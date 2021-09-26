create or replace procedure Prc_Dz_JjHkzl(i_port       in varchar2,
                                          i_date       in varchar2,
                                          i_user       in varchar2,
                                          i_iden       in varchar2,
                                          i_hkdm       in varchar2,
                                          i_bwlx       in varchar2,
                                          o_res        out varchar2,
                                          o_res_detail out varchar2) as
  /**
  *版本：v1.0.0.8
  *用途：将估值系统中划款指令数据表（t_c_cm_mgr)根据规则填充到电子划款指令数据表（jjhkzl)中
  *参数：输入参数组合代码i_port、统计日期i_date,用户代码i_user,序列号 i_iden,报文类型i_bwlx,划款代码 i_hkdm；输出参数：处理结果：o_res（成功 or 失败），o_res_detail 处理结果明细
  *修改记录：
  *   chenyoulong 20130123 创建
  **/

  -- 组合代码
  v_port varchar2(20) := i_port;
  -- 开始日期
  v_date varchar2(20) := i_date;
  -- 序列号
  v_iden varchar2(30) := i_iden;
  -- 划款代码
  v_hkdm varchar2(30) := i_hkdm;
  -- 报文序号
  v_fsnnum nvarchar2(30);
  -- 序列号，提交给托管行的业务流水号
  v_seqno varchar2(30);
  -- 获取生成的数据的行数
  v_rowcount number(10) := 0;
  -- 文件类型
  v_filetype varchar2(4) := i_bwlx;
  -- 报表类型
  v_rpttype varchar2(2) := '01';
  -- 基金代码
  v_fcode varchar(20) := i_port;
begin
  -- 2. 获取fsn代码
  v_fsnnum := CREATEFSN(v_date);
  -- 获取基金代码
  v_fcode := GETFUNDCODE(v_port);
  -- 获取序列号，提交给托管行的业务流水号
  v_seqno := createSeq_no(v_date, v_fcode);

  -- 修改t_c_cm_mgr表当前数据发送的状态
  update t_c_cm_mgr a set a.c_dv_send_state = 'SEND',a.c_send_time = to_char(sysdate,'yyyy-MM-dd hh24:mi:ss') where a.c_iden = v_iden;

  -- 3. 产生电子划款指令数据
  insert into jjhkzl
    (fzldate,
     fhkdate,
     fnum,
     fdzdate,
     fhkren,
     fhkje,
     fhkremark,
     fskren,
     fskbank,
     fskacct,
     fskyt,
     fdelbz,
     fzltype,
     fhktype,
     fhktype2,
     fhkbank,
     fhkacct,
     fsn,
     seq_no,
     result,
     remark,
     checker_code,
     timestmp,
     operation_type,
     fsh,
     fzzr,
     fchk,
     fpk_bookmark,
     fyhsn,
     fhkremarkn,
     fzqdm,
     ffhbz,
     ffhr,
     fxgr)
    select a.d_ord as fzldate,
           a.d_sett as fhkdate,
           a.n_ord_num as fnum,
           a.d_sett as fhkdate,
           a.c_open_acc_name_i as fhkren,
           a.n_ord_money as fhkje,
           nvl(a.c_usage,' ') as fhkremark,
           a.c_open_acc_name as fskren,
           a.c_open_addr as fskbank,
           a.c_open_acc_no as fskacct,
           nvl(a.c_usage,' ') as fskyt,
           'N' as fdelbz,
           case
             when a.c_dv_ord_type = 'ZLLX_SK' then
              1
             else
              0
           end as fzltype,
           'F' as fhktype,
           a.c_hk_name as fhktype2,
           a.c_open_addr_i as fhkbank,
           a.c_open_acc_no_i as fhkacct,
           v_fsnnum as fsn,
           v_seqno as seq_no,
           '' as result,
           '' as remark,
           '' as checker_code,
           to_char(sysdate,'yyyy-MM-dd hh24:mi:ss') as timestmp,
           v_hkdm as operation_type,
           1 as fsh,
           nvl(a.c_create_by,i_user) as fzzr,
           nvl(a.c_check_by,i_user) as fchk,
           ' ' as fpk_bookmark,
           ' ' as fyhsn,
           a.c_desc as fhkremarkn,
           '' as fzqdm,
           '' as ffhbz,
           '' as ffhr,
           '' as fxgr
      from (select a.d_ord,
                   a.c_port_code,
                   a.n_ord_num,
                   a.c_ord_time,
                   a.d_pay,
                   a.c_pay_time,
                   a.d_sett,
                   a.c_sett_time,
                   a.n_ord_money,
                   a.c_open_acc_name,
                   a.c_open_addr,
                   a.c_open_acc_no,
                   a.c_open_acc_name_i,
                   a.c_open_addr_i,
                   a.c_open_acc_no_i,
                   a.c_sys_code,
                   a.c_usage,
                   a.c_desc,
                   a.c_dv_ord_type,
                   a.c_dv_send_state,
                   a.c_send_time,
                   a.c_pay_type,
                   a.n_check_state,
                   a.c_create_by,
                   a.c_check_by,
                   b.c_hk_name
              from t_c_cm_mgr a
              join (select c_hk_code, c_hk_name from t_s_hk_type) b on b.c_hk_code =
                                                                       a.c_pay_type
             where a.c_iden = v_iden) a;
  v_rowcount := sql%rowcount;

  -- 如果已经产生了电子对账数据，则需要向电子对账信息表中添加一条待发送电子对账信息
  if v_rowcount > 0 then
    pro_dz_tdzbbinfo(v_port,
                     to_date(v_date, 'yyyy_MM-dd'),
                     v_fsnnum,
                     i_user,
                     1,
                     v_filetype,
                     v_rpttype);
  end if;
  COMMIT;
  o_res        := 'Success';
  o_res_detail := '产生电子划款指令数据成功';
  -- 如果发生异常则回滚
EXCEPTION
  WHEN OTHERS THEN
    o_res        := 'Fail';
    o_res_detail := sqlerrm;
    dbms_output.put_line(sqlerrm);
    ROLLBACK;
end;
