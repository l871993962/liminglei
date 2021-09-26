CREATE OR REPLACE PROCEDURE pro_SetOther(i_Cury    in varchar2,
                                         i_Mkt     in varchar2,
                                         i_Issue   in varchar2,
                                         i_PortCls in varchar2,
                                         v_DAE     in out nocopy DAE_ROW_TYPE) as
  /*
  v_Cury:科目币种
  v_Mkt:交易市场
  v_Issue:投资分类
  v_PortCls:分级组合 */
  v_Cury    varchar2(20) := i_Cury;
  v_Mkt     varchar2(20) := i_Mkt;
  v_Issue   varchar2(20) := i_Issue;
  v_PortCls varchar2(20) := i_PortCls;
begin

  /*处理一下币种的问题*/
  if (v_DAE.C_DC_Code is null or v_DAE.C_DC_Code = ' ' or
     v_DAE.C_DC_Code = '<DC>' or v_DAE.C_DC_Code = '***' or
     v_DAE.C_DC_Code = 'DC') then
    v_DAE.C_DC_Code := v_Cury;
  end if;
  if (v_DAE.C_DVA_CURR = 'DC' and v_DAE.C_DVA_CURR_V = '<DC>') then
    v_DAE.C_DVA_CURR_V := v_DAE.C_DC_CODE;
  end if;

  /*处理交易市场 条件：当当前的核算元素是市场，或者 固定项目为市场*/
  if (v_DAE.C_DVA_CURR = 'MKT') then
    if (v_Mkt = 'H' /*上交所*/
       ) then
      v_DAE.C_MKT_CODE := 'SS';
    elsif (v_Mkt = 'S' /*深交所*/
          ) then
      v_DAE.C_MKT_CODE := 'SZ';
    elsif (v_Mkt = 'K' /*港交所*/
          ) then
      v_DAE.C_MKT_CODE := 'HK';
    elsif (v_Mkt = 'U' /*纽交所*/
          ) then
      v_DAE.C_MKT_CODE := 'N';
    elsif (v_Mkt = 'N' /*纳斯达克*/
          ) then
      v_DAE.C_MKT_CODE := 'OQ';
    elsif (v_Mkt = 'Z' /*银行间*/
          ) then
      v_DAE.C_MKT_CODE := 'CY';
    elsif (v_Mkt = 'Y' /*场外*/
          ) then
      v_DAE.C_MKT_CODE := 'CW';
    else
      v_DAE.C_MKT_CODE := ' ';
    end if;
    if (v_DAE.C_DVA_CURR = 'MKT' and
       (v_DAE.C_DVA_CURR_V is null or v_DAE.C_DVA_CURR_V = ' ' or
       v_DAE.C_DVA_CURR_V = '<MKT>' or v_DAE.C_DVA_CURR_V = 'MKT')) then
      v_DAE.C_DVA_CURR_V := v_DAE.C_MKT_CODE;
    end if;
  end if;
  /*处理投资分类*/
  if (v_DAE.C_DVA_CURR = 'INVEST_CLS') then
    if (v_Issue = 'J' /*为交易而持有*/
       ) then
      v_DAE.C_DV_INVEST_CLS := 'IC_TD';
    elsif (v_Issue = 'F' /*为出售而持有*/
          ) then
      v_DAE.C_DV_INVEST_CLS := 'IC_FS';
    elsif (v_Issue = 'C' /*持有到期*/
          ) then
      v_DAE.C_DV_INVEST_CLS := 'IC_HM';
    end if;
    if (v_DAE.C_DVA_CURR = 'INVEST_CLS' and
       (v_DAE.C_DVA_CURR_V is null or v_DAE.C_DVA_CURR_V = ' ' or
       v_DAE.C_DVA_CURR_V = '<INVEST_CLS>' or
       v_DAE.C_DVA_CURR_V = 'INVEST_CLS')) then
      v_DAE.C_DVA_CURR_V := v_DAE.C_DV_INVEST_CLS;
    end if;
  end if;
  /*处理组合分级*/
  if (length(ltrim(v_PortCls)) > 0 and v_DAE.C_DVA_CURR = 'PORT_CLS') then
    v_DAE.C_PORT_CLS_CODE := v_PortCls;
    if (v_DAE.C_DVA_CURR = 'PORT_CLS' and
       (v_DAE.C_DVA_CURR_V is null or v_DAE.C_DVA_CURR_V = ' ' or
       v_DAE.C_DVA_CURR_V = '<PORT_CLS>' or
       v_DAE.C_DVA_CURR_V = 'PORT_CLS')) then
      v_DAE.C_DVA_CURR_V := v_DAE.C_PORT_CLS_CODE;
    end if;
  end if;
end pro_SetOther;
