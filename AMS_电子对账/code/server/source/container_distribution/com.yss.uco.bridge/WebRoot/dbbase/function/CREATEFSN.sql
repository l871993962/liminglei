create or replace function createFsn(i_date in varchar2) return varchar2 as
  /**
   *版本：v1.0.0.7
   *用途：生成报文序号
   *参数：输入参数统计日期i_date
   *返回：返回生成的报文序号
   *修改记录：
   *   chenyoulong 20121226 创建
   **/

  /**
  * 定义返回的报文序号
  **/
  v_fsnnum varchar2(30);

  /**
  * 定义日期，并将传递进来的参数datestr赋值给他
  **/
  v_date varchar2(20) := i_date;

  /**
   * 处理逻辑:
   *此函数是根据日期，查询该日期中报文基本信息处理表（TDZBBINFO）中的最大的fsn号，
   *如果报文基本信息表中不存在数据，则返回初始的报文序号
   *报文序号产生规则：‘DZ’+ 日期（yyyyMMdd）+ 编号；
   *若，当天的日期为2012-12-21日，从TDZBBINFO表中取到的最大的报文序号为DZ2012122100004,
   *则 当前的报文序号在原来的序号基础上 + 1 ，即为DZ2012122100005,
   *若当天TDZBBINFO表中不存在数据，则返回初始报文编号 ： DZ2012122100000,
  **/

begin

  declare

    v_fsnSque varchar2(30);

    v_fsnlength number(2);

    v_fsn varchar2(30);

    v_tempStr varchar2(20);

    /**
    * 根据日期从报文处理信息表中(TDZBBINFO)，获取当天最大的报文序号（Fsn），放在游标fsnCursor中
    **/
    Cursor fsn_cur is
      select max(fsn) as fsn
        from TDZBBINFO
       where FDATE = to_date(v_date, 'yyyy-MM-dd')
       order by FSN desc;

    /**
    * 开始使用游标取数据
    **/
  begin
    -- 打开游标
    open fsn_cur;
    Loop
      fetch fsn_cur
        into v_fsnSque;
      exit when fsn_cur%notfound;

      -- 如果不为空，则原来最大报文序号+1 为当前序号
      if v_fsnSque is not null then
        -- 获取后5位报文序号
        v_fsn := substr(v_fsnSque, 11, 5);

        -- 后5位报文序号加 + 1，并转换成字符
        v_fsn := to_char(to_number(v_fsn) + 1);

        -- 获取+1后的报文序号的长度
        v_fsnlength := length(v_fsn);

        -- 如果新的后5位报文序号长度小于5，则在前面补0，直到等于5位为止
        if v_fsnlength < 5 then
          v_fsnlength := 5 - v_fsnlength - 1;
          for i in 0 .. v_fsnlength loop
            v_tempStr := v_tempStr || '0';
          end loop;
          v_fsn := v_tempStr || v_fsn;
        end if;
        v_fsnnum := 'DZ' ||
                  to_char(to_date(v_date, 'yyyy-MM-dd'), 'yyyyMMdd') || v_fsn;
      else
        -- 如果为空，则后五位默认为00000
        v_fsnnum := 'DZ' ||
                  to_char(to_date(v_date, 'yyyy-MM-dd'), 'yyyyMMdd') ||
                  '00000';
      end if;
    End loop;
    -- 关闭游标
    close fsn_cur;
  end;
  -- 返回新的报文序号
  return v_fsnnum;
end createFsn;
