create or replace function createSeq_no(i_date in varchar2,i_fundcode in varchar2) return varchar2 as
  /**
   *版本：v1.0.0.8
   *用途：生成序列号，提交给托管行的业务流水号
   *参数：输入参数统计日期i_date,基金代码
   *返回：返回生成的托管行的业务流水号
   *修改记录：
   *   chenyoulong 20130123 创建
   **/

  /**
  * 定义托管行的业务流水号
  **/
  v_seqno varchar2(30);

  /**
  * 定义日期，并将传递进来的参数datestr赋值给他
  **/
  v_date varchar2(20) := i_date;

  /**
   * 定义基金代码,将传递进来的参数datestr赋值给他
   **/
 v_fundcode varchar2(30):= i_fundcode;

  /**
   * 处理逻辑:
   * SEQ_NO格式：“ZL+组合代码＋日期＋流水号”， 22位，例如ZL00120100830000000001
  **/

begin

  declare

    v_seqSque varchar2(30);

    v_seqlength number(2);

    v_seq varchar2(30);

    v_tempStr varchar2(20);

    /**
    * 根据日期从报文处理信息表中(TDZBBINFO)，获取当天最大的报文序号（Fsn），放在游标fsnCursor中
    **/
    Cursor seq_cur is
     select max(seq_no) as seq_no from jjhkzl a where a.FZlDate = to_date(v_date, 'yyyy-MM-dd');

    /**
    * 开始使用游标取数据
    **/
  begin
    -- 打开游标
    open seq_cur;
    Loop
      fetch seq_cur
        into v_seqSque;
      exit when seq_cur%notfound;

      -- 如果不为空，则原来最大报文序号+1 为当前序号
      if v_seqSque is not null then
        -- 获取后5位报文序号
        v_seq := substr(v_seqSque,-9,9);

        -- 后5位报文序号加 + 1，并转换成字符
        v_seq := to_char(to_number(v_seq) + 1);

        -- 获取+1后的报文序号的长度
        v_seqlength := length(v_seq);

        -- 如果新的后5位报文序号长度小于5，则在前面补0，直到等于5位为止
        if v_seqlength < 9 then
          v_seqlength := 9 - v_seqlength - 1;
          for i in 0 .. v_seqlength loop
            v_tempStr := v_tempStr || '0';
          end loop;
          v_seq := v_tempStr || v_seq;
        end if;
        v_seqno := 'ZL' || v_fundcode ||
                  to_char(to_date(v_date, 'yyyy-MM-dd'), 'yyyyMMdd') || v_seq;
      else
        -- 如果为空，则后五位默认为00000
        v_seqno := 'ZL' || v_fundcode ||
                  to_char(to_date(v_date, 'yyyy-MM-dd'), 'yyyyMMdd') ||
                  '000000001';
      end if;
    End loop;
    -- 关闭游标
    close seq_cur;
  end;
  -- 返回新的报文序号
  return v_seqno;
end createSeq_no;
